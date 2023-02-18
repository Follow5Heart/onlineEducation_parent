package com.zty.onlineedu.edu.controller.admin;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.edu.entity.form.CourseInfoForm;
import com.zty.onlineedu.edu.service.EduCourseService;
import com.zty.onlineedu.service.base.exceptions.GeneralException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zty
 * @Date 2022/12/3 14:02
 */
@Log4j2
@CrossOrigin
@RestController
@Api(tags="课程管理")
@RequestMapping("/admin/edu/course")
public class CourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation("新增课程")
    @PostMapping("/saveCourseInfo")
    public Result saveCourseInfo(@ApiParam("课程基本信息")
                                 @RequestBody CourseInfoForm courseInfoForm){
        try{
            String courseId = eduCourseService.saveCourseInfo(courseInfoForm);
            return Result.ok().data("courseId",courseId).message("保存成功！");

        }catch(Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.SAVE_COURSE_DATA_ERROR);

        }

    }

    @ApiOperation("根据ID查询课程")
    @GetMapping("/getCourseInfo/{id}")
    public Result getCourseInfo(@ApiParam(value = "课程ID" ,required = true) @PathVariable String id){
        try{
            CourseInfoForm courseInfoForm=eduCourseService.getCourseInfo(id);
            if(courseInfoForm!=null){
                return Result.ok().data("courseInfo",courseInfoForm);

            }else{
                return Result.ok().message("数据不存在");
            }

        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.GET_COURSE_BYID_ERROR);

        }



    }


}

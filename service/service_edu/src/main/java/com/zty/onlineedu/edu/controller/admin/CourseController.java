package com.zty.onlineedu.edu.controller.admin;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.edu.pojo.dto.CourseInfoFormDto;
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
                                 @RequestBody CourseInfoFormDto courseInfoFormDto){
        try{
            String courseId = eduCourseService.saveCourseInfo(courseInfoFormDto);
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
            CourseInfoFormDto courseInfoFormDto =eduCourseService.getCourseInfo(id);
            if(courseInfoFormDto !=null){
                return Result.ok().data("courseInfo", courseInfoFormDto);

            }else{
                return Result.ok().message("数据不存在");
            }

        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.GET_COURSE_BYID_ERROR);

        }

    }

    @ApiOperation(value = "更新课程")
    @PutMapping("/updateCourse")
    public Result updateCourse(@ApiParam(value = "课程基本信息") @RequestBody CourseInfoFormDto courseInfoFormDto){
        try{
            eduCourseService.updateCourse(courseInfoFormDto);
            return Result.ok().message("更新成功");
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.UPDATE_COURSE_ERROR);
        }

    }


}

package com.zty.onlineedu.edu.controller.api;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.edu.pojo.entity.EduTeacher;
import com.zty.onlineedu.edu.pojo.vo.CourseVo;
import com.zty.onlineedu.edu.service.EduCourseService;
import com.zty.onlineedu.edu.service.EduTeacherService;
import com.zty.onlineedu.service.base.exceptions.GeneralException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zty
 * @Date 2023/4/9 14:08
 *  首页接口-前端服务器接口
 */
@Api(tags = "首页")
@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/api/edu/index")
public class ApiIndexController {
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;


    @ApiOperation("课程和讲师的首页数据")
    @GetMapping("/getIndexData")
    public Result getIndexData(){
        try{
            //获取热门课程数据
            List<CourseVo> courseList= eduCourseService.getHotCourse();

            //获取热门讲师数据
            List<EduTeacher> teacherList=eduTeacherService.getHotTeacher();

            return Result.ok().data("courseList",courseList).data("teacherList",teacherList);
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.GET_INDEX_DATA_ERROR);

        }




    }

}

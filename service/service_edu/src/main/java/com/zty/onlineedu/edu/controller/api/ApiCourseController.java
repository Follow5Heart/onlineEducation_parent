package com.zty.onlineedu.edu.controller.api;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.edu.pojo.query.WebCourseQueryParam;
import com.zty.onlineedu.edu.pojo.vo.CourseVo;
import com.zty.onlineedu.edu.service.EduCourseService;
import com.zty.onlineedu.service.base.exceptions.GeneralException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zty
 * @Date 2023/4/2 14:27
 * 前端服务器nuxt需求的接口**
 */
@Api(tags = "课程")
@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/api/edu/course")
public class ApiCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation("课程列表查询")
    @GetMapping("getCourseList")
    public Result getCourseList(@ApiParam(value = "前端服务器课程查询对象") WebCourseQueryParam webCourseQueryParam){
        try{
            List<CourseVo> courseVoList= eduCourseService.getCourseList(webCourseQueryParam);
            return Result.ok().data("CourseList",courseVoList);
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.WEB_GET_COURSE_LIST_ERROR);

        }

    }

}

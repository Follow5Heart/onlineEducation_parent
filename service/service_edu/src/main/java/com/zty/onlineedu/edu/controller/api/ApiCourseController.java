package com.zty.onlineedu.edu.controller.api;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.edu.pojo.query.WebCourseQueryParam;
import com.zty.onlineedu.edu.pojo.vo.ChapterVo;
import com.zty.onlineedu.edu.pojo.vo.CourseVo;
import com.zty.onlineedu.edu.pojo.vo.WebCourseVo;
import com.zty.onlineedu.edu.service.EduChapterService;
import com.zty.onlineedu.edu.service.EduCourseService;
import com.zty.onlineedu.service.base.exceptions.GeneralException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private EduChapterService eduChapterService;

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

    @ApiOperation("课程详情页信息")
    @GetMapping("getWebCourseInfo/{courseId}")
    public Result getWebCourseInfo(@ApiParam(value = "课程Id",required = true) @PathVariable String courseId){
        try{
            WebCourseVo webCourseInfo=eduCourseService.getWebCourseInfo(courseId);
            List<ChapterVo> nestedListByCourseId = eduChapterService.getNestedListByCourseId(courseId);
            return Result.ok().data("WebCourseInfo",webCourseInfo).data("courseNextList",nestedListByCourseId);
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.WEB_GET_COURSE_INFO_ERROR);

        }

    }

}

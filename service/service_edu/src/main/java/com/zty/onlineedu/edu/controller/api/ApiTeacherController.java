package com.zty.onlineedu.edu.controller.api;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.edu.pojo.entity.EduTeacher;
import com.zty.onlineedu.edu.pojo.query.TeacherQueryParam;
import com.zty.onlineedu.edu.service.EduTeacherService;
import com.zty.onlineedu.service.base.exceptions.GeneralException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author zty
 * @Date 2023/4/1 10:35
 * 前端服务器nuxt需求的接口*
 */
@Api(tags = "讲师")
@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/api/edu/teacher")
public class ApiTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("讲师列表")
    @GetMapping("/list")
    public Result teacherList(){
        //统一格式：try{}catch(Exception e){log.error(""),throw new GeneralException(ResultCodeEnum r)}
        try{
            TeacherQueryParam teacherQueryParam = new TeacherQueryParam();
            List<EduTeacher> teacherList=eduTeacherService.teacherList(teacherQueryParam);
            return Result.ok().data("items", teacherList);
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.TEACHER_LIST_ERROR);
        }

    }

    @ApiOperation("获取讲师")
    @GetMapping("/get/{id}")
    public Result getTeacherById(@ApiParam(value = "讲师id",required = true) @PathVariable("id") String id){
        try{

            Map<String,Object> teacherList=eduTeacherService.getTeacherById(id);
            return Result.ok().data("items", teacherList);
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.GET_TEACHER_DATA_ERROR);
        }

    }



}

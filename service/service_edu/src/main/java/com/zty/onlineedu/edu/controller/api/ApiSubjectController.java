package com.zty.onlineedu.edu.controller.api;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.edu.pojo.vo.NextedSubjectVo;
import com.zty.onlineedu.edu.service.EduSubjectService;
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
 * @Date 2023/4/2 17:06
 */
@Api(tags = "课程分类")
@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/api/edu/subject")
public class ApiSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @ApiOperation("获取嵌套课程")
    @GetMapping("/nextedSubject")
    public Result nextedSubject(){
        try{
            List<NextedSubjectVo> nestedSubjectList=eduSubjectService.nextedSubject();
            return Result.ok().data("items",nestedSubjectList).message("获取嵌套课程成功");
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.NEXTED_SUBJECT_DATA_ERROR);
        }

    }
}

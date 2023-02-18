package com.zty.onlineedu.edu.controller.admin;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.edu.entity.EduSubject;
import com.zty.onlineedu.edu.entity.vo.NextedSubjectVo;
import com.zty.onlineedu.edu.service.EduSubjectService;
import com.zty.onlineedu.service.base.exceptions.GeneralException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author zty
 * @Date 2022/12/3 14:02
 */
@CrossOrigin //容许跨域
@Api(tags  = "课程分类管理")
@RestController
@Log4j2
@RequestMapping("/admin/edu/subject")
public class SubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @ApiOperation("Excel批量导入课程分类")
    @PostMapping("/batchImportExcel")
    public Result importExcel(@ApiParam(value = "Excel对象",required = true)
                            @RequestParam("file") MultipartFile file){
        try{
            eduSubjectService.batchImport(file);
            return Result.ok().message("Excel批量导入成功");
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);

        }

    }

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

    @ApiOperation("课程当前级的课程分类")
    @GetMapping("/getCurrentSubjectList")
    public Result getCurrentSubjectList(@ApiParam("课程分类父级id")
                                        @RequestParam("parentId") String parentId){
        try{
            List<EduSubject> currentSubjectList=eduSubjectService.getCurrentSubjectList(parentId);
            return Result.ok().data("currentSubjectList",currentSubjectList);
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.GET_CURRENT_SUBJECT_DATA_ERROR);

        }

    }
}

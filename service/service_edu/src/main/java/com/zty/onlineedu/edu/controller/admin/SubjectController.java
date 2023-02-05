package com.zty.onlineedu.edu.controller.admin;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.edu.service.EduSubjectService;
import com.zty.onlineedu.service.base.exceptions.GeneralException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author zty
 * @Date 2022/12/3 14:02
 */
@CrossOrigin //容许跨域
@Api(tags  = "课程分类管理")
@RestController
@Log4j2
@RequestMapping("/edu/subject")
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
}

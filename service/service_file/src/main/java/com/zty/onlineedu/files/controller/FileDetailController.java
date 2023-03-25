package com.zty.onlineedu.files.controller;

import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.files.service.FileDetailService;
import com.zty.onlineedu.service.base.exceptions.GeneralException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeUnit;


/**
 * @Author zty
 * @Date 2023/1/15 20:51
 */
@Api(tags="文件管理")
@CrossOrigin
@Log4j2
@RestController
@RequestMapping("/service-file/files")
public class FileDetailController {
    /**
     * 使用spring file storage 就要注意实例
     */
    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileDetailService fileDetailService;

    @ApiOperation("文件上传服务")
    @PostMapping("/upload")
    public Result uploadFile(@ApiParam(value = "文件对象", required = true)
                             @RequestParam("file") MultipartFile file) {
        try{
            FileInfo fileInfo = fileStorageService.of(file).upload();
            return fileInfo == null ? Result.ok().message("文件上传失败！") : Result.ok().data("fileInfo",fileInfo).message("文件上传成功");
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.FILE_UPLOAD_ERROR);

        }


    }

    @ApiOperation("文件删除功能")
    @DeleteMapping("/delete")
    public Result deleteFile(@ApiParam(value = "文件id",required=true) @RequestBody String fileId){
        try{
            boolean result=fileDetailService.deleteFile(fileId);
            if (result){
                return Result.ok().message("删除成功");
            }else{
                return Result.error().message("删除失败");
            }

        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.FILE_DELETE_ERROR);

        }

    }

    /**
     * 用于微服务之间调用的测试方法
     * @return
     */
    @ApiOperation(value = "测试")
    @GetMapping("/test")
    public Result test(){
           log.info("file test被调用");
           try{
               TimeUnit.SECONDS.sleep(3);
           }catch (Exception e){
               e.printStackTrace();

           }
           return Result.ok();

    }


}

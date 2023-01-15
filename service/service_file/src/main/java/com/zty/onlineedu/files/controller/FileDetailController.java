package com.zty.onlineedu.files.controller;

import cn.xuyanwu.spring.file.storage.FileInfo;
import cn.xuyanwu.spring.file.storage.FileStorageService;
import com.zty.onlineedu.common.base.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author zty
 * @Date 2023/1/15 20:51
 */
@Api("文件服务")
@CrossOrigin
@RestController
@RequestMapping("/service-files")
public class FileDetailController {
    /**
     * 使用spring file storage 就要注意实例
     */
    @Autowired
    private FileStorageService fileStorageService;

    @ApiOperation("文件上传服务")
    @PostMapping("/upload")
    public Result uploadFile(@ApiParam(value = "文件对象", required = true)
                             @RequestParam("file") MultipartFile file) {
        FileInfo fileInfo = fileStorageService.of(file).upload();

        return fileInfo == null ? Result.ok().message("文件上传失败！") : Result.ok().data("fileInfo",fileInfo).message("文件上传成功");


    }


}

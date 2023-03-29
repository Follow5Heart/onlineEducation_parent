package com.zty.onlineedu.vod.controller;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.service.base.exceptions.GeneralException;
import com.zty.onlineedu.vod.service.VideoManagerService;
import com.zty.onlineedu.vod.util.ChunkMultipartFileParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @Author zty
 * @Date 2023/1/15 20:51
 */
@Api(tags = "视频管理")
@CrossOrigin
@Log4j2
@Scope(value = "prototype")
@RestController
@RequestMapping("/service-vod/video")
public class VideoManagerController {

    @Resource(name = "videoManagerService")
    private VideoManagerService videoManagerService;


    @ApiOperation("视频分块上传服务")
    @PostMapping("/chunkUpload")
    public Result chunkUpload(@ApiParam(value = "文件分块实体类", required = true)
                                      ChunkMultipartFileParam chunk) {
        try {
            Map<String, Object> uploadResult = videoManagerService.saveChunk(chunk);
            return Result.ok().code(200).message("上传成功").data(uploadResult);

        }catch (Exception e) {
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.VIDEO_CHUNK_UPLOAD_ERROR);

        }

    }

    @ApiOperation("视频分块合并服务")
    @GetMapping("/chunkMerge")
    public Result chunkMerge(@ApiParam("文件名") @RequestParam("filename") String filename,
                             @ApiParam("文件唯一标识") @RequestParam("identifier") String identifier,
                             @ApiParam("文件类型") @RequestParam("contentType") String contentType,
                             @ApiParam("文件大小") @RequestParam("filesize") String filesize) {
        try {
            Map<String, Object> mergeResult = videoManagerService.chunkMerge(filename, identifier, contentType, filesize);
            return Result.ok().code(200).message("合并成功").data(mergeResult);

        } catch (Exception e) {
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.VIDEO_CHUNK_MERGE_ERROR);

        }

    }

    @ApiOperation("检测断点和秒传")
    @GetMapping("/chunkUpload")
    public Result checkChunk(@ApiParam(value = "文件分块实体类", required = true)
                                     ChunkMultipartFileParam chunk) {
        try {
            Map<String, Object> uploadResult = videoManagerService.checkBreakpointOrSecondTransmission(chunk);
            return Result.ok().code(200).message("校验成功").data(uploadResult);

        } catch (Exception e) {
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.VIDEO_CHECK_CHUNK_UPLOAD_ERROR);

        }

    }

    @ApiOperation("视频删除功能")
    @DeleteMapping("/removeVideo/{videoSourceId}")
    public Result removeVideo(@ApiParam(value = "视频id", required = true) @PathVariable String videoSourceId) {
        try {
            Boolean result = videoManagerService.removeVideo(videoSourceId);
            if (result) {
                return Result.ok().message("删除成功");
            } else {
                return Result.error().message("删除失败");
            }

        } catch (Exception e) {
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.FILE_DELETE_ERROR);

        }

    }

    @ApiOperation("批量删除功能")
    @DeleteMapping("/batchRemoveVideoByIds")
    public Result batchRemoveVideoByIds(@ApiParam(value = "视频id列表", required = true) @RequestBody List<String> videoSourceIds) {
        try {
            Boolean result = videoManagerService.batchRemoveVideoByIds(videoSourceIds);
            if (result) {
                return Result.ok().message("批量删除成功");
            } else {
                return Result.error().message("批量删除失败");
            }

        } catch (Exception e) {
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.FILE_BATCH_DELETE_ERROR);

        }

    }

    /**
     * 用于微服务之间调用的测试方法
     *
     * @return
     */
    @ApiOperation(value = "测试")
    @GetMapping("/test")
    public Result test() {
        log.info("file test被调用");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return Result.ok();

    }


}

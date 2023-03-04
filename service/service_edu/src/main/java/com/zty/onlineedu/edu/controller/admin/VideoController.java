package com.zty.onlineedu.edu.controller.admin;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.edu.pojo.entity.EduVideo;
import com.zty.onlineedu.edu.pojo.vo.VideoVo;
import com.zty.onlineedu.edu.service.EduVideoService;
import com.zty.onlineedu.service.base.exceptions.GeneralException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zty
 * @Date 2022/12/3 14:02
 */
@Api(tags = "课时管理-视频")
@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/admin/edu/video")
public class VideoController {
    @Autowired
    private EduVideoService eduVideoService;

    @ApiOperation("修改课时")
    @PutMapping("/updateVideo")
    public Result updateVideo(@ApiParam(value = "课时对象",required = true)
                              @RequestBody EduVideo eduVideo){
        try{
            Boolean result=eduVideoService.updateVideo(eduVideo);
            if (result){
                return Result.ok().message("更新成功");
            }else{
                return Result.error().message("更新失败");
            }
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.UPDATE_VIDEO_ERROR);

        }
    }

    @ApiOperation("删除课时")
    @DeleteMapping("/deleteVideo/{videoId}")
    public Result deleteVideo(@ApiParam(value = "课时id",required = true)
                              @PathVariable String videoId){
        try{
            Boolean result=eduVideoService.deleteVideo(videoId);
            if (result){
                return Result.ok().message("删除成功");
            }else{
                return Result.error().message("删除失败");
            }
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.DELETE_VIDEO_ERROR);

        }
    }


    @ApiOperation("新增课时")
    @PostMapping("/saveVideo")
    public Result saveVideo(@ApiParam(value = "课时对象",required = true)
                              @RequestBody EduVideo eduVideo){
        try{
            Boolean result=eduVideoService.saveVideo(eduVideo);
            if (result){
                return Result.ok().message("新增成功");
            }else{
                return Result.error().message("新增失败");
            }
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.SAVE_VIDEO_ERROR);

        }
    }


    @ApiOperation("查询课时")
    @GetMapping("/getVideoById/{videoId}")
    public Result getVideoById(@ApiParam(value = "课时id",required = true)
                            @PathVariable String videoId){
        try{
            VideoVo videoVo=eduVideoService.getVideoById(videoId);
            if (videoVo==null){
                return Result.error().message("查询课时数据为空");

            }else{
                return Result.ok().data("items",videoVo);
            }
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.SAVE_VIDEO_ERROR);

        }
    }
}

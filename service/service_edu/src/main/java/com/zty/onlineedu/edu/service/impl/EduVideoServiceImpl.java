package com.zty.onlineedu.edu.service.impl;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.utils.LocalDateTimeUtils;
import com.zty.onlineedu.common.base.utils.UUIDUtils;
import com.zty.onlineedu.edu.feign.VodService;
import com.zty.onlineedu.edu.mapper.EduVideoMapper;
import com.zty.onlineedu.edu.pojo.entity.EduVideo;
import com.zty.onlineedu.edu.pojo.vo.VideoVo;
import com.zty.onlineedu.edu.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author 17939
* @description 针对表【edu_video(课程视频)】的数据库操作Service实现
* @createDate 2022-12-03 13:53:14
*/
@Service
public class EduVideoServiceImpl implements EduVideoService{
    @Autowired
    private EduVideoMapper eduVideoMapper;

    @Autowired
    private VodService vodService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateVideo(EduVideo eduVideo) {
        eduVideo.setGmtModified(LocalDateTimeUtils.FormatNow());
        Boolean result=eduVideoMapper.updateVideo(eduVideo);
        return result;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteVideo(String videoId) {
        //通过课程视频的主键查询 video_source_id
        String videoSourceId=eduVideoMapper.getVideoSourceIdById(videoId);
        //远程调用 删除课程视频
        Result  remoteResult= vodService.removeVideo(videoSourceId);

        //删除课程视频表信息
        Boolean result=eduVideoMapper.deleteVideoById(videoId);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveVideo(EduVideo eduVideo) {
        eduVideo.setId(UUIDUtils.getUUID32());
        eduVideo.setGmtCreate(LocalDateTimeUtils.FormatNow());
        Boolean result=eduVideoMapper.saveVideo(eduVideo);
        return result;

    }

    @Override
    public VideoVo getVideoById(String videoId) {
        VideoVo videoVo=eduVideoMapper.getVideoById(videoId);
        return videoVo;
    }
}

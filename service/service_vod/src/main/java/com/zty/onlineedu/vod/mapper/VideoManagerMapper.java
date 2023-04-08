package com.zty.onlineedu.vod.mapper;

import com.zty.onlineedu.vod.entity.EduVideoInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 17939
* @description 针对表【edu_file_info(文件信息表)】的数据库操作Mapper
* @createDate 2023-01-16 00:18:27
* @Entity com.zty.onlineedu.files.pojo.vo.EduFileInfo
*/
@Mapper
public interface VideoManagerMapper {

    /**
     * 保存视频信息
     * @param eduVideoInfo 视频对象
     */
    void saveVideoInfo(EduVideoInfo eduVideoInfo);

    /**
     * 通过视频id获取视频地址
     * @param videoId 视频id
     * @return 视频地址
     */
    String queryVideoUrlById(String videoId);

    /**
     * 删除视频
     * @param videoSourceId 视频id
     * @return 删除结果
     */
    Boolean removeVideo(String videoSourceId);

    /**
     * 批量删除视频
     * @param videoSourceIds 视频id列表
     * @return 批量删除结果
     */
    Boolean batchRemoveVideoByIds(List<String> videoSourceIds);

    /**
     * 通过课程id获取视频链接
     * @param videoId 课程id
     * @return 视频链接
     */
    String getVideoUrlById(String videoId);

}





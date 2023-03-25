package com.zty.onlineedu.vod.mapper;

import com.zty.onlineedu.vod.entity.EduVideoInfo;
import org.apache.ibatis.annotations.Mapper;

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

}





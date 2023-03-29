package com.zty.onlineedu.edu.mapper;

import com.zty.onlineedu.edu.pojo.entity.EduVideo;
import com.zty.onlineedu.edu.pojo.vo.VideoVo;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 17939
* @description 针对表【edu_video(课程视频)】的数据库操作Mapper
* @createDate 2022-12-03 13:53:14
* @Entity com.zty.onlineedu.edu.pojo.entity.EduVideo
*/
@Mapper
public interface EduVideoMapper{

    /**
     * 更新课时
     * @param eduVideo 课时对象
     * @return 成功与否
     */
    Boolean updateVideo(EduVideo eduVideo);

    /**
     * 删除课时
     * @param videoId 课程id
     * @return 成功与否
     */
    Boolean deleteVideoById(String videoId);

    /**
     * 保存课时
     * @param eduVideo 课程对象
     * @return 成功与否
     */
    Boolean saveVideo(EduVideo eduVideo);

    /**
     * 通过课时id，查询课时数据
     * @param videoId 课时id
     * @return 课时数据
     */
    VideoVo getVideoById(String videoId);

    /**
     *  通过课时id，查询课时的视频id
     * @param videoId 视频信息id
     * @return 视频id
     */
    String getVideoSourceIdById(String videoId);

}

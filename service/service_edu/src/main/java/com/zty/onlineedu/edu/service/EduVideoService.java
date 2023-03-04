package com.zty.onlineedu.edu.service;

import com.zty.onlineedu.edu.pojo.entity.EduVideo;
import com.zty.onlineedu.edu.pojo.vo.VideoVo;

/**
* @author 17939
* @description 针对表【edu_video(课程视频)】的数据库操作Service
* @createDate 2022-12-03 13:53:14
*/
public interface EduVideoService{
    /**
     * 更新课时
     * @param eduVideo 课时对象
     * @return 成功与否
     */
    Boolean updateVideo(EduVideo eduVideo);

    /**
     * 删除课时
     * @param videoId 课时id
     * @return 成功与否
     */
    Boolean deleteVideo(String videoId);

    /**
     * 新增课时
     * @param eduVideo 课时对象
     * @return 成功与否
     */
    Boolean saveVideo(EduVideo eduVideo);

    /**
     * 通过课时id，查询课时数据
     * @param videoId 课时id
     * @return 课时数据
     */
    VideoVo getVideoById(String videoId);

}

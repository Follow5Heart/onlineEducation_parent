package com.zty.onlineedu.vod.service;

import com.zty.onlineedu.vod.util.ChunkMultipartFileParam;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author zty
 * @Date 2023/1/16 0:07
 */

public interface VideoManagerService{

    /**
     * 保存分块文件
     * @param chunk 分块对象
     * @return 接受到分块数量
     */
    Map<String, Object> saveChunk(ChunkMultipartFileParam chunk) throws IOException, NoSuchFieldException;

    /**
     * 检查是秒传还是断点上传
     * @param chunk 分块上传对象
     * @return 秒传还是断点的返回信息
     */
    Map<String, Object> checkBreakpointOrSecondTransmission(ChunkMultipartFileParam chunk);

    /**
     * 合并分块视频
     * @param contentType 文件类型
     * @param filesize 文件大小
     * @param filename 文件名
     * @param identifier 文件唯一标识
     * @return 合并结果
     */
    Map<String,Object> chunkMerge(String contentType, String filesize, String filename, String identifier) throws IOException;

    /**
     *删除视频
     * @param videoSourceId 视频id
     * @return 删除结果
     */
    Boolean removeVideo(String videoSourceId);

    /**
     * 批量文件删除
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

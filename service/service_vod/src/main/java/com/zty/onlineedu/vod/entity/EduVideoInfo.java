package com.zty.onlineedu.vod.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * 视频信息表
 * @TableName edu_video_info
 */
@Data
public class EduVideoInfo implements Serializable {
    /**
     * 视频ID
     */
    private String id;

    /**
     * 视频名称
     */
    private String name;

    /**
     * 是否图片
     */
    private Integer isimg;

    /**
     * 文件类型
     */
    private String contenttype;

    /**
     * 大小
     */
    private Integer filesize;

    /**
     * 物理路径
     */
    private String path;

    /**
     * 访问的url
     */
    private String url;

    /**
     * 文件来源
     */
    private String source;

    /**
     * 创建日期
     */
    private String createtime;

    private static final long serialVersionUID = 1L;
}
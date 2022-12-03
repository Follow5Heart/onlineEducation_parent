package com.zty.onlineedu.edu.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 课程视频
 * @TableName edu_video
 */
@Data
public class EduVideo implements Serializable {
    /**
     * 视频ID
     */
    private String id;

    /**
     * 课程ID
     */
    private String courseId;

    /**
     * 章节ID
     */
    private String chapterId;

    /**
     * 节点名称
     */
    private String title;

    /**
     * 云端视频资源
     */
    private String videoSourceId;

    /**
     * 原始文件名称
     */
    private String videoOriginalName;

    /**
     * 排序字段
     */
    private Object sort;

    /**
     * 播放次数
     */
    private Long playCount;

    /**
     * 是否可以试听：0收费 1免费
     */
    private Integer isFree;

    /**
     * 视频时长（秒）
     */
    private Double duration;

    /**
     * 状态
     */
    private String status;

    /**
     * 视频源文件大小（字节）
     */
    private Long size;

    /**
     * 乐观锁
     */
    private Long version;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

    private static final long serialVersionUID = 1L;
}
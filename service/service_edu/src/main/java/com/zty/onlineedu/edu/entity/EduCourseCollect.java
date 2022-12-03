package com.zty.onlineedu.edu.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 课程收藏
 * @TableName edu_course_collect
 */
@Data
public class EduCourseCollect implements Serializable {
    /**
     * 收藏ID
     */
    private String id;

    /**
     * 课程讲师ID
     */
    private String courseId;

    /**
     * 课程专业ID
     */
    private String memberId;

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
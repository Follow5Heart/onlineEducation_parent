package com.zty.onlineedu.edu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 课程简介
 * @TableName edu_course_description
 */
@Data
public class EduCourseDescription implements Serializable {
    /**
     * 课程ID
     */
    private String id;

    /**
     * 课程简介
     */
    private String description;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 更新时间
     */
    private String gmtModified;

    private static final long serialVersionUID = 1L;
}
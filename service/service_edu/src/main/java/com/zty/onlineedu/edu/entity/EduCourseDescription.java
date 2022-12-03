package com.zty.onlineedu.edu.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

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
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

    private static final long serialVersionUID = 1L;
}
package com.zty.onlineedu.edu.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 课程科目
 * @TableName edu_subject
 */
@Data
public class EduSubject implements Serializable {
    /**
     * 课程类别ID
     */
    private String id;

    /**
     * 类别名称
     */
    private String title;

    /**
     * 父ID
     */
    private String parentId;

    /**
     * 排序字段
     */
    private Object sort;

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
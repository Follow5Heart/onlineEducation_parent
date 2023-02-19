package com.zty.onlineedu.edu.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 课程科目
 * @author 17939
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
    private String sort;

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
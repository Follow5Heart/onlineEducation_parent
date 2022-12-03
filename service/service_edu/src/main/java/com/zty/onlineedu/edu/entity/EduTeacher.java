package com.zty.onlineedu.edu.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 讲师
 * @TableName edu_teacher
 */
@Data
public class EduTeacher implements Serializable {
    /**
     * 讲师ID
     */
    private String id;

    /**
     * 讲师姓名
     */
    private String name;

    /**
     * 讲师简介
     */
    private String intro;

    /**
     * 讲师资历,一句话说明讲师
     */
    private String career;

    /**
     * 头衔 1高级讲师 2首席讲师
     */
    private Object level;

    /**
     * 讲师头像
     */
    private String avatar;

    /**
     * 排序
     */
    private Object sort;

    /**
     * 入驻时间
     */
    private Date joinDate;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    private Integer isDeleted;

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
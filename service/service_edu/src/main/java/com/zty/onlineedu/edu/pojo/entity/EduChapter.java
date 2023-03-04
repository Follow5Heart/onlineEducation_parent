package com.zty.onlineedu.edu.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 课程
 * @author zty
 * @TableName edu_chapter
 */
@Data
public class EduChapter implements Serializable {
    /**
     * 章节ID
     */
    private String id;

    /**
     * 课程ID
     */
    private String courseId;

    /**
     * 章节名称
     */
    private String title;

    /**
     * 显示排序
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


    @Override
    public String toString() {
        return "EduChapter{" +
                "id='" + id + '\'' +
                ", courseId='" + courseId + '\'' +
                ", title='" + title + '\'' +
                ", sort=" + sort +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
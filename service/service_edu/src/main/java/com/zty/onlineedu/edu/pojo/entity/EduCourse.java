package com.zty.onlineedu.edu.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 课程
 * @TableName edu_course
 */
@Data
public class EduCourse implements Serializable {

    public static final String DRAFT="Draft";
    public static final String NORMAL="Normal";
    /**
     * 课程ID
     */
    private String id;

    /**
     * 课程讲师ID
     */
    private String teacherId;

    /**
     * 课程专业ID
     */
    private String subjectId;

    /**
     * 课程专业父级ID
     */
    private String subjectParentId;

    /**
     * 课程标题
     */
    private String title;

    /**
     * 课程销售价格，设置为0则可免费观看
     */
    private String  price;

    /**
     * 总课时
     */
    private String  lessonNum;

    /**
     * 课程封面图片路径
     */
    private String cover;

    /**
     * 销售数量
     */
    private String  buyCount;

    /**
     * 浏览数量
     */
    private String  viewCount;

    /**
     * 乐观锁
     */
    private String  version;

    /**
     * 课程状态 Draft未发布  Normal已发布
     */
    private String status;

    /**
     * 创建时间
     */
    private String  gmtCreate;

    /**
     * 更新时间
     */
    private String gmtModified;

    private static final long serialVersionUID = 1L;
}
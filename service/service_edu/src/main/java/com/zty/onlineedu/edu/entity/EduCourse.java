package com.zty.onlineedu.edu.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 课程
 * @TableName edu_course
 */
@Data
public class EduCourse implements Serializable {
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
    private BigDecimal price;

    /**
     * 总课时
     */
    private Object lessonNum;

    /**
     * 课程封面图片路径
     */
    private String cover;

    /**
     * 销售数量
     */
    private Long buyCount;

    /**
     * 浏览数量
     */
    private Long viewCount;

    /**
     * 乐观锁
     */
    private Long version;

    /**
     * 课程状态 Draft未发布  Normal已发布
     */
    private String status;

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
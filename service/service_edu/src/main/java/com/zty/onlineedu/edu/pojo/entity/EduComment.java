package com.zty.onlineedu.edu.pojo.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 评论
 * @TableName edu_comment
 */
@Data
public class EduComment implements Serializable {
    /**
     * 讲师ID
     */
    private String id;

    /**
     * 课程id
     */
    private String courseId;

    /**
     * 讲师id
     */
    private String teacherId;

    /**
     * 会员id
     */
    private String memberId;

    /**
     * 会员昵称
     */
    private String nickname;

    /**
     * 会员头像
     */
    private String avatar;

    /**
     * 评论内容
     */
    private String content;

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
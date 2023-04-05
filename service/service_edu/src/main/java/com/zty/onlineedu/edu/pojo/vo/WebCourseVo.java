package com.zty.onlineedu.edu.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zty
 * @Date 2023/4/4 22:30
 * 前端服务器课程详情信息*
 */
@Data
public class WebCourseVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String  price;
    private Integer lessonNum;
    private String cover;
    private String buyCount;
    private String viewCount;
    private String description;
    private String teacherId;
    private String teacherName;
    private String intro;
    private String avatar;
    private String subjectLevelOneId;
    private String subjectLevelOne;
    private String subjectLevelTwoId;
    private String subjectLevelTwo;
}

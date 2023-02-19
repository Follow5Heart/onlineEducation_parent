package com.zty.onlineedu.edu.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zty
 * @Date 2023/2/19 14:26
 */
@Data
public class CourseVo implements Serializable {
    private static final long serialVersionUID =1L;
    private String id;
    private String title;
    private String subjectParentTitle;
    private String subjectTitle;
    private String teacherName;
    private String lessonNum;
    private String price;
    private String cover;
    private String buyCount;
    private String viewCount;
    private String status;
    private String gmtCreate;
}

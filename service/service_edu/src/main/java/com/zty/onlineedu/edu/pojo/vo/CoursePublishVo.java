package com.zty.onlineedu.edu.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author zty
 * @Date 2023/2/23 23:25
 */
@Data
public class CoursePublishVo implements Serializable {
    private static final long serialVersionUID =1L;
    private String id;
    private String title;
    private String subjectParentTitle;
    private String subjectTitle;
    private String teacherName;
    private String lessonNum;
    private String price;
    private String cover;

}

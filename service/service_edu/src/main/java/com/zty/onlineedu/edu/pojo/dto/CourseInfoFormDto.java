package com.zty.onlineedu.edu.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zty
 * @Date 2023/2/16 22:44
 */
@ApiModel("课程基本信息")
@Data
public class CourseInfoFormDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    @ApiModelProperty(value = "课程ID")
    private String id;

    /**
     * 课程讲师ID
     */
    @ApiModelProperty(value = "课程讲师ID")
    private String teacherId;

    /**
     * 课程专业ID
     */
    @ApiModelProperty(value = "课程专业ID")
    private String subjectId;

    /**
     * 课程专业父级ID
     */
    @ApiModelProperty(value = "课程专业父级ID")
    private String subjectParentId;

    /**
     * 课程标题
     */
    @ApiModelProperty(value = "课程标题")
    private String title;

    /**
     * 课程销售价格，设置为0则可免费观看
     */
    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private String  price;

    /**
     * 总课时
     */
    @ApiModelProperty(value = "总课时")
    private String  lessonNum;

    /**
     * 课程封面图片路径
     */
    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    /**
     * 课程简介
     */
    @ApiModelProperty(value = "课程简介")
    private String description;


}

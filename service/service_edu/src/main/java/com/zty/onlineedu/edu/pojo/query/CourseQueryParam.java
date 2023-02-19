package com.zty.onlineedu.edu.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zty
 * @Date 2023/2/19 14:21
 */

@ApiModel("course课程查询对象")
@Data
public class CourseQueryParam implements Serializable {
    private static final long serialVersionUID =1L;

    @ApiModelProperty(value = "课程标题")
    private String title;
    @ApiModelProperty(value="讲师id")
    private String teacherId;
    @ApiModelProperty(value="课程分类二级id")
    private String subjectParentId;
    @ApiModelProperty("课程分类一级id")
    private String subjectId;
}

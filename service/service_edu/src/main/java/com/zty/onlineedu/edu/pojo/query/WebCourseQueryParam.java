package com.zty.onlineedu.edu.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zty
 * @Date 2023/4/1 16:13
 * @deprecated 前端服务器 课程查询对象
 */
@Data
@ApiModel("前端服务器课程查询对象")
public class WebCourseQueryParam implements Serializable {
    private static final long serialVersionUID =1L;

    @ApiModelProperty(value="课程分类二级id")
    private String subjectParentId;

    @ApiModelProperty("课程分类一级id")
    private String subjectId;

    @ApiModelProperty(value = "销量")
    private String buyCountSort;

    @ApiModelProperty(value="最新")
    private String gmtCreateSort;

    @ApiModelProperty(value="价格")
    private String priceSort;

}

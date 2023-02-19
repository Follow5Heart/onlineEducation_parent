package com.zty.onlineedu.edu.pojo.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zty
 * @Date 2022/12/6 20:27
 */
@ApiModel("Teacher查询对象")
@Data
public class TeacherQueryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讲师姓名")
    private String name;

    @ApiModelProperty(value = "讲师级别")
    private Integer level;

    @ApiModelProperty(value = "开始时间")
    private String joinDateBegin;

    @ApiModelProperty(value = "结束时间")
    private String joinDateEnd;

}

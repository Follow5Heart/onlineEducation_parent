package com.zty.onlineedu.edu.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 讲师
 * @TableName edu_teacher
 */
@ApiModel("讲师数据表")
@Data
public class EduTeacher implements Serializable {
    /**
     * 讲师ID
     */
    @ApiModelProperty("讲师id")
    private String id;

    /**
     * 讲师姓名
     */
    @ApiModelProperty("讲师姓名")
    private String name;

    /**
     * 讲师简介
     */
    @ApiModelProperty("讲师简介")
    private String intro;

    /**
     * 讲师资历,一句话说明讲师
     */
    @ApiModelProperty("讲师资历,一句话说明讲师")
    private String career;

    /**
     * 头衔 1高级讲师 2首席讲师
     */
    @ApiModelProperty("头衔 1高级讲师 2首席讲师")
    private String level;

    /**
     * 讲师头像
     */
    @ApiModelProperty("讲师头像")
    private String avatar;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;

    /**
     * 入驻时间
     */
    @ApiModelProperty("入驻时间")
    private String joinDate;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private String gmtCreate;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private String gmtModified;

    private static final long serialVersionUID = 1L;
}
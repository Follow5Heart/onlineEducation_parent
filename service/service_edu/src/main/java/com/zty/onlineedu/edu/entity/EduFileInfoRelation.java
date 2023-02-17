package com.zty.onlineedu.edu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 附件与业务关系表
 * @author 17939
 * @TableName edu_file_info_relation
 */
@Data
public class EduFileInfoRelation implements Serializable {
    /**
     * 主键
     */
    private String datakey;

    /**
     * 间接关联文件id 应用在表格附件如项目id
     */
    private String fileIndirectId;

    /**
     * 直接关联文件ID
     */
    private String fileDirectId;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件ID
     */
    private String fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件版本
     */
    private String fileVersion;

    /**
     * 创建人ID
     */
    private String createUid;

    /**
     * 创建人姓名
     */
    private String createUname;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 年份
     */
    private String year;

    /**
     * 地区
     */
    private String province;

    private static final long serialVersionUID = 1L;
}
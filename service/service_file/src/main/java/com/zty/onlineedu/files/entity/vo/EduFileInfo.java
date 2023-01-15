package com.zty.onlineedu.files.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件信息表
 * @author 17939
 * @TableName edu_file_info
 */
@Data
public class EduFileInfo implements Serializable {
    /**
     * 文件ID
     */
    private String id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 是否图片
     */
    private Integer isimg;

    /**
     * 文件类型
     */
    private String contenttype;

    /**
     * 大小
     */
    private Integer filesize;

    /**
     * 物理路径
     */
    private String path;

    /**
     * 访问的url
     */
    private String url;

    /**
     * 文件来源
     */
    private String source;

    /**
     * 创建日期
     */
    private String createtime;

    private static final long serialVersionUID = 1L;
}
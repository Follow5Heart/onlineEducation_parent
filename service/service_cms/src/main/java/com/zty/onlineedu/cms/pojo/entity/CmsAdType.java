package com.zty.onlineedu.cms.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 推荐位
 * @TableName cms_ad_type
 */
@Data
public class CmsAdType implements Serializable {
    /**
     * ID
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 创建时间
     */
    private String gmtCreate;

    /**
     * 更新时间
     */
    private String gmtModified;

    private static final long serialVersionUID = 1L;
}
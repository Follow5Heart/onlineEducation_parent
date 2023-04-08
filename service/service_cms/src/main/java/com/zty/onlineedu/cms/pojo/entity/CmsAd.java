package com.zty.onlineedu.cms.pojo.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 广告推荐
 * @TableName cms_ad
 */
@Data
public class CmsAd implements Serializable {
    /**
     * ID
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 类型ID
     */
    private String typeId;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 背景颜色
     */
    private String color;

    /**
     * 链接地址
     */
    private String linkUrl;

    /**
     * 排序
     */
    private Object sort;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 更新时间
     */
    private Date gmtModified;

    private static final long serialVersionUID = 1L;
}
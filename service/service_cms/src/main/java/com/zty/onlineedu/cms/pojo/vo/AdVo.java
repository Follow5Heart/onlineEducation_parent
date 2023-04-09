package com.zty.onlineedu.cms.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author helen
 * @since 2020/4/26
 */
@Data
public class AdVo implements Serializable {

    private static final long serialVersionUID=1L;
    private String id;
    private String title;
    private String sort;
    private String type;
}

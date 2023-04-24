package com.zty.onlineedu.ucenter.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zty
 * @Date 2023/4/19 0:44
 */
@ApiModel("二维码信息")
@Data
public class WxQrInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "二维码地址")
    private String qrUrl;

    @ApiModelProperty(value = "用户临时标识")
    private String tempUserId;

    @ApiModelProperty(value = "过期时间")
    private String Expires;


}

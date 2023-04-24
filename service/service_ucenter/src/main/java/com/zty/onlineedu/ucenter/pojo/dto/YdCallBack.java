package com.zty.onlineedu.ucenter.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Author zty
 * @Date 2023/4/19 0:58
 */
@ApiModel("易登回调信息")
@Data
public class YdCallBack implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "扫码成功标识")
    private String scanSuccess;

    @ApiModelProperty(value = "用户临时标识")
    private String tempUserId;

    @ApiModelProperty(value = "用户拒绝授权，登录不成功")
    private String cancelLogin;

    @ApiModelProperty(value = "微信用户信息")
    private Map<String, String> wxMaUserInfo;
}

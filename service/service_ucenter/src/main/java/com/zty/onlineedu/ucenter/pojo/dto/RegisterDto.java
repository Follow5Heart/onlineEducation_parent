package com.zty.onlineedu.ucenter.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author zty
 * @Date 2023/4/13 20:51
 */
@ApiModel("用户注册信息")
@Data
public class RegisterDto implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "用户手机号")
    private String phone;

    /**
     * 密码
     */
    @ApiModelProperty(value = "用户密码")
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码")
    private String code;

}

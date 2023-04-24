package com.zty.onlineedu.ucenter.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author zty
 * @Date 2023/4/16 16:02
 */
@Data
@ApiModel("登录信息")
public class LoginDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("密码")
    private String password;
}

package com.zty.onlineedu.ucenter.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 会员表
 * @TableName ucenter_member
 */
@Data
public class UcenterMember implements Serializable {
    /**
     * 会员id
     */
    private String id;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别 1 男，2 女
     */
    private String sex;

    /**
     * 年龄
     */
    private String age;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户签名
     */
    private String sign;

    /**
     * 是否禁用 1（true）已禁用，  0（false）未禁用
     */
    private String isDisabled;

    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    private String isDeleted;

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
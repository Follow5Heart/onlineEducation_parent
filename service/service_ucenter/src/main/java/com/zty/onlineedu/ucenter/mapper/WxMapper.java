package com.zty.onlineedu.ucenter.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @Author zty
 * @Date 2023/4/23 23:01
 */
@Mapper
public interface WxMapper {
    /**
     * 保存微信用户信息
     * @param wxMaUserInfo 微信用户信息
     */
    void saveUserInfo(Map<String, String> wxMaUserInfo);

    /**
     * 查询微信的openId是否存在
     * @param openId 微信openId
     * @return 是否存在
     */
    Boolean queryOpenIdExist(String openId);
}

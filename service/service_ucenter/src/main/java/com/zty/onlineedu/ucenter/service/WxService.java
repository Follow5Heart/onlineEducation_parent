package com.zty.onlineedu.ucenter.service;

import java.util.Map;

/**
 * @Author zty
 * @Date 2023/4/23 22:59
 */
public interface WxService {
    /**
     * 保存微信用户信息
     * @param wxMaUserInfo 微信用户信息
     */
    void saveUserInfo(Map<String, String> wxMaUserInfo);

}

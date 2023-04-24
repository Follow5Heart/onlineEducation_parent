package com.zty.onlineedu.ucenter.service.impl;

import cn.hutool.core.util.IdUtil;
import com.zty.onlineedu.ucenter.mapper.WxMapper;
import com.zty.onlineedu.ucenter.service.WxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author zty
 * @Date 2023/4/23 23:00
 */
@Service
public class WxServiceImpl implements WxService {
    @Autowired
    private WxMapper wxMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUserInfo(Map<String, String> wxMaUserInfo) {

        String openId=wxMaUserInfo.get("openId")==null?"":wxMaUserInfo.get("openId").toString();
        Boolean isExist=wxMapper.queryOpenIdExist(openId);
        if (!isExist){
            HashMap<String, String> map = new HashMap<>();
            map.put("openid",openId);
            map.put("id",IdUtil.simpleUUID());
            map.put("nickname",wxMaUserInfo.get("nickName")==null ? "" : wxMaUserInfo.get("nickName").toString());

            map.put("avatar",wxMaUserInfo.get("avatarUrl")==null?"":wxMaUserInfo.get("avatarUrl").toString());
            map.put("sex",wxMaUserInfo.get("gender")==null?"0":wxMaUserInfo.get("gender").toString());
            wxMapper.saveUserInfo(map);
        }



    }
}

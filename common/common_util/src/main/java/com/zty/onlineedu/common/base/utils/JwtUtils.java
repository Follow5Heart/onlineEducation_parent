package com.zty.onlineedu.common.base.utils;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.jwt.JWT;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * @Author zty
 * @Date 2023/4/16 14:20
 */
public class JwtUtils {
    /**
     * 秘钥
     */
    public static final String SECRET_KEY = "CD094214B7DC157F7185BC00DEEF4DBB";


    /**
     * 生成JWT-有过期时间 默认是HS265(HmacSHA256)算法
     *
     * @param payload 有效载荷
     * @param expire  过期时间(秒)
     * @return JWT字符串
     */
    public static String getJwtToken(Map<String, Object> payload, int expire) {
        return JWT.create()
                .addPayloads(payload) //添加载荷  例如 一些信息 用户名不是重要的的数据
                .setExpiresAt(DateUtil.offsetSecond(new Date(), expire)) //设置过期时间
                .setKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8)) //设置秘钥
                .sign();

    }

    /**
     * 生成JWT-没有过期时间 默认是HS265(HmacSHA256)算法
     *
     * @param payload 载荷
     * @return JWT字符串
     */
    public static String getJwtToken(Map<String, Object> payload) {
        return getJwtToken(payload, 0);
    }


    /**
     * 解析 JWT 令牌
     *
     * @param token JWT 令牌
     * @return JWT 载荷信息
     */
    public static Map<String, Object> parseToken(String token) {
        JWT jwt = JWT.of(token);
        JSONObject payloads = jwt.getPayloads();
        return JSONUtil.parseObj(payloads.toJSONString(0));
    }

    /**
     * 根据token获取会员id
     *
     * @param request
     * @return
     */
    public static JwtInfo getMemberIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if (StringUtils.isEmpty(jwtToken)) {
            return null;
        }
        JWT jwt = JWT.of(jwtToken);
        JSONObject payloads = jwt.getPayloads();
        Map<String, Object> map = JSONUtil.parseObj(payloads.toJSONString(0));
        JwtInfo jwtInfo = new JwtInfo(
                map.get("id") == null ? "" : map.get("id").toString(),
                map.get("nickname") == null ? "" : map.get("nickname").toString(),
                map.get("avatar") == null ? "" : map.get("avatar").toString());
        return jwtInfo;
    }

    /**
     * 根据token获取会员id
     *
     * @param token JWT 令牌
     * @return
     */
    public static JwtInfo getMemberIdByJwtToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        Map<String, Object> map = parseToken(token);
        JwtInfo jwtInfo = new JwtInfo(
                map.get("id") == null ? "" : map.get("id").toString(),
                map.get("nickname") == null ? "" : map.get("nickname").toString(),
                map.get("avatar") == null ? "" : map.get("avatar").toString());
        return jwtInfo;
    }


    /**
     * 判断token是否存在与有效
     *
     * @param token jwt令牌
     * @return
     */
    public static boolean checkJwtTToken(String token) {
        if (StrUtil.hasBlank()) {
            return false;
        }
        boolean verify = JWT.of(token).setKey(SECRET_KEY.getBytes(StandardCharsets.UTF_8)).verify();
        return verify;

    }

    /**
     * 判断token是否存在与有效
     *
     * @param request
     * @return
     */
    public static boolean checkJwtTToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        boolean verify = checkJwtTToken(jwtToken);
        return verify;

    }





}

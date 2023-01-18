package com.zty.onlineedu.common.base.utils;

import com.github.pagehelper.util.StringUtil;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 * @Author zty
 * @Date 2023/1/18 21:59
 * 进行base64加密和解密
 */
public class Base64Utils {
    public Base64Utils()
    {
    }

    /**
     * 对字符串进行base64加密
     * @param s
     * @return
     */
    public static String encodeBase64(String s)
    {
        String result = null;
        if (StringUtil.isNotEmpty(s)) {
            result = new String(Base64.encodeBase64(s.getBytes()));
        }
        return result;
    }

    /**
     * 对加密后的字符串进行base64解密
     * @param s
     * @return
     */
    public static String decodeBase64(String s)
    {
        String result = null;
        if (StringUtil.isNotEmpty(s)) {
            result = new String(Base64.decodeBase64(s.getBytes()));
        }
        return result;
    }
}

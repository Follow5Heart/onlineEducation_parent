package com.zty.onlineedu.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @Author zty
 * @Date 2023/4/11 21:55
 */
public interface SmsService {
    /**
     * 阿里云短信服务
     * @param phone 手机号
     * @param sixVerificationCode 六位验证码
     * @return 发送返回结果
     */
    Map<String, Object> sendSms(String phone, String sixVerificationCode) throws ExecutionException, InterruptedException;

}

package com.zty.onlineedu.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author zty
 * @Date 2023/4/11 21:50
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.sms")
public class SmsProperties {
    private String regionId;
    private String keyId;
    private String keySecret;
    private String templateCode;
    private String signName;

}

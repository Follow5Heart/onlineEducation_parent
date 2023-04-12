package com.zty.onlineedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author zty
 * @Date 2023/4/11 21:44
 */
@SpringBootApplication()
@EnableDiscoveryClient  //开始nacos得服务注册
@EnableFeignClients //开启openFeign微服务之间的调用
@EnableConfigurationProperties
public class ServiceSmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceSmsApplication.class, args);

    }
}

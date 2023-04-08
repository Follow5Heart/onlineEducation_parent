package com.zty.onlineedu.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author zty
 * @Date 2023/4/8 14:09
 */
@SpringBootApplication()
@ComponentScan("com.zty.onlineedu")
public class ServiceCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmsApplication.class, args);

    }
}

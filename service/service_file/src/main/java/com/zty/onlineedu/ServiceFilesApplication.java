package com.zty.onlineedu;

import cn.xuyanwu.spring.file.storage.EnableFileStorage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author zty
 * @Date 2023/1/15 20:35
 */
/*如果应用spring-file-storage,
要在启动类上加上@EnableFileStorage注解*/
@EnableFileStorage
@SpringBootApplication()
@EnableDiscoveryClient
public class ServiceFilesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceFilesApplication.class, args);

    }
}

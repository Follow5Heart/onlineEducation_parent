package com.zty.onlineedu.vod.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 配置读取类
 * @author 17939
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "vod") //声明当前类为配置读取类 读取前缀为vod的属性
public class VodProperties {
    private String myVodPath;
    private String chunkPath;

}

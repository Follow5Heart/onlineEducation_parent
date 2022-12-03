package com.zty.onlineedu.service.base.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.github.pagehelper.PageInterceptor;

/**
 * mybatis的配置类
 *
 * @author 17939
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.zty.onlineedu.*.mapper")
public class MybatisConfig {

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PageInterceptor getPageInterceptor() {
        return new PageInterceptor();

    }


}

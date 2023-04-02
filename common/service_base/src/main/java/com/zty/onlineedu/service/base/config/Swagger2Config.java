package com.zty.onlineedu.service.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author zty
 * @Date 2022/12/3 20:13
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createWebApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(apiInfoWeb())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
    }

    @Bean
    public Docket createAdminApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(apiInfoAdmin())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/service-edu/.*")))
                .build();

    }

    @Bean
    public Docket createFileApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("fileApi")
                .apiInfo(apiInfoAdmin())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/service-file/.*")))
                .build();

    }

    @Bean
    public Docket createVideoApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("videoApi")
                .apiInfo(apiInfoAdmin())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/service-vod/.*")))
                .build();

    }

    private ApiInfo apiInfoWeb(){
        return new ApiInfoBuilder()
                .title("网站开发接口文档")
                .description("仅对开发人员使用")
                .version("1.0.0")
                .build();

    }

    private ApiInfo apiInfoAdmin(){
        return new ApiInfoBuilder()
                .title("后端管理系统开发接口文档")
                .description("后端管理系统的api接口定义")
                .version("1.0.0")
                .build();

    }
}

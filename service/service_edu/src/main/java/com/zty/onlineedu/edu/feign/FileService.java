package com.zty.onlineedu.edu.feign;

import com.zty.onlineedu.common.base.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author zty
 * @Date 2023/1/22 17:02
 * openFeign调用 service-file服务
 */

@Service
//在nacos中的注册服务名，通过在nacos找到对应的服务名称，在通过/service-files/test找到对应的接口
@FeignClient("service-file")
public interface FileService {
    @GetMapping("/service-files/test")
    Result test();
}

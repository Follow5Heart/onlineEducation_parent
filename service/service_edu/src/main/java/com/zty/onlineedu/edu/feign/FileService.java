package com.zty.onlineedu.edu.feign;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.edu.feign.fallback.FileServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author zty
 * @Date 2023/1/22 17:02
 * openFeign调用 service-file服务
 * value="service-file" 在nacos中的注册服务名，通过在nacos找到对应的服务名称，在通过/service-files/test找到对应的接口
 *  fallback=void.class 定义容错的处理类，当调用远程接口失败或超时时，会调用对应接口的容错逻辑，fallback指定的类必须实现@FeignClient标记的接口*
 */
@Service
@FeignClient(value = "service-files",fallback= FileServiceFallBack.class)
public interface FileService {
    /**
     * 调用微服务的测试方法
     * @return
     */
    @GetMapping("/service-files/file/test")
    Result test();

    /**
     * 调用service-file服务的文件删除方法*
     * @param fileId 文件id
     * @return
     */
    @DeleteMapping("/service-file/files/delete")
    Result deleteFile(String fileId);


}

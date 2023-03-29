package com.zty.onlineedu.edu.feign;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.edu.feign.fallback.VodServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author zty
 * @Date 2023/3/27 22:18
 */
@Service
@FeignClient(value = "service-vod",fallback= VodServiceFallBack.class)
public interface VodService {


    /**远程删除视频
     * @param videoSourceId 视频id
     * @return
     */
    @DeleteMapping("/service-vod/video/removeVideo/{videoSourceId}")
    Result removeVideo(@PathVariable("videoSourceId") String videoSourceId);
}

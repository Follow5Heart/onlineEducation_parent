package com.zty.onlineedu.cms.feign.fallback;

import com.zty.onlineedu.cms.feign.VodService;
import com.zty.onlineedu.common.base.result.Result;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author zty
 * @Date 2023/2/4 11:27
 */
@Component
@Log4j2
public class VodServiceFallBack implements VodService {


    @Override
    public Result removeVideo(String videoSourceId) {
        log.error("远程调用失败，文件没有删除成功，进行熔断保护");
        //使用OpenFeign的容错处理类作为最后的备选方案。可以在下面进行降级处理
        /**
         * 处理代码
         */
        return Result.error();
    }

    @Override
    public Result batchRemoveVideoByIds(List<String> videoSourceIds) {
        log.error("远程调用失败，文件没有删除成功，进行熔断保护");
        //使用OpenFeign的容错处理类作为最后的备选方案。可以在下面进行降级处理
        /**
         * 处理代码
         */
        return Result.error();
    }
}

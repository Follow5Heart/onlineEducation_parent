package com.zty.onlineedu.cms.controller.api;

/**
 * @Author zty
 * @Date 2023/4/9 11:13
 */

import com.zty.onlineedu.cms.pojo.entity.CmsAd;
import com.zty.onlineedu.cms.service.AdService;
import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.service.base.exceptions.GeneralException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 广告推荐 前端服务器
 * @author 17939
 */
@CrossOrigin //解决跨域问题
@Api(tags = "广告推荐")
@RestController
@RequestMapping("/api/cms/ad")
@Slf4j
public class ApiAdController {
    @Autowired
    private AdService adService;


    @ApiOperation("根据推荐位id显示广告推荐")
    @GetMapping("/list/{adTypeId}")
    public Result listByAdTypeId(@ApiParam(value ="推荐位id",required = true) @PathVariable String adTypeId){
        try{
            List<CmsAd> adList=adService.listByAdTypeId(adTypeId);
            return Result.ok().data("items",adList);
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.GET_AD_BY_ADTYPE_LIST_ERROR);

        }
    }


}

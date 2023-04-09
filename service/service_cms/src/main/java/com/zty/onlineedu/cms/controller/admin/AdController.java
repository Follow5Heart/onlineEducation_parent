package com.zty.onlineedu.cms.controller.admin;


import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zty.onlineedu.cms.pojo.entity.CmsAd;
import com.zty.onlineedu.cms.pojo.vo.AdVo;
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
 * 广告推荐
 * @author 17939
 */
@CrossOrigin //解决跨域问题
@Api(tags = "广告推荐管理")
@RestController
@RequestMapping("/service-cms/admin/cms/ad")
@Slf4j
public class AdController {

    @Autowired
    private AdService adService;

    @ApiOperation(value = "根据ID删除推荐")
    @DeleteMapping("/remove/{id}")
    public Result removeById(@ApiParam(value = "推荐ID", required = true) @PathVariable String id) {
        //删除图片
        adService.removeAdImageById(id);

        //删除推荐
        boolean result = adService.removeById(id);
        if (result) {
            return Result.ok().message("删除成功");
        } else {
            return Result.error().message("数据不存在");
        }
    }

    @ApiOperation("推荐分页列表")
    @GetMapping("/list/{page}/{limit}")
    public Result listPage(@ApiParam(value = "当前页码", required = true) @PathVariable int page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable int limit) {
        PageHelper.clearPage();
        PageHelper.startPage(page, limit);
        List<AdVo> listPage = adService.getAdAllData();
        PageInfo<AdVo> PageInfo = new PageInfo<>(listPage, limit);
        return Result.ok().data("items",PageInfo);
    }

    @ApiOperation("新增推荐")
    @PostMapping("/save")
    public Result save(@ApiParam(value = "推荐对象", required = true) @RequestBody CmsAd ad) {

        boolean result = adService.save(ad);
        if (result) {
            return Result.ok().message("保存成功");
        } else {
            return Result.error().message("保存失败");
        }
    }

    @ApiOperation("更新推荐")
    @PutMapping("/update")
    public Result updateById(@ApiParam(value = "讲师推荐", required = true) @RequestBody CmsAd ad) {
        try{
            String adId=ad.getId();
            if (!StrUtil.hasBlank(adId)){
                boolean result = adService.updateById(ad);
                if (result) {
                    return Result.ok().message("修改成功");
                } else {
                    return Result.error().message("数据不存在");
                }
            }else{
                return Result.error().message("Id不能为空");
            }

        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.UPDATE_AD_ERROR);
        }

    }

    @ApiOperation("根据id获取推荐信息")
    @GetMapping("/get/{id}")
    public Result getById(@ApiParam(value = "推荐ID", required = true) @PathVariable String id) {
        CmsAd ad = adService.getById(id);
        if (ad != null) {
            return Result.ok().data("item", ad);
        } else {
            return Result.error().message("数据不存在");
        }
    }
}


package com.zty.onlineedu.cms.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zty.onlineedu.cms.pojo.entity.CmsAdType;
import com.zty.onlineedu.cms.service.AdTypeService;
import com.zty.onlineedu.common.base.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  推荐位
 * </p>
 *
 * @author Helen
 * @since 2020-04-26
 */
@CrossOrigin //解决跨域问题
@Api(description = "推荐位管理")
@RestController
@RequestMapping("/service-cms/admin/cms/ad-type")
@Slf4j
public class AdTypeController {

    @Autowired
    private AdTypeService adTypeService;

    @ApiOperation("所有推荐类别列表")
    @GetMapping("list")
    public Result listAll() {
        List<CmsAdType> list = adTypeService.list();
        return Result.ok().data("items", list);
    }

    @ApiOperation("推荐类别分页列表")
    @GetMapping("list/{page}/{limit}")
    public Result listPage(@ApiParam(value = "当前页码", required = true) @PathVariable int page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable int limit) {

        PageHelper.clearPage();
        PageHelper.startPage(page, limit);
        List<CmsAdType> listPage = adTypeService.list();
        PageInfo<CmsAdType> PageInfo = new PageInfo<>(listPage, limit);
        return Result.ok().data("items",PageInfo);
    }

    @ApiOperation(value = "根据ID删除推荐类别")
    @DeleteMapping("remove/{id}")
    public Result removeById(@ApiParam(value = "推荐类别ID", required = true) @PathVariable String id) {

        boolean result = adTypeService.removeById(id);
        if (result) {
            return Result.ok().message("删除成功");
        } else {
            return Result.error().message("数据不存在");
        }
    }

    @ApiOperation("新增推荐类别")
    @PostMapping("save")
    public Result save(@ApiParam(value = "推荐类别对象", required = true) @RequestBody CmsAdType adType) {

        boolean result = adTypeService.save(adType);
        if (result) {
            return Result.ok().message("保存成功");
        } else {
            return Result.error().message("保存失败");
        }
    }

    @ApiOperation("更新推荐类别")
    @PutMapping("update")
    public Result updateById(@ApiParam(value = "讲师推荐类别", required = true) @RequestBody CmsAdType adType) {
        boolean result = adTypeService.updateById(adType);
        if (result) {
            return Result.ok().message("修改成功");
        } else {
            return Result.error().message("数据不存在");
        }
    }

    @ApiOperation("根据id获取推荐类别信息")
    @GetMapping("get/{id}")
    public Result getById(@ApiParam(value = "推荐类别ID", required = true) @PathVariable String id) {
        CmsAdType adType = adTypeService.getById(id);
        if (adType != null) {
            return Result.ok().data("item", adType);
        } else {
            return Result.error().message("数据不存在");
        }
    }
}


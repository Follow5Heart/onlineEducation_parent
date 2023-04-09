package com.zty.onlineedu.cms.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.zty.onlineedu.cms.feign.FileService;
import com.zty.onlineedu.cms.mapper.CmsAdMapper;
import com.zty.onlineedu.cms.pojo.entity.CmsAd;
import com.zty.onlineedu.cms.pojo.vo.AdVo;
import com.zty.onlineedu.cms.service.AdService;
import com.zty.onlineedu.common.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-04-26
 */
@Service
public class AdServiceImpl  implements AdService {

    @Autowired
    private CmsAdMapper cmsAdMapper;

    @Autowired
    private FileService fileService;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeAdImageById(String id) {
        CmsAd ad = cmsAdMapper.selectById(id);
        if(ad != null) {
            String imagesUrl = ad.getImageUrl();
            if(!StringUtils.isEmpty(imagesUrl)){
                //删除图片
                Result r = fileService.deleteFileByUrl(imagesUrl);
                return Result.error().getSuccess();
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(String id) {

        boolean removeResult=cmsAdMapper.removeById(id);
        return removeResult;
    }

    @Override
    public List<AdVo> getAdAllData() {
        List<AdVo> cmsDataList=cmsAdMapper.getAdAllData();
        return cmsDataList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(CmsAd ad) {

        //添加id
        ad.setId(IdUtil.simpleUUID());
        //添加更新时间
        ad.setGmtCreate(DateUtil.now());
        boolean saveResult=cmsAdMapper.save(ad);
        return saveResult;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(CmsAd ad) {
        //添加修改时间
        ad.setGmtModified(DateUtil.now());
        boolean updateResult=cmsAdMapper.updateById(ad);
        return updateResult;
    }

    @Override
    public CmsAd getById(String id) {
        CmsAd adInfo=cmsAdMapper.getById(id);
        return adInfo;
    }

    @Override
    public List<CmsAd> listByAdTypeId(String adTypeId) {
        List<CmsAd> adList=cmsAdMapper.listByAdTypeId(adTypeId);
        return adList;


    }

}

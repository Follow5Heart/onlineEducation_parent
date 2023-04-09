package com.zty.onlineedu.cms.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.zty.onlineedu.cms.mapper.CmsAdTypeMapper;
import com.zty.onlineedu.cms.pojo.entity.CmsAdType;
import com.zty.onlineedu.cms.service.AdTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  推荐位 服务实现类
 * </p>
 *
 * @author Helen
 * @since 2020-04-26
 */
@Service
public class AdTypeServiceImpl  implements AdTypeService {
    @Autowired
    private CmsAdTypeMapper cmsAdTypeMapper;

    @Override
    public List<CmsAdType> list() {
        List<CmsAdType> cmsAdTypeList= cmsAdTypeMapper.list();
        return cmsAdTypeList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeById(String id) {

        boolean removeResult=cmsAdTypeMapper.removeById(id);
        return removeResult;
    }

    @Override
    public boolean save(CmsAdType adType) {
        //添加id
        adType.setId(IdUtil.simpleUUID());
        //添加更新时间
        adType.setGmtCreate(DateUtil.now());
        boolean saveResult=cmsAdTypeMapper.save(adType);
        return saveResult;
    }

    @Override
    public boolean updateById(CmsAdType adType) {
        //添加修改时间
        adType.setGmtModified(DateUtil.now());
        boolean updateResult=cmsAdTypeMapper.updateById(adType);
        return updateResult;
    }

    @Override
    public CmsAdType getById(String id) {
        CmsAdType adInfo=cmsAdTypeMapper.getById(id);
        return adInfo;
    }
}

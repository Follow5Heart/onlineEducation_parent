package com.zty.onlineedu.cms.service;

import com.zty.onlineedu.cms.pojo.entity.CmsAdType;

import java.util.List;

/**
 * <p>
 *  推荐位 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-04-26
 */
public interface AdTypeService {

    /**
     *  获取推荐位所有信息
     * @return 推荐位信息集合
     */
    List<CmsAdType> list();

    /**
     * 根据id删除推荐位
     * @param id 广告推荐id
     * @return 成功与否
     */
    boolean removeById(String id);


    /**
     * 保存推荐位
     * @param adType 推荐位实体类
     * @return 成功与否
     */
    boolean save(CmsAdType adType);

    /**
     * 更新推荐位
     * @param adType 推荐位实体类
     * @return 成功与否
     */
    boolean updateById(CmsAdType adType);

    /**
     *  根据id获取推荐位信息
     * @param id 推荐位id
     * @return  推荐位信息
     */
    CmsAdType getById(String id);

}

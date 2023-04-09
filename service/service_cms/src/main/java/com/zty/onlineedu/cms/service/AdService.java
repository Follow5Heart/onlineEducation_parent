package com.zty.onlineedu.cms.service;

import com.zty.onlineedu.cms.pojo.entity.CmsAd;
import com.zty.onlineedu.cms.pojo.vo.AdVo;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务类
 * </p>
 *
 * @author Helen
 * @since 2020-04-26
 */
public interface AdService {


    boolean removeAdImageById(String id);

    /**
     * 根据id删除广告推荐信息
     * @param id 广告推荐id
     * @return 成功与否
     */
    boolean removeById(String id);

    /**
     * 获取所有广告推荐信息
     * @return 广告推荐信息列表
     */
    List<AdVo> getAdAllData();


    /**
     * 保存广告推荐信息
     * @param ad 广告推荐实体类
     * @return 成功与否
     */
    boolean save(CmsAd ad);

    /**
     * 更新广告推荐信息
     * @param ad 广告推荐实体类
     * @return 成功与否
     */
    boolean updateById(CmsAd ad);

    /**
     *  根据id获取推荐信息
     * @param id 广告推荐id
     * @return 广告推荐信息
     */
    CmsAd getById(String id);

    /**
     *  根据推荐位id显示广告推荐
     * @param adTypeId 推荐位id
     * @return 广告推荐列表
     */
    List<CmsAd> listByAdTypeId(String adTypeId);

}

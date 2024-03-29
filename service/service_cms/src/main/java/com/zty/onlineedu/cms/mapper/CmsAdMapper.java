package com.zty.onlineedu.cms.mapper;

import com.zty.onlineedu.cms.pojo.entity.CmsAd;
import com.zty.onlineedu.cms.pojo.vo.AdVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 17939
* @description 针对表【cms_ad(广告推荐)】的数据库操作Mapper
* @createDate 2023-04-08 14:33:06
* @Entity com.zty.onlineedu.cms.pojo.entity.CmsAd
*/
@Mapper
public interface CmsAdMapper {

    /**
     *广告推荐id，查询广告推荐信息
     * @param id 广告推荐id
     * @return 广告推荐信息
     */
    CmsAd selectById(String id);

    /**
     * 通过id，删除广告推荐信息
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
     * @param ad 广告推荐信息实体类
     * @return
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





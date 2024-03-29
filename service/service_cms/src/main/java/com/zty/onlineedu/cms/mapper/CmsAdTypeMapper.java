package com.zty.onlineedu.cms.mapper;

import com.zty.onlineedu.cms.pojo.entity.CmsAdType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 17939
* @description 针对表【cms_ad_type(推荐位)】的数据库操作Mapper
* @createDate 2023-04-08 14:33:06
* @Entity com.zty.onlineedu.cms.pojo.entity.CmsAdType
*/
@Mapper
public interface CmsAdTypeMapper {

    /**
     *  获取推荐位所有信息
     * @return 推荐位信息集合
     */
    List<CmsAdType> list();

    /**
     * 通过id，删除广告推荐
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
     * 通过id，更新推荐位
     * @param adType 推荐位实体类
     * @return 成功与否
     */
    boolean updateById(CmsAdType adType);

    /**
     *  根据id获取推荐位
     * @param id 推荐位id
     * @return 推荐位实体类
     */
    CmsAdType getById(String id);
}





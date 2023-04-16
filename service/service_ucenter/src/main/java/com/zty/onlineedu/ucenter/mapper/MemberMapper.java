package com.zty.onlineedu.ucenter.mapper;

import com.zty.onlineedu.ucenter.pojo.entity.UcenterMember;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 17939
* @description 针对表【ucenter_member(会员表)】的数据库操作Mapper
* @createDate 2023-04-13 20:41:40
*
*/
@Mapper
public interface MemberMapper {

    /**
     * 判断当前昵称是否已经存在
     * @param nickname 昵称
     * @return 是否
     */
    boolean isMatchByNickname(String nickname);

    /**
     * 判断当前手机号是否已经存在
     * @param phone 手机号
     * @return 是否
     */
    Boolean isMatchByPhone(String phone);

    /**
     * 保存会员信息
     * @param ucenterMember 会员对象
     *
     */
    void saveMember(UcenterMember ucenterMember);

}





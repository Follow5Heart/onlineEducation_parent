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
    Boolean queryNicknameExist(String nickname);


    /**
     * 保存会员信息
     * @param ucenterMember 会员对象
     *
     */
    void saveMember(UcenterMember ucenterMember);

    /**
     * 校验手机号是否存在
     * @param phone 手机号
     * @return 是否存在
     */
    Boolean queryPhoneExist(String phone);

    /**
     * 通过手机号获取加密密码
     * @param phone 手机号
     * @return 加密密码
     */
    String getPasswordByPhone(String phone);

    /**
     * 通过手机号，查询当前用户是否被禁用
     * @param phone 手机号
     * @return 是否禁用
     */
    Boolean queryIsDisabled(String phone);

    /**
     * 通过手机号获取用户的所有信息
     * @param phone 手机号
     * @return 用户的所有信息
     */
    UcenterMember getMemberInfo(String phone);

}





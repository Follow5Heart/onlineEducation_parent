package com.zty.onlineedu.ucenter.service;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.ucenter.pojo.dto.LoginDto;
import com.zty.onlineedu.ucenter.pojo.dto.RegisterDto;

/**
* @author 17939
* @description 针对表【ucenter_member(会员表)】的数据库操作Service
* @createDate 2023-04-13 20:41:40
*/
public interface MemberService {

    /**
     * 注册用户
     * @param registerDto 注册信息
     * @return
     */
    Result registerMember(RegisterDto registerDto);

    /**
     * 会员登录
     * @param loginDto 登录信息
     * @return
     */
    Result login(LoginDto loginDto);

}

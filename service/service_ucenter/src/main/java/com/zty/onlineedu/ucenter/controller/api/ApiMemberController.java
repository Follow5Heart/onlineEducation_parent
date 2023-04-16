package com.zty.onlineedu.ucenter.controller.api;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.service.base.exceptions.GeneralException;
import com.zty.onlineedu.ucenter.pojo.dto.RegisterDto;
import com.zty.onlineedu.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zty
 * @Date 2023/4/2 14:27
 * 前端服务器nuxt需求的接口**
 */
@Api(tags = "会员管理")
@Log4j2
@CrossOrigin
@RestController
@RequestMapping("/api/ucenter/member")
public class ApiMemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value ="会员注册")
    @PostMapping("/register")
    public Result registerMember(@ApiParam(value ="注册信息",required = true)
                                     @RequestBody RegisterDto registerDto){
        try{
            return memberService.registerMember(registerDto);
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.USER_REGISTER_ERROR);

        }

    }


}

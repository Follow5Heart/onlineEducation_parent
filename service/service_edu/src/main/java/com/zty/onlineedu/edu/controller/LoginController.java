package com.zty.onlineedu.edu.controller;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.edu.entity.vo.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zty
 * @Date 2023/1/4 21:06
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
@Api(tags = "用户登录接口")
public class LoginController {

    /**
     * 用户登录验证*
     * @param user
     * @return
     */
    @ApiOperation("用户信息验证")
    @PostMapping("/login")
    public Result login(@RequestBody @ApiParam("用户信息对象") UserInfo user){
        String username=user.getUsername();
        String password=user.getPassword();
        if (username.equals("admin")&& password.equals("111111")){
            return Result.ok().data("token","admin");
        }else{
            return Result.error();
        }

    }

    @ApiOperation("用户token验证接口")
    @GetMapping("info")
    public Result info(@RequestParam("token") @ApiParam("token令牌") String token){
        if (token.equals("admin")) {

            return Result.ok().data("name","dev_admin")
                    .data("roles","[admin]")
                    .data("avatar","https://img0.baidu.com/it/u=2161987666,4267232203&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500");
        }
        return Result.error();

    }


    @ApiOperation("用户退出")
    @PostMapping("/logout")
    public Result logout(){
        return Result.ok();

    }
}

package com.zty.onlineedu.ucenter.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.utils.JwtInfo;
import com.zty.onlineedu.common.base.utils.JwtUtils;
import com.zty.onlineedu.common.base.utils.LocalDateTimeUtils;
import com.zty.onlineedu.common.base.utils.PhoneCheckUtil;
import com.zty.onlineedu.service.base.redis.RedisService;
import com.zty.onlineedu.ucenter.mapper.MemberMapper;
import com.zty.onlineedu.ucenter.pojo.dto.LoginDto;
import com.zty.onlineedu.ucenter.pojo.dto.RegisterDto;
import com.zty.onlineedu.ucenter.pojo.entity.UcenterMember;
import com.zty.onlineedu.ucenter.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 17939
 * @description 针对表【ucenter_member(会员表)】的数据库操作Service实现
 * @createDate 2023-04-13 20:41:40
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Resource(name = "RedisUtils")
    private RedisService redisService;

    @Autowired
    private MemberMapper mapper;

    @Override
    public Result registerMember(RegisterDto registerDto) {

        //校验参数
        String nickname = registerDto.getNickname();
        String phone = registerDto.getPhone();
        String password = registerDto.getPassword();
        String code = registerDto.getCode();

        if (StrUtil.hasBlank(phone)) {
            return Result.error().message("手机号不能为空！");
        }
        if (!PhoneCheckUtil.checkChinaAndHkPhone(phone)) {
            return Result.error().message("手机号码格式不正确！");
        }

        if (StrUtil.hasBlank(code)) {
            return Result.error().message("验证码不能为空！");
        }

        if (StrUtil.hasBlank(nickname)) {
            return Result.error().message("昵称不能为空！");
        }

        if (StrUtil.hasBlank(password)) {
            return Result.error().message("密码不能为空！");
        }

        String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[_!@#\\$%\\^&\\*`~()\\-\\+=])[0-9a-zA-Z_!@#\\$%\\^&\\*`~()\\-\\+=]{8,16}$";
        boolean isMatch = password.matches(regex);
        if (!isMatch) {
            return Result.error().message("必须满足密码 包含大小写字母、数字及特殊符号，且长度在8-16位之间！");
        }


        //先校验验证码，因为验证码是从redis中获取，如果获取失败或者没有，直接返回错误结果，不用再去查数据库，浪费资源
        //判断存不在这个key
        String key = "sms-" + phone;
        boolean existsKey = redisService.existsKey(key);
        if (!existsKey) {
            return Result.error().message("验证码不正确！");
        } else {
            String saveRedisCode = (String) redisService.vGet(key);
            if (!code.equals(saveRedisCode)) {
                return Result.error().message("验证码不正确！");
            }
        }


        //如果上述都满足，开始查询库数据，进行验证
        Boolean isExistNickname = mapper.queryNicknameExist(nickname);
        if (isExistNickname) {
            return Result.error().message("当前昵称已经使用！");
        }
        Boolean isExistPhone = mapper.queryPhoneExist(phone);
        if (isExistPhone) {
            return Result.error().message("当前手机号已经注册！");
        }

        //封装参数
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setId(IdUtil.simpleUUID());
        ucenterMember.setNickname(nickname);
        ucenterMember.setPassword(SecureUtil.md5(password));
        ucenterMember.setPhone(phone);
        ucenterMember.setGmtCreate(LocalDateTimeUtils.FormatNow());
        mapper.saveMember(ucenterMember);
        return Result.ok().message("注册成功！");


    }

    @Override
    public Result login(LoginDto loginDto) {

        //校验：参数是否存在
        if (loginDto == null) {
            return Result.error().message("参数不能为空");
        }

        if (StrUtil.hasBlank(loginDto.getPhone())) {
            return Result.error().message("手机号不能为空");
        }
        if (StrUtil.hasBlank(loginDto.getPassword())) {
            return Result.error().message("密码不能为空");
        }

        //校验:参数是否合法
        if (!PhoneCheckUtil.checkChinaAndHkPhone(loginDto.getPhone())) {
            return Result.error().message("手机号码格式不正确");
        }

        //校验：手机号是否在库中存在
        Boolean phoneIsExist = mapper.queryPhoneExist(loginDto.getPhone());
        if (!phoneIsExist) {
            return Result.error().message("手机号还没有注册");
        }

        //校验： 密码是否正确
        String encryptionPassword = mapper.getPasswordByPhone(loginDto.getPhone());
        if (!encryptionPassword.equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.error().message("密码不正确");
        }

        //校验：用户是否被禁用
        Boolean isDisabled = mapper.queryIsDisabled(loginDto.getPhone());
        if (isDisabled) {
            return Result.error().message("当前用户已禁用");
        }

        //登录，生成token
        UcenterMember member = mapper.getMemberInfo(loginDto.getPhone());
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(member.getId());
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setAvatar(member.getAvatar());
        Map<String, Object> map = BeanUtil.beanToMap(jwtInfo);
        String token = JwtUtils.getJwtToken(map, 1800);
        return Result.ok().message("登录成功").data("token", token);

    }
}





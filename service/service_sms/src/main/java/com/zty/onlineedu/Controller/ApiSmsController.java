package com.zty.onlineedu.Controller;

import com.zty.onlineedu.Service.SmsService;
import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.common.base.utils.PhoneCheckUtil;
import com.zty.onlineedu.common.base.utils.RandomUtils;
import com.zty.onlineedu.service.base.exceptions.GeneralException;
import com.zty.onlineedu.service.base.redis.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author zty
 * @Date 2023/4/11 21:56
 */
@CrossOrigin //解决跨域问题
@Api(tags = "短信管理")
@RestController
@RequestMapping("/api/sms")
@Slf4j
public class ApiSmsController {

    @Autowired
    private SmsService smsService;

    @Resource(name="RedisUtils")
    private RedisService redisService;

    @ApiOperation("发送验证码服务")
    @GetMapping("/sendSms/{phone}")
    public Result sendSms(@ApiParam(value = "手机号",required = true) @PathVariable String phone){
        try{
            //手机号验证
            boolean checkResult = PhoneCheckUtil.checkChinaAndHkPhone(phone);
            if (checkResult){
                //随机生成验证码
                String  sixVerificationCode= RandomUtils.getSixBitRandom();
                Map<String,Object> sendResult=smsService.sendSms(phone,sixVerificationCode);
                if ("OK".equals(sendResult.get("code").toString())){
                    //把验证码保存到redis中，设置过期时间为5分钟
                    redisService.vSet("sms-"+phone,sixVerificationCode,(long)300);
                    return Result.ok().message("发送短信成功");
                }else{
                    return Result.error().message(sendResult.get("message").toString()).code(Integer.valueOf(sendResult.get("code").toString()));
                }


            }else{
                return Result.error().message("手机号格式不对，请重新输入！");
            }
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.SEND_SMS_ERROR);

        }

    }



}

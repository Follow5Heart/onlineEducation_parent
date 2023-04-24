package com.zty.onlineedu.ucenter.controller.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import com.zty.onlineedu.common.base.utils.ExceptionUtils;
import com.zty.onlineedu.common.base.utils.JwtInfo;
import com.zty.onlineedu.common.base.utils.JwtUtils;
import com.zty.onlineedu.service.base.exceptions.GeneralException;
import com.zty.onlineedu.service.base.redis.RedisService;
import com.zty.onlineedu.ucenter.pojo.dto.YdCallBack;
import com.zty.onlineedu.ucenter.pojo.vo.WxQrInfoVo;
import com.zty.onlineedu.ucenter.service.WxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author zty
 * @Date 2023/4/19 0:22
 */
@Api(tags = "第三方微信登录模块")
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/api/ucenter/wx")
public class ApiWxController {

    @Value("${yd.secret}")
    private String secret;

    @Resource(name = "RedisUtils")
    private RedisService redisService;

    @Autowired
    private WxService wxService;

    @ApiOperation("获取微信登录二维码信息")
    @GetMapping("/wxQr")
    public Result wxQr(HttpSession session) {
        try{
            // 请求易登获取二维码接口
            String StrData = HttpUtil.get("https://yd.jylt.cc/api/wxLogin/tempUserId?secret=" + secret);
            JSONObject parseObj = JSONUtil.parseObj(StrData);
            if (parseObj.getInt("code") != 0) {
                return Result.error().message("二维码获取失败").code(parseObj.getInt("code"));
            }else{
                JSONObject dataJson=(JSONObject)parseObj.get("data");
                String qrUrl = dataJson.get("qrUrl").toString();
                String url=qrUrl.substring(0,qrUrl.indexOf(".png")+4);
                String expires=qrUrl.substring(qrUrl.indexOf("?Expires=")+9,qrUrl.indexOf("&OSSAccessKeyId="));

                WxQrInfoVo wxQrInfoVo = new WxQrInfoVo();
                wxQrInfoVo.setQrUrl(url);
                wxQrInfoVo.setExpires(expires);
                wxQrInfoVo.setTempUserId(dataJson.get("tempUserId").toString());

                //WxQrInfoVo wxQrInfoVo = parseObj.get("data", WxQrInfoVo.class);
                //session.setAttribute("wx_tempUserId",wxQrInfoVo.getTempUserId());

                return Result.ok().data("wxQrInfo",wxQrInfoVo);
            }
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.GET_QR_ERROR);

        }
    }


    @ApiOperation("用户授权回调")
    @PostMapping("/callBack")
    public Result wxCallBack(@RequestBody YdCallBack ydCallBack) {
        try{
           //取出用户的临时标识
            String tempUserId=ydCallBack.getTempUserId();

            if (!StrUtil.hasBlank(tempUserId)){
              //将信息存入到redis中 三分钟过期
                redisService.vSet("wx_temp_login_id:"+tempUserId,ydCallBack,(long)180);
                if (ydCallBack.getWxMaUserInfo()!=null){
                    Map<String, String> wxMaUserInfo = ydCallBack.getWxMaUserInfo();
                    wxService.saveUserInfo(wxMaUserInfo);
                }
            }else{
                return Result.error().message("tempUserId参数为空");
            }
            return Result.ok().message("回调成功");
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.YD_CALLBACK_REQUEST_ERROR);

        }
    }


    @ApiOperation("验证用户是否登录接口")
    @GetMapping("/verifyLoginStatus")
    public Result verifyLoginStatus(@ApiParam(value="用户临时标识",required = true) String tempUserId ) {
        try{
            if (!StrUtil.hasBlank(tempUserId)){
                if (redisService.existsKey("wx_temp_login_id:" + tempUserId)){
                    YdCallBack ydCallBack = (YdCallBack)redisService.vGet("wx_temp_login_id:" + tempUserId);
                    if (ydCallBack.getWxMaUserInfo()!=null){
                        JwtInfo jwtInfo = new JwtInfo();
                        jwtInfo.setId("");
                        jwtInfo.setNickname(ydCallBack.getWxMaUserInfo().get("nickName"));
                        jwtInfo.setAvatar(ydCallBack.getWxMaUserInfo().get("avatarUrl"));
                        Map<String, Object> map = BeanUtil.beanToMap(jwtInfo);
                        String token = JwtUtils.getJwtToken(map, 1800);
                        return Result.ok().message("登录成功").data("token", token);
                    } else if (ydCallBack.getCancelLogin() != null) {
                        return Result.ok().data("cancelLogin",ydCallBack.getCancelLogin());
                    }else if(ydCallBack.getScanSuccess()!=null){
                        return Result.ok().data("scanSuccess",ydCallBack.getScanSuccess());
                    }else{
                        return Result.error().message("缓存数据异常");
                    }
                }else{
                    return Result.ok().message("等待授权中");
                }

            }else{
                return Result.error().message("tempUserId参数为空");
            }
        }catch (Exception e){
            log.error(ExceptionUtils.getExceptionMessage(e));
            throw new GeneralException(ResultCodeEnum.VERIFY_LOGIN_STATUS_ERROR);

        }
    }
}

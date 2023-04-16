package com.zty.onlineedu.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponseBody;
import com.google.gson.Gson;
import com.zty.onlineedu.service.SmsService;
import com.zty.onlineedu.util.SmsProperties;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author zty
 * @Date 2023/4/11 21:55
 */
@Log4j2
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private SmsProperties smsProperties;

    @Override
    public Map<String, Object> sendSms(String phone, String sixVerificationCode) throws ExecutionException, InterruptedException {

        //拼接模板变量实际值
        HashMap<String, String> map = new HashMap<>();
        map.put("code", sixVerificationCode);
        String codeTemplate = JSONUtil.toJsonStr(map);

        // Configure Credentials authentication information, including ak, secret, token
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(smsProperties.getKeyId())
                .accessKeySecret(smsProperties.getKeySecret())
                //.securityToken("<your-token>") // use STS token
                .build());

        // Configure the Client
        AsyncClient client = AsyncClient.builder()
                .region(smsProperties.getRegionId()) // Region ID
                //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                .credentialsProvider(provider)
                //.serviceConfiguration(Configuration.create()) // Service-level configuration
                // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride("dysmsapi.aliyuncs.com")
                        .setConnectTimeout(Duration.ofSeconds(30))
                )
                .build();

        // Parameter settings for API request
        SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                .phoneNumbers(phone)
                .signName(smsProperties.getSignName())
                .templateCode(smsProperties.getTemplateCode())
                .templateParam(codeTemplate)
                // Request-level configuration rewrite, can set Http request parameters, etc.
                // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
                .build();

        // Asynchronously get the return value of the API request
        CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
        // Synchronously get the return value of the API request
        SendSmsResponse resp = response.get();
        SendSmsResponseBody body = resp.getBody();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", body.getCode());
        resultMap.put("message", body.getMessage());
        log.info("当前阿里云短信服务响应体内容："+new Gson().toJson(resp));


        // Finally, close the client
        client.close();
        return resultMap;
    }
}

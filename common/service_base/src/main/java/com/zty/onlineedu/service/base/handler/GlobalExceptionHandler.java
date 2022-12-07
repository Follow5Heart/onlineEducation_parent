package com.zty.onlineedu.service.base.handler;

import com.zty.onlineedu.common.base.result.Result;
import com.zty.onlineedu.service.base.exceptions.BusinessException;
import com.zty.onlineedu.service.base.exceptions.CheckUserNameException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.zty.onlineedu.common.base.result.ResultCodeEnum.*;

/**
 * @RestControllerAdvice 全局异常处理注解，并且里面包含了@ResponseBody注解
 * 把对象变成josn返回给前端 ，由于启动类ServiceEduApplication里面的扫描包含了com.zty.onlineedu  所以可以找到
 *
 * -@RestControllerAdvice 注解是 Spring Boot 用于捕获 @Controller 和 @RestController 层系统抛出的异常
 * (注意，如果已经编写了 try-catch 且在 catch 模块中没有使用 throw 抛出异常， 则 @RestControllerAdvice 捕获不到异常)。
 * -@ExceptionHandler 注解用于指定方法处理的 Exception 的类型
 *
 * @Author zty
 * @Date 2022/12/7 20:25
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result error(HttpMessageNotReadableException e){
        e.printStackTrace();
        return Result.setResult(JSON_PARSE_ERROR);

    }


    /**
     * 通用型异常处理*
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result error(Exception e){
        //打印堆栈信息
        e.printStackTrace();
        return Result.error();

    }

    /**
     * 业务异常捕获
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public Result error(BusinessException e){
        e.printStackTrace();
        return Result.setResult(BUSINESS_ERROR);

    }

    @ExceptionHandler(CheckUserNameException.class)
    public Result error(CheckUserNameException e){
        e.printStackTrace();
        return Result.setResult(USER_EXIST_ERROR);

    }




}

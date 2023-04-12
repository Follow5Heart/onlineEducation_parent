package com.zty.onlineedu.service.base.exceptions;

import com.zty.onlineedu.common.base.result.ResultCodeEnum;
import lombok.Data;

/**
 * @Author zty
 * @Date 2023/1/19 19:33
 * 自定义的通用异常，用于controller层的统一异常处理
 * 定义好，需要在全局异常统一处理
 */
@Data
public class GeneralException extends RuntimeException{

    private Integer code;
    private ResultCodeEnum resultCodeEnum;



    public GeneralException(String message, Integer  code){
        super(message);
        this.code = code;

    }


    public GeneralException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.resultCodeEnum=resultCodeEnum;
    }

    @Override
    public String toString() {
        return "GeneralException{" +
                "code=" + code +","+
                "message=" + resultCodeEnum.getMessage() +
                '}';
    }







}

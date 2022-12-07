package com.zty.onlineedu.service.base.exceptions;

/**
 * @Author zty
 * @Date 2022/12/7 22:26
 */
public class BusinessException extends RuntimeException{
    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }
}

package com.zty.onlineedu.service.base.exceptions;

import java.util.List;

import static com.zty.onlineedu.common.base.result.ResultCodeEnum.USER_EXIST_ERROR;

/**
 *  检查用户名是否存在，编译期异常
 * @Author zty
 * @Date 2022/12/7 22:42
 */
public class CheckUserNameException extends Exception{


    public CheckUserNameException() {
    }

    public CheckUserNameException(String message) {
        super(message);
    }


    public static void checkUserName(List<String> list, String userName) throws CheckUserNameException {
        for (String s : list) {
            if (s.equals(userName)){
                throw new CheckUserNameException(USER_EXIST_ERROR.getMessage());
            }
        }

    }


}

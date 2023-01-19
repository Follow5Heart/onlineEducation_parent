package com.zty.onlineedu.common.base.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Author zty
 * @Date 2023/1/19 19:15
 * 打印异常处理工具类
 */
public class ExceptionUtils {
    public ExceptionUtils()
    {
    }

    public static String getExceptionMessage(Throwable e)
    {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        String str = sw.toString();
        return str;
    }

    public static String getRootErrorMessage(Exception e)
    {
        Throwable root = org.apache.commons.lang3.exception.ExceptionUtils.getRootCause(e);
        root = ((Throwable) (root != null ? root : ((Throwable) (e))));
        if (root == null) {
            return "";
        }
        String msg = root.getMessage();
        if (msg == null) {
            return "null";
        } else {
            return StringUtils.defaultString(msg);
        }
    }
}

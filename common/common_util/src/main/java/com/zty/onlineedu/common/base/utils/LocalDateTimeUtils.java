package com.zty.onlineedu.common.base.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author zty
 * @Date 2023/1/16 21:50
 * 时间日期工具类
 */
public class LocalDateTimeUtils {
    public static String FormatNow(){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dtf.format(time);
        return format;
    }


}

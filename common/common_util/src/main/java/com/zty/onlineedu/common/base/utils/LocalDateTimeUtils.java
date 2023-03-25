package com.zty.onlineedu.common.base.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author zty
 * @Date 2023/1/16 21:50
 * 时间日期工具类
 */
public class LocalDateTimeUtils {
    public static final String PATTERN_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DATE_MINUTES = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_DATE_HOUR = "yyyy-MM-dd HH";
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    public static final String PATTERN_OTHER_DATE = "yyyy/MM/dd";
    public static final String PATTERN_YEAR_MONTH = "yyyy-MM";
    public static final String PATTERN_TIME = "HH:mm:ss";

    public static String FormatNow(){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = dtf.format(time);
        return format;
    }


    public static String customFormatDataTime(String formatStr){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(formatStr);
        String customFormatDataTime = dtf.format(time);
        return customFormatDataTime;

    }


}

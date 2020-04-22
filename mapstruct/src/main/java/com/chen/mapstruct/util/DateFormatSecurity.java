package com.chen.mapstruct.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 言少钱
 * @date 2020年04月22日 10:18
 * @Description:
 * JDK1.8新特性DateTimeFormatter：线程安全日期格式化组件
 */
public class DateFormatSecurity {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static DateTimeFormatter getDateTimeFormatter(){
        return DateTimeFormatter.ofPattern(DATE_FORMAT);
    }

    /**
     * 字符串转日期
     * @param str
     * @return
     */
    public static LocalDateTime strToDate(String str){
        return LocalDateTime.parse(str,getDateTimeFormatter());
    }

    /**
     * 日期转字符串
     * @param dateTime
     * @return
     */
    public static String dateToStr(LocalDateTime dateTime){
        return getDateTimeFormatter().format(dateTime);
    }

    public static void main(String[] args) {
        System.out.println(dateToStr(LocalDateTime.now()));
        System.out.println(strToDate("2020-04-22 10:18:24"));
    }
}

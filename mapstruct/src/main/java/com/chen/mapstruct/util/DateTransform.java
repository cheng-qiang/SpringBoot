package com.chen.mapstruct.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 言少钱
 * @date 2020年04月22日 10:00
 * @Description:线程安全时间格式转换类
 */
public class DateTransform {

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>();

    public static DateFormat getDateFormat()
    {
        DateFormat df = threadLocal.get();
        if(df==null){
            df = new SimpleDateFormat(DATE_FORMAT);
            threadLocal.set(df);
        }
        return df;
    }

    /**
     * 日期转字符串
     * @param date
     * @return
     * @throws ParseException
     */
    public static String formatDate(Date date) throws ParseException {
        return getDateFormat().format(date);
    }

    /**
     * 字符串转日期
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }
}

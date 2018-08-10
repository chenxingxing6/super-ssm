package com.dingtalk;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lanxinghua
 * @date 2018/08/06 19:43
 * @description
 */
public class DateUtil {
    public static Date addSeconds(Date date,int second){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND,second);
        return c.getTime();
    }

    public static String printDate(Date date){
        Format f = new SimpleDateFormat("HH:mm:ss");
        String result = ((SimpleDateFormat) f).format(date);
        return result;
    }
}
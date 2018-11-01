package com.wdq.micorestore.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sinosoft_wan on 2018/10/30.
 */

public class DateUtil {

    public static String  geDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
//        time1.setText("Date获取当前日期时间"+simpleDateFormat.format(date));
       return simpleDateFormat.format(date);
    }

    public static Date  geDate(String params){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
        Date date = new Date(System.currentTimeMillis());
//        time1.setText("Date获取当前日期时间"+simpleDateFormat.format(date));
        return date;
    }
    public static String  retrunDateStr(String params){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss
//获取当前时间
//        Date date = new Date(System.currentTimeMillis());
//        time1.setText("Date获取当前日期时间"+simpleDateFormat.format(date));
        return simpleDateFormat.format(params);
    }
}

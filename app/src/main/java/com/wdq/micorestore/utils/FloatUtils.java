package com.wdq.micorestore.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Created by sinosoft_wan on 2019-1-24.
 */

public class FloatUtils {


    //保留2位小数
    public static float to2(float ft){
        DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p=decimalFormat.format(ft);//format 返回的是字符串
//        int   scale  =   2;//设置位数
//        int   roundingMode  =  4;//表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
//        BigDecimal bd  =   new  BigDecimal((double)ft);
//        bd   =  bd.setScale(scale,roundingMode);
        return  Float.valueOf(p);
    }
//    自定义保留小数位数
    public float to2(float ft,int scale){
//        int   scale  =   2;//设置位数
        int   roundingMode  =  4;//表示四舍五入，可以选择其他舍值方式，例如去尾，等等.
        BigDecimal bd  =   new  BigDecimal((double)ft);
        bd   =  bd.setScale(scale,roundingMode);
        return  bd.floatValue();
    }
}

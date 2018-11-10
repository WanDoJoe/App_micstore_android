package com.wdq.micorestore.utils;

import android.util.Log;

import java.util.regex.Pattern;

/**
 * Created by sinosoft_wan on 2018/9/28.
 */

public class StringUtils {
    public static String replaceStr_n(String str){
        return str.replace("\n", "");
    }


    /*
     * 是否为浮点数？double或float类型。
     * @param str 传入的字符串。
     * @return 是浮点数返回true,否则返回false。
     */
    public static boolean isDoubleOrFloat(String str) {
        Log.e("StringUtils",str);
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        Log.e("StringUtils",pattern.matcher(str).matches()+"");
        return pattern.matcher(str).matches();
    }

}

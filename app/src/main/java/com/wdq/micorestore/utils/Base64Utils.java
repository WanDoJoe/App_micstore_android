package com.wdq.micorestore.utils;

import android.util.Base64;

/**
 * Created by sinosoft_wan on 2019-6-24.
 */

public class Base64Utils {

    /**
     * base64编码
     */

    public static String encodeToString(String params){
        String strBase64 = Base64.encodeToString(params.getBytes(), Base64.DEFAULT);
        return strBase64;
    }

    /**
     * base64解码
     */

    public static String decode(String params){
        String str2 = new String(Base64.decode(params.getBytes(), Base64.DEFAULT));
        return str2;
    }
}

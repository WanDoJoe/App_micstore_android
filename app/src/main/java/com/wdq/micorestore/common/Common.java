package com.wdq.micorestore.common;

import android.app.Activity;
import android.content.Context;

import com.wdq.micorestore.GoodsImportActivity;
import com.wdq.micorestore.LoginActivity;
import com.wdq.micorestore.MainActivity;
import com.wdq.micorestore.SaleActivity;
import com.wdq.micorestore.zxing.ZxingCameraCodeActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sinosoft_wan on 2018/9/20.
 */

public class Common {
    //AES Key
    public static final String AES_KEY="micorestore";
    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;
    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;

    public static boolean isNotNetWork=true;

    public static Map<String ,Class> launcherItam(){
        Map<String,Class> map=new HashMap<>();
        map.put("login", LoginActivity.class);
        map.put("sale", SaleActivity.class);
        map.put("goods", GoodsImportActivity.class);
        return map;
    }
}

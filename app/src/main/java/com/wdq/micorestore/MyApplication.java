package com.wdq.micorestore;

import android.app.Application;

import com.uuzuche.lib_zxing.ZApplication;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;
import com.wdq.micorestore.DAO.DaoManager;

/**
 * Created by sinosoft_wan on 2018/9/20.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
        DaoManager.getInstance().init(this);
    }
}

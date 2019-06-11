package com.wdq.micorestore;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by sinosoft_wan on 2018/9/25.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout();
        initView(savedInstanceState);


    }

    @Override
    protected void onResume() {
        super.onResume();
        initDate();
        setView();
        onClick();
    }

    protected abstract void setContentLayout();
    protected abstract void initView(Bundle savedInstanceState);
    protected abstract void initDate();
    protected abstract void setView();
    protected abstract void onClick();


}

package com.wdq.micorestore.order;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wdq.micorestore.R;
import com.wdq.micorestore.order.manage.OrderMenuManageActivity;

public class OrderSettingActivity extends AppCompatActivity {
    Context mContext;

    Button setting_menu_bn,setting_table_bn,setting_reckoning_bn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_order_setting);

        initView();

        onListener();
    }

    private void onListener() {
        setting_menu_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, OrderMenuManageActivity.class);
                startActivity(intent);

            }
        });
    }

    private void initView() {
        setting_menu_bn=findViewById(R.id.order_setting_menu_bn);
    }
}

package com.wdq.micorestore.grocery;

import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wdq.micorestore.BaseActivity;
import com.wdq.micorestore.MyApplication;
import com.wdq.micorestore.R;
import com.wdq.micorestore.bean.UserBean;
import com.wdq.micorestore.grocery.setting.goodsmanage.GrocerySettingGoodsActivity;
import com.wdq.micorestore.grocery.setting.inwarehouse.GrocerySettingInWarehouseActivity;
import com.wdq.micorestore.grocery.setting.usermanage.GrocerySettingUserActivity;
import com.wdq.micorestore.order.bean.OrderUserBean;

import java.util.List;

public class GrocerySettingActivity extends BaseActivity {
    private Context mContext;
    public MyApplication app=MyApplication.sharedApp();
//    private UserBean userBean;
    private Button userManage_Bn,warehouse_Bn,goods_Bn,log_Bn;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//    }


    @Override
    protected void setContentLayout() {
        mContext=this;
        setContentView(R.layout.activity_grocery_setting);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        userManage_Bn=findViewById(R.id.grocery_setting_user);
        warehouse_Bn=findViewById(R.id.grocery_setting_in_warehouse);
        goods_Bn=findViewById(R.id.grocery_setting_goods);
        log_Bn=findViewById(R.id.grocery_setting_log);
    }

    @Override
    protected void setView() {
//        if(null!=userBean){
//            if(userBean.getUsername().equals("admin")){
//                userManage_Bn.setVisibility(View.VISIBLE);
//            }else{
//                userManage_Bn.setVisibility(View.GONE);
//            }
//        }else{
//            userManage_Bn.setVisibility(View.GONE);
//        }
    }

    @Override
    protected void initDate() {
        app.getOrderUserBeanDaoUtils();
    }

    @Override
    protected void onClick() {
        userManage_Bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                List<OrderUserBean> userBeans=app.getOrderUserBeanDaoUtils().queryAll();
//                Toast.makeText(mContext,"a="+userBeans.get(0).getUsername(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext, GrocerySettingUserActivity.class);
                startActivity(intent);
            }
        });
        warehouse_Bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, GrocerySettingInWarehouseActivity.class);
                startActivity(intent);
            }
        });
        goods_Bn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, GrocerySettingGoodsActivity.class);
                startActivity(intent);
            }
        });
    }
}

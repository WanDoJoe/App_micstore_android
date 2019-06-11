package com.wdq.micorestore.grocery.setting.usermanage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wdq.micorestore.BaseActivity;
import com.wdq.micorestore.MyApplication;
import com.wdq.micorestore.R;
import com.wdq.micorestore.grocery.adapter.GrocerySettingUserListAdapter;
import com.wdq.micorestore.order.bean.OrderUserBean;

import java.util.ArrayList;
import java.util.List;

public class GrocerySettingUserActivity extends BaseActivity {
    private Context mContext;
    private MyApplication app=MyApplication.sharedApp();

    private RecyclerView mRecyclerView;
    private GrocerySettingUserListAdapter userListAdapter;

    private List<OrderUserBean> userBeanList=new ArrayList<>();

    @Override
    protected void setContentLayout() {
        mContext=this;
        setContentView(R.layout.activity_grocery_setting_user_layout);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mRecyclerView=findViewById(R.id.grocery_setting_user_list_recyclerview);
    }


    @Override
    protected void initDate() {
        userBeanList=app.getOrderUserBeanDaoUtils().queryAll();
    }
    @Override
    protected void setView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        if (userBeanList.size()>0) {
//            Toast.makeText(mContext,userBeanList.get(0).getName(),Toast.LENGTH_SHORT).show();
            userListAdapter = new
                    GrocerySettingUserListAdapter(mContext, userBeanList,
                    R.layout.activity_grocery_setting_user_layout_list_item);
            mRecyclerView.setAdapter(userListAdapter);
            userListAdapter.notifyDataSetChanged();
        }else{
            Toast.makeText(mContext,"没有数据",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onClick() {
        userListAdapter.setmItemClickListener(new GrocerySettingUserListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }
}

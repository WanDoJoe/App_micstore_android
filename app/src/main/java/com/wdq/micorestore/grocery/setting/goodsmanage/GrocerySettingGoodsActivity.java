package com.wdq.micorestore.grocery.setting.goodsmanage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wdq.micorestore.BaseActivity;
import com.wdq.micorestore.R;
import com.wdq.micorestore.grocery.adapter.GrocerySettingGoodsRightAdapter;
import com.wdq.micorestore.grocery.bean.GroceryGoods;
import com.wdq.micorestore.grocery.dao.GroceryGoodsDaoUtils;
import com.wdq.micorestore.grocery.dao.GroceryGoodsHistorySizeBeanDaoUtils;
import com.wdq.micorestore.grocery.dao.GroceryGoodsHistoryTypeBeanDaoUtils;

import java.util.List;

public class GrocerySettingGoodsActivity extends BaseActivity {
    private Context mContext;
    private RecyclerView rightRecyclerView;

    private GroceryGoodsDaoUtils groceryGoodsDaoUtils;
    private GroceryGoodsHistoryTypeBeanDaoUtils historyTypeBeanDaoUtils;
    private GroceryGoodsHistorySizeBeanDaoUtils historySizeBeanDaoUtils;

    private List<GroceryGoods> mGroceryGoodsList;
    private GrocerySettingGoodsRightAdapter mGrocerySettingGoodsRightAdapter;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_grocery_setting_goods);
        mContext=this;

        groceryGoodsDaoUtils=new GroceryGoodsDaoUtils(mContext);
        historyTypeBeanDaoUtils=new GroceryGoodsHistoryTypeBeanDaoUtils(mContext);
        historySizeBeanDaoUtils=new GroceryGoodsHistorySizeBeanDaoUtils(mContext);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        rightRecyclerView=findViewById(R.id.activity_grocery_setting_goods_recyclerview);

        rightRecyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
    }

    @Override
    protected void initDate() {
        mGroceryGoodsList=groceryGoodsDaoUtils.queryAllMeizi();
    }

    @Override
    protected void setView() {
        Log.e("GroceGoodsActivity","setViewsetViewsetView");
        if(mGroceryGoodsList!=null) {
            if (mGroceryGoodsList.size() > 0) {
                Log.e("GroceGoodsActivity",mGroceryGoodsList.get(0).getGrocery_cqcode()+":::");
                mGrocerySettingGoodsRightAdapter=
                        new GrocerySettingGoodsRightAdapter(mContext,mGroceryGoodsList,
                                R.layout.activity_grocery_setting_user_layout_list_item);
                mGrocerySettingGoodsRightAdapter.notifyDataSetChanged();

            }else{
                Log.e("GroceGoodsActivity","空空空空空空空空空空空空空空空");
            }
        }else{
            Log.e("GroceGoodsActivity","nullllllnulllll");
        }
    }

    @Override
    protected void onClick() {
        mGrocerySettingGoodsRightAdapter.setmItemClickListener(new GrocerySettingGoodsRightAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }
}

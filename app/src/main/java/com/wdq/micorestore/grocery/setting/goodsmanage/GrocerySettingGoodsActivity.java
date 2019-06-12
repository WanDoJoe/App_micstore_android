package com.wdq.micorestore.grocery.setting.goodsmanage;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wdq.micorestore.BaseActivity;
import com.wdq.micorestore.R;
import com.wdq.micorestore.grocery.adapter.GrocerySettingGoodsLeftAdapter;
import com.wdq.micorestore.grocery.adapter.GrocerySettingGoodsRightAdapter;
import com.wdq.micorestore.grocery.bean.GroceryGoods;
import com.wdq.micorestore.grocery.bean.GroceryGoodsHistoryTypeBean;
import com.wdq.micorestore.grocery.dao.GroceryGoodsDaoUtils;
import com.wdq.micorestore.grocery.dao.GroceryGoodsHistorySizeBeanDaoUtils;
import com.wdq.micorestore.grocery.dao.GroceryGoodsHistoryTypeBeanDaoUtils;

import java.util.ArrayList;
import java.util.List;

public class GrocerySettingGoodsActivity extends BaseActivity {
    private Context mContext;
    private RecyclerView rightRecyclerView,leftRecyclerView;

    private GroceryGoodsDaoUtils groceryGoodsDaoUtils;
    private GroceryGoodsHistoryTypeBeanDaoUtils historyTypeBeanDaoUtils;
    private GroceryGoodsHistorySizeBeanDaoUtils historySizeBeanDaoUtils;

    private List<GroceryGoods> mGroceryGoodsRightList=new ArrayList<>();
    private List<GroceryGoodsHistoryTypeBean> mGroceryGoodsHistoryTypeBeanList;
    private GrocerySettingGoodsLeftAdapter mGrocerySettingGoodsLeftAdapter;
    private GrocerySettingGoodsRightAdapter mGrocerySettingGoodsRightAdapter=new GrocerySettingGoodsRightAdapter();

    public GrocerySettingGoodsActivity() {
    }

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
        rightRecyclerView=findViewById(R.id.activity_grocery_setting_goods_recyclerview_right);
        leftRecyclerView=findViewById(R.id.activity_grocery_setting_goods_recyclerview_left);

        leftRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        rightRecyclerView.setLayoutManager(new GridLayoutManager(mContext,3));


        mGroceryGoodsHistoryTypeBeanList=historyTypeBeanDaoUtils.queryAllMeizi();
        if(mGroceryGoodsHistoryTypeBeanList.isEmpty()){
            mGrocerySettingGoodsLeftAdapter=new GrocerySettingGoodsLeftAdapter();
        }else{
            mGrocerySettingGoodsLeftAdapter=new GrocerySettingGoodsLeftAdapter(mContext,
                    mGroceryGoodsHistoryTypeBeanList
                    ,R.layout.activity_grocery_setting_goods_left_item);
            leftRecyclerView.setAdapter(mGrocerySettingGoodsLeftAdapter);
            mGrocerySettingGoodsLeftAdapter.notifyDataSetChanged();

            setRightRecyclerViewAdapter(mGroceryGoodsHistoryTypeBeanList.get(0));

        }

    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void setView() {
    }

    @Override
    protected void onClick() {
       mGrocerySettingGoodsLeftAdapter.setmItemClickListener(new GrocerySettingGoodsLeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                setRightRecyclerViewAdapter(mGroceryGoodsHistoryTypeBeanList.get(position));
            }
        });
    }

    private void setRightRecyclerViewAdapter(GroceryGoodsHistoryTypeBean historyTypebean){
        mGroceryGoodsRightList.clear();
        mGrocerySettingGoodsRightAdapter.notifyDataSetChanged();
        String[] strs={historyTypebean.getTypeName()};
        mGroceryGoodsRightList=groceryGoodsDaoUtils.queryMeiziByNativeSql("where grocery_goods_type=?"
                ,strs);
        if(mGroceryGoodsRightList!=null) {
            if (mGroceryGoodsRightList.size() > 0) {
                mGrocerySettingGoodsRightAdapter=
                        new GrocerySettingGoodsRightAdapter(mContext,mGroceryGoodsRightList,
                                R.layout.activity_grocery_setting_goods_right_item);
                rightRecyclerView.setAdapter(mGrocerySettingGoodsRightAdapter);
                mGrocerySettingGoodsRightAdapter.notifyDataSetChanged();

            }else{
                Log.e("GroceGoodsActivity","空空空空空空空空空空空空空空空");
            }
        }else{
            Log.e("GroceGoodsActivity","nullllllnulllll");
        }
    }
}

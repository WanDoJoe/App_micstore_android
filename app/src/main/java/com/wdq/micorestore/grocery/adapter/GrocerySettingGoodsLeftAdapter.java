package com.wdq.micorestore.grocery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wdq.micorestore.R;
import com.wdq.micorestore.grocery.bean.GroceryGoods;
import com.wdq.micorestore.grocery.bean.GroceryGoodsHistoryTypeBean;

import java.util.List;

/**
 * Created by sinosoft_wan on 2019-6-11.
 */

public class GrocerySettingGoodsLeftAdapter extends  RecyclerView.Adapter<GrocerySettingGoodsLeftAdapter.MyViewHolder> {
    private static String TAG=GrocerySettingGoodsLeftAdapter.class.getSimpleName();
    private Context context;
    private List<GroceryGoodsHistoryTypeBean> mList;
    private int layoutId;

    public GrocerySettingGoodsLeftAdapter() {
    }

    public GrocerySettingGoodsLeftAdapter(Context context, List<GroceryGoodsHistoryTypeBean> mList, int layoutId) {
        this.context = context;
        this.mList = mList;
        this.layoutId = layoutId;
    }


    @Override
    public GrocerySettingGoodsLeftAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GrocerySettingGoodsLeftAdapter.MyViewHolder myViewHolder=new GrocerySettingGoodsLeftAdapter
                .MyViewHolder(LayoutInflater.from(context).inflate(layoutId,null));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(GrocerySettingGoodsLeftAdapter.MyViewHolder holder, final int position) {
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(v,position);
            }
        });
        holder.name.setText(mList.get(position).getTypeName());
    }

    @Override
    public int getItemCount() {
//        Log.e("TAG,mList.get(0).getTypeName());
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
        public TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            if(itemView!=null) {
                linearLayout = itemView.findViewById(R.id.activity_grocery_setting_goods_left_item_ll);
                name = itemView.findViewById(R.id.activity_grocery_setting_goods_left_item_name);
            }else{
                Log.e(TAG,mList.get(0).getTypeName());
            }
        }
    }


    private OnItemClickListener mItemClickListener;
    public interface OnItemClickListener{
        //点击事件
        void onItemClick(View view, int position);
    }

    public OnItemClickListener getmItemClickListener() {
        return mItemClickListener;
    }

    public void setmItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}

package com.wdq.micorestore.grocery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.wdq.micorestore.R;
import com.wdq.micorestore.grocery.bean.GroceryGoods;

import java.util.List;

/**
 * Created by sinosoft_wan on 2019-6-11.
 */

public class GrocerySettingGoodsRightAdapter extends RecyclerView.Adapter<GrocerySettingGoodsRightAdapter.MyViewHolder> {
    private Context context;
    private List<GroceryGoods> mList;
    private int layoutId;

    public GrocerySettingGoodsRightAdapter(Context context, List<GroceryGoods> mList, int layoutId) {
        this.context = context;
        this.mList = mList;
        this.layoutId = layoutId;
    }

    @Override
    public GrocerySettingGoodsRightAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GrocerySettingGoodsRightAdapter.MyViewHolder myViewHolder=new GrocerySettingGoodsRightAdapter
                .MyViewHolder(LayoutInflater.from(context).inflate(layoutId,null));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(GrocerySettingGoodsRightAdapter.MyViewHolder holder, final int position) {
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(v,position);
            }
        });
        holder.name.setText(mList.get(position).getGrocery_goods_name());
    }

    @Override
    public int getItemCount() {
        Log.e("GroceGoodsActivity",mList.get(0).getGrocery_goods_name());
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout=itemView.findViewById(R.id.grocery_setting_user_list_recyclerview_item);
            name=itemView.findViewById(R.id.grocery_setting_user_list_recyclerview_item_name);
        }
    }


    private OnItemClickListener mItemClickListener;
    public interface OnItemClickListener{
        //点击事件
        void onItemClick(View view,int position);
    }

    public OnItemClickListener getmItemClickListener() {
        return mItemClickListener;
    }

    public void setmItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}

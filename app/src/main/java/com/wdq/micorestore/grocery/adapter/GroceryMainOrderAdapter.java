package com.wdq.micorestore.grocery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.wdq.micorestore.R;
import com.wdq.micorestore.grocery.bean.GroceryReckoning;

import java.util.List;

/**
 * Created by sinosoft_wan on 2019-6-12.
 */

public class GroceryMainOrderAdapter extends RecyclerView.Adapter<GroceryMainOrderAdapter.MyViewHolder> {
    private static final String TAG = GroceryMainOrderAdapter.class.getSimpleName();
    private Context mContext;
    private List<GroceryReckoning> mList;
    private int layoutId;

    public GroceryMainOrderAdapter(){}
    public GroceryMainOrderAdapter(Context mContext, List<GroceryReckoning> mList, int layoutId) {
        this.mContext = mContext;
        this.mList = mList;
        this.layoutId = layoutId;
    }

    @Override
    public GroceryMainOrderAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GroceryMainOrderAdapter.MyViewHolder myViewHolder=new GroceryMainOrderAdapter
                .MyViewHolder(LayoutInflater.from(mContext).inflate(layoutId,null));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(GroceryMainOrderAdapter.MyViewHolder holder, final int position) {

        holder.name_tv.setText(mList.get(position).getGrocery_goods_name()+":"+mList.get(position).getGrocery_goods_brand());
        holder.price_tv.setText(mList.get(position).getReckoning_totle_prices());
        holder.numb_tv.setText(mList.get(position).getReckoning_numb());

        holder.add_bn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Log.e(TAG,"mAddItemClickListener");
                mAddItemClickListener.onItemClick(v,position);
            }
        });
        holder.minus_bn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
//                Log.e(TAG,"setmMinusItemClickListener");
                mMinusItemClickListener.onItemClick(v,position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_tv;
        TextView price_tv;
        TextView numb_tv;
        Button minus_bn,add_bn;
        public MyViewHolder(View itemView) {
            super(itemView);
            name_tv=itemView.findViewById(R.id.activity_grocery_main_rv_list_item_tv);
            price_tv=itemView.findViewById(R.id.activity_grocery_main_rv_list_item_tv_price);
            numb_tv=itemView.findViewById(R.id.activity_grocery_main_rv_list_item_num_tv);
            minus_bn = itemView.findViewById(R.id.activity_grocery_main_rv_list_item_minus);
            add_bn = itemView.findViewById(R.id.activity_grocery_main_rv_list_item_bn_add);
        }
    }

    public interface OnItemClickListener {
        //点击事件
        void onItemClick(View view, int position);
    }
    private GroceryMainOrderAdapter.OnItemClickListener mMinusItemClickListener;
    private GroceryMainOrderAdapter.OnItemClickListener mAddItemClickListener;

    public GroceryMainOrderAdapter.OnItemClickListener getmMinusItemClickListener() {
        return mMinusItemClickListener;
    }

    public void setmMinusItemClickListener(OnItemClickListener mMinusItemClickListener) {
        this.mMinusItemClickListener = mMinusItemClickListener;
    }

    public GroceryMainOrderAdapter.OnItemClickListener getmAddItemClickListener() {
        return mAddItemClickListener;
    }

    public void setmAddItemClickListener(OnItemClickListener mAddItemClickListener) {
        this.mAddItemClickListener = mAddItemClickListener;
    }
}

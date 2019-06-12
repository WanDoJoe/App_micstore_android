package com.wdq.micorestore.grocery.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.wdq.micorestore.R;
import com.wdq.micorestore.grocery.bean.GroceryGoods;
import com.wdq.micorestore.grocery.setting.goodsmanage.GrocerySettingGoodsInfoActivity;

import java.util.List;

/**
 * Created by sinosoft_wan on 2019-6-11.
 */

public class GrocerySettingGoodsRightAdapter extends RecyclerView.Adapter<GrocerySettingGoodsRightAdapter.MyViewHolder> {
    private Context context;
    private List<GroceryGoods> mList;
    private int layoutId;
//    private  LinearLayout linearLayout;

    public GrocerySettingGoodsRightAdapter(){}
    public GrocerySettingGoodsRightAdapter(Context context, List<GroceryGoods> mList, int layoutId) {
        this.context = context;
        this.mList = mList;
        this.layoutId = layoutId;
    }


    @Override
    public GrocerySettingGoodsRightAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView=LayoutInflater.from(context).inflate(R.layout.activity_grocery_setting_goods_left_item, null);
        View itemView=LayoutInflater.from(context).inflate(layoutId, null);
        GrocerySettingGoodsRightAdapter.MyViewHolder myViewHolder = new GrocerySettingGoodsRightAdapter
                .MyViewHolder(itemView);
//        linearLayout=itemView.findViewById(R.id.activity_grocery_setting_goods_right_item_ll);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(GrocerySettingGoodsRightAdapter.MyViewHolder holder, final int position) {

            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(context, mList.get(position).getGrocery_goods_name(), Toast.LENGTH_SHORT).show();
//                    mRightItemClickListener.onItemClick(v,position);
                    Intent intent=new Intent(context, GrocerySettingGoodsInfoActivity.class);
                    intent.putExtra("cqCode",mList.get(position).getGrocery_cqcode());
                    context.startActivity(intent);

                }
            });


            holder.name.setText(mList.get(position).getGrocery_goods_name());

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.activity_grocery_setting_goods_right_item_name);
            linearLayout = itemView.findViewById(R.id.activity_grocery_setting_goods_right_item_ll);
        }
    }



    private OnItemClickListener mRightItemClickListener;

    public interface OnItemClickListener {
        //点击事件
        void onItemClick(View view, int position);
    }

    public OnItemClickListener getmItemClickListener() {
        return mRightItemClickListener;
    }

    public void setmItemClickListener(OnItemClickListener mRightItemClickListener) {
        this.mRightItemClickListener = mRightItemClickListener;
    }
}

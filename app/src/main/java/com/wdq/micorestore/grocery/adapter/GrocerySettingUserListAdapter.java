package com.wdq.micorestore.grocery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wdq.micorestore.R;
import com.wdq.micorestore.order.bean.OrderUserBean;

import java.util.List;

/**
 * Created by sinosoft_wan on 2019-6-10.
 */

public class GrocerySettingUserListAdapter extends RecyclerView.Adapter<GrocerySettingUserListAdapter.MyViewHolder> {
    private Context context;
    private List<OrderUserBean> mList;
    private int layoutId;

    public GrocerySettingUserListAdapter(Context context, List<OrderUserBean> mList, int layoutId) {
        this.context = context;
        this.mList = mList;
        this.layoutId = layoutId;
    }

    @Override
    public GrocerySettingUserListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GrocerySettingUserListAdapter.MyViewHolder myViewHolder=new GrocerySettingUserListAdapter
                .MyViewHolder(LayoutInflater.from(context).inflate(layoutId,null));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(GrocerySettingUserListAdapter.MyViewHolder holder, final int position) {
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(v,position);
                }
            });
            holder.name.setText(mList.get(position).getName());
    }

    @Override
    public int getItemCount() {
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

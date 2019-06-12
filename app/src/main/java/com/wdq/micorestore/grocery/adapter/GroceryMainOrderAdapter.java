package com.wdq.micorestore.grocery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wdq.micorestore.R;
import com.wdq.micorestore.grocery.bean.GroceryReckoning;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by sinosoft_wan on 2019-6-12.
 */

public class GroceryMainOrderAdapter extends RecyclerView.Adapter<GroceryMainOrderAdapter.MyViewHolder> {
    private Context mContext;
    private List<GroceryReckoning> mList;
    private int layoutId;

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
    public void onBindViewHolder(GroceryMainOrderAdapter.MyViewHolder holder, int position) {

        holder.name_tv.setText(mList.get(position).getGrocery_goods_name());
//        holder.numb_tv.setText(mList.);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name_tv;

        TextView numb_tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            name_tv=itemView.findViewById(R.id.activity_grocery_main_rv_list_item_tv);
            numb_tv=itemView.findViewById(R.id.activity_grocery_main_rv_list_item_num_tv);
        }
    }
}

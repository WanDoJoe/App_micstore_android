package com.wdq.micorestore.order.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wdq.micorestore.R;
import com.wdq.micorestore.order.bean.OrderSubMenu;
import com.wdq.micorestore.utils.VibrateHelp;

import java.util.List;

/**
 * Created by sinosoft_wan on 2018/11/9.
 */

public class OrderSubMenuAdapter extends RecyclerView.Adapter<OrderSubMenuAdapter.MyViewHolder> {
    private Context context;
    private List<OrderSubMenu> mList;
    private int layoutId;

    public OrderSubMenuAdapter(Context context,List<OrderSubMenu> mList,int layoutId){
        this.context=context;
        this.mList=mList;
        this.layoutId=layoutId;
    }


    @Override
    public OrderSubMenuAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderSubMenuAdapter.MyViewHolder myViewHolder=new OrderSubMenuAdapter
                .MyViewHolder(LayoutInflater.from(context).inflate(layoutId,null));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(OrderSubMenuAdapter.MyViewHolder holder, final int position) {
        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                VibrateHelp.vSimple(context);
                onItemLongTouchLinstener.onLongTouch(v,position);
                return true;
            }
        });

        holder.linearLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(v,position);
            }
        });
        holder.name.setText(mList.get(position).getName());
        holder.price.setText(Float.toString(mList.get(position).getPrice()));
        holder.salePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.salePrice.setText(Float.toString(mList.get(position).getPrice()*mList.get(position).getSale()));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView price;
        TextView salePrice;
        LinearLayout linearLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.order_submenu_listview_item_name_tv);
            price=itemView.findViewById(R.id.order_submenu_listview_item_price_new_tv);
            salePrice=itemView .findViewById(R.id.order_submenu_listview_item_price_tv);
            linearLayout=itemView.findViewById(R.id.order_submenu_listview_item_layout_ll);
        }
    }

    public interface OnItemClickListener{
        //点击事件
        void onItemClick(View view,int position);
    }
    public interface  OnItemLongTouchLinstener{
        //长按事件
        void onLongTouch(View view,int position);
    }
    private OnItemClickListener mItemClickListener;
    private OnItemLongTouchLinstener onItemLongTouchLinstener;

    public OnItemLongTouchLinstener getOnItemLongTouchLinstener() {
        return onItemLongTouchLinstener;
    }

    public void setOnItemLongTouchLinstener(OnItemLongTouchLinstener onItemLongTouchLinstener) {
        this.onItemLongTouchLinstener = onItemLongTouchLinstener;
    }

    public OnItemClickListener getmItemClickListener() {
        return mItemClickListener;
    }

    public void setmItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}

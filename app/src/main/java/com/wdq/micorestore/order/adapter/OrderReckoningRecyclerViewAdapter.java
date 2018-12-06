package com.wdq.micorestore.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wdq.micorestore.order.bean.OrderSubMenu;

import java.util.List;
import java.util.Map;

/**
 * Created by wan on 18-11-13.
 */

public class OrderReckoningRecyclerViewAdapter extends RecyclerView.Adapter<OrderReckoningRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<OrderSubMenu> mList;
    private int layoutId;
    private Map<String ,Integer> map;//设置控件使用


    public OrderReckoningRecyclerViewAdapter(Context context, List<OrderSubMenu> mList, int layoutId, Map<String ,Integer> map) {
        this.context = context;
        this.mList = mList;
        this.layoutId = layoutId;
        this.map=map;
    }

    @Override
    public OrderReckoningRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OrderReckoningRecyclerViewAdapter.MyViewHolder myViewHolder = new OrderReckoningRecyclerViewAdapter
                .MyViewHolder(LayoutInflater.from(context).inflate(layoutId, null));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(OrderReckoningRecyclerViewAdapter.MyViewHolder holder, final int position) {
//        holder.linearLayout.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                VibrateHelp.vSimple(context);
//                onItemLongTouchLinstener.onLongTouch(v, position);
//                return true;
//            }
//        });
//
//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mItemClickListener.onItemClick(v, position);
//            }
//        });
        holder.name.setText(mList.get(position).getName());
        holder.num.setText(mList.get(position).getChoseNumb()+"");
//        holder.price.setText(Float.toString(mList.get(position).getPrice()));
//        holder.salePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        holder.salePrice.setText(Float.toString(mList.get(position).getPrice() * mList.get(position).getSale()));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView num;
        Button minus_bn,add_bn;
        LinearLayout rootlayout_ll;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(map.get("name_tv"));
            Log.e("num_tv",map.get("num_tv")+"");
            num = itemView.findViewById(map.get("num_tv"));
            minus_bn = itemView.findViewById(map.get("minus_bn"));
            add_bn = itemView.findViewById(map.get("add_bn"));
            rootlayout_ll = itemView.findViewById(map.get("rootlayout"));

        }
    }

    public interface OnItemClickListener {
        //点击事件
        void onItemClick(View view, int position);
    }

    public interface OnItemLongTouchLinstener {
        //长按事件
        void onLongTouch(View view, int position);
    }

    private OrderSubMenuAdapter.OnItemClickListener mItemClickListener;
    private OrderSubMenuAdapter.OnItemLongTouchLinstener onItemLongTouchLinstener;

    public OrderSubMenuAdapter.OnItemLongTouchLinstener getOnItemLongTouchLinstener() {
        return onItemLongTouchLinstener;
    }

    public void setOnItemLongTouchLinstener(OrderSubMenuAdapter.OnItemLongTouchLinstener onItemLongTouchLinstener) {
        this.onItemLongTouchLinstener = onItemLongTouchLinstener;
    }

    public OrderSubMenuAdapter.OnItemClickListener getmItemClickListener() {
        return mItemClickListener;
    }

    public void setmItemClickListener(OrderSubMenuAdapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
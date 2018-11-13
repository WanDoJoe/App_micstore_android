package com.wdq.micorestore.order.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wdq.micorestore.R;
import com.wdq.micorestore.utils.VibrateHelp;

import java.util.List;

/**
 * Created by wan on 18-11-13.
 */

public class PopupDialogMediaRecyclerViewAdapter extends RecyclerView.Adapter<PopupDialogMediaRecyclerViewAdapter.MyViewHolder> {
    private Context context;
    private List<String> mList;
    private int layoutId;

    public PopupDialogMediaRecyclerViewAdapter(Context context, List<String> mList, int layoutId) {
        this.context = context;
        this.mList = mList;
        this.layoutId = layoutId;
    }


    @Override
    public PopupDialogMediaRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        PopupDialogMediaRecyclerViewAdapter.MyViewHolder myViewHolder = new PopupDialogMediaRecyclerViewAdapter
                .MyViewHolder(LayoutInflater.from(context).inflate(layoutId, null));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(PopupDialogMediaRecyclerViewAdapter.MyViewHolder holder, final int position) {
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
        holder.name.setText(mList.get(position));
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
        TextView price;
        TextView salePrice;
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.order_main_popup_dialog_item_name);
//            price = itemView.findViewById(R.id.order_submenu_listview_item_price_new_tv);
//            salePrice = itemView.findViewById(R.id.order_submenu_listview_item_price_tv);
            linearLayout = itemView.findViewById(R.id.order_main_popup_dialog_item_layout_ll);
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
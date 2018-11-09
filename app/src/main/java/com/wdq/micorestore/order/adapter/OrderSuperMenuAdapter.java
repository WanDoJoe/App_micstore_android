package com.wdq.micorestore.order.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wdq.micorestore.R;
import com.wdq.micorestore.order.bean.OrderSuperMenu;

import java.util.List;

/**
 * Created by sinosoft_wan on 2018/11/9.
 */

public class OrderSuperMenuAdapter extends BaseAdapter {
    private Context mContext;
    private List<OrderSuperMenu> menuList;
    private int layoutId;

    public OrderSuperMenuAdapter(Context mContext, List<OrderSuperMenu> menuList, int layoutId) {
        this.mContext = mContext;
        this.menuList = menuList;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HandlerView handlerView=new HandlerView();
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(layoutId,null);
            handlerView.name=convertView.findViewById(R.id.order_supermenu_listview_item_name_tv);
            convertView.setTag(handlerView);
        }else{
            handlerView= (HandlerView) convertView.getTag();
        }
            handlerView.name.setText(menuList.get(position).getName());
        return convertView;
    }
    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
    class HandlerView{
        TextView name;
    }
}

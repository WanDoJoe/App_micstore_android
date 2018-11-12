package com.wdq.micorestore.order.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wdq.micorestore.R;
import com.wdq.micorestore.order.bean.OrderSuperTableBean;

import java.util.List;

/**
 * Created by sinosoft_wan on 2018/11/12.
 */

public class OrderSuperTableAdapter extends BaseAdapter {
    Context mContext;
    private List<OrderSuperTableBean> mList;

    public OrderSuperTableAdapter(Context mContext, List<OrderSuperTableBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HandleView handleView=new HandleView();
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.order_manage_table_super_sp_item,null);
            handleView.name_tv=convertView.findViewById(R.id.order_manage_table_super_sp_item_name);
            convertView.setTag(handleView);
        }else{
            handleView= (HandleView) convertView.getTag();
        }
        handleView.name_tv.setText(mList.get(position).getName());
        return convertView;
    }

    class HandleView{
        TextView name_tv;
    }
}

package com.wdq.micorestore.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wdq.micorestore.R;

import java.util.List;


/**
 * Created by sinosoft_wan on 2018/9/21.
 */

public class LauncherAdapter extends RecyclerView.Adapter<LauncherAdapter.MyViewHolder> {
    private Context context;
    private List<String> mList;
    private OnItemClickListener mItemClickListener;

    public LauncherAdapter(Context context,List<String> mList){
        this.context=context;
        this.mList=mList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder=new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.launcher_adapter_layout,null));

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        holder.tv.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                mItemClickListener.onItemClick(v,position);
//            }
//        });
        holder.linearLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(v,position);
            }
        });
            holder.tv.setText(mList.get(position));
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        LinearLayout linearLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.launcher_adapter_layout_tv);
            linearLayout=itemView.findViewById(R.id.launcher_adapter_layout_ll);
        }
    }


    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public OnItemClickListener getmItemClickListener() {
        return mItemClickListener;
    }

    public void setmItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}

package com.wdq.micorestore.order.manage;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.wdq.micorestore.R;
import com.wdq.micorestore.order.adapter.OrderSubTableAdapter;
import com.wdq.micorestore.order.adapter.OrderSuperTableAdapter;
import com.wdq.micorestore.order.bean.OrderSubTableBean;
import com.wdq.micorestore.order.bean.OrderSuperTableBean;
import com.wdq.micorestore.order.dao.OrderSubTableBeanDaoUtils;
import com.wdq.micorestore.order.dao.OrderSuperTableBeanDaoUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderTableManageActivity extends AppCompatActivity {
    Context mContext;
    EditText superTable_ed,subTable_ed;
    Button superTable_add_bn,subTable_add_bn;
    Spinner superTable_sp;
    GridView subTable_recylerv;

    List<OrderSubTableBean> orderSubTableBeanList;
    List<OrderSuperTableBean> orderSuperTableBeansList;

    OrderSubTableBeanDaoUtils subTableBeanDaoUtils;
    OrderSuperTableBeanDaoUtils superTableBeanDaoUtils;

    Long superId;

    OrderSubTableAdapter subTableApater=null;
    OrderSuperTableAdapter orderSubTableAdapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        orderSubTableBeanList=new ArrayList<>();
        orderSuperTableBeansList=new ArrayList<>();
        setContentView(R.layout.activity_order_table_manage);
        initView();
        initDB();

        onListener();
    }

    private void initDB() {
        subTableBeanDaoUtils=new OrderSubTableBeanDaoUtils(mContext);
        superTableBeanDaoUtils=new OrderSuperTableBeanDaoUtils(mContext);

        superTablequeryAll();
        if(orderSubTableBeanList.size()>0) {
            subTablequeryAll(orderSuperTableBeansList.get(0).getId());
        }
    }

    private void onListener() {
        superTable_add_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(superTable_ed.getText().toString().trim().equals("")){
                        Toast.makeText(mContext,"请填写名称",Toast.LENGTH_SHORT).show();
                }else {
                    OrderSuperTableBean bean=new OrderSuperTableBean();
                    bean.setName(superTable_ed.getText().toString());
                    if(superTableBeanDaoUtils.insertByOne(bean)){
                        Toast.makeText(mContext,"添加成功！",Toast.LENGTH_SHORT).show();
                        superTable_ed.setText("");
                        superTablequeryAll();
                    }else{
                        Toast.makeText(mContext,"添加失败！",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        superTable_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                superId=orderSuperTableBeansList.get(position).getId();
                subTablequeryAll(superId);
                if(subTableApater!=null) {
                    subTableApater.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        superTable_sp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,orderSuperTableBeansList.get(position).getName(),Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        subTable_add_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subTable_ed.getText().toString().trim().equals("")){
                    Toast.makeText(mContext,"请填写名称",Toast.LENGTH_SHORT).show();
                }else if(superId==0){
                    Toast.makeText(mContext,"请选择要添加的类型",Toast.LENGTH_SHORT).show();
                }else{
                    OrderSubTableBean bean=new OrderSubTableBean();
                    bean.setName(subTable_ed.getText().toString());
                    bean.setSuperTableID(superId);
                    if(subTableBeanDaoUtils.insertByOne(bean)){
                        Toast.makeText(mContext,"添加成功！",Toast.LENGTH_SHORT).show();
                        subTable_ed.setText("");
                        subTablequeryAll(superId);
                    }else{
                        Toast.makeText(mContext,"添加失败！",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        subTable_recylerv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext,orderSubTableBeanList.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        superTable_ed=findViewById(R.id.order_table_manage_supertable_name_ed);
        subTable_ed=findViewById(R.id.order_table_manage_subtable_name_ed);
        superTable_sp=findViewById(R.id.order_table_manage_spinner);
        subTable_recylerv=findViewById(R.id.order_table_manage_subtable_recyclerview);
        superTable_add_bn=findViewById(R.id.order_table_manage_supertable_add_bn);
        subTable_add_bn=findViewById(R.id.order_table_manage_subtable_add_bn);


    }


    public void  superTablequeryAll(){
        orderSuperTableBeansList.clear();
        List<OrderSuperTableBean> allList = superTableBeanDaoUtils.queryAll();
        if (allList.size()>0){
            orderSuperTableBeansList=allList;
        }
        if(orderSuperTableBeansList.size()>0){
            orderSubTableAdapter=new OrderSuperTableAdapter(mContext,orderSuperTableBeansList);
            superTable_sp.setAdapter(orderSubTableAdapter);

            orderSubTableAdapter.notifyDataSetChanged();
        }
    }

    public void subTablequeryAll(Long superId){
        orderSubTableBeanList.clear();
        String sql="WHERE SUPER_TABLE_ID=?";
        String[] condition=new  String[]{String.valueOf(superId)};
        List<OrderSubTableBean> subtablelist = subTableBeanDaoUtils.queryByNativeSql(sql, condition);
        if(subtablelist.size()>0){
            orderSubTableBeanList=subtablelist;
        }
        Toast.makeText(mContext,superId+"",Toast.LENGTH_SHORT).show();
        if(orderSubTableBeanList.size()>0){
            subTableApater = new OrderSubTableAdapter(mContext, orderSubTableBeanList);
            subTable_recylerv.setAdapter(subTableApater);

        }
        if(subTableApater!=null){
            subTableApater.notifyDataSetChanged();
        }
    }
}


package com.wdq.micorestore.order;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wdq.micorestore.Launcher;
import com.wdq.micorestore.R;
import com.wdq.micorestore.order.adapter.OrderReckoningRecyclerViewAdapter;
import com.wdq.micorestore.order.adapter.OrderSubMenuAdapter;
import com.wdq.micorestore.order.adapter.OrderSubTableAdapter;
import com.wdq.micorestore.order.adapter.OrderSuperMenuAdapter;
import com.wdq.micorestore.order.adapter.OrderSuperTableAdapter;
import com.wdq.micorestore.order.adapter.PopupDialogMediaRecyclerViewAdapter;
import com.wdq.micorestore.order.bean.OrderSubMenu;
import com.wdq.micorestore.order.bean.OrderSubMenuDao;
import com.wdq.micorestore.order.bean.OrderSubTableBean;
import com.wdq.micorestore.order.bean.OrderSuperMenu;
import com.wdq.micorestore.order.bean.OrderSuperTableBean;
import com.wdq.micorestore.order.dao.OrderSubMenuDaoUtils;
import com.wdq.micorestore.order.dao.OrderSubTableBeanDaoUtils;
import com.wdq.micorestore.order.dao.OrderSuperMenuDaoUtils;
import com.wdq.micorestore.order.dao.OrderSuperTableBeanDaoUtils;
import com.wdq.micorestore.widget.PopupDialogMedia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 *  1.点餐
 *      1.1  菜单
 *      1.2  桌位
 *      1.3  点餐人
 *      1.4  订单
 *  2.结账
 *      2.1  账单显示
 *      2.2  结算方式
 *   3.数据备份
 *      3.1  数据导出
 *      3.2  数据导入
 */

public class OrderMainActivity extends AppCompatActivity {
    Context mContext;
    ListView superMenu_Listview;
    RecyclerView subMenu_RecyclerView;
    Spinner superTable_Spinner,subTable_Spinner;

    Button order_bottom_order_bn,order_bottom_reckoning_bn;
    ImageButton order_header_setting_bn;
    TextView order_bottom_order_price_totle,order_bottom_order_price;

    OrderSuperMenuDaoUtils orderSuperMenuDaoUtils;
    OrderSubMenuDaoUtils orderSubMenuDaoUtils;
    OrderSuperTableBeanDaoUtils orderSuperTableBeanDaoUtils;
    OrderSubTableBeanDaoUtils orderSubTableBeanDaoUtils;

    List<OrderSubMenu>  orderSubMenus_List;
    List<OrderSuperMenu> orderSuperMenu_List=new ArrayList<>();
    List<OrderSubTableBean>  orderSubTableBean_List;
    List<OrderSuperTableBean> orderSuperTableBean_List=new ArrayList<>();


    OrderSubMenuAdapter orderSubMenuAdapter;
    OrderSuperMenuAdapter orderSuperMenuAdapter;
    private OrderSubMenuAdapter SubMenuAdapter;

    Long superTable_Id;

    List<OrderSubMenu> reckoningList=new ArrayList<>();
    RecyclerView order_launcher_bottom_recyclerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        orderSubMenus_List=new ArrayList<>();
        setContentView(R.layout.activity_order_main);
        initDate();
        initView();
//        setDate();

        setView();
    }

    private void initDate() {
        orderSuperMenuDaoUtils=new OrderSuperMenuDaoUtils(mContext);
        orderSubMenuDaoUtils=new OrderSubMenuDaoUtils(mContext);
        orderSuperTableBeanDaoUtils=new OrderSuperTableBeanDaoUtils(mContext);
        orderSubTableBeanDaoUtils=new OrderSubTableBeanDaoUtils(mContext);

    }

    private void initView() {
        superMenu_Listview=findViewById(R.id.order_launcher_listview);
        subMenu_RecyclerView=findViewById(R.id.order_launcher_recyclerview);
        superTable_Spinner=findViewById(R.id.order_super_table);
        subTable_Spinner=findViewById(R.id.order_sub_table);
        order_bottom_order_bn=findViewById(R.id.order_bottom_order_bn);
        order_bottom_reckoning_bn=findViewById(R.id.order_bottom_reckoning_bn);
        order_header_setting_bn=findViewById(R.id.order_header_setting_bn);
        order_bottom_order_price_totle=findViewById(R.id.order_bottom_order_price_totle);
        order_bottom_order_price=findViewById(R.id.order_bottom_order_price);
        order_launcher_bottom_recyclerview=findViewById(R.id.order_launcher_bottom_recyclerview);

        order_launcher_bottom_recyclerview.setLayoutManager(new LinearLayoutManager(mContext));

        subMenu_RecyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
    }

    private void setDate() {
        orderSuperMenu_List= orderSuperMenuDaoUtils.queryAll();
//        orderSuperTableBean_List=orderSuperTableBeanDaoUtils.queryAll();
//        superMenuqueryAll();
        if(orderSuperMenu_List.size()>0) {
            findOrderSubMenu(orderSuperMenu_List.get(0).getId());
        }

        orderSuperTableBean_List=orderSuperTableBeanDaoUtils.queryAll();
        if(orderSuperTableBean_List.size()>0){
            findOrderSubTable(orderSuperTableBean_List.get(0).getId());
        }
    }


    private void setView() {
        setDate();
        orderSuperMenuAdapter=new OrderSuperMenuAdapter(mContext,orderSuperMenu_List,R.layout.order_supermenu_listview_item);

        superMenu_Listview.setAdapter(orderSuperMenuAdapter);
        superMenu_Listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                findOrderSubMenu(orderSuperMenu_List.get(position).getId());
            }
        });
        orderSuperMenuAdapter.notifyDataSetChanged();


        order_header_setting_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,OrderSettingActivity.class);
                startActivity(intent);
            }
        });
        OrderSuperTableAdapter superTableadapter=new OrderSuperTableAdapter(mContext,orderSuperTableBean_List);
        superTable_Spinner.setAdapter(superTableadapter);
        superTable_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                superTable_Id=orderSuperTableBean_List.get(position).getId();
                findOrderSubTable(orderSuperTableBean_List.get(position).getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        subTable_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, orderSubTableBean_List.get(position).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        order_bottom_order_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                popupDialogMediaView();
                if(isShow_order_launcher_bottom_recyclerview) {
                    order_launcher_bottom_recyclerview.setVisibility(View.VISIBLE);
                    isShow_order_launcher_bottom_recyclerview=false;
                }else{
                    order_launcher_bottom_recyclerview.setVisibility(View.GONE);
                    isShow_order_launcher_bottom_recyclerview=true;
                }
            }
        });
    }
    boolean isShow_order_launcher_bottom_recyclerview=true;
    //显示所选商品订单
    private void showOrderList(){
        //去掉重复选择
        reckoningList=removeDuplicate(reckoningList);
        //设置界面
        Map<String,Integer> witgetIdMap=new HashMap<>();
        witgetIdMap.put("rootlayout",R.id.order_main_popup_dialog_item_layout_ll);
        witgetIdMap.put("name_tv",R.id.order_main_bottom_rc_item_tv);
        witgetIdMap.put("minus_bn",R.id.order_main_bottom_rc_item_bn_minus);
        witgetIdMap.put("num_tv",R.id.order_main_bottom_rc_item_bn_num_tv);
        witgetIdMap.put("add_bn",R.id.order_main_bottom_rc_item_bn_add);

        OrderReckoningRecyclerViewAdapter adapter=new OrderReckoningRecyclerViewAdapter(mContext,
                reckoningList,R.layout.order_main_bottom_recyclerview_item,witgetIdMap);
        order_launcher_bottom_recyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    public static List removeDuplicate(List list) {
        //去重
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    //订单详情dialog方式
    private void popupDialogMediaView(){
        View popupDialo_View= LayoutInflater.from(mContext).inflate(R.layout.order_main_popup_dialog_layout,null);
        RecyclerView popupDialo_rv=popupDialo_View.findViewById(R.id.order_main_popup_dialog_rv);
//        PopupDialogMediaRecyclerViewAdapter dialogMediaRecyclerViewAdapter
//                =new PopupDialogMediaRecyclerViewAdapter(mContext,reckoningList,R.layout.order_main_popup_dialog_item,null);
//        popupDialo_rv.setLayoutManager(new LinearLayoutManager(mContext));
//        popupDialo_rv.setAdapter(dialogMediaRecyclerViewAdapter);
//        dialogMediaRecyclerViewAdapter.notifyDataSetChanged();

        new PopupDialogMedia(mContext,popupDialo_View).show();
    }


    //查询子菜单
    private void findOrderSubMenu(Long superId){
    try {
        orderSubMenus_List.clear();
        String sql = "where SUPER_MENU_Id = ?";
        String[] condition = new String[]{String.valueOf(superId)};
        List<OrderSubMenu> orderSubMenuUtilsList = orderSubMenuDaoUtils.queryByNativeSql(sql, condition);
        if (orderSubMenuUtilsList.size() > 0) {
            orderSubMenus_List = orderSubMenuUtilsList;
        }
        if (orderSubMenus_List.size() > 0) {
            SubMenuAdapter = new OrderSubMenuAdapter(mContext, orderSubMenus_List, R.layout.order_submenu_listview_item);
            subMenu_RecyclerView.setAdapter(SubMenuAdapter);
            SubMenuAdapter.notifyDataSetChanged();
            SubMenuAdapter.setmItemClickListener(new OrderSubMenuAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    //将点的菜 加入账单列表中
//                    Toast.makeText(mContext, orderSubMenus_List.get(position).getName(), Toast.LENGTH_SHORT).show();
                    orderSubMenus_List.get(position).setChoseNumb(orderSubMenus_List.get(position).getChoseNumb()+1);
                    reckoningList.add(orderSubMenus_List.get(position));
                    showOrderList();
                }
            });
            SubMenuAdapter.setOnItemLongTouchLinstener(new OrderSubMenuAdapter.OnItemLongTouchLinstener() {
                @Override
                public void onLongTouch(View view, int position) {

                }
            });
        }
    }catch (Exception e){
        e.printStackTrace();
    }
    }

    //查询子桌号
    private void findOrderSubTable(Long superId){
        String sql = "where SUPER_TABLE_ID = ?";
        String[] condition = new String[]{String.valueOf(superId)};
        orderSubTableBean_List=new ArrayList<>();
        orderSubTableBean_List=orderSubTableBeanDaoUtils.queryByNativeSql(sql,condition);
        if(orderSubTableBean_List.size()>0){
            OrderSubTableAdapter subTableAdapter=new OrderSubTableAdapter(mContext,orderSubTableBean_List);
            subTable_Spinner.setAdapter(subTableAdapter);
            subTableAdapter.notifyDataSetChanged();
        }
    }
}

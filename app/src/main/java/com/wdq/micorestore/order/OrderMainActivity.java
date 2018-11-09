package com.wdq.micorestore.order;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.wdq.micorestore.Launcher;
import com.wdq.micorestore.R;
import com.wdq.micorestore.order.adapter.OrderSubMenuAdapter;
import com.wdq.micorestore.order.adapter.OrderSuperMenuAdapter;
import com.wdq.micorestore.order.bean.OrderSubMenu;
import com.wdq.micorestore.order.bean.OrderSubMenuDao;
import com.wdq.micorestore.order.bean.OrderSubTableBean;
import com.wdq.micorestore.order.bean.OrderSuperMenu;
import com.wdq.micorestore.order.bean.OrderSuperTableBean;
import com.wdq.micorestore.order.dao.OrderSubMenuDaoUtils;
import com.wdq.micorestore.order.dao.OrderSubTableBeanDaoUtils;
import com.wdq.micorestore.order.dao.OrderSuperMenuDaoUtils;
import com.wdq.micorestore.order.dao.OrderSuperTableBeanDaoUtils;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_order_main);
        initDate();
        initView();
        setDate();

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

        subMenu_RecyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
    }

    private void setDate() {
        orderSuperMenu_List= orderSuperMenuDaoUtils.queryAll();
        orderSuperTableBean_List=orderSuperTableBeanDaoUtils.queryAll();
    }


    private void setView() {

        orderSuperMenuAdapter=new OrderSuperMenuAdapter(mContext,orderSuperMenu_List,R.layout.order_supermenu_listview_item);

        superMenu_Listview.setAdapter(orderSuperMenuAdapter);
        superMenu_Listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                findOrderSubMenu(orderSubMenus_List.get(position).getId());
            }
        });
        orderSuperMenuAdapter.notifyDataSetChanged();



    }




    //查询子菜单
    private void findOrderSubMenu(Long superId){
        String sql = "where SUPER_MENU_Id = ?";
        String[] condition = new String[]{String.valueOf(superId)};
        orderSubMenus_List=new ArrayList<>();
        orderSubMenus_List=orderSubMenuDaoUtils.queryByNativeSql(sql,condition);
        orderSubMenuAdapter=new OrderSubMenuAdapter(mContext,orderSubMenus_List,R.layout.order_submenu_listview_item);
        subMenu_RecyclerView.setAdapter(orderSubMenuAdapter);
        orderSubMenuAdapter.notifyDataSetChanged();
        orderSubMenuAdapter.setmItemClickListener(new OrderSubMenuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //将点的菜 加入账单列表中
            }
        });
    }

    //查询子桌号
    private void findOrderSuperTable(Long superId){
        String sql = "where SUPER_TABLE_ID = ?";
        String[] condition = new String[]{String.valueOf(superId)};
        orderSubTableBean_List=new ArrayList<>();
        orderSubTableBean_List=orderSubTableBeanDaoUtils.queryByNativeSql(sql,condition);
    }
}

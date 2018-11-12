package com.wdq.micorestore.order.manage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wdq.micorestore.R;
import com.wdq.micorestore.order.adapter.OrderSubMenuAdapter;
import com.wdq.micorestore.order.adapter.OrderSuperMenuAdapter;
import com.wdq.micorestore.order.bean.OrderSubMenu;
import com.wdq.micorestore.order.bean.OrderSuperMenu;
import com.wdq.micorestore.order.dao.OrderSubMenuDaoUtils;
import com.wdq.micorestore.order.dao.OrderSuperMenuDaoUtils;
import com.wdq.micorestore.utils.DateUtil;
import com.wdq.micorestore.utils.StringUtils;
import com.wdq.micorestore.utils.VibrateHelp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class OrderMenuManageActivity extends AppCompatActivity {
    private Context mContext;
    private Button superMenu_add_bn,subMenu_add_bn,update_bn,commit_bn;
    private ListView superMenu_sp;
    private RecyclerView subMenu_RecyclerView;

    private View superMenuAdd_dialog_view,subMenuAdd_dialog_view;
    private Dialog superMenuAdd_dialog,subMenuAdd_dialog;
    private EditText superMenu_name_dialog_ed,superMenu_pinyin_dialog_ed;
    private EditText subMenu_name_dialog_ed,subMenu_price_dialog_ed,subMenu_sale_dialog_ed,
            subMenu_piinyin_dialog_ed,subMenu_introduction_dialog_ed;
    private TextView subMenu_date_dialog_tv,subMenu_supername_dialog_tv,order_menu_manage_super_name;
    private Button subMenu_sale_dialog_reset_bn,subMenu_setting_dialog_delete_bn;

    private OrderSuperMenuDaoUtils orderSuperMenuDaoUtils;
    private OrderSubMenuDaoUtils orderSubMenuDaoUtils;

    private List<OrderSuperMenu> orderSuperMenuList;
    private List<OrderSubMenu> orderSubMenuList;

    private String superMenu_sp_select_name="";
    private Long superMenuId;

    OrderSubMenuAdapter SubMenuAdapter=null;
    OrderSuperMenuAdapter superMenuAdapter=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_order_menu_manage);
        orderSuperMenuList=new ArrayList<>();
        orderSubMenuList=new ArrayList<>();
        initView();
        initDB();
        setView();
        onListener();
    }



    private void initDB() {
        orderSuperMenuDaoUtils=new OrderSuperMenuDaoUtils(mContext);
        orderSubMenuDaoUtils=new OrderSubMenuDaoUtils(mContext);
        superMenuqueryAll();
        if(orderSuperMenuList.size()>0) {
            subMenuqueryAll(orderSuperMenuList.get(0).getId());
        }
    }


    private void setView() {
        subMenu_RecyclerView.setLayoutManager(new GridLayoutManager(mContext,3));
    }

    private void onListener() {
        superMenu_add_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSuperMenuDialog();
            }
        });

        subMenu_add_bn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addSubMenuDialog(null,"click");

            }
        });

        update_bn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        commit_bn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        superMenu_sp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                superMenu_sp_select_name=orderSuperMenuList.get(position).getName();
                orderSubMenuList.clear();
                superMenuId=orderSuperMenuList.get(position).getId();
                order_menu_manage_super_name.setText(orderSuperMenuList.get(position).getName());
                subMenuqueryAll(superMenuId);
                if(SubMenuAdapter!=null) {
                    SubMenuAdapter.notifyDataSetChanged();
                }
            }
        });
        superMenu_sp.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                VibrateHelp.vSimple(mContext);
                new AlertDialog.Builder(mContext)
                        .setTitle("提示")
                        .setMessage("删除："+orderSuperMenuList.get(position).getName())
                        .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                orderSuperMenuDaoUtils.deleteByOne(orderSuperMenuList.get(position));
                                superMenuAdapter.notifyDataSetChanged();
                            }
                        })
                        .setPositiveButton("取消",null)
                        .show();

                return true;
            }
        });


    }

    private void initView() {
        superMenu_add_bn=findViewById(R.id.order_menu_manage_super_add_bn);
        subMenu_add_bn=findViewById(R.id.order_menu_manage_sub_add_bn);
        update_bn=findViewById(R.id.order_menu_manage_update_bn);
        commit_bn=findViewById(R.id.order_menu_manage_commit_bn);
        superMenu_sp=findViewById(R.id.order_menu_manage_super_spinner);
        subMenu_RecyclerView=findViewById(R.id.order_menu_manage_sub_RecyclerView);

        order_menu_manage_super_name=findViewById(R.id.order_menu_manage_super_name);
    }

    private void addSuperMenuDialog(){
        superMenuAdd_dialog_view= LayoutInflater.from(mContext)
                .inflate(R.layout.order_menu_setting_super_dialoglayout,null);
        superMenu_name_dialog_ed=superMenuAdd_dialog_view.findViewById(R.id.order_menu_setting_super_dialog_name);
        superMenu_pinyin_dialog_ed=superMenuAdd_dialog_view.findViewById(R.id.order_menu_setting_super_dialog_pinyin);
        superMenuAdd_dialog= new AlertDialog.Builder(mContext)
                .setCancelable(false)
                .setTitle("添加分类")
                .setView(superMenuAdd_dialog_view)
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(superMenu_name_dialog_ed.getText().toString().trim().equals("")){
                            Toast.makeText(mContext,"请填写分类名词",Toast.LENGTH_SHORT).show();
                            mydismissDialog(dialog,false);
                        }else {
                            if (superMenu_pinyin_dialog_ed.equals(null)) {
                                superMenu_pinyin_dialog_ed.setText("");
                            }
                            OrderSuperMenu superMenu = new OrderSuperMenu();
                            superMenu.setName(superMenu_name_dialog_ed.getText().toString());
                            superMenu.setPinyingId(superMenu_pinyin_dialog_ed.getText().toString());
                            superMenu.setIntroduction("");
                            if (orderSuperMenuDaoUtils.insertByOne(superMenu)) {
                                superMenuqueryAll();
                            }
                            mydismissDialog(dialog,true);
                        }

                    }
                })
                .setPositiveButton("取消",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mydismissDialog(dialog,true);
                    }
                })
                .show();

    }

    private void addSubMenuDialog(final OrderSubMenu orderSubMenu,String action){
        subMenuAdd_dialog_view=LayoutInflater.from(mContext).inflate(R.layout.order_menu_setting_sub_dialog,null);
        subMenu_date_dialog_tv=subMenuAdd_dialog_view.findViewById(R.id.order_menu_setting_sub_dialog_date);
        subMenu_supername_dialog_tv=subMenuAdd_dialog_view.findViewById(R.id.order_menu_setting_sub_dialog_supername);
        subMenu_setting_dialog_delete_bn=subMenuAdd_dialog_view.findViewById(R.id.order_menu_setting_sub_dialog_delete_bn);

        subMenu_name_dialog_ed=subMenuAdd_dialog_view.findViewById(R.id.order_menu_setting_sub_dialog_name);
        subMenu_price_dialog_ed=subMenuAdd_dialog_view.findViewById(R.id.order_menu_setting_sub_dialog_price);
        subMenu_sale_dialog_ed=subMenuAdd_dialog_view.findViewById(R.id.order_menu_setting_sub_dialog_price_sale);
        subMenu_piinyin_dialog_ed=subMenuAdd_dialog_view.findViewById(R.id.order_menu_setting_sub_dialog_pinyin);
        subMenu_introduction_dialog_ed=subMenuAdd_dialog_view.findViewById(R.id.order_menu_setting_sub_dialog_introduction);
        subMenu_sale_dialog_reset_bn=subMenuAdd_dialog_view.findViewById(R.id.order_menu_setting_sub_dialog_price_sale_reset_bn);

        final String date = DateUtil.geDate();
        if(orderSubMenu!=null){
            subMenu_setting_dialog_delete_bn.setVisibility(View.VISIBLE);
            subMenu_date_dialog_tv.setText(orderSubMenu.getCreateYear());
            subMenu_name_dialog_ed.setText(orderSubMenu.getName());
            subMenu_price_dialog_ed.setText(Float.toString(orderSubMenu.getPrice()));
            subMenu_sale_dialog_ed.setText(Float.toString(orderSubMenu.getSale()*10));
            subMenu_piinyin_dialog_ed.setText(orderSubMenu.getPinyingId());
            subMenu_introduction_dialog_ed.setText(orderSubMenu.getIntroduction());
        }else {
            subMenu_setting_dialog_delete_bn.setVisibility(View.GONE);
            subMenu_date_dialog_tv.setText(date);
        }
        subMenuAdd_dialog= new AlertDialog.Builder(mContext)
                .setCancelable(false)
                .setTitle(superMenu_sp_select_name+"菜品")
                .setView(subMenuAdd_dialog_view)
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(subMenu_name_dialog_ed.getText().toString().trim().equals("")){
                            Toast.makeText(mContext,"请填写菜品名词",Toast.LENGTH_SHORT).show();
                            mydismissDialog(dialog,false);
                        }else if(subMenu_price_dialog_ed.getText().toString().trim().equals("")){
                            Toast.makeText(mContext,"请填写菜品单价",Toast.LENGTH_SHORT).show();
                            mydismissDialog(dialog,false);
                        }else{
                            if(orderSubMenu!=null){
                                OrderSubMenu SubMenu = new OrderSubMenu();
                                SubMenu.setId(orderSubMenu.getId());
                                SubMenu.setName(subMenu_name_dialog_ed.getText().toString());
                                SubMenu.setCreateYear(date);
                                String price=subMenu_price_dialog_ed.getText().toString();
                                SubMenu.setPrice(Float.valueOf(subMenu_price_dialog_ed.getText().toString()));
                                SubMenu.setIsAble("1");
                                SubMenu.setSuperMenuId(orderSubMenu.getSuperMenuId());
                                SubMenu.setPinyingId(subMenu_piinyin_dialog_ed.getText().toString());
                                SubMenu.setIntroduction(subMenu_introduction_dialog_ed.getText().toString());

                                if (Float.valueOf(subMenu_sale_dialog_ed.getText().toString()) > 10 ||
                                        Float.valueOf(subMenu_sale_dialog_ed.getText().toString()) < 0) {
                                    //否者输入数字除以10
                                    Toast.makeText(mContext, "请输入10到1之间的数，保留2为小数点", Toast.LENGTH_SHORT).show();
                                    mydismissDialog(dialog, false);
                                    return;
                                }else {
                                    SubMenu.setSale((float)(Float.valueOf(subMenu_sale_dialog_ed.getText().toString())*0.1));
                                }

                                    if(orderSubMenuDaoUtils.updateByOne(SubMenu)){
                                   Toast.makeText(mContext,"更新成功",Toast.LENGTH_SHORT).show();
                                        subMenuqueryAll(SubMenu.getSuperMenuId());
                                }else {
                                    Toast.makeText(mContext,"更新失败",Toast.LENGTH_SHORT).show();
                                }

                                mydismissDialog(dialog, true);
                            }else {
                                Log.e("wan","插入新数据");
                                OrderSubMenu orderSubMenu = new OrderSubMenu();
                                orderSubMenu.setName(subMenu_name_dialog_ed.getText().toString());
                                orderSubMenu.setCreateYear(date);
                                orderSubMenu.setPrice(Float.valueOf(subMenu_price_dialog_ed.getText().toString()));
                                orderSubMenu.setIsAble("1");
                                orderSubMenu.setSuperMenuId(superMenuId);
                                orderSubMenu.setPinyingId(subMenu_piinyin_dialog_ed.getText().toString());
                                orderSubMenu.setIntroduction(subMenu_introduction_dialog_ed.getText().toString());
                                if (subMenu_sale_dialog_ed.getText().toString().trim().equals("")) {
                                    //如果空默认是1
                                    orderSubMenu.setSale(1);
                                    if (orderSubMenuDaoUtils.insertByOne(orderSubMenu)) {
                                        subMenuqueryAll(superMenuId);
                                        mydismissDialog(dialog, true);
                                    } else {
                                        Toast.makeText(mContext, "添加菜品失败！", Toast.LENGTH_SHORT).show();
                                        mydismissDialog(dialog, false);
                                    }
                                } else if (!StringUtils.isDoubleOrFloat(subMenu_sale_dialog_ed.getText().toString())) {
                                    Toast.makeText(mContext, "请填写正确的折扣", Toast.LENGTH_SHORT).show();
                                    mydismissDialog(dialog, false);
                                } else if (Float.valueOf(subMenu_sale_dialog_ed.getText().toString()) > 10 ||
                                        Float.valueOf(subMenu_sale_dialog_ed.getText().toString()) < 0) {
                                    //否者输入数字除以10
                                    Toast.makeText(mContext, "请输入10到1之间的数，保留2为小数点", Toast.LENGTH_SHORT).show();
                                    mydismissDialog(dialog, false);
                                } else {
                                    orderSubMenu.setPrice((float)(Float.valueOf(subMenu_sale_dialog_ed.getText().toString())*0.1));
                                    Toast.makeText(mContext, "折扣=" + subMenu_sale_dialog_ed.getText().toString(), Toast.LENGTH_SHORT).show();
                                    if (orderSubMenuDaoUtils.insertByOne(orderSubMenu)) {
                                        subMenuqueryAll(superMenuId);
                                        mydismissDialog(dialog, true);
                                    } else {
                                        Toast.makeText(mContext, "添加菜品失败！", Toast.LENGTH_SHORT).show();
                                        mydismissDialog(dialog, false);

                                    }
                                }
                            }
                        }

                    }
                })
                .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mydismissDialog(dialog,true);
                    }
                })
                .show();
        subMenu_sale_dialog_reset_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subMenu_sale_dialog_ed.setText("");
            }
        });
        subMenu_setting_dialog_delete_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("提示")
                        .setMessage("删除："+superMenu_sp_select_name+"-"+orderSubMenu.getName())
                        .setNeutralButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(orderSubMenuDaoUtils.deleteByOne(orderSubMenu)){
                                    subMenuAdd_dialog.dismiss();
                                    subMenuqueryAll(superMenuId);
                                }else{
                                    Toast.makeText(mContext,"删除失败！",Toast.LENGTH_SHORT).show();
                                }

                            }
                        })
                        .setPositiveButton("取消",null)
                        .show();

            }
        });
    }

    private void subMenuqueryAll(Long superId){
        orderSubMenuList.clear();
        String sql = "where SUPER_MENU_Id = ?";
        String[] condition = new String[]{String.valueOf(superId)};
        List<OrderSubMenu> orderSubMenuUtilsList = orderSubMenuDaoUtils.queryByNativeSql(sql,condition);
        if (orderSubMenuUtilsList.size()>0){
            orderSubMenuList=orderSubMenuUtilsList;
        }
        if(orderSubMenuList.size()>0) {
            SubMenuAdapter = new OrderSubMenuAdapter(mContext, orderSubMenuList, R.layout.order_submenu_listview_item);
            subMenu_RecyclerView.setAdapter(SubMenuAdapter);
            SubMenuAdapter.notifyDataSetChanged();
            SubMenuAdapter.setmItemClickListener(new OrderSubMenuAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Toast.makeText(mContext,orderSubMenuList.get(position).getName(),Toast.LENGTH_SHORT).show();
                }
            });
            SubMenuAdapter.setOnItemLongTouchLinstener(new OrderSubMenuAdapter.OnItemLongTouchLinstener() {
                @Override
                public void onLongTouch(View view, int position) {
                    addSubMenuDialog(orderSubMenuList.get(position),"update");
                }
            });
        }

    }

    private void superMenuqueryAll(){
        orderSuperMenuList.clear();
        orderSubMenuList.clear();
        List<OrderSuperMenu> orderSuperMenuDaoUtilsList = orderSuperMenuDaoUtils.queryAll();
        if (orderSuperMenuDaoUtilsList.size()>0){
            orderSuperMenuList=orderSuperMenuDaoUtilsList;

        }
       if(orderSuperMenuList.size()>0) {
           superMenuAdapter = new OrderSuperMenuAdapter(mContext, orderSuperMenuList, R.layout.order_supermenu_listview_item);
           superMenu_sp.setAdapter(superMenuAdapter);
           superMenuAdapter.notifyDataSetChanged();
       }
    }


    public void mydismissDialog(DialogInterface dialog,boolean isdismiss){
        try {
            //不关闭 isdismiss=false

            Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");

            field.setAccessible(true);

            field.set(dialog, isdismiss);

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}

package com.wdq.micorestore.grocery.setting.inwarehouse;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.wdq.micorestore.BaseActivity;
import com.wdq.micorestore.R;
import com.wdq.micorestore.common.Common;
import com.wdq.micorestore.grocery.bean.GroceryGoods;
import com.wdq.micorestore.grocery.bean.GroceryGoodsBrandBean;
import com.wdq.micorestore.grocery.bean.GroceryGoodsHistoryTypeBean;
import com.wdq.micorestore.grocery.bean.GroceryGoodsSizeBean;
import com.wdq.micorestore.grocery.dao.GroceryGoodsBrandBeanDAOUtils;
import com.wdq.micorestore.grocery.dao.GroceryGoodsDaoUtils;
import com.wdq.micorestore.grocery.dao.GroceryGoodsHistorySizeBeanDaoUtils;
import com.wdq.micorestore.grocery.dao.GroceryGoodsHistoryTypeBeanDaoUtils;
import com.wdq.micorestore.utils.DateUtil;
import com.wdq.micorestore.zxing.client.MyCaptureActivity;

import java.util.List;

public class GrocerySettingInWarehouseActivity extends BaseActivity {
    private Context mContext;
    EditText cqcode_ed,inwarehouse_ed_in_date,grocery_goods_name_ed,
            grocery_goods_code_ed,grocery_goods_brand_ed,grocery_goods_costprice,grocery_goods_price,
            grocery_goods_type_ed,grocery_goods_size_ed,grocery_goods_date_ed,grocery_in_date_ed,grocery_in_numb_ed,
            grocery_remark_ed;//  activity_grocery_setting_inwarehouse_ed_in_date
    ImageButton cqCode_IBn;
    Button goodsType_history_bn,goodsSize_history_bn,goodsDate_bn,goods_brand_bn;
    Button submit_bn;

    GroceryGoodsDaoUtils groceryGoodsDaoUtils;
    GroceryGoodsHistoryTypeBeanDaoUtils historyTypeBeanDaoUtils;
    GroceryGoodsHistorySizeBeanDaoUtils historySizeBeanDaoUtils;
    GroceryGoodsBrandBeanDAOUtils brandBeanDAOUtils;

    private boolean isCqcode_ed=false;
    private GroceryGoods mGroceryGoods;
    @Override
    protected void setContentLayout() {
        mContext=this;
        setContentView(R.layout.activity_grocery_setting_inwarehouse_layout);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        cqCode_IBn=findViewById(R.id.activity_grocery_setting_inwarehouse_bn_cqcode);

        cqcode_ed =findViewById(R.id.activity_grocery_setting_inwarehouse_ed_cqcode);
        inwarehouse_ed_in_date=findViewById(R.id.activity_grocery_setting_inwarehouse_ed_in_date);
        grocery_goods_code_ed=findViewById(R.id.activity_grocery_setting_inwarehouse_ed_code);
        grocery_goods_name_ed =findViewById(R.id.activity_grocery_setting_inwarehouse_ed_goods_name);
        grocery_goods_brand_ed =findViewById(R.id.activity_grocery_setting_inwarehouse_ed_goods_brand);
        grocery_goods_type_ed =findViewById(R.id.activity_grocery_setting_inwarehouse_ed_goods_type);
        grocery_goods_size_ed =findViewById(R.id.activity_grocery_setting_inwarehouse_ed_goods_size);
        grocery_goods_date_ed =findViewById(R.id.activity_grocery_setting_inwarehouse_ed_goods_date);
        grocery_in_date_ed =findViewById(R.id.activity_grocery_setting_inwarehouse_ed_in_date);
        grocery_in_numb_ed =findViewById(R.id.activity_grocery_setting_inwarehouse_ed_in_numb);
        grocery_remark_ed =findViewById(R.id.activity_grocery_setting_inwarehouse_ed_remark);
        grocery_goods_costprice=findViewById(R.id.activity_grocery_setting_inwarehouse_ed_goods_costprice);
        grocery_goods_price=findViewById(R.id.activity_grocery_setting_inwarehouse_ed_goods_price);

        goodsType_history_bn=findViewById(R.id.activity_grocery_setting_inwarehouse_bn_goods_type);
        goodsSize_history_bn=findViewById(R.id.activity_grocery_setting_inwarehouse_bn_goods_size);
        goodsDate_bn=findViewById(R.id.activity_grocery_setting_inwarehouse_bn_goods_date);
        goods_brand_bn=findViewById(R.id.activity_grocery_setting_inwarehouse_bn_goods_brand);

        submit_bn=findViewById(R.id.activity_grocery_setting_inwarehouse_bn_submit);

        groceryGoodsDaoUtils=new GroceryGoodsDaoUtils(mContext);
        historyTypeBeanDaoUtils=new GroceryGoodsHistoryTypeBeanDaoUtils(mContext);
        historySizeBeanDaoUtils=new GroceryGoodsHistorySizeBeanDaoUtils(mContext);
        brandBeanDAOUtils=new GroceryGoodsBrandBeanDAOUtils(mContext);
    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void setView() {
        inwarehouse_ed_in_date.setText(DateUtil.geDate());
    }

    @Override
    protected void onClick() {
        cqCode_IBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(mContext,MyCaptureActivity.class);
                startActivityForResult(intent,Common.REQUEST_CODE);
            }
        });
        goods_brand_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<GroceryGoodsBrandBean> mList = brandBeanDAOUtils.queryAllMeizi();
                if(mList.size()>0){

                }else{
                    Toast.makeText(mContext,"没有历史纪录",Toast.LENGTH_SHORT).show();
                }
            }
        });

        goodsType_history_bn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    List<GroceryGoodsHistoryTypeBean> mlist = historyTypeBeanDaoUtils.queryAllMeizi();
                    if(mlist.size()>0){

                    }else{
                        Toast.makeText(mContext,"没有历史纪录",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(mContext,"没有历史纪录",Toast.LENGTH_SHORT).show();
                }

            }
        });
        goodsSize_history_bn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try{
                    List<GroceryGoodsSizeBean> mlist = historySizeBeanDaoUtils.queryAllMeizi();
                    if(mlist.size()>0){

                    }else{
                        Toast.makeText(mContext,"没有历史纪录",Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(mContext,"没有历史纪录",Toast.LENGTH_SHORT).show();
                }

            }
        });
        goodsDate_bn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });
        submit_bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cqcode_ed.getText().toString().trim().equals("")){
                    Toast.makeText(mContext,"请扫描商品"+getResources().getString(R.string.grocery_cqcode),Toast.LENGTH_SHORT).show();
                }else if(grocery_goods_name_ed.getText().toString().trim().equals("")) {
                    Toast.makeText(mContext,"请填写："+getResources().getString(R.string.grocery_goods_name),Toast.LENGTH_SHORT).show();
                }else if(grocery_in_numb_ed.getText().toString().trim().equals("")){
                    Toast.makeText(mContext,"请填写："+getResources().getString(R.string.grocery_goods_name),Toast.LENGTH_SHORT).show();
                }else{
                    if(!grocery_goods_size_ed.getText().toString().trim().equals("")){
                        //不为空时插入历史 规格
                        GroceryGoodsSizeBean bean=new GroceryGoodsSizeBean();
                        bean.setSizeName(grocery_goods_size_ed.getText().toString());
                        String[] str={grocery_goods_size_ed.getText().toString()};
                        if(historySizeBeanDaoUtils.queryMeiziByNativeSql("where SIZE_NAME=?",str).isEmpty()) {
                            historySizeBeanDaoUtils.insertGoods(bean);
                        }
                    }
                    if(!grocery_goods_type_ed.getText().toString().trim().equals("")){
                        //不为空时插入历史 类型
                        GroceryGoodsHistoryTypeBean bean=new GroceryGoodsHistoryTypeBean();
                        bean.setTypeName(grocery_goods_type_ed.getText().toString());

                        String[] str={grocery_goods_type_ed.getText().toString()};
                        if(historyTypeBeanDaoUtils.queryMeiziByNativeSql("where TYPE_NAME=?",str).isEmpty()) {
                            historyTypeBeanDaoUtils.insertGoods(bean);
                        }
                    }
                    if(!grocery_goods_brand_ed.getText().toString().trim().equals("")){
                        GroceryGoodsBrandBean brandBean=new GroceryGoodsBrandBean();
                        brandBean.setBeandName(grocery_goods_brand_ed.getText().toString());
//                        brandBeanDAOUtils.insertGoods(brandBean);
                        String[] str={grocery_goods_brand_ed.getText().toString()};
                        if(brandBeanDAOUtils.queryMeiziByNativeSql("where BEAND_NAME=?",str).isEmpty()) {
                            brandBeanDAOUtils.insertGoods(brandBean);
                        }
                    }

                        //插入商品
                        GroceryGoods groceryGoods = new GroceryGoods();
                        groceryGoods.setGrocery_cqcode(cqcode_ed.getText().toString());

                        groceryGoods.setGrocery_cqcode(cqcode_ed.getText().toString());//">二维码号</string>
                        groceryGoods.setGrocery_code(grocery_goods_code_ed.getText().toString());//">商品代码</string>
                        groceryGoods.setGrocery_goods_name(grocery_goods_name_ed.getText().toString());//">商品名</string>
                        groceryGoods.setGrocery_goods_brand(grocery_goods_brand_ed.getText().toString());//">商品品牌</string>
                        groceryGoods.setGrocery_goods_type(grocery_goods_type_ed.getText().toString());//">商品类型</string>
                        groceryGoods.setGrocery_goods_size(grocery_goods_size_ed.getText().toString());//">规格</string>
                        groceryGoods.setGrocery_goods_date(grocery_goods_date_ed.getText().toString());//">生产日期</string>
                        groceryGoods.setGrocery_in_date(grocery_in_date_ed.getText().toString());//">入库日期</string>
                        groceryGoods.setGrocery_in_numb(grocery_in_numb_ed.getText().toString());//">入库数量</string>
                        groceryGoods.setGrocery_up_sale("0");//">上架</string>
                        groceryGoods.setGrocery_down_sale("0");//">下架</string>
                        groceryGoods.setCost_price(grocery_goods_costprice.getText().toString());
                        groceryGoods.setPrice(grocery_goods_price.getText().toString());
                        groceryGoods.setGrocery_remark(grocery_remark_ed.getText().toString());//">备注</string>
                    if(isCqcode_ed) {
                        if (groceryGoodsDaoUtils.insertGoods(groceryGoods)) {
                            resetGoodsValue_Ed();
                        }
                    }else{
                        mGroceryGoods.setGrocery_cqcode(cqcode_ed.getText().toString());

                        mGroceryGoods.setGrocery_cqcode(cqcode_ed.getText().toString());//">二维码号</string>
                        mGroceryGoods.setGrocery_code(grocery_goods_code_ed.getText().toString());//">商品代码</string>
                        mGroceryGoods.setGrocery_goods_name(grocery_goods_name_ed.getText().toString());//">商品名</string>
                        mGroceryGoods.setGrocery_goods_brand(grocery_goods_brand_ed.getText().toString());//">商品品牌</string>
                        mGroceryGoods.setGrocery_goods_type(grocery_goods_type_ed.getText().toString());//">商品类型</string>
                        mGroceryGoods.setGrocery_goods_size(grocery_goods_size_ed.getText().toString());//">规格</string>
                        mGroceryGoods.setGrocery_goods_date(grocery_goods_date_ed.getText().toString());//">生产日期</string>
                        mGroceryGoods.setGrocery_in_date(grocery_in_date_ed.getText().toString());//">入库日期</string>
                        mGroceryGoods.setGrocery_in_numb(grocery_in_numb_ed.getText().toString());//">入库数量</string>
                        mGroceryGoods.setGrocery_up_sale("0");//">上架</string>
                        mGroceryGoods.setGrocery_down_sale("0");//">下架</string>
                        mGroceryGoods.setCost_price(grocery_goods_costprice.getText().toString());
                        mGroceryGoods.setPrice(grocery_goods_price.getText().toString());
                        mGroceryGoods.setGrocery_remark(grocery_remark_ed.getText().toString());//">备注</string>

                        if (groceryGoodsDaoUtils.updateMeizi(mGroceryGoods)) {
                            Log.e("GrocerySeInWarehouse","GrocerySettingInWarehouseActivity--if");
                            resetGoodsValue_Ed();
                        }else{
                            Log.e("GrocerySeInWarehouse","GrocerySettingInWarehouseActivity--else");
                        }
                    }

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Common.REQUEST_CODE){
            Log.e(getClass().getName(), "获取到的扫描结果是：" + resultCode);
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                Log.e(getClass().getName(), "获取到的扫描结果是：" + resultCode);
                if (bundle.getInt(Common.RESULT_TYPE) == Common.RESULT_SUCCESS) {
                    String result = bundle.getString(Common.RESULT_STRING);
//                    goods_code.setText("产品编号："+result);
//                    goodsCodeStr=result;
                    String[] str={result};
                    List<GroceryGoods> queryCqCodeGoodsList=
                            groceryGoodsDaoUtils.queryMeiziByNativeSql("where grocery_cqcode=?",str);
                    if(queryCqCodeGoodsList.isEmpty()){
                        isCqcode_ed=true;
                        cqcode_ed.setText(result);
                    }else {
                        isCqcode_ed=false;
                        setGoodsValue_Ed(queryCqCodeGoodsList.get(0));
                        mGroceryGoods=queryCqCodeGoodsList.get(0);
                    }
                    Toast.makeText(mContext, "二维码解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(Common.RESULT_TYPE) == Common.RESULT_FAILED) {
                    Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }else{
            Toast.makeText(mContext, "解析二维码失败", Toast.LENGTH_LONG).show();
        }
    }

    private void setGoodsValue_Ed(GroceryGoods goodsBean){

        cqcode_ed .setText(goodsBean.getGrocery_cqcode());
        inwarehouse_ed_in_date.setText(goodsBean.getGrocery_in_date());
        grocery_goods_code_ed.setText(goodsBean.getGrocery_code());
        grocery_goods_name_ed .setText(goodsBean.getGrocery_goods_name());
        grocery_goods_brand_ed.setText(goodsBean.getGrocery_goods_brand());
        grocery_goods_type_ed.setText(goodsBean.getGrocery_goods_type());
        grocery_goods_size_ed .setText(goodsBean.getGrocery_goods_size());
        grocery_goods_date_ed .setText(goodsBean.getGrocery_goods_date());
        grocery_in_date_ed .setText(goodsBean.getGrocery_in_date());
        grocery_in_numb_ed .setText(goodsBean.getGrocery_in_numb());
        grocery_remark_ed .setText(goodsBean.getGrocery_remark());
        grocery_goods_costprice .setText(goodsBean.getCost_price());
        grocery_goods_price.setText(goodsBean.getPrice());
    }

    private void resetGoodsValue_Ed(){

        cqcode_ed .setText("");
        inwarehouse_ed_in_date.setText("");
        grocery_goods_code_ed.setText("");
        grocery_goods_name_ed.setText("");
        grocery_goods_brand_ed.setText("");
        grocery_goods_type_ed.setText("");
        grocery_goods_size_ed.setText("");
        grocery_goods_date_ed.setText("");
        grocery_in_date_ed.setText("");
        grocery_in_numb_ed.setText("");
        grocery_remark_ed.setText("");
        grocery_goods_costprice .setText("");
        grocery_goods_price.setText("");
    }
}

package com.wdq.micorestore;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.uuzuche.lib_zxing.activity.CaptureActivity;
//import com.uuzuche.lib_zxing.activity.CodeUtils;
//import com.wdq.micorestore.DAO.GoodsDAOUtils;
//import com.wdq.micorestore.DAO.SizeClornumbDAOUtils;
//import com.wdq.micorestore.bean.GoodsBean;
//import com.wdq.micorestore.bean.SizeClorNumbBean;
//import com.wdq.micorestore.common.Common;
//import com.wdq.micorestore.utils.DateUtil;

/**
 * Created by sinosoft_wan on 2018/9/21.
 * 用于厂库 商品录入
 * 包含条形码，二维码扫描，商品信息录入
 */

public class GoodsImportActivity{}
//extends Activity {
//    Context mContext;
//    Button back_bn,camera_bn,goodsimport_submit_bn;
//    TextView goods_code,goods_date;
//    EditText goods_name,goods_costprice,goods_sellprice,goods_info,goods_class;
//
//    GoodsDAOUtils goodsDAOUtils;
//    SizeClornumbDAOUtils sizeClornumbDAOUtils;
//
//    String goodsCodeStr="";
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.acitivity_goodsimport);
//        mContext=this;
//        initView();
//        getData();
//        setData();
//        onLinsteners();
//    }
//    public void getData() {
//    }
//    private void setData() {
//        goods_date.setText(DateUtil.geDate());
//    }
//
//    private void initView() {
//        goodsDAOUtils=new GoodsDAOUtils(this);
//        sizeClornumbDAOUtils=new SizeClornumbDAOUtils(this);
//
//        back_bn=findViewById(R.id.goodsimport_back_bn);
//        camera_bn=findViewById(R.id.goodsimport_camera_bn);
//        goodsimport_submit_bn=findViewById(R.id.goodsimport_submit_bn);
//
//        goods_code=findViewById(R.id.goods_code);
//        goods_date=findViewById(R.id.goods_date);
//
//        goods_name=findViewById(R.id.goods_name);
//        goods_costprice=findViewById(R.id.goods_costprice);
//        goods_sellprice=findViewById(R.id.goods_sellprice);
//        goods_info=findViewById(R.id.goods_info);
//        goods_class=findViewById(R.id.goods_class);
//    }
//
//    private void onLinsteners() {
//        back_bn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        camera_bn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(GoodsImportActivity.this, CaptureActivity.class);
//                startActivityForResult(intent, Common.REQUEST_CODE);
//            }
//        });
//        goodsimport_submit_bn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                GoodsBean bean=new GoodsBean();
//                bean.setName(goods_name.getText().toString());
//                bean.setGoodsCode(goodsCodeStr);
//                bean.setCostPrice(Float.valueOf(goods_costprice.getText().toString()));
//                bean.setCreatDate(DateUtil.geDate(goods_date.getText().toString()));
//
//                SizeClorNumbBean sizeClorNumbBean=new SizeClorNumbBean();
//                sizeClorNumbBean.setGoodsCode(goodsCodeStr);
//                sizeClorNumbBean.setType1_name("尺码");
//                sizeClorNumbBean.setType1_value("x");
//                sizeClorNumbBean.setType2_name("颜色");
//                sizeClorNumbBean.setType2_value("红色");
//                sizeClorNumbBean.setType3_name("库存");
//                sizeClorNumbBean.setType3_value("2");
//
//                if(goods_sellprice.getText().toString().trim().equals("")){
//                    bean.setSellingPrice(Float.valueOf(goods_costprice.getText().toString()));
//                }else {
//                    bean.setSellingPrice(Float.valueOf(goods_sellprice.getText().toString()));
//                }
//                if(goodsCodeStr.equals("")){
//                    Toast.makeText(mContext, "编码为空！", Toast.LENGTH_SHORT).show();
//                }else {
//                    if (goodsDAOUtils.insertGoods(bean)) {
//                        sizeClornumbDAOUtils.insertGoods(sizeClorNumbBean);
//                        Toast.makeText(mContext, "添加成功", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(mContext, "失败！！！", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==Common.REQUEST_CODE){
//            //处理扫描结果（在界面上显示）
//            if (null != data) {
//                Bundle bundle = data.getExtras();
//                if (bundle == null) {
//                    return;
//                }
//                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
//                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    goods_code.setText("产品编号："+result);
//                    goodsCodeStr=result;
////                    Toast.makeText(this, "二维码解析结果:" + result, Toast.LENGTH_LONG).show();
//                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
//                    Toast.makeText(GoodsImportActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
//                }
//            }
//        }
//    }
//
//
//}

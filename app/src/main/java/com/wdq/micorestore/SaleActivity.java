package com.wdq.micorestore;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.wdq.micorestore.DAO.GoodsDAOUtils;
import com.wdq.micorestore.bean.GoodsBean;
import com.wdq.micorestore.common.Common;
import com.wdq.micorestore.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class SaleActivity extends AppCompatActivity {
    TextView sale_good_code,sale_date,sale_good_name,sale_good_costprice,sale_good_sellingprice;

    GoodsDAOUtils goodsDAOUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        Toolbar toolbar = (Toolbar) findViewById(R.id.sale_toolbar);
        goodsDAOUtils=new GoodsDAOUtils(this);
        initView();
        initSetViewData();

        setSupportActionBar(toolbar);
        Intent intent=new Intent(SaleActivity.this, CaptureActivity.class);
        startActivityForResult(intent, Common.REQUEST_CODE);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.sale_submit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    private void getData(String codeStr) {
        String sql = "where GOODS_CODE = ?";
        String[] condition = new String[]{codeStr};
        List<GoodsBean> goodsBeanList=new ArrayList<>();
        goodsBeanList= goodsDAOUtils.queryMeiziByNativeSql(sql,condition);
        for (int i=0;i<goodsBeanList.size();i++){
//            sale_good_code.setText(goodsBeanList.get(i).getGoodsCode());
            sale_date.setText(DateUtil.geDate());
            sale_good_name.setText(goodsBeanList.get(i).getName());
            sale_good_costprice.setText("原价："+String.valueOf(goodsBeanList.get(i).getCostPrice()));
            sale_good_sellingprice.setText(String.valueOf(goodsBeanList.get(i).getSellingPrice()));
        }
    }


    private void initView() {
        sale_good_code=findViewById(R.id.sale_good_code);
        sale_date=findViewById(R.id.sale_date);
        sale_good_name=findViewById(R.id.sale_good_name);
        sale_good_costprice=findViewById(R.id.sale_good_costprice);
        sale_good_sellingprice=findViewById(R.id.sale_good_sellingprice);
    }

    private void initSetViewData() {
        sale_date.setText(DateUtil.geDate());
        sale_good_name.setText(DateUtil.geDate());
        sale_good_costprice.setText("");
        sale_good_sellingprice.setText(DateUtil.geDate());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Common.REQUEST_CODE){
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    codeStr=result;
                    getData(result);
                    sale_good_code.setText("产品编号："+result);
//                    Toast.makeText(this, "二维码解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(SaleActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}

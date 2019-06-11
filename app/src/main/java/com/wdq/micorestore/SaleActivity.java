package com.wdq.micorestore;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.uuzuche.lib_zxing.activity.CaptureActivity;
//import com.uuzuche.lib_zxing.activity.CaptureFragment;
//import com.uuzuche.lib_zxing.activity.CodeUtils;
//import com.wdq.micorestore.DAO.GoodsDAOUtils;
//import com.wdq.micorestore.DAO.SizeClornumbDAOUtils;
//import com.wdq.micorestore.bean.GoodsBean;
//import com.wdq.micorestore.bean.SizeClorNumbBean;
//import com.wdq.micorestore.common.Common;
//import com.wdq.micorestore.utils.DateUtil;
//
//import java.util.ArrayList;
//import java.util.List;

public class SaleActivity{}
//extends AppCompatActivity {
//
//
//
//    GoodsDAOUtils goodsDAOUtils;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sale);
//
//        initZxing();
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.sale_toolbar);
//        goodsDAOUtils=new GoodsDAOUtils(this);
//
//        initView();
//
//        setSupportActionBar(toolbar);
////        Intent intent=new Intent(SaleActivity.this, CaptureActivity.class);
////        startActivityForResult(intent, Common.REQUEST_CODE);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.sale_submit);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//    }
//
//    private void initZxing(){
//        CaptureFragment captureFragment = new CaptureFragment();
//        // 为二维码扫描界面设置定制化界面
//        CodeUtils.setFragmentArgs(captureFragment, R.layout.my_camera);
//        captureFragment.setAnalyzeCallback(analyzeCallback);
//        getSupportFragmentManager().beginTransaction().replace(R.id.sale_good_zxing, captureFragment).commit();
//    }
//
//    private void getData(String codeStr) {
//        if(codeStr.equals("识别失败！")){
//            Toast.makeText(this,"识别失败！",Toast.LENGTH_SHORT).show();
//        }
//        String sql = "where GOODS_CODE = ?";
//        String[] condition = new String[]{codeStr};
//        List<GoodsBean> goodsBeanList=new ArrayList<>();
//        goodsBeanList= goodsDAOUtils.queryMeiziByNativeSql(sql,condition);
//        for (int i=0;i<goodsBeanList.size();i++){
////            sale_good_code.setText(goodsBeanList.get(i).getGoodsCode());
////            sale_date.setText(DateUtil.geDate());
////            sale_good_name.setText(goodsBeanList.get(i).getName());
////            sale_good_costprice.setText("原价："+String.valueOf(goodsBeanList.get(i).getCostPrice()));
////            sale_good_sellingprice.setText(String.valueOf(goodsBeanList.get(i).getSellingPrice()));
//
//        }
//        SizeClornumbDAOUtils sizeClornumbDAOUtils=new SizeClornumbDAOUtils(this);
//        String sqls = "where GOODS_CODE = ?";
//        String[] conditions = new String[]{codeStr};
//        List<SizeClorNumbBean> sizeBeanList=new ArrayList<>();
//        sizeBeanList= sizeClornumbDAOUtils.queryMeiziByNativeSql(sqls,conditions);
//
//        for (int i=0;i<sizeBeanList.size();i++){
////            sale_good_size.setText(sizeBeanList.get(i).getType1_name()
////                    +"-"+sizeBeanList.get(i).getType2_name()
////                    +"-"+sizeBeanList.get(i).getType3_name());
////            sale_good_color.setText(sizeBeanList.get(i).getType1_value()
////                    +"-"+sizeBeanList.get(i).getType2_value()
////                    +"-"+sizeBeanList.get(i).getType3_value());
////            sale_good_numb.setText(sizeBeanList.get(i).getNumb_value());
//        }
//    }
//
//
//    private void initView() {
//
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
////                    codeStr=result;
////                    getData(result);
////                    Toast.makeText(this, "二维码解析结果:" + result, Toast.LENGTH_LONG).show();
//                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
//                    Toast.makeText(SaleActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
//                }
//            }
//        }
//    }
//
//
//    /**
//     * 二维码解析回调函数
//     */
//    CodeUtils.AnalyzeCallback analyzeCallback = new CodeUtils.AnalyzeCallback() {
//        @Override
//        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
////            Intent resultIntent = new Intent();
////            Bundle bundle = new Bundle();
////            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
////            bundle.putString(CodeUtils.RESULT_STRING, result);
////            resultIntent.putExtras(bundle);
//            Log.e("analyzeCallback",result);
//
//            getData(result);
//            Toast.makeText(SaleActivity.this,result,Toast.LENGTH_SHORT).show();
////            SaleActivity.this.setResult(RESULT_OK, resultIntent);
////            SaleActivity.this.finish();
//        }
//
//        @Override
//        public void onAnalyzeFailed() {
//            Intent resultIntent = new Intent();
//            Bundle bundle = new Bundle();
//            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
//            bundle.putString(CodeUtils.RESULT_STRING, "");
//            resultIntent.putExtras(bundle);
//            Toast.makeText(SaleActivity.this,"识别失败！",Toast.LENGTH_SHORT).show();
////            getData("识别失败！");
////            SaleActivity.this.setResult(RESULT_OK, resultIntent);
////            SaleActivity.this.finish();
//        }
//    };
//}

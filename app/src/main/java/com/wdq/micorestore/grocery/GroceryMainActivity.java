package com.wdq.micorestore.grocery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

//import com.google.zxing.client.android.CaptureActivity;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.wdq.micorestore.Launcher;
import com.wdq.micorestore.R;
import com.wdq.micorestore.common.Common;

import java.util.List;

public class GroceryMainActivity extends AppCompatActivity {

    private Context mContext;

    private ImageButton setting_IBn;

   //zxing扫描
//    private CaptureManager capture;
    private DecoratedBarcodeView bv_barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_grocery_main);

        bv_barcode = findViewById(R.id.bv_barcode);
        bv_barcode.decodeContinuous(barcodeCallback);
        initView();
        onClick();
    }
    private void initView(){
        setting_IBn=findViewById(R.id.grocery_main_header_setting_bn);
    };
    private void onClick(){
        setting_IBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,GrocerySettingActivity.class);
                startActivity(intent);
            }
        });
    };
    private BarcodeCallback barcodeCallback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            bv_barcode.pause();
            if (result != null){
                Log.e(getClass().getName(), "获取到的扫描结果是：" + result.getText());
                Toast.makeText(mContext,"获取到的扫描结果是：" + result.getText(),Toast.LENGTH_SHORT).show();
                //可以对result进行一些判断，比如说扫描结果不符合就再进行一次扫描
                if (result.getText().contains("符合我的结果")){
                    //符合的可以不在扫描了，当然你想继续扫描也是可以的
                } else {
                    bv_barcode.resume();
                }
            }
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {

        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        bv_barcode.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bv_barcode.pause();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return bv_barcode.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
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
            }
        }
    }
}

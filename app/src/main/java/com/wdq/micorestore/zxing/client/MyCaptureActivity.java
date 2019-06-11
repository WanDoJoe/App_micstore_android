package com.wdq.micorestore.zxing.client;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.wdq.micorestore.R;
import com.wdq.micorestore.common.Common;

import java.util.List;

/**
 * Created by sinosoft_wan on 2019-6-11.
 */

public class MyCaptureActivity extends AppCompatActivity{
    private Context mContext;
    private DecoratedBarcodeView bv_barcode;
    private Button button;
    private Camera camera;
    private Camera.Parameters parameters;
    public boolean hasClosed = true; // 定义开关状态，状态为false，打开状态，状态为true，关闭状态

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_mycaputer_layout);
        initView();
    }

    private void initView() {
        bv_barcode = findViewById(R.id.mycaputer_bv_barcode);
        bv_barcode.decodeContinuous(barcodeCallback);
    }
    private BarcodeCallback barcodeCallback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
//            bv_barcode.pause();
            if (result != null){
                Log.e(getClass().getName(), "获取到的扫描结果是：" + result.getText());
//                Toast.makeText(mContext,"获取到的扫描结果是：" + result.getText(),Toast.LENGTH_SHORT).show();
                //可以对result进行一些判断，比如说扫描结果不符合就再进行一次扫描
//                if (result.getText().contains("符合我的结果")){
                    //符合的可以不在扫描了，当然你想继续扫描也是可以的
                    Intent resultIntent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putInt(Common.RESULT_TYPE, Common.RESULT_SUCCESS);
                    bundle.putString(Common.RESULT_STRING, result.getText());
                    resultIntent.putExtras(bundle);
                    MyCaptureActivity.this.setResult(RESULT_OK, resultIntent);
                    MyCaptureActivity.this.finish();
//                } else {
//                    bv_barcode.resume();
//                }
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


    /**
     * 闪光灯
     * @param view
     */
    public void toggleLight(View view) {
        if (hasClosed) {
            camera = Camera.open();
            parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);// 开启
            camera.setParameters(parameters);
            button.setText("关闭闪光灯");
            hasClosed = false;
        } else {
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);// 关闭
            camera.setParameters(parameters);
            button.setText("开启闪光灯");
            hasClosed = true;
            camera.release();
        }
    }
}

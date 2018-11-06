package com.wdq.micorestore;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.wdq.micorestore.utils.CheckPermissionUtils;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity {}
//extends AppCompatActivity implements EasyPermissions.PermissionCallbacks {
//    /**
//     * 扫描跳转Activity RequestCode
//     */
//    public static final int REQUEST_CODE = 111;
//    /**
//     * 选择系统图片Request Code
//     */
//    public static final int REQUEST_IMAGE = 112;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Button button1=(Button) findViewById(R.id.button1);
//
//        button1.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplication(),CaptureActivity.class);
//                startActivityForResult(intent, REQUEST_CODE);
//
//            }
//        });
////初始化权限
//        initPermission();
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        /**
//         * 处理二维码扫描结果
//         */
//        if (requestCode == REQUEST_CODE) {
//            //处理扫描结果（在界面上显示）
//            if (null != data) {
//                Bundle bundle = data.getExtras();
//                if (bundle == null) {
//                    return;
//                }
//                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
//                    String result = bundle.getString(CodeUtils.RESULT_STRING);
//                    Toast.makeText(this, "二维码解析结果:" + result, Toast.LENGTH_LONG).show();
//                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
//                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
//                }
//            }
//        }
//
//        /**
//         * 选择系统图片并解析
//         */
////        else if (requestCode == REQUEST_IMAGE) {
////            if (data != null) {
////                Uri uri = data.getData();
////                try {
////                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(this, uri), new CodeUtils.AnalyzeCallback() {
////                        @Override
////                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
////                            Toast.makeText(MainActivity.this, "图片二维码解析结果:" + result, Toast.LENGTH_LONG).show();
////                        }
////
////                        @Override
////                        public void onAnalyzeFailed() {
////                            Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
////                        }
////                    });
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////            }
////        }
//
//        else if (requestCode == REQUEST_CAMERA_PERM) {
//            Toast.makeText(this, "从设置页面返回...", Toast.LENGTH_SHORT)
//                    .show();
//        }
//    }
//
//    /**
//     * 初始化权限事件
//     */
//    private void initPermission() {
//        //检查权限
//        String[] permissions = CheckPermissionUtils.checkPermission(this);
//        if (permissions.length == 0) {
//            //权限都申请了
//            //是否登录
//        } else {
//            //申请权限
//            ActivityCompat.requestPermissions(this, permissions, 100);
//        }
//    }
//
//    /**
//     * 请求CAMERA权限码
//     */
//    public static final int REQUEST_CAMERA_PERM = 101;
//
//    @Override
//    public void onPermissionsGranted(int requestCode, List<String> perms) {
//        Toast.makeText(this, "执行onPermissionsGranted()...", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        Toast.makeText(this, "执行onPermissionsDenied()...", Toast.LENGTH_SHORT).show();
//        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//            new AppSettingsDialog.Builder(this, "当前App需要申请camera权限,需要打开设置页面么?")
//                    .setTitle("权限申请")
//                    .setPositiveButton("确认")
//                    .setNegativeButton("取消", null /* click listener */)
//                    .setRequestCode(REQUEST_CAMERA_PERM)
//                    .build()
//                    .show();
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        // Forward results to EasyPermissions
//        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
//    }
//    @AfterPermissionGranted(REQUEST_CAMERA_PERM)
//    public void cameraTask(int viewId) {
//        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
//            // Have permission, do the thing!
//            Log.e("aaa","Have permission, do the thing!");
//        } else {
//            // Ask for one permission
//            EasyPermissions.requestPermissions(this, "需要请求camera权限",
//                    REQUEST_CAMERA_PERM, Manifest.permission.CAMERA);
//        }
//    }
//
//}

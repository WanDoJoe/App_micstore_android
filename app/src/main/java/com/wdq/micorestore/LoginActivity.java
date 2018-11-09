package com.wdq.micorestore;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wdq.micorestore.common.Common;
import com.wdq.micorestore.httpapi.HttpApi;
import com.wdq.micorestore.httpapi.MyDefaultObserver;
import com.wdq.micorestore.order.OrderMainActivity;
import com.wdq.micorestore.utils.AesEncodeUtil;
import com.wdq.micorestore.utils.CheckPermissionUtils;
import com.wdq.micorestore.utils.MD5Utils;
import com.wdq.micorestore.utils.PhoneInfoUtil;
import com.wdq.micorestore.utils.StringUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by sinosoft_wan on 2018/9/20.
 */

public class LoginActivity extends Activity implements EasyPermissions.PermissionCallbacks{

    private Button login_bn;
    private EditText username_ET,password_ET;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.);
        setContentView(R.layout.activity_login);
        initPermission();
        initView();
    }

    private void initView() {
        login_bn=findViewById(R.id.login_bn);
        username_ET=findViewById(R.id.login_username_et);
        password_ET=findViewById(R.id.login_password_et);
        username_ET.setText("admin");
        password_ET.setText("admin");
        login_bn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(username_ET.getText().toString().trim().equals("")){
                    Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }else if(password_ET.getText().toString().trim().equals("")){
                    Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }

                String password= MD5Utils.md5Password(password_ET.getText().toString());
//                Log.e("aaaaaaaaaaaaaaaaaaaaaa",password);

                if(Common.isNotNetWork){
                    if(username_ET.getText().toString().equals("admin")&&password_ET.getText().toString().equals("admin")) {
                        Intent intent = new Intent(LoginActivity.this, Launcher.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    HttpApi.loginRESTfulk("login", username_ET.getText().toString(),password ,
                            setInfo(), new MyDefaultObserver<String>(LoginActivity.this) {
                                @Override
                                protected void onSuccess(String o) {

                                    try {

                                        JSONObject jsonObject=new JSONObject(o);
                                        if(jsonObject.getString("result").equals("success")) {
                                            Log.e("login",jsonObject.toString());
//                                            Intent intent = new Intent(LoginActivity.this, Launcher.class);
                                            Intent intent = new Intent(LoginActivity.this, OrderMainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }else{
//                                ToastShow.show(LoginActivity.this,jsonObject.getString("message"));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                }



            }
        });
    }

    /**
     * 初始化权限事件
     */
    private void initPermission() {
        //检查权限
        String[] permissions = CheckPermissionUtils.checkPermission(this);
        if (permissions.length == 0) {
            //权限都申请了
            //是否登录
        } else {
            //申请权限
            ActivityCompat.requestPermissions(this, permissions, 100);
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
//        Toast.makeText(this, "执行onPermissionsGranted()...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
//        Toast.makeText(this, "执行onPermissionsDenied()...", Toast.LENGTH_SHORT).show();
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
//            new AppSettingsDialog.Builder(this, "当前App需要申请camera权限,需要打开设置页面么?")
//                    .setTitle("权限申请")
//                    .setPositiveButton("确认")
//                    .setNegativeButton("取消", null /* click listener */)
//                    .setRequestCode(Common.REQUEST_CAMERA_PERM)
//                    .build()
//                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @AfterPermissionGranted(Common.REQUEST_CAMERA_PERM)
    public void cameraTask(int viewId) {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            // Have permission, do the thing!
            Log.e("aaa","Have permission, do the thing!");
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "需要请求camera权限",
                    Common.REQUEST_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }

    private String setInfo(){
        JSONObject json=new JSONObject();
        try {

            json.put("appversion",PhoneInfoUtil.getVerName(this));
            json.put("appVersionCode",PhoneInfoUtil.getVersionCode(this));
            json.put("SystemVersion",PhoneInfoUtil.getSystemVersion());
            json.put("SystemModel",PhoneInfoUtil.getSystemModel());
            json.put("IMEI",PhoneInfoUtil.getIMEI(this));
            json.put("DeviceBrand",PhoneInfoUtil.getDeviceBrand());
            json.put("SystemType","Android");
            Log.e("loginacitivyt",json.toString());

            return StringUtils.replaceStr_n(AesEncodeUtil.encrypt(json.toString()));
        } catch (JSONException e) {
            e.printStackTrace();
            return "error:"+e.getLocalizedMessage();
        }

//        return "";
    }
}

package com.wdq.micorestore;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.wdq.micorestore.adapter.LauncherAdapter;
import com.wdq.micorestore.bean.LauncherItemBean;
import com.wdq.micorestore.common.Common;
import com.wdq.micorestore.utils.AssetsUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sinosoft_wan on 2018/9/21.
 */

public class Launcher extends BaseActivity {
    List<LauncherItemBean> mlist;

    RecyclerView recyclerView;
    ImageView search_img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        initMenu();
        initView();
        initdata();
        onListener();

    }

    private void initdata() {

    }

    private void initView() {
        recyclerView=findViewById(R.id.launcher_recyclerview);
        search_img=findViewById(R.id.search_img);
        recyclerView.setLayoutManager(new GridLayoutManager(Launcher.this,2));
    }


    private void onListener() {
        LauncherAdapter adapter=new LauncherAdapter(this,mlist);
        adapter.setmItemClickListener(new LauncherAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(Launcher.this,mlist.get(position),Toast.LENGTH_SHORT).show();
                try {
                    Intent intent = new Intent(Launcher.this, launcherItam().get(mlist.get(position).getActivity()));
                    startActivity(intent);
                }catch (Exception e){
                    if(e.getLocalizedMessage().contains("java.lang.String java.lang.Class.getName()")){
                        Toast.makeText(Launcher.this,"该模块正在开发中！",Toast.LENGTH_SHORT).show();
                    }else{
                        Log.e("micorestore","Launcher:"+e.getLocalizedMessage());
                    }

                }
            }

        });
        recyclerView.setAdapter(adapter);



        search_img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Launcher.this, CaptureActivity.class);
                startActivityForResult(intent,Common.REQUEST_CODE);
            }
        });
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
                    Toast.makeText(this, "二维码解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(Launcher.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }



    private void initMenu() {
        try {
            String jsonStr= AssetsUtils.getJson(this,"mic_clothingstore_launcher_menu.json");
            mlist=new ArrayList<>();
            JSONObject jsonObject=new JSONObject(jsonStr);
            JSONArray jsonArray=jsonObject.getJSONArray("menu");

            for (int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                LauncherItemBean itemBean=new LauncherItemBean();
                itemBean.setName(object.optString("name"));
                itemBean.setActivity(object.optString("activity"));
                itemBean.setIsAble(object.optString("isAble"));
                itemBean.setImg(object.optString("img"));
                itemBean.setUrl(object.optString("url"));
                mlist.add(itemBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static Map<String ,Class> launcherItam(){
        Map<String,Class> map=new HashMap<>();
        map.put("sale", SaleActivity.class);
        map.put("goods", GoodsImportActivity.class);
        map.put("account", AccountActivity.class);
        return map;
    }
}

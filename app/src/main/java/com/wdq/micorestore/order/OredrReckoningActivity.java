package com.wdq.micorestore.order;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wdq.micorestore.R;
import com.wdq.micorestore.order.bean.OrderSubMenu;
import com.wdq.micorestore.utils.FloatUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OredrReckoningActivity extends AppCompatActivity {
    Context mContext;
    WebView order_reckoning_webview;
    String table="";
    String data="";
    float totle=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_oredr_reckoning);
        try {
//            table=getIntent().getStringExtra("table");
            data =getIntent().getStringExtra("data");
//            Log.e("data",data);
        }catch (Exception e){
            data="";
        }
        initData();
        initView();
        initWebView();
    }

    private void initData() {
        try {
            JSONObject jsonObject=new JSONObject(data);
            JSONArray jsonArray=jsonObject.getJSONArray("list");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                OrderSubMenu subMenu=new OrderSubMenu();
                subMenu.setId(object.getLong("id"));
                int choseNumb=object.getInt("choseNumb");
                subMenu.setChoseNumb(choseNumb);
                subMenu.setCreateYear(object.getString("createYear"));
                subMenu.setIntroduction( object.getString("introduction"));
                subMenu.setIsAble(object.getString("isAble"));
                subMenu.setName(object.getString("name"));
                subMenu.setPinyingId(object.getString("pinyingId"));
                float price=FloatUtils.to2(Float.valueOf(object.getString("price")));
                subMenu.setPrice(price);
                float sale=FloatUtils.to2(Float.valueOf(object.getString("sale")));
                subMenu.setSale(sale);
                subMenu.setSuperMenuId(object.getLong("superMenuId"));
                subMenu.setUnit(object.getString("unit"));
                totle+= FloatUtils.to2(choseNumb*(price));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        order_reckoning_webview=findViewById(R.id.order_reckoning_webview);
    }
@JavascriptInterface
    private void initWebView() {
        order_reckoning_webview.loadUrl("file:///android_asset/orderReckoningDetail.html");
        WebSettings webSettings = order_reckoning_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        order_reckoning_webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(data!=""){
                    show();
                }
            }
        });
    }

    @JavascriptInterface
    public void show(){
        Log.e("show","datav="+data);
//        Log.e("show","table="+table);
        order_reckoning_webview.loadUrl("javascript:show("+data+","+totle+")");
    }

}

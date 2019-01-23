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

public class OredrReckoningActivity extends AppCompatActivity {
    Context mContext;
    WebView order_reckoning_webview;

    String data="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=this;
        setContentView(R.layout.activity_oredr_reckoning);
        try {
            data =getIntent().getStringExtra("data");
//            Log.e("data",data);
        }catch (Exception e){
            data="";
        }

        initView();
        initWebView();
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
        order_reckoning_webview.loadUrl("javascript:show("+data+")");
    }

}

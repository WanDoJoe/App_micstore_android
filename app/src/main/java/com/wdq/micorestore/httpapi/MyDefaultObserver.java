package com.wdq.micorestore.httpapi;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

//import com.demo.widget.DialogWaiting;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by sinosoft_wan on 2017/10/12.
 */
//观察者

public abstract class MyDefaultObserver<T> implements Observer<T> {
    private Context context;
//    private Dialog waiting;
    private boolean isShowWaitingDialog=true;
    public MyDefaultObserver(Context context){
        this.context=context;
//        waiting=new DialogWaiting(context);
    }
    public MyDefaultObserver(Context context,boolean isShowWaitingDialog){
        this.context=context;
//        waiting=new DialogWaiting(context);
        this.isShowWaitingDialog=isShowWaitingDialog;
    }
    @Override
    public void onSubscribe( Disposable d) {
        Log.e("onSubscribe","onSubscribe");
//        showWaiting();
    }

    @Override
    public void onNext(T o) {
        Log.e("onNext","onNext");
        onSuccess(o);
    }

    @Override
    public void onError( Throwable e) {
        Log.e("onError","onError");
        Log.e("", e.getLocalizedMessage());

//        dismissWaiting();
        if(e.getLocalizedMessage().contains("Connection timed out")){
            Toast.makeText(context,"网络连接超时",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "网络请求发生错误:" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComplete() {
        Log.e("onComplete","onComplete");
//        dismissWaiting();
    }
    protected abstract void onSuccess(T o);

//    private void showWaiting(){
//        if(isShowWaitingDialog) {
//            if (waiting != null) {
//                waiting.show();
//            }
//        }
//    }
//    private void dismissWaiting(){
//        if(isShowWaitingDialog) {
//            if (waiting != null) {
//                waiting.dismiss();
//            }
//        }
//    }
}
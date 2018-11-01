package com.wdq.micorestore.httpapi;


import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sinosoft_wan on 2017/10/12.
 * 使用
 * HttpApi.方法名
 *
 *
 */

public class HttpApi {
    public static void hello( String test,Observer observer){
//        get(returnHttpApiInterfa().hello(test), observer);
    }


    public static void login(String method, String uname, String password, Observer observer) {
        try {
            String params = Protocol.login(uname, password);
//            Call<ResponseBody> repos=returnHttpApiInterfa().login(params);//实现对应api参数请求
            setPost(returnHttpApiInterfa().login(params), observer);

        } catch (JSONException e) {

        }
    }

    public static void loginRESTfulk(String method, String uname, String password,String version, Observer observer){
//        String params=method+"/"+uname+"/"+password;
        Log.e("loginRESTfulk",method+"/"+uname+"/"+password);
        requestGet(returnHttpApiInterfa().loginRESTfulk(uname,password,version),observer);
    }

    public static void taskList(String page, String pageSize, Observer observer) {
        try {
            String params = Protocol.taskList(page, pageSize);
            setPost(returnHttpApiInterfa().taskList(params), observer);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private static void get(final Call<Object> repos, Observer observer){
        requestGet(repos, observer);
    }
    public static void requestGet(final Call<Object> repos, Observer observer) {
        Observable.create(new ObservableOnSubscribe<String>() {//Rxjava被观察者
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                Log.e("call", repos.request().headers().toString() + "-" + repos.request().toString());
                repos.enqueue(new Callback<Object>() {//异步请求，并获取返回值
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        String value = null;
                        try {
                            value = response.body().toString();//.string();
                            emitter.onNext(value);
                            emitter.onComplete();//完成发送
                        } catch (Exception e) {
                            e.printStackTrace();
                            repos.cancel();
                        }
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        emitter.onError(t);
                        repos.cancel();
                    }
                });
            }
        })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在主线程中运行
                .subscribe(observer);//观察者与被观察者绑定

    }

    ///////////////////////////////////////////////

    private static void setPost(Call<ResponseBody> repos, Observer observer) {
        request(repos, observer);
    }

    public static void request(final Call<ResponseBody> repos, Observer observer) {
        Observable.create(new ObservableOnSubscribe<String>() {//Rxjava被观察者
            @Override
            public void subscribe(final ObservableEmitter<String> emitter) throws Exception {
                Log.e("call", repos.request().headers().toString() + "-" + repos.request().toString());
                repos.enqueue(new Callback<ResponseBody>() {//异步请求，并获取返回值
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String value = null;
                        try {
                            value = response.body().string();
                            emitter.onNext(value);
                            emitter.onComplete();//完成发送
                        } catch (IOException e) {
                            e.printStackTrace();
                            repos.cancel();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        emitter.onError(t);
                        repos.cancel();
                    }
                });
            }
        })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在主线程中运行
                .subscribe(observer);//观察者与被观察者绑定

    }

    public static String remove(String json) {
//        String o = json.getString( "result");
//        JSONObject object = JSONObject.parseObject(json);
//        String s = object.getString("RFM_12_var50" );
        return "";
    }

    //获取请求响应接口
    //retrofit请求使用
    public static HttpApiInterface returnHttpApiInterfa() {
        HttpApiInterface service = getRetrofit().create(HttpApiInterface.class);
        return service;
    }

    //返回OKhttpclient
    private static OkHttpClient returnOKhttpClient() {
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient client = httpBuilder
                .readTimeout(Protocol.CONNINCATIONTIMEOUT, TimeUnit.MINUTES)
                .connectTimeout(Protocol.CONNINCATIONTIMEOUT, TimeUnit.MINUTES)
                .writeTimeout(Protocol.CONNINCATIONTIMEOUT, TimeUnit.MINUTES) //设置超时
                .build();
        return client;
    }

    //创建retrofit请求实例
    public static Retrofit getRetrofit() {
        Retrofit myRetrofit = new Retrofit.Builder()
                .baseUrl(Protocol.BASE_URL)
//                .addConverterFactory( new Converter.Factory() {
//                    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
//
//                        return new Converter<ResponseBody, String>() {
//                            @Override
//                            public String convert(ResponseBody value) throws IOException {
//                                return value.string();
//                            }
//                        };
//                    }
//                } )
                .client(returnOKhttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return myRetrofit;
    }

    //文件下载
    public static void download(final Context context, final String url, final String fileName, Observer observer) {

        Observable.create(new ObservableOnSubscribe<Double>() {//Rxjava被观察者
            @Override
            public void subscribe(final ObservableEmitter<Double> emitter) throws Exception {
                Call<ResponseBody> repos = returnHttpApiInterfa().downloadFileWithDynamicUrlSync(url);
                repos.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.e("download", "server contacted and has file");

                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    boolean writtenToDisk = writeResponseBodyToDisk(context, emitter, fileName, response.body());
                                    if (writtenToDisk) {
                                        Log.e("download", "正在下载。。。");
                                        emitter.onComplete();
                                    } else {
                                        emitter.onComplete();
                                        Log.e("download", "下载失败。。。");
                                    }
                                }
                            }).start();


                        } else {

                            try {
                                Log.e("response", response.errorBody().string() + "--" + response.message().toString() + "--");
                                Log.e("download", "server contact failed");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("download", "error");
                        emitter.onError(t);
                    }
                });

            }

        })
//          .subscribeOn(Schedulers.io())
//           .subscribeOn(Schedulers.newThread())
//           .observeOn(AndroidSchedulers.mainThread())//在主线程中运行
                .subscribe(observer);//观察者与被观察者绑定
    }

    public static void downloadGET(final Context context, final String url, final String fileName, Observer observer) {
        Log.e("download-url", url);
        Observable.create(new ObservableOnSubscribe<Double>() {//Rxjava被观察者
            @Override
            public void subscribe(final ObservableEmitter<Double> emitter) throws Exception {
                Call<ResponseBody> repos = returnHttpApiInterfa().downloadFileWithDynamicUrlSync(url);
                repos.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.e("download", "server contacted and has file");

                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    boolean writtenToDisk = writeResponseBodyToDisk(context, emitter, fileName, response.body());
                                    if (writtenToDisk) {
                                        Log.e("download", "正在下载。。。");
                                        emitter.onComplete();
                                    } else {
                                        emitter.onComplete();
                                        Log.e("download", "下载失败。。。");
                                    }
                                }
                            }).start();


                        } else {
                            Log.e("response", response.headers().toString() + "--" + response.message().toString() + "--");
                            Log.e("download", "server contact failed");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("download", "error");
                        emitter.onError(t);
                    }
                });

            }

        })
//          .subscribeOn(Schedulers.io())
//           .subscribeOn(Schedulers.newThread())
//           .observeOn(AndroidSchedulers.mainThread())//在主线程中运行
                .subscribe(observer);//观察者与被观察者绑定
    }

    private static boolean writeResponseBodyToDisk(Context context, ObservableEmitter<Double> emitter, String fileName, ResponseBody body) {
        try {
            boolean isSdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); //判断sd卡是否存在
            // todo change the file location/name according to your needs
            File futureStudioIconFile = null;
            String fileType = "/filecache";
//            File Dir=new File()
            if (isSdCardExist) {
                Log.e("download", "有内存卡");
                File dirFirstFolder = new File(Protocol.DOWNLOADFILEPATH + fileType);
                if (!dirFirstFolder.exists()) { //如果该文件夹不存在，则进行创建
                    dirFirstFolder.mkdirs();//创建文件夹
                }
                futureStudioIconFile = new File(Protocol.DOWNLOADFILEPATH + fileType, fileName);
                if (!futureStudioIconFile.exists()) {
                    try {
                        futureStudioIconFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Log.e("download", "没有内存卡");
            }
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

//                    Log.e("file-download", "file download: " + fileSizeDownloaded + " of " + fileSize);
                    double i = ((fileSizeDownloaded * 1.0 / fileSize * 1.0) * 100.0);
                    emitter.onNext(i);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                Log.e("", e.getLocalizedMessage());
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }


    //TODO文件上传

    /**
     * upload
     */
//    protected Call newUploadRequest(final ConfigInfo configInfo) {
//        if (serviceUpload == null) {
//            initUpload();
//        }
//        configInfo.listener.registEventBus();
//        Map<String, RequestBody> requestBodyMap = new HashMap<>();
//        if (configInfo.files != null && configInfo.files.size() > 0) {
//            Map<String, String> files = configInfo.files;
//            int count = files.size();
//            if (count > 0) {
//                Set<Map.Entry<String, String>> set = files.entrySet();
//                for (Map.Entry<String, String> entry : set) {
//                    String key = entry.getKey();
//                    String value = entry.getValue();
//                    File file = new File(value);
//                    String type = Tool.getMimeType(file);//拿到文件的实际类型
//                    Log.e("type", "mimetype:" + type);
//                    UploadFileRequestBody fileRequestBody = new UploadFileRequestBody(file, type, configInfo.url);
//                    requestBodyMap.put(key + "\"; filename=\"" + file.getName(), fileRequestBody);
//                }
//            }
//        }
//        Call<ResponseBody> call = service.uploadWithProgress(configInfo.url, configInfo.params, requestBodyMap);
//        return call;
//    }



}

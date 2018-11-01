package com.wdq.micorestore.httpapi;

import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by sinosoft_wan on 2017/10/13.
 *
 * 新请求接口请根据login方式添加
 * @POST内填写请求方法名
 * 参数只有一个
 */

public interface HttpApiInterface {
    @GET("MobileServce/service/hello/post/1231231")
    Call<String>  hello(@Query("test") String test);

    @GET("login/{uname}/{password}")
    Call<Object> loginRESTfulk(@Path("uname") String uname, @Path("password")String password, @Header("info")String info);

    //POST
    @POST("login")//请求的方式、请求方法名
    Call<ResponseBody> login(@Query("jsonParams") String json);//请求参数

    //Url类型为  http://ip/taskList?paramskey1=value1&paramskey2=value2
    @POST("taskList")
    Call<ResponseBody> taskList(@Query("encoded = true") Map<String, String> xson);
    //Url类型为  http://ip/taskList?jsonParams=value1
    @POST("taskList")
    Call<ResponseBody> taskList(@Query("jsonParams") String xson);
    /**
     * 下载文件
     * @param fileUrl
     * @return
     */
    @Streaming
    @POST
    Call<ResponseBody> downloadFileWithDynamicUrlSync(@Url String fileUrl);
    @Streaming
    @GET
    Call<ResponseBody> downloadFileWithDynamicUrlSyncGET(@Url String fileUrl);


    @Multipart
    @POST()
    Call<ResponseBody> uploadFile(@Part("description") RequestBody description, @Part MultipartBody.Part file);

    @Multipart
    @POST()
    Call<ResponseBody> uploadFiles(@Url String url, @PartMap() Map<String, RequestBody> maps);

    @POST()
    @Multipart
    Call<ResponseBody> uploadWithProgress(@Url String url, @QueryMap Map<String, String> options, @PartMap Map<String, RequestBody> fileParameters) ;

}

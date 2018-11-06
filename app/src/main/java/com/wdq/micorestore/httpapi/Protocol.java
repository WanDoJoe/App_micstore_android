package com.wdq.micorestore.httpapi;

import android.os.Environment;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import static android.content.ContentValues.TAG;

/**
 * Created by sinosoft_wan on 2017/10/16.
 */

public class Protocol {

    public static String BASE_URL="http://10.160.6.104:8080";

    public static int CONNINCATIONTIMEOUT=1;

    public static String DOWNLOADFILEPATH= Environment.getExternalStorageDirectory().getPath()+"/sinosoft";

    public static String login(String username,String password) throws JSONException{
        return getRequest("taskList", new String[][] {{ "username", username },{ "password", password } });
    }
    public static String taskList(String pages,String pageSize) throws JSONException {
        return getRequest("taskList", new String[][] {{ "pages", pages },{ "pageSize", pageSize } });
    }



    public static String sysParam="";
    public static String biz="params";
    public static String getRequest(String method, String[][] arr)
            throws JSONException {
        JSONObject json = new JSONObject();
        json.put(sysParam, getSys(method));
        json.put(biz, getInfo(arr));
        Log.e("method:"+method+"--getRequest", json.toString());
        return json.toString();
    }

    public static String getRequest(String method, String info)
            throws JSONException {
        JSONObject json = new JSONObject();
        json.put(sysParam, getSys(method));
        json.put(biz, info);
        Log.d(TAG, json.toString());
        return json.toString();
    }

    public static String getRequest(String method, Map<String, Object> map)
            throws JSONException {
        JSONObject json = new JSONObject();
        json.put(sysParam, getSys(method));
        json.put(biz, JsonUtil.map2json(map));
        Log.d(TAG, json.toString());
        return json.toString();
    }

    private static JSONObject getSys(String method) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("appcode", "");
        json.put("compressdata", false);
        json.put("encryptdata", false);
        json.put("keycode", "");
        json.put("method", method);
        return json;
    }

    private static JSONObject getInfo(String[][] arr) throws JSONException {
        JSONObject json = new JSONObject();
        if (arr != null && arr.length > 0) {
            for (int i = arr.length - 1; i >= 0; i--) {
                json.put(arr[i][0], arr[i][1]);
            }
        }
        return json;
    }
}

package com.wdq.micorestore.bean;

/**
 * Created by sinosoft_wan on 2018/11/6.
 *  "name":"收银",
 "url":"",
 "img":"index_sale.png",
 "activity":"sale",
 "isAble":"true"
 */

public class LauncherItemBean {
    private String name;
    private String url;
    private String img;
    private String activity;
    private String isAble;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getIsAble() {
        return isAble;
    }

    public void setIsAble(String isAble) {
        this.isAble = isAble;
    }
}

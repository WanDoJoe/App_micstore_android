package com.wdq.micorestore.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sinosoft_wan on 2018/11/1.
 */

@Entity
public class SizeClorNumbBean {
    @Id(autoincrement = true)
    @NotNull
    private long id;
    @NotNull
    private int goodsId;
    private String color;
    private String size;
    private int numb;

    @Generated(hash = 1409604912)
    public SizeClorNumbBean(long id, int goodsId, String color, String size,
            int numb) {
        this.id = id;
        this.goodsId = goodsId;
        this.color = color;
        this.size = size;
        this.numb = numb;
    }

    @Generated(hash = 636292108)
    public SizeClorNumbBean() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }
}

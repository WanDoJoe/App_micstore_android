package com.wdq.micorestore.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sinosoft_wan on 2018/10/9.
 */
@Entity
public class GoodsBean {
    @Id(autoincrement = true)
    private long id;
    private String name;
    private Date creatDate;
//    private int sizecolornumbId;
    private float costPrice;
    private float sellingPrice;
    private String goodsCode;
    private String goodsType;
    private String saleNum;//折扣

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    @Generated(hash = 416075410)
    public GoodsBean(long id, String name, Date creatDate, float costPrice,
            float sellingPrice, String goodsCode, String goodsType) {
        this.id = id;
        this.name = name;
        this.creatDate = creatDate;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
        this.goodsCode = goodsCode;
        this.goodsType = goodsType;
    }

    @Generated(hash = 1806305570)
    public GoodsBean() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(Date creatDate) {
        this.creatDate = creatDate;
    }

    public float getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(float costPrice) {
        this.costPrice = costPrice;
    }

    public float getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}

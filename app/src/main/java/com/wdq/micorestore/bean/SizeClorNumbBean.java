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


    private String type1_name;
    private String type1_value;
    private String type2_name;
    private String type2_value;
    private String type3_name;
    private String type3_value;

    private String goodsCode;

    private String numb_name;
    private String numb_value;

    public String getNumb_name() {
        return numb_name;
    }

    public void setNumb_name(String numb_name) {
        this.numb_name = numb_name;
    }

    public String getNumb_value() {
        return numb_value;
    }

    public void setNumb_value(String numb_value) {
        this.numb_value = numb_value;
    }

//    @Generated(hash = 841922164)
//    public SizeClorNumbBean(long id, String type1_name, String type1_value,
//            String type2_name, String type2_value, String type3_name,
//            String type3_value, String goodsCode) {
//        this.id = id;
//        this.type1_name = type1_name;
//        this.type1_value = type1_value;
//        this.type2_name = type2_name;
//        this.type2_value = type2_value;
//        this.type3_name = type3_name;
//        this.type3_value = type3_value;
//        this.goodsCode = goodsCode;
//
//    }

    @Generated(hash = 636292108)
    public SizeClorNumbBean() {
    }

    @Generated(hash = 1856809102)
    public SizeClorNumbBean(long id, String type1_name, String type1_value,
            String type2_name, String type2_value, String type3_name,
            String type3_value, String goodsCode, String numb_name,
            String numb_value) {
        this.id = id;
        this.type1_name = type1_name;
        this.type1_value = type1_value;
        this.type2_name = type2_name;
        this.type2_value = type2_value;
        this.type3_name = type3_name;
        this.type3_value = type3_value;
        this.goodsCode = goodsCode;
        this.numb_name = numb_name;
        this.numb_value = numb_value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType1_name() {
        return type1_name;
    }

    public void setType1_name(String type1_name) {
        this.type1_name = type1_name;
    }

    public String getType1_value() {
        return type1_value;
    }

    public void setType1_value(String type1_value) {
        this.type1_value = type1_value;
    }

    public String getType2_name() {
        return type2_name;
    }

    public void setType2_name(String type2_name) {
        this.type2_name = type2_name;
    }

    public String getType2_value() {
        return type2_value;
    }

    public void setType2_value(String type2_value) {
        this.type2_value = type2_value;
    }

    public String getType3_name() {
        return type3_name;
    }

    public void setType3_name(String type3_name) {
        this.type3_name = type3_name;
    }

    public String getType3_value() {
        return type3_value;
    }

    public void setType3_value(String type3_value) {
        this.type3_value = type3_value;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }
}

package com.wdq.micorestore.grocery.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sinosoft_wan on 2019-6-12.
 */

@Entity
public class GroceryGoodsBrandBean {
    @Id
    Long id;
    String beandName;
    @Generated(hash = 1769290094)
    public GroceryGoodsBrandBean(Long id, String beandName) {
        this.id = id;
        this.beandName = beandName;
    }
    @Generated(hash = 2123016635)
    public GroceryGoodsBrandBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBeandName() {
        return this.beandName;
    }
    public void setBeandName(String beandName) {
        this.beandName = beandName;
    }
}

package com.wdq.micorestore.grocery.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sinosoft_wan on 2019-6-11.
 */

@Entity
public class GroceryGoodsSizeBean {
    @Id(autoincrement = true)
    Long id;
    String sizeName;
    @Generated(hash = 1014607745)
    public GroceryGoodsSizeBean(Long id, String sizeName) {
        this.id = id;
        this.sizeName = sizeName;
    }
    @Generated(hash = 1746521049)
    public GroceryGoodsSizeBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSizeName() {
        return this.sizeName;
    }
    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

}

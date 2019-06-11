package com.wdq.micorestore.grocery.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sinosoft_wan on 2019-6-11.
 */
@Entity
public class GroceryGoodsHistoryTypeBean {
    @Id(autoincrement = true)
    Long id;
    String typeName;
    @Generated(hash = 2076500887)
    public GroceryGoodsHistoryTypeBean(Long id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }
    @Generated(hash = 501274034)
    public GroceryGoodsHistoryTypeBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTypeName() {
        return this.typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

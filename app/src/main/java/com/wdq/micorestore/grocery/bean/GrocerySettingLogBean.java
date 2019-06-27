package com.wdq.micorestore.grocery.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sinosoft_wan on 2019-6-24.
 */
@Entity
public class GrocerySettingLogBean {
    @Id
    Long id;
    String name;
    String context;
    String date;
    String details;
    String Tag;
    @Generated(hash = 633407968)
    public GrocerySettingLogBean(Long id, String name, String context, String date,
            String details, String Tag) {
        this.id = id;
        this.name = name;
        this.context = context;
        this.date = date;
        this.details = details;
        this.Tag = Tag;
    }
    @Generated(hash = 2003277541)
    public GrocerySettingLogBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContext() {
        return this.context;
    }
    public void setContext(String context) {
        this.context = context;
    }
    public String getDate() {
        return this.date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDetails() {
        return this.details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public String getTag() {
        return this.Tag;
    }
    public void setTag(String Tag) {
        this.Tag = Tag;
    }

}


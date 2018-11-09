package com.wdq.micorestore.order.bean;

import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sinosoft_wan on 2018/11/9.
 */

@Entity
public class OrderSuperTableBean {
    @Id(autoincrement = true)
    private Long id;
    private String name;

    @Generated(hash = 1906061455)
    public OrderSuperTableBean(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 2135132189)
    public OrderSuperTableBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

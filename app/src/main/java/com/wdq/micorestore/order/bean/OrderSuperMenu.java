package com.wdq.micorestore.order.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sinosoft_wan on 2018/11/9.
 * 点餐系统-父菜单表
 *
 */

@Entity
public class OrderSuperMenu {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String pinyingId;//缩写id  比如 锅底：gd或者GD
    private String introduction;
    @Generated(hash = 381972970)
    public OrderSuperMenu(Long id, String name, String pinyingId,
            String introduction) {
        this.id = id;
        this.name = name;
        this.pinyingId = pinyingId;
        this.introduction = introduction;
    }
    @Generated(hash = 306840094)
    public OrderSuperMenu() {
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
    public String getPinyingId() {
        return this.pinyingId;
    }
    public void setPinyingId(String pinyingId) {
        this.pinyingId = pinyingId;
    }
    public String getIntroduction() {
        return this.introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }


}

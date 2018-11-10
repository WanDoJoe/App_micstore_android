package com.wdq.micorestore.order.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sinosoft_wan on 2018/11/9.
 *
 * 点餐系统-父菜单表
 *
 */
@Entity
public class OrderSubMenu {
    @Id(autoincrement = true)
    private Long id;
    private Long superMenuId;
    private String pinyingId;//缩写id  比如 锅底：gd或者GD
    private String name;
    private String introduction;
    private float price;
    private float sale;//折扣
    private String isAble;
    private String createYear;
    @Generated(hash = 1771293636)
    public OrderSubMenu(Long id, Long superMenuId, String pinyingId, String name,
            String introduction, float price, float sale, String isAble,
            String createYear) {
        this.id = id;
        this.superMenuId = superMenuId;
        this.pinyingId = pinyingId;
        this.name = name;
        this.introduction = introduction;
        this.price = price;
        this.sale = sale;
        this.isAble = isAble;
        this.createYear = createYear;
    }
    @Generated(hash = 841012027)
    public OrderSubMenu() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getSuperMenuId() {
        return this.superMenuId;
    }
    public void setSuperMenuId(Long superMenuId) {
        this.superMenuId = superMenuId;
    }
    public String getPinyingId() {
        return this.pinyingId;
    }
    public void setPinyingId(String pinyingId) {
        this.pinyingId = pinyingId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIntroduction() {
        return this.introduction;
    }
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public float getPrice() {
        return this.price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public float getSale() {
        return this.sale;
    }
    public void setSale(float sale) {
        this.sale = sale;
    }
    public String getIsAble() {
        return this.isAble;
    }
    public void setIsAble(String isAble) {
        this.isAble = isAble;
    }
    public String getCreateYear() {
        return this.createYear;
    }
    public void setCreateYear(String createYear) {
        this.createYear = createYear;
    }
   
}

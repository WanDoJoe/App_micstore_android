package com.wdq.micorestore.order.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sinosoft_wan on 2018/11/9.
 */
@Entity
public class OrderSubTableBean {
    @Id(autoincrement = true)
    private Long id;
    private Long superTableID;
    private String name;

    @Generated(hash = 2087152630)
    public OrderSubTableBean(Long id, Long superTableID, String name) {
        this.id = id;
        this.superTableID = superTableID;
        this.name = name;
    }

    @Generated(hash = 691816289)
    public OrderSubTableBean() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSuperTableID() {
        return superTableID;
    }

    public void setSuperTableID(Long superTableID) {
        this.superTableID = superTableID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

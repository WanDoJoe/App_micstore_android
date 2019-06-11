package com.wdq.micorestore.order.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sinosoft_wan on 2018/11/9.
 * 点餐系统-用户表
 *
 */


@Entity
public class OrderUserBean {
    @Id
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String date;
    private String headerImg;
    private String Role;
    private String name;


    @Generated(hash = 740733164)
    public OrderUserBean(Long id, @NotNull String username,
            @NotNull String password, String date, String headerImg, String Role,
            String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.date = date;
        this.headerImg = headerImg;
        this.Role = Role;
        this.name = name;
    }

    @Generated(hash = 1839367459)
    public OrderUserBean() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

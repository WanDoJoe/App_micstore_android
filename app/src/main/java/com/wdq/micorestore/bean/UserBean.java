package com.wdq.micorestore.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sinosoft_wan on 2018/10/9.
 *
 * @Entity：将我们的java普通类变为一个能够被greenDAO识别的数据库类型的实体类;
 @nameInDb：在数据库中的名字，如不写则为实体中类名；
 @Id：选择一个long / Long属性作为实体ID。 在数据库方面，它是主键。 参数autoincrement是设置ID值自增；
 @NotNull：使该属性在数据库端成为“NOT NULL”列。 通常使用@NotNull标记原始类型（long，int，short，byte）是有意义的；
 @Transient：表明这个字段不会被写入数据库，只是作为一个普通的java类字段，用来临时存储数据的，不会被持久化。
 */

@Entity
public class UserBean {
    @Id(autoincrement = true)
    private long id;
    private String username;
    private String password;

    @Generated(hash = 264317175)
    public UserBean(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    @Generated(hash = 1203313951)
    public UserBean() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}

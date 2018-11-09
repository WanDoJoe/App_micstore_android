package com.wdq.micorestore.order.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sinosoft_wan on 2018/11/9.
 *
 * 点餐系统-账单表
 *
 *
 */
@Entity
public class OredrReckoningBean {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String serviceId;
    private String servicerName;
    private String createDate;
    private String edtDate;
    private String context;//账单内容，使用base64 方式压缩json字符串
    private float receivable;//应收
    private float makeCollections;//实收
    private float oddChange;//找零
    private String isCancel;

    @Generated(hash = 1997581360)
    public OredrReckoningBean(Long id, String name, String serviceId,
            String servicerName, String createDate, String edtDate, String context,
            float receivable, float makeCollections, float oddChange,
            String isCancel) {
        this.id = id;
        this.name = name;
        this.serviceId = serviceId;
        this.servicerName = servicerName;
        this.createDate = createDate;
        this.edtDate = edtDate;
        this.context = context;
        this.receivable = receivable;
        this.makeCollections = makeCollections;
        this.oddChange = oddChange;
        this.isCancel = isCancel;
    }

    @Generated(hash = 1215315359)
    public OredrReckoningBean() {
    }

    public String getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServicerName() {
        return servicerName;
    }

    public void setServicerName(String servicerName) {
        this.servicerName = servicerName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEdtDate() {
        return edtDate;
    }

    public void setEdtDate(String edtDate) {
        this.edtDate = edtDate;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public float getReceivable() {
        return receivable;
    }

    public void setReceivable(float receivable) {
        this.receivable = receivable;
    }

    public float getMakeCollections() {
        return makeCollections;
    }

    public void setMakeCollections(float makeCollections) {
        this.makeCollections = makeCollections;
    }

    public float getOddChange() {
        return oddChange;
    }

    public void setOddChange(float oddChange) {
        this.oddChange = oddChange;
    }
}

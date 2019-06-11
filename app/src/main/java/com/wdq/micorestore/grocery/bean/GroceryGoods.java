package com.wdq.micorestore.grocery.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by sinosoft_wan on 2019-6-11.
 */
@Entity
public class GroceryGoods {
    @Id(autoincrement = true)
    Long id;
    @NotNull
    String grocery_cqcode;//">二维码号</string>
    String grocery_code;//">商品代码</string>
    @NotNull
    String grocery_goods_name;//">商品名</string>
    String grocery_goods_brand;//">商品品牌</string>
    String grocery_goods_type;//">商品类型</string>
    String grocery_goods_size;//">规格</string>
    String grocery_goods_date;//">生产日期</string>
    String grocery_in_date;//">入库日期</string>
    @NotNull
    String grocery_in_numb;//">入库数量</string>
    String grocery_up_sale;//">上架</string>
    String grocery_down_sale;//">下架</string>
    String grocery_remark;//">备注</string>
    @Generated(hash = 832831513)
    public GroceryGoods(Long id, @NotNull String grocery_cqcode,
            String grocery_code, @NotNull String grocery_goods_name,
            String grocery_goods_brand, String grocery_goods_type,
            String grocery_goods_size, String grocery_goods_date,
            String grocery_in_date, @NotNull String grocery_in_numb,
            String grocery_up_sale, String grocery_down_sale,
            String grocery_remark) {
        this.id = id;
        this.grocery_cqcode = grocery_cqcode;
        this.grocery_code = grocery_code;
        this.grocery_goods_name = grocery_goods_name;
        this.grocery_goods_brand = grocery_goods_brand;
        this.grocery_goods_type = grocery_goods_type;
        this.grocery_goods_size = grocery_goods_size;
        this.grocery_goods_date = grocery_goods_date;
        this.grocery_in_date = grocery_in_date;
        this.grocery_in_numb = grocery_in_numb;
        this.grocery_up_sale = grocery_up_sale;
        this.grocery_down_sale = grocery_down_sale;
        this.grocery_remark = grocery_remark;
    }
    @Generated(hash = 1422402415)
    public GroceryGoods() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getGrocery_cqcode() {
        return this.grocery_cqcode;
    }
    public void setGrocery_cqcode(String grocery_cqcode) {
        this.grocery_cqcode = grocery_cqcode;
    }
    public String getGrocery_code() {
        return this.grocery_code;
    }
    public void setGrocery_code(String grocery_code) {
        this.grocery_code = grocery_code;
    }
    public String getGrocery_goods_name() {
        return this.grocery_goods_name;
    }
    public void setGrocery_goods_name(String grocery_goods_name) {
        this.grocery_goods_name = grocery_goods_name;
    }
    public String getGrocery_goods_brand() {
        return this.grocery_goods_brand;
    }
    public void setGrocery_goods_brand(String grocery_goods_brand) {
        this.grocery_goods_brand = grocery_goods_brand;
    }
    public String getGrocery_goods_type() {
        return this.grocery_goods_type;
    }
    public void setGrocery_goods_type(String grocery_goods_type) {
        this.grocery_goods_type = grocery_goods_type;
    }
    public String getGrocery_goods_size() {
        return this.grocery_goods_size;
    }
    public void setGrocery_goods_size(String grocery_goods_size) {
        this.grocery_goods_size = grocery_goods_size;
    }
    public String getGrocery_goods_date() {
        return this.grocery_goods_date;
    }
    public void setGrocery_goods_date(String grocery_goods_date) {
        this.grocery_goods_date = grocery_goods_date;
    }
    public String getGrocery_in_date() {
        return this.grocery_in_date;
    }
    public void setGrocery_in_date(String grocery_in_date) {
        this.grocery_in_date = grocery_in_date;
    }
    public String getGrocery_in_numb() {
        return this.grocery_in_numb;
    }
    public void setGrocery_in_numb(String grocery_in_numb) {
        this.grocery_in_numb = grocery_in_numb;
    }
    public String getGrocery_up_sale() {
        return this.grocery_up_sale;
    }
    public void setGrocery_up_sale(String grocery_up_sale) {
        this.grocery_up_sale = grocery_up_sale;
    }
    public String getGrocery_down_sale() {
        return this.grocery_down_sale;
    }
    public void setGrocery_down_sale(String grocery_down_sale) {
        this.grocery_down_sale = grocery_down_sale;
    }
    public String getGrocery_remark() {
        return this.grocery_remark;
    }
    public void setGrocery_remark(String grocery_remark) {
        this.grocery_remark = grocery_remark;
    }
}

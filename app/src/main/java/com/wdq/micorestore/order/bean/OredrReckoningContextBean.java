package com.wdq.micorestore.order.bean;

/**
 * Created by sinosoft_wan on 2018/12/4.
 */

public class OredrReckoningContextBean {

    private int id;
    private String name;
    private float price;
    private int number;
//    private float totol;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

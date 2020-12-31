package com.SE.bean;

import java.sql.Date;

public class orderinf {
    private int order_id;
    private String wuliu_id;
    private int user_id;
    private String address;
    private String order_state;
    private String item_img;
    private float item_bprice;
    private String add_name;
    private String add_phone;

    public String getAdd_phone() {
        return add_phone;
    }

    public void setAdd_phone(String add_phone) {
        this.add_phone = add_phone;
    }

    public String getSeller_pay() {
        return seller_pay;
    }

    public void setSeller_pay(String seller_pay) {
        this.seller_pay = seller_pay;
    }

    private String seller_pay;

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    private String item_name;

    public String getAdd_name() {
        return add_name;
    }

    public void setAdd_name(String add_name) {
        this.add_name = add_name;
    }

    public String getItem_img() {
        return item_img;
    }

    public void setItem_img(String item_img) {
        this.item_img = item_img;
    }

    public float getItem_bprice() {
        return item_bprice;
    }

    public void setItem_bprice(float item_bprice) {
        this.item_bprice = item_bprice;
    }

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getConfirm_id() {
        return confirm_id;
    }

    public void setConfirm_id(int confirm_id) {
        this.confirm_id = confirm_id;
    }

    private int confirm_id;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getWuliu_id() {
        return wuliu_id;
    }

    public void setWuliu_id(String wuliu_id) {
        this.wuliu_id = wuliu_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    private int item_id;
    private int order_num;
    private Date order_date;
}

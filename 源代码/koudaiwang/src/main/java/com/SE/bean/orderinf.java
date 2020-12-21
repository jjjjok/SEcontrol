package com.SE.bean;

import java.sql.Date;

public class orderinf {
    private int order_id;
    private String wuliu_id;
    private int user_id;
    private int add_id;
    private String order_state;

    public String getOrder_state() {
        return order_state;
    }

    public void setOrder_state(String order_state) {
        this.order_state = order_state;
    }

    public int getAdd_id() {
        return add_id;
    }

    public void setAdd_id(int add_id) {
        this.add_id = add_id;
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

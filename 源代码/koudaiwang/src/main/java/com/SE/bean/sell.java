package com.SE.bean;

import java.sql.Date;

public class sell {
    private int sell_id;
    private int item_id;
    private int user_id;
    private Date sell_date;
    private int sell_state;
    private String item_name;
    private String item_inf;

    public String getItem_inf() {
        return item_inf;
    }

    public void setItem_inf(String item_inf) {
        this.item_inf = item_inf;
    }

    public int getSell_state() {
        return sell_state;
    }

    public void setSell_state(int sell_state) {
        this.sell_state = sell_state;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_img() {
        return item_img;
    }

    public void setItem_img(String item_img) {
        this.item_img = item_img;
    }

    public float getItem_aprice() {
        return item_aprice;
    }

    public void setItem_aprice(float item_aprice) {
        this.item_aprice = item_aprice;
    }

    public float getItem_bprice() {
        return item_bprice;
    }

    public void setItem_bprice(float item_bprice) {
        this.item_bprice = item_bprice;
    }

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    private String item_img;
    private float item_aprice;
    private float item_bprice;
    private int item_num;


    public int getSell_id() {
        return sell_id;
    }

    public void setSell_id(int sell_id) {
        this.sell_id = sell_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getSell_date() {
        return sell_date;
    }

    public void setSell_date(Date sell_date) {
        this.sell_date = sell_date;
    }
}

package com.SE.bean;

public class collectitem {
    private int item_id;
    private String item_name;
    private String item_inf;
    private String item_img;
    private int user_id;
    private int item_num;

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
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

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_inf() {
        return item_inf;
    }

    public void setItem_inf(String item_inf) {
        this.item_inf = item_inf;
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

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    private float item_aprice;
    private float item_bprice;
    private int collect_count;

}

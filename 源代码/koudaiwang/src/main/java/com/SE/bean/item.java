package com.SE.bean;

public class item {
    private int item_id;
    private String item_name;
    private  int item_check;

    public int getItem_check() {
        return item_check;
    }

    public void setItem_check(int item_check) {
        this.item_check = item_check;
    }

    private String item_inf;
    private String item_img;


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

    public void setItem_aprice(float item_price) {
        this.item_aprice = item_price;
    }

    public int getItem_num() {
        return item_num;
    }

    public void setItem_num(int item_num) {
        this.item_num = item_num;
    }

    private float item_aprice;
    private float item_bprice;

    public float getItem_bprice() {
        return item_bprice;
    }

    public void setItem_bprice(float item_bprice) {
        this.item_bprice = item_bprice;
    }

    private int item_num;
}

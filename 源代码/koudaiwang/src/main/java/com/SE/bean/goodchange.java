package com.SE.bean;

public class goodchange {
    private int id;
    private int order_id;
    private String change_rea;
    private String change_inf;
    private String change_img;
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getChange_rea() {
        return change_rea;
    }

    public void setChange_rea(String change_rea) {
        this.change_rea = change_rea;
    }

    public String getChange_inf() {
        return change_inf;
    }

    public void setChange_inf(String change_inf) {
        this.change_inf = change_inf;
    }

    public String getChange_img() {
        return change_img;
    }

    public void setChange_img(String change_img) {
        this.change_img = change_img;
    }
}

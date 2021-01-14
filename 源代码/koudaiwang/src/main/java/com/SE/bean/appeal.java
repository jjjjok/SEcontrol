package com.SE.bean;

public class appeal {
    private int appeal_id;
    private int order_id;
    private String appeal_img;
    private String appeal_inf;
    private String appeal_bool;
    private String appeal_rea;
    private int user_id;
    private String appeal_result;

    public String getAppeal_result() {
        return appeal_result;
    }

    public void setAppeal_result(String appeal_result) {
        this.appeal_result = appeal_result;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAppeal_rea() {
        return appeal_rea;
    }

    public void setAppeal_rea(String appeal_rea) {
        this.appeal_rea = appeal_rea;
    }

    public int getAppeal_id() {
        return appeal_id;
    }

    public void setAppeal_id(int appeal_id) {
        this.appeal_id = appeal_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getAppeal_img() {
        return appeal_img;
    }

    public void setAppeal_img(String appeal_img) {
        this.appeal_img = appeal_img;
    }

    public String getAppeal_inf() {
        return appeal_inf;
    }

    public void setAppeal_inf(String appeal_inf) {
        this.appeal_inf = appeal_inf;
    }

    public String getAppeal_bool() {
        return appeal_bool;
    }

    public void setAppeal_bool(String appeal_bool) {
        this.appeal_bool = appeal_bool;
    }
}

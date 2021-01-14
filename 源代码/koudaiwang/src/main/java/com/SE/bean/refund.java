package com.SE.bean;

public class refund {
    private int refund_id;
    private int order_id;
    private int user_id;
    private String refund_rea;
    private String refund_inf;
    private String refund_img;
    private String refund_bool;

    public int getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(int refund_id) {
        this.refund_id = refund_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getRefund_rea() {
        return refund_rea;
    }

    public void setRefund_rea(String refund_rea) {
        this.refund_rea = refund_rea;
    }

    public String getRefund_inf() {
        return refund_inf;
    }

    public void setRefund_inf(String refund_inf) {
        this.refund_inf = refund_inf;
    }

    public String getRefund_img() {
        return refund_img;
    }

    public void setRefund_img(String refund_img) {
        this.refund_img = refund_img;
    }

    public String getRefund_bool() {
        return refund_bool;
    }

    public void setRefund_bool(String refund_bool) {
        this.refund_bool = refund_bool;
    }
}

package com.SE.bean;

import java.sql.Date;

public class sell {
    private int sell_id;
    private int item_id;
    private int user_id;
    private Date sell_date;

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

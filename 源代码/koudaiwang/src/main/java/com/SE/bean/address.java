package com.SE.bean;

public class address {
    private int user_id;
    private String address;
    private int id;
    private String user_name;
    private String user_phone;
    private int add_state;

    public int getAdd_state() {
        return add_state;
    }

    public void setAdd_state(int add_state) {
        this.add_state = add_state;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

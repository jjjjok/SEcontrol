package com.SE.dao;

import com.SE.bean.collect;
import com.SE.bean.item;
import com.SE.bean.userinf;
import com.SE.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDao {
    public static ArrayList<item> selectAllItem(){
        ArrayList<item> list = new ArrayList<item>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;


        try {
            String sql="select * from item";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;

    }

    public static int itemInsert(item i){
        String sql="insert into item(item_name,item_aprice,item_bprice,item_inf,item_num,item_img) values(?,?,?,?,?,?)";
        Object[] params ={
                i.getItem_name(),i.getItem_aprice(),i.getItem_bprice(),i.getItem_inf(),i.getItem_num(),i.getItem_img()
        };
        return DBUtil.exectuIUD(sql,params);
    }

    public static int selectIdByName(String name){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select item_id from item where item_name=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);
            rs=ps.executeQuery();
            while(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }
        return count;
    }
//后台显示全部物品并分页
    public static ArrayList<item> selectAllItem(int cpage,int count){
        ArrayList<item> list = new ArrayList<item>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select item_id,item_name,item_aprice,item_bprice,item_num,item_img,item_inf from item limit ?,?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,(cpage-1)*count);
            ps.setInt(2,count);
            rs=ps.executeQuery();
            while(rs.next()){
                item i=new item();
                i.setItem_id(rs.getInt(1));
                i.setItem_name(rs.getString(2));
                i.setItem_aprice(rs.getFloat(3));
                i.setItem_bprice(rs.getFloat(4));
                i.setItem_num(rs.getInt(5));
                i.setItem_img(rs.getString(6));
                i.setItem_inf(rs.getString(7));
                list.add(i);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;
    }

    //前台根据分类id显示物品
    public static ArrayList<item> selectItemByFlId(int id){
        ArrayList<item> list = new ArrayList<item>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select item_name,item_aprice,item_bprice,item_img,item_id from item where item_id in(select item_id from link where fl_id=?)";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                item i=new item();
                i.setItem_name(rs.getString(1));
                i.setItem_aprice(rs.getFloat(2));
                i.setItem_bprice(rs.getFloat(3));
                i.setItem_img(rs.getString(4));
                i.setItem_id(rs.getInt(5));
                list.add(i);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;
    }
//根据id查找物品
    public static item selectItemById(int id){
        item i=new item();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select item_id,item_name,item_aprice,item_bprice,item_num,item_img,item_inf from item where item_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                i.setItem_id(rs.getInt(1));
                i.setItem_name(rs.getString(2));
                i.setItem_aprice(rs.getFloat(3));
                i.setItem_bprice(rs.getFloat(4));
                i.setItem_num(rs.getInt(5));
                i.setItem_img(rs.getString(6));
                i.setItem_inf(rs.getString(7));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return i;
    }

    //首页显示全部物品并分页
    public static ArrayList<item> selectPreAllItem(int cpage,int count){
        ArrayList<item> list = new ArrayList<item>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select item_id,item_name,item_aprice,item_bprice,item_img from item limit ?,?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,(cpage-1)*count);
            ps.setInt(2,count);
            rs=ps.executeQuery();
            while(rs.next()){
                item i=new item();
                i.setItem_id(rs.getInt(1));
                i.setItem_name(rs.getString(2));
                i.setItem_aprice(rs.getFloat(3));
                i.setItem_bprice(rs.getFloat(4));
                i.setItem_img(rs.getString(5));
                list.add(i);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;
    }



}

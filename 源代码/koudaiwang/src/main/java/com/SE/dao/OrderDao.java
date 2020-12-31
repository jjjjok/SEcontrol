package com.SE.dao;

import com.SE.bean.item;
import com.SE.bean.orderinf;
import com.SE.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDao {
    //返回订单表中最大的confirm_id
    public static int selectMaxCId(){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select max(confirm_id) from orderinf ";
            ps=conn.prepareStatement(sql);
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

    //返回订单表中某用户最大的confirm_id
    public static int selectMaxCIdByUid(int uid){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select max(confirm_id) from orderinf where user_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,uid);
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
    //根据orderid找itemid
    public static int selectIIdByOid(int oid){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select item_id from orderinf where order_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,oid);
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



    //增加订单
    public static int orderAdd(int confirmid,int uid,int itemid,int itemnum,String item_img,float item_bprice,String item_name,String seller_pay){
        String sql="insert into orderinf(confirm_id,user_id,item_id,order_num,order_date,item_img,item_bprice,item_name,seller_pay) values(?,?,?,?,now(),?,?,?,?)";
        Object[] params ={
                confirmid,uid,itemid,itemnum,item_img,item_bprice,item_name,seller_pay
        };
        return DBUtil.exectuIUD(sql,params);
    }
    //用户支付成功后，更新订单信息,1为已支付，未发货
    public static int updateOrder(int confirm_id){
        String sql="update orderinf set order_state=1 where confirm_id=?";
        Object[] params ={
                confirm_id
        };
        return DBUtil.exectuIUD(sql,params);
    }
    //返回订单表中最大的order_id
    public static int selectMaxOId(int confirm_id){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select max(order_id) from orderinf where confirm_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,confirm_id);
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
    //返回订单表中最小的order_id
    public static int selectMinOId(int confirm_id){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select min(order_id) from orderinf where confirm_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,confirm_id);
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
    //返回订单表中某用户最大的confirm_id
    public static int selectMaxOIdByUid(int userid){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select max(confirm_id) from orderinf where user_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,userid);
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

    //确认地址信息
    public static int addressConfirm(int uid,int addid){
        String sql="update address set add_state=0 where user_id=?";
        Object[] params ={
                uid
        };
        DBUtil.exectuIUD(sql,params);
        sql="update address set add_state=1 where user_id=? and id=?";
        Object[] params1 ={
                uid,addid
        };
        return DBUtil.exectuIUD(sql,params1);

    }
    //订单更新确认地址信息
    public static int addressOrderAdd(int oid,String addid,String addname,String addphone){
        String sql="update orderinf set address=?,add_name=?,add_phone=?,order_state=1 where order_id=?";
        Object[] params ={
                addid,addname,addphone,oid
        };
        return DBUtil.exectuIUD(sql,params);
    }
//订单信息
    public static ArrayList<orderinf> selectOrderInf(int userid){
        ArrayList<orderinf> list = new ArrayList<orderinf>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            String sql="select confirm_id,order_date,order_state,item_id,item_img,item_bprice,address,add_name,item_name,order_num,order_id,add_phone from orderinf where user_id=?";
            ps=conn.prepareStatement(sql);

            ps.setInt(1,userid);
            rs=ps.executeQuery();
            while(rs.next()){
                orderinf o=new orderinf();
                o.setConfirm_id(rs.getInt(1));
                o.setOrder_date(rs.getDate(2));
                o.setOrder_state(rs.getString(3));
                o.setItem_id(rs.getInt(4));
                o.setItem_img(rs.getString(5));
                o.setItem_bprice(rs.getFloat(6));
                o.setAddress(rs.getString(7));
                o.setAdd_name(rs.getString(8));
                o.setItem_name(rs.getString(9));
                o.setOrder_num(rs.getInt(10));
                o.setOrder_id(rs.getInt(11));
                o.setAdd_phone(rs.getString(12));
                list.add(o);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;

    }

    //查找商家等待发货的订单信息
    public static ArrayList<orderinf> selectOrderInfToSend(int userid){
        ArrayList<orderinf> list = new ArrayList<orderinf>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            String sql="select confirm_id,order_date,order_state,item_id,item_img,item_bprice,address,add_name,item_name,order_num,order_id from orderinf where item_id in(select item_id from sell where user_id=?) and order_state=1";
            ps=conn.prepareStatement(sql);

            ps.setInt(1,userid);
            rs=ps.executeQuery();
            while(rs.next()){
                orderinf o=new orderinf();
                o.setConfirm_id(rs.getInt(1));
                o.setOrder_date(rs.getDate(2));
                o.setOrder_state(rs.getString(3));
                o.setItem_id(rs.getInt(4));
                o.setItem_img(rs.getString(5));
                o.setItem_bprice(rs.getFloat(6));
                o.setAddress(rs.getString(7));
                o.setAdd_name(rs.getString(8));
                o.setItem_name(rs.getString(9));
                o.setOrder_num(rs.getInt(10));
                o.setOrder_id(rs.getInt(11));
                list.add(o);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;

    }

    //查找商家已发货，用户未确认的订单
    public static ArrayList<orderinf> selectOrderInfSended(int userid){
        ArrayList<orderinf> list = new ArrayList<orderinf>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            String sql="select confirm_id,order_date,order_state,item_id,item_img,item_bprice,address,add_name,item_name,order_num,order_id from orderinf where item_id in(select item_id from sell where user_id=?) and order_state=2";
            ps=conn.prepareStatement(sql);

            ps.setInt(1,userid);
            rs=ps.executeQuery();
            while(rs.next()){
                orderinf o=new orderinf();
                o.setConfirm_id(rs.getInt(1));
                o.setOrder_date(rs.getDate(2));
                o.setOrder_state(rs.getString(3));
                o.setItem_id(rs.getInt(4));
                o.setItem_img(rs.getString(5));
                o.setItem_bprice(rs.getFloat(6));
                o.setAddress(rs.getString(7));
                o.setAdd_name(rs.getString(8));
                o.setItem_name(rs.getString(9));
                o.setOrder_num(rs.getInt(10));
                o.setOrder_id(rs.getInt(11));
                list.add(o);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;

    }




    //确认收货
    public static int itemConfirm(int orderid){
        String sql="update orderinf set order_state=3 where order_id=?";
        Object[] params ={
                orderid
        };
        return DBUtil.exectuIUD(sql,params);
    }

    //查找用户有没有进行收货的地址
    public static int selectAddressStateByUid(int userid){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select count(id) from address where user_id=? and add_state=1";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,userid);
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


}

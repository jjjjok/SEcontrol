package com.SE.dao;

import com.SE.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    //增加订单
    public static int orderAdd(int confirmid,int uid,int itemid,int itemnum){
        String sql="insert into orderinf(confirm_id,user_id,item_id,order_num,order_date) values(?,?,?,?,now())";
        Object[] params ={
                confirmid,uid,itemid,itemnum
        };
        return DBUtil.exectuIUD(sql,params);
    }
}

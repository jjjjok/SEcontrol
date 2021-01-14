package com.SE.dao;

import com.SE.bean.goodchange;
import com.SE.bean.refund;
import com.SE.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GoodChangeDao {
    //添加换货信息
    public static int goodChangeInsert(goodchange i){
        String sql="insert into goodchange(order_id,user_id,change_rea,change_inf,change_img) values(?,?,?,?,?)";
        Object[] params ={
                i.getOrder_id(),i.getUser_id(),i.getChange_rea(),i.getChange_inf(),i.getChange_img()
        };
        return DBUtil.exectuIUD(sql,params);
    }
    //显示换货详情
    public static goodchange getChangeDetailById(int id){
        goodchange i=new goodchange();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select * from goodchange where order_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                i.setOrder_id(rs.getInt(2));
                i.setChange_rea(rs.getString(3));
                i.setChange_inf(rs.getString(4));
                i.setChange_img(rs.getString(5));
                i.setUser_id(rs.getInt(6));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return i;
    }
    //换货处理
    public static int updateChangeCheck(int oid,String url){
        String sql="update orderinf set order_state=? where order_id=?";
        Object[] params ={
                url,oid
        };
        return DBUtil.exectuIUD(sql,params);
    }
}

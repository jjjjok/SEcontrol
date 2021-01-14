package com.SE.dao;

import com.SE.bean.appeal;
import com.SE.bean.refund;
import com.SE.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppealDao {
    //添加申诉信息
    public static int appealInsert(appeal i){
        String sql="insert into appeal(order_id,user_id,appeal_rea,appeal_inf,appeal_img) values(?,?,?,?,?)";
        Object[] params ={
                i.getOrder_id(),i.getUser_id(),i.getAppeal_rea(),i.getAppeal_inf(),i.getAppeal_img()
        };
        return DBUtil.exectuIUD(sql,params);
    }
    //更新申诉处理信息
    public static int appealUpdate(String appeal_result,int oid){
        String sql="update appeal set appeal_result =? where order_id=?";
        Object[] params ={
                appeal_result,oid
        };
        DBUtil.exectuIUD(sql,params);
        sql="update orderinf set order_state=10 where order_id=?";
        Object[] params1 ={
                oid
        };
        return DBUtil.exectuIUD(sql,params1);
    }


    //显示申诉详情
    public static appeal getAppealDetailById(int id){
        appeal i=new appeal();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select * from appeal where order_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                i.setOrder_id(rs.getInt(2));
                i.setAppeal_rea(rs.getString(6));
                i.setAppeal_inf(rs.getString(4));
                i.setAppeal_img(rs.getString(3));
                i.setUser_id(rs.getInt(7));
                i.setAppeal_result(rs.getString(8));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return i;
    }

}

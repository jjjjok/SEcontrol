package com.SE.dao;

import com.SE.bean.item;
import com.SE.bean.refund;
import com.SE.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RefundDao {
    //添加退款信息
    public static int refundInsert(refund i){
        String sql="insert into refund(order_id,user_id,refund_rea,refund_inf,refund_img) values(?,?,?,?,?)";
        Object[] params ={
                i.getOrder_id(),i.getUser_id(),i.getRefund_rea(),i.getRefund_inf(),i.getRefund_img()
        };
        return DBUtil.exectuIUD(sql,params);
    }


    //显示退款详情
    public static refund getRefundDetailById(int id){
        refund i=new refund();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select * from refund where order_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                i.setOrder_id(rs.getInt(2));
                i.setRefund_rea(rs.getString(4));
                i.setRefund_inf(rs.getString(5));
                i.setRefund_img(rs.getString(6));
                i.setUser_id(rs.getInt(3));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return i;
    }

    //退款处理
    public static int updateRefundCheck(int oid,String url){
        String sql="update orderinf set order_state=? where order_id=?";
        Object[] params ={
                url,oid
        };
        return DBUtil.exectuIUD(sql,params);
    }


}

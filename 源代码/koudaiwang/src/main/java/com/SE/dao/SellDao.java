package com.SE.dao;

import com.SE.bean.orderinf;
import com.SE.bean.sell;
import com.SE.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SellDao {
    //用户发布物品更新发布表
    public static int insertSell(int item_id,int user_id,String name,float a,float b,int num,String img,String inf){
        String sql="insert into sell(item_id,user_id,sell_date,item_name,item_aprice,item_bprice,item_num,item_img,sell_state,item_inf) values(?,?,now(),?,?,?,?,?,1,?)";
        Object[] params ={
                item_id,user_id,name,a,b,num,img,inf
        };
        return DBUtil.exectuIUD(sql,params);
    }

    //已发布信息
    public static ArrayList<sell> selectSellInf(int userid){
        ArrayList<sell> list = new ArrayList<sell>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            String sql="select * from sell where user_id=?";
            ps=conn.prepareStatement(sql);

            ps.setInt(1,userid);
            rs=ps.executeQuery();
            while(rs.next()){
                sell s=new sell();
                s.setSell_id(rs.getInt(1));
                s.setItem_id(rs.getInt(2));
                s.setUser_id(rs.getInt(3));
                s.setSell_date(rs.getDate(4));
                s.setSell_state(rs.getInt(5));
                s.setItem_name(rs.getString(6));
                s.setItem_img(rs.getString(7));
                s.setItem_aprice(rs.getFloat(8));
                s.setItem_bprice(rs.getFloat(9));
                s.setItem_num(rs.getInt(10));
                s.setItem_inf(rs.getString(11));
                list.add(s);


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;

    }
    //发布删除
    public static int itemDelete(int id){
        String sql="delete from sell where item_id=?";
        Object[] params ={
                id
        };
        return DBUtil.exectuIUD(sql,params);
    }
    //插入物流信息
    public static int insertSend(int order_id,String name,String wuliuid){
        String sql="insert into wuliu(order_id,wuliu_name,wuliu_id,wuliu_date) values(?,?,?,now())";
        Object[] params ={
                order_id,name,wuliuid
        };
        DBUtil.exectuIUD(sql,params);
        sql="update orderinf set order_state=2 where order_id=?";
        Object[] params1 ={
                order_id
        };
        return  DBUtil.exectuIUD(sql,params1);

    }
    //换货更新物流信息
    public static int updateSend(int order_id,String name,String wuliuid){
        String sql="update wuliu set wuliu_name=?,wuliu_id=?,wuliu_date=now() where order_id=?";
        Object[] params ={
                name,wuliuid,order_id
        };
        DBUtil.exectuIUD(sql,params);
        sql="update orderinf set order_state=14 where order_id=?";
        Object[] params1 ={
                order_id
        };
        return  DBUtil.exectuIUD(sql,params1);

    }

}

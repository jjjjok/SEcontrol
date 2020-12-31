package com.SE.dao;

import com.SE.bean.collect;
import com.SE.bean.collectitem;
import com.SE.bean.item;
import com.SE.bean.userinf;
import com.SE.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CollectDao {
    //根据用户id得到购物车表
    public static ArrayList<collectitem> selectCollectByUId(int id){
        ArrayList<collectitem> list = new ArrayList<collectitem>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select * from collectitem where user_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                collectitem i=new collectitem();
                i.setItem_id(rs.getInt(2));
                i.setItem_inf(rs.getString(3));
                i.setItem_name(rs.getString(4));
                i.setItem_img(rs.getString(5));
                i.setItem_aprice(rs.getFloat(6));
                i.setItem_bprice(rs.getFloat(7));
                i.setCollect_count(rs.getInt(8));
                i.setUser_id(rs.getInt(9));


                list.add(i);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;
    }



    //返回购物车物品数量
    public static int selectCollectNumByid(int uid,int itemid){
        ArrayList<collect> list = new ArrayList<collect>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select collect_count from collect where item_id=? and user_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,itemid);
            ps.setInt(2,uid);
            rs=ps.executeQuery();
            while(rs.next()){
                count=rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }
        return count;
    }
    //查询物品是否在购物车中
    public static int selectByUAI(int uid,int itemid){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select count(*) from collect where user_id=? and item_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,uid);
            ps.setInt(2,itemid);
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
    //购物车中存在则加数量
    public static int updateNum(int num,int uid,int itemid){
        String sql="update collect set collect_count=? where user_id=? and item_id=?";
        Object[] params ={
                num,uid,itemid
        };
        return DBUtil.exectuIUD(sql,params);
    }
    //更新遍历表信息
    public static int updateCINum(int num,int uid,int itemid){
        String sql="update collectitem set collect_count=? where user_id=? and item_id=?";
        Object[] params ={
                num,uid,itemid
        };
        return DBUtil.exectuIUD(sql,params);
    }


    //购物车添加
    public static int collectInsert(collect c){
        String sql="insert into collect(user_id,item_id,collect_count,collect_valid) values(?,?,?,?)";
        Object[] params ={
                c.getUser_id(),c.getItem_id(),c.getCollect_count(),c.getCollect_valid()
        };
        return DBUtil.exectuIUD(sql,params);
    }
    //购物车遍历表添加
    public static int collectItemInsert(collectitem c){
        String sql="insert into collectitem(item_id,item_inf,item_name,item_img,item_aprice,item_bprice,collect_count,user_id) values(?,?,?,?,?,?,?,?)";
        Object[] params ={
                c.getItem_id(),c.getItem_inf(),c.getItem_name(),c.getItem_img(),c.getItem_aprice(),c.getItem_bprice(),c.getCollect_count(),c.getUser_id()
        };
        return DBUtil.exectuIUD(sql,params);
    }

    //collectitem删除
    public static int collectItemDelete(int uid,int itemid){
        String sql="delete from collectitem where user_id=? and item_id=?";
        Object[] params ={
                uid,itemid
        };
        return DBUtil.exectuIUD(sql,params);
    }





    //购物车删除
    public static int collectDelete(int uid,int itemid){
        String sql="delete from collect where user_id=? and item_id=?";
        Object[] params ={
                uid,itemid
        };
        return DBUtil.exectuIUD(sql,params);
    }
    //根据用户id和选择的商品id得到购物车表
    public static collectitem selectCollectByUIId(int uid,int itemid){
        collectitem i=new collectitem();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select * from collectitem where user_id=? and item_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,uid);
            ps.setInt(2,itemid);
            rs=ps.executeQuery();
            while(rs.next()){

                i.setItem_id(rs.getInt(2));
                i.setItem_inf(rs.getString(3));
                i.setItem_name(rs.getString(4));
                i.setItem_img(rs.getString(5));
                i.setItem_aprice(rs.getFloat(6));
                i.setItem_bprice(rs.getFloat(7));
                i.setCollect_count(rs.getInt(8));
                i.setUser_id(rs.getInt(9));


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return i;
    }

    //根据物品id获取卖家收款账户
    public static String selectPayById(int itemid){
        String pay="";
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select user_pay from userinf where user_id=(select user_id from sell where item_id=?)";
            ps=conn.prepareStatement(sql);

            ps.setInt(1,itemid);
            rs=ps.executeQuery();
            while(rs.next()){

               pay=rs.getString(1);


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return pay;
    }


}

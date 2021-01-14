package com.SE.dao;

import com.SE.bean.collect;
import com.SE.bean.item;
import com.SE.bean.orderinf;
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
        String sql="insert into item(item_name,item_aprice,item_bprice,item_inf,item_num,item_img,item_check) values(?,?,?,?,?,?,0)";
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
            String sql="select item_id,item_name,item_aprice,item_bprice,item_num,item_img,item_inf from item where item_check=1 limit ?,?";
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

    //后台商品审核
    public static ArrayList<item> selectAllCheckItem(int cpage,int count){
        ArrayList<item> list = new ArrayList<item>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select item_id,item_name,item_aprice,item_bprice,item_num,item_img,item_inf from item where item_check=0 limit ?,?";
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

    //后台订单管理
    public static ArrayList<orderinf> selectAllOrder(int cpage, int count){
        ArrayList<orderinf> list = new ArrayList<orderinf>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select confirm_id,order_date,order_state,item_id,item_img,item_bprice,address,add_name,item_name,order_num,seller_pay,user_id,order_id from orderinf where order_state is not null limit ?,?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,(cpage-1)*count);
            ps.setInt(2,count);
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
                o.setSeller_pay(rs.getString(11));
                o.setUser_id(rs.getInt(12));
                o.setOrder_id(rs.getInt(13));
                list.add(o);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;
    }



    //前台根据分类id显示物品
    public static ArrayList<item> selectItemByFlId(int cpage,int count,int id){
        ArrayList<item> list = new ArrayList<item>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select item_name,item_aprice,item_bprice,item_img,item_id from item where item_id in(select item_id from link where fl_id=?) and item_check=1 limit ?,?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            ps.setInt(2,(cpage-1)*count);
            ps.setInt(3,count);
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

    //搜索物品
    public static ArrayList<item> searchItem(String text){
        ArrayList<item> list = new ArrayList<item>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select item_name,item_aprice,item_bprice,item_img,item_id from item where item_name like ? and item_check=1";
            ps=conn.prepareStatement(sql);
            ps.setString(1,"%"+text+"%");
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
            String sql="select item_id,item_name,item_aprice,item_bprice,item_img from item  where item_check=1 limit ?,?";
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
    //用户支付成功后，更新物品数量
    public static int updateItemNum(int item_id,int num){
        String sql="update item set item_num=? where item_id=?";
        Object[] params ={
                num,item_id
        };
        return DBUtil.exectuIUD(sql,params);
    }

    //用户支付成功后，更新物品数量
    public static int updateCIItemNum(int item_id,int num){
        String sql="update collectitem set item_num=? where item_id=?";
        Object[] params ={
                num,item_id
        };
        return DBUtil.exectuIUD(sql,params);
    }
//根据id返回数量
    public static int selectNumById(int id){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select item_num from item where item_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
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
    //根据订单表中的orderid返回物品id
    public static int selectIdByOId(int id){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select item_id from orderinf where order_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
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
    //根据订单表中的orderid返回购买数量
    public static int selectOrderNumByOId(int id){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select order_num from orderinf where order_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
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
    //后台搜索物品
    public static ArrayList<item> selectItemByName(int cpage,int count,String name){
        ArrayList<item> list = new ArrayList<item>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select item_id,item_name,item_aprice,item_bprice,item_img from item where item_name=? limit ?,?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setInt(2,(cpage-1)*count);
            ps.setInt(3,count);
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
    //商品删除
    public static int itemDelete(int id){
        String sql="delete from item where item_id=?";
        Object[] params ={
                id
        };
        return DBUtil.exectuIUD(sql,params);
    }

    //审核通过
    public static int updateCheck(int uid){
        String sql="update item set item_check=1 where item_id=?";
        Object[] params ={
                uid
        };
        return DBUtil.exectuIUD(sql,params);
    }

    //物品数量为0
    public static int updateAfterBuy(int itemid){
        String sql="update item set item_check=2 where item_id=?";
        Object[] params ={
                itemid
        };
        return DBUtil.exectuIUD(sql,params);
    }



    //确认转账
    public static int payChecked(int orderid){
        String sql="update orderinf set order_state=4 where order_id=?";
        Object[] params ={
                orderid
        };
        return DBUtil.exectuIUD(sql,params);
    }


}

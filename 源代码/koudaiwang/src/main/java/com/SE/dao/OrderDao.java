package com.SE.dao;

import com.SE.bean.item;
import com.SE.bean.orderinf;
import com.SE.bean.wuliu;
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
    public static int orderAdd(int confirmid,int uid,int itemid,int itemnum,String item_img,float item_bprice,String item_name,String seller_pay,String buyer_pay,String seller_name){
        String sql="insert into orderinf(confirm_id,user_id,item_id,order_num,order_date,item_img,item_bprice,item_name,seller_pay,buyer_pay,seller_name) values(?,?,?,?,now(),?,?,?,?,?,?)";
        Object[] params ={
                confirmid,uid,itemid,itemnum,item_img,item_bprice,item_name,seller_pay,buyer_pay,seller_name
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
    //退款后更新订单信息
    public static int updateOrderRefund(int order_id){
        String sql="update orderinf set order_state=5 where order_id=?";
        Object[] params ={
                order_id
        };
        return DBUtil.exectuIUD(sql,params);
    }
    //换货后更新订单信息
    public static int updateOrderChange(int order_id){
        String sql="update orderinf set order_state=11 where order_id=?";
        Object[] params ={
                order_id
        };
        return DBUtil.exectuIUD(sql,params);
    }

    //申诉后更新订单信息
    public static int updateOrderAppeal(int order_id){
        String sql="update orderinf set order_state=8 where order_id=?";
        Object[] params ={
                order_id
        };
        return DBUtil.exectuIUD(sql,params);
    }

    //管理员退款后更新订单信息
    public static int updateOrderRefundMan(int order_id){
        String sql="update orderinf set order_state=9 where order_id=?";
        Object[] params ={
                order_id
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
            String sql="select confirm_id,order_date,order_state,item_id,item_img,item_bprice,address,add_name,item_name,order_num,order_id,add_phone,seller_name from orderinf where user_id=? and order_state is not null";
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
                o.setSeller_name(rs.getString(13));
                list.add(o);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;

    }


    //用户相关订单信息
    public static ArrayList<orderinf> selectOrderInfByState(int userid,int orderstate){
        ArrayList<orderinf> list = new ArrayList<orderinf>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            String sql="select confirm_id,order_date,order_state,item_id,item_img,item_bprice,address,add_name,item_name,order_num,order_id,add_phone,seller_name from orderinf where user_id=? and order_state=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,userid);
            ps.setInt(2,orderstate);
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
                o.setSeller_name(rs.getString(13));
                list.add(o);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;

    }
    //用户退款订单信息
    public static ArrayList<orderinf> selectOrderInfRefunding(int userid){
        ArrayList<orderinf> list = new ArrayList<orderinf>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            String sql="select confirm_id,order_date,order_state,item_id,item_img,item_bprice,address,add_name,item_name,order_num,order_id,add_phone,seller_name from orderinf where user_id=? and (order_state=5 or order_state=6 or order_state=7)";
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
                o.setSeller_name(rs.getString(13));
                list.add(o);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;

    }
    //用户已完成订单信息
    public static ArrayList<orderinf> selectOrderInfFinish(int userid){
        ArrayList<orderinf> list = new ArrayList<orderinf>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            String sql="select confirm_id,order_date,order_state,item_id,item_img,item_bprice,address,add_name,item_name,order_num,order_id,add_phone,seller_name from orderinf where user_id=? and (order_state=4 or order_state=9 or order_state=10 or order_state=3)";
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
                o.setSeller_name(rs.getString(13));
                list.add(o);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;
    }

    //用户换货订单信息
    public static ArrayList<orderinf> selectOrderInfChange(int userid) {
        ArrayList<orderinf> list = new ArrayList<orderinf>();
        ResultSet rs = null;
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select confirm_id,order_date,order_state,item_id,item_img,item_bprice,address,add_name,item_name,order_num,order_id,add_phone,seller_name from orderinf where user_id=? and (order_state=11 or order_state=12 or order_state=13 or order_state=14)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                orderinf o = new orderinf();
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
                o.setSeller_name(rs.getString(13));
                list.add(o);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }
        return list;


    }
    //商家换货订单信息
    public static ArrayList<orderinf> selectSellOrderInfChange(int userid) {
        ArrayList<orderinf> list = new ArrayList<orderinf>();
        ResultSet rs = null;
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            String sql = "select confirm_id,order_date,order_state,item_id,item_img,item_bprice,address,add_name,item_name,order_num,order_id,add_phone,seller_name from orderinf where item_id in(select item_id from sell where user_id=?)and (order_state=11 or order_state=12 or order_state=13 or order_state=14)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            while (rs.next()) {
                orderinf o = new orderinf();
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
                o.setSeller_name(rs.getString(13));
                list.add(o);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(rs, ps, conn);
        }
        return list;


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
            String sql="select confirm_id,order_date,order_state,item_id,item_img,item_bprice,address,add_name,item_name,order_num,order_id from orderinf where item_id in(select item_id from sell where user_id=?) and (order_state=2 or order_state=14)";
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


    //查找被购买的商品中买家申请退款的订单
    public static ArrayList<orderinf> selectOrderInfRefund(int userid){
        ArrayList<orderinf> list = new ArrayList<orderinf>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            String sql="select confirm_id,order_date,order_state,item_id,item_img,item_bprice,address,add_name,item_name,order_num,order_id from orderinf where item_id in(select item_id from sell where user_id=?) and (order_state=5 or order_state=6 or order_state=7)";
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




    //查找卖家已完成的订单
    public static ArrayList<orderinf> selectSellOrderInfFinish(int userid){
        ArrayList<orderinf> list = new ArrayList<orderinf>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        try {
            String sql="select confirm_id,order_date,order_state,item_id,item_img,item_bprice,address,add_name,item_name,order_num,order_id from orderinf where item_id in(select item_id from sell where user_id=?) and (order_state=3 or order_state=4)";
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

    //根据id查找订单信息
    public static orderinf selectOrderById(int id){
        orderinf i=new orderinf();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select * from orderinf where order_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
               i.setOrder_id(rs.getInt(1));
               i.setOrder_num(rs.getInt(5));
               i.setOrder_date(rs.getDate(6));
               i.setAddress(rs.getString(7));
               i.setItem_img(rs.getString(10));
               i.setItem_bprice(rs.getFloat(11));
               i.setAdd_name(rs.getString(12));
               i.setItem_name(rs.getString(13));
               i.setAdd_phone(rs.getString(15));



            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return i;
    }


    //根据id查找物流信息
    public static wuliu selectWuliuById(int id){
        wuliu i=new wuliu();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select * from wuliu where order_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                i.setWuliu_id(rs.getString(1));
                i.setOrder_id(rs.getInt(2));
                i.setWuliu_name(rs.getString(3));
                i.setWuliu_date(rs.getDate(5));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return i;
    }


}

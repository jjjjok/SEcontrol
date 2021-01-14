package com.SE.dao;

import com.SE.bean.address;
import com.SE.bean.item;
import com.SE.bean.manager;
import com.SE.bean.userinf;
import com.SE.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
//插入用户
    public static int userInsert(userinf user){
        String sql="insert into userinf(user_name,user_phone,user_pay,user_pwd) values(?,?,?,?)";
        Object[] params ={
                user.getUser_name(),user.getUser_phone(),user.getUser_pay(),user.getUser_pwd()
        };
        return DBUtil.exectuIUD(sql,params);
    }
    //插入地址
    public static int addressInsert(address a,int uid){
        String sql="insert into address(user_name,user_phone,address,user_id) values(?,?,?,?)";
        Object[] params ={
                a.getUser_name(),a.getUser_phone(),a.getAddress(),uid
        };
        return DBUtil.exectuIUD(sql,params);
    }


    //修改地址
    public static int addressChange(address a,int aid){
        String sql="update address set user_name=?,user_phone=?,address=? where id=?";
        Object[] params ={
                a.getUser_name(),a.getUser_phone(),a.getAddress(),aid
        };
        return DBUtil.exectuIUD(sql,params);
    }

    public static int userSearchByName(String name){

        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select count(*) from userinf where user_name=?";
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
    public static int sendSearchById(String name){

        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select count(*) from wuliu where wuliu_id=?";
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
//查找用户使用的地址
    public static String addSearchByUser(int name){

        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        String count ="";
        try {
            String sql="select address from address where user_id=? and add_state=1";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,name);
            rs=ps.executeQuery();
            while(rs.next()){
                count = rs.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }
        return count;

    }

    //查找地址对应的收件人
    public static address addNameSearchByUser(int uid,String add){

        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        address a=new address();
        try {
            String sql="select user_name,user_phone from address where user_id=? and address=? and add_state=1";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,uid);
            ps.setString(2,add);
            rs=ps.executeQuery();
            while(rs.next()){
                a.setUser_name(rs.getString(1));
                a.setUser_phone(rs.getString(2));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }
        return a;

    }



//根据手机号和用户名查找
    public static int userSearchByNameAndPhone(String name,String phone){

        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select count(*) from userinf where user_name=? and user_phone=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,phone);
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



//登录时搜索用户
    public static int selectByName(String name,String pwd){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select count(*) from userinf where user_name=? and user_pwd=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,""+name+"");
            ps.setString(2,pwd);
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

    //登录时搜索管理员
    public static int selectManagerByName(String name,String pwd){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select count(*) from manager where man_name=? and man_pwd=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,pwd);
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

    /**
     * 根据用户名和密码搜索用户
     * @param name
     * @param pwd
     * @return
     */

    public static userinf selectAdmin(String name,String pwd){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        userinf user=new userinf();

        try {
            String sql="select * from userinf where user_name=? and user_pwd=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,pwd);
            rs=ps.executeQuery();
            while(rs.next()){
                user.setUser_id(rs.getInt(1));
                user.setUser_name(rs.getString(2));
                user.setUser_pwd(rs.getString(3));
                user.setUser_phone(rs.getString(5));
                user.setUser_pay(rs.getString(6));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }
        return user;
    }

//根据用户名和密码搜索管理员
    public static manager selectManager(String name, String pwd){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        manager man=new manager();

        try {
            String sql="select * from manager where man_name=? and man_pwd=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,pwd);
            rs=ps.executeQuery();
            while(rs.next()){
                man.setMan_id(rs.getInt(1));
                man.setMan_name(rs.getString(2));
                man.setMan_pwd(rs.getString(3));

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }
        return man;
    }

    /**
     *
     * 搜索全部用户并分页
     * @return
     */
    public static ArrayList<userinf> selectAllUser(int cpage,int count){
        ArrayList<userinf> list = new ArrayList<userinf>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select user_id,user_name,user_pay,user_phone from userinf limit ?,?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,(cpage-1)*count);
            ps.setInt(2,count);
            rs=ps.executeQuery();
            while(rs.next()){
                userinf user=new userinf();
                user.setUser_id(rs.getInt(1));
                user.setUser_name(rs.getString(2));
                user.setUser_phone(rs.getString(4));
                user.setUser_pay(rs.getString(3));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;
    }
    //后台搜索用户
    public static ArrayList<userinf> selectUserByName(int cpage,int count,int name){
        ArrayList<userinf> list = new ArrayList<userinf>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select user_id,user_name,user_pay,user_phone from userinf where user_id=? limit ?,?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,name);
            ps.setInt(2,(cpage-1)*count);
            ps.setInt(3,count);
            rs=ps.executeQuery();
            while(rs.next()){
                userinf user=new userinf();
                user.setUser_id(rs.getInt(1));
                user.setUser_name(rs.getString(2));
                user.setUser_phone(rs.getString(4));
                user.setUser_pay(rs.getString(3));
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;

    }
//通过id查找用户
    public static userinf selectById(int id){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        userinf user=new userinf();

        try {
            String sql="select * from userinf where user_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();
            while(rs.next()){
                user.setUser_id(rs.getInt(1));
                user.setUser_name(rs.getString(2));
                user.setUser_pwd(rs.getString(3));
                user.setUser_phone(rs.getString(5));
                user.setUser_pay(rs.getString(6));
                user.setUser_img(rs.getString(7));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }
        return user;
    }
    //用户删除
    public static int userDelete(int uid){
        String sql="delete from userinf where user_id=?";
        Object[] params ={
                uid
        };
        return DBUtil.exectuIUD(sql,params);
    }
    //地址删除
    public static int addressDelete(int id){
        String sql="delete from address where id=?";
        Object[] params ={
                id
        };
        return DBUtil.exectuIUD(sql,params);
    }

    //修改密码
    public static int updatePwd(String name,String pwd){
        String sql="update userinf set user_pwd=? where user_name=?";
        Object[] params ={
                pwd,name
        };
        return DBUtil.exectuIUD(sql,params);
    }



    //查找用户的地址表
    public static ArrayList<address> selectUserAddress(int uid){
        ArrayList<address> list = new ArrayList<address>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select user_id,address,id,user_name,user_phone from address where user_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,uid);

            rs=ps.executeQuery();
            while(rs.next()){
                address a=new address();
                a.setUser_id(rs.getInt(1));
                a.setAddress(rs.getString(2));
                a.setId(rs.getInt(3));
                a.setUser_name(rs.getString(4));
                a.setUser_phone(rs.getString(5));
                list.add(a);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;

    }

    //根据地址编号查找地址
    public static address selectAddressByAddId(int aid){
        address a=new address();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select user_id,address,id,user_name,user_phone from address where id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,aid);

            rs=ps.executeQuery();
            while(rs.next()){

                a.setUser_id(rs.getInt(1));
                a.setAddress(rs.getString(2));
                a.setId(rs.getInt(3));
                a.setUser_name(rs.getString(4));
                a.setUser_phone(rs.getString(5));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return a;

    }





}

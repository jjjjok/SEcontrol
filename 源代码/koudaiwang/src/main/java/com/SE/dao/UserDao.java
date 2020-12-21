package com.SE.dao;

import com.SE.bean.item;
import com.SE.bean.userinf;
import com.SE.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    public static int userInsert(userinf user){
        String sql="insert into userinf(user_name,user_phone,user_pwd) values(?,?,?)";
        Object[] params ={
                user.getUser_name(),user.getUser_phone(), user.getUser_pwd()
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
//登录时搜索用户
    public static int selectByName(String name,String pwd){
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        int count =0;
        try {
            String sql="select count(*) from userinf where user_name=? and user_pwd=?";
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
    public static ArrayList<userinf> selectUserByName(int cpage,int count,String name){
        ArrayList<userinf> list = new ArrayList<userinf>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select user_id,user_name,user_pay,user_phone from userinf where user_name=? limit ?,?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);
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
        userinf user=null;

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

}

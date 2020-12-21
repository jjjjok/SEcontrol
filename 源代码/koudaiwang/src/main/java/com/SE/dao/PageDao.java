package com.SE.dao;

import com.SE.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PageDao {
    //后台全部用户分页
    public static int[] userPage(int count){
        int result[]={0,1};
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs =null;
        try {
            String sql="select count(*) from userinf";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                result[0] = rs.getInt(1);
                if(result[0]%count==0){
                    result[1]=result[0]/count;
                }else{
                    result[1]=result[0]/count+1;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }
        return result;
    }
    //后台搜索用户分页
    public static int[] userNamePage(int count,String name){
        int result[]={0,1};
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs =null;
        try {
            String sql="select count(*) from userinf where user_name=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);
            rs=ps.executeQuery();
            while(rs.next()){
                result[0] = rs.getInt(1);
                if(result[0]%count==0){
                    result[1]=result[0]/count;
                }else{
                    result[1]=result[0]/count+1;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }
        return result;
    }
    //全部物品分页
    public static int[] itemPage(int count){
        int result[]={0,1};
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs =null;
        try {
            String sql="select count(*) from item";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                result[0] = rs.getInt(1);
                if(result[0]%count==0){
                    result[1]=result[0]/count;
                }else{
                    result[1]=result[0]/count+1;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }
        return result;
    }
    //后台搜索物品分页
    public static int[] itemNamePage(int count,String name){
        int result[]={0,1};
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet rs =null;
        try {
            String sql="select count(*) from item where item_name=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,name);
            rs=ps.executeQuery();
            while(rs.next()){
                result[0] = rs.getInt(1);
                if(result[0]%count==0){
                    result[1]=result[0]/count;
                }else{
                    result[1]=result[0]/count+1;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }
        return result;
    }

}

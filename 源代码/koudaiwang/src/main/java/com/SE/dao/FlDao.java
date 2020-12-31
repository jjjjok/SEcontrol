package com.SE.dao;

import com.SE.bean.fl;
import com.SE.bean.item;
import com.SE.bean.userinf;
import com.SE.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlDao {
    public static ArrayList<fl> selectFl(){
        ArrayList<fl> list = new ArrayList<fl>();
        ResultSet rs =null;
        Connection conn= DBUtil.getConnection();
        PreparedStatement ps = null;

        try {
            String sql="select fl_name from fl";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                fl f=new fl();
                f.setFl_name(rs.getString(1));
                list.add(f);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,conn);
        }return list;
    }

    public static int linkInsert(int fl_id,int item_id){
        String sql="insert into link(fl_id,item_id) values(?,?)";
        Object[] params ={
                fl_id,item_id
        };
        return DBUtil.exectuIUD(sql,params);
    }

    //分类关联删除
    public static int linkDelete(int id){
        String sql="delete from link where item_id=?";
        Object[] params ={
                id
        };
        return DBUtil.exectuIUD(sql,params);
    }

}

package com.SE.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    private static final String jdbcUrl="jdbc:mysql://localhost:3306/koudaiwang?useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    private static final String dbUser="root";
    private static final String dbPwd="";
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        Connection conn=null;
        try{
            conn=java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return conn;

    }
    public static int exectuIUD(String sql,Object[] params){
        int count=0;
        Connection conn=DBUtil.getConnection();
        PreparedStatement pst=null;
        try {
            pst=conn.prepareStatement(sql);
            for(int i=0;i< params.length;i++){
                pst.setObject(i+1,params[i]);
            }
           count= pst.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            DBUtil.close(null,pst,conn);
        }
        return count;
    }
    public static void close(ResultSet rs,PreparedStatement pst,Connection conn){
        try {
            if(rs!=null)
                rs.close();
            if(pst!=null)
                pst.close();
            if(conn!=null)
                conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}

package com.j2ee.edu_admi.util;

import java.sql.*;

/**
 * @author Dcy
 */

public class DBUtil {

    private static DBUtil DBUtil;

    //单例模式
    private DBUtil() throws ClassNotFoundException {
        //注册驱动
        //在ContextListener中加载
//        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public static DBUtil getDBUtil() throws ClassNotFoundException {
        //如果loginBean是空说明LoginBean的对象没有被创建，则new一个对象返回
        if (DBUtil == null) {
            DBUtil = new DBUtil();
        }
        return DBUtil;
    }

    //连接数据库
    public  Connection connectDB() {
        try {
            //连接数据库
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/j2ee", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //关闭数据库连接
    public  void closeConnect(Connection connect) {
        try {
            if (connect != null) {
                connect.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  void closeConnect(Connection connection, Statement statement) {
       try {
           if (connection != null) {
               connection.close();
           }
           if (statement != null) {
               statement.close();
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
    }

    public  void closeConnect(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet){
        try {
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.j2ee.edu_admi.beans;

import java.sql.*;

public class LoginBean {

    //单例模式
    private static LoginBean loginBean;
    private Connection connection;

    private LoginBean() {
        try {

            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接数据库
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/j2ee", "root", "123456");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static LoginBean getInstance() {
        if (loginBean == null) {
            loginBean = new LoginBean();
        }
        return loginBean;
    }

    public boolean Login(String username, String passwd) {
        try {

            //通过输入用户名向数据库查询
            Statement statement = connection.createStatement();
            String sql = "select * from person where username ='" + username + "'and passwd = '" + passwd + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            //根据resultSet的结果判断账号密码是否正确
            boolean isUserInfoTrue = resultSet.next();

            //关闭所有连接
            resultSet.close();
            statement.close();
            connection.close();

            return isUserInfoTrue;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

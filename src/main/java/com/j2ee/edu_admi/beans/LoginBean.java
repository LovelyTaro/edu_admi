package com.j2ee.edu_admi.beans;

import java.sql.*;

public class LoginBean {

    //单例模式
    private static LoginBean loginBean;
    private LoginBean() throws ClassNotFoundException {

            //注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

    }
    public static LoginBean getInstance() throws ClassNotFoundException {
        //如果loginBean是空说明LoginBean的对象没有被创建，则new一个对象返回
        if (loginBean == null) {
            loginBean = new LoginBean();
        }
        return loginBean;
    }

    //连接数据库
    private Connection connectDB(){
        try {
            //连接数据库
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/j2ee", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //检测登录的函数
    public boolean Login(String username, String passwd) {
        try {
            //创建数据库连接
            Connection connection = connectDB();

            //通过输入用户名向数据库查询
            Statement statement = connection.createStatement();
            String sql = "select * from person where username ='" + username + "'and passwd = '" + passwd + "'";
            ResultSet resultSet = statement.executeQuery(sql);

//            //使用preprareStatement进行数据库的查询
//            String sql1 = "select * from person where username =? and passwd =?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
//            preparedStatement.setString(1,username);
//            preparedStatement.setString(2,passwd);
//            ResultSet resultSet1 = preparedStatement.executeQuery();

            //根据resultSet的结果判断账号密码是否正确
            boolean isUserInfoTrue = resultSet.next();

            //关闭所有连接
            resultSet.close();
            statement.close();
            connection.close();

            //返回结果
            return isUserInfoTrue;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


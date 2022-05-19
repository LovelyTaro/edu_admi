package com.j2ee.edu_admi.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Dcy
 */
public class LoginBean {


    private final DBUtil dbUtil;

    public LoginBean() throws ClassNotFoundException {
        //获取数据库对象
        dbUtil = DBUtil.getDBUtil();
    }


    //检测登录的函数
    public boolean login(User user) {
        //用queryUserExist查询数据库中是否存在次对象
        try {
            return queryUserExist(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    //用户注册
    public boolean register(User user){
        try {
            //查询用户是否存在
            if(queryUserExist(user)){
                return false;
            }
            DBUtil dbUtil = DBUtil.getDBUtil();
             return insertUser(user);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //检查用户名是否重复
    public boolean checkUserName(String username){
        try {
            return queryName(username);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //queryUser是否存在
    public boolean queryUserExist(User user)throws SQLException {
        //获取user信息
        String username = user.getUsername();
        String password = user.getPassword();
        String id = user.getId();

        //连接数据库使用preparedStatement进行查询，参数1：uname，参数2：passwd,参数3：uid
        Connection connection = dbUtil.connectDB();
        String sql = "select * from users where uname =? and passwd =? and uid =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean result = resultSet.next();

        //关闭所有连接
        preparedStatement.close();
        resultSet.close();
        dbUtil.closeConnect(connection);

        //返回查询结果
        return result;
    }


    //查询username是否重复
    public boolean queryName(String username) throws SQLException {
        //连接数据库使用preparedStatement进行查询，参数1：uname
        Connection connection = dbUtil.connectDB();
        String sql = "select * from users where uname =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean result = resultSet.next();

        //关闭所有连接
        preparedStatement.close();
        resultSet.close();
        dbUtil.closeConnect(connection);

        return result;
    }

    public boolean insertUser(User user) {
        //连接数据库使用preparedStatement进行插入，参数1：uname，参数2：passwd,参数3：uid
        try {
            //创建插入的sql语句
            Connection connection = dbUtil.connectDB();
            String sql = "insert into users(uname,passwd,uid) value(?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getId());
            //执行插入语句
            preparedStatement.execute();
            //关闭所有连接
            preparedStatement.close();
            dbUtil.closeConnect(connection);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}


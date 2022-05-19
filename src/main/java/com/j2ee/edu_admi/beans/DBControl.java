package com.j2ee.edu_admi.beans;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Dcy
 */

public class DBControl {

    private static DBControl DBControl;

    //单例模式
    private DBControl() throws ClassNotFoundException {
        //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
    }
    public static DBControl getDBControl() throws ClassNotFoundException {
        //如果loginBean是空说明LoginBean的对象没有被创建，则new一个对象返回
        if (DBControl == null) {
            DBControl = new DBControl();
        }
        return DBControl;
    }

    //连接数据库
    public Connection connectDB() {
        try {
            //连接数据库
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/j2ee", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //queryUser函数
    public boolean queryUserExist(User user)throws SQLException {
            //获取user信息
            String username = user.getUsername();
            String password = user.getPassword();
            String id = user.getId();

            //连接数据库使用preparedStatement进行查询，参数1：uname，参数2：passwd,参数3：uid
            Connection connection = this.connectDB();
            String sql = "select * from users where uname =? and passwd =? and uid =?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Boolean result = resultSet.next();

            //关闭所有连接
            preparedStatement.close();
            connection.close();
            resultSet.close();

            //返回查询结果
            return result;
    }

    //查询username是否重复
    public boolean queryName(String username) throws SQLException {
        //连接数据库使用preparedStatement进行查询，参数1：uname
        Connection connection = this.connectDB();
        String sql = "select * from users where uname =?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        Boolean result = resultSet.next();

        //关闭所有连接
        preparedStatement.close();
        connection.close();
        resultSet.close();

        return result;
    }
    public boolean insertUser(User user) {
        //连接数据库使用preparedStatement进行插入，参数1：uname，参数2：passwd,参数3：uid
        try {
            //创建插入的sql语句
            Connection connection = this.connectDB();
            String sql = "insert into users(uname,passwd,uid) value(?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getId());
            //执行插入语句
            preparedStatement.execute();
            //关闭所有连接
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //获取课程列表
    public List<Course> getCourseList(){
        try{
            Connection connection = this.connectDB();
            String sql = "select * from courses";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);;
            ResultSet result = preparedStatement.executeQuery();
            List<Course> courses = new LinkedList<Course>();
            while (result.next()){
                Course course = new Course(
                        result.getInt(1),
                        result.getString(2),
                        result.getInt(3),
                        result.getString(4),
                        result.getString(5),
                        result.getInt(6),
                        result.getInt(7),
                        result.getInt(8)
                );
                courses.add(course);
            }
            preparedStatement.close();
            connection.close();
            //将课程列表返回
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

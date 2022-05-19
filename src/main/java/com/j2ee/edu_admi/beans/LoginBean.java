package com.j2ee.edu_admi.beans;

import java.sql.SQLException;

/**
 * @author Dcy
 */
public class LoginBean {


    private final DBControl dbControl;

    public LoginBean() throws ClassNotFoundException {
        //获取数据库对象
        dbControl = DBControl.getDBControl();
    }


    //检测登录的函数
    public boolean login(User user) {
        //用queryUserExist查询数据库中是否存在次对象
        try {
            return dbControl.queryUserExist(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    //用户注册
    public boolean register(User user){
        try {
            //查询用户是否存在
            if(dbControl.queryUserExist(user)){
                return false;
            }
            DBControl dbControl = DBControl.getDBControl();
             return dbControl.insertUser(user);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    //检查用户名是否重复
    public boolean checkUserName(String username){
        try {
            DBControl dbControl = DBControl.getDBControl();
            return dbControl.queryName(username);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}


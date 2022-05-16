package com.j2ee.edu_admi.beans;

import java.sql.SQLException;

public class LoginBean {


    private DBControl DBControl;

    public LoginBean() throws ClassNotFoundException {
        //获取数据库对象
        DBControl = DBControl.getDataBaseBean();
    }


    //检测登录的函数
    public boolean login(User user) {

        //用queryUserExist查询数据库中是否存在次对象
        try {
            return DBControl.queryUserExist(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean register(User user){

        try {
            DBControl dbControl = DBControl.getDataBaseBean();
             return dbControl.insertUser(user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean checkUserName(String username){
        try {
            DBControl dbControl = DBControl.getDataBaseBean();
            return dbControl.queryName(username);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}


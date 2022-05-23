package com.j2ee.edu_admi.dao;

import com.j2ee.edu_admi.beans.User;

public interface UserDao {
    //根据username获取一条记录
    User getUser(String username) throws Exception;
    User getUserByNum(int userNum) throws Exception;
    //插入一条user
    void insertUser(User user) throws Exception;
    //根据id删除一条数据
    void deleteUserByUserNum(Integer userNum) throws Exception;
    //获取一共有多少用户
    Integer getUserCount() throws Exception;
    //查询用户是否存在
    Boolean queryUserExist(User user) throws Exception;
    //查询用户名是否存在
    Boolean queryUserNameExist(String username) throws Exception;

    void alterUser(User user) throws Exception;
}

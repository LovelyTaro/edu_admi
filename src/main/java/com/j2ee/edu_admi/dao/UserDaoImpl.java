package com.j2ee.edu_admi.dao;

import com.j2ee.edu_admi.beans.User;

import java.util.List;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    @Override
    // 根据Username获得User
    public User getUser(String username) throws Exception {
        User user = null;
        String sql = "select * from users where username =?";
        List<User> list = this.queryForList(sql, username);
        if (list.size() != 0) {
            user = list.get(0);
        }
        return user;
    }

    @Override
    public User getUserByNum(int userNum) throws Exception{
        User user = null;
        String sql = "select * from users where userNum =?";
        List<User> list = this.queryForList(sql, userNum);
        if (list.size() != 0) {
            user = list.get(0);
        }
        return user;
    }

    //添加一个用户user
    @Override
    public void insertUser(User user) throws Exception {
        String sql = "insert into users(username,password,id) values(?,?,?)";
        this.executeUpdate(sql, user.getUsername(), user.getPassword(), user.getId());
    }

    @Override
    public void deleteUserByUserNum(Integer userNum) throws Exception {
        String sql = "delete from users where userNum =?";
        this.executeUpdate(sql, userNum);
    }

    @Override
    public Integer getUserCount() throws Exception {
        String sql = "select count(*) from users";
        return Integer.valueOf(this.queryForValue(sql).toString());
    }

    @Override
    public Boolean queryUserExist(User user) throws Exception {
        String sql = "select * from users where username= ? and password = ? and id = ?";
        List<User> list = this.queryForList(sql,user.getUsername(),user.getPassword(),user.getId());
        return list.size() != 0;
    }

    @Override
    public Boolean queryUserNameExist(String username) throws Exception {
        String sql = "select * from users where username= ?";
        return this.queryForList(sql, username).size() != 0;
    }
    @Override
    public void alterUser(User user) throws Exception {
        String sql = "update users set username=? , password = ?  where userNum = ?";
        this.executeUpdate(
                sql,
                user.getUsername(),
                user.getPassword(),
                user.getUserNum()
        );
    }
}

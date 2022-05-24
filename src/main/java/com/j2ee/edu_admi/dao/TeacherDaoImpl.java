package com.j2ee.edu_admi.dao;

import com.j2ee.edu_admi.beans.Teacher;
import com.j2ee.edu_admi.beans.User;

import java.util.List;

public class TeacherDaoImpl extends BaseDao<Teacher> implements TeacherDao{

    @Override
    public Teacher getTeacherByNum(int teacherNum) throws Exception {
        Teacher teacher = null;
        String sql = "select * from teachers where teacherNum =?";
        List<Teacher> list = this.queryForList(sql, teacherNum);
        if (list.size() != 0) {
            teacher = list.get(0);
        }
        return teacher;
    }

    @Override
    public Teacher getTeacherByName(String teacherName) throws Exception {
        Teacher teacher = null;
        String sql = "select * from teachers where teacherName =?";
        List<Teacher> list = this.queryForList(sql, teacherName);
        if (list.size() != 0) {
            teacher = list.get(0);
        }
        return teacher;
    }

    @Override
    public void insertTeacher(Teacher teacher) throws Exception {
        UserDao userDao = new UserDaoImpl();
        if (userDao.queryUserNameExist(teacher.getUsername())) {
            throw new Exception();
        } else {
            userDao.insertUser(teacher);
            User user = userDao.getUser(teacher.getUsername());
            String sql = "insert into teachers(userNum,teacherName,gender,facultyNum,birth) values(?,?,?,?,?)";
            this.executeUpdate(sql, user.getUserNum(), teacher.getTeacherName(), teacher.getGender(), teacher.getFacultyNum(), teacher.getBirth());
        }
    }

    //通过外键关联删除
    @Override
    public void deleteTeacherByNum(Integer teacherNum) throws Exception {
        UserDao userDao = new UserDaoImpl();
        userDao.deleteUserByUserNum(getTeacherByNum(teacherNum).getUserNum());
    }

    @Override
    public Integer getTeacherCount() throws Exception {
        String sql = "select count(*) from teachers";
        return Integer.valueOf(this.queryForValue(sql).toString());
    }

    @Override
    public List<Teacher> getTeacherList(int page) throws Exception {
        String sql = "select * from teachers";
        List<Teacher> list = this.queryForList(sql);
        if (page * 8 > list.size()) {
            return list.subList((page - 1) * 8, list.size());
        }
        return list.subList((page - 1) * 8, page * 8);
    }

    public Teacher getStudentByUsername(String username) throws Exception{
        Teacher teacher = null;
        String sql = "select * from teachers where userNum = (select userNum from users where username = ?)";
        List<Teacher> list = this.queryForList(sql, username);
        if (list.size() != 0) {
            teacher = list.get(0);
        }
        return teacher;
    }

    @Override
    public void alterTeacher(Teacher teacher) throws Exception {
        String sql = "update teachers set teacherName=? , gender = ? , facultyNum = ? , birth = ? where teacherNum = ?";
        this.executeUpdate(
                sql,
                teacher.getTeacherName(),
                teacher.getGender(),
                teacher.getFacultyNum(),
                teacher.getBirth(),
                teacher.getTeacherNum()
        );
    }
}

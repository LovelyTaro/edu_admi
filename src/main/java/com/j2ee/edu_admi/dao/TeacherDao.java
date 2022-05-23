package com.j2ee.edu_admi.dao;

import com.j2ee.edu_admi.beans.Teacher;

import java.util.List;

public interface TeacherDao {

    //根据老师编号/姓名获得教师
    Teacher getTeacherByNum(int teacherNum) throws Exception;
    Teacher getTeacherByName(String teacherName) throws Exception;
    //插入一条Teacher
    void insertTeacher(Teacher teacher) throws Exception;
    //根据teacherNum删除一条数据
    void deleteTeacherByNum(Integer teacherNum) throws Exception;
    //获取一共有多少Teacher
    Integer getTeacherCount() throws Exception;
    //获取教师列表
    List<Teacher> getTeacherList(int page) throws Exception;
    //修改教师
    void alterTeacher(Teacher teacher) throws Exception;

}

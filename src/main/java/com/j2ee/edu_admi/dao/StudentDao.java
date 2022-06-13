package com.j2ee.edu_admi.dao;

import com.j2ee.edu_admi.beans.Student;

import java.util.List;

public interface StudentDao {
    //根据学生编号/姓名获得学生
    Student getStudentByNum(int studentNum) throws Exception;
    Student getStudentByName(String studentName) throws Exception;
    Student getStudentByUsername(String username) throws Exception;
    //插入一条Student
    void insertStudent(Student student) throws Exception;
    //根据studentNum删除一条数据
    void deleteStudentByNum(Integer studentNum) throws Exception;
    //获取一共有多少Student
    Integer getStudentCount() throws Exception;
    //获取学生列表
    List<Student> getStudentList(int page) throws Exception;
    //修改学生
    void alterStudent(Student student) throws Exception;
    //获取选择某个课的所有学生
    List<Student> getCourseStudents(int courseNum) throws Exception;
    List<Student> getSelectedStudent(int courseNum)throws Exception;
    int getSelectedStudentCount(int courseNum)throws Exception;
}

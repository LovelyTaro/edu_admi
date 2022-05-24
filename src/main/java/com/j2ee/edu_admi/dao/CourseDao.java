package com.j2ee.edu_admi.dao;

import com.j2ee.edu_admi.beans.Course;

import java.util.List;

public interface CourseDao {
    //根据courseNum获取一条记录
    Course getCourseByNum(int courseNum) throws Exception;
    List<Course> getCourseByName(String courseName) throws Exception;
    //插入一条Course
    void insertCourse(Course course) throws Exception;
    //根据courseNum删除一条数据
    void deleteCourseByNum(Integer courseNum) throws Exception;
    //获取一共有多少课程
    Integer getCourseCount() throws Exception;
    //获取课程列表
    List<Course> getCourseList(int page) throws Exception;
    //修改课程
    void alterCourse(Course course) throws Exception;
    //获得某个学生已经选择的课
    List<Course> getSelectedCourseList(String username,int page) throws Exception;
    int getSelectedCourseListCount(String username) throws Exception;
    //选课
    void selectCourse(int courseNum,String username)throws Exception;
    void deleteSelectCourse(int courseNum,String username)throws Exception;
    //获取某个教师的课程列表
    List<Course> getTeacherCourseList(String username, int page) throws Exception;
    int getTeacherCourseListCount(String username) throws Exception;
    //打分
    void score(int courseNum,int studentNum,int score) throws Exception;
}

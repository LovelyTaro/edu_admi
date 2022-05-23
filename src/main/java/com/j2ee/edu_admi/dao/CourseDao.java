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
}

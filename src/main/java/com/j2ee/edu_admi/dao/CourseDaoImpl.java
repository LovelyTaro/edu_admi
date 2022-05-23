package com.j2ee.edu_admi.dao;

import com.j2ee.edu_admi.beans.Course;

import java.util.List;

public class CourseDaoImpl extends BaseDao<Course> implements CourseDao {
    @Override
    public Course getCourseByNum(int courseNum) throws Exception {
        Course course = null;
        String sql = "select * from courses where courseNum =?";
        List<Course> list = this.queryForList(sql, courseNum);
        if (list.size() != 0) {
            course = list.get(0);
        }
        return course;
    }

    @Override
    public List<Course> getCourseByName(String courseName) throws Exception {
        Course course = null;
        String sql = "select * from courses where courseName =?";
        return this.queryForList(sql, courseName);
    }

    @Override
    public void insertCourse(Course course) throws Exception {
        String sql = "insert into courses(courseName,teacherNum,courseTime,coursePosition,weeks,facultyNum,credit) values(?,?,?,?,?,?,?)";
        this.executeUpdate(sql, course.getCourseName(), course.getTeacherNum(), course.getCourseTime(), course.getCoursePosition(), course.getWeeks(), course.getFacultyNum(), course.getCredit());
    }

    @Override
    public void deleteCourseByNum(Integer courseNum) throws Exception {
        String sql = "delete from courses where courseNum =?";
        this.executeUpdate(sql, courseNum);
    }

    @Override
    public Integer getCourseCount() throws Exception {
        String sql = "select count(*) from courses";
        return Integer.valueOf(this.queryForValue(sql).toString());
    }

    @Override
    public List<Course> getCourseList(int page) throws Exception {
        String sql = "select * from courses";
        List<Course> list = this.queryForList(sql);
        if(page*8>list.size()){
            return list.subList((page-1)*8, list.size());
        }
        return list.subList((page-1)*8,page*8);
    }

    @Override
    public void alterCourse(Course course) throws Exception {
        String sql = "update courses set courseName=? , teacherNum = ? , courseTime = ? , coursePosition = ? , weeks = ? , facultyNum = ? , credit = ? where courseNum = ?";
        this.executeUpdate(
                sql,
                course.getCourseName(),
                course.getTeacherNum(),
                course.getCourseTime(),
                course.getCoursePosition(),
                course.getWeeks(),
                course.getFacultyNum(),
                course.getCredit(),
                course.getCourseNum());
    }
}

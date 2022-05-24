package com.j2ee.edu_admi.dao;

import com.j2ee.edu_admi.beans.Course;
import com.j2ee.edu_admi.beans.Student;

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
        String sql = "select * from courses limit ?,?";
        return this.queryForList(sql, (page - 1) * 8, 8);
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

    @Override
    public List<Course> getSelectedCourseList(String username, int page) throws Exception {
//        String sql = "select * from courses where courseNum in (select courseNum from selectcourse where studentNum = ? ) limit ?,?";
        String sql = "    select courses.courseNum,courseName,teacherNum,courseTime,coursePosition,weeks,facultyNum,credit,score from courses,selectcourse where courses.courseNum = selectcourse.courseNum and  studentNum = ? limit ?,? ";
        StudentDao studentDao = new StudentDaoImpl();
        int studentNum = studentDao.getStudentByUsername(username).getStudentNum();

        return this.queryForList(sql, studentNum, (page - 1) * 8, 8);
    }
    public int getSelectedCourseListCount(String username) throws Exception{
        String sql = "select count(*) from courses where courseNum in (select courseNum from selectcourse where studentNum = ? )";
        StudentDao studentDao = new StudentDaoImpl();
        int studentNum = studentDao.getStudentByUsername(username).getStudentNum();

        return Integer.parseInt(this.queryForValue(sql, studentNum).toString());
    }

    @Override
    public void selectCourse(int courseNum, String username) throws Exception{
        String sql = "insert into selectcourse(courseNum,studentNum) values(?,?)";
        try {
            StudentDao studentDao = new StudentDaoImpl();
            Student student = studentDao.getStudentByUsername(username);
            this.executeUpdate(sql, courseNum, student.getStudentNum());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSelectCourse(int courseNum, String username) throws Exception {
        String sql = "delete from selectcourse where courseNum =? and studentNum =?";
        try {
            StudentDao studentDao = new StudentDaoImpl();
            Student student = studentDao.getStudentByUsername(username);
            this.executeUpdate(sql, courseNum, student.getStudentNum());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Course> getTeacherCourseList(String username, int page) throws Exception {

        String sql = "select * from courses where  teacherNum = ?  limit ?,?";

        TeacherDao teacherDao = new TeacherDaoImpl();
        int teacherNum = teacherDao.getStudentByUsername(username).getTeacherNum();

        return this.queryForList(sql, teacherNum, (page - 1) * 8, 8);

    }

    @Override
    public int getTeacherCourseListCount(String username) throws Exception {

        String sql = "select count(*) from courses where  teacherNum = ? ";

        TeacherDao teacherDao = new TeacherDaoImpl();
        int teacherNum = teacherDao.getStudentByUsername(username).getTeacherNum();

        return Integer.parseInt(this.queryForValue(sql, teacherNum).toString());

    }

    public void score(int courseNum,int studentNum,int score) throws Exception{
        String sql = "update selectcourse set score = ? where courseNum=? and studentNum = ?";
        this.executeUpdate(sql,score,courseNum,studentNum);
    }


}

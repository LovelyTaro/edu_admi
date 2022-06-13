package com.j2ee.edu_admi.servlet;

import com.j2ee.edu_admi.beans.Course;
import com.j2ee.edu_admi.dao.CourseDao;
import com.j2ee.edu_admi.dao.CourseDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 * @author Dcy
 */
@WebServlet(name = "CourseManageServlet", value = "/CourseManageServlet")
public class CourseManageServlet extends HttpServlet {

    //一页可以展示的最大课程数
    private static final int MAXLENGTH = 8;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("utf-8");

            //初始化
            CourseDao courseDao = new CourseDaoImpl();
            int number = 1;
            int page = 1;
            List<Course> courses = null;

            //TODO 根据get中传入的数据判断要进行什么操作
            if (request.getParameter("search") == null) {
                if (courseDao.getCourseCount()%MAXLENGTH == 0){
                    number = courseDao.getCourseCount() / MAXLENGTH;
                }else{
                    number = courseDao.getCourseCount() / MAXLENGTH + 1;
                }
                //如果页面请求了delete
                if (request.getParameter("delete") != null) {
                    courseDao.deleteCourseByNum(Integer.parseInt(request.getParameter("delete")));
                }
                //如果request里面没有page则page=1,如果page不能大于num
                if (request.getParameter("page") != null) {
                    page = Math.min(Integer.parseInt(request.getParameter("page")), number);
                }
                courses = courseDao.getCourseList(page);
            } else {
                courses = courseDao.getCourseByName(request.getParameter("search"));
            }
            request.setAttribute("courseList", courses);
            request.setAttribute("courseNum", number);
            request.setAttribute("page", page);
            getServletContext().getRequestDispatcher("/adm_coursemanage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //编辑课程或添加课程
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        CourseDao courseDao = new CourseDaoImpl();

        Course course = null;

        int courseNum;
        String courseName = request.getParameter("courseName");
        int teacherNum = Integer.parseInt(request.getParameter("teacherNum"));
        String courseTime = request.getParameter("courseTime");
        String coursePosition = request.getParameter("coursePosition");
        int weeks = Integer.parseInt(request.getParameter("weeks"));
        int facultyNum = Integer.parseInt(request.getParameter("facultyNum"));
        int credit = Integer.parseInt(request.getParameter("credit"));
        //如果courseNum存在说明信息是编辑课程传来的,直接将原数据删除,插入新数据
        if (request.getParameter("courseNum") != null) {
            try {
                courseNum = Integer.parseInt(request.getParameter("courseNum"));
                course = courseDao.getCourseByNum(courseNum);
                if (!course.getCourseName().equals(courseName)
                        || course.getTeacherNum() != teacherNum
                        || !course.getCourseTime().equals(courseTime)
                        || !course.getCoursePosition().equals(coursePosition)
                        || course.getWeeks() != weeks
                        || course.getFacultyNum() != facultyNum
                        || course.getCredit() != credit) {
                    courseDao.alterCourse(new Course(courseNum, courseName, teacherNum, courseTime, coursePosition, weeks, facultyNum, credit));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //else则说明信息是从添加课程传进来的
        } else {
            try {
                course = new Course(courseName, teacherNum, courseTime, coursePosition, weeks, facultyNum, credit);
                courseDao.insertCourse(course);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        doGet(request,response);
    }
}

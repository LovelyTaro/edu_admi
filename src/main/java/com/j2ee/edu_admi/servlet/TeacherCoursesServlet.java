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

@WebServlet(name = "TeacherCoursesServlet", value = "/TeacherCoursesServlet")
public class TeacherCoursesServlet extends HttpServlet {

    private static final int MAXLENGTH = 8;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        try {
            getCourses(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void getCourses(HttpServletRequest request, HttpServletResponse response) throws Exception{

        String username = (String) request.getSession().getAttribute("username");
        int count;
        int page = 1;
        //获取页数
        List<Course> list;
        CourseDao courseDao = new CourseDaoImpl();
        int courseCount = courseDao.getTeacherCourseListCount(username);
        if (courseCount % MAXLENGTH == 0) {
            count = courseCount / MAXLENGTH;
        } else {
            count = courseCount / MAXLENGTH + 1;
        }
        //如果page的信息不为空
        if (request.getParameter("page") != null) {
            page = Math.min(Integer.parseInt(request.getParameter("page")), count);
        }
        list = courseDao.getTeacherCourseList(username,page);
        //将所有信息填入request
        request.setAttribute("courseList", list);
        request.setAttribute("page", page);
        request.setAttribute("count", count);
        getServletContext().getRequestDispatcher("/tea_courses.jsp").forward(request, response);


    }
}

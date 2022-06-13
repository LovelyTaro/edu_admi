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
@WebServlet(name = "SelectCourseServlet", value = "/SelectCourseServlet")
public class SelectCourseServlet extends HttpServlet {

    private static final int MAXLENGTH = 8;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置返回类型为html 设置编码类型utf-8
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        try {
            //如果选课按钮被触发了，则执行选课
            if (request.getParameter("select") != null) {
                selectCourse(request, response);
            }
            //获取课程列表并转跳选课页面
            getCourseList(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    //获取所有课程列表（根据当前的page)
    private void getCourseList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int count;
        int page = 1;
        //获取页数
        List<Course> list;
        CourseDao courseDao = new CourseDaoImpl();
        int studentCount = courseDao.getCourseCount();
        if (studentCount % MAXLENGTH == 0) {
            count = studentCount / MAXLENGTH;
        } else {
            count = studentCount / MAXLENGTH + 1;
        }
        //如果page的信息不为空
        if (request.getParameter("page") != null) {
            page = Math.min(Integer.parseInt(request.getParameter("page")), count);
        }
        list = courseDao.getCourseList(page);
        //将所有信息填入request
        request.setAttribute("courseList", list);
        request.setAttribute("page", page);
        request.setAttribute("count", count);
        getServletContext().getRequestDispatcher("/stu_selectcourse.jsp").forward(request, response);
    }

    //选课
    private void selectCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int courseNum = Integer.parseInt(request.getParameter("select"));
        String username = (String) request.getSession().getAttribute("username");
        CourseDao courseDao = new CourseDaoImpl();
        courseDao.selectCourse(courseNum,username);
    }

}

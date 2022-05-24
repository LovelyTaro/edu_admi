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

@WebServlet(name = "ShowSelectedCourseServlet", value = "/ShowSelectedCourseServlet")
public class ShowSelectedCourseServlet extends HttpServlet {

    private static final int MAXLENGTH = 8;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置返回类型为html 设置编码类型utf-8
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        try {
            //如果发生推选，则执行退选
            if (request.getParameter("delete") != null) {
                deleteSelect(request,response);
            }
            showSelectCourse(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    //
    private void showSelectCourse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = (String) request.getSession().getAttribute("username");
        int count;
        int page = 1;
        //获取页数
        List<Course> list;
        CourseDao courseDao = new CourseDaoImpl();
        int studentCount = courseDao.getSelectedCourseListCount(username);
        if (studentCount % MAXLENGTH == 0) {
            count = studentCount / MAXLENGTH;
        } else {
            count = studentCount / MAXLENGTH + 1;
        }
        //如果page的信息不为空
        if (request.getParameter("page") != null) {
            page = Math.min(Integer.parseInt(request.getParameter("page")), count);
        }

        list = courseDao.getSelectedCourseList(username, page);
        //将所有信息填入request
        request.setAttribute("courseList", list);
        request.setAttribute("page", page);
        request.setAttribute("count", count);
        getServletContext().getRequestDispatcher("/stu_selectedcourse.jsp").forward(request, response);

    }

    private void deleteSelect(HttpServletRequest request, HttpServletResponse response) throws Exception{

        int courseNum = Integer.parseInt(request.getParameter("delete"));
        String username = (String) request.getSession().getAttribute("username");
        CourseDao courseDao = new CourseDaoImpl();
        courseDao.deleteSelectCourse(courseNum,username);

    }
}

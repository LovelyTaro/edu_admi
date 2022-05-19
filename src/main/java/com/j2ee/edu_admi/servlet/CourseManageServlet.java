package com.j2ee.edu_admi.servlet;

import com.j2ee.edu_admi.beans.Course;
import com.j2ee.edu_admi.beans.CourseManagementBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CourseManageServlet", value = "/CourseManageServlet")
public class CourseManageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //获取课程列表
            //翻页是通过变换向页面传输的page的数据来控制的
            CourseManagementBean courseManagement = new CourseManagementBean();
            List<Course> courses;
            int number = courseManagement.getPageNum();
            int page;
            //如果request里面没有page则page=1,如果page不能大于num
            if (request.getParameter("page") == null) {
                page = 1;
            } else if (Integer.parseInt(request.getParameter("page")) > number) {
                page = number;
            } else {
                page = Integer.parseInt(request.getParameter("page"));
            }
            courses = courseManagement.getCourseList(page);
            request.setAttribute("courseList", courses);
            request.setAttribute("courseNum", number);
            request.setAttribute("page", page);
            getServletContext().getRequestDispatcher("/course_management.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    //编辑课程或添加课程
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}

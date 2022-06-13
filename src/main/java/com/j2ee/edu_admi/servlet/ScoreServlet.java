package com.j2ee.edu_admi.servlet;

import com.google.gson.Gson;
import com.j2ee.edu_admi.beans.Student;
import com.j2ee.edu_admi.dao.CourseDao;
import com.j2ee.edu_admi.dao.CourseDaoImpl;
import com.j2ee.edu_admi.dao.StudentDao;
import com.j2ee.edu_admi.dao.StudentDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author Dcy
 */
@WebServlet(name = "ScoreServlet", value = "/ScoreServlet")
public class ScoreServlet extends HttpServlet {

    private static final int MAXLENGTH = 8;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        try {
            getStudentList(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/javascript;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        try {
            score(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getStudentList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int courseNum = 1;
        if (request.getParameter("courseNum") != null) {
            courseNum = Integer.parseInt(request.getParameter("courseNum"));
        }

        request.setAttribute("courseNum", courseNum);
        request.setAttribute("courseName", request.getParameter("courseName"));
        int count;
        int page = 1;
        //获取页数
        List<Student> list;
        StudentDao studentDao = new StudentDaoImpl();
        int studentCount = studentDao.getSelectedStudentCount(courseNum);
        if (studentCount % MAXLENGTH == 0) {
            count = studentCount / MAXLENGTH;
        } else {
            count = studentCount / MAXLENGTH + 1;
        }
        //如果page的信息不为空
        if (request.getParameter("page") != null) {
            page = Math.min(Integer.parseInt(request.getParameter("page")), count);
        }
        list = studentDao.getSelectedStudent(courseNum);
        //将所有信息填入request
        request.setAttribute("studentList", list);
        request.setAttribute("page", page);
        request.setAttribute("count", count);
        getServletContext().getRequestDispatcher("/tea_score.jsp").forward(request, response);
    }

    private void score(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int courseNum = Integer.parseInt(request.getParameter("courseNum"));
        int studentNum = Integer.parseInt(request.getParameter("studentNum"));
        int score = Integer.parseInt(request.getParameter("score"));
        CourseDao courseDao = new CourseDaoImpl();
        courseDao.score(courseNum, studentNum, score);
        PrintWriter out = response.getWriter();
        Map<String, Object> result = new HashMap<>();
        result.put("result", true);
        String json = new Gson().toJson(result);
        out.print(json);
    }
}

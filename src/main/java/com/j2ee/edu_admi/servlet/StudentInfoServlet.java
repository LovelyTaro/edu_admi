package com.j2ee.edu_admi.servlet;

import com.google.gson.Gson;
import com.j2ee.edu_admi.beans.Student;
import com.j2ee.edu_admi.beans.Teacher;
import com.j2ee.edu_admi.dao.StudentDao;
import com.j2ee.edu_admi.dao.StudentDaoImpl;
import com.j2ee.edu_admi.dao.TeacherDao;
import com.j2ee.edu_admi.dao.TeacherDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "StudentInfoServlet", value = "/StudentInfoServlet")
public class StudentInfoServlet extends HttpServlet {

    @Override
    //用来获取个人信息
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/javascript;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        if(request.getSession().getAttribute("id") == "stu"){
            getStudentInfo(request,response);
        }
        if(request.getSession().getAttribute("id") == "tea"){
            getTeacherInfo(request,response);
        }

    }

    //用来获取选课信息的
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private void getStudentInfo(HttpServletRequest request, HttpServletResponse response){

       String username = (String) request.getSession().getAttribute("username");

        try(PrintWriter out =response.getWriter()){
            StudentDao studentDao = new StudentDaoImpl();
            Student student = studentDao.getStudentByUsername(username);

            Map<String,Object> map = new HashMap<>();
            map.put("studentNum",student.getStudentNum());
            map.put("studentName",student.getStudentName());
            map.put("gender",student.getGender());
            map.put("faculty",student.getFacultyNum());
            map.put("birth",student.getBirth());
            map.put("userNum",student.getUserNum());
            map.put("username",username);

            String json = new Gson().toJson(map);
            out.print(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void getTeacherInfo(HttpServletRequest request, HttpServletResponse response){

        String username = (String) request.getSession().getAttribute("username");

        try(PrintWriter out =response.getWriter()){
            TeacherDao teacherDao = new TeacherDaoImpl();
            Teacher teacher = teacherDao.getStudentByUsername(username);

            Map<String,Object> map = new HashMap<>();
            map.put("teacherNum",teacher.getTeacherNum());
            map.put("teacherName",teacher.getTeacherName());
            map.put("gender",teacher.getGender());
            map.put("faculty",teacher.getFacultyNum());
            map.put("birth",teacher.getBirth());
            map.put("userNum",teacher.getUserNum());
            map.put("username",username);
            String json = new Gson().toJson(map);
            out.print(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

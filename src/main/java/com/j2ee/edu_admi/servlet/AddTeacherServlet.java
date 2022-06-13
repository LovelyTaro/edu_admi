package com.j2ee.edu_admi.servlet;

import com.google.gson.Gson;
import com.j2ee.edu_admi.dao.TeacherDao;
import com.j2ee.edu_admi.dao.TeacherDaoImpl;
import com.j2ee.edu_admi.dao.UserDao;
import com.j2ee.edu_admi.dao.UserDaoImpl;
import com.j2ee.edu_admi.beans.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author Dcy
 */
@WebServlet(name = "AddTeacherServlet", value = "/AddTeacherServlet")
public class AddTeacherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        searchTeacher(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/javascript;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        checkTeacher(request,response);
    }

    private void checkTeacher(HttpServletRequest request, HttpServletResponse response){

        String username = request.getParameter("username");

        UserDao userDao = new UserDaoImpl();
        try(PrintWriter out =response.getWriter()){
            if(userDao.queryUserNameExist(username)){
                Map<String,Object> result = new HashMap<>();
                result.put("exist",true);
                String json = new Gson().toJson(result);
                out.print(json);
            }
            else{
                addTeacher(request,response);
                Map<String,Object> result = new HashMap<>();
                result.put("exist",false);
                String json = new Gson().toJson(result);
                out.print(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addTeacher(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String teacherName = request.getParameter("teacherName");
            String gender = request.getParameter("gender");
            int facultyNum = Integer.parseInt(request.getParameter("facultyNum"));
            String birth = request.getParameter("birth");

            Teacher teacher = new Teacher(teacherName, gender, facultyNum, birth);
            teacher.setUsername(username);
            teacher.setPassword(password);

            TeacherDao teacherDao = new TeacherDaoImpl();
            teacherDao.insertTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error","addTeacherError");
        }

    }

    //查找学生---返回studentList，转到adm_studentmanage.jsp
    private void searchTeacher(HttpServletRequest request, HttpServletResponse response){
        try {
            String teacherName = request.getParameter("search");
            TeacherDao teacherDao = new TeacherDaoImpl();
            List<Teacher> teachers = new ArrayList<>();
            teachers.add(teacherDao.getTeacherByName(teacherName)) ;
            request.setAttribute("teacherList",teachers);
            getServletContext().getRequestDispatcher("/adm_teachermanage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


package com.j2ee.edu_admi.servlet;

import com.google.gson.Gson;
import com.j2ee.edu_admi.dao.StudentDao;
import com.j2ee.edu_admi.dao.StudentDaoImpl;
import com.j2ee.edu_admi.dao.UserDao;
import com.j2ee.edu_admi.dao.UserDaoImpl;
import com.j2ee.edu_admi.beans.Student;

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

@WebServlet(name = "AddStudentServlet", value = "/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        searchStudent(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/javascript;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        checkStudent(request,response);

    }

    //检查学生是否存在--ajax
    private void checkStudent(HttpServletRequest request, HttpServletResponse response){

        String username = request.getParameter("username");
        String birth = request.getParameter("birth");

        StudentDao studentDao = new StudentDaoImpl();
        UserDao userDao = new UserDaoImpl();
        try(PrintWriter out =response.getWriter()){
            if(userDao.queryUserNameExist(username)){
                Map<String,Object> result = new HashMap<>();
                result.put("exist",true);
                String json = new Gson().toJson(result);
                out.print(json);
            }
            else{
                addStudent(request,response);
                Map<String,Object> result = new HashMap<>();
                result.put("exist",false);
                String json = new Gson().toJson(result);
                out.print(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加学生--ajax
    private void addStudent(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String studentName = request.getParameter("studentName");
            String gender = request.getParameter("gender");
            int facultyNum = Integer.parseInt(request.getParameter("facultyNum"));
            String birth = request.getParameter("birth");

            Student student = new Student(studentName, gender, facultyNum, birth);
            student.setUsername(username);
            student.setPassword(password);

            StudentDao studentDao = new StudentDaoImpl();
            studentDao.insertStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error","addStudentError");
        }
    }

    //查找学生---返回studentList，转到adm_studentmanage.jsp
    private void searchStudent(HttpServletRequest request, HttpServletResponse response){
        try {
            String studentName = request.getParameter("search");
            StudentDao studentDao = new StudentDaoImpl();
            List<Student> students = new ArrayList<>();
            students.add(studentDao.getStudentByName(studentName)) ;
            request.setAttribute("studentList",students);
            getServletContext().getRequestDispatcher("/adm_studentmanage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.j2ee.edu_admi.servlet;

import com.j2ee.edu_admi.dao.StudentDao;
import com.j2ee.edu_admi.dao.StudentDaoImpl;
import com.j2ee.edu_admi.dao.TeacherDao;
import com.j2ee.edu_admi.dao.TeacherDaoImpl;
import com.j2ee.edu_admi.beans.Student;
import com.j2ee.edu_admi.beans.Teacher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Dcy
 * Date 2022.05.21
 */

@WebServlet(name = "UserManageServlet", value = "/UserManageServlet")
public class UserManageServlet extends HttpServlet {

    //一页可以展示的最大课程数
    private static final int MAXLENGTH = 8;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            //设置返回类型为html 设置编码类型utf-8
            response.setContentType("text/html;charset=utf-8");
            request.setCharacterEncoding("utf-8");
            //获取request中userType
            String userType = request.getParameter("userType");
            //userType为学生则处理学生信息
            if (userType.equals("Student")) {
                handleStudent(request, response);
            }
            //userType为教师，处理教师信息
            if (userType.equals("Teacher")) {
                handleTeacher(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        设置返回类型为html 设置编码类型utf-8
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        if(request.getParameter("studentName") != null){
            if(request.getParameter("studentNum") != null){
                updateStudent(request,response);
            }
            handleStudent(request,response);
        }
        if(request.getParameter("teacherName") != null){
            if(request.getParameter("teacherNum") != null){
                updateTeacher(request,response);
            }
            handleTeacher(request,response);
        }
    }

    //处理学生信息---此时request中userType=Student
    private void handleStudent(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 初始化所有信息
            //获取学生列表
            int count;
            int page = 1;
            //获取页数
            List<Student> list;
            StudentDao studentDao = new StudentDaoImpl();
            int studentCount =studentDao.getStudentCount();
            if (studentCount%MAXLENGTH == 0){
                count = studentCount / MAXLENGTH;
            }else{
                count = studentCount / MAXLENGTH + 1;
            }
            //如果page的信息不为空
            if (request.getParameter("page") != null) {
                page = Math.min(Integer.parseInt(request.getParameter("page")), count);
            }
            list = studentDao.getStudentList(page);
            //将所有信息填入request
            request.setAttribute("studentList", list);
            request.setAttribute("page", page);
            request.setAttribute("count", count);
            getServletContext().getRequestDispatcher("/adm_studentmanage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleTeacher(HttpServletRequest request, HttpServletResponse response) {
        try {
            // 初始化所有信息
            //获取学生列表
            List<Teacher> list;
            int count = 3;
            int page = 1;
            //获取页数
            TeacherDao teacherDao = new TeacherDaoImpl();
            int teacherCount = teacherDao.getTeacherCount();
            if (teacherCount%MAXLENGTH == 0){
                count = teacherCount / MAXLENGTH;
            }else{
                count = teacherCount / MAXLENGTH + 1;
            }
            //如果page的信息不为空
            if (request.getParameter("page") != null) {
                page = Math.min(Integer.parseInt(request.getParameter("page")), count);
            }
            list = teacherDao.getTeacherList(page);
            //将所有信息填入request
            request.setAttribute("teacherList", list);
            request.setAttribute("page", page);
            request.setAttribute("count", count);
            getServletContext().getRequestDispatcher("/adm_teachermanage.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTeacher(HttpServletRequest request, HttpServletResponse response){
        try {
            int teacherNum = Integer.parseInt(request.getParameter("teacherNum"));
            int userNum = Integer.parseInt(request.getParameter("userNum"));
            String teacherName = request.getParameter("teacherName");
            String gender = request.getParameter("gender");
            int facultyNum = Integer.parseInt(request.getParameter("facultyNum"));
            String birth = request.getParameter("birth");

            TeacherDao teacherDao = new TeacherDaoImpl();
            Teacher teacher = teacherDao.getTeacherByNum(teacherNum);

            if(!teacher.getTeacherName().equals(teacherName) ||
                    !teacher.getGender().equals(gender) ||
                    teacher.getFacultyNum() != facultyNum ||
                    !teacher.getBirth().equals(birth)
            ){
                Teacher newTeacher = new Teacher(teacherNum,userNum,teacherName,gender,facultyNum,birth);
                teacherDao.alterTeacher(newTeacher);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error","updateTeacherError");
        }

    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response){
        try {
            int studentNum = Integer.parseInt(request.getParameter("studentNum"));
            int userNum = Integer.parseInt(request.getParameter("userNum"));
            String studentName = request.getParameter("studentName");
            String gender = request.getParameter("gender");
            int facultyNum = Integer.parseInt(request.getParameter("facultyNum"));
            String birth = request.getParameter("birth");

            StudentDao studentDao = new StudentDaoImpl();
            Student student = studentDao.getStudentByNum(studentNum);

            if(!student.getStudentName().equals(studentName) ||
                    !student.getGender().equals(gender) ||
                    student.getFacultyNum() != facultyNum ||
                    !student.getBirth().equals(birth)
            ){
                Student newStudent = new Student(studentNum,userNum,studentName,gender,facultyNum,birth);
                studentDao.alterStudent(newStudent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error","updateStudentError");
        }
    }

}

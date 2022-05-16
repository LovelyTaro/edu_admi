package com.j2ee.edu_admi.servlet;

import com.j2ee.edu_admi.beans.LoginBean;
import com.j2ee.edu_admi.beans.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        //设置request的编码为utf8否则中文会乱码
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("usernameInfo");
        String password = request.getParameter("passwordInfo");
        String id = request.getParameter("idInfo");
        //创建用户对象
        User user = new User(username,password,id);

        try(PrintWriter out =response.getWriter()) {
            LoginBean loginBean = new LoginBean();
            if(loginBean.login(user)){

                //获取session
                request.getSession().setAttribute("LoginInfo",username);
                //登录成功跳转至home.html
                getServletContext().getRequestDispatcher("/home.jsp").forward(request,response);

            }else{
                //登录失败
                getServletContext().getRequestDispatcher("/test.jsp").forward(request,response);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

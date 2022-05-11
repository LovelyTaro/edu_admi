package com.j2ee.edu_admi.servlet;

import com.j2ee.edu_admi.beans.LoginBean;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //设置request的编码为utf8否则中文会乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("usernameInfo");
        String passwd = request.getParameter("passwdInfo");

        try {
            LoginBean loginBean = LoginBean.getInstance();
            if(loginBean.Login(username,passwd)){
                getServletContext().getRequestDispatcher("/home.html").forward(request,response);

            }else{
                getServletContext().getRequestDispatcher("/test.jsp").forward(request,response);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
package com.j2ee.edu_admi.servlet;

import com.google.gson.Gson;
import com.j2ee.edu_admi.beans.LoginBean;
import com.j2ee.edu_admi.beans.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dcy
 */

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("usernameInfo");
        String password = request.getParameter("passwordInfo");
        String id = request.getParameter("idInfo");
        //创建用户对象
        User user = new User(username, password, id);

        try(PrintWriter out =response.getWriter()) {
            LoginBean loginBean = new LoginBean();
            if(loginBean.register(user)){
                //获取session
                request.getSession().setAttribute("LoginInfo",username);
                //登录成功跳转至home.html
//                getServletContext().getRequestDispatcher("/home.jsp").forward(request,response);
                Map result = new HashMap();
                result.put("registerResult",true);
                String json = new Gson().toJson(result);
                out.print(json);

            }else{
                //登录失败
//                getServletContext().getRequestDispatcher("/test.jsp").forward(request,response);
                Map result = new HashMap();
                result.put("registerResult",false);
                String json = new Gson().toJson(result);
                out.print(json);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}

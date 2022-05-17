package com.j2ee.edu_admi.servlet;

import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CheckLoginServlet", value = "/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/javascript;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //检测当前session中LoginInfo是否存在,若存在则已登录，不存在则未登录
        if(request.getSession().getAttribute("LoginInfo") != null){
            Map<String,Object> result = new HashMap<String,Object>();
            result.put("isLogin", true);
            result.put("username",request.getSession().getAttribute("LoginInfo"));
            String json = new Gson().toJson(result);
            out.print(json);
        }else{
            Map<String,Object> result = new HashMap<String,Object>();
            result.put("isLogin", false);
            String json = new Gson().toJson(result);
            out.print(json);
        }



    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

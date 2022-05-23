package com.j2ee.edu_admi.servlet;

import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CheckLoginServlet", value = "/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/javascript;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        //检测当前session中LoginInfo是否存在,若存在则已登录，不存在则未登录
        if(request.getSession().getAttribute("username") != null){
            Map<String,Object> result = new HashMap<>();
            result.put("isLogin", true);
            result.put("username",request.getSession().getAttribute("LoginInfo"));
            String json = new Gson().toJson(result);
            out.print(json);
        }else{
            Map<String,Object> result = new HashMap<>();
            result.put("isLogin", false);
            String json = new Gson().toJson(result);
            out.print(json);
        }
    }
}

package com.j2ee.edu_admi.servlet;

import com.google.gson.Gson;
import com.j2ee.edu_admi.beans.LoginBean;

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

@WebServlet(name = "CheckNameAjaxServlet", value = "/CheckNameAjaxServlet")
public class CheckNameAjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/javascript;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        //获取传入的username
        String username = request.getParameter("username");
        try (PrintWriter out =response.getWriter()){
            //获取loginBean对象
            LoginBean loginBean = new LoginBean();
            Map<String,Object> result = new HashMap<>();
            //如果存在用户名则返回 exist：truer
            //如果不存在则返回 exist：false
            //返回值类型为 json
            if (loginBean.checkUserName(username)) {
                result.put("exist",true);
                String json = new Gson().toJson(result);
                out.print(json);
            } else {
                result.put("exist",false);
                String json = new Gson().toJson(result);
                out.print(json);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

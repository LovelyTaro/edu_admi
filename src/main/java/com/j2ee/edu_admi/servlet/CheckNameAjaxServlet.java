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

        String username = request.getParameter("username");

        try (PrintWriter out =response.getWriter()){
            LoginBean loginBean = new LoginBean();
            Map result = new HashMap();
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

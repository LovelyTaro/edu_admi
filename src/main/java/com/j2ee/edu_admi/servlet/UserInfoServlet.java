package com.j2ee.edu_admi.servlet;

import com.google.gson.Gson;
import com.j2ee.edu_admi.dao.UserDao;
import com.j2ee.edu_admi.dao.UserDaoImpl;
import com.j2ee.edu_admi.beans.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "UserInfoServlet", value = "/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/javascript;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        checkUser(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/javascript;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        submitChange(request,response);
    }


    private void checkUser(HttpServletRequest request, HttpServletResponse response) {
        int userNum = Integer.parseInt(request.getParameter("userNum"));
        UserDao userDao = new UserDaoImpl();
        try (PrintWriter out = response.getWriter()) {
            User user = userDao.getUserByNum(userNum);
            Map<String, Object> result = new HashMap<>();
            result.put("username", user.getUsername());
            result.put("password", user.getPassword());
            String json = new Gson().toJson(result);
            out.print(json);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void submitChange(HttpServletRequest request, HttpServletResponse response){

        int userNum = Integer.parseInt(request.getParameter("userNum"));
        String username =request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(userNum,username,password);
        UserDao userDao = new UserDaoImpl();
        try (PrintWriter out = response.getWriter()) {
            userDao.alterUser(user);
            Map<String, Object> result = new HashMap<>();
            result.put("result", true);
            String json = new Gson().toJson(result);
            out.print(json);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                PrintWriter out = response.getWriter();
                Map<String, Object> result = new HashMap<>();
                result.put("result", false);
                String json = new Gson().toJson(result);
                out.print(json);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

}

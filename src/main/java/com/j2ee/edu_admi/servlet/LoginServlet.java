package com.j2ee.edu_admi.servlet;

import com.google.gson.Gson;
import com.j2ee.edu_admi.dao.UserDao;
import com.j2ee.edu_admi.dao.UserDaoImpl;
import com.j2ee.edu_admi.beans.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dcy
 */

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    //对LoginServlet的get请求就是退出登录
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //退出登录
        response.setContentType("text/javascript;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("id");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/javascript;charset=utf-8");
        //设置request的编码为utf8否则中文会乱码
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("usernameInfo");
        String password = request.getParameter("passwordInfo");
        String id = request.getParameter("idInfo");

        //创建用户对象
        User user = new User(username,password,id);

        try(PrintWriter out =response.getWriter()) {
//            LoginBean loginBean = new LoginBean();
            UserDao userDao = new UserDaoImpl();
            if(userDao.queryUserExist(user)){
                //获取session
                request.getSession().setAttribute("username",username);
                request.getSession().setAttribute("id",user.getId());
                //返回loginResult：true表示登录成功
                Map<String,Object> result = new HashMap<>();
                result.put("loginResult",true);
                String json = new Gson().toJson(result);
                out.print(json);
            }else{
                //登录失败
                //返回loginResult：false表示登录失败
                Map<String,Object> result = new HashMap<>();
                result.put("loginResult",false);
                String json = new Gson().toJson(result);
                out.print(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

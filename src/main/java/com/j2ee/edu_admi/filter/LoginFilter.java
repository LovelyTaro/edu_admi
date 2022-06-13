package com.j2ee.edu_admi.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();

        if(requestURI.contains("login.html") || requestURI.contains("LoginServlet")){
            filterChain.doFilter(request, response);
            return;
        }
        HttpSession session = request.getSession();

        String username = String.valueOf(session.getAttribute("username"));
        if(username == null){
            request.setAttribute("error",true);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        if (username != "null"){
            filterChain.doFilter(request, response);
        }else{
            request.setAttribute("error",true);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    public void destroy() {
    }
}

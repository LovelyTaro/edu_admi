<%@ page import="java.util.List" %>
<%@ page import="com.j2ee.edu_admi.beans.User" %>
<%@ page import="com.j2ee.edu_admi.beans.Dao.*" %>
<%@ page import="com.j2ee.edu_admi.beans.Course" %>
<%@ page import="com.j2ee.edu_admi.beans.Student" %><%--
  Created by IntelliJ IDEA.
  User: 54684
  Date: 2022/5/11
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>

<body>
<div>

<%--    <%--%>
<%--        try {--%>
<%--            UserDao userDao = new UserDaoImpl();--%>
<%--            User user = userDao.getUser("Dcy");--%>
<%--//            userDao.deleteUserByUserNum(2);--%>

<%--    %>--%>

<%--    <p><%=user.getId()%></p>--%>
<%--    <p><%=user.getUsername()%></p>--%>
<%--    <p><%=user.getPassword()%></p>--%>
<%--    <p><%=userDao.getUserCount()%></p>--%>

<%--    <%--%>

<%--            } catch (Exception e) {--%>
<%--                e.printStackTrace();--%>
<%--            }--%>
<%--    %>--%>
    <%
        try {
            Student a = new Student();
            a.setUserNum(123);
            a.setPassword("1231231");
    %>

    <p><%=a.getUserNum()%></p>
    <p><%=a.getPassword()%></p>
<%--    <p><%=course.getCourseName()%></p>--%>
<%--    <p><%=course.getCoursePosition()%></p>--%>
<%--    <p><%=courseDao.getCourseCount()%></p>--%>

    <%

        } catch (Exception e) {
            e.printStackTrace();
        }
    %>

</div>
</body>
</html>

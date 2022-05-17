<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: 54684
  Date: 2022/5/17
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录状态</title>
    <script src="jquery-3.5.1/jquery-3.5.1.min.js"></script>
</head>
<body>
<div>
    <div>
        <h4>登陆状态</h4>
    </div>
    <%
        if(session.getAttribute("LoginInfo")!= null){

    %>
    <h5><%= "已登录" %></h5>
    <%
        }else{
    %>
    <h5><%= "未登录" %></h5>
    <%
        }
    %>
    <div>
        <input type="button" onclick="logout()" value="登出"/>
    </div>
</div>
</body>
</html>

<script>
    function logout(){
        $.ajax({
            url:'LoginServlet',
            type:'get',
            success: function (){
                    window.location.reload();
            }
        })

    }
</script>
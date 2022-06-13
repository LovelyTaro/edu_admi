<%--
  Created by IntelliJ IDEA.
  User: Dcy
  Date: 2022/5/24
  Time: 23:53
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="author" content="Dcy">
    <!-- jQuery -->
    <script src="jquery-3.5.1/jquery-3.5.1.js"></script>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js" integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="css/login.css">
    <script src="js/login.js"></script>

    <title>登录</title>
</head>

<style>
    .form-signin input[id='passwordInfo']{
        margin-bottom: 10px;
    }
</style>

<body>

<div class="container"><!-- BootStrap提供的登录示例 -->

    <form class="form-signin" id="loginForm" method="post" action="LoginServlet">
        <h2 class="form-signin-heading">登录</h2>
        <label for="usernameInfo" class="sr-only">用户名</label>
        <input type="text" id="usernameInfo" class="form-control" placeholder="请输入用户名" name="usernameInfo" onblur="checkUsernameIsEmpty()">
        <label for="passwordInfo" class="sr-only">密码</label>
        <input type="password" id="passwordInfo" class="form-control" name="passwordInfo" placeholder="请输入密码" onblur="checkPassword()">
        <!--身份选择菜单-->
        <div style="margin-bottom: 10px;font-size: 16px;">
            <span style="margin-right: 5px">请选择您的身份:</span>
            <select id="idInfo" name="idInfo">
                <option>学生</option>
                <option>老师</option>
                <option>管理员</option>
            </select>
        </div>

        <button class="btn btn-lg btn-primary btn-block" type="button" onclick="loginSubmit();">登录</button>
        <div>
            <label>
                <!--                <a href="register.html">没有账号，去注册</a>-->
            </label>
        </div>
    </form>
</div> <!-- /container -->

</body>
</html>
<script>
    $(document).ready(function () {
        <c:if test="${error == true}">
        alert("您还未登录，请登录后访问");
        </c:if>
    });
</script>

<%--
  Created by IntelliJ IDEA.
  User: Dcy
  Date: 2022/5/16
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>首页</title>

    <!--导入css-->
    <link rel="stylesheet" href="css/home.css" type="text/css" media="screen"/>

    <!-- 导入js-->
    <script type="text/javascript" src="jquery-3.5.1/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="js/home.js"></script>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
            integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
            crossorigin="anonymous"></script>
</head>
<body>
<!--顶部-->
<div class="layout_top_header">
    <div style="float: left">
        <span style="font-size: 16px;line-height: 45px;padding-left: 20px;color: #8d8d8d">教务管理系统</span>
    </div>
    <div id="ad_setting" class="ad_setting">
        <span class="ad_setting_a">
            <i class="icon-user glyph-icon" style="font-size: 20px"></i>
            <span>
                <%--通过c标签获得session中存的登录信息--%>
                <c:out value="${sessionScope.get('username')}" default="管理员"/>
            </span>
        </span>
        <span class="ad_setting_a2">
            <span><a style="color: #8d8d8d" href="javascript:logout();">退出</a></span>
        </span>
    </div>
</div>
<!--顶部结束-->
<!--菜单-->
<div class="layout_left_menu">
    <ul id="menu">
        <!--首页-->
        <li class="childUlLi">
            <a href="adm_home.jsp"><i class="glyph-icon icon-home"></i>首页</a>
            <ul></ul>
        </li>
        <!--课程管理-->
        <li class="childUlLi">
            <a href="CourseManageServlet"> <i class="glyph-icon icon-reorder"></i>课程管理</a>
            <ul>

            </ul>
        </li>
        <!--人员管理管理-->
        <li class="childUlLi">
            <a> <i class="glyph-icon icon-reorder"></i>人员管理</a>
            <ul>
                <li><a href="UserManageServlet?userType=Student"><i class="glyph-icon icon-chevron-right"></i>学生管理</a></li>
                <li><a href="UserManageServlet?userType=Teacher"><i class="glyph-icon icon-chevron-right"></i>教师管理</a></li>
            </ul>
        </li>
    </ul>
</div>
<!--菜单结束-->
<!--右边主体部分 -->
<div id="layout_right_content" class="layout_right_content">
    <!--右边主体部分的索引-->
    <div class="route_bg" id="content_index">
        <a href="adm_home.jsp">主页</a><i class="glyph-icon icon-chevron-right"></i>
    </div>
    <!--右边主体部分的索引结束-->
    <!--右边主体部分的内容-->
    <div class="main_content">
        <div class="container-fluid">
            <div class="jumbotron">
                <h1>你好 ,  <c:out value="${sessionScope.get('username')}" default="管理员"/>！</h1>
                <h2 style="margin-left: 50px">欢迎使用，教务管理系统！</h2>
                <br>
                <p><a class="btn btn-primary btn-lg" href="#" role="button">关于我们</a></p>
            </div>
        </div>
    </div>
    <!--右边主体部分的内容结束-->
</div>
<!--右边主体部分结束 -->
</div>

<!--底部-->
<div class="layout_footer">
    <p>Copyright © 董辰钰</p>
</div>
<!--底部结束-->
</body>
</html>

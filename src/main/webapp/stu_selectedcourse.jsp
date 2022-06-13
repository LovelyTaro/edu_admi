<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.j2ee.edu_admi.beans.Course" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Dcy
  Date: 2022/5/16
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>选课</title>

    <!--导入css-->
    <link rel="stylesheet" href="css/home.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css"/>

    <!-- 导入js-->
    <script type="text/javascript" src="jquery-3.5.1/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="js/home.js"></script>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
          integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous"/>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"
            integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd"
            crossorigin="anonymous"></script>
    <script src="js/bootstrap-datetimepicker.min.js"></script>
    <script src="js/bootstrap-datetimepicker.zh-CN.js"></script>
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
            <span><c:out value="${sessionScope.get('username')}" default="同学"/></span>
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
            <a href="stu_home.jsp"> <i class="glyph-icon icon-reorder"></i>个人信息</a>
            <ul>
            </ul>
        </li>
        <!--成员管理-->
        <li class="childUlLi">
            <a href="SelectCourseServlet"> <i class="glyph-icon icon-reorder"></i>选课</a>
            <ul>
            </ul>
        </li>
        <!--角色管理-->
        <li class="childUlLi">
            <a href="ShowSelectedCourseServlet"> <i class="glyph-icon icon-reorder"></i>课表</a>
            <ul>
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
        <a>课表</a>
    </div>
    <!--右边主体部分的索引结束-->
    <!--右边主体部分的内容-->
    <div class="main_content">
        <!--课程管理-->
        <div id="courseManagement" class="page_content">
            <div class="container-fluid">
                <div class="row" style="width: 100%;height: 10%;">
                    <div class=".col-md-6"
                         style="width: fit-content;display: inline-block;padding-right: 40px;margin-left: 50px">
                        <!--bootstrap提供的大标题-->
                        <div class="page-header">
                            <h1>课表 <small>Timetable</small></h1>
                        </div>
                    </div>
                </div>
                <div class="row" style="height: 75%;">
                    <!--信息展示-->
                    <div class="content_main">
                        <!--表格-用来展示信息-->
                        <div class="table_outside">
                            <!--bootstrap响应式的表格-->
                            <table class="table table-striped">
                                <!--表头-->
                                <thead>
                                <tr>
                                    <th>课程编号</th>
                                    <th>课程名称</th>
                                    <th>课程教师</th>
                                    <th>上课时间</th>
                                    <th>上课地点</th>
                                    <th>上课周数</th>
                                    <th>院系</th>
                                    <th>成绩</th>
                                    <th>学分</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%--根据后台后台传入的courseList动态生成当前表格--%>
                                <c:forEach var="course" items="${courseList}" begin="0" end="${courseList.size()}"
                                           step="1">
                                    <c:if test="${course.courseNum != 0}">
                                        <tr>
                                            <th scope="row"><c:out value="${course.courseNum}"/></th>
                                            <td><c:out value="${course.courseName}"/></td>
                                            <td><c:if test="${course.courseNum != 0}"><c:out
                                                    value="${course.teacherNum}"/></c:if></td>
                                            <td><c:out value="${course.courseTime}"/></td>
                                            <td><c:out value="${course.coursePosition}"/></td>
                                            <td><c:if test="${course.courseNum != 0}"><c:out
                                                    value="${course.weeks}"/></c:if></td>
                                            <td><c:out value="${course.facultyName}"/></td>

                                            <c:if test="${course.score != null}">
                                                <td>
                                                    <c:out value="${course.score}"/>
                                                </td>
                                            </c:if>
                                            <c:if test="${course.score == null}">
                                                <td>
                                                    未打分
                                                </td>
                                            </c:if>
                                            <td><c:if test="${course.courseNum != 0}"><c:out value="${course.credit}"/></c:if></td>
                                            <td>
                                                <button class="btn btn-default" id="select"
                                                        data-courseNum="${course.courseNum}"
                                                        onclick="deleteSelectButton(this)">退选
                                                </button>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!--表格结束-->
                    </div>
                    <!--信息展示结束-->
                </div>
                <div class="row" style="height: 10%;">
                    <!--分页-用来翻页-->
                    <div class="nav_outside">
                        <nav aria-label="...">
                            <ul class="pager">
                                <li><a href="javascript:pageForwardSelectedCourse();">向前</a></li>
                                <span style="margin-left: 10px;" id="pageNow">
                                <%--当前所在的页数--%>
                                <c:out value="${page}" default="1"/>
<%--                                ${page}--%>
                            </span>
                                <span>/</span>
                                <span style="margin-right: 10px;" id="pageAll">
                                <%--共有几页--%>
                                <c:out value="${count}" default="1"/>
                            </span>
                                <li><a href="javascript:pageBackwardSelectedCourse();">向后</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <!--课程管理结束-->
    </div>
    <!--右边主体部分的内容结束-->
</div>
<!--右边主体部分结束 -->
<!--底部-->
<div class="layout_footer">
    <p>Copyright © 董辰钰</p>
</div>
<!--底部结束-->
</body>
</html>
<script>
    function deleteSelectButton(e) {
        window.location.href = "ShowSelectedCourseServlet?page=${page}&delete=" + e.getAttribute("data-courseNum");
    }
</script>

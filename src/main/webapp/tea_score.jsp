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
            <span><c:out value="${sessionScope.get('username')}" default="老师"/></span>
        </span>
        <span class="ad_setting_a2">
            <span><a style="color: #8d8d8d" href="javascript:logout();">退出</a></span>
        </span>
    </div>
</div>
<!--顶部结束-->
<!--右边主体部分 -->
<div class="container-fluid">
    <div class="row" style="width: 100%;height: 10%;">
        <div class=".col-md-6"
             style="width: fit-content;display: inline-block;padding-right: 40px;margin-left: 50px">
            <!--bootstrap提供的大标题-->
            <div class="page-header">
                <button class="btn btn-default" style="display: inline-block;margin-right: 50px;margin-bottom: 10px" onclick="back()">返回</button>
                <div style="display: inline-block">
                    <h1>打分系统 <small>    课程：${courseName}</small></h1>
                </div>

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
                        <th>学生编号</th>
                        <th>学生姓名</th>
                        <th>性别</th>
                        <th>院系</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--根据后台后台传入的courseList动态生成当前表格--%>
                    <c:forEach var="student" items="${studentList}" begin="0" end="7" step="1">
                        <c:if test="${student.studentNum != 0}">
                            <tr>
                                <th scope="row"><c:out value="${student.studentNum}"/></th>
                                <td><c:out value="${student.studentName}"/></td>
                                <td><c:out value="${student.gender}"/></td>
                                <td><c:out value="${student.facultyName}"/></td>
                                <td>
                                    <c:if test="${student.score != null}">
                                        <input id="score" class="form-control" style="width: 50px" value="${student.score}"/>
                                    </c:if>
                                    <c:if test="${student.score == null}">
                                        <input id="score" class="form-control" style="width: 50px"/>
                                    </c:if>
                                    <button class="btn btn-default" data-studentNum="${student.studentNum}"
                                            >打分
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
    $('button').click(function (){
        let courseNum = ${courseNum};
        let studentNum = this.getAttribute("data-studentNum");
        let score = $(this).prev().val();
        console.log({courseNum,studentNum,score})
        $.ajax({
            url:'ScoreServlet',
            data:{courseNum:courseNum,studentNum:studentNum,score:score},
            dataType:'json',
            type:'post',
            success:function (data){
                if(data.result){
                    alert("打分成功");
                    window.location.href = "ScoreServlet?courseNum="+courseNum;
                }
            }
        });
    })

    function back(){
        window.location.href = "TeacherCoursesServlet";
    }

</script>
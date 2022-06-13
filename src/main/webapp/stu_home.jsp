<%--
  Created by IntelliJ IDEA.
  User: Dcy
  Date: 2022/5/16
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>首页</title>

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
    <div class="route_bg">
        <a>主页</a><i class="glyph-icon icon-chevron-right"></i>
    </div>

    <!--右边主题部分的内容-->
    <div class="main_content">
        <div id="page_content">
            <div class="container-fluid">
                <div class="row" style="width: 100%;height: 80px">
                    <!--bootstrap提供的大标题-->
                    <div class="page-header" style="margin-left: 50px">
                        <h1>个人信息 <small>Personal Information</small></h1>
                    </div>
                </div>
<%--                <div class="row" id="message-row" style="margin-top: 50px">--%>
<%--                    <form class="form-horizontal" style="height: 100%">--%>
<%--                        <div class="form-group">--%>
<%--                            <label for="studentNum" class="col-sm-2 control-label" >学号：</label>--%>
<%--                            <div class="col-sm-10">--%>
<%--                                <input type="text" class="form-control" id="studentNum"  readonly>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <label for="studentName" class="col-sm-2 control-label">姓名：</label>--%>
<%--                            <div class="col-sm-10">--%>
<%--                                <input type="text" class="form-control" id="studentName" readonly>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <label for="gender" class="col-sm-2 control-label">性别：</label>--%>
<%--                            <div class="col-sm-10">--%>
<%--                                <input type="text" class="form-control" id="gender" readonly>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <label for="faculty" class="col-sm-2 control-label">院系：</label>--%>
<%--                            <div class="col-sm-10">--%>
<%--                                <input type="text" class="form-control" id="faculty" readonly>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="form-group">--%>
<%--                            <label for="birth" class="col-sm-2 control-label">生日：</label>--%>
<%--                            <div class="col-sm-10">--%>
<%--                                <input type="text" class="form-control" id="birth" readonly>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </form>--%>
<%--                </div>--%>
<%--                <div style="">--%>
                <div class="col-" id="message-row" style="margin-top: 50px;width: 600px ;height: 100% ;float: left">
                    <form class="form-horizontal" style="margin-left: 100px; height: 100%">
                        <div class="row">
                            <label for="studentNum" class="col-sm-2 control-label" >工号：</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="studentNum"  readonly>
                            </div>
                        </div>
                        <div class="row">
                            <label for="studentName" class="col-sm-2 control-label">姓名：</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="studentName" readonly>
                            </div>
                        </div>
                        <div class="row">
                            <label for="gender" class="col-sm-2 control-label">性别：</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="gender" readonly>
                            </div>
                        </div>
                        <div class="row">
                            <label for="faculty" class="col-sm-2 control-label">院系：</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="faculty" readonly>
                            </div>
                        </div>
                        <div class="row">
                            <label for="birth" class="col-sm-2 control-label">生日：</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="birth" readonly>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-" style="width: 600px; float: right;height: 100%;margin-top:50px; margin-right: 300px">
                    <form class="form-horizontal" style="margin-left: 10px;margin-right: 20px; height: 100%">
                        <div class="row">
                            <label for="userNum" class="col-sm-2 control-label">编号：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control"  id="userNum" readonly>
                            </div>
                        </div>
                        <div class="row">
                            <label for="username" class="col-sm-2 control-label">用户名：</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="username" readonly>
                            </div>
                            <button type="button" class="form-control col-sm-2 edit" id="edit2" style="width: 80px">修改</button>
                        </div>
                        <div class="row">
                            <label for="password" class="col-sm-2 control-label">密码：</label>
                            <div class="col-sm-8">
                                <input type="password" class="form-control"  id="password" placeholder="请输入要修改后的密码" readonly>
                            </div>
                        </div>
                        <div class="row" style="width: 70%;margin:0 auto">
                            <button type="button" class="form-control btn-danger" id="submitChange" style="height: 34px;"> 保存</button>
                        </div>
                    </form>
                </div>
                </div>
            </div>
        </div>
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

<style>
    .form-group {
        width: 500px;
        padding-top: 10px;
    }

    .form-horizontal {
        margin-left: 100px;
    }

    .control-label{
        font-size: 16px;
    }
    .row{
        padding-top: 30px;
    }
</style>

<script>
    $(document).ready(function () {
        $.ajax({
            url:'StudentInfoServlet',
            type:'get',
            dataType:'json',
            success:function (data){
                console.log(data);
                $('#studentNum').val(data.studentNum);
                $('#studentName').val(data.studentName);
                $('#gender').val(data.gender);
                $('#faculty').val(data.faculty);
                $('#birth').val(data.birth);
                $('#userNum').val(data.userNum);
                $('#username').val(data.username);
            }
        })
    });

    $('.edit').click(function (){
        $('#username').attr('readonly',false);
        $('#password').attr('readonly',false);
    })

    $('#submitChange').click(function (){
        let username = $('#username').val();
        let password = $('#password').val();
        let userNum = $('#userNum').val();
        $.ajax({
            url: 'UserInfoServlet',
            type: 'post',
            data: {userNum: userNum, username: username, password: password},
            dataType: 'json',
            success: function (data) {
                if (data.result) {
                    alert("信息修改成功");
                    window.location.reload();
                }
            }
        });
    })
</script>

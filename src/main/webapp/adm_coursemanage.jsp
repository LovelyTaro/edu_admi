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
    <title>课程管理</title>

    <!--导入css-->
    <link rel="stylesheet" href="css/home.css" type="text/css" media="screen"/>

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
            <span><c:out value="${sessionScope.get('username')}" default="管理员"/></span>
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
        <!--成员管理-->
        <li class="childUlLi">
            <a href="CourseManageServlet"> <i class="glyph-icon icon-reorder"></i>课程管理</a>
            <ul></ul>
        </li>
        <!--角色管理-->
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
        <a>课程管理</a>
    </div>
    <!--右边主体部分的索引结束-->
    <!--右边主体部分的内容-->
    <div class="main_content">
        <!--课程管理-->
        <div id="courseManagement" class="page_content">
            <div class="container-fluid" style="height: 80%">
            <!--头部搜索栏-->
            <div class="content_top_wrap">
                <!--顶部的标题-->
                <div class="content_top content_top_title">
                    <!--bootstrap提供的大标题-->
                    <div class="page-header">
                        <h1>课程管理 <small>Course management</small></h1>
                    </div>
                </div>
                <div class="search">
                    <!--课程的搜索框-->
                    <div class="content_top content_top_search">
                        <div class="input-group">
                            <input type="text" class="form-control" id="search-input" placeholder="搜索课程"/>
                            <span class="input-group-btn">
                            <button class="btn btn-default" type="button" onclick="searchCourse()">搜索</button>
                        </span>
                        </div>
                    </div>
                    <!--添加课程按钮-->
                    <div class="content_top content_top_button">
                        <span class="content_top_button">
                            <button class="btn btn-default" type="button" data-toggle="modal" data-target="#addModal" >+添加课程</button>
                        </span>
                    </div>
                </div>
            </div>
            <!--头部搜索栏结束-->
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
                            <th>学分</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%--根据后台后台传入的courseList动态生成当前表格--%>
                        <c:forEach var="course" items="${courseList}" begin="0" end="${courseList.size()}" step="1">
                            <c:if test="${student.courseNum != 0}">
                                <tr>
                                    <th scope="row"><c:out value="${course.courseNum}"/></th>
                                    <td><c:out value="${course.courseName}"/></td>
                                    <td><c:if test="${course.courseNum != 0}"><c:out value="${course.teacherNum}"/></c:if></td>
                                    <td><c:out value="${course.courseTime}"/></td>
                                    <td><c:out value="${course.coursePosition}"/></td>
                                    <td><c:if test="${course.courseNum != 0}"><c:out value="${course.weeks}"/></c:if></td>
                                    <td><c:out value="${course.facultyName}"/></td>
                                    <td><c:if test="${course.courseNum != 0}"><c:out value="${course.credit}"/></c:if></td><td>
                                        <button class="btn btn-default" data-toggle="modal" data-target="#editModal"
                                                data-flag="${course.courseNum}">编辑
                                        </button>
                                        <button class="btn btn-default delete" id="delete" data-deleteNum="${course.courseNum}" onclick="deleteButton(this)">删除</button>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!--信息展示结束-->

                <!--分页-用来翻页-->
                <div class="nav_outside">
                    <nav aria-label="...">
                        <ul class="pager">
                            <li><a href="javascript:pageForward();">向前</a></li>
                            <span style="margin-left: 10px;" id="pageNow">
                                <%--当前所在的页数--%>
                                <c:out value="${page}" default="0"/>
<%--                                ${page}--%>
                            </span>
                            <span>/</span>
                            <span style="margin-right: 10px;" id="pageAll">
                                <%--共有几页--%>
                                <c:out value="${courseNum}" default="1"/>
                            </span>
                            <li><a href="javascript:pageBackward();">向后</a></li>
                        </ul>
                    </nav>
                </div>

                <!--编辑课程的弹出框-->
                <div id="editModel">
                    <div class="modal fade" id="editModal" tabindex="-1" role="dialog"
                         aria-labelledby="editModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <!-- 弹出框的标题 -->
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">×</span>
                                    </button>
                                    <h4 class="modal-title" id="editModalLabel">编辑课程</h4>
                                </div>
                                <div class="modal-body">
                                    <!-- 提交修改信息的表单-->
                                    <form action="CourseManageServlet" method="post" id="editCourse">
                                        <div class="form-group">
                                            <div class="form-title"> <label for="edit-courseNum" class="control-label">课程编号:</label></div>
                                            <div class="form-inside"><input type="number" class="form-control" name="courseNum" id="edit-courseNum" required readonly/></div>
                                        </div>
                                        <div class="form-group">
                                            <div class="form-title"> <label for="edit-courseName" class="control-label">课程名称:</label></div>
                                            <div class="form-inside"><input type="text" class="form-control" name="courseName" required id="edit-courseName"/></div>
                                        </div>
                                        <div class="form-group">
                                            <div class="form-title"> <label for="edit-teacherNum" class="control-label">课程教师:</label></div>
                                            <div class="form-inside"><input type="number" class="form-control" name="teacherNum" id="edit-teacherNum" required/></div>
                                        </div>
                                        <div class="form-group">
                                            <div class="form-title"><label for="edit-courseTime" class="control-label">上课时间:</label> </div>
                                            <div class="form-inside"><input type="text" class="form-control" name="courseTime" required
                                                                            id="edit-courseTime"/></div>
                                        </div>
                                        <div class="form-group">
                                            <div class="form-title"><label for="edit-coursePosition" class="control-label">上课地点:</label> </div>
                                            <div class="form-inside"><input type="text" class="form-control" name="coursePosition" required
                                                                            id="edit-coursePosition"/></div>
                                        </div>
                                        <div class="form-group">
                                            <div class="form-title"> <label for="edit-weeks" class="control-label">上课周数:</label></div>
                                            <div class="form-inside"><input type="number" class="form-control" name="weeks" required
                                                                            id="edit-weeks"/></div>
                                        </div>
                                        <div class="form-group">
                                            <div class="form-title"> <label for="edit-facultyNum" class="control-label">院系:</label></div>
                                            <div class="form-inside">
                                                <%--                                                <input type="number" class="form-control" name="facultyNum" required--%>
                                                <%--                                                                            id="edit-facultyNum"/>--%>
                                                <select id="edit-facultyNum" name="facultyNum"
                                                        class="form-control" style="width: 300px;height: 34px">
                                                    <option value="1">计算机系</option>
                                                    <option value="2">电子系</option>
                                                    <option value="3">自动化系</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="form-title"><label for="edit-credit" class="control-label">学分:</label> </div>
                                            <div class="form-inside"><input type="number" class="form-control" name="credit" required
                                                                            id="edit-credit"/></div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                                            </button>
                                            <button type="submit" class="btn btn-primary"
                                                    style="background: RGB(53,54,54)"
                                                    id="preservation">保存
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                   <!-- 课程编辑栏的弹出框的js -->
                    <script>
                        $('#editModal').on('show.bs.modal', function (event) {
                            let button = $(event.relatedTarget); // 触发事件的按钮
                            let recipient = button.data('flag');// 解析出按钮传入的data-flag内容
                            let modal = $(this);//获取当前的model--即弹出窗口
                            modal.find('.modal-title').text('编辑编号为：' + recipient + '的课程');//给弹出窗口设置标题
                            modal.find('#edit-courseNum').val(recipient);//通过recipient给第一行赋值
                            // 用jsp自带的foreach遍历request中存在的courseList，找到和按钮点击对应course
                            // 将当前课程现有的数据写入弹出框
                            <c:forEach var="student" items="${courseList}" begin="0" end="8" step="1">
                            if (recipient === <c:out value="${student.courseNum}"/>) {
                                modal.find('#edit-courseName').val('${student.courseName}');
                                modal.find('#edit-teacherNum').val(${student.teacherNum});
                                modal.find('#edit-courseTime').val('${student.courseTime}');
                                modal.find('#edit-coursePosition').val('${student.coursePosition}');
                                modal.find('#edit-weeks').val(${student.weeks});
                                modal.find('#edit-facultyNum').val(${student.facultyNum});
                                modal.find('#edit-credit').val(${student.credit});
                            } else {
                            }
                            </c:forEach>
                        });

                    </script>

                </div>
                <!--编辑课程的弹出框结束-->

                <!--添加课程的弹出框-->
                <div id="addModel">
                    <div class="modal fade" id="addModal" tabindex="-1" role="dialog"
                         aria-labelledby="editModalLabel">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <!-- 弹出框的标题 -->
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">×</span>
                                    </button>
                                    <h4 class="modal-title" id="addModalLabel">添加课程</h4>
                                </div>
                                <div class="modal-body">
                                    <!-- 提交修改信息的表单-->
                                    <form action="CourseManageServlet" method="post" id="addCourse" >
                                        <div class="form-group">
                                            <div class="form-title"> <label for="add-courseName" class="control-label">课程名称</label></div>
                                            <div class="form-inside"><input type="text" class="form-control" name="courseName" id="add-courseName" required/></div>
                                        </div>
                                        <div class="form-group">
                                            <div class="form-title"> <label for="add-teacherNum" class="control-label">课程教师:</label></div>
                                            <div class="form-inside"><input type="number" class="form-control" name="teacherNum" id="add-teacherNum" required/></div>
                                        </div>
                                        <div class="form-group">
                                            <div class="form-title"> <label for="add-time" class="control-label">上课时间:</label></div>
                                            <div class="form-inside"><input type="text" class="form-control" name="courseTime" id="add-time" required/></div>
                                        </div>
                                        <div class="form-group">
                                            <div class="form-title"> <label for="add-coursePosition" class="control-label">上课地点:</label></div>
                                            <div class="form-inside"><input type="text" class="form-control" name="coursePosition" id="add-coursePosition" required/></div>
                                        </div>
                                        <div class="form-group">
                                            <div class="form-title"> <label for="add-weeks" class="control-label">上课周数:</label></div>
                                            <div class="form-inside"><input type="number" class="form-control" name="weeks" id="add-weeks" required/></div>
                                        </div>
                                        <div class="form-group">
                                            <div class="form-title"><label for="add-facultyNum" class="control-label">院系:</label></div>
                                            <div class="form-inside">
                                                <select id="add-facultyNum" name="facultyNum"
                                                        class="form-control" style="width: 300px;height: 34px">
                                                    <option value="1">计算机系</option>
                                                    <option value="2">电子系</option>
                                                    <option value="3">自动化系</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <div class="form-title"><label for="add-credit" class="control-label">学分:</label></div>
                                            <div class="form-inside"><input type="number" class="form-control" name="credit" id="add-credit" required/></div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                            <button type="submit" class="btn btn-primary" style="background: RGB(53,54,54)" >保存</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- 添加课程的弹出框的js -->
                    <script>
                        $('#addModal').on('show.bs.modal', function (event) {
                            let button = $(event.relatedTarget); // 触发事件的按钮
                            let recipient = button.data('flag');// 解析出按钮传入的data-flag内容
                            let modal = $(this);//获取当前的model--即弹出窗口
                            modal.find('.modal-title').text('添加课程');//给弹出窗口设置标题
                        });

                    </script>
                </div>


            </div>
            <!--信息展示结束-->
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
    $(document).ready(function () {
       // if()
    });
</script>

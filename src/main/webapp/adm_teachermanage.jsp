<%@ page import="java.io.PrintWriter" %>
<%@ page import="com.j2ee.edu_admi.beans.Teacher" %>
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
    <title>教务管理系统</title>

    <!--导入css-->
    <link rel="stylesheet" href="css/home.css" type="text/css" media="screen"/>
    <link rel="stylesheet" href="css/bootstrap-datetimepicker.min.css" />

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
                <li><a href="UserManageServlet?userType=Student"><i class="glyph-icon icon-chevron-right"></i>学生管理</a>
                </li>
                <li><a href="UserManageServlet?userType=Teacher"><i class="glyph-icon icon-chevron-right"></i>教师管理</a>
                </li>
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
        <a>人员管理</a><i class="glyph-icon icon-chevron-right"></i>
        <a>教师管理</a>
    </div>
    <!--右边主体部分的索引结束-->
    <!--右边主体部分的内容-->
    <div class="main_content">
        <!--教师管理-->
        <div id="teacherManagement" class="page_content">
            <div class="container-fluid">
                <!--标题和搜索框-->
                <div class="row" style="width: 100%;height: 10%;">
                    <div class=".col-md-6"
                         style="width: fit-content;display: inline-block;padding-right: 40px;margin-left: 50px">
                        <%--                        <div class="content_top content_top_title">--%>
                        <!--bootstrap提供的大标题-->
                        <div class="page-header">
                            <h1>教师信息管理 <small>Teacher Information Management</small></h1>
                        </div>
                        <%--                        </div>--%>
                    </div>
                    <div class=".col-md-6"
                         style="width: fit-content;display: inline-block;float: right;margin-top: 50px;margin-right: 35px">
                        <%--                        <div class="search">--%>
                        <!--教师的搜索框-->
                        <div class="content_top content_top_search">
                            <div class="input-group">
                                <input type="text" class="form-control" id="search-input" placeholder="搜索教师"/>
                                <span class="input-group-btn">
                            <button class="btn btn-default" type="button" onclick="searchTeacher()">搜索</button>
                        </span>
                            </div>
                        </div>
                        <!--添加教师按钮-->
                        <div class="content_top content_top_button">
                        <span class="content_top_button">
                            <button class="btn btn-default" type="button" data-toggle="modal" data-target="#addModal">+添加教师</button>
                        </span>
                        </div>
                    </div>
                </div>
                <!--展示信息的表格-->
                <div class="row" style="height: 75%;">
                    <!--信息展示-->
                    <div class="content_main">
                        <!--表格-用来展示信息-->
                        <div class="table_outside">
                            <!--bootstrap响应式的表格-->
                            <table class="table table-striped">
                                <%--如果是学生--%>
                                <c:if test="${teacherList != null}">
                                <!--表头-->
                                <thead>
                                <tr>
                                    <th>教师编号</th>
                                    <th>用户编号</th>
                                    <th>教师姓名</th>
                                    <th>性别</th>
                                    <th>院系</th>
                                    <th>生日</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <%--根据后台后台传入的teacherList动态生成当前表格--%>
                                <c:forEach var="teacher" items="${teacherList}" begin="0" end="${teacherList.size()}" step="1">
                                    <c:if test="${teacher.teacherNum != 0}">
                                        <tr>
                                            <th scope="row"><c:out value="${teacher.teacherNum}"/></th>
                                            <td><c:out value="${teacher.userNum}"/></td>
                                            <td><c:out value="${teacher.teacherName}"/></td>
                                            <td><c:out value="${teacher.gender}"/></td>
                                            <td><c:out value="${teacher.facultyName}"/></td>
                                            <td><c:out value="${teacher.birth}"/></td>
                                            <td>
                                                <button class="btn btn-default" data-toggle="modal"
                                                        data-target="#editModal"
                                                        data-flag="${teacher.teacherNum}">编辑
                                                </button>
                                                <button class="btn btn-default" data-toggle="modal"
                                                        data-target="#userModal"
                                                        data-flag="${teacher.userNum}" onclick="">查看账户
                                                </button>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!--信息展示结束-->
                        <!--编辑教师的弹出框-->
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
                                            <h4 class="modal-title" id="editModalLabel">教师学生</h4>
                                        </div>
                                        <div class="modal-body">
                                            <!-- 提交修改信息的表单-->
                                            <form action="UserManageServlet" method="post" id="editTeacher">
                                                <div class="form-group">
                                                    <div class="form-title"><label for="edit-teacherNum" class="control-label">教师编号:</label></div>
                                                    <div class="form-inside"><input type="number" class="form-control" name="teacherNum"
                                                                                    id="edit-teacherNum" required
                                                                                    readonly/></div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="form-title"><label for="edit-userNum" class="control-label">用户编号：</label></div>
                                                    <div class="form-inside"><input type="number" class="form-control" name="userNum" required readonly
                                                                                    id="edit-userNum"/></div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="form-title"><label for="edit-teacherName" class="control-label">教师姓名:</label></div>
                                                    <div class="form-inside"><input type="text" class="form-control" name="teacherName"
                                                                                    id="edit-teacherName" required/></div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="form-title"><label for="edit-gender"
                                                                                   class="control-label">性别</label>
                                                    </div>
                                                    <div class="form-inside"><select id="edit-gender" name="gender"
                                                                                     class="form-control"
                                                                                     style="width: 300px;height: 34px">
                                                        <option>女</option>
                                                        <option>男</option>
                                                    </select></div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="form-title"><label for="edit-facultyNum" class="control-label">院系编号</label></div>
                                                    <div class="form-inside"><select id="edit-facultyNum" name="facultyNum"
                                                                                     class="form-control" style="width: 300px;height: 34px">
                                                        <option value="1">计算机系</option>
                                                        <option value="2">电子系</option>
                                                        <option value="3">自动化系</option>
                                                    </select></div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="form-title"><label for="edit-birth" class="control-label">生日</label></div>
                                                    <div class="form-inside"><input type="text" class="form-control" name="birth" required
                                                                                    id="edit-birth"/></div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        关闭
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
                            <!-- 教师编辑栏的弹出框的js -->

                        </div>
                        <!--编辑教师的弹出框结束-->
                        </c:if>
                        <!--添加教师的弹出框-->
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
                                            <h4 class="modal-title" id="addModalLabel">添加教师</h4>
                                        </div>
                                        <div class="modal-body">
                                            <!-- 提交修改信息的表单-->
                                            <form action="UserManageServlet" method="post" id="addTeacher">
                                                <div class="form-group">
                                                    <div class="form-title"><label for="add-username" class="control-label">用户名：</label></div>
                                                    <div class="form-inside"><input type="text" class="form-control" name="username"
                                                                                    id="add-username" required/></div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="form-title"><label for="add-password" class="control-label">密码：</label></div>
                                                    <div class="form-inside"><input type="text" class="form-control" name="password"
                                                                                    id="add-password" required/></div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="form-title"><label for="add-teacherName" class="control-label">教师姓名</label></div>
                                                    <div class="form-inside"><input type="text" class="form-control" name="teacherName"
                                                                                    id="add-teacherName" required/></div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="form-title"><label for="add-gender" class="control-label">性别</label>
                                                        </div>
                                                    <div class="form-inside"><select id="add-gender" name="gender" class="form-control"
                                                                                     style="width: 300px;height: 34px">
                                                        <option>女</option>
                                                        <option>男</option>
                                                    </select></div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="form-title"><label for="add-facultyNum" class="control-label">院系编号</label></div>
                                                    <div class="form-inside"><select id="add-facultyNum" name="facultyNum"
                                                                                     class="form-control" style="width: 300px;height: 34px">
                                                        <option value="1">计算机系</option>
                                                        <option value="2">电子系</option>
                                                        <option value="3">自动化系</option>
                                                    </select></div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="form-title"><label for="add-birth" class="control-label">生日</label></div>
                                                    <div class="form-inside"><input type="text" class="form-control"  name="birth" required
                                                                                    id="add-birth"/></div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        关闭
                                                    </button>
                                                    <button type="button" class="btn btn-primary"
                                                            style="background: RGB(53,54,54)" onclick="addTeacher()">保存
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- 添加教师的弹出框的js -->

                        </div>
                        <!--添加教师的弹出框结束-->

                        <!--查看账户的弹出框-->
                        <div id="userModel">
                            <div class="modal fade" id="userModal" tabindex="-1" role="dialog"
                                 aria-labelledby="editModalLabel">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <!-- 弹出框的标题 -->
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">×</span>
                                            </button>
                                            <h4 class="modal-title" id="userModalLabel">账户信息</h4>
                                        </div>
                                        <div class="modal-body">
                                            <!-- 提交修改信息的表单-->
                                            <form>
                                                <div class="form-group">

                                                    <%--//TODO 修改！！--%>
                                                    <div class="form-title">
                                                        <label for="user-userNum" class="control-label">用户编号：</label>
                                                    </div>
                                                    <div class="form-inside">
                                                        <input type="text" class="form-control" name="userNum"
                                                               id="user-userNum" style="width: 300px" readonly/>
                                                    </div>
                                                    <div class="form-bottom">
                                                        <button type="button" class="btn btn-default" style="visibility: hidden"
                                                                onclick="editUserName()">
                                                            修改
                                                        </button>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="form-title">
                                                        <label for="user-userName" class="control-label">用户名：</label>
                                                    </div>
                                                    <div class="form-inside">
                                                        <input type="text" class="form-control" name="username"
                                                               id="user-userName" style="width: 300px" readonly
                                                               required/>
                                                    </div>
                                                    <div class="form-bottom">
                                                        <button type="button" class="btn btn-default"
                                                                onclick="editUserName()">
                                                            修改
                                                        </button>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <div class="form-title">
                                                        <label for="user-password" class="control-label">密码：</label>
                                                    </div>
                                                    <div class="form-inside"><input type="text" class="form-control"
                                                                                    name="password" id="user-password"
                                                                                    style="width: 300px" readonly
                                                                                    required/>
                                                    </div>
                                                    <div class="form-bottom">
                                                        <button type="button" class="btn btn-default"
                                                                onclick="editUserPassword()">
                                                            修改
                                                        </button>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        关闭
                                                    </button>
                                                    <button type="button" class="btn btn-primary" id="submitButton"
                                                            style="background: RGB(53,54,54);display: none"
                                                            onclick="submitUserChange()">保存
                                                    </button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--查看账户的弹出框结束-->

                    </div>
                    <!--信息展示结束-->
                </div>
                <!--底部翻页按钮-->
                <div class="row" style="height: 10%;">
                    <!--分页-用来翻页-->
                    <div class="nav_outside">
                        <nav aria-label="...">
                            <ul class="pager">
                                <li><a href="javascript:pageForwardTeacher();">向前</a></li>
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
                                <li><a href="javascript:pageBackwardTeacher();">向后</a></li>
                            </ul>
                        </nav>
                    </div>
                </div>
            </div>
        </div>
        <!--教师管理结束-->
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

    $('#editModal').on('show.bs.modal', function (event) {
        let button = $(event.relatedTarget); // 触发事件的按钮
        let recipient = button.data('flag');// 解析出按钮传入的data-flag内容
        let modal = $(this);//获取当前的model--即弹出窗口
        modal.find('.modal-title').text('添加教师');//给弹出窗口设置标题
        $('#add-birth').datetimepicker({
            forceParse:0,
            language:'zh-CN',
            format:"yyyy-mm-dd",
            minView:'month',
            initialDate:new Date(),
            autoclose:true,
            todayBtn:false
        });
    });


    $('#editModal').on('show.bs.modal', function (event) {
        let button = $(event.relatedTarget); // 触发事件的按钮
        let recipient = button.data('flag');// 解析出按钮传入的data-flag内容
        let modal = $(this);//获取当前的model--即弹出窗口
        // 用jsp自带的foreach遍历request中存在的teacherList，找到和按钮点击对应teacher
        // 将当前教师现有的数据写入弹出框
        <c:forEach var="teacher" items="${teacherList}" begin="0" end="${teacherList.size()}" step="1">
        if (recipient === ${teacher.teacherNum}) {
            modal.find('.modal-title').text('编辑编号为：' + ${teacher.teacherNum} + '的老师');//给弹出窗口设置标题
            modal.find('#edit-teacherNum').val(${teacher.teacherNum});//通过recipient给第一行赋值
            modal.find('#edit-userNum').val(${teacher.userNum});
            modal.find('#edit-teacherName').val('${teacher.teacherName}');
            modal.find('#edit-gender').val('${teacher.gender}');
            modal.find('#edit-facultyNum').val('${teacher.facultyNum}');
            modal.find('#edit-birth').val('${teacher.birth}');
        } else {
        }
        </c:forEach>
        $('#edit-birth').datetimepicker({
            forceParse:0,
            language:'zh-CN',
            format:"yyyy-mm-dd",
            minView:'month',
            initialDate:new Date(),
            autoclose:true,
            todayBtn:false
        });
    });

    $('#userModal').on('show.bs.modal', function (event) {
        let button = $(event.relatedTarget); // 触发事件的按钮
        let userNum = button.data('flag');// 解析出按钮传入的data-flag内容
        let modal = $(this);//获取当前的model--即弹出窗口
        modal.find('.modal-title').text('账户信息');//给弹出窗口设置标题
        modal.find('#user-userNum').val(userNum);
        $.ajax({
            url: 'UserInfoServlet',
            type: 'get',
            data: {userNum: userNum},
            dataType: 'json',
            success: function (data) {
                console.log(data.username);

                modal.find('#user-userName').val(data.username);
                modal.find('#user-password').val(data.password);
            }
        });
    });

    function editUserName() {
        $('#user-userName').attr('readonly', false);
        $('#submitButton').show();
    }

    function editUserPassword() {
        $('#user-password').attr('readonly', false);
        $('#submitButton').show();
    }

    $('#userModal').on('hide.bs.modal', function () {
        $('#user-userName').attr('readonly', true);
        $('#user-password').attr('readonly', true);
        $('#submitButton').hide();
    });


    function submitUserChange() {
        let userNum = $('#user-userNum').val();
        let username = $('#user-userName').val();
        let password = $('#user-password').val();

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
    }
</script>

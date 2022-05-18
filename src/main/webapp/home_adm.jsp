<%--
  Created by IntelliJ IDEA.
  User: Dcy
  Date: 2022/5/16
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>教务管理系统</title>

    <!--导入css-->
    <link rel="stylesheet" href="css/index.css" type="text/css" media="screen"/>

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
        <a class="ad_setting_a" href="javascript:; ">
            <i class="icon-user glyph-icon" style="font-size: 20px"></i>
            <span>管理员</span>
            <i class="icon-chevron-down glyph-icon"></i>
        </a>
        <ul class="dropdown-menu-uu" style="display: none" id="ad_setting_ul">
            <li class="ad_setting_ul_li">
                <a href="javascript:logout();">
                    <i class="icon-signout glyph-icon"></i>
                    <span class="font-bold">退出</span>
                </a>
            </li>
        </ul>
    </div>
</div>
<!--顶部结束-->

<!--菜单-->
<div class="layout_left_menu">
    <ul id="menu">
        <!--首页-->
        <li class="childUlLi">
            <a href="home_adm.jsp"><i class="glyph-icon icon-home"></i>首页</a>
            <ul>

            </ul>
        </li>

        <!--成员管理-->
        <li class="childUlLi">
            <a> <i class="glyph-icon icon-reorder"></i>课程管理</a>
            <ul>
                <%--                <li><a href="#"><i class="glyph-icon icon-chevron-right"></i>后台菜单管理</a></li>--%>
                <%--                <li><a href="#"><i class="glyph-icon icon-chevron-right"></i>展示商品管理</a></li>--%>
                <%--                <li><a href="#"><i class="glyph-icon icon-chevron-right"></i>数据管理</a></li>--%>
            </ul>
        </li>
        <!--角色管理-->
        <li class="childUlLi">
            <a> <i class="glyph-icon icon-reorder"></i>人员管理</a>
            <ul>
                <%--                <li><a href="#"><i class="glyph-icon icon-chevron-right"></i>修改密码</a></li>--%>
                <%--                <li><a href="#"><i class="glyph-icon icon-chevron-right"></i>帮助</a></li>--%>
            </ul>
        </li>
        <!--菜单管理-->
        <li class="childUlLi">
            <a> <i class="glyph-icon  icon-location-arrow"></i>账号密码管理</a>
            <ul>
                <%--                <li><a href="meunbox.html" target="menuFrame"><i class="glyph-icon icon-chevron-right"></i>平台菜单</a></li>--%>
                <%--                <li><a href="meunbox_add.html" target="menuFrame"><i class="glyph-icon icon-chevron-right"></i>运行商菜单</a>--%>
                <%--                </li>--%>
                <%--                <li><a href="#" target="menuFrame"><i class="glyph-icon icon-chevron-right"></i>服务站菜单</a></li>--%>
                <%--                <li><a href="#" target="menuFrame"><i class="glyph-icon icon-chevron-right"></i>商家菜单</a></li>--%>
            </ul>
        </li>
    </ul>
</div>
<!--菜单结束-->

<!--右边主体部分 -->
<div id="layout_right_content" class="layout_right_content">

    <!--右边主体部分的索引-->
    <div class="route_bg" id="content_index">
        <a href="#">主页</a><i class="glyph-icon icon-chevron-right"></i>
        <a href="#">菜单管理</a>
    </div>

    <!--右边主题部分的内容-->
    <div class="main_content">
        <div id="page_content_courseManagement" class="page_content">
            <%--头部搜索栏--%>
            <div class="content_top_wrap">
                <%--顶部的标题--%>
                <div class="content_top content_top_title">
                    <h1 id="course">课程管理</h1>
                </div>
                <%--bootstrap的搜索框--%>
                <div class="content_top content_top_search">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="搜索课程">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">搜索</button>
                        </span>
                    </div>
                </div>
                <%--添加课程按钮--%>
                <div class="content_top content_top_button">
                        <span class="content_top_button">
                            <button class="btn btn-default" type="button">添加课程</button>
                        </span>
                </div>
            </div>
            <%--信息展示的表格--%>
            <div>

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

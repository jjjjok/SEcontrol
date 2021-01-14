<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2020/12/27
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>找回密码</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css1/login.css"/>
    <script src="${pageContext.request.contextPath}/js/forget.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <style>
        .reg p .empty{
            display: inline-block;
            background-color: #FFFFFF;
            color: #CC0000;
            font-size: 10px;
        }
    </style>
</head>
<body><!-------------------reg-------------------------->
<header class = "container">
    <nav class="header_1">
        <ul style="z-index: 9999; position: relative">
            <li><a href="#" style="text-indent:-15px">中国大陆<i class="fas fa-angle-down" style="float: left;margin-left: 67px; margin-top:-22px;"></i></a>
                <ul style="width: auto; height: 250px; overflow: scroll">

                </ul>
            </li>
            <li><a href="${pageContext.request.contextPath}/user/tologin" style="color: red" >叮，请登录…</a></li>
        </ul>
    </nav>
    <div class="header_2">
        <ul class="header_2_1">
            <li class="header_2_1_1">
                <a href="${pageContext.request.contextPath}/user/myorder">我的口袋</a>
            </li>
            <li class="header_2_1_2">
                <a href="${pageContext.request.contextPath}/item/collectshow">购物车</a>
            </li>
            <li class="header_2_1_3">
                <a href="${pageContext.request.contextPath}/item/tousersell" >我要出售</a>
            </li>

            <li class="header_2_1_5">
                <a href="${pageContext.request.contextPath}/item/preselectallitem">首页</a>
            </li>
        </ul>
    </div>
</header>
<div class="logo">
    <div  class = logo_1>
        <img src="${pageContext.request.contextPath}/img/logo.png" width="70" height="70" style = vertical-align:sub />
        <span style = font-size:35px>口袋网</span>
        <span>找回密码</span>
    </div>
</div>
<div class="reg">
    <form action="${pageContext.request.contextPath}/user/forget"  method="post" onsubmit="return CheckForm(this)">
        <p>找回密码</p>

        <p><input type="text" name="username" value="" id="username" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入用户名"><span class="empty"></span></p>
        <p><input type="text" name="userphone" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入注册时使用的手机号"><span class="empty"></span></p>
        <p><input type="text" name="userpwd" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入新密码"><span class="empty"></span></p>
        <p><input type="text" name="userpwd1" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请确认新密码"><span class="empty"></span></p>

        <p><input type="submit" name="" value="确认"></p>
        <p class="txt"><a href="#"><span></span>已有账号登录</a></p>
        </form>
</div>

</body>
</html>
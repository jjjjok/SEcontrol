<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2020/12/27
  Time: 14:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css1/login.css"/>
    <script src="${pageContext.request.contextPath}/js/function.js"></script>
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

            </li>

        </ul>
    </nav>
    <div class="header_2">
        <ul class="header_2_1">
            <li class="header_2_1_4">
                <a href="${pageContext.request.contextPath}/item/preselectallitem">首页</a>
            </li>
            <li class="header_2_1_5">
                <a href="${pageContext.request.contextPath}/usermanager/tologin">管理员登录</a>
            </li>
        </ul>
    </div>
</header>
<div class="logo">
    <div  class = logo_1>
        <img src="${pageContext.request.contextPath}/img/logo.png" width="95" height="95" style = vertical-align:sub />
        <span style = font-size:50px>口袋网</span>
        <span>注册</span>
    </div>
</div>
<div class="reg">

        <form action="${pageContext.request.contextPath}/user/register" method="post" onsubmit="return CheckForm(this)"><h1><a href="index.html"></a></h1>
            <p>用户注册</p>
            <p><input type="text" name="username" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入用户名"><span class="empty"></span></p>
            <p><input type="text" name="userphone" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入手机号"><span class="empty"></span></p>
            <p><input type="text" name="pay" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入支付宝账户，用于收款"><span class="empty"></span></p>
            <p><input type="text" name="userpwd" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入密码"><span class="empty"></span></p>
            <p><input type="text" name="userpwd1" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请确认密码"><span class="empty"></span></p>
            <p><input type="submit" name="" value="注册" ></p>

        <p class="txt"><a href="${pageContext.request.contextPath}/user/tologin"><span></span>已有账号登录</a></p>
</form>

</div>
<div id="footer">
    <p class="dibu">
        Copyright  ©2020-2022  哆啦A梦的口袋网版权所有	浙江省网络食品销售第三方平台提供者备案：浙网食A33010001<br/>
        出版物网络交易平台服务经营备案号：新出发浙备字第002号	市场名称登记证：工商网市字3301004120号
    </p>
</div>
</body>
</html>
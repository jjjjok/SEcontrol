<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>
    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/function.js"></script>
    <script src="${pageContext.request.contextPath}/js/verycode.js"></script>

    <style>
        .reg p .empty{
            display: inline-block;
            border: 1px solid #CC0000;
            background-color: #FFFFFF;
        }
    </style>

</head>
<body><!-------------------reg-------------------------->
<div class="reg">
    <form action="${pageContext.request.contextPath}/user/register" method="post" onsubmit="return CheckForm(this)"><h1><a href="index.html"></a></h1>
        <p>用户注册</p>
        <p><input type="text" name="username" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入用户名"><span class="empty"></span></p>
        <p><input type="text" name="userphone" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入手机号"><span class="empty"></span></p>
        <p><input type="text" name="userpwd" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请输入密码"><span class="empty"></span></p>
        <p><input type="text" name="userpwd1" value="" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="请确认密码"><span class="empty"></span></p>
        <p><input type="submit" name="" value="注册" ></p>

        <p class="txt"><a href="#"><span></span>已有账号登录</a></p>
        <!--<a href="#" class="off"><img src="img/temp/off.png"></a>--></form>
</div>
</body>
</html>
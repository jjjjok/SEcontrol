<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css"/>
</head>
<body><!-------------------login-------------------------->
<div class="login">
    <form action="${pageContext.request.contextPath}/user/login" method="post"><h1><a href="index.html"></a></h1>
        <p></p>
        <div class="msg-warn hide"><b></b>公共场所不建议自动登录，以防账号丢失</div>
        <p><input type="text" name="name" value="" placeholder="用户名/手机号"></p>
        <p><input type="text" name="password" value="" placeholder="密码"></p>
        <p><input type="submit" name="" value="登  录"></p>
        <p class="txt"><a class="" href="">免费注册</a><a href="">忘记密码？</a></p></form>
</div>
</body>
</html>
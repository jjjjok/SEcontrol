<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2020/12/27
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head lang="en">
    <meta charset="utf-8">
    <title>管理员登录</title>
    <<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css1/login.css"/>
</head>
<body>
<header class = "container">
    <nav class="header_1">
        <ul style="z-index: 9999; position: relative">
            <li><a href="#" style="text-indent:-15px">中国大陆<i class="fas fa-angle-down" style="float: left;margin-left: 67px; margin-top:-22px;"></i></a>

            </li>

        </ul>
    </nav>
    <div class="header_2">
        <ul class="header_2_1">

            <li class="header_2_1_5">
                <a href="${pageContext.request.contextPath}/item/preselectallitem">首页</a>
            </li>
        </ul></div>
</header>
<div class="logo">
    <div  class = logo_1>
        <img src="${pageContext.request.contextPath}/img/logo.png" width="95" height="95" style = vertical-align:sub />
        <span style = font-size:50px>口袋网</span>
        <span>管理员登录</span>
    </div>
</div>
<div class="login">
    <form action="${pageContext.request.contextPath}/usermanager/login" method="post">
        <p>管理员登录</p>
        <p><input type="text" name="name" value="" placeholder="账号"></p>
        <p><input type="text" name="password" value="" placeholder="密码"></p>
        <p><input type="submit" name="" value="登  录"></p>
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

</body>
</html>

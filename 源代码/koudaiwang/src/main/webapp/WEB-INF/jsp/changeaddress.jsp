<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2020/12/27
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head lang="en">
    <meta charset="utf-8">
    <title></title>
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
        <span>编辑地址信息</span>
    </div>
</div>
<div class="reg">
    <form action="${pageContext.request.contextPath}/user/addresschange?id=${addlist.id} " method="post" onsubmit="return CheckForm(this)">
       <p><input type="text" name="name" value="${addlist.user_name}" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="收件人" ><span class="empty"></span></p>
        <p><input type="text" name="userphone" value="${addlist.user_phone}" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="联系电话" ><span class="empty"></span></p>
        <p><input type="text" name="address" value="${addlist.address}" onfocus="FocusItem(this)" onblur="CheckItem(this)" placeholder="详细地址" ><span class="empty"></span></p>
        <p><input type="submit" name="" value="确认"></p>
    </form>
</div>
<div id="footer">
    <p class="dibu">
        Copyright  ©2020-2022  哆啦A梦的口袋网版权所有	浙江省网络食品销售第三方平台提供者备案：浙网食A33010001<br/>
        出版物网络交易平台服务经营备案号：新出发浙备字第002号	市场名称登记证：工商网市字3301004120号 联系电话：18656600273
    </p>
</div>
</body>
</html>


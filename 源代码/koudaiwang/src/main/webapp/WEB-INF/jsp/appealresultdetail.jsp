<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2021/1/4
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/myorder.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css1/login.css"/>
</head>
<body><!------------------------------head------------------------------>
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

        </ul>
    </div>
</header>
<div class="logo">
    <div  class = logo_1>
        <img src="${pageContext.request.contextPath}/img/logo.png" width="70" height="70" style = vertical-align:sub />
        <span style = font-size:35px>口袋网</span>
        <span>申诉处理结果</span>
    </div>
</div>
<div class="Bott">
    <div class="wrapper clearfix">
        <div class="zuo fl">

        </div>


        <div class="you fl">
            <div class="my clearfix"><h2>申诉详情</h2>
                <h3>订单号：<span>${appeal.order_id}</span></h3></div>
            <div class="orderList">

                <div class="orderList1"><h3>申诉人信息</h3>
                    <p>姓 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：<span>${username}</span></p>
                    <p>联系电话：<span>${userphone}</span></p>

                </div>
                <div class="orderList1"><h3>申诉原因</h3>
                    <div class="clearfix">
                        <p>申诉原因：<span>${appeal.appeal_rea}</span></p>
                        <c:if test="${not empty appeal.appeal_img}">
                            <a href="#" class="fl"><img src="${pageContext.request.contextPath}/imgs/${appeal.appeal_img}"/></a>
                        </c:if>
                        <p class="fl"><a href="#">${appeal.appeal_inf}</a></p></div>
                    <div class="orderList1"><h3>处理结果</h3>
                        <p class="fl"><a href="#">${appeal.appeal_result}</a></p></div>
                </div>
            </div>
        </div>
    </div>

    <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/public.js" type="text/javascript" charset="utf-8"></script>
    <script src="js/user.js" type="text/javascript" charset="utf-8"></script>

</div>
</body>
</html>

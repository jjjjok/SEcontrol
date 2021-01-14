<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2021/1/4
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <span>退款详情</span>
    </div>
</div>
<div class="Bott">
    <div class="wrapper clearfix">
        <div class="zuo fl">
            <h3>
                <p class="clearfix"><span class="fl">${name.user_name}</span><span class="fr"><a href="${pageContext.request.contextPath}/user/tologin">[退出登录]</a> </span></p></h3>
            <div><h4>我的交易</h4>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/item/collectshow">我的购物车</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/myorder">我的订单</a></li>

                </ul>
                <h4>个人中心</h4>
                <ul>

                    <li><a href="${pageContext.request.contextPath}/user/addressmanager">地址管理</a></li>
                </ul>
                <h4>我的发布</h4>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/item/tousersell">我要发布</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/myselltosend">已发布的</a></li>
                </ul>
            </div>
        </div>


        <div class="you fl">
            <div class="my clearfix"><h2>物流详情</h2>
                <h3>订单号：<span>${order.order_id}</span></h3></div>
            <div class="orderList">

                <div class="orderList1"><h3>订单信息</h3>
                    <p>收货人：<span>${order.add_name}</span></p>
                    <p>联系电话：<span>${order.add_phone}</span></p>
                    <p>收货地址：<span>${order.address}</span></p>
                    <p>订单内容：<span></span></p>
                    <p> <span><img src="${pageContext.request.contextPath}/imgs/${order.item_img}"/>
                   <a>${order.item_name}</a><a href="#">   ¥${order.item_bprice}×${order.order_num}</a></span></p>


                </div>
                <div class="orderList1"><h3>物流详情</h3>
                    <div class="clearfix">
                        <p>物流公司：<span>${w.wuliu_name}</span></p>
                        <p>邮递编号：<span>${w.wuliu_id}</span></p>
                        <p>发出时间：<span>${w.wuliu_date}</span></p>

                </div>
            </div>
        </div>


        <script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/public.js" type="text/javascript" charset="utf-8"></script>
        <script src="js/user.js" type="text/javascript" charset="utf-8"></script>
        </div>
    </div>
</div>
</body>
</html>

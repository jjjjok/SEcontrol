<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2020/12/28
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mygxin.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css1/index.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proList.css"/>
</head>
<body><!------------------------------head------------------------------>
<header class = "container">
    <nav class="header_1">
        <ul style="z-index: 9999; position: relative">
            <li><a href="#" style="text-indent:-15px">中国大陆<i class="fas fa-angle-down" style="float: left;margin-left: 67px; margin-top:-22px;"></i></a>
            </li>
            <li><a href="" style="color: red" >你好，${name.user_name}</a></li>
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
        <img src="${pageContext.request.contextPath}/img/logo.png" width="95" height="95" style = vertical-align:sub />
        <span style = font-size:50px>口袋网</span>
        <span>我的口袋</span>
    </div>
</div><!------------------------------idea------------------------------>
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
        <div class="you fl"><h2>收货地址</h2>
            <div class="add">
                <div><a href="${pageContext.request.contextPath}/user/toaddaddress" id="addxad"><img src="${pageContext.request.contextPath}/img/jia.png"/></a><span>添加新地址</span></div>
                <c:forEach var="add" items="${addlist}">

                <div id="dizhi"><p>${add.user_name}</p>
                    <p>${add.user_phone}</p>
                    <p>${add.address}</p>
                    <p class="addp"><a href="${pageContext.request.contextPath}/user/tochangeaddress?id=${add.id}"  id="readd">修改</a>
                        <a href="javascript:Delete('确定删除该地址吗','deleteaddress?id=${add.id}')" id="deladd">删除</a></p>
                    </div>

                </c:forEach>
            </div>
<script>
    function Delete(inf,url){
        if(confirm(inf)){
            location.href=url;
        }
    }
</script>
        </div>
    </div>
</div><!--编辑弹框--><!--遮罩-->


<div class="footer">

    <p class="dibu">Copyright  ©2020-2022  哆啦A梦的口袋网版权所有	浙江省网络食品销售第三方平台提供者备案：浙网食A33010001<br/>
        出版物网络交易平台服务经营备案号：新出发浙备字第002号	市场名称登记证：工商网市字3301004120号</p></div>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript" charset="utf-8"></script>

</body>
</html>
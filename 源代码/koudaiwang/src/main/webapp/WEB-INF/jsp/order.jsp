<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2020/12/18
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proList.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mygxin.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css1/index.css"/>
</head>
<body>
<header class = "container">
    <nav class="header_1">
        <ul style="z-index: 9999; position: relative">
            <li><a href="#" style="text-indent:-15px">中国大陆<i class="fas fa-angle-down" style="float: left;margin-left: 67px; margin-top:-22px;"></i></a>

            </li>


            <c:if test="${isLogin !=1}">
                <li><a href="${pageContext.request.contextPath}/user/tologin"style="color: red" >叮，请登录…</a></li>
            </c:if>
            <c:if test="${isLogin ==1}">
                <li><a href="" style="color: red" >你好，${name.user_name}</a></li>
            </c:if>
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
    <span>订单确认</span>
</div>

<div class="order cart mt"><!-----------------site------------------->
    <div class="site"><p class="wrapper clearfix"><span class="fl"></span><img class="top" src="${pageContext.request.contextPath}/img/2.png" width="480px" height="65px"></p>
    </div><!-----------------orderCon------------------->
    <div class="orderCon wrapper clearfix">
        <div class="orderL fl"><!--------h3----------------><h3>选择收货地址信息<a href="${pageContext.request.contextPath}/item/toaddaddress" class="fr">新增地址</a></h3>
            <!--------addres---------------->

            <div class="addres clearfix">



                <c:forEach var="add" items="${requestScope.addlist}">
                    <div class="addre fl">

                        <div class="tit clearfix" name="add" id="add"><p class="fl">${add.user_name}</p>

                            <p class="fr"><a href="${pageContext.request.contextPath}/item/addressconfirm?addid=${add.id}">点此选择该地址</a><span>|</span>
                                <a href="javascript:Delete('确定删除该地址吗','deleteaddress?id=${add.id}')">删除</a><span>|</span>
                                <a href="${pageContext.request.contextPath}/item/tochangeaddress?id=${add.id}" class="edit">编辑</a></p></div>
                        <div class="addCon"><p>${add.address}</p>
                            <p>${add.user_phone}</p></div>
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
        <div class="orderR fr">
            <div class="msg"><h3>订单内容</h3><!--------ul---------------->

                <c:forEach var="i" items="${requestScope.confirmlist}">
                <ul class="clearfix">

                    <li class="fl"><img src="../imgs/${i.item_img}" width="85" height="85"></li>
                    <li class="fl"><p>${i.item_name}</p>
                        <p>数量：${i.collect_count}</p></li>
                    <li class="fr">￥<fmt:formatNumber type="number" value="${i.item_bprice*i.collect_count}" maxFractionDigits="2" /></li>
                </ul>
                </c:forEach>

            </div><!--------tips---------------->
            <div class="tips">

            <div class="count tips"><p><span class="fl">合计：</span><span class="fr">￥${sum}</span></p></div>
             <a href="${pageContext.request.contextPath}/item/pay?confirmid=${confirm}&sum=${sum}" class="pay">去支付</a></div>
    </div>
</div>
</div>
<div class="mask"></div>


<div class="footer">

    <p class="dibu">Copyright  ©2020-2022  哆啦A梦的口袋网版权所有	浙江省网络食品销售第三方平台提供者备案：浙网食A33010001<br/>
        出版物网络交易平台服务经营备案号：新出发浙备字第002号	市场名称登记证：工商网市字3301004120号 联系电话：18656600273</p></div>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript" charset="utf-8"></script>
</div>
</body>
</html>
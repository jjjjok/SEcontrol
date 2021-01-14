<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2020/12/28
  Time: 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/myorder.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css1/index.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <script>
        function Delete(inf,url){
            if(confirm(inf)){
                location.href=url;
            }
        }
    </script>
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
        <img src="${pageContext.request.contextPath}/img/logo.png" width="70" height="70" style = vertical-align:sub />
        <span style = font-size:35px>口袋网</span>
        <span>我的口袋</span>
    </div>
</div><!------------------------------idea------------------------------>
<div class="Bott">
    <div class="wrapper clearfix">
        <div class="zuo fl">
            <h3>
                <p class="clearfix"><span class="fl">${name.user_name}</span><span class="fr"><a href="javascript:Delete('确定退出当前账户吗？','quit')">[退出登录]</a> </span></p></h3>
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
            <div class="my clearfix"><h2 class="fl">我的发布</h2></div>
            <div class="dlist clearfix">
                <ul class="fl clearfix" id="wa">
                    <li class="on"><a href="${pageContext.request.contextPath}/user/myselltosend">等待发货</a></li>
                    <li class="on"><a href="${pageContext.request.contextPath}/user/mysellsended">已发货</a></li>
                    <li class="on"><a href="${pageContext.request.contextPath}/user/mysellrefund">退款申请</a></li>
                    <li class="on"><a href="${pageContext.request.contextPath}/user/mysellchange">换货申请</a></li>
                    <li class="on"><a href="${pageContext.request.contextPath}/user/mysellfinish">已完成</a></li>
                </ul>

                </form>
            </div>

            <c:forEach var="order" items="${orderlist}">


                <div class="dkuang"><p class="one"></p>
                    <div class="word clearfix">
                        <ul class="fl clearfix">
                            <li>被购日期${order.order_date}</li>
                            <li>商品名称：${order.item_name}</li>
                            <li>收货人：${order.add_name}</li>
                            <li>联系电话：${order.add_phone}</li>
                            <li>购买数量:${order.order_num}</li>
                            <li>订单编号:${order.order_id}</li>
                            <c:if test="${order.order_state==5}">
                                <a href="${pageContext.request.contextPath}/user/refunddetail?id=${order.order_id}">查看退款详情</a>
                            </c:if>


                        </ul>
                        <p class="fr">订单金额：<span>￥<fmt:formatNumber type="number" value="${order.item_bprice*order.order_num}" maxFractionDigits="2" /></span>元</p></div>
                    <div class="shohou clearfix"><a href="#" class="fl"><img src="${pageContext.request.contextPath}/imgs/${order.item_img}"/></a>
                        <p class="fl"><a href="#">收货地址：${order.address}</a></p>

                        <c:if test="${order.order_state==5}">
                        <p class="fr">
                            <a href="${pageContext.request.contextPath}/user/refundchecked?url=6&id=${order.order_id}">同意退款</a>
                            <a href="${pageContext.request.contextPath}/user/refundchecked?url=7&id=${order.order_id}">不同意退款</a></p>
                    </c:if>
                    <c:if test="${order.order_state==6}">
                        <p class="fr"> 已同意退款</p>
                    </c:if>
                    <c:if test="${order.order_state==7}">
                        <p class="fr"> 已拒绝退款</p>
                    </c:if>
                </div>
                </div>

            </c:forEach>




        </div>
    </div>
</div>
</div>
<div class="footer">

    <p class="dibu">Copyright  ©2020-2022  哆啦A梦的口袋网版权所有	浙江省网络食品销售第三方平台提供者备案：浙网食A33010001<br/>
        出版物网络交易平台服务经营备案号：新出发浙备字第002号	市场名称登记证：工商网市字3301004120号 联系电话：18656600273</p></div></div>
</div><!--返回顶部-->


<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/user.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
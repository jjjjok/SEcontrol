<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2020/11/21
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proList.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css1/index.css"/>
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
        <span>商品详情</span>
    </div>
</div>
<div class="detCon">
    <div class="proDet wrapper">
        <div class="proCon clearfix">
            <div class="proImg fl"><img class="det" src="../imgs/${i.item_img}"/>

            </div>
            <div class="fr intro">
                <div class="title"><h4>${i.item_name}</h4>
                   <p><span>购入价：￥${i.item_aprice}</span><p><span>出售价：￥${i.item_bprice}</span></p>
                </div>
                <div class="proIntro"><p></p>
                    <div class="title"><h4>${i.item_inf}</h4></div>

                    <p>库存<span>${i.item_num}</span>件</p>
                    <div class="num clearfix">
                        <img class="fl sub" src="${pageContext.request.contextPath}/img/temp/sub.jpg">
                        <span id="count" class="fl" datasrc1="${i.item_num}" contentEditable="true">1</span><img
                            class="fl add" src="${pageContext.request.contextPath}/img/temp/add.jpg">
                </div>

            </div>
                <div class="btns clearfix">
                    <a href="javascript:buy('${i.item_id}','1')"><p class="buy fl">立即购买</p></a>
                    <a href="javascript:buy('${i.item_id}','2')"><p class="cart fr">加入购物车</p></a></div>
        </div>
    </div>
</div>
</div>
    <script>
        function buy(id,url){
            var count=document.getElementById("count").innerHTML;
            location.href='${pageContext.request.contextPath}/item/collectadd?id='+id+'&count='+count+'&url='+url;

        }
    </script>






<div class="msk"></div><!--footer-->
<div class="footer">
    <div class="top">
        <div class="wrapper">

        </div>
    </div>
    <p class="dibu">哆啦A梦的口袋网&copy;2020-2022公司版权所有 京ICP备080100-44备0000111000号<br/>
        违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试 联系电话：18656600273</p></div>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/jquery.SuperSlide.2.1.1.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/nav.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/cart.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">jQuery(".bottom").slide({
    titCell: ".hd ul",
    mainCell: ".bd .likeList",
    autoPage: true,
    autoPlay: false,
    effect: "leftLoop",
    autoPlay: true,
    vis: 1
});</script>
</body>
</html>

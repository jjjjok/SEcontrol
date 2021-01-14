<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2020/12/27
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<html>
<script>
    function f() {
        var url = "${pageContext.request.contextPath}/item/preselectallitem"
        $.post(url)
    }
    $(document).ready(f())

    function search(){
        location.href="${pageContext.request.contextPath}/item/searchitem"
    }

</script>
<head lang="en">
    <meta charset="utf-8"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proList.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css1/index.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
</head>
<body><!------------------------------head------------------------------>
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
            </li>
        </ul>
    </div>
</header>
<div class="logo">
    <div  class = logo_1>
        <img src="${pageContext.request.contextPath}/img/logo.png" width="70" height="70" style = vertical-align:sub />
        <span style = font-size:35px>口袋网</span>
        <span>首页</span>
    </div>
    <div class="all_logo_2_2">      <!--js搜索框的实现-->
        <div id="wrap">
            <div style="overflow: hidden;">
                <i class="fas fa-search"></i>  <!--引入放大镜-->
                <input type="text" id="input" name="text" placeholder="台灯" />
                <a href="" id="a"> <img src="${pageContext.request.contextPath}/img/ss.png" width="27px" height="27px"></a>
                <script type="text/javascript">
                    input.onkeyup=function(){
                        a.setAttribute("href","${pageContext.request.contextPath}/item/searchitem?text="+input.value);
                    }
                </script>

            </div>
            <ul id="list"></ul>
        </div>
    </div>
</div>
<div id ="nav">
    <a href="${pageContext.request.contextPath}/item/preselectallitem">首页</a>

    <a href="${pageContext.request.contextPath}/item/selectflitem?id=1">宿舍用品</a>

    <a href="${pageContext.request.contextPath}/item/selectflitem?id=2">二手书本</a>

    <a href="${pageContext.request.contextPath}/item/selectflitem?id=3">电子产品</a>

    <a href="${pageContext.request.contextPath}/item/selectflitem?id=4">生活用品</a>

    <a href="${pageContext.request.contextPath}/item/selectflitem?id=5">零食小吃</a>
</div>

<ul class="proList wrapper clearfix">

    <c:forEach var="i" items="${list}">
        <li>  <a href="${pageContext.request.contextPath}/item/detail?id=${i.item_id}">
            <dl>
                <dt><span class="abl"></span><img src="${pageContext.request.contextPath}/imgs/${i.item_img}" width="219px" height="219px" /><span class="abr"></span></dt>
                <dd>${i.item_name}</dd>
                <dd><span>￥${i.item_bprice}</span></dd>
            </dl>
        </a></li>

    </c:forEach>


</ul><!----------------mask------------------->
<div class="list-page">
    共${itemsum}条记录，当前${itemcpage}/${itempage}页
    <a href="${pageContext.request.contextPath}/item/selectflitem?cp=1&id=${id}">首页</a>
    <a href="${pageContext.request.contextPath}/item/selectflitem?cp=${itemcpage-1<1?1:itemcpage-1}&id=${id}">上一页</a>
    <a href="${pageContext.request.contextPath}/item/selectflitem?cp=${itemcpage+1>itempage?itempage:itemcpage+1}&id=${id}">下一页</a>
    <a href="${pageContext.request.contextPath}/item/selectflitem?cp=${itempage}&id=${id}">尾页</a>

</div>
<div class="msk"></div><!--footer-->
<div class="footer">
    <p class="dibu">Copyright  ©2020-2022  哆啦A梦的口袋网版权所有	浙江省网络食品销售第三方平台提供者备案：浙网食A33010001<br/>
        出版物网络交易平台服务经营备案号：新出发浙备字第002号	联系电话：18656600273</p></div>

<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/nav.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/cart.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
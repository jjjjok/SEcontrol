<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2020/12/7
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>商品详情</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proList.css"/>
</head>
<body><!------------------------------head------------------------------>


<!-----------------address------------------------------->

<div class="current">
        <div class="fr choice"><p class="default">排序方式</p>
            <ul class="select">
                <li>新品上市</li>
                <li>销量从高到低</li>
                <li>销量从低到高</li>
                <li>价格从高到低</li>
                <li>价格从低到高</li>
            </ul>
        </div>
    </div>
</div><!----------------proList------------------------->
<ul class="proList wrapper clearfix">

    <c:forEach var="i" items="${list}">


    <li><a href="${pageContext.request.contextPath}/item/detail?id=${i.item_id}">
        <dl>
            <dt><img src="../imgs/${i.item_img}"></dt>
            <dd>${i.item_name}</dd>
            <dd>出售价：${i.item_bprice}</dd>
        </dl>
    </a></li>

    </c:forEach>


</ul><!----------------mask------------------->


<div class="footer">

    <p class="dibu">哆啦A梦的口袋网&copy;2020-2022公司版权所有 京ICP备080100-44备0000111000号<br/>
        违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p></div>
<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/nav.js" type="text/javascript" charset="utf-8"></script>
<script src="js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="js/cart.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
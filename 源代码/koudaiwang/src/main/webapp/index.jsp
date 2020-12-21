<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" +
            request.getServerPort() + path + "/";
%>
<script>
    function f() {
        var url = "${pageContext.request.contextPath}/item/preselectallitem"
        $.post(url)
    }
    $(document).ready(f())
</script>
<base href="<%=basePath%>">
<head lang="en">
    <meta charset="utf-8"/>
    <title>主页</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/public.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/index.css"/>
</head>
<body><!------------------------------head------------------------------>
<div class="head">
    <div class="wrapper clearfix">
        <div class="clearfix" id="top"><h1 class="fl"><a href="index.html"></a></h1>
            <c:if test="${isLogin !=1}">
            <div class="fr clearfix" id="top1"><p class="fl"><a href="${pageContext.request.contextPath}/user/tologin" id="login">登录</a><a href="${pageContext.request.contextPath}/user/toregister" id="reg">注册</a><a href="${pageContext.request.contextPath}/usermanager/tomanager" id="man">后台</a>


                </c:if>
                <c:if test="${isLogin ==1}">
                <div class="fr clearfix" id="top1"><p class="fl"><a href="${pageContext.request.contextPath}/user/tologin" id="login">你好，${name.user_name}</a><a href="${pageContext.request.contextPath}/user/toregister" id="reg">注册</a><a href="${pageContext.request.contextPath}/usermanager/tomanager" id="man">后台</a>
                </c:if>
            </p>
                <form action="#" method="get" class="fl"><input type="button"/></form>
                <div class="btn fl clearfix"><a href="mygxin.html"><img src="img/grzx.png"/></a><a href="#" class="er1"><img
                        src="img/ewm.png"/></a><a href="cart.html"><img src="img/gwc.png"/></a>
                    <p><a href="#"><img src="img/smewm.png"/></a></p></div>
            </div>
        </div>
        <ul class="clearfix" id="bott">
            <li><a href="${pageContext.request.contextPath}/item/indexselect">首页</a></li>

            <li><a href="${pageContext.request.contextPath}/item/selectflitem?id=1">宿舍用品</a>
            <li><a href="${pageContext.request.contextPath}/item/selectflitem?id=2">日常用具</a>
            <li><a href="${pageContext.request.contextPath}/item/selectflitem?id=3">学习用品</a>
            <li><a href="${pageContext.request.contextPath}/item/selectflitem?id=4">零食</a>
            </li>

        </ul>
    </div>
</div><!-------------------------banner--------------------------->



<div class="people">
    <div class="wrapper">

        <div class="pList clearfix tran">
            <c:forEach var="i" items="${itemlist}">
            <a href="${pageContext.request.contextPath}/item/detail?id=${i.item_id}">

            <dl>
                <dt><span class="abl"></span><img src="koudaiwang/../imgs/${i.item_img}"/><span class="abr"></span></dt>
                <dd>${i.item_name}</dd>
                <dd><span>出售价：${i.item_bprice}</span></dd>
            </dl>
            </c:forEach>
        </div>
    </div>
</div><!--返回顶部-->
<div class="list-page">
    共${itemsum}条记录，当前${itemcpage}/${itempage}页
    <a href="${pageContext.request.contextPath}/item/preselectallitem?cp=1">首页</a>
    <a href="${pageContext.request.contextPath}/item/preselectallitem?cp=${itemcpage-1<1?1:itemcpage-1}">上一页</a>
    <a href="${pageContext.request.contextPath}/item/preselectallitem?cp=${itemcpage+1>itempage?itempage:itemcpage+1}">下一页</a>
    <a href="${pageContext.request.contextPath}/item/preselectallitem?cp=${itempage}">尾页</a>

</div>

    <p>400-800-8200</p></div><!-------------------login--------------------------><!--footer-->
<div class="footer">

    <p class="dibu">哆啦A梦的口袋网&copy;2020-2022公司版权所有 京ICP备080100-44备0000111000号<br/>
        违法和不良信息举报电话：400-800-8200，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p></div>
<script src="js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/nav.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.flexslider-min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">$(function () {
    $('#home_slider').flexslider({
        animation: 'slide',
        controlNav: true,
        directionNav: true,
        animationLoop: true,
        slideshow: true,
        slideshowSpeed: 2000,
        useCSS: false
    });
});</script>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2020/12/18
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<html>
<head lang="en">
    <meta charset="utf-8"/>
    <title>购物车</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proList.css"/>
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
                <li><a href=""style="color: red" >你好，${name.user_name}</a></li>
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
        <span>购物车</span>

    </div>
</div>
<div class="cart mt"><!-----------------logo------------------->
    <!--<div class="logo"><h1 class="wrapper clearfix"><a href="index.html"><img class="fl" src="img/temp/logo.png"></a><img class="top" src="img/temp/cartTop01.png"></h1></div>-->
    <!-----------------site------------------->

    <form action="${pageContext.request.contextPath}/item/collectsdelete" id="myform" method="post">
    <div class="site"><p class=" wrapper clearfix"><span class="fl"></span><img class="top" src="${pageContext.request.contextPath}/img/1.png" width="480px" height="65px"></p></div><!-----------------table------------------->
    <div class="table wrapper">
        <div class="tr">
            <div>商品</div>
            <div>单价</div>
            <div>数量</div>
            <div>小计</div>
            <div>操作</div>
        </div>


<c:forEach var="i" items="${requestScope.list}">



        <div class="th">
            <div class="pro clearfix"><label class="fl"><input name="cid" type="checkbox" value="${i.item_id}"/>
                <span></span></label><a class="fl" href="${pageContext.request.contextPath}/item/detail?id=${i.item_id}">
                <dl class="clearfix">
                    <dt class="fl"><img src="../imgs/${i.item_img}" width="120" height="120"></dt>
                    <dd class="fl"><p>${i.item_name}</p>
                </dl>
            </a></div>
            <div class="price">￥${i.item_bprice}</div>
            <div class="number"><p class="num clearfix"><img class="fl sub" src="${pageContext.request.contextPath}/img/temp/sub.jpg">
                <span datasrc="${i.item_id}" datasrc1="${i.item_num}"

                    class="fl">${i.collect_count}</span><img class="fl add" src="${pageContext.request.contextPath}/img/temp/add.jpg"></p>
            </div>
            <div class="price sAll">￥<fmt:formatNumber type="number" value="${i.item_bprice*i.collect_count}" maxFractionDigits="2" /></div>
            <div class="price"><a class="del" datasrc="${i.item_id}" href="javascript:Delete('确定删除该商品吗','collectdelete?itemid=${i.item_id}')">删除</a></div>
        </div>

</c:forEach>
        <c:if test="${count==0}">
            <div class="goOn" style="display: block;">购物车还没有东西哦~<a href="${pageContext.request.contextPath}/item/preselectallitem">去看看</a></div>
        </c:if>
        <script>
            function Delete(inf,url){
                if(confirm(inf)){
                    location.href=url;
                }
            }

            function Deletecollects(inf,formname){
                if(confirm(inf)){
                    var form=document.getElementById(formname);
                    form.submit();
                }
            }

        </script>



        <div class="tr clearfix"><label class="fl"><input class="checkAll" type="checkbox"/><span></span></label>
            <p class="fl"><a href="#">全选</a><a href="javascript:Deletecollects('确定删除这些商品吗','myform')" class="del">删除</a></p>
            <p class="fr"><span>共<small id="sl">0</small>件商品</span><span>合计:&nbsp;<small id="all">￥0.00</small></span><a
                    href="javascript:toorder()" class="count">结算</a></p></div>
    </div>
    </form>
</div>
<script>
    function toorder(){
        var str="";
        $("input[name='cid']:checked").each(function (index,item){
            if($("input[name='cid']:checked").length-1==index){
                str+=$(this).val();
            }else{
                str+=$(this).val()+",";
            }

        });
        location.href="orderconfirm?itemids="+str;
    }
</script>
<div class="mask"></div>


<div class="footer">

    <p class="dibu">Copyright  ©2020-2022  哆啦A梦的口袋网版权所有	浙江省网络食品销售第三方平台提供者备案：浙网食A33010001<br/>
        出版物网络交易平台服务经营备案号：新出发浙备字第002号	市场名称登记证：工商网市字3301004120号 联系电话：18656600273</p></div><!----------------mask------------------->




<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>

<script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/cart.js" type="text/javascript" charset="utf-8"></script>

</body>
</html>
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
    <title>cart</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proList.css"/>
</head>
<body><!--------------------------------------cart--------------------->
<div class="head ding">
    <div class="wrapper clearfix">
        <div class="clearfix" id="top"><h1 class="fl"><a href="index.html"><img src="img/logo.png"/></a></h1>
            <div class="fr clearfix" id="top1"><p class="fl"><a href="login.html" id="login">登录</a><a href="reg.html"
                                                                                                      id="reg">注册</a>
            </p>
                <form action="#" method="get" class="fl"><input type="text" placeholder="搜索"/><input type="button"/>
                </form>
                <div class="btn fl clearfix"><a href="mygxin.html"><img src="img/grzx.png"/></a><a href="#" class="er1"><img
                        src="img/ewm.png"/></a><a href="cart.html"><img src="img/gwc.png"/></a>
                    <p><a href="#"><img src="img/smewm.png"/></a></p></div>
            </div>
        </div>
    </div>
</div>
<div class="cart mt"><!-----------------logo------------------->
    <!--<div class="logo"><h1 class="wrapper clearfix"><a href="index.html"><img class="fl" src="img/temp/logo.png"></a><img class="top" src="img/temp/cartTop01.png"></h1></div>-->
    <!-----------------site------------------->
    <div class="site"><p class=" wrapper clearfix"><span class="fl">购物车</span><img class="top"
                                                                                   src="img/temp/cartTop01.png"><a
            href="index.html" class="fr">继续购物&gt;</a></p></div><!-----------------table------------------->
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
                <span datasrc="${i.item_id}"

                    class="fl">${i.collect_count}</span><img class="fl add" src="${pageContext.request.contextPath}/img/temp/add.jpg"></p>
            </div>
            <div class="price sAll">￥<fmt:formatNumber type="number" value="${i.item_bprice*i.collect_count}" maxFractionDigits="2" /></div>
            <div class="price"><a class="del" datasrc="${i.item_id}" href="#2">删除</a></div>
        </div>

</c:forEach>


        <div class="goOn">空空如也~<a href="index.html">去逛逛</a></div>
        <div class="tr clearfix"><label class="fl"><input class="checkAll" type="checkbox"/><span></span></label>
            <p class="fl"><a href="#">全选</a><a href="#" class="del">删除</a></p>
            <p class="fr"><span>共<small id="sl">0</small>件商品</span><span>合计:&nbsp;<small id="all">￥0.00</small></span><a
                    href="javascript:toorder()" class="count">结算</a></p></div>
    </div>
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
<div class="tipDel"><p>确定要删除该商品吗？</p>
    <p class="clearfix"><a class="fl cer" href="#">确定</a><a class="fr cancel" href="#">取消</a></p></div><!--返回顶部-->
<div class="gotop"><a href="cart.html">
    <dl>
        <dt><img src="img/gt1.png"/></dt>
        <dd>去购<br/>物车</dd>
    </dl>
</a><a href="#" class="dh">
    <dl>
        <dt><img src="img/gt2.png"/></dt>
        <dd>联系<br/>客服</dd>
    </dl>
</a><a href="mygxin.html">
    <dl>
        <dt><img src="img/gt3.png"/></dt>
        <dd>个人<br/>中心</dd>
    </dl>
</a><a href="#" class="toptop" style="display: none;">
    <dl>
        <dt><img src="img/gt4.png"/></dt>
        <dd>返回<br/>顶部</dd>
    </dl>
</a>
    <p>400-800-8200</p></div><!--footer-->
<div class="footer">
    <div class="top">

    </div>
    <p class="dibu">最家家居&copy;2013-2017公司版权所有 京ICP备080100-44备0000111000号<br/>
        违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p></div><!----------------mask------------------->
<div class="mask"></div><!-------------------mask内容------------------->
<div class="proDets"><img class="off" src="img/temp/off.jpg"/>
    <div class="proCon clearfix">
        <div class="proImg fr"><img class="list" src="img/temp/proDet.jpg"/>
            <div class="smallImg clearfix"><img src="img/temp/proDet01.jpg" data-src="img/temp/proDet01_big.jpg"><img
                    src="img/temp/proDet02.jpg" data-src="img/temp/proDet02_big.jpg"><img src="img/temp/proDet03.jpg"
                                                                                          data-src="img/temp/proDet03_big.jpg"><img
                    src="img/temp/proDet04.jpg" data-src="img/temp/proDet04_big.jpg"></div>
        </div>
        <div class="fl">
            <div class="proIntro change"><p>颜色分类</p>
                <div class="smallImg clearfix"><p class="fl on"><img src="img/temp/prosmall01.jpg" alt="白瓷花瓶+20支快乐花"
                                                                     data-src="img/temp/proBig01.jpg"></p>
                    <p class="fl"><img src="img/temp/prosmall02.jpg" alt="白瓷花瓶+20支兔尾巴草"
                                       data-src="img/temp/proBig02.jpg"></p>
                    <p class="fl"><img src="img/temp/prosmall03.jpg" alt="20支快乐花" data-src="img/temp/proBig03.jpg"></p>
                    <p class="fl"><img src="img/temp/prosmall04.jpg" alt="20支兔尾巴草" data-src="img/temp/proBig04.jpg"></p>
                </div>
            </div>
            <div class="changeBtn clearfix"><a href="#2" class="fl"><p class="buy">确认</p></a><a href="#2" class="fr"><p
                    class="cart">取消</p></a></div>
        </div>
    </div>
</div>
<div class="pleaseC"><p>请选择宝贝</p><img class="off" src="img/temp/off.jpg"/></div>

<script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/cart.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
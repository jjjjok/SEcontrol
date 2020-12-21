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
    <title>order</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/public.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/proList.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mygxin.css"/>
</head>
<body><!----------------------------------------order------------------>
<div class="head ding">
    <div class="wrapper clearfix">
        <div class="clearfix" id="top"><h1 class="fl"><a href="index.html"><img src="img/logo.png"/></a></h1>
            <div class="fr clearfix" id="top1"><p class="fl"><a href="#" id="login">登录</a><a href="#" id="reg">注册</a>
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
<div class="order cart mt"><!-----------------site------------------->
    <div class="site"><p class="wrapper clearfix"><span class="fl">订单确认</span><img class="top"
                                                                                   src="img/temp/cartTop02.png"></p>
    </div><!-----------------orderCon------------------->
    <div class="orderCon wrapper clearfix">
        <div class="orderL fl"><!--------h3----------------><h3>收件信息<a href="#" class="fr">新增地址</a></h3>
            <!--------addres---------------->
            <div class="addres clearfix">
                <div class="addre fl on">
                    <div class="tit clearfix"><p class="fl">张三1 <span class="default">[默认地址]</span></p>
                        <p class="fr"><a href="#">删除</a><span>|</span><a href="#" class="edit">编辑</a></p></div>
                    <div class="addCon"><p>河北省&nbsp;唐山市&nbsp;路北区&nbsp;大学生公寓村</p>
                        <p>15732570937</p></div>
                </div>
                <div class="addre fl">
                    <div class="tit clearfix"><p class="fl">张三2</p>
                        <p class="fr"><a href="#" class="setDefault">设为默认</a><span>|</span><a
                                href="#">删除</a><span>|</span><a href="#" class="edit">编辑</a></p></div>
                    <div class="addCon"><p>河北省&nbsp;唐山市&nbsp;路北区&nbsp;大学生公寓村</p>
                        <p>15732570937</p></div>
                </div>
                <div class="addre fl">
                    <div class="tit clearfix"><p class="fl">张三3</p>
                        <p class="fr"><a href="#" class="setDefault">设为默认</a><span>|</span><a
                                href="#">删除</a><span>|</span><a href="#" class="edit">编辑</a></p></div>
                    <div class="addCon"><p>河北省&nbsp;唐山市&nbsp;路北区&nbsp;大学生公寓村</p>
                        <p>15732570937</p></div>
                </div>
            </div>

        </div>
        <div class="orderR fr">
            <div class="msg"><h3>订单内容<a href="cart.html" class="fr">返回购物车</a></h3><!--------ul---------------->

                <c:forEach var="i" items="${requestScope.confirmlist}">
                <ul class="clearfix">
                    <li class="fl"><img src="../imgs/${i.item_img}" width="85" height="85"></li>
                    <li class="fl"><p>${i.item_name}</p>
                        <p>数量：${i.collect_count}</p></li>
                    <li class="fr">￥<fmt:formatNumber type="number" value="${i.item_bprice*i.collect_count}" maxFractionDigits="2" /></li>
                </ul>
                </c:forEach>

            </div><!--------tips---------------->
            <div class="tips"><p><span class="fl">商品金额：</span><span class="fr">￥${sum}</span></p>

            <div class="count tips"><p><span class="fl">合计：</span><span class="fr">￥${sum}</span></p></div>
             <a href="${pageContext.request.contextPath}/item/pay?confirmid=${confirm}&sum=${sum}" class="pay">去支付</a></div>
    </div>
</div><!--编辑弹框--><!--遮罩-->
</div>
<div class="mask"></div>
<div class="adddz editAddre">
    <form action="#" method="get"><input type="text" placeholder="姓名" class="on"/><input type="text" placeholder="手机号"/>
        <div class="city"><select name="">
            <option value="省份/自治区">省份/自治区</option>
        </select><select>
            <option value="城市/地区">城市/地区</option>
        </select><select>
            <option value="区/县">区/县</option>
        </select><select>
            <option value="配送区域">配送区域</option>
        </select></div>
        <textarea name="" rows="" cols="" placeholder="详细地址"></textarea><input type="text" placeholder="邮政编码"/>
        <div class="bc"><input type="button" value="保存"/><input type="button" value="取消"/></div>
    </form>
</div><!--返回顶部-->
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

    <p class="dibu">最家家居&copy;2013-2017公司版权所有 京ICP备080100-44备0000111000号<br/>
        违法和不良信息举报电话：188-0130-1238，本网站所列数据，除特殊说明，所有数据均出自我司实验室测试</p></div>
<script src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/pro.js" type="text/javascript" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/user.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
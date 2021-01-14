<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2020/12/2
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--输出,条件,迭代标签库-->
<html>
<head>
    <meta charset="UTF-8"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css1/login.css"/>
</head>
<body>
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
                <a href="${pageContext.request.contextPath}/item/preselectallitem">首页</a>
            </li>


        </ul>
    </div>
</header>
<div class="logo">
    <div  class = logo_1>
        <img src="${pageContext.request.contextPath}/img/logo.png" width="70" height="70" style = vertical-align:sub />
        <span style = font-size:35px>口袋网</span>
        <span>后台管理</span>
    </div>
</div>

<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>菜单</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>常用操作</a>
                    <ul class="sub-menu">
                        <li><a href="${pageContext.request.contextPath}/usermanager/selectalluser"><i class="icon-font">&#xe008;</i>用户管理</a></li>

                        <li><a href="${pageContext.request.contextPath}/itemmanager/selectallitem"><i class="icon-font">&#xe006;</i>商品管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/itemmanager/itemcheck"><i class="icon-font">&#xe006;</i>商品审核</a></li>
                        <li><a href="${pageContext.request.contextPath}/itemmanager/ordermanager"><i class="icon-font">&#xe006;</i>订单管理</a></li>

                    </ul>
                </li>

            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html"></a><span class="crumb-step"></span><span class="crumb-name"></span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">

            </div>
        </div>
        <div class="result-wrap">
            <form action="${pageContext.request.contextPath}/itemmanager/deleteitems" name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">


                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="5%"><input class="allChoose" name="" onclick="selectall(this)" type="checkbox"></th>
                            <th>订单ID</th>
                            <th>商品id</th>
                            <th>价格</th>
                            <th>购买人</th>
                            <th>卖家收款账户</th>
                            <th>买家收款账户</th>
                            <th></th>
                            <th>订单状态</th>
                            <th>操作</th>

                        </tr>

                        <c:forEach var="i" items="${orderlist}">

                            <tr>
                                <td class="tc"><input name="id[]" value="${i.item_id}" type="checkbox"></td>
                                <td>${i.order_id}</td>
                                <td>${i.item_id}</td>
                                <td>${i.item_bprice}</td>
                                <td>${i.order_num}</td>
                                <td>${i.user_id}</td>
                                <td>${i.seller_pay}</td>
                                <td>${i.buyer_pay}</td>
                                <c:if test="${i.order_state==1}">
                                <td>等待发货</td>
                                    <td>
                                       等待用户操作
                                    </td>
                                </c:if>
                                <c:if test="${i.order_state==2}">
                                    <td>订单派送中</td>
                                    <td>
                                       等待用户操作

                                    </td>
                                </c:if>
                                <c:if test="${i.order_state==3}">
                                    <td>用户已确认收货，卖家等待转账</td>
                                    <td>
                                        <a class="link-update" href="${pageContext.request.contextPath}/itemmanager/paychecked?id=${i.order_id}&cpage=${ordercpage}">已转账</a>

                                    </td>
                                </c:if>
                                <c:if test="${i.order_state==4}">
                                    <td>用户已完成订单</td>
                                    <td>
                                        订单已关闭

                                    </td>
                                </c:if>
                                <c:if test="${i.order_state==5}">
                                    <td>用户申请退款</td>
                                    <td>
                                        <a class="link-update" href="${pageContext.request.contextPath}/itemmanager/refunddetail?id=${i.order_id}">查看详情</a>

                                    </td>
                                </c:if>
                                <c:if test="${i.order_state==6}">
                                    <td>用户申请退款，卖家已同意</td>
                                    <td>
                                        <a class="link-update" href="${pageContext.request.contextPath}/itemmanager/refundchecked?id=${i.order_id}&cpage=${cpage}">已退款</a>

                                    </td>
                                </c:if>
                                <c:if test="${i.order_state==7}">
                                    <td>用户申请退款，卖家未同意</td>
                                    <td>
                                        等待用户操作

                                    </td>
                                </c:if>
                                <c:if test="${i.order_state==8}">
                                    <td>用户提出申诉</td>
                                    <td>
                                        <a class="link-update" href="${pageContext.request.contextPath}/itemmanager/appealdetail?id=${i.order_id}">查看详情</a>
                                        <a class="link-update" href="${pageContext.request.contextPath}/itemmanager/toappealresult?id=${i.order_id}&cpage=${ordercpage}">提交结果</a>

                                    </td>
                                </c:if>
                                <c:if test="${i.order_state==9}">
                                    <td>管理员已退款</td>
                                    <td>
                                        订单已关闭

                                    </td>
                                </c:if>
                                <c:if test="${i.order_state==10}">
                                    <td>申诉已处理</td>
                                    <td>
                                        订单已关闭

                                    </td>
                                </c:if>
                                <c:if test="${i.order_state==11}">
                                    <td>用户申请换货</td>
                                    <td>
                                        等待用户操作

                                    </td>
                                </c:if>

                            </tr>
                        </c:forEach>
                        <script>
                            function Delete(inf,url){
                                if(confirm(inf)){
                                    location.href=url;
                                }
                            }
                            function selectall(obj) {
                                var a = document.getElementsByName('id[]');
                                for (var i = 0; i < a.length; i++) {
                                    a[i].checked = obj.checked;

                                }
                            }
                            function DeleteUsers(inf,formname){
                                if(confirm(inf)){
                                    var form=document.getElementById(formname);
                                    form.submit();
                                }
                            }



                        </script>


                    </table>
                    <div class="list-page">
                        共${ordersum}条记录，当前${ordercpage}/${orderpage}页
                        <a href="${pageContext.request.contextPath}/itemmanager/ordermanager?cp=1">首页</a>
                        <a href="${pageContext.request.contextPath}/itemmanager/ordermanager?cp=${ordercpage-1<1?1:ordercpage-1}">上一页</a>
                        <a href="${pageContext.request.contextPath}/itemmanager/ordermanager?cp=${ordercpage+1>orderpage?orderpage:ordercpage+1}">下一页</a>
                        <a href="${pageContext.request.contextPath}/itemmanager/ordermanager?cp=${orderpage}">尾页</a>

                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>
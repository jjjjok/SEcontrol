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
    <title>后台管理</title>
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
        <img src="${pageContext.request.contextPath}/img/logo.png" width="95" height="95" style = vertical-align:sub />
        <span style = font-size:50px>口袋网</span>
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
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html"></a><span class="crumb-step"></span><span class="crumb-name"></span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="${pageContext.request.contextPath}/itemmanager/selectitembyname" method="get">
                    <table class="search-tab">
                        <tr>


                            <td><input class="common-text" placeholder="商品名" name="keywords" value="" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
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
                            <th>ID</th>
                            <th>商品名</th>
                            <th>原价格</th>
                            <th>出售价</th>
                            <th>数量</th>
                            <th>图片</th>
                            <th>描述</th>
                            <th>操作</th>

                        </tr>

                        <c:forEach var="i" items="${itemlist}">

                            <tr>
                                <td class="tc"><input name="id[]" value="${i.item_id}" type="checkbox"></td>
                                <td>${i.item_id}</td>
                                <td>${i.item_name}</td>
                                <td>${i.item_aprice}</td>
                                <td>${i.item_bprice}</td>
                                <td>${i.item_num}</td>
                                <td><img src="../imgs/${i.item_img}" width="80" height="80"></td>
                                <td>${i.item_inf}</td>

                                <td>
                                    <a class="link-update" href="${pageContext.request.contextPath}/itemmanager/itemchecked?id=${i.item_id}&cpage=${itemcpage}">通过</a>

                                </td>
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
                        共${itemsum}条记录，当前${itemcpage}/${itempage}页
                        <a href="${pageContext.request.contextPath}/itemmanager/selectallitem?cp=1">首页</a>
                        <a href="${pageContext.request.contextPath}/itemmanager/selectallitem?cp=${itemcpage-1<1?1:itemcpage-1}">上一页</a>
                        <a href="${pageContext.request.contextPath}/itemmanager/selectallitem?cp=${itemcpage+1>itempage?itempage:itemcpage+1}">下一页</a>
                        <a href="${pageContext.request.contextPath}/itemmanager/selectallitem?cp=${itempage}">尾页</a>

                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>
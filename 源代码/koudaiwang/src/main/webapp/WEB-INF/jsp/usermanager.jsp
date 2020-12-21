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
</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a class="on" href="index.html">首页</a></li>
                <li><a href="#" target="_blank">网站首页</a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="#">管理员</a></li>
                <li><a href="#">修改密码</a></li>
                <li><a href="#">退出</a></li>
            </ul>
        </div>
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
                        <li><a href="${pageContext.request.contextPath}/itemmanager/toadditem"><i class="icon-font">&#xe005;</i>商品管理</a></li>
                        <li><a href="${pageContext.request.contextPath}/itemmanager/toitemmanager"><i class="icon-font">&#xe006;</i>商品管理</a></li>

                    </ul>
                </li>

            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">作品管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="${pageContext.request.contextPath}/usermanager/selectuserbyname" method="get">
                    <table class="search-tab">
                        <tr>

                            <th width="70">用户名:</th>
                            <td><input class="common-text" placeholder="用户名" name="keywords" value="" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form action="${pageContext.request.contextPath}/usermanager/deleteusers" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="insert.html"><i class="icon-font"></i>新增作品</a>
                        <a id="batchDel" href="javascript:DeleteUsers('确定删除这些用户吗','myform')"><i class="icon-font"></i>批量删除</a>
                        <a id="updateOrd" href="javascript:void(0)"><i class="icon-font"></i>更新排序</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="5%"><input class="allChoose" name="" onclick="selectall(this)" type="checkbox"></th>
                            <th>ID</th>
                            <th>用户名</th>
                            <th>用户电话</th>
                            <th>用户收款账户</th>
                            <th>操作</th>

                        </tr>

                        <c:forEach var="u" items="${userlist}">

                        <tr>
                            <td class="tc"><input name="id[]" value="${u.user_id}" type="checkbox"></td>
                            <td>${u.user_id}</td>
                            <td>${u.user_name}</td>
                            <td>${u.user_phone}</td>
                            <td>${u.user_pay}</td>

                            <td>
                                <a class="link-update" href="${pageContext.request.contextPath}/usermanager/touserupdatemanager?id=${u.user_id}&cpage=${cpage}">修改</a>
                                <a class="link-del" href="javascript:Delete('确定删除该用户吗','deleteuser?id=${u.user_id}&cpage=${usercpage}')">删除</a>
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
                        共${usersum}条记录，当前${usercpage}/${userpage}页
                        <a href="${pageContext.request.contextPath}/usermanager/selectalluser?cp=1">首页</a>
                        <a href="${pageContext.request.contextPath}/usermanager/selectalluser?cp=${usercpage-1<1?1:usercpage-1}">上一页</a>
                        <a href="${pageContext.request.contextPath}/usermanager/selectalluser?cp=${usercpage+1>userpage?userpage:usercpage+1}">下一页</a>
                        <a href="${pageContext.request.contextPath}/usermanager/selectalluser?cp=${userpage}">尾页</a>

                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>
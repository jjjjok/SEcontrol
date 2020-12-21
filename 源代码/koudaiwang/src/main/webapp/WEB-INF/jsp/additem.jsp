<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2020/12/5
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

                    <ul class="sub-menu">

                        <li><a href="design.html"><i class="icon-font">&#xe005;</i>商品管理</a></li>

                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="/jscss/admin/design/">首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="/jscss/admin/design/">商品管理</a><span class="crumb-step">&gt;</span><span>新增商品</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="${pageContext.request.contextPath}/upload/additem" method="post" id="myform" name="myform" enctype="multipart/form-data">
                    <table class="insert-tab" width="100%">
                        <tbody><tr>
                            <th width="120"><i class="require-red">*</i>分类：</th>
                            <td>
                                <select name="fl" id="catid" class="required">
                                    <option value="">请选择</option>
                                    <option value="1">宿舍用品</option><option value="2">日常用具</option><option value="3">学习用具</option><option value="4">零食</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><i class="require-red">*</i>商品名称：</th>
                            <td>
                                <input class="common-text required" id="title" name="name" size="50" value="" type="text">
                            </td>
                        </tr>
                        <tr>
                            <th>购买价格：</th>
                            <td><input class="common-text" name="aprice" size="50" value="" type="text"></td>
                        </tr>
                        <tr>
                        <tr>
                            <th>出售价格：</th>
                            <td><input class="common-text" name="bprice" size="50" value="" type="text"></td>
                        </tr>
                        <tr>


                            <th><i class="require-red">*</i>上传图片：</th>
                            <td><input name="img" id="" type="file" multiple><!--<input type="submit" onclick="submitForm('/jscss/admin/design/upload')" value="上传图片"/>--></td>
                        </tr>
                        <tr>
                            <th>描述：</th>
                            <td><textarea name="content" class="common-textarea" cols="30" style="width: 98%;" rows="10"></textarea></td>
                        </tr>
                        <tr>
                        <tr>
                            <th>数量：</th>
                            <td><textarea name="num" class="common-textarea" cols="30" style="width: 98%;" rows="10"></textarea></td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                
                            </td>
                        </tr>
                        </tbody></table>
                </form>
            </div>
        </div>

    </div>
    <!--/main-->
</div>
</body>
</html>
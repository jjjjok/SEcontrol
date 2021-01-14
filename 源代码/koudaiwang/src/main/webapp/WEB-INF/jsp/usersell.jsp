<%--
  Created by IntelliJ IDEA.
  User: yy
  Date: 2020/12/5
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html" pageEncoding="GBK"%>
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


        </ul>
    </div>
</header>
<div class="logo">
    <div  class = logo_1>
        <img src="${pageContext.request.contextPath}/img/logo.png" width="70" height="70" style = vertical-align:sub />
        <span style = font-size:35px>口袋网</span>
        <span>发布商品</span>
    </div>
</div>


<div class="container clearfix">

    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list">
            </div>
            <div class="result-wrap">
                <div class="result-content">
                    <form action="${pageContext.request.contextPath}/upload/useradditem" method="post" id="myform" name="myform" enctype="multipart/form-data">
                        <table class="insert-tab" width="100%">
                            <tbody><tr>
                                <th width="120"><i class="require-red">*</i>分类：</th>
                                <td>
                                    <select name="fl" id="catid" class="required">
                                        <option value="">请选择</option>
                                        <option value="1">宿舍用品</option><option value="2">二手书本</option><option value="3">电子产品</option><option value="4">生活用品</option><option value="5">零食小吃</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>商品名称：</th>
                                <td>
                                    <input class="common-text required" id="title" name="name"  size="50" value="" type="text">
                                </td>
                            </tr>



                            <tr>
                                <th><i class="require-red">*</i>购买价格：</th>
                                <td><input class="common-text" name="aprice" size="50" value="" type="text"></td>
                            </tr>
                            <tr>
                            <tr>
                                <th><i class="require-red">*</i>出售价格：</th>
                                <td><input class="common-text" name="bprice" size="50" value="" type="text"></td>
                            </tr>
                            <tr>
                                <th><i class="require-red">*</i>数量：</th>
                                <td><input class="common-text" name="num" size="50" value="" type="text"></td>
                            </tr>



                            <th><i class="require-red">*</i>上传图片：</th>
                            <td><input name="img" id="" type="file" multiple><!--<input type="submit" onclick="submitForm('/jscss/admin/design/upload')" value="上传图片"/>--></td>
                            </tr>
                            <tr>
                                <th>描述：</th>
                                <td><textarea name="content" class="common-textarea" cols="30" style="width: 98%;" rows="10"></textarea></td>
                            </tr>

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
</div>

</body>
</html>
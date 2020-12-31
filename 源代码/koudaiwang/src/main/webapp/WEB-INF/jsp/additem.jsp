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

                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list">
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="${pageContext.request.contextPath}/upload/manadditem" method="post" id="myform" name="myform" enctype="multipart/form-data">
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
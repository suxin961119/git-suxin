<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<c:set var="app" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--当前页面更好支持移动端-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <%--引入bootstrap核心样式--%>
    <link rel="stylesheet" href="${app}/statics/boot/css/bootstrap.min.css">
    <%--引入jqgrid核心基础样式--%>
    <link rel="stylesheet" href="${app}/statics/jqgrid/css/ui.jqgrid.css">
    <%--引入jqgrid的bootstra皮肤--%>
    <link rel="stylesheet" href="${app}/statics/jqgrid/css/ui.jqgrid-bootstrap.css">
    <%--引入jquery核心js--%>
    <script src="${app}/statics/boot/js/jquery-3.3.1.min.js"></script>
    <%--引入boot核心js--%>
    <script src="${app}/statics/boot/js/bootstrap.min.js"></script>
    <%--引入jqgrid核心js--%>
    <script src="${app}/statics/jqgrid/js/jquery.jqGrid.min.js"></script>
    <%--引入i18njs--%>
    <script src="${app}/statics/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <%-- 引入ajax --%>
    <script src="${app}/statics/jqgrid/js/ajaxfileupload.js"></script>

    <script charset="utf-8" src="${app}/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${app}/kindeditor/lang/zh-CN.js"></script>

    <script src="${app}/echarts/echarts.min.js"></script>

    <script>
        $(function () {

            /*$("#btn").click(function(){
               //修改中心布局的内容
                $("#centerLayout").load("./user/lists_s.jsp");//引入一张页面到当前页面中
            });*/

        })
    </script>
</head>
<body>
<!-- 导航条 -->
<nav class="nav navbar-inverse">
    <div class="container-fluid">
        <!--导航标题-->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">持明法洲后台管理系统
                <small>v1.0</small>
            </a>
        </div>

        <!--导航条内容-->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎:<font color="aqua">${loginAdmin.username}</font></a></li>
                <li><a href="${app}/admin/exit">退出登录 <span class="glyphicon glyphicon-log-out"></span> </a></li>
            </ul>
        </div>
    </div>
</nav>

<!-- 页面主题内容 -->
<div class="container-fluid" style="padding-top: 20px">
    <div class="row">
        <!-- 菜单 -->
        <div class="col-sm-2">
            <!-- 手风琴控件 -->
            <div class="panel-group" id="accordion">
                <!-- 轮播图面板 -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="carouselPanel">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#carouseLists"
                               aria-expanded="true" aria-controls="collapseOne">
                                轮播图管理
                            </a>
                        </h4>
                    </div>
                    <div id="carouseLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a
                                        href="javascript:$('#centerLayout').load('${app}/carousel/main.jsp');" id="btn">所有轮播图</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--专辑面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="albumPanel">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#albumLists"
                               aria-expanded="true" aria-controls="collapseOne">
                                专辑管理
                            </a>
                        </h4>
                    </div>
                    <div id="albumLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a
                                        href="javascript:$('#centerLayout').load('${app}/album/main.jsp');" id="btn1">专辑数据表</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--文章面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="chapterPanel">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#chapterLists"
                               aria-expanded="true" aria-controls="collapseOne">
                                文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="chapterLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a
                                        href="javascript:$('#centerLayout').load('${app}/article/main.jsp');" id="btn5">文章表</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--用户面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="userPanel">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#userLists"
                               aria-expanded="true" aria-controls="collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="userLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a
                                        href="javascript:$('#centerLayout').load('${app}/user/main.jsp');" id="btn3">所有用户</a>
                                </li>
                                <li class="list-group-item"><a
                                        href="javascript:$('#centerLayout').load('${app}/user/show.jsp');" id="btn6">统计图形</a>
                                </li>

                            </ul>
                        </div>
                    </div>
                </div>

                <!--明星面板-->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="starPanel">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#starLists"
                               aria-expanded="true" aria-controls="collapseOne">
                                明星管理
                            </a>
                        </h4>
                    </div>
                    <div id="starLists" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a
                                        href="javascript:$('#centerLayout').load('${app}/star/main.jsp');" id="btn2">所有明星</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>


            </div>
        </div>

        <!-- 中心部分  -->
        <div class="col-sm-10" id="centerLayout">
            <!-- 巨幕 -->
            <div class="jumbotron">
                <h5>欢迎来到持明法洲后台管理系统!</h5>
            </div>
            <div class="col-sm-10" id="tu">
                <img src="${app}/img/10.png" alt="" class="img-rounded">
            </div>
        </div>


    </div>
</div>


<!-- 结尾 -->
<!-- 导航条 -->
<nav class="nav navbar-default" style="padding: 20px 300px">
    <div class="container-fluid">
        <!--导航标题-->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">持明法洲后台管理系统@Java166班 2019-10-27</a>
        </div>
    </div>
</nav>


</body>
</html>

<%--
  Created by Ericheel.
  Date: 2018/5/13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员后台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
</head>
<body background="${pageContext.request.contextPath}/img/bg1.png">
<form action="login" method="post">
    <div style="margin-top: 150px" class="container">
        <div class="row">
            <div class="col-lg-4 hidden-xs"></div>
            <font class="col-lg-4 hidden-xs" size="5" style="text-align: center" color="#6495ed">午安煎饼计划考勤系统管理后台</font>
            <font class="hidden-lg hidden-md hidden-sm col-xs-12" style="text-align: center" size="5" color="#6495ed">考勤系统管理后台</font>
            <div class="col-lg-4 hidden-xs"></div>
        </div>
        <br><br>
        <div class="row">
            <div class="col-lg-4 col-xs-1"></div>
            <div class="col-lg-4 col-xs-10 input-group">
                <font color="#6495ed">用户名:</font><input name="email" placeholder="email" type="text"
                                                        class="form-control">
            </div>
            <div class="col-lg-4 col-xs-1"></div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-4 col-xs-1"></div>
            <div class="col-lg-4 col-xs-10 input-group">
                <font color="#6495ed">密码:</font><input name="password" placeholder="password" type="password"
                                                       class="form-control">
            </div>
            <div class="col-lg-4 col-xs-1"></div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <br>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-5 col-xs-1"></div>
            <button type="submit" class="col-lg-2 col-xs-4 btn btn-default">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录
            </button>
            <div class="col-lg-5 col-xs-7" style="line-height: 35px"><font color="red">${ msg }</font></div>
        </div>
    </div>
</form>
</body>
</html>

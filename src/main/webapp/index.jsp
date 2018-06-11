<%--
  Created by Ericheel.
  Date: 2018/5/13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员后台</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
</head>
<body background="${pageContext.request.contextPath}/img/bg1.png">
<form action="login" method="post">
    <div style="margin-top: 200px" class="container">
        <div class="row hidden-xs hidden-sm" style="text-align: center">
            <font size="5" color="#6495ed">午安煎饼计划考勤系统管理后台</font>
        </div>
        <br><br>
        <div class="row">
            <div class="col-lg-4"></div>
            <div class="col-lg-4 input-group">
                <font color="#6495ed">用户名:</font><input name="email" placeholder="email" type="text"
                                                        class="form-control">
            </div>
            <div class="col-lg-4"></div>
        </div>
        <br>
        <div class="row">
            <div class="col-lg-4"></div>
            <div class="col-lg-4 input-group">
                <font color="#6495ed">密码:</font><input name="password" placeholder="password" type="password"
                                                       class="form-control">
            </div>
            <div class="col-lg-4"></div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <br>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4"></div>
            <div style="padding-left: 88px;" class="col-lg-4">
                <button type="submit" style="width: 200px" class="btn btn-default">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录
                </button>
            </div>
            <div class="col-lg-4" style="line-height: 35px"><font color="red">${ msg }</font></div>
        </div>
    </div>
</form>
</body>
</html>

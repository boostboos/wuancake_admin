<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">

    <div class="navbar-header">
        <a class="navbar-brand" href="#">考勤汇总</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li>
                <a href="# ">查看周报</a>
            </li>
            <li>
                <a href="# ">新增管理员</a>
            </li>
            <li>
                <a href="# ">新增导师</a>
            </li>
            <li>
                <a href="# ">清人汇总</a>
            </li>
        </ul>

        <form class="navbar-form navbar-right " style="padding-left: 50px; ">
            <div class="form-group ">
                <input type="text " class="form-control " placeholder="输入学员QQ号 ">
            </div>
            <button type="submit " class="btn btn-default ">学员检索</button>
        </form>
        <font style="line-height: 50px;padding-left: 200px;float: right">
            <c:if test="${isAdmin.auth == 1}">
                导师：
            </c:if>
            <c:if test="${isAdmin.auth == 2}">
                管理员：
            </c:if>
            <c:if test="${isAdmin.auth == 3}">
                超级管理员：
            </c:if>
            ${isAdmin.username}
        </font>


    </div>
</nav>

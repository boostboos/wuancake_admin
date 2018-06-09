<%--
  Created by IntelliJ IDEA.
  User: Ericheel
  Date: 2018/6/5
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>清人汇总</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
</head>
<body background="${pageContext.request.contextPath}/img/bg1.png">
<div class="container-fluid">
    <jsp:include page="guide.jsp"/>

    <table class="table table-bordered">
        <tr>
            <td>分组</td>
            <td>昵称</td>
            <td>QQ</td>
            <td>操作人</td>
            <td>操作时间</td>
        </tr>
        <c:forEach items="${kickList}" var="list">
            <tr>
                <td>${list.groupName}</td>
                <td>${list.userName}</td>
                <td>${list.QQ}</td>
                <td>${list.headsman}</td>
                <td>${list.modifyTime}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

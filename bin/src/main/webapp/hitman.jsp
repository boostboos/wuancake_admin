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
<body>
<div class="container-fluid">
    <jsp:include page="guide.jsp"/>

    <table class="table table-striped" style="text-align: center;table-layout:fixed;">
        <tr>
            <td>分组</td>
            <td>昵称</td>
            <td>QQ</td>
            <td>操作人</td>
            <td>操作时间</td>
        </tr>
        <c:forEach items="${pageBean.kickBeanList}" var="list">
            <tr>
                <td>${list.groupName}</td>
                <td>${list.userName}</td>
                <td>${list.QQ}</td>
                <td>${list.headsman}</td>
                <td>${list.modifyTime}</td>
            </tr>
        </c:forEach>
    </table>
    <div class="row " style="text-align: center; ">
        <ul class="pagination">
            <c:if test="${pageBean.currPage==1}">
                <li class="disabled">
                    <a>&laquo;</a>
                </li>
            </c:if>
            <c:if test="${pageBean.currPage!=1}">
                <li>
                    <a href="${pageContext.request.contextPath}/queryKickList?currPage = ${pageBean.currPage - 1}">&laquo;</a>
                </li>
            </c:if>

            <c:forEach varStatus="vs" begin="1" end="${pageBean.totalPage}">

                <c:if test="${pageBean.currPage == vs.count}">
                    <li class="active">
                </c:if>
                <c:if test="${pageBean.currPage != vs.count}">
                    <li>
                </c:if>
                <a href="${pageContext.request.contextPath}/queryKickList?currPage = ${vs.count}">
                    <span> ${vs.count} <span class="sr-only"></span></span>
                </a>
                </li>

            </c:forEach>
            <c:if test="${pageBean.currPage == pageBean.totalPage}">
                <li class="disabled">
                    <a>&raquo;</a>
                </li>
            </c:if>
            <c:if test="${pageBean.currPage != pageBean.totalPage}">
                <li>
                    <a href="${pageContext.request.contextPath}/queryKickList?currPage=${pageBean.currPage + 1}">&raquo;</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
</body>
</html>

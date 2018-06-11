<%--
  Created by IntelliJ IDEA.
  User: kellen
  Date: 2018/6/10
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="pagination">
    <c:if test="${pageBean.currPage==1}">
        <li class="disabled">
            <a>&laquo;</a>
        </li>
    </c:if>
    <c:if test="${pageBean.currPage!=1}">
        <li>
            <a href="${pageContext.request.contextPath}/queryGatherList?currPage = ${pageBean.currPage - 1}">&laquo;</a>
        </li>
    </c:if>

    <c:forEach varStatus="vs" begin="1" end="${pageBean.totalPage}">

        <c:if test="${pageBean.currPage == vs.count}">
            <li class="active">
        </c:if>
        <c:if test="${pageBean.currPage != vs.count}">
            <li>
        </c:if>
        <a href="${pageContext.request.contextPath}/queryGatherList?currPage = ${vs.count}">
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
            <a href="${pageContext.request.contextPath}/queryGatherList?currPage=${pageBean.currPage + 1}">&raquo;</a>
        </li>
    </c:if>
</ul>

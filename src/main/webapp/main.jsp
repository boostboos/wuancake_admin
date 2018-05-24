<%--
  Created by Ericheel.
  Date: 2018/5/13
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>管理员后台</title>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>

</head>
<body>
<div class="container-fluid">

    <jsp:include page="guide.jsp"/>

    <div class="row ">
        <form method="post" action="main">
            <script>
                $(function () {
                    //请求分组
                    $.post("${pageContext.request.contextPath}/showGroup", "", function (data) {
                        $(data).each(function (m, n) {
                            $("#groups").append("<option>" + n.group_name + "</option>")
                        })
                    }, "json")
                    //请求周数
                    $.post("${pageContext.request.contextPath}/showWeekNum", "", function (data) {
                        for (var i = data; i >= 1; i--) {
                            $("#weekNum").append("<option>" + i + "</option>");
                        }
                    }, "json")

                })


                //移除学员
                function sureRemove(id, currPage) {
                    var flag = confirm("确定移除?");
                    if (flag) {
                        window.location.href = "${pageContext.request.contextPath}/removeSb?user_id=" + id + "&currPage=" + currPage;
                    }
                }

            </script>

            <c:if test="${isAdmin.auth != 1}">
                <div class="col-lg-2 " style="padding-left: 5%; ">
                    分组：
                    <select id="groups" name="groups">
                        <option value='0'>选择分组</option>
                    </select>
                </div>
            </c:if>

            <div class="col-lg-2 ">
                截至周数：
                <select id="weekNum" name="weekNum">
                    <option value="0">选择周数</option>
                </select>
            </div>
            <div class="col-lg-8 ">
                <button type="submit " id="subSearch1">确定</button>
                <span id="warn"></span>
            </div>
        </form>
    </div>
    <div class="container-fluid">
        <div class="row ">
            <table class="table table-bordered">
                <tr class="active ">
                    <td>分组</td>
                    <td>昵称</td>
                    <td>QQ号</td>
                    <c:forEach items="${pageBean.gathers}" var="gather">
                        <c:forEach items="${gather.report4StatusMap}" var="week">
                            <td>第${week.key}周</td>
                        </c:forEach>
                    </c:forEach>
                    <td>操作</td>
                </tr>

                <c:forEach items="${pageBean.gathers}" var="gathers">
                    <tr>
                        <td>${gathers.group_name}</td>
                        <td>${gathers.user_name}</td>
                        <td>${gathers.QQ}</td>
                        <c:if test="${gathers.isUnderProtected == 1}">
                            <td colspan="4" class="right" style="text-align: center">处于保护期</td>
                        </c:if>
                        <c:if test="${gathers.isUnderProtected == 0}">
                            <c:forEach items="${gathers.report4StatusMap}" var="status">
                                <c:if test="${status.value == 1}">
                                    <td class="danger">未提交</td>
                                </c:if>
                                <c:if test="${status.value == 2}">
                                    <td class="right">已提交</td>
                                </c:if>
                                <c:if test="${status.value == 3}">
                                    <td class="warning">已请假</td>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <td><a href="#" onclick="sureRemove(${gathers.id},${pageBean.currPage})">移除</a></td>
                    </tr>
                </c:forEach>

            </table>
        </div>
    </div>

    <div class="row " style="text-align: center; ">
        <ul class="pagination ">

            <%--上一页--%>
            <c:if test="${pageBean.currPage!=1}">
                <li>
                <span>
                        <span aria-hidden="true ">
                            <a href="${pageContext.request.contextPath}/queryGatherList?currPage = ${pageBean.currPage - 1}">&laquo;</a>
                        </span>
                </span>
                </li>
            </c:if>

            <c:forEach varStatus="vs" begin="1" end="${pageBean.totalPage}">

                <c:if test="${pageBean.currPage == vs.count}">
                    <li class="active">
                </c:if>

                <li>
                    <a href="${pageContext.request.contextPath}/queryGatherList?currPage = ${pageBean.currPage}">
                        <span> ${vs.count} <span class="sr-only "></span></span>
                    </a>
                </li>
                </li>

            </c:forEach>
            <%--下一页--%>
            <c:if test="${pageBean.currPage != pageBean.totalPage}">
                <li>
                <span>
                    <span aria-hidden="true ">
                        <a href="${pageContext.request.contextPath}/queryGatherList?currPage=${pageBean.currPage + 1}">&raquo;</a>
                    </span>
                 </span>
                </li>
            </c:if>
        </ul>
    </div>
</div>
</body>
</html>

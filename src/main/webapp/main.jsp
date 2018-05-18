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
                <c:if test="${sessionList[0].auth == 1}">
                    导师：
                </c:if>
                <c:if test="${sessionList[0].auth == 2}">
                    管理员：
                </c:if>
                <c:if test="${sessionList[0].auth == 3}">
                    超级管理员：
                </c:if>
                ${sessionList[0].username}
            </font>


        </div>
    </nav>

    <div class="row ">
        <form method="post" action="main">
            <script>
                $(function () {
                    //异步请求分组和周数填充select组件

                    $.post("${pageContext.request.contextPath}/showGroup", "", function (data) {
                        $(data).each(function (m, n) {
                            $("#groups").append("<option>" + n.group_name + "</option>")
                        })
                    }, "json")

                    $.post("${pageContext.request.contextPath}/showWeekNum", "", function (data) {
                        for (var i = data; i >= 1; i--) {
                            $("#weekNum").append("<option>" + i + "</option>");
                        }
                    }, "json")

                })
            </script>

            <c:if test="${sessionList[0].auth != 1}">
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

                    <c:forEach items="${sessionList[1].gathers}" var="gather">
                        <c:forEach items="${gather.report4StatusMap}" var="week">
                            <td>第${week.key}周</td>
                        </c:forEach>
                    </c:forEach>

                    <td>操作</td>
                </tr>
                <c:forEach items="${sessionList[1].gathers}" var="gathers">
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
                        <td>移除</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

    <div class="row " style="text-align: center; ">
        <ul class="pagination ">

            <%--上一页--%>
            <c:if test="${sessionList[1].currPage!=1}">
                <li>
                <span>
                        <span aria-hidden="true "><a
                                href="${pageContext.request.contextPath}/queryGatherByPage?currPage=${sessionList[1].currPage-1}">&laquo;</a></span>
                </span>
                </li>
            </c:if>

            <c:forEach varStatus="vs" begin="1" end="${sessionList[1].totalPage}">

                <c:if test="${sessionList[1].currPage == vs.count}">
                    <li class="active">
                </c:if>

                <li>
                    <a href="${pageContext.request.contextPath}/queryGatherByPage?currPage=${sessionList[1].currPage}"> <span> ${vs.count} <span
                            class="sr-only "></span></span></a>
                </li>

            </c:forEach>
            <%--下一页--%>
            <c:if test="${sessionList[1].currPage != sessionList[1].totalPage}">
                <li>
                <span>
                    <span aria-hidden="true "><a
                            href="${pageContext.request.contextPath}/queryGatherByPage?currPage=${sessionList[1].currPage+1}">&raquo;</a></span>
                 </span>
                </li>
            </c:if>
        </ul>
    </div>
</div>
</body>
</html>

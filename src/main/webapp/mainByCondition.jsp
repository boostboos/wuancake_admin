<%--
  Created by IntelliJ IDEA.
  User: Ericheel
  Date: 2018/6/6
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>管理员后台</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
</head>
<body>
<script type="text/javascript">
    $(function () {
        $.post("${pageContext.request.contextPath}/showGroup", "", function (data) {
            $(data).each(function (m, n) {
                $("#groups").append("<option value=" + n.groupId + ">" + n.groupName + "</option>")
            })
        }, "json")
        $.post("${pageContext.request.contextPath}/showWeekNum", "", function (data) {
            for (var i = data; i >= 1; i--) {
                $("#weekNum").append("<option>" + i + "</option>");
            }
        }, "json")

    })

    function sureRemove(id, currPage) {
        var flag = confirm("确定?");
        if (flag) {
            window.location.href = "${pageContext.request.contextPath}/removeSbOnCondition?userId=" + id + "&currPage=" + currPage;
        }
    }

    function sureSub() {
        $("#sb").html()
        var $subGroup = $("#groups").find("option:selected").val()
        var $subWeek = $("#weekNum").find("option:selected").val()
        if ($subGroup == 0 && $subWeek == 0) {
            window.location.href = "${pageContext.request.contextPath}/queryGatherList?currPage = 1";
            return;
        }
        $("#sub").submit()
    }

</script>
<div class="container-fluid">

    <jsp:include page="guide.jsp"/>

    <div class="row ">

        <%--根据选择的分组/周数来查询考勤汇总--%>
        <form id="sub" method="post"
              action="${pageContext.request.contextPath}/queryGatherListByGroupAndWeek?currPage=1">
            <c:if test="${isAdmin.auth != 1}">
                <div class="col-lg-2" style="padding-left: 5%; ">
                    分组：
                    <select id="groups" name="groups">
                        <option value='0'>选择分组</option>
                    </select>
                </div>
            </c:if>

            <div class="col-lg-2">
                截至周数：
                <select id="weekNum" name="weekNum">
                    <option value="0">选择周数</option>
                </select>
            </div>
            <div class="col-lg-2">
                <button type="button" onclick="sureSub()">确定</button>
                <span id="warn"></span>
            </div>
            <div class="col-lg-6">
                <span id="sb"></span>
            </div>
        </form>

    </div>
    <div class="container-fluid">
        <div class="row ">
            <table class="table table-bordered" style="text-align: center">
                <tr class="active ">
                    <td>分组</td>
                    <td>昵称</td>
                    <td>QQ号</td>
                    <c:forEach var="vs" begin="${pageBean.weekNum-3}"
                               end="${pageBean.weekNum}" step="1">
                        <td>第${vs}周</td>
                    </c:forEach>
                    <td>操作</td>
                </tr>

                <c:forEach items="${pageBean.gathers}" var="gathers">
                    <tr>
                        <td>${gathers.groupName}</td>
                        <td>${gathers.userName}</td>
                        <td>${gathers.QQ}</td>
                        <c:if test="${gathers.isUnderProtected == 1}">
                            <td colspan="4" class="right" style="text-align: center">处于保护期</td>
                        </c:if>
                        <c:if test="${gathers.isUnderProtected == 2}">
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
        <jsp:include page="toolbar.jsp"/>
    </div>
</div>
</body>
</html>


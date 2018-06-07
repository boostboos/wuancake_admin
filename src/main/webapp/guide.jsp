<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">

    <div class="navbar-header">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/queryGatherList?currPage = 1">考勤汇总</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li>
                <a href="${pageContext.request.contextPath}/lookWeeklyReport.jsp">查看周报</a>
            </li>
            <c:if test="${isAdmin.auth==3}">
                <li>
                    <a href="${pageContext.request.contextPath}/addAdmin.jsp ">新增管理员</a>
                </li>
            </c:if>
            <c:if test="${isAdmin.auth!=1}">
                <li>
                    <a href="${pageContext.request.contextPath}/addTutor.jsp ">新增导师</a>
                </li>
            </c:if>
            <li>
                <a href="${pageContext.request.contextPath}/hitman47">清人汇总</a>
            </li>
        </ul>

        <form class="navbar-form navbar-left" style="padding-left: 100px;">
            <div class="form-group">
                <input id="qq" type="text" class="form-control" placeholder="输入学员QQ号">
            </div>

            <a tabindex="0"
               class="btn btn-default"
               role="button"
               data-placement="bottom"
               data-toggle="popover"
               data-trigger="focus"
               onclick="sure()">
                学员检索
            </a>&nbsp;&nbsp;&nbsp;<span id="fck"></span>
            <script type="text/javascript">
                function sure() {
                    var reg = "[1-9]([0-9]{5,11})";
                    if (!$("#qq").val().match(reg)) {
                        $("#fck").html("");
                        $("#fck").html("<font color='red'>QQ格式错误</font>");
                        $('[data-toggle="popover"]').popover({
                            content: ""
                        })
                    } else {
                        $("#fck").html("");
                        var qq = $("#qq").val()
                        $.post("${pageContext.request.contextPath}/searchInfoByQQ", {"qq": qq}, function (data) {
                            if (data == null) {
                                $("#fck").html("<font color='green'>没有此学员</font>");
                            } else {
                                $('[data-toggle="popover"]').popover({
                                    content: "QQ:" + data.QQ + "\n" + "昵称:" + data.userName + "\n" + "分组:" + data.groupName
                                })
                            }
                        }, "json");
                    }
                }
            </script>
        </form>
        <font style="line-height: 50px;padding-right: 50px;float: right">
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
            &nbsp;&nbsp;&nbsp;
            <a href="${pageContext.request.contextPath}/logout">注销</a>
        </font>


    </div>
</nav>

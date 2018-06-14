<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="./js/windowLoad.js"></script>
<div class="container-fluid">
    <nav class="navbar navbar-default">

        <div class="navbar-header" data-toggle="collapse" data-target="#navBar">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/queryGatherList?currPage = 1">考勤汇总</a>
        </div>
        <div class="collapse navbar-collapse" id="navBar">
            <ul class="nav navbar-nav dropdown">
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
                    <a href="${pageContext.request.contextPath}/queryKickList?currPage=1">清人汇总</a>
                </li>
            </ul>

            <form class="navbar-form navbar-left dropdown">
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
                <span id="al" style="display: none">
                </span>
                <script type="text/javascript">
                    function sure() {
                        var reg = "[1-9]([0-9]{5,11})";
                        $("#al").prop("style", "display:none")
                        if (!$("#qq").val().match(reg)) {
                            $("#fck").html("");
                            $("#fck").html("<font color='red'>QQ格式错误</font>");

                        } else {
                            $("#fck").html("");
                            $("#al").html("");
                            var qq = $("#qq").val()
                            $.post("${pageContext.request.contextPath}/searchInfoByQQ", {"qq": qq}, function (data) {
                                if (data.QQ == "aptx4869") {
                                    $("#fck").html("<font color='red'>没有此学员</font>");
                                } else {
                                    $("#al").html("<font color='green'>学员信息&nbsp;&nbsp;</font>" + data.userName + "&nbsp;&nbsp;" + data.groupName);
                                    //$("#al").html("<table style='text-align: center'><tr><td>QQ</td><td>用户名</td><td>分组</td></tr><tr><td>" + data.QQ + "</td><td>" + data.userName + "</td><td>" + data.groupName + "</td></tr></table>");
                                    $("#al").slideDown(300);
                                }
                            }, "json");

                        }
                    }

                </script>
            </form>
            <font style="line-height: 50px;float: right">
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
                <a href="${pageContext.request.contextPath}/logout">注销</a>&nbsp;&nbsp;&nbsp;
                <a href="${pageContext.request.contextPath}/resetPwd.jsp">修改密码</a>
            </font>
        </div>

    </nav>
</div>
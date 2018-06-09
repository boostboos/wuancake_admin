<%--
  Created by IntelliJ IDEA.
  User: Ericheel
  Date: 2018/5/24
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加导师</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
</head>
<body background="${pageContext.request.contextPath}/img/bg1.png">
<script type="text/javascript">

    $(function () {
        //请求分组
        $.post("${pageContext.request.contextPath}/showGroup", "", function (data) {
            $(data).each(function (m, n) {
                $("#groups").append("<option value='" + n.groupId + "'>" + n.groupName + "</option>")
            })
        }, "json")
    })

    function sureAdd() {
        $("#bad").html("");
        $("#good").html("");
        var v1 = $("#groups").find("option:selected").val();
        var v2 = $("#email").val();
        var v3 = $("#username").val();
        var v4 = $("#password").val();
        if (v1 == 0 || v2 == "" || v3 == "" || v4 == "") {
            $("#bad").append("分组 昵称 邮箱 密码都填写了吗")
        } else {
            var flg = confirm("确定添加?");
            if (flg) {
                $("#group").val($("#groups").find($("option:selected")).val());
                $("#sub").submit();
            }
        }
    }

</script>
<jsp:include page="guide.jsp"/>
<div class="container-fluid">
    <form id="sub" action="${pageContext.request.contextPath}/addTutor" method="post">
        <input type="hidden" name="groupId" id="group"/>
        <div style="margin-top: 120px" class="container">
            <div class="row">
                <div class="col-lg-2 " style="padding-left: 5%; ">
                    分组：
                    <select id="groups">
                        <option value='0'>选择分组</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-4 input-group">
                    <font color="#6495ed" size="3">昵称:</font><input id="username" name="username" placeholder="午安网用户名"
                                                                    type="text"
                                                                    class="form-control">
                </div>
                <div class="col-lg-4"></div>
            </div>


            <br>
            <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-4 input-group">
                    <font color="#6495ed" size="3">邮箱:</font><input id="email" name="email" placeholder="邮箱作为登录名"
                                                                    type="text"
                                                                    class="form-control">
                </div>
                <div class="col-lg-4"></div>
            </div>


            <br>
            <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-4 input-group">
                    <font color="#6495ed" size="3">密码:</font><input id="password" name="password" placeholder="密码"
                                                                    type="text"
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
                <div style="padding-left: 88px;" class="col-lg-3">
                    <button type="button" onclick="sureAdd()" style="width: 200px" class="btn btn-default">新&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;增
                    </button>
                </div>
                <div class="col-lg-5" style="line-height: 35px"><font id="bad" color="red">${ authBadInfo }</font></div>
                <div class="col-lg-5" style="line-height: 35px"><font id="good" color="green">${ authGoodInfo }</font>
                </div>
            </div>
        </div>
    </form>
</div>
</body>

</html>

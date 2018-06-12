<%--
  Created by IntelliJ IDEA.
  User: Ericheel
  Date: 2018/5/24
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加管理员</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
</head>
<body>
<script type="text/javascript">
    $(function () {
        $("#bad").html("");
        $("#good").html("");
    })

    function sureAdd() {
        $("#bad").html("");
        $("#good").html("");
        var v2 = $("#email").val();
        var v3 = $("#username").val();
        var v4 = $("#password").val();
        if (v2 == "" || v3 == "" || v4 == "") {
            $("#bad").append("昵称 邮箱 密码都填写了吗")
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
    <form id="sub" action="${pageContext.request.contextPath}/addAdmin" method="post">
        <input type="hidden" name="groupId" id="group"/>
        <div style="margin-top: 120px" class="container">
            <div class="row">
                <div class="col-lg-4 col-xs-1"></div>
                <div class="col-lg-4 col-xs-10 input-group">
                    <font color="#6495ed" size="3">昵称:</font><input id="username" name="username" placeholder="午安网用户名"
                                                                    type="text"
                                                                    class="form-control">
                </div>
                <div class="col-lg-4 col-xs-1"></div>
            </div>
            <br>
            <div class="row">
                <div class="col-lg-4 col-xs-1"></div>
                <div class="col-lg-4 col-xs-10 input-group">
                    <font color="#6495ed" size="3">邮箱:</font><input id="email" name="email" placeholder="邮箱作为登录名"
                                                                    type="text"
                                                                    class="form-control">
                </div>
                <div class="col-lg-4 col-xs-1"></div>
            </div>


            <br>
            <div class="row">
                <div class="col-lg-4 col-xs-1"></div>
                <div class="col-lg-4 col-xs-10 input-group">
                    <font color="#6495ed" size="3">密码:</font><input id="password" name="password" placeholder="密码"
                                                                    type="password"
                                                                    class="form-control">
                </div>
                <div class="col-lg-4 col-xs-1"></div>
            </div>


            <div class="row">
                <div class="col-lg-12">
                    <br>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-5 col-xs-1"></div>
                <button class="col-lg-2 col-xs-4  btn btn-default" type="button" onclick="sureAdd()"
                        class="btn btn-default">新&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;增
                </button>
                <div class="col-lg-5" style="line-height: 35px"><font id="bad"
                                                                      color="red">${ authBadInfo }</font></div>
                <div class="col-lg-5" style="line-height: 35px"><font id="good"
                                                                      color="green">${ authGoodInfo }</font>
                </div>
            </div>


        </div>
    </form>
</div>
</body>

</html>


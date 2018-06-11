<%--
  Created by IntelliJ IDEA.
  User: kellen
  Date: 2018/6/11
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
</head>
<body>
<div class="container-fluid">
    <jsp:include page="guide.jsp"/>
    <form id="sub" action="${pageContext.request.contextPath}/resetPwd" method="post">
        <input type="hidden" name="groupId" id="group"/>
        <div style="margin-top: 120px" class="container">
            <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-4 input-group">
                    <font color="#6495ed" size="3">原密码:</font><input id="oldPwd" name="oldPwd"
                                                                     type="text"
                                                                     class="form-control">
                </div>
                <div class="col-lg-4"></div>
            </div>
            <br>
            <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-4 input-group">
                    <font color="#6495ed" size="3">新密码:</font><input id="newPwd" name="newPwd"
                                                                     type="password"
                                                                     class="form-control">
                </div>
                <div class="col-lg-4"></div>
            </div>


            <br>
            <div class="row">
                <div class="col-lg-4"></div>
                <div class="col-lg-4 input-group">
                    <font color="#6495ed" size="3">确认新密码:</font><input id="rePwd" name="rePwd"
                                                                       type="password"
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
                    <button type="button" onclick="sureSet()" style="width: 200px" class="btn btn-default">修&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;改
                    </button>
                </div>
                <div class="col-lg-5" style="line-height: 35px"><font id="bad" color="red">${ authBadInfo }</font></div>
                <div class="col-lg-5" style="line-height: 35px"><font id="good" color="green">${ authGoodInfo }</font>
                </div>
            </div>
            <script type="text/javascript">
                function sureSet() {
                    $("#bad").html("");
                    $("#good").html("");
                    var v1 = $("#oldPwd").val();
                    var v2 = $("#newPwd").val();
                    var v3 = $("#rePwd").val();

                    if (v1 == "" || v2 == "" || v3 == "") {
                        $("#bad").append("不要留空")
                    } else if (v2 != v3) {
                        $("#bad").append("两次新密码不一致")
                    } else if (v1 == v2) {
                        $("#bad").append("原密码和新密码不能相同")
                    } else {
                        $("#sub").submit();
                    }
                }
            </script>

        </div>
    </form>
</div>
</body>
</html>

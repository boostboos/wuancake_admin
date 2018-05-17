<%--
  Created by IntelliJ IDEA.
  User: kellen
  Date: 2018/5/13
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员后台</title>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
</head>
<body>

<div class="row ">
    <div class="col-lg-2 " style="padding-left: 5%; ">
        分组：
        <select>
            <option>Java</option>
        </select>
    </div>
    <div class="col-lg-2 ">
        截至周数：
        <select>
            <option>第4周</option>
        </select>
    </div>
    <div class="col-lg-8 ">
        <button type="submit ">确定</button>
    </div>
</div>
<div class="row ">
    <table class="table table-hover " style="width: 90%; margin: 0 auto; ">
        <tr class="active ">
            <td>分组</td>
            <td>昵称</td>
            <td>QQ号</td>
            <td>第1周</td>
            <td>第2周</td>
            <td>第3周</td>
            <td>第4周</td>
            <td>操作</td>
        </tr>
        <tr class="danger ">
            <td>Java</td>
            <td>余古夕</td>
            <td>283773833</td>
            <td>已提交</td>
            <td>已提交</td>
            <td>未提交</td>
            <td>未提交</td>
            <td>移除</td>
        </tr>
    </table>
</div>

<div class="row " style="text-align: center; ">
    <ul class="pagination ">
        <li class="disabled ">
      				<span>
        				<span aria-hidden="true ">&laquo;</span>
     					 </span>
        </li>

        <li class="active ">
            <span>1 <span class="sr-only "></span></span>
        </li>
        <li>
            <span>2 <span class="sr-only "></span></span>
        </li>
        <li>
            <span>3 <span class="sr-only "></span></span>
        </li>

        <li class="disabled ">
      				<span>
        				<span aria-hidden="true ">&raquo;</span>
     					 </span>
        </li>
    </ul>
</div>
</div>

</body>
</html>

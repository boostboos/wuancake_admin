<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="org.wuancake.entity.ReportBean" import="java.util.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
<title>查看周报</title>
</head>
<body>
	<jsp:include page="guide.jsp" />
	<form method="post" action="/lookReport" id="group_and_week">
		<div class="col-lg-2 " style="padding-left: 5%;">
			分组 <select id="groups" name="groups">
				<option value=-1>选择分组</option>
			</select>
		</div>
		<div class="col-lg-2 ">
			周数 <select id="weeks" name="weeks">
				<option value=-1>选择周数</option>
			</select>
		</div>
		<div class="col-lg-8 ">
			<input type="submit" value="确定" />
		</div>
	</form>
	<div class="container-fluid">
		<div class="row">
			<table class="table table-bordered">
				<tr class="active">
					<td>分组</td>
					<td>昵称</td>
					<td>周数</td>
					<td>考勤情况</td>
					<td>考勤内容</td>
				</tr>
				<c:forEach items="${report}" var="reportBean">
					<tr>
						<td>${reportBean.groupName}</td>
						<td>${reportBean.userName}</td>
						<td>${reportBean.weekNum}</td>
						<td>${reportBean.status}</td>
						<td>${reportBean.text}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
<script type="text/javascript" src="./js/windowLoad.js"></script>
</html>
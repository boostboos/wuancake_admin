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
<%!private String displayStatus(int status) {
		return status == 2 ? "已提交" : status == 3 ? "已请假" : "未提交";
	}%>
<body>
	<div class="container-fluid">
		<jsp:include page="guide.jsp" />
		<div class="container-fluid" style="padding-top: 10px">
			<form method="post" action="/lookReport" id="form" name="form">
				<div class="col-lg-2 " style="padding-left: 5%;">
					分组 
					<select id="groups" name="groups">
						<option value=-1>选择分组</option>
					</select>
				</div>
				<div class="col-lg-2 ">
					周数
					<select id="weeks" name="weeks">
						<option value=-1>选择周数</option>
					</select>
				</div>
				<div class="col-lg-8 ">
					<input type="submit" value="确定" />
				</div>
			</form>
			<div class="container-fluid">
				<div class="row">
					<table class="table table-bordered"
						style="text-align: center; table-layout: fixed;">
						<tr class="active">
							<td>分组</td>
							<td>昵称</td>
							<td>周数</td>
							<td>考勤情况</td>
							<td>考勤内容</td>
						</tr>
						<%
							//每页大小
							int size = 8;
							int i = 1;
							String color = "";
							try {
								if (request.getSession().getAttribute("report") != null) {
									List<ReportBean> list = (List) request.getSession().getAttribute("report");
									for (ReportBean reportBean : list) {
										if ((i % 2) == 0) {
											out.print("<tr class='active'>");
										} else {
											out.print("<tr>");
										}
										i++;
										out.print("<td>" + reportBean.getGroupName() + "</td>");
										out.print("<td>" + reportBean.getUserName() + "</td>");
										out.print("<td>" + reportBean.getWeekNum() + "</td>");
										if (reportBean.getStatus() == 1) {
											color = "class='danger'";
										} else if (reportBean.getStatus() == 2) {
											color = "style='background-color: #a6e1ec'";
										} else if (reportBean.getStatus() == 3) {
											color = "class='warning'";
										}
										out.print("<td " + color + ">" + displayStatus(reportBean.getStatus()) + "</td>");
										out.print("<td>" + reportBean.getText() + "</td>");
										out.print("<tr>");
									}
								}
							} catch (NullPointerException e) {
								response.sendRedirect("/lookWeeklyReport");
							}
						%>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//网页加载时从服务器获得分组情况和当前周数
	window.onload = function() {
		var url = "/showGroup";
		var request = new XMLHttpRequest();
		request.open("POST", url);
		request.send(null);
		request.onload = function() {
			if (request.status == 200) {
				displayContent(request.responseText);
			}
		};
		var url2 = "/showWeekNum";
		var request2 = new XMLHttpRequest();
		request2.open("POST", url2);
		request2.send(null);
		request2.onload = function() {
			if (request2.status == 200) {
				showWeekNum(request2.responseText);
			}
		}
	}
	//将分组显示在下拉列表中
	function displayContent(content) {
		var groups = JSON.parse(content);
		var p = document.getElementById("groups");
		for (var i = 0; i < groups.length; i++) {
			var option = document.createElement("option");
			option.value = groups[i].groupId;
			option.innerHTML = groups[i].groupName;
			p.add(option, p.options[null]);
		}
	}
	//显示周数
	function showWeekNum(weekNum) {
		var p = document.getElementById("weeks");
		for (var i = weekNum; i >= 1; i--) {
			var option = document.createElement("option");
			option.value = i;
			option.innerHTML = i;
			p.add(option, p.options[null]);
		}
	}
</script>
</html>
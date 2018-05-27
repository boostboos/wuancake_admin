<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看周报</title>
</head>
<body>
	分组
	<script>
		window.onload = function() {
			var url = "${pageContext.request.contextPath}/showGroup";
			var request = new XMLHttpRequest();
			request.onload = function() {
				if(request.status == 200) {
					displayContent(request.responseText);
				}
			};
			request.open("POST",url);
			request.send(null);
		}
		function displayContent(content) {
			var p = document.getElementById("content");
			p.innerHTML = content;
		}
		
	</script>
	<div id = "content"></div>
	<select>
		<option>全部</option>

	</select>

</body>
</html>
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
			var url = "${request.contextPath}/showGroup";
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
			var groups = JSON.parse(content);
			var p = document.getElementById("groups");
			for(var i = 0; i < groups.length; i++) {
				//window.alert(groups[i].group_id);
			}
			console.log(groups);
		}
	</script>
	<select id = "groups">
		<option>全部</option>
	</select>

</body>
</html>
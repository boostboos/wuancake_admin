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
	for(var i = weekNum; i >= 1; i--) {
		var option = document.createElement("option");
		option.value = i;
		option.innerHTML = i;
		p.add(option, p.options[null]);
	}
}

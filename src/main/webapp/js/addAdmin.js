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

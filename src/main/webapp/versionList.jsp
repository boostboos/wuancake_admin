<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>APP版本列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-theme.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/toastr.min.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/toastr.min.js"></script>

    <script type="text/javascript">
        toastr.options.positionClass = 'toast-bottom-right';

        $(function () {
            $('#myModal').on('hide.bs.modal', function () {
                $("#form-version :input").not(":button, :submit, :reset, :hidden").val("").removeAttr("checked").remove("selected");//核心
            });
         });

        function Refresh() {
        	window.location.replace(location);
        };

        function update(vid){
            $("#vid").val(vid);
            $.ajax({
                type: 'POST',
                data: {vid: vid},
                url: '${pageContext.request.contextPath}/findOneVersion',
                success: function(result) {
                    if(result.code == 0){
                        $("#versionNum").val(result.data.v);
                        $("#versionLink").val(result.data.url);
                    }else{
                        toastr.error(result.msg);
                    }
                }
            });
        };

        function save(){
            $.ajax({
                type: 'POST',
                data: $("#form-version").serialize(),
                url: $("#form-version").attr('action'),
                success: function(result) {
                    if(result.code == 0){
                        $('#myModal').modal('hide');
                        toastr.success(result.msg);
                        //提交成功后，刷新页面
                        setTimeout('Refresh()',2000);
                    }else{
                        toastr.error(result.msg);
                    }
                }
            });
        };
    </script>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="guide.jsp"/>

    <table class="table table-bordered table-hover">
        <tr>
            <td>编号</td>
            <td>版本号</td>
            <td>链接地址</td>
            <td>操作</td>
        </tr>
        <c:forEach items="${list}" var="version">
            <tr>
                <td>${version.vid}</td>
                <td>${version.v}</td>
                <td>${version.url}</td>
                <td>
                    <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal">新增</button>
                    <button type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#myModal" onclick="update('${version.vid}')">修改</button>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="row " style="text-align: center;">
        <ul class="pagination">
            <c:if test="${page.currPage==1}">
                <li class="disabled">
                    <a>&laquo;</a>
                </li>
            </c:if>
            <c:if test="${page.currPage!=1}">
                <li>
                    <a href="${pageContext.request.contextPath}/findAllVersion?currPage = ${page.currPage - 1}">&laquo;</a>
                </li>
            </c:if>
            <c:forEach varStatus="vs" begin="1" end="${page.totalPage}">
                <c:if test="${page.currPage == vs.count}">
                    <li class="active">
                </c:if>
                <c:if test="${page.currPage != vs.count}">
                    <li>
                </c:if>
                    <a href="${pageContext.request.contextPath}/findAllVersion?currPage = ${vs.count}">
                        <span> ${vs.count} <span class="sr-only"></span></span>
                    </a>
                </li>
            </c:forEach>
            <c:if test="${page.currPage == page.totalPage}">
                <li class="disabled">
                    <a>&raquo;</a>
                </li>
            </c:if>
            <c:if test="${page.currPage != page.totalPage}">
                <li>
                    <a href="${pageContext.request.contextPath}/findAllVersion?currPage=${page.currPage + 1}">&raquo;</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">维护APP版本信息</h4>
      </div>
      <div class="modal-body">
        <form class="form-horizontal" id="form-version" action="${pageContext.request.contextPath}/saveVersion" method="post">
          <input type="hidden" id="vid" name="vid" value="">
          <div class="form-group">
            <label for="versionNum" class="col-sm-3 control-label">APP版本号：</label>
            <div class="col-sm-8">
              <input type="text" class="form-control" id="versionNum" name="v" placeholder="请输入版本号...">
            </div>
          </div>
          <div class="form-group">
            <label for="versionLink" class="col-sm-3 control-label">链接地址：</label>
            <div class="col-sm-8">
              <input type="url" class="form-control" id="versionLink" name="url" placeholder="请输入链接地址...">
            </div>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="save()">保存</button>
      </div>
    </div>
  </div>
</div>

</body>
</html>
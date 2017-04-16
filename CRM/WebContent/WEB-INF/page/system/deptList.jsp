<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/page.tld" prefix="m" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>部门管理</title>
		 <link href="${path }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<link rel="stylesheet" href="${path }/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${path }/css/global.css" media="all">
</head>
<body>

<div class="container">
	<div class="col-md-12 column">
		<blockquote class="layui-elem-quote">部门列表</blockquote>
		 <fieldset>
	 		<legend>
				<span class="glyphicon glyphicon-th-list" style="color: rgb(9, 109, 169);"> 部门列表</span>
				<a href="" class="btn btn-primary" data-toggle="modal" data-target="#modal" id="add" onclick="setAddValue()"><em class="glyphicon glyphicon-plus"></em> 新增</a> 
			</legend>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>部门名称</th>
						<th>部门负责人</th>
						<th>操作</th>
					</tr>
				</thead>		
				<tbody>
				<s:iterator value="pagevo.data" var="item">
					<tr>
						<td>${item.name }</td>
						<td>${item.principal }</td>
						<td>
						<a href="" data-toggle="modal" data-target="#modal" id="update" onclick="setUpdateValue('${item.id }','${item.name }','${principal }')">修改</a>
						<input type="hidden" value="" id="">
						<a href="">删除</a>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
		</fieldset>				
	</div>
	<!-- 模态框，新增部门 -->
	<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <form action="doUpdateDept.action" method="post">
            <input type="hidden" name="id" id="deptId">
            <div class="modal-body">
            <label for="name" class="col-sm-12 control-label">部门名称</label>
				<input type="text" name="name" class="form-control" id="deptName">
			 <label for="principal" class="col-sm-12 control-label">部门负责人</label>
				<input type="text" name="principal" class="form-control" id="deptPrincipal">
			</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" class="btn btn-primary">保存</button>
            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
	
</div>

<script src="${path }/bootstrap/js/jquery.min.js"></script>
<script src="${path }/bootstrap/js/bootstrap.min.js"></script>
</body>
<script type="text/javascript">
	function setUpdateValue(id,name,principal){
		$("#deptName").attr("value",name);
		$("#deptPrincipal").attr("value",principal);
		$("#deptId").attr("value",id);
		$("#myModalLabel").text("修改部门");
	}
	
	function setAddValue(){
		$("#myModalLabel").text("添加部门");
	}
</script>
</html>
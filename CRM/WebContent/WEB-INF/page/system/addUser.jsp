<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>添加用户</title>
	    <link href="${path }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<link rel="stylesheet" href="${path }/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${path }/css/global.css" media="all">
	</head>
	<body>
	<s:if test="msg!=null">
		<script type="text/javascript">
			alert('${msg}');
		</script>
	</s:if>
		<div class="container">
			<blockquote class="layui-elem-quote">新建-用户</blockquote>
			<form class="form-horizontal" action="doAddUser.action" method="post">
				<fieldset>
					<legend><span class="glyphicon glyphicon-th-list" style="color: rgb(9, 109, 169);"> 添加用户</span>
						<div class="btn-group">
							<button type="submit" class="btn btn-default">保存</button>
							<button type="button" class="btn btn-primary">返回</button>
						</div>
					</legend>
				<div class="form-group">
					<label for="account" class="col-sm-2 control-label">账号</label>
				    <div class="col-sm-4">
				      	<input name="account" type="text" class="form-control" id="account" required="required">
				    </div>
				    <label for="password" class="col-sm-2 control-label">密码</label>
				    <div class="col-sm-4">
				      	<input name="password" type="password" class="form-control" id="password">
				    </div>
				</div>
				<div class="form-group">
					<label for="telephone" class="col-sm-2 control-label">手机号</label>
				    <div class="col-sm-4">
				      	<input name="telephone" type="tel" class="form-control" id="telephone">
				    </div>
				    <label for="email" class="col-sm-2 control-label">邮箱</label>
				    <div class="col-sm-4">
				      	<input name="email" type="email" class="form-control" id="email">
				    </div>
				 </div>
				 <div class="form-group">
				 	<label for="name" class="col-sm-2 control-label">用户姓名</label>
				    <div class="col-sm-4">
						<input name="name" type="text" class="form-control" id="name">
				    </div>
					<label for="admin" class="col-sm-2 control-label">是否管理员</label>
				    <div class="col-sm-4">
				      	<input name="admin" type="radio"  id="admin" value="1">是
				      	<input name="admin" type="radio"  id="admin" value="0" checked="checked">否
				    </div>
				 </div>
				 <div class="form-group">
				 	<label for="creater" class="col-sm-2 control-label">所属部门</label>
				    <div class="col-sm-4">
				      	<select id="dept" name="deptId" class="form-control"></select>
				    </div>
				 </div>
				</fieldset>
			</form>
		</div>
		<script src="${path }/bootstrap/js/jquery.min.js"></script>
		<script src="${path }/bootstrap/js/bootstrap.min.js"></script>
	</body>
	<script type="text/javascript">
	$.getJSON("http://localhost:8080/CRM/dept/getDept.action",function(data){
		$.each(data,function(index,obj){
			var Opt = $("<option></option>");
			Opt.attr("value",obj.id).text(obj.name);
			$("#dept").append(Opt);	
		});
	});
	</script>
</html>

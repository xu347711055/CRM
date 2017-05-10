<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>修改用户</title>
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
			<blockquote class="layui-elem-quote">修改-用户</blockquote>
			<form class="form-horizontal" action="doUpdateUser.action" method="post">
				<input type="hidden" value="${user.password }" name="password">
				<input type="hidden" value="${user.id }" name="id">
				<input type="hidden" value="${user.account }" name="account">
				<fieldset>
					<legend><span class="glyphicon glyphicon-th-list" style="color: rgb(9, 109, 169);"> 修改用户</span>
						<div class="btn-group">
							<button type="submit" class="btn btn-default">保存</button>
							<button type="button" class="btn btn-primary" onclick="goback()">返回</button>
						</div>
					</legend>
				<div class="form-group">
					<label for="account" class="col-sm-2 control-label">账号</label>
				    <div class="col-sm-4">
				      	<input value="${user.account }" name="account" type="text" class="form-control" id="account" disabled="disabled">
				    </div>
				   
				</div>
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">修改密码</label>
				    <div class="col-sm-4">
				      	<input name="chpwd" type="password" class="form-control" id="password">
				    </div>
				    <label for="confirm" class="col-sm-2 control-label">确认密码</label>
				    <div class="col-sm-4">
				      	<input name="confirm" type="password" class="form-control" id="confirm">
				    </div>
				</div>
				<div class="form-group">
					<label for="telephone" class="col-sm-2 control-label">手机号</label>
				    <div class="col-sm-4">
				      	<input value="${user.telephone }" name="telephone" type="tel" class="form-control" id="telephone">
				    </div>
				    <label for="email" class="col-sm-2 control-label">邮箱</label>
				    <div class="col-sm-4">
				      	<input value="${user.email }" name="email" type="email" class="form-control" id="email">
				    </div>
				 </div>
				 <div class="form-group">
				 	<label for="name" class="col-sm-2 control-label">用户姓名</label>
				    <div class="col-sm-4">
						<input value="${user.name }" name="name" type="text" class="form-control" id="name">
				    </div>
					<label for="admin" class="col-sm-2 control-label">是否管理员</label>
				    <div class="col-sm-4">
				    <s:if test="user.admin==1">
				    	<input name="admin" type="radio"  id="admin" value="1" checked="checked" >是
				      	<input name="admin" type="radio"  id="admin" value="0" >否
				    </s:if>
				    <s:else>
				    	<input name="admin" type="radio"  id="admin" value="1">是
				      	<input name="admin" type="radio"  id="admin" value="0" checked="checked">否
				    </s:else>
				    </div>
				 </div>
				 <div class="form-group">
				 	<label for="dept" class="col-sm-2 control-label">所属部门</label>
				    <div class="col-sm-4">
				      	<select id="dept" name="deptId" class="form-control"></select>
				    </div>
				    <label for="dept" class="col-sm-2 control-label">角色</label>
				    <div class="col-sm-4">
				     	<input type="text" value="${user.role.name }" disabled="disabled" class="form-control">
				     	<input type="hidden" name="roleId" value="${user.role.id }" >
				    </div>
				 </div>
				</fieldset>
			</form>
		</div>
		<script src="${path }/bootstrap/js/jquery.min.js"></script>
		<script src="${path }/bootstrap/js/bootstrap.min.js"></script>
	</body>
	<script type="text/javascript">
	function goback(){
		history.go(-1);
	}
	
	$.getJSON("http://localhost:8080/CRM/dept/getDept.action",function(data){
		$("#dept").append($("<option>--请选择--</option>"));
		$.each(data,function(index,obj){
			var Opt = $("<option></option>");
			Opt.attr("value",obj.id).text(obj.name);
			if(obj.name=='${user.dept.name}'){
				Opt.attr("selected","selected");
			}
			$("#dept").append(Opt);	
		});
	});
	</script>
</html>

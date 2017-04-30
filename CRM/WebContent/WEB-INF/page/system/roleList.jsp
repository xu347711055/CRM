<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
		<title>权限管理</title>
		 <link href="${path }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<link rel="stylesheet" href="${path }/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${path }/css/global.css" media="all">
</head>
<body>

<div class="container">
	<div class="col-md-12 column">
		<blockquote class="layui-elem-quote">角色列表</blockquote>
		
		 <fieldset>
	 		<legend>
				<span class="glyphicon glyphicon-th-list" style="color: rgb(9, 109, 169);"> 角色列表</span>
				<a href="" class="btn btn-primary" data-toggle="modal" data-target="#modal" id="add" onclick="setAddValue()"><em class="glyphicon glyphicon-plus"></em> 新增</a> 
			</legend>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>角色名</th>
						<th>创建时间</th>
						<th>最后修改时间</th>
						<th>操作</th>
					</tr>
				</thead>		
				<tbody>
				<s:iterator value="pagevo.data" var="item">
					<tr>
						<td>${item.name }</td>
						<td>${item.createTime }</td>
						<td>${item.updateTime }</td>
						<td>
							<a class="btn btn-link" data-toggle="modal" data-target="#setUserRole" id="setRole" onclick="setUserRole('${item.id }')">添加人员</a>
							<a class="btn btn-link" data-toggle="modal" data-target="#modal" id="update" onclick="setUpdateValue('${item.id }','${item.name }')">修改</a>
							<a class="btn btn-link" onclick="delRole('${item.id }')">删除</a>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
		</fieldset>				
	</div>
	<!-- 模态框，新增角色 -->
	<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel"></h4>
            </div>
            <form action="doAddRole.action" method="post">
            <input type="hidden" name="id" id="roleId">
            <div class="modal-body">
            <label for="name" class="col-sm-12 control-label">角色名称</label>
				<input type="text" name="name" class="form-control" id="roleName"><br>
			<h5>设置权限</h5>
			<hr style="height:5px">
			<table class="table">
				<thead>
					<tr class="active">
						<th>模块名称</th>
						<th>权限</th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="moduleList" var="module">
					<tr>
						<td>${module.name }</td>
						<td class="auth">
						<s:iterator value="#module.auths" var="auth">
							${auth.name } <input type="checkbox" name="authId" value="${auth.id }"><br>
						</s:iterator>
						</td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
			</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="submit" class="btn btn-primary">保存</button>
            </div>
            </form>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
    </div>
    
    <!-- 模态框，添加用户角色 -->
	<div class="modal fade" id="setUserRole" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            	<h4 class="modal-title" id="myModalLabel">将此用户设置为该角色</h4>
            </div>
				<form id="modalForm" role="form" method="post" action="setUserRole.action">
				<input type="hidden" name="roleId" id="role_id">
            <div class="modal-body">
				<div class="col-sm-12 column" id="modalBody">
				<div class="form-group">
					<select id="dept" name="deptId" class="form-control">
						<option>--请选择部门--</option>
					</select>
				</div>
				<div class="form-group">
					<select id="user" name="userId" class="form-control">
						<option>--请选择用户--</option>
					</select>
				</div>
				</div>
			</div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" >保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
           </form> 
        </div>
    </div>
</div>
</div>
<script src="${path }/bootstrap/js/jquery.min.js"></script>
<script src="${path }/bootstrap/js/bootstrap.min.js"></script>
</body>
<script type="text/javascript">

	function delRole(id){
		if(confirm('确定要删除该角色？')){
			window.location.href='delRole.action?id='+id;
		}
	}

	function setUserRole(roleId){
		console.log('roleId:'+roleId)
		$("#role_id").attr("value",roleId);
	}

	$.getJSON("${path }/dept/getDept.action",function(data){
		$.each(data,function(index,obj){
			var Opt = $("<option></option>");
			Opt.attr("value",obj.id).text(obj.name);
			$("#dept").append(Opt);	
		});
	});
	
	$("#dept").change(function(){
		var deptId = $(this).val();
		getUserByDeptId(deptId);
	});
	
	function getUserByDeptId(deptId){
		$.getJSON("${path }/user/getUserByDept.action?deptId="+deptId,function(data){
			$("#user").empty();
			$("#user").append($("<option>--请选择用户--</option>"));
			$.each(data,function(index,obj){
				var Opt = $("<option></option>");
				Opt.attr("value",obj.id).text(obj.name);
				$("#user").append(Opt);	
			});
		});
	}

	function setUpdateValue(id,name){
		$("#roleName").attr("value",name);
		$("#roleId").attr("value",id);
		$("#myModalLabel").text("修改角色");
		var inputs = $(".auth input")
		$.getJSON("http://localhost:8080/CRM/system/getAuthsByRole.action?id="+id,function(data){
			$.each(inputs,function(index,input){
				$.each(data,function(index,id){
					if($(input).attr("value")==id){
						$(input).attr("checked","checked");
					}
				});
			});
		});
	}
	
	function setAddValue(){
		$("#myModalLabel").text("添加角色");
	}
</script>
</html>
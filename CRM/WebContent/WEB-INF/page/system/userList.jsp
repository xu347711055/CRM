<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
       <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>用户列表</title>
	    <link href="${path }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<link rel="stylesheet" href="${path }/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${path }/css/global.css" media="all">
    </head>
    <body>
    	<div class="container">
		<div class="col-md-12 column">
			<blockquote class="layui-elem-quote">用户列表</blockquote>
			<form class="form-horizontal" role="form" action="" method="post">
				<fieldset>
					<legend><span class="glyphicon glyphicon-search" style="color: rgb(9, 109, 169);"> 用户搜索</span></legend>
					<div class="form-group">
						<label for="name" class="col-sm-1 control-label">姓名</label>
					    <div class="col-sm-2">
					      	<input name="name" type="text" class="form-control" id="name">
					    </div>
					    <label for="telephone" class="col-sm-1 control-label">账号</label>
					    <div class="col-sm-2">
							<input name="account" type="tel" class="form-control" id="account">
					    </div>
					    <div class="col-sm-3">
					      <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search">搜索</span></button>
					      <button type="reset" class="btn btn-default" value="清空"><span class="glyphicon glyphicon-trash" style="color: rgb(0, 0, 0);">清空</span></button>
					    </div>
				 	</div>
			 	</fieldset>
			 	<fieldset>
				<legend>
					<span class="glyphicon glyphicon-th-list" style="color: rgb(9, 109, 169);"> 联系人列表</span>
					<a class="btn btn-info" href="addUser.action"><em class="glyphicon glyphicon-plus"></em> 新建</a> 
				</legend>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>账号</th>
							<th>姓名</th>
							<th>手机</th>
							<th>邮箱</th>
							<th>部门</th>
							<th>操作</th>
						</tr>
					</thead>		
					<tbody>
					<s:iterator value="users" var="item">
						<tr>
							<td><a href="updateUser.action?userId=${item.id }">${item.account }</a></td>
							<td>${item.name }</td>
							<td>${item.telephone }</td>
							<td>${item.email }</td>
							<td>${item.dept.name }</td>
							<td><a href="delUserAction">删除</a></td>
						</tr>
					</s:iterator>
					</tbody>
				</table>
			</fieldset>				
			</form>
			</div>
		</div>
    	<script src="${path }/bootstrap/js/jquery.min.js"></script>
		<script src="${path }/bootstrap/js/bootstrap.min.js"></script>
 	</body>
</html>
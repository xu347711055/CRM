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
			 	<fieldset>
				<legend>
					<span class="glyphicon glyphicon-th-list" style="color: rgb(9, 109, 169);"> 用户列表</span>
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
							<td><a href="updateUser.action?userId=${item.id }" class="btn btn-link">${item.account }</a></td>
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
			</div>
		</div>
    	<script src="${path }/bootstrap/js/jquery.min.js"></script>
		<script src="${path }/bootstrap/js/bootstrap.min.js"></script>
 	</body>
</html>
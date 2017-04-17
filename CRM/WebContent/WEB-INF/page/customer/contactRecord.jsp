<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>联系记录</title>
	    <link href="${path}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<link rel="stylesheet" href="${path}/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${path}/css/global.css" media="all">
	</head>
	<body>
		<div class="container">
			<blockquote class="layui-elem-quote">${cname }联系记录</blockquote>
			<form class="form-horizontal" action="" method="post">
				<fieldset>
					<legend><span class="glyphicon glyphicon-search" style="color: rgb(9, 109, 169);"> 搜索联系记录</span></legend>
				</fieldset>
				<div class="form-group">
					<label for="custName" class="col-sm-2 control-label">客户名称</label>
				    <div class="col-sm-4">
				      	<input name="custName" type="text" class="form-control" id="custName">
				    </div>
				    <label for="contactName" class="col-sm-2 control-label">联系人姓名</label>
				    <div class="col-sm-4">
				      	<input name="contactName" type="text" class="form-control" id="contactName">
				    </div>
				</div>
				<div class="form-group">
					<label for="date" class="col-sm-2 control-label">创建日期</label>
				    <div class="col-sm-4">
				      	<input name="date" type="date" class="form-control" id="date">
				    </div>
				    <div class="col-sm-3">
				      <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search">搜索</span></button>
				      <button type="reset" class="btn btn-default" value="清空"><span class="glyphicon glyphicon-trash" style="color: rgb(0, 0, 0);">清空</span></button>
				    </div>
				</div>
			</form>
			<div class="col-sm-12">
			<span class="glyphicon glyphicon-th-list" style="color: rgb(9, 109, 169);font-size:20px ;">${cname } 联系记录列表</span>
			<s:if test="updateType=='listRecordByCust'">
				<a class="btn btn-info" href="addRecord.action?custId=${custId }&cname=${cname }"><em class="glyphicon glyphicon-plus"></em> 新建</a> 
			</s:if>
			<hr>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>客户名称</th>
							<th>联系人</th>
							<th>创建日期</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="pagevo.data" var="item">
							<tr>
								<td><a href="updateRecord.action?updateType=${updateType }&recordId=${item.id }&custId=${item.cust.id }&cname=${item.cust.cname }">
									${item.cust.cname }</a>
								</td>
								<td>${item.contactName }</td>
								<td>${item.createTime }</td>
								<td><a href="">删除</a></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
		<script src="${path}/bootstrap/js/jquery.min.js"></script>
		<script src="${path}/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>

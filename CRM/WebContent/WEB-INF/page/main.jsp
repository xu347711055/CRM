<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${path }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<link rel="stylesheet" href="${path }/plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${path }/css/global.css" media="all">
	<title>首页</title>
</head>
<body>
	<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="jumbotron">
				<h1>
					您好${username }，欢迎回来!!
				</h1>
				<p>
				<s:if test="custPagevo.totalRecord!=0">
					今天您有${custPagevo.totalRecord }个需要联系的客户<br>
				</s:if>
				<s:else>
					今天很清闲哦，没有需要联系的客户
				</s:else>
					<s:if test="contPagevo.totalRecord!=0">
						${contPagevo.totalRecord }个联系人今天生日，快去联系他们吧！
					</s:if>
				</p>
				<p>
					 <a class="btn btn-primary btn-large" data-toggle="modal" data-target="#modal-birthdayContact">查看今天生日的人</a>
					 <div class="modal fade" id="modal-birthdayContact" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">
								今日生日的联系人
							</h4>
						</div>
						<div class="modal-body">
						<ol class="list-unstyled">
						<s:iterator value="contPagevo.data" var="item">
							<li><a href="customer/updateContact.action?custId=${item.cust.id }
							&id=${item.id }&resultType=${resultType }">${item.cust.cname}的${item.name }</a></li>
						</s:iterator>
						</ol>
						</div>
						
					</div>
					
				</div>
				
			</div>
				</p>
			</div>
		</div>
	</div>
	
	<div class="col-md-12 column">
		<fieldset>
					<legend>
						<span class="glyphicon glyphicon-list-alt" style="color: rgb(9, 109, 169);">今天需要联系的客户</span><br />
						<div class="btn-group btn-group-md">
						</div>						
					</legend>
					<table class="table table-hover">
				<thead>
					<tr>
						<th>
							客户编号
						</th>
						<th>
							客户名称
						</th>
						<th>
							电话
						</th>
						<th>
							电子邮件
						</th>
						<th>
							下次联系时间
						</th>
						<th>
							联系人
						</th>
						
					</tr>
				</thead>
				<tbody>
					<s:iterator value="custPagevo.data" var="item">
						<tr>
					<td><a class="btn btn-link" href="customer/updateCust.action?id=${item.id}&resultType=${resultType }">${item.cnumber}</a></td>
					<td>${item.cname}</td>
					<td>${item.telephone}</td>
					<td>${item.email}</td>
					<td>${item.contactDate}</td>
					<s:iterator value="#item.contacts" var="ct">
						<s:if test="#ct.mainContact==1">
							<td><a href="contact/updateContact.action?id=${ct.id }&custId=${item.id }&resultType=${resultType }">${ct.name}</a></td>
						</s:if>
					</s:iterator>
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
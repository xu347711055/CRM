<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="/WEB-INF/page.tld"  prefix="t" %>
<!DOCTYPE html>
<html>
	<head>
		 <meta charset="utf-8">
    <!-- bootstrap 不支持IE的兼容模型，让IE运行最新的渲染模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 控制移动端浏览器视口的大小和缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签必须放在最前面，任何其他内容都必须跟随其后！ -->
    <title>联系人</title>

    <!-- Bootstrap -->
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <!-- 让IE8支持h5标签 -->
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <!-- 让IE8支持媒体查询 --><!--响应式-->
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../css/global.css" media="all">
		<title>联系人</title>
	</head>
	<body>
		<div class="container">
		<div class="col-md-12 column">
			<blockquote class="layui-elem-quote">${user.name }联系人列表</blockquote>
			<form class="form-horizontal" role="form" action="doSearchContByUser.action" method="post">
			<input type="hidden" name="resultType" value="${resultType }">
				<fieldset>
					<legend><span class="glyphicon glyphicon-search" style="color: rgb(9, 109, 169);"> 联系人搜索</span></legend>
					<div class="form-group">
						<label for="contactName" class="col-sm-1 control-label">姓名</label>
					    <div class="col-sm-2">
					      	<input name="contName" type="text" class="form-control" id="contactName" placeholder="联系人姓名">
					    </div>
					    <label for="telephone" class="col-sm-1 control-label">手机</label>
					    <div class="col-sm-2">
							<input  name="telephone" type="tel" class="form-control" id="telephone" placeholder="手机">
					    </div>
					     <label for="qq" class="col-sm-1 control-label">QQ号</label>
					    <div class="col-sm-2">
					      <input name="qqNum" type="tel" class="form-control" id="qq" placeholder="QQ">
					    </div>
					    <div class="col-sm-3">
					      <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search">搜索</span></button>
					      <button type="reset" class="btn btn-default" value="清空"><span class="glyphicon glyphicon-trash" style="color: rgb(0, 0, 0);">清空</span></button>
					    </div>
				 	</div>
				 	<div class="form-group">
						<label for="custName" class="col-sm-1 control-label">客户名称</label>
					    <div class="col-sm-2">
							<input  name="custName" type="text" class="form-control" id="custName" placeholder="客户名称">
					    </div>
					    <label for="email" class="col-sm-1 control-label">电子邮箱</label>
					    <div class="col-sm-2">
							<input name="text" type="text" class="form-control" id="email" placeholder="电子邮箱">					    
					    </div>
				 	</div>
			 	</fieldset>
			</form>
			<fieldset>
				<legend>
				<span class="glyphicon glyphicon-th-list" style="color: rgb(9, 109, 169);"> ${customer.cname }联系人列表</span>
				</legend>
				<table class="table table-hover">
					<thead>
						<tr>
							<th>姓名</th>
							<th>性别</th>
							<th>手机</th>
							<th>邮箱</th>
							<th>客户名称</th>
							<th>部门</th>
							<th>职务</th>
						</tr>
					</thead>		
					<tbody>
					<s:iterator value="pagevo.data" var="item">
					<s:if test="#item.mainContact==1">
						<tr class="success">
					</s:if>
					<s:else>
						<tr>
					</s:else>
							<td><a href="updateContact.action?resultType=${resultType }&id=${item.id }&custId=${item.cust.id }">${item.name }</a></td>
							<s:if test="#item.sex==1">
								<td>男</td>
							</s:if>
							<s:else>
								<td>女</td>
							</s:else>
							<td>${item.telephone }</td>
							<td>${item.email }</td>
							<td>${item.cust.cname }</td>
							<td>${item.dept }</td>
							<td>${item.duty }</td>
						</tr>
					</s:iterator>
					</tbody>
				</table>
				<t:page url="listContByUser.action" pagevo="${pagevo}"/>
			</fieldset>				
		</div>
		</div>
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>新建联系记录</title>
	    <link href="${path }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<link rel="stylesheet" href="${path }/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${path }/css/global.css" media="all">
	</head>
	<body>
		<div class="container">
			<blockquote class="layui-elem-quote">新建-联系记录</blockquote>
			<form class="form-horizontal" action="doUpdateRecord.action" method="post">
			<input type="hidden" value="${updateType }" name="updateType">
			<input type="hidden" value="${custId }" name="custId">
			<input type="hidden" value="${cname }" name="cname">
			<input type="hidden" value="${recordId }" name="recordId">
				<fieldset>
					<legend><span class="glyphicon glyphicon-th-list" style="color: rgb(9, 109, 169);"> 联系记录</span>
						<div class="btn-group">
							<button type="submit" class="btn btn-default">保存</button>
							<button type="button" class="btn btn-primary">返回</button>
						</div>
					</legend>
				<div class="form-group">
					<label for="custName" class="col-sm-2 control-label">客户名称</label>
				    <div class="col-sm-4">
				      	<input value="${cname }" name="custName" type="text" class="form-control" id="custName" disabled>
				    </div>
				    <label for="contactName" class="col-sm-2 control-label">联系人姓名</label>
				    <div class="col-sm-4">
				      	<input value="${record.contactName }" name="contactName" type="text" class="form-control" id="contactName">
				    </div>
				</div>
				<div class="form-group">
					<label for="telephone" class="col-sm-2 control-label">联系人电话</label>
				    <div class="col-sm-4">
				      	<input value="${record.telephone }" name="telephone" type="tel" class="form-control" id="telephone">
				    </div>
				    <label for="contactDate" class="col-sm-2 control-label">联系日期</label>
				    <div class="col-sm-4">
				      	<input value="${formatedDate }" name="contactDate" type="datetime-local" class="form-control" id="contactDate">
				    </div>
				 </div>
				
				<div class="form-group">
					<label for="content" class="col-sm-2 control-label">内容</label>
				    <div class="col-sm-10">
						<textarea class="form-control" rows="4" id="content" name="content">${record.content }</textarea>
				    </div>
				</div>
				</fieldset>
			</form>
		</div>
		<script src="${path }/bootstrap/js/jquery.min.js"></script>
		<script src="${path }/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>

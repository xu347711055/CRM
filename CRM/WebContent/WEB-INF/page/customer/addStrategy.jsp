<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>新建客户策略</title>
	    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">
	    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<link rel="stylesheet" href="../plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="../css/global.css" media="all">
	</head>
	<body>
		<script src="../bootstrap/js/jquery.min.js"></script>
		<script src="../bootstrap/js/bootstrap.min.js"></script>
		<div class="container">
			<blockquote class="layui-elem-quote">新建-客户策略</blockquote>
			<form class="form-horizontal" action="doAddStrategy.action" method="post">
			<input type="hidden" value="${custId }" name="custId">
			<input type="hidden" value="${cname }" name="cname">
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
				</div>
								
				<div class="form-group">
						<label for="title" class="col-sm-2 control-label">标题</label>
				    <div class="col-sm-12">
				      	<input name="title" type="text" class="form-control" id="title">
				    </div>
				</div>
				<div class="form-group">
					<label for="content" class="col-sm-2 control-label">内容</label>
				    <div class="col-sm-10">
						<textarea class="form-control" rows="4" id="content" name="content"></textarea>
				    </div>
				</div>
				</fieldset>
			</form>
		</div>
	</body>
</html>

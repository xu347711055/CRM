<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>新建客户策略</title>
	    <link href="${path }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<link rel="stylesheet" href="${path }/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${path }/css/global.css" media="all">
</head>
<body>
	<div class="container">
			<blockquote class="layui-elem-quote">新建-联系策略</blockquote>
			<form class="form-horizontal" action="doAddStrategy.action" method="post">
			<input type="hidden" value="${custId }" name="custId">
			<input type="hidden" value="${cname }" name="cname">
				<fieldset>
					<legend><span class="glyphicon glyphicon-th-list" style="color: rgb(9, 109, 169);"> 联系记录</span>
						<div class="btn-group">
							<button type="submit" class="btn btn-default">保存</button>
							<button type="button" class="btn btn-primary" onclick="goback()">返回</button>
						</div>
					</legend>
				<div class="form-group">
					<label for="content" class="col-sm-2 control-label">${cname }客户策略</label>
				    <div class="col-sm-10">
				      	<textarea name="strategy" rows="50" cols="200" class="form-control" id="content">${strategy }</textarea>
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
	</script>
</html>
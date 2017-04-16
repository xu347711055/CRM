<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>新建联系人</title>
	    <link href="${path }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<link rel="stylesheet" href="${path }/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${path }/css/global.css" media="all">
	</head>
	<body>
		<div class="container">
			<div class="col-sm-12 column">
				<blockquote class="layui-elem-quote">新增-联系人</blockquote>
				<form class="form-horizontal" action="doAddContact.action" method="post">
					<legend><span class="glyphicon glyphicon-th-list" style="color: rgb(9, 109, 169);"> 基本信息</span>
						<div class="btn-group">
							<button type="submit" class="btn btn-default">保存</button>
							<button type="button" class="btn btn-primary" onclick="goback()">返回</button>
						</div>
					</legend>
					<input type="hidden" value="${custId }" name="custId">
					<div class="form-group">
						<label class="col-sm-2" for="contactName">姓名</label>
						<div class="col-sm-4">
   							<input type="text" class="form-control" id="contactName" name="name" required>
   						</div>
   						<label class="col-sm-2" for="sex">性别</label>
   						<div class="col-sm-4">
							<select class="form-control" name="sex" id="sex">
								<option value="1">男</option>
								<option value="0">女</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2" for="dept">部门</label>
   						<div class="col-sm-4">
							<input type="text" name="dept" id="dept" class="form-control" />
						</div>
   						<label class="col-sm-2" for="birthday">出生日期</label>
   						<div class="col-sm-4">
							<input type="date" name="birthday" id="birthday" class="form-control" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="col-sm-2" for="fax">传真</label>
						<div class="col-sm-4">
   							<input type="text" class="form-control" id="fax" name="fax">
   						</div>
   						<label class="col-sm-2">是否主联系人</label>
   						<div class="col-sm-4">
					    	<input type="radio" name="ctState" id="optionsRadios3" value="1" >是
					    	<input type="radio" name="ctState" id="optionsRadios4" value="0" checked>否
   						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2" for="job">职务</label>
						<div class="col-sm-4">
   							<input type="text" class="form-control" id="job" name="duty">
   						</div>
   						<label class="col-sm-2" for="officephone">办公室电话</label>
   						<div class="col-sm-4">
							<input type="tel" name="officePhone" id="officephone" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2" for="telephone">手机</label>
						<div class="col-sm-4">
   							<input type="tel" class="form-control" id="telephone" name="telephone">
   						</div>
   						<label class="col-sm-2" for="email">电子邮箱</label>
   						<div class="col-sm-4">
							<input type="email" name="email" id="email" class="form-control" />
						</div>
					</div>
					<div class="form-group">
						
   						<label class="col-sm-2" for="qq">QQ</label>
   						<div class="col-sm-4">
							<input type="tel" name="qqNum" id="qq" class="form-control" />
						</div>
						<label class="col-sm-2" for="qqName">QQ昵称</label>
						<div class="col-sm-4">
   							<input type="text" class="form-control" id="qqName" name="qqName">
   						</div>
					</div>
					<div class="form-group">
						
   						<label class="col-sm-2" for="wechatNum">微信号</label>
   						<div class="col-sm-4">
							<input type="text" name="wechatNum" id="wechatNum" class="form-control" />
						</div>
						<label class="col-sm-2" for="wechatName">微信名</label>
						<div class="col-sm-4">
   							<input type="text" class="form-control" id="wechatName" name="wechatName">
   						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2" for="address">联系地址</label>
						<div class="col-sm-10">
   							<input type="text" class="form-control" id="address" name="address">
   						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2" for="hobby">爱好</label>
						<div class="col-sm-10">
							<textarea name="hobby" id="hobby" class="form-control" rows="4"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2" for="dislike">忌讳</label>
						<div class="col-sm-10">
							<textarea name="hate" id="dislike" class="form-control" rows="4"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2" for="remark">备注</label>
						<div class="col-sm-10">
							<textarea name="remark" id="remark" class="form-control" rows="4"></textarea>
						</div>
					</div>
				</form>
			</div>
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

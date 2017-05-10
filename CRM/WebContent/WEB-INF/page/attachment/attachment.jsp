<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/WEB-INF/page.tld"  prefix="t" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
    <!-- bootstrap 不支持IE的兼容模型，让IE运行最新的渲染模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 控制移动端浏览器视口的大小和缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签必须放在最前面，任何其他内容都必须跟随其后！ -->
    <title>附件管理</title>

    <!-- Bootstrap -->
    <link href="../bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <!-- 让IE8支持h5标签 -->
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <!-- 让IE8支持媒体查询 --><!--响应式-->
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<link rel="stylesheet" href="${path}/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${path}/css/global.css" media="all">
</head>
<body>
	<div class="container">
	<div class="row clearfix">
	<blockquote class="layui-elem-quote"><a href="${path }/attachment/list.action" class="btn btn-link">首页 ></a>附件上传</blockquote>
	<button class="btn btn-primary" data-toggle="modal" data-target="#upload" onclick="upload()">上传</button>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="upload" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	        	<form id="form" class="form-horizontal" role="form" action="doUpload.action" method="post" enctype="multipart/form-data" >
	           	<input type="hidden" name="custId" value="${custId }">
	           	<input type="hidden" name="attachId" id="attachId">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">上传文件</h4>
	            </div>
	            <div class="modal-body">
		            <div class="form-group">
		            	<label class="col-sm-2 control-label" for="fileName">文件名</label>
		            	<div class="col-sm-10">
							<input type="text" name="fileName" id="fileName" class="form-control">
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="inputfile">文件输入</label>
						<div class="col-sm-10">
						 	<input type="file" name="upload">
					 	</div>
					</div>
				</div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="submit" class="btn btn-primary">上传</button>
	            </div>
	            </form>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	<table class="table">
		<thead>
			<tr>
				<th>名称</th>
				<th>上传者</th>
				<th>更新时间</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="pagevo.data" var="item">
			<tr>
				<td>
				${item.fileName }<br>
				<a class="btn btn-link" href="/file/${item.saveName }">下载</a>
				<button class="btn btn-link" data-toggle="modal" data-target="#upload" onclick="update(${item.id },'${item.fileName }')">更新或重命名</button>
				<button class="btn btn-link" onclick="del(${item.id },${custId })">删除</button>
				</td>
				<td>${item.creator.name }</td>
				<td>${item.updateTime }</td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
		<t:page url="${path }/attachment/listAttach.action" pagevo="${pagevo}"/>
	</div>
	</div>
	
	<%@include file="/WEB-INF/page/commonjs.jsp" %>
</body>

<script type="text/javascript">
	function update(id, fileName){
		$('#form').attr('action','doUpdate.action');
		$('#fileName').val(fileName);
		$('#attachId').val(id);
	}
	
	function upload(){
		$('#form').attr('action','doUpload.action');
		$('#fileName').val('');
	}
	
	function del(id,custId){
		if(window.confirm('确定要删除此附件？'))
			window.location.href='del.action?attachId='+id+"&custId="+custId;
	}
</script>
</html>
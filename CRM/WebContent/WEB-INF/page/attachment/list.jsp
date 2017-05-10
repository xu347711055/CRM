<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s" %>
<%@ taglib uri="/WEB-INF/page.tld"  prefix="t" %>

<!doctype html>
<html>

	<head>
	<meta charset="utf-8">
    <!-- bootstrap 不支持IE的兼容模型，让IE运行最新的渲染模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 控制移动端浏览器视口的大小和缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签必须放在最前面，任何其他内容都必须跟随其后！ -->
    <title>客户管理</title>

    <!-- Bootstrap -->
    <link href="${path}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <!-- 让IE8支持h5标签 -->
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <!-- 让IE8支持媒体查询 --><!--响应式-->
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<link rel="stylesheet" href="${path }/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${path }/css/global.css" media="all">
	</head>

	<body>
		
	<div class="container">
	<div class="row clearfix">
		<br />
		<div class="col-sm-12 column">
			<form class="form-horizontal" role="form" action="${path }/customer/doSearch.action" method="post">
				<input type="hidden" name="resultType" value="${resultType }">
				<fieldset>
					<legend><span class="glyphicon glyphicon-search" style="color: rgb(9, 109, 169);"> 客户搜索</span></legend>
					<div class="form-group">
						<label for="custId" class="col-sm-1 control-label">客户编号</label>
					    <div class="col-sm-2">
					      	<input name="cnumber" type="text" class="form-control" id="custId" placeholder="客户编号" >
					    </div>
					    <label for="custName" class="col-sm-1 control-label">客户名称</label>
					    <div class="col-sm-2">
							<input  name="cname" type="text" class="form-control" id="custName" placeholder="客户名称">
					    </div>
					     <label for="telephone" class="col-sm-1 control-label">电话</label>
					    <div class="col-sm-2">
					      <input name="telephone" type="text" class="form-control" id="telephone" placeholder="电话">
					    </div>
					    <div class="col-sm-3">
					      <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-search">搜索</span></button>
					      <button type="reset" class="btn btn-default" value="清空"><span class="glyphicon glyphicon-trash" style="color: rgb(0, 0, 0);">清空</span></button>
					    </div>
				 	</div>
				 	<div class="form-group">
						 <label for="bProperty" class="col-sm-1 control-label">企业性质</label>
					    <div class="col-sm-2">
							<select class="form-control" id="bProperty" name="companyNature">
								<option value="">--请选择--</option>
							</select>
					    </div>
					    <label for="level" class="col-sm-1 control-label">客户等级</label>
					    <div class="col-sm-2">
					  		<select class="form-control" id="level" name="level">
								<option value="">--请选择--</option>
							</select>
					    </div>
					    <label for="contactDate" class="col-sm-1 control-label">联系日期</label>
					    <div class="col-sm-2">
					  		<input type="date" class="form-control" name="contactDate" id="contactDate"/>
					    </div>
				 	</div>
				 	<div class="form-group">
						<label for="source" class="col-sm-1 control-label">客户来源</label>
					    <div class="col-sm-2">
					      	<select class="form-control" id="source" name="source">
								<option value="">--请选择--</option>
							</select>
					    </div>
				 	</div>
			 	</fieldset>
			</form>
			<br />
			
			<div class="col-sm-12 column">
				<fieldset>
					<legend>
						<span class="glyphicon glyphicon-list-alt" style="color: rgb(9, 109, 169);">客户列表</span><br />
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
				<s:iterator value="custList" var="item">
					<tr>
					<td><a class="btn btn-link" href="listAttach.action?custId=${item.id}">${item.cnumber}</a></td>
					<td>${item.cname}</td>
					<td>${item.telephone}</td>
					<td>${item.email}</td>
					<td>${item.contactDate}</td>
					<s:iterator value="#item.contacts" var="ct">
						<s:if test="#ct.mainContact==1">
							<td><a href="updateContact.action?id=${ct.id }&custId=${item.id }&resultType=${resultType }">${ct.name}</a></td>
						</s:if>
					</s:iterator>
					</tr>
				</s:iterator>
				</tbody>
			</table>
				<t:page url="${path }/attachment/list.action" pagevo="${pagevo}"/>
				</fieldset>
			</div>
		</div>
	</div>
</div>

	<%@include file="/WEB-INF/page/commonjs.jsp" %>
	</body>
	<script type="text/javascript">
	$("#shareCust").click(function(){
		$("#modalForm").attr("action","shareCust.action");
	});
	
	$("#changeOwner").click(function(){
		$("#modalForm").attr("action","shareCust.action");
		$("#shareType").attr("value","changeOwner");
	}); 
	
	function submitShare(){
		if(window.confirm('确定提交？')){
			$("#modalForm").submit();
		}
	}
	
	$.getJSON("${path }/dept/getDept.action",function(data){
		$.each(data,function(index,obj){
			var Opt = $("<option></option>");
			Opt.attr("value",obj.id).text(obj.name);
			$("#dept").append(Opt);	
		});
	});
	
	$("#dept").change(function(){
		var deptId = $(this).val();
		getUserByDeptId(deptId);
	});
	
	function getUserByDeptId(deptId){
		$.getJSON("${path }/user/getUserByDept.action?deptId="+deptId,function(data){
			$("#user").empty();
			$("#user").append($("<option>--请选择用户--</option>"));
			$.each(data,function(index,obj){
				var Opt = $("<option></option>");
				Opt.attr("value",obj.id).text(obj.name);
				$("#user").append(Opt);	
			});
		});
	}
	
	function optionClick(custId){
		$("#delCust").click(function(){
			var flag = window.confirm("删除联系人可能会影响其他用户的使用，您确定要删除？");
			if(flag==true){
				window.location.href="delCust.action?custId="+custId;
			}
		});
		$("#formInput").attr("value",custId);
	}
	
	$.getJSON("http://localhost:8080/CRM/getDicData.action",function(data){
		/* $.each(data, function(index,obj) {
			addOption("level",obj)
		}); */
		$.each(data["level"],function(index,obj){
			addOption("level",obj);
		});
		
		$.each(data["source"],function(index,obj){
			addOption("source",obj);
		});
		$.each(data["quality"],function(index,obj){
			addOption("bProperty",obj);
		});
		
	});
	
	
	function addOption(id,obj){
		var Opt = $("<option></option>");
		Opt.attr("value",obj.value).text(obj.value);
		$("#"+id).append(Opt);
	}
	</script>
</html>
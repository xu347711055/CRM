<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
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
		<title>客户资料</title>
	</head>
	<style>
		
	</style>
	<body>
	
		<div class="container">
			<div class="row clearfix">
				<div class="col-sm-12">
					<blockquote class="layui-elem-quote"><a href="custManage.action" class="btn btn-link">首页 ></a>修改-客户</blockquote>
					<form class="form-horizontal" role="form" action="doUpdateCust.action" method="post">
					<input type="hidden" value="${customer.id }" name="id">
					<fieldset>
						<legend>基本信息 &nbsp;<button class="btn btn-primary" type="submit"><em class="glyphicon glyphicon-file"></em> 保存</button>
						<a class="btn btn-success" href="listContByCust.action?custId=${customer.id }"><em class="glyphicon glyphicon-file"></em> 联系人</a>
							<a class="btn btn-warning" href="listRecordByCust.action?custId=${customer.id }"><em class="glyphicon glyphicon-file"></em> 联系记录</a>
							<button class="btn btn-default" type="button" onclick="goback()"><em class="glyphicon glyphicon-file"></em> 取消</button>
						 </legend>
						<div class="form-group">
							<label for="custId" class="col-sm-2 control-label"><span class="labelspan">客户编号：</span></label>
						    <div class="col-sm-4">
						      	<input type="text" class="form-control" id="custId" value="${customer.cnumber}" disabled >
						      	<input type="hidden" value="${customer.cnumber}" name="cnumber">
						    </div>
						    <label for="custName" class="col-sm-2 control-label"><span class="labelspan">客户名称：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.cname }" name="cname" type="text" class="form-control" id="custName" maxlength="20" required="required">
						    </div>
					    </div>
					    <div class="form-group">
							<label for="level" class="col-sm-2 control-label"><span class="labelspan">客户等级：</span></label>
						    <div class="col-sm-4">
						      	<select class="form-control" name="level" id="level">
									
						      	</select>
						    </div>
						    <label for="region" class="col-sm-2 control-label"><span class="labelspan">区域名称：</span></label>
						    <div class="col-sm-4">
						      	<select class="form-control" name="region" id="region">
						      	</select>
						    </div>
					    </div>
					    <div class="form-group">
							<label for="source" class="col-sm-2 control-label"><span class="labelspan">客户来源：</span></label>
						    <div class="col-sm-4">
						      	<select class="form-control" name="source" id="source">
						      	</select>
						    </div>
						    <label for="industry" class="col-sm-2 control-label"><span class="labelspan">所属行业：</span></label>
						    <div class="col-sm-4">
						        <select class="form-control" name="industry" id="industry">
						      	</select>
						    </div>
					    </div>
					    <div class="form-group">
							<label for="scale" class="col-sm-2 control-label"><span class="labelspan">公司规模：</span></label>
						    <div class="col-sm-4">
						      	<select class="form-control" name="scale" id="scale">
									
						      	</select>
						    </div>
						    <label for="fax" class="col-sm-2 control-label"><span class="labelspan">传真：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.fax }" name="fax" type="text" class="form-control" id="fax" maxlength="8" pattern="^d{0-9}$">
						    </div>
					    </div>
					    <div class="form-group">
							<label for="address" class="col-sm-2 control-label"><span class="labelspan">联系地址：</span></label>
						    <div class="col-sm-10">
						      	<input value="${customer.address }" name="address" type="text" class="form-control" id="address" maxlength="30">
						    </div>
					    </div>
					    <div class="form-group">
							<label for="province" class="col-sm-2 control-label"><span class="labelspan">省份：</span></label>
						    <div class="col-sm-4">
						      	<select class="form-control" name="province" id="province">
										
						      	</select>
						    </div>
						    <label for="city" class="col-sm-2 control-label"><span class="labelspan">城市：</span></label>
						    <div class="col-sm-4">
						      	<select class="form-control" name="cityId" id="city" required>
						      	</select>
						    </div>
					    </div>
					    <div class="form-group">
							<label for="postNum" class="col-sm-2 control-label"><span class="labelspan">邮政编码：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.postNum }" name="postNum" type="text" class="form-control" id="postNum" maxlength="6" >
						    </div>
						    <label for="email" class="col-sm-2 control-label"><span class="labelspan">电子邮件：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.email }" name="email" type="email" class="form-control" id="email" >
						    </div>
					    </div>
					    <div class="form-group">
							<label for="website" class="col-sm-2 control-label"><span class="labelspan">公司网站：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.website }" name="website" type="text" class="form-control" id="website">
						    </div>
						    <label for="telephone1" class="col-sm-2 control-label"><span class="labelspan">手机：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.telephone }" name="telephone" type="text" class="form-control" id="telephone1" >
						    </div>
					    </div>
					    <div class="form-group">
							<label for="phone1" class="col-sm-2 control-label"><span class="labelspan">电话一：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.phone1 }" name="phone1" type="text" class="form-control" id="phone1" placeholder="电话一">
						    </div>
						    <label for="phone2" class="col-sm-2 control-label"><span class="labelspan">电话二：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.phone2 }" name="phone2" type="text" class="form-control" id="phone2" >
						    </div>
					    </div>
					    <div class="form-group">
						    <label for="nextContact" class="col-sm-2 control-label"><span class="labelspan">下次联系时间：</span></label>
						    <div class="col-sm-4">
						      	<input value="${formatedDate }" name="contactDate" type="datetime-local" class="form-control" id="nextContact">
						    </div>
						    <label for="custProperty" class="col-sm-2 control-label"><span class="labelspan">客户性质：</span></label>
						    <div class="col-sm-4">
						      	<select class="form-control" name="custNature" id="custQuality">
						      	</select>
						    </div>
					    </div>
					    <div class="form-group">
							<label for="remark" class="col-sm-2 control-label"><span class="labelspan">备注：</span></label>				    	
					    	<div class="col-sm-10">
						      	<textarea name="remark" class="form-control" rows="3" id="remark">${customer.fax }</textarea>
						    </div>
					    </div>
					</fieldset>
					<fieldset>
						<legend>企业信息</legend>
						<div class="form-group">
						    <div class="form-group">
					    	<label for="taxNum" class="col-sm-2 control-label"><span class="labelspan">公司税号：</span></label>
					    	<div class="col-sm-4">
						      	<input value="${customer.taxNum }" type="text" name="taxNum" id="taxNum" class="form-control"/>
						    </div>
						    <label for="bProperty" class="col-sm-2 control-label"><span class="labelspan">企业性质：</span></label>
						    <div class="col-sm-4">
						      	<select class="form-control" name="companyNature" id="bProperty">
									
						      	</select>
						    </div>
					    </div>
					    <div class="form-group">
						    <label for="legal_person" class="col-sm-2 control-label"><span class="labelspan">法人代表：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.represent }" type="text" name="represent" id="legal_person" class="form-control"/>
						    </div>
						    <label for="bProperty" class="col-sm-2 control-label"><span class="labelspan">注册资金：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.regCapital }" type="number" name="regCapital" id="regCapital" class="form-control"/>
						    </div>
					    </div>
					    <div class="form-group">
						    <label for="bank" class="col-sm-2 control-label"><span class="labelspan">开户银行：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.bank }" type="text" name="bank" id="bank" class="form-control"/>
						    </div>
						    <label for="bankNum" class="col-sm-2 control-label"><span class="labelspan">银行账号：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.bankAccount }" type="text" name="bankAccount" id="bankAccount" class="form-control"/>
						    </div>
					    </div>
					     
					 </div>
					</fieldset>
					<fieldset>
						<legend>其他信息</legend>
						 <div class="form-group">
						    <label for="require1" class="col-sm-2 control-label"><span class="labelspan">客户需求1：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.demand1 }" type="text" name="demand1" id="demand1" class="form-control"/>
						    </div>
						    <label for="require2" class="col-sm-2 control-label"><span class="labelspan">客户需求2：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.demand2 }" type="text" name="demand2" id="demand2" class="form-control"/>
						    </div>
					    </div>
					    <div class="form-group">
						    <label for="require3" class="col-sm-2 control-label"><span class="labelspan">客户需求3：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.demand3 }" type="text" name="demand3" id="demand3" class="form-control"/>
						    </div>
						    <label for="require4" class="col-sm-2 control-label"><span class="labelspan">客户需求4：</span></label>
						    <div class="col-sm-4">
						      	<input value="${customer.demand4 }" type="text" name="demand4" id="demand4" class="form-control"/>
						    </div>
					    </div>
					    
					     <div class="form-group">
						    <label for="owner" class="col-sm-2 control-label"><span class="labelspan">所属人员工号：</span></label>
						    <div class="col-sm-4">
						    <s:iterator value="customer.owner" var="i">
						      	<input value="${i.account}" type="text" id="owner" class="form-control" required="required" disabled="disabled" />
						   		<input type="hidden" value="${i.account }" name="account">
						    </s:iterator>
						    </div>
					    </div>
					    <input type="hidden" name="creater" value="${customer.creator.id }">
					</fieldset>
					</form>
				</div>
			</div>
		</div>
		
		
		<!--  jQuery文件。务必在bootstrap.min.js 之前引入  -->
	<script src="${path}/bootstrap/js/jquery.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script src="${path}/bootstrap/js/bootstrap.min.js"></script>
	</body>
	<script type="text/javascript">
		function goback(){
			history.go(-1);
		}
	$(document).ready(function(){
		
		$.getJSON("http://localhost:8080/CRM/getProvinceData.action",function(data){
			$.each(data,function(index,obj){
				var Opt = $("<option></option>");
				Opt.attr("value",obj.id).text(obj.name);
				$("#province").append(Opt);	
				if(${customer.city.province.id}==obj.id){
					Opt.attr("selected","selected");
					getCity(obj.id);
				}
			});
		});
		
		$("#province").change(function(){
			var parentId = $(this).val();
			getCity(parentId);
		});
		
		function getCity(parentId){
			$.getJSON("http://localhost:8080/CRM/getCityData.action?id="+parentId,function(data){
				$("#city").empty();
				$.each(data,function(index,obj){
					var Opt = $("<option></option>");
					Opt.attr("value",obj.id).text(obj.name);
					$("#city").append(Opt);
					if(${customer.city.id}==obj.id){
						Opt.attr("selected","selected");
					}
				});
			});
		}
		
		$.getJSON("http://localhost:8080/CRM/getDicData.action",function(data){
			/* $.each(data, function(index,obj) {
				addOption("level",obj)
			}); */
			$.each(data["level"],function(index,obj){
				addOption("level",obj,"${customer.level}")
			});
			
			$.each(data["source"],function(index,obj){
				addOption("source",obj,"${customer.source}")
			});
			$.each(data["regionName"],function(index,obj){
				addOption("region",obj,"${customer.region}");
			});
			$.each(data["quality"],function(index,obj){		/*企业性质*/
				addOption("bProperty",obj,"${customer.companyNature}");
			});
			$.each(data["trade"],function(index,obj){
				addOption("industry",obj,"${customer.industry}");
			});
			$.each(data["scale"],function(index,obj){
				addOption("scale",obj,"${customer.scale}");
			});
			$.each(data["kind"],function(index,obj){	/*客户性质*/
				addOption("custQuality",obj,"${customer.custNature}");
			});
		});
		
		function addOption(id,obj,selected){
			var Opt = $("<option></option>");
			Opt.attr("value",obj.value).text(obj.value);
			$("#"+id).append(Opt);
			if(selected==obj.value){
				Opt.attr("selected","selected");
			}
		}
		
		$("#owner").change(function(){
			var account = $(this).val();
			$.getJSON("http://localhost:8080/CRM/customer/checkOwner.action?account="+account,function(data){
				if(data==null){
					$("#owner").val("");
					$("#msg").css("display","inline");
				}
				if(data!=null){
					$("#msg").css("display","none");
				}
			});
		}); 
	})
	</script>
	
</html>

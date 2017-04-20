<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <title>客户分析</title>
	    <link href="${path }/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
		<link rel="stylesheet" href="${path }/plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="${path }/css/global.css" media="all">
	</head>
	<body>
	<div class="col-sm-12 column">
	<div class="btn-group">
		<button type="button" class="btn btn-primary" id="accDataBtn">本账号数据</button>
		<button type="button" class="btn btn-default" id="sysDataBtn">系统数据数据</button>
	</div>
	</div>
	<div class="col-sm-4 column">
		<div id="container1" style="min-width: 220px;height: 220px;"></div>
	</div>
	<div class="col-sm-4 column">
		<div id="container2" style="min-width: 220px;height: 220px;"></div>
	</div>
	<div class="col-sm-4 column">
		<div id="container3" style="min-width: 220px;height: 220px;"></div>
	</div>
	<div class="col-sm-4 column">
		<div id="container4" style="min-width: 220px;height: 220px;"></div>
	</div>
	<div class="col-sm-4 column">
		<div id="container5" style="min-width: 220px;height: 220px;"></div>
	</div>
<script src="${path }/bootstrap/js/jquery.min.js"></script>
<script src="${path }/bootstrap/js/bootstrap.min.js"></script>
<script src="${path }/js/highcharts.js"></script>
	</body>
	<script>
	var url = "${path }/statistic/getPieChartData.action";
	
	drawCharts();
	
	$("#sysDataBtn").click(function(){
		url = "${path }/statistic/getAllPieChartData.action";
		drawCharts();
	});
	
	$("#accDataBtn").click(function(){
		url = "${path }/statistic/getPieChartData.action";
		drawCharts();
	});
	
	function drawCharts(){
		$.getJSON(url+"?group=c.companyNature",function(data){
		  	var chart = getchart(data,'container1','客户性质');
	   	});
		 $.getJSON(url+"?group=c.region",function(data){
			  	var chart = getchart(data,'container2','客户地区');
		});
		 $.getJSON(url+"?group=c.level",function(data){
			  	var chart = getchart(data,'container3','客户等级');
		});
		 $.getJSON(url+"?group=c.source",function(data){
			  	var chart = getchart(data,'container4','客户来源');
		});
		 $.getJSON(url+"?group=c.industry",function(data){
			  	var chart = getchart(data,'container5','客户行业组成');
		});
	}
	 
	 function getchart(data,divId,title){
		 return new Highcharts.Chart({
		       chart: {
		           plotBackgroundColor: null,
		           plotBorderWidth: null,
		           plotShadow: false,
		           renderTo: divId
		       },
		       title: {
		           text: title
		       },
		       tooltip: {
		           headerFormat: '{series.name}<br>',
		           pointFormat: '{point.name}: <b>{point.percentage:.1f}%</b>'
		       },
		       plotOptions: {
		           pie: {
		               allowPointSelect: true,
		               cursor: 'pointer',
		               dataLabels: {
		                   enabled: true,
		                   format: '<b>{point.name}</b>: {point.percentage:.1f} %',
		                   style: {
		                       color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                   }
		               }
		           }
		       },
		       series: [{
		           type: 'pie',
		           name: '',
		           data: data
		       }]
		   });
	 }
	</script>
</html>

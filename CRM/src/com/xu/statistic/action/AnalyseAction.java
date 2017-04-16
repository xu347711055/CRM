package com.xu.statistic.action;

import java.awt.Font;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.xu.common.domain.Dictionary;
import com.xu.common.service.BaseService;
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;

@Namespace("/statistic")
@ParentPackage("chart")
@Action("analyse")
@Result(name="success",type="chart",params={"width","800","height","600"})
public class AnalyseAction {
	private JFreeChart chart;
	 @Autowired
	 private CustService custService;
	 @Autowired()
	 @Qualifier("baseService")
	 private BaseService<Dictionary> baseService;
	 private String code;	//数据字典中的值
	 private String chartName;
	 
	 public JFreeChart getChart() {
		chart = ChartFactory.createPieChart("系统中客户来源组成饼图", getDataset(), true, true, false);
		chart.setTitle(new TextTitle(chartName,new Font("黑体",Font.BOLD,22)));
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("宋体", Font.BOLD	, 10));
		chart.getLegend().setItemFont(new Font("微软雅黑",Font.BOLD,12));
		return chart;
	}

	public String execute(){
		
		return "success";
	}
	
	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	 
	public DefaultPieDataset  getDataset(){
		DefaultPieDataset dataset = new DefaultPieDataset();
		Map<String, Object> conditions = new HashMap<>();
		System.out.println("code"+"--"+this.code);
		conditions.put("code", this.code);
		List<Dictionary> dicList = baseService.list(conditions, Dictionary.class, null);//先查出数据字典对象
		for(Dictionary dic : dicList){
			Map<String, Object> custConditions = new HashMap<>();
			custConditions.put(dic.getCode(), dic.getValue());
			//再按照该数据字典对象的code和value查询符合条件的记录的数量
			int count = custService.list(custConditions, Customer.class, null).size();
			System.out.println(dic.getValue()+"--"+count);
			if(count!=0){
				dataset.setValue(dic.getValue(),count);
			}
		}
       return dataset;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}

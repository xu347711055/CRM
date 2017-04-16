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

import com.opensymphony.xwork2.ActionSupport;
import com.xu.common.domain.Dictionary;
import com.xu.common.service.BaseService;
import com.xu.customer.service.CustService;

@Namespace("/statistic")
@ParentPackage("chart")
@Action("analyseSoruce")
@Result(name="success",type="chart",params={"width","800","height","600"})
public class AnalyseSoruceAction extends ActionSupport {
	 private JFreeChart chart;
	 @Autowired
	 private CustService custService;
	 @Autowired()
	 @Qualifier("baseService")
	 private BaseService<Dictionary> baseService;
	public JFreeChart getChart() {
		chart = ChartFactory.createPieChart("系统中客户来源组成饼图", getDataset(), true, true, false);
		chart.setTitle(new TextTitle("客户来源组成",new Font("黑体",Font.BOLD,22)));
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("宋体", Font.BOLD	, 10));
		chart.getLegend().setItemFont(new Font("微软雅黑",Font.BOLD,12));
		return chart;
	}

	public String execute(){
		
		return "success";
	}
	
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}
	 
	public DefaultPieDataset  getDataset(){
		DefaultPieDataset dataset = new DefaultPieDataset();
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("code", "source");
		List<Dictionary> dicList = baseService.list(conditions, Dictionary.class, null);
		for(Dictionary dic : dicList){
			int count = custService.getCountByDic(dic);
			System.out.println(dic.getValue()+"--"+count);
			if(count!=0){
				dataset.setValue(dic.getValue(),count);//更新成最新值
			}
		}
        return dataset;
	}
	 
}

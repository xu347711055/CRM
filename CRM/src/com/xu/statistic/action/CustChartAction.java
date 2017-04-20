package com.xu.statistic.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;


@Namespace("statistic")
@Action("custChart")
@Result(name="success",location="custChart.jsp")
public class CustChartAction {

	public String execute(){
		
		return "success";
	}
}

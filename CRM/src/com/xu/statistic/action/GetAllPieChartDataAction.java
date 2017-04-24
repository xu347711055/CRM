package com.xu.statistic.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.common.domain.PieChartData;
import com.xu.customer.service.CustService;

@ParentPackage("json")
@Namespace("/statistic")
@Action("getAllPieChartData")
@InterceptorRef("privilegeStack")
@Result(name="success",type="json",params={"root","pieData"})
public class GetAllPieChartDataAction {
	@Autowired
	private CustService custService;
	private List<PieChartData> pieData;
	private String group;
	
	public String execute() throws Exception{
		pieData = custService.getPieChartData("c", "u", null, group);
		return "success";
	}

	public List<PieChartData> getPieData() {
		return pieData;
	}

	public void setPieData(List<PieChartData> pieData) {
		this.pieData = pieData;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
}

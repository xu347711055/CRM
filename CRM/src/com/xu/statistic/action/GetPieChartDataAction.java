package com.xu.statistic.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.xu.common.domain.PieChartData;
import com.xu.customer.service.CustService;
import com.xu.user.domain.User;

@ParentPackage("json")
@Namespace("/statistic")
@Action("getPieChartData")
@InterceptorRef("privilegeStack")
@Result(name="success",type="json",params={"root","pieData"})
public class GetPieChartDataAction {
	@Autowired
	private CustService custService;
	private List<PieChartData> pieData;
	private String group;
	
	public String execute() throws Exception{
		User curUser = (User) ActionContext.getContext().getSession().get("user");
		//设置查询条件
		Map<String,Object> conditions = new HashMap<>();
		conditions.put("owner", curUser.getId());
		pieData = custService.getPieChartData("c", "u", conditions, group);
		return "success";
	}

	
	public String getGroup() {
		return group;
	}


	public void setGroup(String group) {
		this.group = group;
	}


	public List<PieChartData> getPieData() {
		return pieData;
	}

	public void setPieData(List<PieChartData> pieData) {
		this.pieData = pieData;
	}
	
}

package com.xu.strategy.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.customer.service.CustService;

@Namespace("/customer")
@Action("doAddStrategy")
@Result(name="success",type="redirectAction",params={"actionName","updateCust","id","${custId}"})
public class DoAddStrategyAction {
	@Autowired
	private CustService custService;
	private String custId;
	private String strategy;
	
	public String execute(){
		if(custId!=null){
			custService.addStrategy(Integer.parseInt(custId), strategy);
		}
		return "success";
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
	
}

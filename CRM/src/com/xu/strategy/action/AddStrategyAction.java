package com.xu.strategy.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;

@Result(name="success",location="strategy.jsp")
@Namespace("/customer")
@Action("addStrategy")
public class AddStrategyAction {
	@Autowired
	private CustService custService;
	private String custId;
	private String strategy;
	
	public String execute(){
		Customer customer = custService.get(Customer.class, Integer.valueOf(custId));
		strategy = customer.getStrategy();
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

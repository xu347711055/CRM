package com.xu.customer.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.xu.common.util.CommonUtil;
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;

@ParentPackage("cms")
@Action("/updateCust")
@Namespace("/customer")
@Result(name="success",location="updateCust.jsp")
public class UpdateCustAction implements ModelDriven<Customer> {
	@Autowired
	private CustService custService;
	private Customer customer = new Customer();
	private String formatedDate;
	private String resultType;
	
	public String execute(){
		customer = custService.get(Customer.class, customer.getId());
		formatedDate = CommonUtil.dateConvert(customer.getContactDate());
		return"success";
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	@Override
	public Customer getModel() {
		return this.customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getFormatedDate() {
		return formatedDate;
	}

	public void setFormatedDate(String formatedDate) {
		this.formatedDate = formatedDate;
	}
	
}

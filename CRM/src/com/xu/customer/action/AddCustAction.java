package com.xu.customer.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@ParentPackage("cms")
@Namespace("/customer")
@Result(name="success",location="addcust.jsp")
@Action("addCust")
public class AddCustAction {
	private String custNum;
	
	public String execute(){
		custNum = String.valueOf(new Date().getTime());
		return "success";
	}

	public String getCustNum() {
		return custNum;
	}

	public void setCustNum(String custNum) {
		this.custNum = custNum;
	}
	
}

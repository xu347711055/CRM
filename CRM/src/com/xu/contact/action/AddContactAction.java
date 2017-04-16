package com.xu.contact.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@ParentPackage("cms")
@Action("/addContact")
@Namespace("/customer")
@Result(name="success",location="addContact.jsp")
public class AddContactAction {
	private String custId;
	
	public String execute(){
		return "success";
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

}

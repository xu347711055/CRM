package com.xu.contactrecord.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@ParentPackage("cms")
@Namespace("/customer")
@Result(name="success",location="addRecord.jsp")
@Action("addRecord")
public class AddRecordAction {
	private String custId;
	private String cname;
	
	public String execute(){
		return "success";
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
}

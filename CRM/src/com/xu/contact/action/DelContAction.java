package com.xu.contact.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.common.Constants.Constant;
import com.xu.contact.domain.Contact;
import com.xu.contact.service.ContactService;

@ParentPackage("cms")
@Namespace("/customer")
@Action("delCont")
@InterceptorRef("privilegeStack")
@Result(name=Constant.ResultType_ListContByCust, type="redirectAction", 
		params={"actionName","${resultType}.action","custId","${custId}"})
public class DelContAction {
	@Autowired
	private ContactService contService;
	private String resultType;
	private int id;
	private int custId;
	
	public String execute(){
		Contact contact = contService.get(Contact.class, id);
		contact.setState(0);
		contService.update(contact);
		return resultType;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}
}

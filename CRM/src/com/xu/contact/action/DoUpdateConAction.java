package com.xu.contact.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.xu.common.Constants.Constant;
import com.xu.contact.domain.Contact;
import com.xu.contact.service.ContactService;
import com.xu.customer.domain.Customer;

@ParentPackage("cms")
@Action("doUpdateCon")
@Namespace("/customer")
@Results({@Result(name=Constant.ResultType_ListContByUser,type="redirectAction",params={"actionName",Constant.ResultType_ListContByUser,"custId","${custId}"}),
	@Result(name=Constant.ResultType_ListContByCust,type="redirectAction",params={"actionName",Constant.ResultType_ListContByCust,"custId","${custId}"}),
	@Result(name=Constant.ResultType_ListCustManage,location="/customer/"+Constant.ResultType_ListCustManage+".action",type="redirect"),
	@Result(name=Constant.ResultType_ListCustMain,location="/"+Constant.ResultType_ListCustMain+".action",type="redirect")})

public class DoUpdateConAction implements ModelDriven<Contact> {
	@Autowired
	private ContactService contactService;
	private String custId;
	private Contact contact = new Contact();
	private String resultType;
	
	public String execute(){
		System.out.println(resultType);
		Customer customer = new Customer();
		customer.setId(Integer.valueOf(custId));
		contact.setCust(customer);
		contact.setState(1);
		contactService.saveContact(contact);
		return resultType;
	}
	
	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@Override
	public Contact getModel() {
		return this.contact;
	}
}

package com.xu.contact.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.xu.contact.domain.Contact;
import com.xu.contact.service.ContactService;
import com.xu.customer.domain.Customer;

@ParentPackage("cms")
@Action("doUpdateCon")
@Namespace("/customer")
@Results({@Result(name="listContByUser",type="redirectAction",params={"actionName","listContByUser","custId","${custId}"}),
	@Result(name="listContByCust",type="redirectAction",params={"actionName","listContByCust","custId","${custId}"}),
	@Result(name="custManage",location="/customer/custManage.action",type="redirect")})

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
		System.out.println("******contact:"+contact);
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

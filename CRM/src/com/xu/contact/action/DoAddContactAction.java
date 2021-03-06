package com.xu.contact.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.xu.contact.domain.Contact;
import com.xu.contact.service.ContactService;
import com.xu.customer.domain.Customer;

@ParentPackage("cms")
@Action("/doAddContact")
@Namespace("/customer")
@InterceptorRef("privilegeStack")
@Result(name="success",type="redirectAction",params={"actionName","listContByCust","custId","${custId}"})
public class DoAddContactAction implements ModelDriven<Contact>{
	@Autowired
	private ContactService contactService;
	private Contact contact = new Contact();
	private String custId;
	
	public String execute(){
		Customer customer = new Customer();
		if(custId!=null){
			customer.setId(Integer.valueOf(custId));
		}
		contact.setCust(customer);
		contact.setState(1);
		contactService.saveContact(contact);
		return "success";
	}
	
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getCustId() {
		return custId;
	}


	public void setCustId(String custId) {
		this.custId = custId;
	}

	@Override
	public Contact getModel() {
		return this.contact;
	}
}

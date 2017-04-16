package com.xu.contact.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.contact.domain.Contact;
import com.xu.contact.service.ContactService;

@ParentPackage("cms")
@Action("updateContact")
@Namespace("/customer")
@Result(name="success",location="updateContact.jsp")
public class UpdateContactAction {
	@Autowired
	private ContactService contactService;
	private String id;
	private Contact contact;
	private String custId;
	private String resultType;
	
	public String execute(){
		contact = contactService.get(Contact.class, Integer.valueOf(id));
		return "success";
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
	
}

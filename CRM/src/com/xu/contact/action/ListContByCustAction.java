package com.xu.contact.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.contact.domain.Contact;
import com.xu.contact.service.ContactService;
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;

@ParentPackage("cms")
@Action("/listContByCust")
@Namespace("/customer")
@Result(name="success",location="contactList.jsp")
public class ListContByCustAction {
	@Autowired
	private ContactService contactService;
	@Autowired
	private CustService custService;
	private String custId;
	private List<Contact> contactList;
	private Customer customer;
	private String resultType;
	
	public String execute(){
		int customerId = Integer.valueOf(custId);
		Map<String, Object> conditions = new HashMap<>();
		Customer cust = new Customer();
		if(custId!=null){
			cust.setId(customerId);
		}
		conditions.put("cust", cust);
		contactList = contactService.list(conditions, Contact.class, null);
		customer = custService.get(Customer.class, customerId);
		this.resultType="listContByCust";
		return "success";
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

}

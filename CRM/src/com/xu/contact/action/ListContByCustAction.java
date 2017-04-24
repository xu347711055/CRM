package com.xu.contact.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.xu.common.Constants.Constant;
import com.xu.common.page.PageVO;
import com.xu.contact.domain.Contact;
import com.xu.contact.service.ContactService;
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;

@ParentPackage("cms")
@Action("/listContByCust")
@Namespace("/customer")
@Result(name="success",location="contactList.jsp")
@InterceptorRef("privilegeStack")
public class ListContByCustAction implements ModelDriven<PageVO<Contact>> {
	@Autowired
	private ContactService contactService;
	@Autowired
	private CustService custService;
	private String custId;
	private Customer customer;
	private String resultType;
	private PageVO<Contact> pagevo = new PageVO<>();
	
	public String execute(){
		int customerId = Integer.valueOf(custId);
		Map<String, Object> conditions = new HashMap<>();
		Customer cust = new Customer();
		if(custId!=null){
			cust.setId(customerId);
		}
		conditions.put("cust", cust);
		conditions.put("state", 1);
		pagevo = contactService.listByPage(Contact.class, pagevo, conditions, null);
		customer = custService.get(Customer.class, customerId);
		this.resultType=Constant.ResultType_ListContByCust;
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

	public PageVO<Contact> getPagevo() {
		return pagevo;
	}

	public void setPagevo(PageVO<Contact> pagevo) {
		this.pagevo = pagevo;
	}

	@Override
	public PageVO<Contact> getModel() {
		return this.pagevo;
	}

}

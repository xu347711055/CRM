package com.xu.contactrecord.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xu.contactrecord.domain.ContactRecord;
import com.xu.contactrecord.service.RecordService;
import com.xu.customer.domain.Customer;
import com.xu.user.domain.User;

@ParentPackage("cms")
@Namespace("/customer")
@Result(name="success",type="redirectAction",
	params={"actionName","listRecordByCust","custId","${custId}","cname","${cname}"})
@Action("doAddRecord")
@InterceptorRef("privilegeStack")
public class DoAddRecordAction implements ModelDriven<ContactRecord>{
	@Autowired
	private RecordService recordService;
	private ContactRecord record = new ContactRecord();
	private String custId;
	private String cname;
	
	public String execute(){
		User creater = (User) ActionContext.getContext().getSession().get("user");
		record.setCreater(creater);
		record.setUpdater(creater);
		record.setState(1);
		Customer cust = new Customer();
		cust.setId(Integer.valueOf(custId));
		record.setCust(cust);
		System.out.println(record);
		
		recordService.add(record);
		return "success";
	}
	
	
	public ContactRecord getRecord() {
		return record;
	}


	public void setRecord(ContactRecord record) {
		this.record = record;
	}


	public String getCustId() {
		return custId;
	}


	public void setCustId(String custId) {
		this.custId = custId;
	}


	public String getCname() {
		return cname;
	}


	public void setCname(String cname) {
		this.cname = cname;
	}


	@Override
	public ContactRecord getModel() {
		return this.record;
	}
}

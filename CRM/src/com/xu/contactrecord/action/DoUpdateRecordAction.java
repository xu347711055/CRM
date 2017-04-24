package com.xu.contactrecord.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.xu.contactrecord.domain.ContactRecord;
import com.xu.contactrecord.service.RecordService;
import com.xu.customer.domain.Customer;

@ParentPackage("cms")
@Namespace("/customer")
@Results({@Result(name="listRecordByCust",type="redirectAction",
params={"actionName","listRecordByCust","custId","${custId}","cname","${cname}"}),
	@Result(name="listRecordByUser",type="redirect",location="listRecordByUser.action")})
@Action("doUpdateRecord")
@InterceptorRef("privilegeStack")
public class DoUpdateRecordAction implements ModelDriven<ContactRecord>{
	@Autowired
	private RecordService recordService;
	private ContactRecord record = new ContactRecord();
	private String custId;
	private String cname;
	private String recordId;
	private String updateType;		//更新类型
	
	public String execute(){
		record.setId(Integer.valueOf(recordId));
		Customer cust = new Customer();
		cust.setId(Integer.valueOf(custId));
		record.setCust(cust);
		record.setState(1);
		recordService.merge(record);
		System.out.println(record);
		return updateType;
	}
	
	
	public String getUpdateType() {
		return updateType;
	}


	public void setUpdateType(String updateType) {
		this.updateType = updateType;
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


	public String getRecordId() {
		return recordId;
	}


	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}


	@Override
	public ContactRecord getModel() {
		return this.record;
	}
}

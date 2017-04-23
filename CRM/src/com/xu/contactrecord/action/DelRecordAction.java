package com.xu.contactrecord.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.contactrecord.domain.ContactRecord;
import com.xu.contactrecord.service.RecordService;

@ParentPackage("cms")
@Namespace("/customer")
@Action("delRecord")
@Result(name="success",type="redirectAction",params={"actionName","${updateType}.action","custId","${custId}"})
public class DelRecordAction {
	
	private int id;
	private String updateType;
	private int custId;
	@Autowired
	private RecordService recordService;
	
	public String execute(){
		ContactRecord record = recordService.get(ContactRecord.class, id);
		record.setState(0);
		recordService.update(record);
		custId = record.getCust().getId();
		return "success";
	}

	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	
}

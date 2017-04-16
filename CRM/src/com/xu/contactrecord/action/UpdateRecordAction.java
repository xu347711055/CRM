package com.xu.contactrecord.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.common.util.CommonUtil;
import com.xu.contactrecord.domain.ContactRecord;
import com.xu.contactrecord.service.RecordService;

@ParentPackage("cms")
@Namespace("/customer")
@Result(name="success",location="updateRecord.jsp")
@Action("updateRecord")
public class UpdateRecordAction {
	@Autowired
	private RecordService recordService;
	private String recordId;
	private String custId;
	private String cname;
	private ContactRecord record;
	private String formatedDate;
	private String updateType;
	
	public String execute(){
		record = recordService.get(ContactRecord.class, Integer.valueOf(recordId));
		formatedDate = CommonUtil.dateConvert(record.getContactDate());
		return "success";
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	public String getFormatedDate() {
		return formatedDate;
	}

	public void setFormatedDate(String formatedDate) {
		this.formatedDate = formatedDate;
	}

	public ContactRecord getRecord() {
		return record;
	}

	public void setRecord(ContactRecord record) {
		this.record = record;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
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
	
}

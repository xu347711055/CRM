package com.xu.contactrecord.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.common.page.PageVO;
import com.xu.contactrecord.domain.ContactRecord;
import com.xu.contactrecord.service.RecordService;
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;

@ParentPackage("cms")
@Namespace("/customer")
@Result(name="success",location="contactRecord.jsp")
@Action("listRecordByCust")
public class ListRecordByCustAction {
	@Autowired
	private RecordService recordService;
	@Autowired
	private CustService custService;
	private String custId;
	private String cname;
	private String updateType;
	private PageVO<ContactRecord> pagevo = new PageVO<>();
	
	public String execute(){
		Customer cust = custService.get(Customer.class, Integer.valueOf(custId));
		cname = cust.getCname();
		Map<String,Object> conditions = new HashMap<>();
		conditions.put("cust", cust);
		pagevo = recordService.listByPage(ContactRecord.class, pagevo, conditions, null);
		updateType = "listRecordByCust";
		return "success";
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public PageVO<ContactRecord> getPagevo() {
		return pagevo;
	}

	public void setPagevo(PageVO<ContactRecord> pagevo) {
		this.pagevo = pagevo;
	}
	
}

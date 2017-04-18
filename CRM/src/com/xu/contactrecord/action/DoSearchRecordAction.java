package com.xu.contactrecord.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.xu.common.page.PageVO;
import com.xu.contactrecord.domain.ContactRecord;
import com.xu.contactrecord.service.RecordService;
import com.xu.customer.domain.Customer;
import com.xu.customer.service.CustService;
import com.xu.user.domain.User;

@ParentPackage("cms")
@Namespace("/customer")
@Result(name="success",location="contactRecord.jsp")
@Action("doSearchRecord")
public class DoSearchRecordAction {
	@Autowired
	private RecordService recordService;
	@Autowired
	private CustService custService;
	
	private String custId;
	private String cname;
	private String updateType;
	private PageVO<ContactRecord> pagevo = new PageVO<>();

	private String contactName;
	private Date contactDate;
	private String custName;
	
	public String execute(){
		Map<String, Object> conditionsEq = new HashMap<>();
		Map<String, Object> conditionLike = new HashMap<>();
		Map<String, List<Object>> orEq = new HashMap<>();
		//封装查询条件
		if(!StringUtils.isEmpty(contactName)){
			conditionLike.put("contactName", contactName);
		}
		if(contactDate!=null){
			conditionsEq.put("ct.contactDate", contactDate);
		}
		if(!StringUtils.isEmpty(custName)){
			Map<String, Object> custNameCondition = new HashMap<>();
			custNameCondition.put("cname", custName);
			List<Customer> custList = custService.listLike(custNameCondition, Customer.class, null);
			List<Object> custids = new ArrayList<>();
			for(Customer cust : custList){
				custids.add(cust.getId());
			}
			orEq.put("cust_id", custids);
		}
		//判断应该按客户查还是按用户查
		if(!StringUtils.isEmpty(custId)){
			conditionsEq.put("cust_id", custId);
			pagevo = recordService.getRecordsByConditions(pagevo, conditionsEq, conditionLike, null, null);
		}else{
			User user = (User) ActionContext.getContext().getSession().get("user");
			if(user.getAdmin()==1){
				pagevo = recordService.getRecordsByConditions(pagevo, conditionsEq, conditionLike, null, orEq);
			}else{
				conditionsEq.put("u.id", user.getId());
				pagevo = recordService.getRecordsByConditions(pagevo, conditionsEq, conditionLike, null, orEq);
			}
		}
		System.out.println("!!!!!!!!!!!!!!!!updateType:"+updateType);
		return "success";
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
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

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	public PageVO<ContactRecord> getPagevo() {
		return pagevo;
	}

	public void setPagevo(PageVO<ContactRecord> pagevo) {
		this.pagevo = pagevo;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Date getContactDate() {
		return contactDate;
	}

	public void setContactDate(Date contactDate) {
		this.contactDate = contactDate;
	}
	
}
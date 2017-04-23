package com.xu.contactrecord.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.xu.common.page.PageVO;
import com.xu.contactrecord.domain.ContactRecord;
import com.xu.contactrecord.service.RecordService;
import com.xu.user.domain.User;

@ParentPackage("cms")
@Namespace("/customer")
@Result(name="success",location="contactRecord.jsp")
@Action("listRecordByUser")
public class ListRecordByUserAction {
	@Autowired
	private RecordService recordService;
	private PageVO<ContactRecord> pagevo = new PageVO<>();
	private String updateType;
	
	public String execute(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(user.getAdmin()==1){
			Map<String,String> orders = new HashMap<>();
			orders.put("id", "asc");
			Map<String,Object> conditions = new HashMap<>();
			conditions.put("state", 1);
			pagevo = recordService.listByPage(ContactRecord.class, pagevo, conditions, orders);
		}else{
			pagevo = recordService.getRecordsByUser(user.getId(), pagevo);
		}
		updateType = "listRecordByUser";
		return "success";
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
	
}

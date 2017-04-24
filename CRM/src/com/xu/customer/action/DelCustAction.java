package com.xu.customer.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.xu.customer.service.CustService;
import com.xu.user.domain.User;

@ParentPackage("cms")
@Namespace("/customer")
@Action("delCust")
@Result(name="success",location="custManage.action",type="redirect")
@InterceptorRef("privilegeStack")
public class DelCustAction {
	@Autowired
	private CustService custService;
	private String custId;
	
	public String execute(){
		User curUser = (User) ActionContext.getContext().getSession().get("user");
		if(curUser.getAdmin()==1){
			custService.adminDelCust(Integer.valueOf(custId));
			return "success";
		}
		custService.delCust(Integer.parseInt(custId), curUser.getId());
		return "success";
	}
	
	public String getCustId() {
		return custId;
	}
	
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
}

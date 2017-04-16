package com.xu.customer.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.user.domain.User;
import com.xu.user.service.UserService;

@ParentPackage("json-default")
@Action("checkOwner")
@Namespace("/customer")
@Result(name="success",type="json",params={"root","result"})
public class CheckOwnerAction {
	@Autowired
	private UserService userService;
	private String account;
	private String result;
	
	public String execute(){
		User user = userService.get(User.class, "account", account);
		if(user!=null){
			result = "1";
		}
		return "success";
	}

	

	public String getAccount() {
		return account;
	}



	public void setAccount(String account) {
		this.account = account;
	}



	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
}

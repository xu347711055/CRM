package com.xu.user.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Action("login")
@Namespace("/user")
@Result(name="success",location="login.jsp")
public class LoginAction {

	public String execute(){
		return "success";
	}
	
}

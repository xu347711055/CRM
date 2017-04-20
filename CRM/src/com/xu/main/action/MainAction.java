package com.xu.main.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
import com.xu.user.domain.User;

@ParentPackage("cms")
@Results({@Result(name="admin",location="adminIndex.jsp"),
	@Result(name="login",location="user/login.jsp"),
	@Result(name="user",location="index.jsp")})
@Action("mainAction")
public class MainAction {
	
	private String username;
	public String execute(){
		User user = (User) ActionContext.getContext().getSession().get("user");
		if(user==null){
			return "login";
		}
		if(user.getAdmin()!=1){
			return "user";
		}
		username = user.getName();
		return "admin";
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}

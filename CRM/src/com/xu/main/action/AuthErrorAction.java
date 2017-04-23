package com.xu.main.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

@Action("authError")
@Result(name="success",location="authError.jsp")
public class AuthErrorAction {

	public String execute(){
		return "success";
	}
}

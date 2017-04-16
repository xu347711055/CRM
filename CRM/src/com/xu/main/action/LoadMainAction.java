package com.xu.main.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

@ParentPackage("cms")
@Result(name="success",location="main.jsp")
@Action("loadMainAction")
public class LoadMainAction {

	public String execute(){
		return "success";
	}
	
}

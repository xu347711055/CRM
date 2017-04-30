package com.xu.user.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.xu.user.domain.User;
import com.xu.user.service.UserService;

@ParentPackage("cms")
@Namespace("/system")
@Action("activeUser")
@Result(name="success",location="listUser.action",type="redirect")
public class ActiveUserAction {
	@Autowired
	private UserService userService;
	private int id;
	
	public String execute(){
		User onlineUser = (User) ActionContext.getContext().getSession().get("user");
		if(onlineUser.getAdmin()!=1){
			return "noAuth"; 
		}
		User user = userService.get(User.class, id);
		user.setStatus(1);
		userService.update(user);
		return "success";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	 
}

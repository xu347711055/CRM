package com.xu.user.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.xu.user.domain.User;
import com.xu.user.service.UserService;

@ParentPackage("cms")
@Action("listUser")
@Namespace("/system")
@Results({@Result(name="success",location="userList.jsp"),
	@Result(name="noAuth",location="user/login.action",type="redirect")})//不是管理员则跳回登陆页
public class ListUserAction {
	@Autowired
	private UserService userService;
	private List<User> users;
	
	public String execute(){
		User onlineUser = (User) ActionContext.getContext().getSession().get("user");
		if(onlineUser.getAdmin()!=1){
			return "noAuth"; 
		}
		Map<String, String> orders = new HashMap<>();
		orders.put("admin", "desc");
		orders.put("id", "asc");
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("status", 1);
		users = userService.list(conditions, User.class, orders);
		users.remove(onlineUser);	//把自己去掉
		return "success";
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}

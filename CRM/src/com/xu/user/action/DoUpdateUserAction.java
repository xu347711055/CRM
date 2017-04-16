package com.xu.user.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xu.dept.domain.Department;
import com.xu.user.domain.User;
import com.xu.user.service.UserService;

@ParentPackage("cms")
@Action("doUpdateUser")
@Namespace("/system")
@Results({@Result(name="success",location="listUser.action",type="redirect"),
	@Result(name="noAuth",location="user/login.action",type="redirect")})//不是管理员则跳回登陆页
public class DoUpdateUserAction implements ModelDriven<User> {
	@Autowired
	private UserService userService;
	private User user = new User();
	private String deptId;
	private String msg;
	
	public String execute(){
		User onlineUser = (User) ActionContext.getContext().getSession().get("user");
		if(onlineUser.getAdmin()!=1){
			return "noAuth"; 
		}
		user.setStatus(1);
		Department dept = new Department();
		dept.setId(Integer.valueOf(deptId));//设置部门
		user.setDept(dept);
		userService.merge(user);
		return "success";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public User getModel() {
		return this.user;
	}
	
}

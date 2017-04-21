package com.xu.user.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xu.common.util.MD5Util;
import com.xu.dept.domain.Department;
import com.xu.user.domain.User;
import com.xu.user.service.UserService;

@ParentPackage("cms")
@Action("doAddUser")
@Namespace("/system")
@Results({@Result(name="success",location="listUser.action",type="redirect"),
	@Result(name="exsist",location="addUser.jsp"),
	@Result(name="noAuth",location="user/login.action",type="redirect")})//不是管理员则跳回登陆页
public class DoAddUserAction implements ModelDriven<User> {
	@Autowired
	private UserService userService;
	private User user = new User();
	private int deptId;
	private String msg;
	
	public String execute() throws Exception{
		User onlineUser = (User) ActionContext.getContext().getSession().get("user");
		if(onlineUser.getAdmin()!=1){
			return "noAuth"; 
		}
		Department dept = new Department();
		dept.setId(deptId);
		User userExsist = userService.get(User.class, "account", user.getAccount());
		if(userExsist!=null){
			msg = "该用户名已存在";
			return "exsist";
		}
		String password = user.getPassword();
		user.setPassword(MD5Util.md5(password));
		user.setDept(dept);
		userService.add(user);
		System.out.println(user);
		return "success";
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	@Override
	public User getModel() {
		return this.user;
	}
}


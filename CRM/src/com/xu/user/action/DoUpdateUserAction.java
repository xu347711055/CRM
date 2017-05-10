package com.xu.user.action;

import org.apache.commons.lang3.StringUtils;
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
import com.xu.role.domain.Role;
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
	private int roleId;
	private String chpwd;	//修改的密码
	
	public String execute() throws Exception{
		User onlineUser = (User) ActionContext.getContext().getSession().get("user");
		if(onlineUser.getAdmin()!=1){
			return "noAuth"; 
		}
		user.setStatus(1);
		if(deptId!=null){
			Department dept = new Department();
			dept.setId(Integer.valueOf(deptId));//设置部门
			user.setDept(dept);
		}
		if(roleId!=0){
			Role role = new Role();
			role.setId(roleId);
			user.setRole(role);
		}
		if(!StringUtils.isEmpty(chpwd)){
			user.setPassword(MD5Util.md5(chpwd));//先把密码加密再保存
		}
		userService.merge(user);
		return "success";
	}

	public String getChpwd() {
		return chpwd;
	}

	public void setChpwd(String chpwd) {
		this.chpwd = chpwd;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

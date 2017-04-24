package com.xu.user.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.dept.domain.Department;
import com.xu.role.domain.Role;
import com.xu.user.domain.User;
import com.xu.user.service.UserService;

@ParentPackage("cms")
@Namespace("/system")
@Action("setUserRole")
@Result(name="success",location="listRole.action",type="redirect")
public class SetUserRoleAction {
	@Autowired
	private UserService userService;
	private int roleId;
	private int deptId;
	private int userId;
	
	public String execute(){
		System.out.println("roleId:"+roleId);
		System.out.println("deptId"+deptId);
		System.out.println("userId"+userId);
		Role role = new Role();
		role.setId(roleId);
		if(userId!=0){
			User user = userService.get(User.class, userId);
			user.setRole(role);
			userService.update(user);
		}else{
			Map<String,Object> conditions = new HashMap<>();
			Department dept = new Department();
			dept.setId(deptId);
			conditions.put("dept", dept);
			List<User> userList = userService.list(conditions, User.class, null);
			for(User user : userList){
				user.setRole(role);
				userService.update(user);
			}
		}
		
		return "success";
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}

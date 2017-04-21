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
import com.xu.user.domain.User;
import com.xu.user.service.UserService;

@ParentPackage("json-default")
@Namespace("/user")
@Action("getUserByDept")
@Result(name="success",type="json",params={"root","userList"})
public class GetUserByDeptAction {
	@Autowired
	private UserService userService;
	private List<User> userList;
	private String deptId;
	
	public String execute(){
		Map<String,Object> conditions = new HashMap<>();
		Department dept = new Department();
		dept.setId(Integer.valueOf(deptId));
		conditions.put("dept", dept);
		userList = userService.list(conditions, User.class, null);
		return "success";
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
}

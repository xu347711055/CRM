package com.xu.user.action;

import java.util.ArrayList;
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
import com.xu.user.domain.UserVO;
import com.xu.user.service.UserService;

@ParentPackage("json-default")
@Namespace("/user")
@Action("getUserByDept")
@Result(name="success",type="json",params={"root","userList"})
public class GetUserByDeptAction {
	@Autowired
	private UserService userService;
	private List<UserVO> userList = new ArrayList<UserVO>();
	private String deptId;
	
	public String execute(){
		Map<String,Object> conditions = new HashMap<>();
		Department dept = new Department();
		dept.setId(Integer.valueOf(deptId));
		conditions.put("dept", dept);
		List<User> list = userService.list(conditions, User.class, null);
		for(User u : list){
			UserVO userVO = new UserVO();
			userVO.setId(u.getId());
			userVO.setName(u.getName());
			userList.add(userVO);
		}
		return "success";
	}


	public List<UserVO> getUserList() {
		return userList;
	}


	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}


	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	
}

package com.xu.dept.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.dept.domain.Department;
import com.xu.dept.service.DeptService;

@ParentPackage("json-default")
@Action("getDept")
@Namespace("/dept")
@Result(name="success",type="json",params={"root","depts"})//不是管理员则跳回登陆页
public class GetDeptAction {
	@Autowired
	private DeptService deptService;
	private List<Department> depts;
	
	public String execute(){
		
		Map<String,String> orders = new HashMap<>();
		orders.put("id", "asc");
		depts = deptService.list(null, Department.class, orders);
		System.out.println(depts);
		return "success";
	}

	public List<Department> getDepts() {
		return depts;
	}

	public void setDepts(List<Department> depts) {
		this.depts = depts;
	}
	
}

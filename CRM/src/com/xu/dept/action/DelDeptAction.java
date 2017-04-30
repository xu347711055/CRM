package com.xu.dept.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.dept.domain.Department;
import com.xu.dept.service.DeptService;

@ParentPackage("cms")
@Namespace("/system")
@Action("delDept")
@Result(name="success",location="listDept.action",type="redirect")
public class DelDeptAction {
	@Autowired
	private DeptService deptService;
	private int id;
	
	public String execute(){
		Department dept = deptService.get(Department.class, id);
		deptService.delete(dept);
		return "success";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

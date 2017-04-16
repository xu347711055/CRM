package com.xu.dept.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xu.dept.domain.Department;
import com.xu.dept.service.DeptService;
import com.xu.user.domain.User;

@ParentPackage("cms")
@Action("doUpdateDept")
@Namespace("/system")
@Results({@Result(name="success",location="listDept.action",type="redirect"),
	@Result(name="noAuth",location="user/login.action",type="redirect")})//不是管理员则跳回登陆页
public class DoUpdateDeptAction implements ModelDriven<Department> {

	@Autowired
	private DeptService deptService;
	private Department dept = new Department();
	
	public String execute(){
		User onlineUser = (User) ActionContext.getContext().getSession().get("user");
		if(onlineUser.getAdmin()!=1){
			return "noAuth"; 
		}
		if(dept.getId()!=0){
			deptService.update(dept);
			return "success";
		}
		deptService.add(dept);
		return "success";
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@Override
	public Department getModel() {
		return this.dept;
	}

}

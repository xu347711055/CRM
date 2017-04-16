package com.xu.dept.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.xu.common.page.PageVO;
import com.xu.dept.domain.Department;
import com.xu.dept.service.DeptService;
import com.xu.user.domain.User;

@Action("listDept")
@Namespace("/system")
@Results({@Result(name="success",location="deptList.jsp"),
	@Result(name="noAuth",location="user/login.action",type="redirect")})//不是管理员则跳回登陆页
public class ListDeptAction implements ModelDriven<PageVO<Department>>{
	@Autowired
	private DeptService deptService;
	private PageVO<Department> pagevo = new PageVO<>();
	
	public String execute(){
		User onlineUser = (User) ActionContext.getContext().getSession().get("user");
		if(onlineUser.getAdmin()!=1){
			return "noAuth"; 
		}
		pagevo = deptService.listByPage(Department.class, pagevo, null, null);
		return "success";
	}

	@Override
	public PageVO<Department> getModel() {
		return this.pagevo;
	}

	public PageVO<Department> getPagevo() {
		return pagevo;
	}

	public void setPagevo(PageVO<Department> pagevo) {
		this.pagevo = pagevo;
	}
	
}

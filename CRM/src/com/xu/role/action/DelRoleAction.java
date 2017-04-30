package com.xu.role.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.role.domain.Role;
import com.xu.role.service.RoleService;

@ParentPackage("cms")
@Namespace("/system")
@Action("delRole")
@Result(name="success",location="listRole.action",type="redirect")
public class DelRoleAction {
	@Autowired
	private RoleService roleService;
	private int id;
	
	public String execute(){
		Role role = new Role();
		role.setId(id);
		roleService.delete(role);
		return "success";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

package com.xu.role.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.common.page.PageVO;
import com.xu.role.domain.Role;
import com.xu.role.service.RoleService;

@Namespace("/system")
@Action("listRole")
@Result(name="success",location="roleList.jsp")
public class ListRoleAction {
	@Autowired
	private RoleService roleService;
	private PageVO<Role> pagevo = new PageVO<>();
	
	public String execute(){
		pagevo = roleService.listByPage(Role.class, pagevo, null, null);
		return "success";
	}

	public PageVO<Role> getPagevo() {
		return pagevo;
	}

	public void setPagevo(PageVO<Role> pagevo) {
		this.pagevo = pagevo;
	}
	
}

package com.xu.role.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.common.page.PageVO;
import com.xu.module.domain.Module;
import com.xu.module.service.ModuleService;
import com.xu.role.domain.Role;
import com.xu.role.service.RoleService;

@Namespace("/system")
@Action("listRole")
@Result(name="success",location="roleList.jsp")
public class ListRoleAction {
	@Autowired
	private RoleService roleService;
	@Autowired
	private ModuleService moduleService;
	private PageVO<Role> pagevo = new PageVO<>();
	private List<Module> moduleList;
	
	public String execute(){
		pagevo = roleService.listByPage(Role.class, pagevo, null, null);
		moduleList = moduleService.list(null, Module.class, null);
		return "success";
	}

	public List<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(List<Module> moduleList) {
		this.moduleList = moduleList;
	}

	public PageVO<Role> getPagevo() {
		return pagevo;
	}

	public void setPagevo(PageVO<Role> pagevo) {
		this.pagevo = pagevo;
	}
	
}

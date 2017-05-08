package com.xu.role.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.privilege.domain.Auth;
import com.xu.role.domain.Role;
import com.xu.role.service.RoleService;

@ParentPackage("cms")
@Namespace("/system")
@Action("doAddRole")
@Result(name="success",type="redirect",location="listRole.action")
public class DoAddRoleAction {
	@Autowired
	private RoleService roleService;
	private int[] authId;	//权限id
	private int id;	//角色id
	private String name;	//角色名称
	
	public String execute(){
		Role role = new Role();	//新增
		if(id!=0){		
			//修改角色
			role = roleService.get(Role.class, id);
		}
		role.setName(name);
		List<Auth> auths = new ArrayList<>();
		for(int i = 0; i < authId.length; i++){
			Auth auth = new Auth();
			auth.setId(authId[i]);
			auths.add(auth);
		}
		role.setAuths(auths);
		roleService.merge(role);
		return "success";
	}

	public int[] getAuthId() {
		return authId;
	}

	public void setAuthId(int[] authId) {
		this.authId = authId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

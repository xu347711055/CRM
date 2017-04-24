package com.xu.role.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.xu.privilege.domain.Auth;
import com.xu.privilege.service.AuthService;
import com.xu.role.service.RoleService;

/**
 * 异步通过角色获取权限
 * @author xu
 *
 */
@ParentPackage("json-default")
@Namespace("/system")
@Action("getAuthsByRole")
@Result(name="success",type="json",params={"root","authIds"})
public class GetAuthsByRoleAction {
	@Autowired
	private RoleService roleService;
	@Autowired
	private AuthService authService;
	private int id;	//角色Id
	private List<Integer> authIds = new ArrayList<>();
	
	public String execute(){
		/*Role role = roleService.get(Role.class, id);
		authList = role.getAuths();*/
		List<Auth> authList = authService.listAuthByRoleId(id);
		for(Auth auth : authList){
			authIds.add(auth.getId());
		}
		return "success";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getAuthIds() {
		return authIds;
	}

	public void setAuthIds(List<Integer> authIds) {
		this.authIds = authIds;
	}

}

package com.xu.user.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.xu.privilege.domain.Auth;
import com.xu.role.domain.Role;
import com.xu.user.domain.User;
import com.xu.user.service.UserService;
/**
 * 登陆检验
 * @author xu
 *
 */
@Action("doLogin")
@Namespace("/user")
@Results({
	@Result(name="success",location="mainAction.action",type="redirect"),
	@Result(name="fail",location="login.jsp")})
public class DoLoginAction {
	@Autowired
	private UserService userService;
	private String account;
	private String password;
	private String msg;
	private String username;
	
	public String execute() throws Exception{
		User user = userService.checkLogin(account, password);
		System.out.println("action:user:"+user);
		if(user!=null){
			Map<String, Object> session = ActionContext.getContext().getSession();
			//用户信息放入session
			session.put("user", user);
			Role role = user.getRole();
			if(role!=null){
				List<Auth> auths = role.getAuths();
				List<String> urlList = new ArrayList<String>();
				for(Auth auth:auths){
					urlList.add(auth.getAccessPath());
				}
				//用户权限放入session
				session.put("auth", urlList);
			}
			
			return "success";
		}
		this.msg = "用户名或密码错误";
		return "fail";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}

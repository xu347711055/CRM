package com.xu.user.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
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
	
	public String execute() throws Exception{
		User user = userService.checkLogin(account, password);
		if(user!=null){
			ActionContext.getContext().getSession().put("user", user);
			return "success";
		}
		this.msg = "用户名或密码错误";
		return "fail";
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

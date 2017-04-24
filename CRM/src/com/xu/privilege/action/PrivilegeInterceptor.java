package com.xu.privilege.action;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PrivilegeInterceptor extends AbstractInterceptor  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		List<String> urlList = (List<String>) session.get("auth");
		String actionName = invocation.getAction().getClass().getName();
		
		
		logger.info("action name:" + actionName);
		System.out.println("action name:" + actionName);
		for(String url:urlList){
			if(actionName.equals(url)){
				logger.info("您有权限访问:"+actionName);
				System.out.println("您有权限访问:"+actionName);
				return invocation.invoke();
			}
		}
		logger.warn("您无权访问:"+actionName);
		System.out.println("您无权访问:"+actionName);
		return "authError";
	}

}

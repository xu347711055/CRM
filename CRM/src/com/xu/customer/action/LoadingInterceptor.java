package com.xu.customer.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoadingInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				PrintWriter out = null;
				try {
					out = response.getWriter();
					out.write("<div id='loading' style='position:fixed;left:500px;top: 160px;'><img  src='/CRM/images/loading.gif'></img></div>");
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
		return invocation.invoke();
	}

}

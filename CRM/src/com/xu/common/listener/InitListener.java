package com.xu.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.xu.common.util.SpringUtil;

/**
 * Application Lifecycle Listener implementation class InitListener
 *启动配置contextPath
 *
 */
@WebListener
public class InitListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InitListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	String contextPath = event.getServletContext().getContextPath();
    	event.getServletContext().setAttribute("path", contextPath);
    	SpringUtil.init("application.xml");
    }
	
}

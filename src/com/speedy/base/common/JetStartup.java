package com.speedy.base.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class JetStartup implements ServletContextListener
{	
	public void contextInitialized(ServletContextEvent event) 
	{
		ApplicationContext ac1 = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
		

		ApplicaitonContextUtil.setApplicationContext(ac1);
		//FileUpload.generateUploadPath(event.getServletContext());
	}
	public void contextDestroyed(ServletContextEvent event) 
	{
	  
	}

}



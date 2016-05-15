package com.speedy.base.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicaitonContextUtil{
 
	    private static ApplicationContext applicationContext;  
	   
	     public static void setApplicationContext(ApplicationContext applicationContext1){
	    	 applicationContext = applicationContext1;
	    }
	     public static Object getBean(String name){
	         return applicationContext.getBean(name);
	     }
	 }

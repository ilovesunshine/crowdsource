/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Title: JfGlobalsListener.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-18 下午4:12:56
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfGlobalsListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context = arg0.getServletContext();
		String csstheme = context.getInitParameter("csstheme");
		String csssuffix = context.getInitParameter("csssuffix");
		String jssuffix = context.getInitParameter("jssuffix");
		context.setAttribute("csstheme", csstheme);
		context.setAttribute("csssuffix", csssuffix);
		context.setAttribute("jssuffix", jssuffix);
		
	}


}
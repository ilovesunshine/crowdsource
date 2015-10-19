/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import org.slf4j.Logger;

/**
 * @Title: JfLog.java
 * @Description: <br>
 * <br>
 * @Company: crowdsource
 * @Created on 2015-8-24 上午10:48:37
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfLog {

	/**
	 * 
	 */
	private JfLog() {
	}

	public static void debug(Logger log,String message) {

	}

	public static void warn(Logger log,String message) {

	}

	public static void info(Logger log,String message) {

	}

	public static void error(Logger log,String message) {
		if(log.isErrorEnabled()){
    		log.error(message);
    	}
	}
	
	public static void error(Logger log,String message,Exception e) {
		if(log.isErrorEnabled()){
    		log.error(e.getMessage(),message);
    	}
	}

}

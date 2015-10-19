/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.event;

import com.common.exception.JfEventException;

/**
 * @Title: JfPayEvent.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-17 下午5:15:27
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public interface JfPayEvent {
	
	/**
	 * 事件处理
	 * @throws JfEventException
	 */
	public void handle() throws JfEventException;

}

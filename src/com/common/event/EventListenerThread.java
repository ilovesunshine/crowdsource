/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.utils.JfLog;

/**
 * @Title: EventListenerThread.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-17 下午8:56:02
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class EventListenerThread extends Thread {

	private static final Logger LOG = LoggerFactory.getLogger(EventListenerThread.class);

	private boolean running = false;
	
	private EventManager manager = null;
	
	public EventListenerThread(EventManager manager){
		this.manager = manager;
	}
	
	public void setState(boolean state){
		this.running = state;
	}
	@Override
	public void run() {
		JfPayEvent event = null;
		while(running){
			try {
			    while((event = manager.getEventQueue().poll())!=null){
		        	if(LOG.isDebugEnabled()){
		        		JfLog.debug(LOG,"Begin to handle a event");
		        	}
                    try {
                        event.handle();
                        Thread.sleep(500);
                    } catch (Exception e) {
                    	{
                    		JfLog.error(LOG,"事件处理失败:"+e.getMessage(), e);
                    	}
                    }
			    }
	        	if(LOG.isDebugEnabled()){
	        		JfLog.debug(LOG,"No event ,sleep for " + manager.getInterval() + "....");
	        	}
                Thread.sleep(manager.getInterval());
			} catch (Exception e) {
	        	{
	        		JfLog.error(LOG,e.getMessage(), e);
	        	}
			} 
		}
	}

}

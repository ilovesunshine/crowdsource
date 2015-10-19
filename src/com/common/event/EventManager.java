/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.event;

import java.math.BigDecimal;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.exception.JfEventException;
import com.common.exception.JfPayException;
import com.common.utils.DateUtil;
import com.common.utils.JfLog;

/**
 * @Title: EventManager.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-17 下午8:58:41
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class EventManager {
	
	private static final Logger LOG = LoggerFactory.getLogger(EventManager.class);
	 
	private static EventManager manager = null;
	
	private EventListenerThread eventListenerThread = null;
	
	private Queue<JfPayEvent> eventQueue;
	
	private int interval = 30*1000;//5 minutes
	
	private static final Object LOCK = new Object();
	
	private EventManager(){
	}

	public static EventManager getInstance() throws JfEventException{
		try {
		    synchronized (LOCK) {
		        if(manager==null){
	                manager = new EventManager();
	                manager.init();
	            }
            }
			return manager;
		} catch (Exception e) {
			throw new JfEventException(e);
		}
	}
	
	private void init() throws JfPayException{
		try {
            this.eventQueue = new ConcurrentLinkedQueue<JfPayEvent>();
            this.start();
            	JfLog.info(LOG,"Event ListenerThread init success!");
        } catch (Exception e) {
        		JfLog.error(LOG,e.getMessage(),e);
            throw new JfPayException(e);
        }
	}
	
	public void setInterval(int seconds){
		this.interval = seconds * 1000;
	}
	
	public int getInterval(){
		return this.interval;
	}
	
	public Queue<JfPayEvent> getEventQueue(){
		return this.eventQueue;
	}
	
	public void start() throws JfEventException{
		try {
			if(eventListenerThread==null||!eventListenerThread.isAlive()){
				eventListenerThread = new EventListenerThread(this);
				eventListenerThread.setState(true);
				eventListenerThread.start();
				JfLog.info(LOG,"Event ListenerThread start success!");
			}else{
				JfLog.debug(LOG,"No event ListenerThread to start!");
			}
		} catch (Exception e) {
			JfLog.error(LOG,"Event listener thread start failed:"+e.getMessage(),e);
			throw new JfEventException(e);
		}
	}
	
	public void stop() throws JfEventException{
		try {
			if(eventListenerThread!=null){
				eventListenerThread.setState(false);
				eventListenerThread.interrupt();
				JfLog.info(LOG,"Event ListenerThread stop success!");
			}else{
				JfLog.info(LOG,"No event ListenerThread to stop!");
			}
		} catch (Exception e) {
			JfLog.error(LOG,"Event listener thread stop failed:"+e.getMessage(),e);
			throw new JfEventException(e);
		}
	}
	
	/**
	 * 
	 * @param tbName 数据源表名
	 * @param model 需要更新的实体
	 * @param fieldName 触发的字段名
	 * @param fieldValue 错误的数据
	 * @param newValue 修正的数据
	 */
	public void triggerEncryptCheck(String tbName, String pmKeyName,Long pmKeyValue,Object model, String fieldName,
	        BigDecimal fieldValue, BigDecimal newValue)  {
		try {
            EncryptedFieldWarnEvent event = new EncryptedFieldWarnEvent();
            event.setTbName(tbName);
            event.setPmKeyName(pmKeyName);
            event.setPmKeyValue(pmKeyValue);
            event.setModel(model);
            event.setFieldName(fieldName);
            event.setFiledValue(fieldValue);
            event.setNewValue(newValue);
            event.setTriggleTime(DateUtil.getCurrentTimestamp());
            this.trigger(event);
        } catch (Exception e) {
        		JfLog.error(LOG,"triggerEncryptCheck failed:"+e.getMessage(),e);
        }
	}
	
	/**
	 * 记录行为审核
	 * @param action
	 * @param message
	 * @param actObj
	 * @param actResult
	 * @param accountType
	 * @throws JfEventException
	 */
	public void triggerJfAuditor(String action,String message,String actObj,int actResult,String accountType){
	    try {
            JfAuditorEvent event = new JfAuditorEvent();
            event.setAction(action);
            event.setMessage(message);
            event.setActObj(actObj);
            event.setActResult(actResult);
            event.setAccountType(accountType);
            this.trigger(event);
        } catch (Exception e) {
        		JfLog.error(LOG,"triggerJfAuditor failed:"+e.getMessage(),e);
        }
	}
	
	/**
	 * 记录行为审核（含订单信息）
	 * @param action
	 * @param message
	 * @param actObj
	 * @param actResult
	 * @param orderNo
	 * @param orderType
	 * @param returnMessage
	 * @param accountType
	 * @throws JfEventException
	 */
	public void triggerJfAuditor2(String action,String message,String actObj,int actResult,String orderNo,String orderType,String returnMessage,String accountType){
        try {
            JfAuditorEvent event = new JfAuditorEvent();
            event.setAction(action);
            event.setMessage(message);
            event.setActObj(actObj);
            event.setActResult(actResult);
            
            event.setOrderNo(orderNo);
            event.setOrderType(orderType);
            event.setReturnMessage(returnMessage);
            
            event.setAccountType(accountType);
            this.trigger(event);
        } catch (Exception e) {
        		JfLog.error(LOG,"triggerJfAuditor2 failed:"+e.getMessage(),e);
        }
    }
	public void triggerJfSynchronizedOpenAccountResult(String action,String message,String buId,String buidSecrate,
			String accountType){
        try {
            SynchronizedOpenAccountEvent event = new
            		SynchronizedOpenAccountEvent(action, message, buId, buidSecrate, accountType);
            this.trigger(event);
        } catch (JfEventException e) {
        		JfLog.error(LOG,"triggerJfSynchronizedOpenAccountResult failed:"+e.getMessage(),e);
        }
    }
	

	/**
	 * Triggle a event
	 * @param event
	 * @throws JfEventException
	 */
	public void trigger(JfPayEvent event) throws JfEventException{
		try {
		    //检查一下线程的状态
		    this.start();
			if(event!=null){
				eventQueue.add(event);
			}
			JfLog.debug(LOG,"Trigger a JfPayEvent !");
		} catch (Exception e) {
			JfLog.error(LOG,"Event triggle failed:"+e.getMessage(),e);
			throw new JfEventException(e);
		}
	}

}

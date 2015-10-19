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
 * @Title: SynchronizedOpenEntAccountEvent.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-6-17 下午5:21:13
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class SynchronizedOpenAccountEvent implements JfPayEvent {
	
	private String action;
    private String message;
    private String buId;
    private String buidSecrate;
    private String accountType;
    
	/**
	 * @param action
	 * @param message
	 * @param buId
	 * @param buidSecrate
	 * @param accountType
	 */
	public SynchronizedOpenAccountEvent(String action, String message,
			String buId, String buidSecrate, String accountType) {
		super();
		this.action = action;
		this.message = message;
		this.buId = buId;
		this.buidSecrate = buidSecrate;
		this.accountType = accountType;
	}

	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return Returns the buId.
	 */
	public String getBuId() {
		return buId;
	}

	/**
	 * @param buId The buId to set.
	 */
	public void setBuId(String buId) {
		this.buId = buId;
	}

	/**
	 * @return Returns the buidSecrate.
	 */
	public String getBuidSecrate() {
		return buidSecrate;
	}

	/**
	 * @param buidSecrate The buidSecrate to set.
	 */
	public void setBuidSecrate(String buidSecrate) {
		this.buidSecrate = buidSecrate;
	}

	/**
	 * @return Returns the accountType.
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType The accountType to set.
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/* (non-Javadoc)
	 * @see com.csi.jf.pay.common.event.JfPayEvent#handle()
	 */
	@Override
	public void handle() throws JfEventException {
		//JfSdk.synchronizedOpenAccountResult(action,message,buId,buidSecrate, accountType);
	}

}

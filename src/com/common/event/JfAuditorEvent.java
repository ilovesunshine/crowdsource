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

import com.common.exception.JfEventException;


/**
 * @Title: JfAuditorEvent.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-5-21 下午12:22:07
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfAuditorEvent implements JfPayEvent {

    private static final Logger LOG = LoggerFactory.getLogger(JfAuditorEvent.class);
    
    private String action;
    private String message;
    private String actObj;
    private int actResult;
    private String orderNo;
    private String orderType;
    private String returnMessage;
    private String accountType;
    
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getActObj() {
        return actObj;
    }

    public void setActObj(String actObj) {
        this.actObj = actObj;
    }

    public int getActResult() {
        return actResult;
    }

    public void setActResult(int actResult) {
        this.actResult = actResult;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public void handle() throws JfEventException {
        // TODO Auto-generated method stub
        
    }

}

/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.exception;


import com.common.constants.JfApiConstants;



/**
 * @Title: JfInsufficientBalanceException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-22 下午3:53:25
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfInsufficientBalanceException extends JfPayApiException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1603708886971822460L;

    /**
     * 
     */
    public JfInsufficientBalanceException() {
        super();
    }
    
    @Override
    public int getErrorCode() {
        return JfApiConstants.RC_BALANCE;
    }

    @Override
    public String getErrorName() {
        return JfApiConstants.RC_BALANCE_NAME;
    }
    /**
     * @param message
     * @param cause
     */
    public JfInsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public JfInsufficientBalanceException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfInsufficientBalanceException(Throwable cause) {
        super(cause);
    }

}

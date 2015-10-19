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
 * @Title: JfIlleageParametersException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-22 下午4:15:50
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfIlleageParametersException extends JfPayApiException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1145489854784701936L;

    /**
     * 
     */
    public JfIlleageParametersException() {
        super();
    }
    
    public int getErrorCode(){
        return JfApiConstants.RC_BADREQUEST;
    }
    
    public String getErrorName(){
        return JfApiConstants.RC_BADREQUEST_NAME;
    }

    /**
     * @param message
     * @param cause
     */
    public JfIlleageParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public JfIlleageParametersException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfIlleageParametersException(Throwable cause) {
        super(cause);
    }

    /**
     * @param buId
     * @param buName
     * @param orderNo
     * @param orderNo
     * @param message
     */
   /* public JfIlleageParametersException(String orderNo,Long payerId,String payer,Long payeeId,String payee,BigDecimal amount,String message) {
        super(orderNo, payerId,payer, payeeId,payee,amount,message);
    }*/
}

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
 * @Title: JfDataSignatureException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-22 下午4:33:38
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfDataSignatureException extends JfPayApiException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -774308896860342455L;

    /**
     * 
     */
    public JfDataSignatureException() {
        super();
    }
    
    @Override
    public int getErrorCode() {
        return JfApiConstants.RC_BADSIGN;
    }

    @Override
    public String getErrorName() {
        return JfApiConstants.RC_BADSIGN_NAME;
    }

    /**
     * @param message
     * @param cause
     */
    public JfDataSignatureException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public JfDataSignatureException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfDataSignatureException(Throwable cause) {
        super(cause);
    }

    /**
     * @param buId
     * @param buName
     * @param orderNo
     * @param orderNo
     * @param message
     */
   /* public JfDataSignatureException(String orderNo,Long payerId,String payer,Long payeeId,String payee,BigDecimal amount,String message) {
        super(orderNo, payerId,payer, payeeId,payee,amount,message);
    }*/
}

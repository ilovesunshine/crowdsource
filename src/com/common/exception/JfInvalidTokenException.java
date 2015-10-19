/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.exception;

import java.math.BigDecimal;


/**
 * @Title: JfInvalidTokenException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-22 下午4:42:21
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfInvalidTokenException extends JfOrderException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5566661501578555466L;

    /**
     * 
     */
    public JfInvalidTokenException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public JfInvalidTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public JfInvalidTokenException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfInvalidTokenException(Throwable cause) {
        super(cause);
    }

    /**
     * @param buId
     * @param buName
     * @param orderNo
     * @param orderNo
     * @param message
     */
    public JfInvalidTokenException(String orderNo,Long payerId,String payer,Long payeeId,String payee,BigDecimal amount,String message) {
        super(orderNo, payerId,payer, payeeId,payee,amount,message);
    }
}

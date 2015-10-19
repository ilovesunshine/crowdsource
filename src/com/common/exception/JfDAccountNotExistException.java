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
 * @Title: JfDAccountNotExistException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-4 下午4:14:44
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfDAccountNotExistException extends JfOrderException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8836646052923570711L;

    /**
     * 
     */
    public JfDAccountNotExistException() {
    }

    /**
     * @param message
     */
    public JfDAccountNotExistException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfDAccountNotExistException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public JfDAccountNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * @param buId
     * @param buName
     * @param orderNo
     * @param orderNo
     * @param message
     */
    public JfDAccountNotExistException(String orderNo,Long payerId,String payer,Long payeeId,String payee,BigDecimal amount,String message) {
        super(orderNo, payerId,payer, payeeId,payee,amount,message);
    }

}

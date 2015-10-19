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
 * 
 * @Title: PayAccountDuplicatedException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-26 下午5:39:16
 * @author FengHaiBing
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class PayAccountDuplicatedException extends JfOrderException {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4280299174483394005L;

    /**
     * 
     */
    public PayAccountDuplicatedException() {
        super("账户已存在");
    }

    /**
     * @param message
     * @param cause
     */
    public PayAccountDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public PayAccountDuplicatedException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public PayAccountDuplicatedException(Throwable cause) {
        super(cause);
    }

    /**
     * @param buId
     * @param JfOrderException
     * @param orderNo
     * @param orderNo
     * @param message
     */
    public PayAccountDuplicatedException(String orderNo,Long payerId,String payer,Long payeeId,String payee,BigDecimal amount,String message) {
        super(orderNo, payerId,payer, payeeId,payee,amount,message);
    }
}

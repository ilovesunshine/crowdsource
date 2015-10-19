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
 * @Title: JfNoBindedBankCardsException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-22 下午4:40:41
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfNoBindedBankCardsException extends JfOrderException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1460443450327107000L;

    /**
     * 
     */
    public JfNoBindedBankCardsException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public JfNoBindedBankCardsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public JfNoBindedBankCardsException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfNoBindedBankCardsException(Throwable cause) {
        super(cause);
    }

    /**
     * @param buId
     * @param buName
     * @param orderNo
     * @param orderNo
     * @param message
     */
    public JfNoBindedBankCardsException(String orderNo,Long payerId,String payer,Long payeeId,String payee,BigDecimal amount,String message) {
        super(orderNo, payerId,payer, payeeId,payee,amount,message);
    }
}

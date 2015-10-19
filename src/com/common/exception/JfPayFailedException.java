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
 * @Title: JfBillPayFailedException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-25 下午3:31:49
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfPayFailedException extends JfOrderException {

    /**
     * 
     */
    private static final long serialVersionUID = 1364804874677259836L;

    private String resultcode;
    private String errtext;
    /**
     * 
     */
    public JfPayFailedException() {
    }
    
    public JfPayFailedException(String resultcode,String errtext,String message) {
        super(message);
        this.resultcode = resultcode;
        this.errtext = errtext;
    }

    /**
     * @param message
     */
    public JfPayFailedException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfPayFailedException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public JfPayFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getResultcode() {
        return resultcode;
    }

    public String getErrtext() {
        return errtext;
    }
    
    public JfPayFailedException(String orderNo,Long payerId,String payer,Long payeeId,String payee,BigDecimal amount,String message) {
        super(orderNo, payerId,payer, payeeId,payee,amount,message);
    }
    
}

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
 * @Title: JfOrderException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-24 下午5:12:27
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfOrderException  extends RuntimeException{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1378260247887596954L;
    
    private String orderNo;
    private Long payerId;
    private String payer;
    private Long payeeId;
    private String payee;
    private BigDecimal amount;

    /**
     * JfOrderException
     */
    public JfOrderException() {
    }

    public JfOrderException(String orderNo,Long payerId,String payer,Long payeeId,String payee,BigDecimal amount,String message) {
        super(message);
        this.orderNo = orderNo;
        this.payerId = payerId;
        this.payer = payer;
        this.payeeId = payeeId;
        this.payee = payee;
        this.amount = amount;
    }
    
    /**
     * @param message
     */
    public JfOrderException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfOrderException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public JfOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public String getOrderNo() {
        return orderNo;
    }

    public Long getPayerId() {
        return payerId;
    }

    public String getPayer() {
        return payer;
    }

    public Long getPayeeId() {
        return payeeId;
    }

    public String getPayee() {
        return payee;
    }

    public BigDecimal getAmount() {
        return amount;
    }

}

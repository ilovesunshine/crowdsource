/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.exception;

/**
 * @Title: PayNumException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-7-8 上午11:17:53
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class PayNumException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6091482936951705287L;

    /**
     * 
     */
    public PayNumException() {
    }

    /**
     * @param message
     */
    public PayNumException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public PayNumException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public PayNumException(String message, Throwable cause) {
        super(message, cause);
    }

}

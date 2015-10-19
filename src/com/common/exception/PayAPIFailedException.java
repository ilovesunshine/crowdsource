/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.exception;

/**
 * @Title: PayAPIFailedException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-28 下午3:23:53
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class PayAPIFailedException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7897215473882303957L;

    /**
     * 
     */
    public PayAPIFailedException() {
    }

    /**
     * @param message
     */
    public PayAPIFailedException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public PayAPIFailedException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public PayAPIFailedException(String message, Throwable cause) {
        super(message, cause);
    }

}

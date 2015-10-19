/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.exception;

/**
 * @Title: UnimplementedException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-11 上午10:37:48
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class UnimplementedException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 6510696544649350476L;

    /**
     * 
     */
    public UnimplementedException() {
    }

    /**
     * @param message
     */
    public UnimplementedException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public UnimplementedException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public UnimplementedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public UnimplementedException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

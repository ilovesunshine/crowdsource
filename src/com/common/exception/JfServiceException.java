/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.exception;

/**
 * @Title: JfServiceException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-7-8 下午12:43:00
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfServiceException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2470294325944146468L;

    /**
     * 
     */
    public JfServiceException() {
    }

    /**
     * @param message
     */
    public JfServiceException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public JfServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}

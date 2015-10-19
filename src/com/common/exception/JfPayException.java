/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.exception;

/**
 * @Title: JfPayException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-6-24 下午12:54:24
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfPayException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8985751365364973037L;

    /**
     * 
     */
    public JfPayException() {
        super();
    }

    /**
     * @param arg0
     * @param arg1
     */
    public JfPayException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0
     */
    public JfPayException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public JfPayException(Throwable arg0) {
        super(arg0);
    }

}

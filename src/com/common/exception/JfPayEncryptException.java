/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.exception;

/**
 * @Title: JfPayEncryptException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-7-8 下午5:06:53
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfPayEncryptException extends JfPayException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6090609912766953918L;

    /**
     * 
     */
    public JfPayEncryptException() {
    }

    /**
     * @param arg0
     * @param arg1
     */
    public JfPayEncryptException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0
     */
    public JfPayEncryptException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public JfPayEncryptException(Throwable arg0) {
        super(arg0);
    }

}

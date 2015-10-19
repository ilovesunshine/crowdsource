/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.exception;

/**
 * @Title: JfPaySecurityException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-7-8 下午5:17:24
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfPaySecurityException extends JfPayException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 699113285170460609L;

    /**
     * 
     */
    public JfPaySecurityException() {
    }

    /**
     * @param arg0
     * @param arg1
     */
    public JfPaySecurityException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0
     */
    public JfPaySecurityException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public JfPaySecurityException(Throwable arg0) {
        super(arg0);
    }

}

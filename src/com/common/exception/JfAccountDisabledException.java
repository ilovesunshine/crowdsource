/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.exception;

import com.common.constants.JfApiConstants;

/**
 * @Title: JfAccountDisabledException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-22 下午3:49:14
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfAccountDisabledException extends JfPayApiException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 50966460413628011L;

    /**
     * 
     */
    public JfAccountDisabledException() {
        super();
    }

    @Override
    public int getErrorCode() {
        return JfApiConstants.RC_LOCKED;
    }

    @Override
    public String getErrorName() {
        return JfApiConstants.RC_LOCKED_NAME;
    }

    /**
     * @param message
     * @param cause
     */
    public JfAccountDisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public JfAccountDisabledException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfAccountDisabledException(Throwable cause) {
        super(cause);
    }

}

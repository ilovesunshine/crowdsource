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
 * @Title: JfNotRealTimePayTypeException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-3-9 下午4:12:12
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfNotRealTimePayTypeException extends JfPayApiException {

	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1322389163361608450L;

    /**
	 * 
	 */
	public JfNotRealTimePayTypeException() {
	}

    public int getErrorCode() {
        return JfApiConstants.RC_NOT_REALTIME_PAYTYPE;
    }

    public String getErrorName() {
        return JfApiConstants.RC_NOT_REALTIME_PAYTYPE_NAME;
    }

	/**
	 * @param message
	 */
	public JfNotRealTimePayTypeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public JfNotRealTimePayTypeException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JfNotRealTimePayTypeException(String message, Throwable cause) {
		super(message, cause);
	}

}

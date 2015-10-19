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
 * @Title: JfBillUnpayedException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-22 下午3:50:47
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfBillUnpayedException extends JfPayApiException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1481802807879481985L;

    /**
     * 
     */
    public JfBillUnpayedException() {
        super();
    }
    
    @Override
    public int getErrorCode() {
        return JfApiConstants.RC_DEBT;
    }

    @Override
    public String getErrorName() {
        return JfApiConstants.RC_DEBT_NAME;
    }

    /**
     * @param message
     * @param cause
     */
    public JfBillUnpayedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public JfBillUnpayedException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfBillUnpayedException(Throwable cause) {
        super(cause);
    }
}

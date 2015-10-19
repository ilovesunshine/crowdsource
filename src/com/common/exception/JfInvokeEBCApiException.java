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
 * @Title: JfInvokeEBCApiException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-6-1 下午4:20:10
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfInvokeEBCApiException extends JfPayApiException {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4280299174483394006L;

    /**
     * 
     */
    public JfInvokeEBCApiException() {
        super();
    }
    
    @Override
    public int getErrorCode() {
        return JfApiConstants.RC_INVOKE_EBC_ERROR;
    }

    @Override
    public String getErrorName() {
        return JfApiConstants.RC_INVOKE_EBC_ERROR_NAME;
    }

    /**
     * @param message
     * @param cause
     */
    public JfInvokeEBCApiException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public JfInvokeEBCApiException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfInvokeEBCApiException(Throwable cause) {
        super(cause);
    }

}

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
 * @Title: JfAccountAbnormalException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-29 下午2:47:30
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfAccountAbnormalException extends JfPayApiException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4766075529432883739L;

    /**
     * 
     */
    public JfAccountAbnormalException() {
    }

    @Override
    public int getErrorCode() {
        return JfApiConstants.RC_ABNORMAL;
    }

    @Override
    public String getErrorName() {
        return JfApiConstants.RC_ABNORMAL_NAME;
    }
    /**
     * @param message
     */
    public JfAccountAbnormalException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfAccountAbnormalException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public JfAccountAbnormalException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

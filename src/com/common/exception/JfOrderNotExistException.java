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
 * @Title: JfOrderNotExistException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-22 下午4:30:46
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfOrderNotExistException extends JfPayApiException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5759398648828438698L;
    
    /**
     * 
     */
    public JfOrderNotExistException() {
        super();
    }
    
    public int getErrorCode(){
        return JfApiConstants.RC_NOORDER;
    }
    
    public String getErrorName(){
        return JfApiConstants.RC_NOORDER_NAME;
    }

    /**
     * @param message
     * @param cause
     */
    public JfOrderNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public JfOrderNotExistException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfOrderNotExistException(Throwable cause) {
        super(cause);
    }

}

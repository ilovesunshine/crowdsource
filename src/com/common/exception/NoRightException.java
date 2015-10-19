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
 * @Title: NoRightException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-8-29 下午6:37:34
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class NoRightException extends JfPayApiException {
 
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2920430041004914576L;
    
    public NoRightException() {
        super();
    }
    
    public int getErrorCode(){
        return JfApiConstants.RC_NO_RIGHT;
    }
    
    public String getErrorName(){
        return JfApiConstants.RC_NO_RIGHT_NAME;
    }

    public NoRightException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRightException(String message) {
        super(message);
    }

    public NoRightException(Throwable cause) {
        super(cause);
    }

}

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
 * @Title: JfPayApiException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-7-8 下午4:28:34
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfPayApiException extends JfPayException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7422606134352957808L;
    
    
    public int getErrorCode(){
        return JfApiConstants.RC_ERROR;
    }
    
    public String getErrorName(){
        return JfApiConstants.RC_ERROR_NAME;
    }

    /**
     * 
     */
    public JfPayApiException() {
    }

    /**
     * @param arg0
     * @param arg1
     */
    public JfPayApiException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0
     */
    public JfPayApiException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     */
    public JfPayApiException(Throwable arg0) {
        super(arg0);
    }

}

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
 * @Title: JfAccountNotExistException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-22 下午4:01:36
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfAccountNotExistException extends JfPayApiException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4294526324022460044L;

    /**
     * 
     */
    public JfAccountNotExistException() {
        super();
    }

    @Override
    public int getErrorCode() {
        return JfApiConstants.RC_NOTEXIST;
    }

    @Override
    public String getErrorName() {
        return JfApiConstants.RC_NOTEXIST_NAME;
    }
    
    /**
     * @param message
     * @param cause
     */
    public JfAccountNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public JfAccountNotExistException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfAccountNotExistException(Throwable cause) {
        super(cause);
    }

}

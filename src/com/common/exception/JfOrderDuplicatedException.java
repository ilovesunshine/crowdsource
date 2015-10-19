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
 * @Title: JfOrderDuplicatedException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-22 下午3:46:54
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfOrderDuplicatedException extends JfPayApiException {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4280299174483394005L;

    /**
     * 
     */
    public JfOrderDuplicatedException() {
        super();
    }
    
    public int getErrorCode(){
        return JfApiConstants.RC_DUPLICATE_COMMIT;
    }
    
    public String getErrorName(){
        return JfApiConstants.RC_DUPLICATE_COMMIT_NAME;
    }


    /**
     * @param message
     * @param cause
     */
    public JfOrderDuplicatedException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public JfOrderDuplicatedException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfOrderDuplicatedException(Throwable cause) {
        super(cause);
    }

}

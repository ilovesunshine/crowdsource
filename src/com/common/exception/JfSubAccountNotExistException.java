package com.common.exception;

import com.common.constants.JfApiConstants;

/**
 * @Title: JfSubAccountNotExistException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-5-14 下午2:27:22
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfSubAccountNotExistException extends JfPayApiException {

	private static final long serialVersionUID = 10000000000002L;
	/**
     * 
     */
    public JfSubAccountNotExistException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public JfSubAccountNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public JfSubAccountNotExistException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfSubAccountNotExistException(Throwable cause) {
        super(cause);
    }

    @Override
    public int getErrorCode() {
        return JfApiConstants.RC_NOTEXIST;
    }

    @Override
    public String getErrorName() {
        return JfApiConstants.RC_NOTEXIST_SUB_NAME;
    }
}

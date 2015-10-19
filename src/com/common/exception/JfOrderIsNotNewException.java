package com.common.exception;

import com.common.constants.JfApiConstants;

/**
 * @Title: JfOrderIsNotNewExistException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-5-12 下午5:06:49
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfOrderIsNotNewException extends JfPayApiException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 10000000111L;
	/**
     * 
     */
    public JfOrderIsNotNewException() {
        super();
    }

    public int getErrorCode(){
        return JfApiConstants.RC_ORDER_NOT_NEW;
    }
    
    public String getErrorName(){
        return JfApiConstants.RC_ORDER_NOT_NEW_NAME;
    }

    /**
     * @param message
     * @param cause
     */
    public JfOrderIsNotNewException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public JfOrderIsNotNewException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfOrderIsNotNewException(Throwable cause) {
        super(cause);
    }

}

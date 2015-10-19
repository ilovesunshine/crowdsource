package com.common.exception;

import java.math.BigDecimal;


/**
 * @Title: JfBalanceNotEqualException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-28 下午1:38:01
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfBalanceNotEqualException extends JfOrderException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 163193624081352462L;

	
    public JfBalanceNotEqualException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public JfBalanceNotEqualException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public JfBalanceNotEqualException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfBalanceNotEqualException(Throwable cause) {
        super(cause);
    }

    /**
     * @param buId
     * @param buName
     * @param orderNo
     * @param orderNo
     * @param message
     */
    public JfBalanceNotEqualException(String orderNo,Long payerId,String payer,Long payeeId,String payee,BigDecimal amount,String message) {
        super(orderNo, payerId,payer, payeeId,payee,amount,message);
    }
}

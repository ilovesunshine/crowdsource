/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.exception;

/**
 * @Title: AccountSecureException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-05 15:34:18
 * @author FengHaiBing
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class AccountException extends RuntimeException {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4280299174483394005L;

    public AccountException() {
        super();
    }

    public AccountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountException(String message) {
        super(message);
    }

    public AccountException(Throwable cause) {
        super(cause);
    }

}

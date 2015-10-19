/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.exception;


/**
 * @Title: NoParamConfiguredException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-16 下午3:19:28
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class NoParamConfiguredException extends JfPayException {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7374718966879764178L;

    public NoParamConfiguredException(String reason){
        super(reason);
    }
    
}

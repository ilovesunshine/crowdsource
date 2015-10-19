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
 * @Title: JfOrdeErrorException.java
 * @Description: <br>订单信息错误异常
 *               <br>1、该订单不属于发包方
 *               <br>2、接口处理订单时，类型错误（如：实时支付的新订单不能走逻辑冻结、支付不能走老接口）
 * @Company: crowdsource
 * @Created on 2015-6-1 上午11:08:15
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfOrdeErrorException extends JfPayApiException {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4280299174483394006L;

    /**
     * 
     */
    public JfOrdeErrorException() {
        super();
    }
    
    @Override
    public int getErrorCode() {
        return JfApiConstants.RC_ORDER_INFO_ERROR;
    }

    @Override
    public String getErrorName() {
        return JfApiConstants.RC_ORDER_INFO_ERROR_NAME;
    }

    /**
     * @param message
     * @param cause
     */
    public JfOrdeErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public JfOrdeErrorException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public JfOrdeErrorException(Throwable cause) {
        super(cause);
    }

}

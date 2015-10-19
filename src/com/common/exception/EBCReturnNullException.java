/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.exception;

/**
 * @Title: EBCReturnNullException.java
 * @Description: <br>
 *                  银盈通没有返回值时抛出异常
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-16 上午10:56:53
 * @author zhangchengjun
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class EBCReturnNullException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public EBCReturnNullException(){
        super("第三方支付接口返回值为空");
    }
    
    public EBCReturnNullException(String reason){
        super(reason);
    }
    
    public EBCReturnNullException(String reason,Throwable e){
        super(reason,e);
    }
}

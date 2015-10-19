/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.exception;

/**
 * @Title: AppidNullException.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-16 上午11:09:44
 * @author zhangchengjun
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class AppidNullException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -8424247805316693839L;

    public AppidNullException(){
        super("app_id为空，发送报文失败！");
    }
    
    public AppidNullException(String reason){
        super(reason);
    }
    
    public AppidNullException(String reason,Throwable e){
        super(reason,e);
    }
}

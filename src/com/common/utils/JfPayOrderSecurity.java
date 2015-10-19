/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

/**
 * @Title: JfPayOrderSecurity.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-13 下午4:18:48
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfPayOrderSecurity extends JfPaySecurity{
    
    private static JfPaySecurity instance;
    
    public static JfPaySecurity getInstance(){
        if(instance==null){
            instance = JfPaySecurity.getInstance(JfPaySecurity.T_ORDER,true);
        }
        return instance;
    }
  
}

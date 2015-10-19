/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;


/**
 * @Title: JfPayBonusSecurity.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-13 下午4:29:55
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfPayBonusSecurity {
    
    private static JfPaySecurity instance;
    
    private JfPayBonusSecurity(){
    }

    public static JfPaySecurity getInstance(){
        if(instance==null){
            instance = JfPaySecurity.getInstance(JfPaySecurity.T_BUNOS,true);
        }
        return instance;
    }
  
}

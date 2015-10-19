/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.config;

import com.common.utils.StringUtils;

/**
 * @Title: JfTest.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-7-29 下午5:02:55
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfTest {
    
    private String host;
    private static JfTest jfTest = null;
    private static final Object LOCK = new Object();
    
    private JfTest(){
    }
    
    public static String getTestHost(){
        synchronized (LOCK) {
            if (jfTest == null) {
                jfTest = new JfTest();
            }
        }
        if(StringUtils.isNullStr(jfTest.host)){
            return getLocalServerURL();
        }else{
            return jfTest.host;
        }
    }
    
    public void setHost(String host){
        this.host = host;
    }

    public static String getTestServerURL(){
        return "https://test.jointforce.com/jfpay";
    }
    
    public static String getDevServerURL(){
        return "https://dev.jointforce.com/jfpay";
    }
    
    public static String getRcServerURL(){
        return "https://rc.jointforce.com/jfpay";
    }
    
    public static String getLocalServerURL(){
        return "http://localhost:8080/jfpay";
    }
    
    public static final int I_QUERY_ACCOUNTSTATE = 1;
    public static final int I_CHECK_ORDERISNEW = 2;
    public static final int I_CHECK_PAYABLEFORORDER = 3;
    public static final int I_NOTIFY_ORDERCREATED = 4;
    public static final int I_REALTIME_ORDERCREATEDFORWARD = 5;
    public static final int I_REALTIME_ORDERCREATED = 6;
    public static final int I_NOTIFY_AGREETOPAY = 7;
    public static final int I_REALTIME_FORWARD = 8;
    public static final int I_REALTIME_PAYING = 9;
    public static final int I_REALTIME_PAYINGNEW = 10;
    public static final int I_OVERTIME_FORCEPAY = 11;
    public static final int I_NOTIFY_ORDERCANCELED = 12;
    public static final int I_NOTIFY_ORDERFINISHED = 13;
    
    public static final int I_GET_PERSONALACCOUNTINFO = 14;
    public static final int I_QUERY_BONUSACCOUNTBALANCE = 15;
    public static final int I_QUERY_BONUSSUM = 16;
    public static final int I_NOTIFY_BONUSTOPAY = 17;
    public static final int I_NOTIFY_BONUSFROZENFUND = 18;
    public static final int I_NOTIFY_BONUSCLOSE = 19;
    
}

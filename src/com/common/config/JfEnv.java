/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.config;

import java.math.BigDecimal;

import com.common.utils.JfDecimal;
import com.common.utils.StringUtils;


/**
 * @Title: JfEnv.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-16 上午10:25:39
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfEnv {
    
    private static String SWITCH_ENABLE = "enable";
    
    private JfEnv(){
    }
    
    /**
     * 测试模式启用开关
     * @return
     */
    public static boolean isTestEnable(){
        String curEnv = JFPayConfigFactory.getJfSettings().getEvn();
        if(!StringUtils.isNullStr(curEnv)&&"test".equalsIgnoreCase(curEnv)){
            return true;
        }
        return false;
    }
    
    public static boolean isProduct(){
        String curEnv = JFPayConfigFactory.getJfSettings().getEvn();
        if("Product".equalsIgnoreCase(curEnv)){
            return true;
        }
        return false;
    }
    
    public static final String LOGIN_USER = "jf_pay_user";
    
    public static String getSystemProperty(){
        return JFPayConfigFactory.getValue("system.paypass");
    }
    
    /**
     * 提现按钮开关
     * @return
     */
    public static boolean canWithDraw(){
        if(SWITCH_ENABLE.equals(JFPayConfigFactory.getValue("pay.switch.withdraw"))){
        	return true;
        }
         return false;
    }
    
    /**
     * 充值按钮开关
     * @return
     */
    public static boolean canRecharge(){
        if(SWITCH_ENABLE.equals(JFPayConfigFactory.getValue("pay.switch.recharge"))){
        	return true;
        }
         return false;
    }
    
    /**
     * 充值按钮开关
     * @return
     */
    public static boolean canBindCards(){
        if(SWITCH_ENABLE.equals(JFPayConfigFactory.getValue("pay.switch.bindcards"))){
        	return true;
        }
         return false;
    }
    
    /**
     * 获取红包过去时间
     * @return
     */
    public static String getBonusExpireDate(){
        return JFPayConfigFactory.getValue("pay.bonus.expiredate");
    }
    
    public static boolean checkFeeRate(BigDecimal feeRate){
        boolean flag = JfDecimal.compareLess(feeRate, JfConstants.JF_PAY_LOWEST_FEE_RATE) ||
                JfDecimal.compareGreater(feeRate, JfConstants.JF_PAY_HIGHEST_FEE_RATE);
        return flag;
    }

}

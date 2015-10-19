/**
 * $Id$
 *
 * Copyright (c) 2013 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: JfRight.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-11-28 下午4:06:57
 * @author Administrator
 * @version  
 * @since JDK 1.6
 */
public class JfRight {
    
    /**
     * 经营负责人
     */
    public static final String ROLE_OWNER = "OWNER";
    
    /**
     * 经营助理角色
     */
    public static final String ROLE_BUM = "BUM";

    /**
     * PM 角色
     */
    public static final String ROLE_PM = "PM";
    
    /**
     * TL 角色
     */
    public static final String ROLE_TL = "TL";
    
    /**
     * DEV 角色
     */
    public static final String ROLE_DEV = "DEV";
    
    /**
     * OPT角色
     */
    public static final String ROLE_OPT = "OPT";
    
    /**
     * SYS 角色
     */
    public static final String ROLE_SYS = "SYS";
    
    /**
     * GUEST 角色
     */
    public static final String ROLE_GUEST = "GUEST";
    
    /**
     */
    public static final int ACCOUNT_TYPE_GUEST = -1;
    /**
     */
    public static final int ACCOUNT_TYPE_SYS = 0;
    public static final int ACCOUNT_TYPE_OPT = 1;
    public static final int ACCOUNT_TYPE_BUM = 2;
    public static final int ACCOUNT_TYPE_PM = 3;
    public static final int ACCOUNT_TYPE_TL = 4;
    public static final int ACCOUNT_TYPE_DEV = 5;
    
    private static final Map<String,Integer> ACCOUT_TYPES = new HashMap<String,Integer>();
    
    private JfRight(){
    }
    
    public static String getRoleName(String  role){
        if(ROLE_BUM.equals(role)) {
            return "部门经理";
        } else if(ROLE_DEV.equals(role)) {
            return "项目成员";
        } else if(ROLE_PM.equals(role)) {
            return "项目经理";
        } else if(ROLE_TL.equals(role)) {
            return "项目组长";
        } else if(ROLE_OPT.equals(role)) {
            return "运营管理员";
        } else if(ROLE_SYS.equals(role)) {
            return "系统管理员";
        } else {
            return "";
        }
    }
    
    /**
     *  ACCOUT_TYPES.put(ROLE_HRM, ACCOUNT_TYPE_HRM);
        ACCOUT_TYPES.put(ROLE_COE, ACCOUNT_TYPE_COE);
        ACCOUT_TYPES.put(ROLE_SDUM, ACCOUNT_TYPE_SDUM);
        ACCOUT_TYPES.put(ROLE_GDUM, ACCOUNT_TYPE_GDUM);
     */ 
     static {
        ACCOUT_TYPES.put(ROLE_GUEST, ACCOUNT_TYPE_GUEST);
        ACCOUT_TYPES.put(ROLE_SYS, ACCOUNT_TYPE_SYS);
        ACCOUT_TYPES.put(ROLE_OPT, ACCOUNT_TYPE_OPT);
        ACCOUT_TYPES.put(ROLE_BUM, ACCOUNT_TYPE_BUM);
        ACCOUT_TYPES.put(ROLE_PM, ACCOUNT_TYPE_PM);
        ACCOUT_TYPES.put(ROLE_TL, ACCOUNT_TYPE_TL);
        ACCOUT_TYPES.put(ROLE_DEV, ACCOUNT_TYPE_DEV);
    }
    
    /**
     * 返回账号类型
     * @param role
     * @return
     */
    public static final Integer getAccoutType(String role){
        return ACCOUT_TYPES.get(role);
    }
    
}

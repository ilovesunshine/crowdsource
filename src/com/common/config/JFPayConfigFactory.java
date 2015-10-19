/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project 
 *
 */
package com.common.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.constants.JfSettings;
import com.common.service.PayConfigService;
import com.common.utils.BeanFactory;
import com.common.utils.JfLog;
import com.common.vo.PayConfig;


/**
 * @Title: AlmConfigFactory.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-24 下午4:18:25
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JFPayConfigFactory {

	private static final Logger LOG = LoggerFactory.getLogger(JFPayConfigFactory.class);

    private static JfSettings jfSettings = null;

    private static PayConfigService payConfigService;

    private static final Object LOCK = new Object();
    
    private JFPayConfigFactory() {
    }

    public static void setJfSettings(JfSettings jfSettings){
        JFPayConfigFactory.jfSettings = jfSettings;
    }
    
    public static void setPayConfigService(PayConfigService payConfigService){
        JFPayConfigFactory.payConfigService = payConfigService;
    }
    
    public static PayConfigService getPayConfigService() {
        synchronized (LOCK) {
            if (payConfigService == null) {
                payConfigService = (PayConfigService) BeanFactory.getBean("payConfigService");
            }
        }
        return payConfigService;
    }

    
    public static JfSettings getJfSettings() {
        synchronized (LOCK) {
            if (jfSettings == null) {
                jfSettings = (JfSettings) BeanFactory.getBean("jfSettings");
            }
        }
        return jfSettings;
    }
    
    public static String getValue(String key) {
        try {
            // 缓存里也没有，就从数据库里取一次
            PayConfig o = (PayConfig) getPayConfigService().findById(key);
            return o.getPvalue();
        } catch (Exception e) {
            JfLog.error(LOG,e.getMessage(),e);
        }
        return null;
    }

}

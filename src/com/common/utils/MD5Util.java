/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.config.JfConstants;

/**
 * 
 * @Title: MD5Util.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-1 下午2:33:19
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class MD5Util {
	
	private static final Logger LOG = LoggerFactory.getLogger(MD5Util.class);
    
	private MD5Util(){
	}

	/**
     * encrypt
     * @param s
     * @return
     */
    public final static String encrypt(String s) {
        if(null == s){
            return null;
        }
        
        char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};       

        try {
            byte[] btInput = s.getBytes(JfConstants.UTF8);
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            JfLog.error(LOG,e.getMessage(),e);
            return null;
        }
    }

}

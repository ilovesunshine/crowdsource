/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.config.JFPayConfigFactory;
import com.common.exception.JfPayEncryptException;
import com.csi.jointforce.common.model.AESCrypt;

/**
 * @Title: PayEncryptor.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-18 下午5:53:54
 * @author FengHaiBing
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class PayEncryptor {
    
	private static final Logger LOG = LoggerFactory.getLogger(PayEncryptor.class);
    
    private static final String ENCODING = "utf-8";
    
    /**
     * 字符串默认键值
     */
    private static String strDefaultKey = "BC2BB1776C733F38B70A5B31AC7D7DCF"; 
    
    private static String SECRET = null;
    
    private static String getSecret() throws JfPayEncryptException{
        if (SECRET == null) {
            SECRET = JFPayConfigFactory.getJfSettings().getSecretEncrypt();
            if(StringUtils.isNullStr(SECRET)){
                throw new JfPayEncryptException("SECRET not found!");
            }
        }
        return SECRET;
    }
    
    private PayEncryptor() {
        
    }
    
    /**
     * 加密
     * 
     * @param strIn
     * @return
     */
    public static String encryptWithOldArithmetic(String str) {
        String strIn = str;
        if(null == strIn) {
            return strIn;
        }
        try {
            byte[] strByte = strIn.getBytes(ENCODING);
            strIn = new String(strByte, ENCODING);
        } catch (UnsupportedEncodingException e) {
        	{
        		JfLog.error(LOG,"strIn.getBytes(" + ENCODING + ") " + strIn, e);
        	}
        }
        String strOut = null;
        try {
            strOut = AESCrypt.getInstance().crypt(strIn, strDefaultKey);
        } catch (Exception e) {
        	{
        		JfLog.error(LOG,"encrypt " + strIn, e);
        	}
        }
        
        return strOut;
    }
    
    /**
     * 加密
     * 
     * @param strIn
     * @return
     */
    public static String encrypt(String str) {
        String strIn = str;
        if(null == strIn) {
            return strIn;
        }
        try {
            byte[] strByte = strIn.getBytes(ENCODING);
            strIn = new String(strByte, ENCODING);
        } catch (UnsupportedEncodingException e) {
        	{
        		JfLog.error(LOG,"strIn.getBytes(" + ENCODING + ") " + strIn, e);
        	}
        }
        String strOut = null;
        try {
            strOut = AESCrypt.getInstance().crypt(strIn, getSecret());
        } catch (Exception e) {
        	{
        		JfLog.error(LOG,"encrypt " + strIn, e);
        	}
        }
        return strOut;
    }
    
    /**
     * 加密
     * @param key
     * @param str
     * @return
     */
    public static String encrypt(String key,String str) {
        String strIn = str;
        if (null == strIn || key == null) {
            return strIn;
        }
        try {
            byte[] strByte = strIn.getBytes(ENCODING);
            strIn = new String(strByte, ENCODING);
        } catch (UnsupportedEncodingException e) {
        	{
        		JfLog.error(LOG,"strIn.getBytes(" + ENCODING + ") " + strIn, e);
        	}
        }
        String strOut = null;
        try {
            strOut = AESCrypt.getInstance().crypt(strIn, key);
        } catch (Exception e) {
        	{
        		JfLog.error(LOG,"encrypt " + strIn, e);
        	}
        }
        return strOut;
    }
    
    /**
     * 解密
     * 
     * @param strIn
     * @return
     */
    public static String decryptWithOldArithmetic (String strIn) {
        if(null == strIn) {
            return strIn;
        }
        
        String strOut = null;
        try {
            strOut = AESCrypt.getInstance().decrypt(strIn, strDefaultKey);
        } catch (Exception e) {
        	{
        		JfLog.error(LOG,"decrypt " + strIn, e);
        	}
        }
        
        return strOut;
    }
    
    public static BigDecimal decryptBigDecimal(String d){
    	BigDecimal n = JfDecimal.ZERO;
    	try {
    		if(StringUtils.isNullStr(d)){
    			return n;
    		}
    		if(null != d){
    			String temp = decrypt(d);
    			if(StringUtils.isNullStr(temp)){
    				return n;
    			}else{
    				n = new BigDecimal(temp).setScale(2,BigDecimal.ROUND_HALF_UP);
    			}
    		}
		} catch (Exception e) {
			{
				JfLog.error(LOG,"decrypt " + String.valueOf(d)+"failed:"+e.getMessage(), e);
			}
		}
    	return n;
    }
    
    public static BigDecimal decryptBigDecimal(Object d){
    	if(null == d) {
    		return JfDecimal.ZERO;
    	}
    	
    	return decryptBigDecimal(d.toString());
    }
    
    
    public static String encryptBigDecimal(BigDecimal dig){
        BigDecimal d = dig;
        if(StringUtils.isNullStr(d)) {
            d = BigDecimal.ZERO.setScale(2,BigDecimal.ROUND_HALF_UP);
        } else {
            d = d.setScale(2,BigDecimal.ROUND_HALF_UP);
        }
    	return encrypt(String.valueOf(d));
    }
    
    /**
     * 解密
     * 
     * @param strIn
     * @return
     */
    public static String decrypt(String strIn) {
        if(null == strIn) {
            return strIn;
        }
        
        String strOut = null;
        try {
            strOut = AESCrypt.getInstance().decrypt(strIn, getSecret());
        } catch (Exception e) {
        	{
        		JfLog.error(LOG,"decrypt " + strIn, e);
        	}
        }
        return strOut;
    }
    
    public static String decrypt(String key,String strIn) {
        if(null == strIn) {
            return strIn;
        }
        
        String strOut = null;
        try {
            strOut = AESCrypt.getInstance().decrypt(strIn, key);
        } catch (Exception e) {
        	{
        		JfLog.error(LOG,"decrypt " + strIn, e);
        	}
        }
        return strOut;
    }
    
}

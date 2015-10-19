/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import java.net.URLEncoder;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinasofti.ro.bizframework.core.libs.Base64;
import com.common.config.JFPayConfigFactory;
import com.common.exception.JfDataSignatureException;
import com.common.exception.JfPaySecurityException;
import com.csi.jointforce.common.model.AESCrypt;
import com.csi.jointforce.common.util.MD5Helper;

/**
 * @Title: JfPaySecurity.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-13 下午2:38:20
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfPaySecurity {
    
	private static final Logger LOG = LoggerFactory.getLogger(JfPaySecurity.class);
    
    private static final String ENCODING = "utf-8";
    
    private String secret = null;
    
    private static JfPaySecurity instance;
    
    public static final int T_ORDER = 1;
    public static final int T_BUNOS = 2;
    
    private static final Object LOCK = new Object();
    
    /**
     * 接口响应数据
     */
    private static final String DATA = "data";

    /**
     * 接口响应数据签名
     */
    private static final String DATASIGN = "datasign";
    
    /**
     * return instance
     * @param keyType
     * @return
     */
    public static JfPaySecurity getInstance(int keyType) {
        synchronized (LOCK) {
            if (instance == null) {
                instance = new JfPaySecurity();
                if (T_ORDER==keyType) {
                    instance.setSecret(JFPayConfigFactory.getJfSettings().getSecretOrder());
                } else if(T_BUNOS==keyType){
                    instance.setSecret(JFPayConfigFactory.getJfSettings().getSecretBonus());
                } else {
                    return null;
                }
            }
        }
        return instance;
    }
    
    /**
     * return instance
     * @param keyType
     * @param newInstance
     * @return
     */
    public static JfPaySecurity getInstance(int keyType,boolean newInstance) {
        if(newInstance){
            JfPaySecurity ins = new JfPaySecurity();
            if (T_ORDER==keyType) {
                ins.setSecret(JFPayConfigFactory.getJfSettings().getSecretOrder());
            } else if(T_BUNOS==keyType){
                ins.setSecret(JFPayConfigFactory.getJfSettings().getSecretBonus());
            } else {
                return null;
            }
            return ins;
        }else{
            return getInstance(keyType);
        }
    }
    
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    /**
     * 数据签名
     * @param data
     * @return
     * @throws JfPaySecurityException
     */
    public String signAPI(String data) throws JfPaySecurityException{
        try {
            AESCrypt aes = AESCrypt.getInstance();
            String dataSecret = getSecret();
            if (StringUtils.isNullStr(data)) {
                throw new JfPaySecurityException("数据为空，无法签名！");
            }
            if (StringUtils.isNullStr(dataSecret)) {
                throw new JfPaySecurityException("密钥为空，无法签名！");
            }
            String input = new String(Base64.encode(data.getBytes(ENCODING)),ENCODING);
            return URLEncoder.encode(aes.crypt(input, MD5Helper.encrypt(dataSecret)),ENCODING);
        } catch (Exception e) {
            JfLog.error(LOG,e.getMessage(),e);
            throw new JfPaySecurityException(e);
        }
    }
    
    /**
     * 使用指定证书签名
     * @param dataSecret
     * @param data
     * @return
     * @throws JfPaySecurityException
     */
    public String signAPIWithSecret(String dataSecret,String data)throws JfPaySecurityException{
        try {
            AESCrypt aes = AESCrypt.getInstance();
            if (StringUtils.isNullStr(data)) {
                throw new JfPaySecurityException("数据为空，无法签名！");
            }
            if (StringUtils.isNullStr(dataSecret)) {
                throw new JfPaySecurityException("密钥为空，无法签名！");
            }
            String input = new String(Base64.encode(data.getBytes(ENCODING)),ENCODING);
            return URLEncoder.encode(aes.crypt(input, MD5Helper.encrypt(dataSecret)),ENCODING);
        } catch (Exception e) {
            JfLog.error(LOG,e.getMessage(),e);
            throw new JfPaySecurityException(e);
        }
    }
    
    /**
     * 验证API数据签名
     * @param data
     * @param datasign
     * @return
     */
    public boolean checkAPISignature(String data,String datasign){
        try {
            if(StringUtils.isNullStr(data)||StringUtils.isNullStr(datasign)){
               throw new JfDataSignatureException("数据格式错误，签名无法验证，签名数据为空！明文数据data="+data+"密文数据datasign="+datasign);
            }
            return datasign.equals(signAPI(data));
            //return true;//DEBUG
        } catch (Exception e) {
            JfLog.error(LOG,e.getMessage(),e);
        }
        return false;
    }
    
    /**
     * 使用指定证书进行验签
     * @param dataSecret
     * @param data
     * @param datasign
     * @return
     */
    public boolean checkAPISignature(String dataSecret,String data,String datasign){
        try {
            if(StringUtils.isNullStr(data)||StringUtils.isNullStr(datasign)){
               throw new JfDataSignatureException("数据格式错误，签名无法验证！");
            }
            return datasign.equals(signAPIWithSecret(dataSecret,data));
            //return true;//DEBUG
        } catch (Exception e) {
            JfLog.error(LOG,e.getMessage(),e);
        }
        return false;
    }
    
    /**
     * 验证API响应数据  数据签名
     * @param data
     * @return
     */
    public boolean checkAPIResponseSignature(String resultData){
        String rData;
        String rDatasign;
        try {
              //解析数据
              JSONObject json = JSONObject.fromObject(resultData);
              rData = new String(Base64.decode(json.getString(DATA).getBytes(ENCODING)),ENCODING);
              rDatasign = json.getString(DATASIGN);
            
            if(StringUtils.isNullStr(rData)||StringUtils.isNullStr(rDatasign)){
               throw new JfDataSignatureException("数据格式错误，签名无法验证！");
            }
            return rDatasign.equals(signAPI(rData));
        } catch (Exception e) {
            JfLog.error(LOG,e.getMessage(),e);
        }
        return false;
    }
    
}
/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinasofti.ro.bizframework.core.libs.Base64;
import com.common.config.JFPayConfigFactory;
import com.common.exception.JfPaySecurityException;
import com.csi.jointforce.common.util.MD5Helper;

/**
 * @Title: DataSignatureUtils.java
 * @Description: <br>
 * 1、账户数据
 * 2、
 * 3、交易流水         
 * 4、最小提现额度
 * 5、管理员
 * <br>
 * @Company: crowdsource
 * @Created on 2015-12-5 上午10:34:49
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class DataSignatureUtils {
    
    private static final Logger LOG = LoggerFactory.getLogger(DataSignatureUtils.class);
    
    /**
     * 验签专用BigDecimal型默认值  
     * 数据库中的0.00D,转换为BigDecimal,会自动保留一位小数 
     */
    public static final BigDecimal ZERO_D = JfDecimal.ZERO;
    
    /**
     * 验签专用Long型默认值
     */
    public static final Long ZERO_L = 0L;
    
    private static String SECRET = null;
    
    private static final String ENCODING = "utf-8";
    
    private DataSignatureUtils(){
    }
    
    private static String getSecret() throws JfPaySecurityException{
        if (SECRET == null) {
            SECRET = JFPayConfigFactory.getJfSettings().getSecretEncrypt();
            if(StringUtils.isNullStr(SECRET)){
                throw new JfPaySecurityException("SECRET not found!");
            }
        }
        return SECRET;
    }
    /**
     * Data Signature
     * @param jfId
     * @param credit
     * @param frozen
     * @param payType
     * @param cardNo
     * @param idCardNo
     * @param updateTime
     * @return
     */
    public static String accountSign(Long jfId,BigDecimal credit,
    		BigDecimal frozen,String payType,String cardNo,String idCardNo,
    		Long updateTime) {
		JfLog.info(LOG, "payType=" + payType);
		BigDecimal iCredit = ZERO_D, iFrozen = ZERO_D;
        Long iUpdateTime = ZERO_L;
        if(null != credit) {
            iCredit = credit;
        }
        if(null != frozen) {
            iFrozen = frozen;
        }
        if(null != updateTime) {
            iUpdateTime = updateTime;
        }
        
        return sign(String.valueOf(iCredit), String.valueOf(iUpdateTime), String.valueOf(jfId), cardNo, null, String.valueOf(iFrozen), idCardNo);
    }
    
    /**
     * Check the signdata
     * @param check
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @return
     */
    public static boolean accountSignCheck(String check,Long jfId,
    		BigDecimal credit,BigDecimal frozen,String payType,String cardNo,
    		String idCardNo,Long updateTime){
        if(StringUtils.isNullStr(check)){
            return false;
        }
        BigDecimal iCredit = ZERO_D,iFrozen = ZERO_D;
        Long iUpdateTime = ZERO_L;
        if(null != credit) {
            iCredit = credit;
        }
        if(null != frozen) {
            iFrozen = frozen;
        }
        if(null != updateTime) {
            iUpdateTime = updateTime;
        }
        return check.equals(accountSign(jfId,iCredit,iFrozen,payType,cardNo,idCardNo,iUpdateTime));
    }
    
    /**
     * sign bill
     * @param billId
     * @param accountId
     * @param billDate
     * @param payerId
     * @param amount
     * @param createTime
     * @param state
     * @return
     */
    public static String billSign(Long accountId, Long billDate, String payerId,BigDecimal amount,Long createTime,String state){
        return sign(String.valueOf(billDate), payerId,state,null, String.valueOf(amount), String.valueOf(accountId),String.valueOf(createTime));
    }
    
    /**
     * check bill sign
     * @param check
     * @param billId
     * @param accountId
     * @param billDate
     * @param payerId
     * @param amount
     * @param createTime
     * @param state
     * @return
     */
    public static boolean billSignCheck(String check,Long accountId, Long billDate, String payerId,BigDecimal amount,Long createTime,String state){
        if(StringUtils.isNullStr(check)){
            return false;
        }
        return check.equals(billSign(accountId, billDate, payerId,amount,createTime,state));
    }
    
    /**
     * sign orders
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @return
     */
    public static String orderSign(BigDecimal paymentAmount, String orderNo, Long payerId, BigDecimal amount,String payType,Long updateTime){
        return sign(String.valueOf(updateTime), String.valueOf(amount), String.valueOf(paymentAmount), payType, String.valueOf(payerId), null, orderNo);
    }
    
    /**
     * check order sign
     * @param check
     * @param paymentAmount
     * @param orderNo
     * @param payerId
     * @param amount
     * @param payType
     * @param updateTime
     * @return
     */
    public static boolean orderSignCheck(String check,BigDecimal paymentAmount, String orderNo, Long payerId, BigDecimal amount,String payType,Long updateTime){
        if(StringUtils.isNullStr(check)){
            return false;
        }
        return check.equals(orderSign(paymentAmount, orderNo, payerId, amount, payType, updateTime));
    }
    /**
     * Sign limit
     * @param a
     * @param b
     * @param c
     * @param d
     * @return
     */
    public static String limitSign(String jfId, String limit, Long createTime, 
    		Long updateTime){
		JfLog.info(LOG, "createTime=" + createTime);
        return sign(null, limit, String.valueOf(updateTime), null, null, String.valueOf(jfId), null);
    }
    
    /**
     * check limit sign
     * @param check
     * @param jfId
     * @param limit
     * @param createTime
     * @param updateTime
     * @return
     */
    public static boolean limitSignCheck(String check,String jfId, String limit, Long createTime, Long updateTime){
        if(StringUtils.isNullStr(check)){
            return false;
        }
        return check.equals(limitSign(jfId,limit,createTime,updateTime));
    }
    
    /**
     * sign data
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @param g
     * @return
     */
    private static String sign(String a,String b,String c,String d,String e,String f,String g){
        String s = "";
        if(!StringUtils.isNullStr(e)){
            s += ";"+e;
        }
        if(!StringUtils.isNullStr(c)){
            s += ";"+c;
        }
        if(!StringUtils.isNullStr(d)){
            s += ";"+d;
        }
        if(!StringUtils.isNullStr(a)){
            s += ";"+a;
        }
        if(!StringUtils.isNullStr(g)){
            s += ";"+g;
        }
        if(!StringUtils.isNullStr(f)){
            s += ";"+f;
        }
        if(!StringUtils.isNullStr(b)){
            s += ";"+b;
        }
        if(StringUtils.isNullStr(s)){
            return null;
        }
        try {
            String md5 = MD5Helper.encrypt(s+";"+getSecret());
            return new String(Base64.encode(md5.getBytes(ENCODING)),ENCODING);
        } catch (Exception ex) {
        	{
        		JfLog.error(LOG,ex.getMessage(),ex);
        	}
        }
        return null;
    }

}

/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import java.security.Key;
import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.exception.JfPayException;


/**
 * @Title: DESUtils.java
 * @Description: <br>
 * <br>
 * @Company: crowdsource
 * @Created on 2015-12-4 下午5:04:56
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class DESUtils {

	private static final Logger LOG = LoggerFactory.getLogger(DESUtils.class);
    /**
     * 字符串默认键值
     */
    private static String strDefaultKey = "wwwjointforcecom";

    /**
     * 默认字符集
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    private String strKey;

    /** 加密工具 */
    private Cipher encryptCipher = null;

    /** 解密工具 */
    private Cipher decryptCipher = null;

    private static DESUtils de = null;

    /**
     * 默认构造方法，使用默认密钥
     * 
     * @throws JfPayException
     */
    private DESUtils() throws JfPayException {
        this(strDefaultKey);
    }

    /**
     * 指定密钥构造方法
     * 
     * @param strKey
     *            指定的密钥
     * @throws JfPayException
     */
    private DESUtils(String strKey) throws JfPayException {
        try {
            String jdkvs=System.getProperty("java.vm.vendor");
            if (null != jdkvs && jdkvs.toUpperCase().startsWith("IBM")) {
                try {
                    Security.addProvider((Provider) Class.forName(
                            "com.ibm.crypto.provider.IBMJCE").newInstance());
                } catch (IllegalAccessException e) {
                    JfLog.error(LOG,e.getMessage(),e);
                } catch (InstantiationException e) {
                    JfLog.error(LOG,e.getMessage(),e);
                } catch (ClassNotFoundException e) {
                    JfLog.error(LOG,e.getMessage(),e);
                } catch (Exception e) {
                    JfLog.error(LOG,e.getMessage(),e);
                }
            } else {
                try {
                    Security.addProvider((Provider) Class.forName(
                            "com.sun.crypto.provider.SunJCE").newInstance());
                } catch (IllegalAccessException e) {
                    JfLog.error(LOG,e.getMessage(), e);
                } catch (InstantiationException e) {
                    JfLog.error(LOG,e.getMessage(), e);
                } catch (ClassNotFoundException e) {
                    JfLog.error(LOG,e.getMessage(),e);
                } catch (Exception e) {
                    JfLog.error(LOG,e.getMessage(),e);
                }
            }
            
            this.strKey = strKey;
            Key key = getKey(strKey.getBytes(DEFAULT_CHARSET));

            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);

            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
            JfLog.error(LOG,e.getMessage(),e);
            throw new JfPayException(e);
        }
    }

    /**
     * 取得DESUtils的一个实例
     */
    public static DESUtils getInstance() {
        try {
            // 返回EnvironmentConfig对象
            if (null == de) {
                de = new DESUtils();
            } else if(!strDefaultKey.equals(de.strKey)) { 
                //added by FengHaiBing
                de = new DESUtils();
            }
            return de;
        } catch (Exception e) {
            JfLog.error(LOG,e.getMessage(),e);
        }
        return null;
    }

    /**
     * 取得DESUtils的一个实例
     */
    public static DESUtils getInstance(String strKey) {
        try {
            // 返回EnvironmentConfig对象
            if (null == de) {
                de = new DESUtils(strKey);
            } else if (null == de.strKey || !de.strKey.equals(strKey)) {
                de = new DESUtils(strKey);
            }
            return de;
        } catch (Exception e) {
            JfLog.error(LOG,e.getMessage(),e);
        }
        return null;
    }

    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[]
     * hexStr2ByteArr(String strIn) 互为可逆的转换过程
     * 
     * @param arrB
     *            需要转换的byte数组
     * @return 转换后的字符串
     * @throws JfPayException
     *             本方法不处理任何异常，所有异常全部抛出
     */
    public static String byteArr2HexStr(byte[] arrB) throws JfPayException {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuilder sb = new StringBuilder(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB)
     * 互为可逆的转换过程
     * 
     * @param strIn
     *            需要转换的字符串
     * @return 转换后的byte数组
     * @throws JfPayException
     *             本方法不处理任何异常，所有异常全部抛出
     * @author FengHaiBing
     */
    public static byte[] hexStr2ByteArr(String strIn) throws JfPayException {
        try {
            byte[] arrB = strIn.getBytes(DEFAULT_CHARSET);
            int iLen = arrB.length;

            // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
            byte[] arrOut = new byte[iLen / 2];
            for (int i = 0; i < iLen; i = i + 2) {
                String strTmp = new String(arrB, i, 2);
                arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
            }
            return arrOut;
        } catch (Exception e) {
            JfLog.error(LOG,e.getMessage(),e);
            throw new JfPayException(e);
        }
    }

    /**
     * 加密字节数组
     * 
     * @param arrB
     *            需加密的字节数组
     * @return 加密后的字节数组
     * @throws JfPayException
     */
    public byte[] encrypt(byte[] arrB) throws JfPayException {
        try {
        	JfLog.info(LOG,"\nthis.strKey:" + this.strKey);
            return encryptCipher.doFinal(arrB);
        } catch (Exception e) {
        	JfLog.error(LOG,e.getMessage(),e);
            throw new JfPayException(e);
        }
    }

    /**
     * 加密字符串
     * 
     * @param strIn
     *            需加密的字符串
     * @return 加密后的字符串
     * @throws JfPayException
     */
    public String encrypt(String strIn) {
        try {
        	JfLog.info(LOG,"String before encrypt:" + strIn);
            String strOut = byteArr2HexStr(encrypt(strIn.getBytes(DEFAULT_CHARSET)));
            JfLog.info(LOG,"String after encrypt:" + strOut);
            return strOut;
        } catch (Exception e) {
        	JfLog.error(LOG,e.getMessage(),e);
        }
        return strIn;
    }

    /**
     * 解密字节数组
     * 
     * @param arrB
     *            需解密的字节数组
     * @return 解密后的字节数组
     * @throws JfPayException
     */
    public byte[] decrypt(byte[] arrB) throws JfPayException {
        try {
        	JfLog.info(LOG,"\nthis.strKey:" + this.strKey);
            return decryptCipher.doFinal(arrB);
        } catch (Exception e) {
        	JfLog.error(LOG,e.getMessage(),e);
            throw new JfPayException(e);
        }
    }

    /**
     * 解密字符串
     * 
     * @param strIn
     *            需解密的字符串
     * @return 解密后的字符串
     * @throws JfPayException
     */
    public String decrypt(String strIn) {
        try {
        	JfLog.info(LOG,"String before decrypt:" + strIn);
            String strOut = new String(decrypt(hexStr2ByteArr(strIn)), DEFAULT_CHARSET);
            JfLog.info(LOG,"String after decrypt:" + strOut);
            return strOut;
        } catch (Exception e) {
        	JfLog.error(LOG,e.getMessage(),e);
        }
        return strIn;
    }

    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
     * 
     * @param arrBTmp
     *            构成该字符串的字节数组
     * @return 生成的密钥
     * @throws java.lang.Exception
     */
    private Key getKey(byte[] arrBTmp) throws JfPayException {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];

        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        // 生成密钥
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

        return key;
    }

    /**
     * main方法 。
     * 
     * @author FengHaiBing
     * @param args
     */
    public static void main(String[] args) {
        try {
            DESUtils des = DESUtils.getInstance();// new
            String enExtUserName = "";
            enExtUserName = "ad621897546d0ad1634e35ef828983c5";
            des.decrypt(enExtUserName);
            enExtUserName = "2a173ccfad82cc1517b3d9bcaf4920cb";
            des.decrypt(enExtUserName);
            enExtUserName = "38e100038f762a43a18574db997d472665165caea0dfbbf6";
            des.decrypt(enExtUserName);
        } catch (Exception e) {
        	JfLog.error(LOG,e.getMessage(),e);
        }
        
    }

}

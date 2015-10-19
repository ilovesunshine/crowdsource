/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Title: JFAccountUtils.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-4-22 下午4:56:17
 * @author ouyw
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JFAccountUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(JFAccountUtils.class);
    
    private static final String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    
    private JFAccountUtils(){
    }
    
	/**
	 * 得到随机密码
	 * @return
	 */
	public static String getRPassqord(){
		StringBuilder result = new StringBuilder();
		Random random = new Random();
		for(int i = 0 ; i < 6; i++){
			result.append(random.nextInt(10));
		}
		return MD5Util.encrypt(result.toString());
	}
	
	/**
	 * 开户时，获取随机的后缀字符串 
	 * @param length
	 * @author ouyw
	 * @return
	 */
	public static String getEnterNameSuffix(int length){
		StringBuilder result = new StringBuilder();
		Random random = new Random();
		char[] strTemp = str.toCharArray();
		for(int i = 0 ; i < length;i++){
			result.append(strTemp[random.nextInt(strTemp.length)]);
		}
		JfLog.info(LOG,result.toString());
		return result.toString();
	}
	
	/**
	 * 重组账单编号
	 * @param billNo
	 * @return
	 */
	public static String getSuborderid(String billNo){
		StringBuilder result = new StringBuilder();
		result.append(billNo);
		result.append("-");
		int length = 32 - result.toString().length();
		Random random = new Random();
		for(int i = 0 ; i < length; i++){
			result.append(random.nextInt(10));
		}
		return result.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		JfLog.info(LOG,JFAccountUtils.getRPassqord());
		System.out.println(JFAccountUtils.getSuborderid("B0201507016857194006"));
	}

}

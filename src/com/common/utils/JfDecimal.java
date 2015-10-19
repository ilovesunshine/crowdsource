/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import java.math.BigDecimal;

/**
 * @Title: JfDecimal.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-29 上午12:42:11
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfDecimal {
    
    public static final BigDecimal ZERO = new BigDecimal("0.00");
    
    /**
     * The value 1, with a scale of 0. 
     */
    public static final BigDecimal ONE = BigDecimal.ONE;
    
    public static final BigDecimal NEGATIVE_ONE = new BigDecimal(-1);
    
    private JfDecimal(){
    }

    public static BigDecimal create(String s){
        return new BigDecimal(s).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    // 默认除法运算精度
    public static final int DEF_DIV_SCALE = 2;

    /**
     * 提供精确的加法运算。
     * 
     * @param v1
     *            被加数
     * @param v2
     *            加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).setScale(DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {
        if(null == v1) {
            if(null == v2) {
                return JfDecimal.ZERO;
            } else {
                return v2.setScale(DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
            }
        }
        
        return v1.add(v2).setScale(DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供精确的减法运算。
     * 
     * @param v1
     *            被减数
     * @param v2
     *            减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).setScale(DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {
        if(null == v1) {
            if(null == v2) {
                return JfDecimal.ZERO;
            } else {
                return v2.multiply(JfDecimal.NEGATIVE_ONE).setScale(DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
            }
        }
        
        return v1.subtract(v2).setScale(DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供精确的乘法运算。
     * 
     * @param v1
     *            被乘数
     * @param v2
     *            乘数
     * @return 两个参数的积
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).setScale(DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {
        return v1.multiply(v2).setScale(DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供（相对）精确的除法运算， 当发作除不尽的情况时， 精确到 小数点以后10位， 以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @return 两个参数的商
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    public static BigDecimal div(BigDecimal v1, BigDecimal v2) {
        return v1.divide(v2).setScale(DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 提供（相对）精确的除法运算。 当发作除不尽的情况时， 由scale参数指 定精度， 以后的数字四舍五入。
     * 
     * @param v1
     *            被除数
     * @param v2
     *            除数
     * @param scale
     *            表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        if(v2 == 0) {
            throw new IllegalArgumentException(
                    "The v2 must not be zero");
        }
        
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     * 
     * @param v
     *            需要四舍五入的数字
     * @param scale
     *            小数点后保管几位
     * @return 四舍五入后的后果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        return b.divide(BigDecimal.ONE, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * 提供精确的小数位四舍五入处理。
     * 
     * @param v
     *            需要四舍五入的数字
     * @param scale
     *            小数点后保管几位
     * @return 四舍五入后的后果
     */
    public static double round(double v) {
        return round(v, DEF_DIV_SCALE);
    }

    /**
     * 比较两值得大小
     * @param b1
     * @param b2
     * @return
     */
    public static boolean compareGreater(BigDecimal b1,BigDecimal b2){
    	if(b1.compareTo(b2) == 1){
    		return true;
    	}
    	
    	return false;
    }
    
    /**
     * 比较两值得大小 b1 < b2
     * @param b1
     * @param b2
     * @return
     */
    public static boolean compareLess(BigDecimal b1,BigDecimal b2){
    	if(b1.compareTo(b2) == -1){
    		return true;
    	}
    	
    	return false;
    }
    
    /**
     * 比较是否等于0
     * @param num
     * @return
     */
    public static boolean compareWithZero(BigDecimal num){
    	if(num.compareTo(ZERO) == 0){
    		return true;
    	}
    	
    	return false;
    }
    
    /**
     * 比较大于等于0
     * @param num
     * @return
     */
    public static boolean compareZeroEqOrGr(BigDecimal num){
    	if(num.compareTo(ZERO) == 0 || num.compareTo(ZERO) ==1){
    		return true;
    	}
    	
    	return false;
    }
    
    /**
     * 将BigDecimal转换成double
     * @param num
     * @return
     */
    public static double getDoubleValue(BigDecimal num){
        if(num==null){
            return Double.parseDouble("0.00");
        }
        return num.doubleValue();
    }
    
}

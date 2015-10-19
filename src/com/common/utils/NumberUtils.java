/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: NumberUtils.java
 * @Description: <br>
 * <br>
 * @Company: crowdsource
 * @Created on 2015-12-4 下午6:07:06
 * @author FengHaiBing
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class NumberUtils {

    private NumberUtils() {
    }

    private static final Logger LOG = LoggerFactory
            .getLogger(NumberUtils.class);

    private static final String formatType = "#,###,###,###,###,###,###,###,###,###,###,###.00";
    public static final String formatType_Integer = "#,###,###,###,###,###,###,###,###,###,###,###";
   

    /**
     * 千分位
     * 
     * @param number
     * @return
     */
    public static String number2StringThousand(BigDecimal number) {
        if(null == number) {
            return "0.00";
        }
        return number2String(number, formatType);

    }

    /**
     * cover number to string
     * 
     * @param number
     * @param formatType
     *            #.00, #,###.000 #.00%
     * @return
     */
    public static String number2String(BigDecimal number, String formatType) {
        // 将传入的double转换成指定的formatType格式
        if (null == number) {
            return "0.00";
        }

        if (-1 < number.doubleValue() && number.doubleValue() < 1) {
            BigDecimal bd = number;
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            return bd.toString();
        }
        DecimalFormat df = new DecimalFormat(formatType);

        return df.format(number);

    }
    public static String number2String2(BigDecimal number, String formatType) {
        // 将传入的double转换成指定的formatType格式
        if (null == number) {
            return "0";
        }

        DecimalFormat df = new DecimalFormat(formatType);

        return df.format(number);

    }

    /**
     * 字符转BigDecimal类型
     * @param s
     * @return
     */
    public static BigDecimal str2BigDecimal(String str){
        String s = str;
        if(StringUtils.isNullStr(s)) {
            s = "0";
        }
        
        BigDecimal d = JfDecimal.ZERO;
        try {
            d = JfDecimal.create(s);
        } catch (NumberFormatException e) {
        	{
        		JfLog.error(LOG,"字符["+s+"]转BigDecimal失败！");
        	}
            return JfDecimal.ZERO;
        }
        return d;
    }
    
    public static BigDecimal obj2BigDecimal(Object object) {
        Object obj = object;
        if(null == obj) {
            obj = "0.00";
        }
        
        return str2BigDecimal(obj.toString());
    }

    public static Integer str2Integer(String str) {
        String s = str;
        if (StringUtils.isNullStr(s)) {
            s = "0";
        }

        Integer d = 0;
        try {
            d = Integer.valueOf(s);
        } catch (NumberFormatException e) {
        	{
        		JfLog.error(LOG,"字符[" + s + "]转Integer失败！");
        	}
            return 0;
        }
        return d;
    }

}

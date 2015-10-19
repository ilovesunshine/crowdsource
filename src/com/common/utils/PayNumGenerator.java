/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.exception.JfPayException;
import com.common.exception.PayNumException;
import com.csi.jf.uuid.OrderCodeBean;


/**
 * @Title: PayNumGenerator.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-3 下午2:22:14
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class PayNumGenerator {

	private static final Logger LOG = LoggerFactory.getLogger(PayNumGenerator.class);
    
    private static Object LOCK = new Object();
    
    private static OrderCodeBean orderCodeBean = null;
    
    private final static int  SEQ_LENGTH = 20;
    
    private PayNumGenerator(){
    }
    
    public static OrderCodeBean createOrderCode() {
        synchronized (LOCK) {
            if (orderCodeBean != null) {
                return orderCodeBean;
            } else {
                if (orderCodeBean == null) {
                    orderCodeBean = (OrderCodeBean) BeanFactory.getBean("orderCodeBean");
                }
                return orderCodeBean;
            }
        }
    }
    
    /**
     * 生成交易流水号
     * @param type 2位数字
     * @return 20位流水号
     */
    public static String genNum(String type){
        synchronized (LOCK) {
            if (!StringUtils.isNullStr(type) && type.trim().length() == 2) {
                String code = null;
                try {
                    String prefix = type.concat(DateUtil.getCurrentDayShort());
                    code = createOrderCode().getSeqString(prefix, SEQ_LENGTH);
                } catch (Exception e) {
                	{
                		JfLog.error(LOG,"流水号生成失败：type=["+String.valueOf(type)+"]"+e.getMessage(),e);
                	}
                }
                return code;
            }
        }
        return null;
    }
    
    /**
     * 生成18位账单编号：B+（1位账户类型）+（6位日期）+（10位序号）
     * @param accountType
     * @param billDate
     * @param seq
     * @return
     */
    public static String genBillNo(String accountType, String billDate, Long seq)
            throws PayNumException {
        if (StringUtils.isNullStr(accountType)
                || (!"0".equals(accountType) && !"1".equals(accountType))
                || StringUtils.isNullStr(billDate) || billDate.length() != 8
                || seq == null) {
            throw new JfPayException("输入参数不合法!");
        }
        String s = String.format("%010d", seq);
        if (s.length() > 10) {
            s = s.substring(s.length() - 10, s.length());
        }
        return "B" + accountType + billDate + s;
    }
    
}

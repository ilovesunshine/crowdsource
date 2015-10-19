/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.constants;

/**
 * @Title: JfApi.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-7-10 下午12:41:42
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfApiConstants {
    
    /**
     * 接口响应数据
     */
    public static final String DATA = "data";

    /**
     * 接口响应数据签名
     */
    public static final String DATASIGN = "datasign";
    /**
     * 接口响应代码
     */
    public static final String RESULT_CODE = "resultcode";
    
    /**
     * 支付类型
     */
    public static final String ACCOUNT_PAY_TYPE = "payType";

    /**
     * 账户状态
     */
    public static final String ACCOUNT_PAY_STATE = "state";
    
    /**
     * 账户余额
     */
    public static final String ACCOUNT_BALANCE = "balance";
    
    /**
     * 接口异常
     */
    public static final String ERROR = "error";
    /**
     * 异常信息
     */
    public static final String EXCEPTIONS = "exceptions";

    /**
     * 接口响应：正常
     */
    public static final int RC_SUCCESS = 1;

    /**
     * 接口响应：处理失败，程序处理失败
     */
    public static final int RC_ERROR = 0;
    public static final String RC_ERROR_NAME = "处理失败：服务异常!";
    /**
     * 接口响应：签名验证失败
     */
    public static final int RC_BADSIGN = -1;
    public static final String RC_BADSIGN_NAME = "数据签名验证失败!";
    /**
     * 接口响应：请求参数异常
     */
    public static final int RC_BADREQUEST = -2;
    public static final String RC_BADREQUEST_NAME = "请求失败，参数为空或格式错误!";
    /**
     * 接口响应：未开户
     */
    public static final int RC_NOTEXIST = -3;
    public static final String RC_NOTEXIST_NAME = "资金账户未开通!";
    public static final String RC_NOTEXIST_SUB_NAME = "子账户未开通!";
    /**
     * 接口响应：账户异常，无法发包
     */
    public static final int RC_ABNORMAL = -4;
    public static final String RC_ABNORMAL_NAME = "资金账户异常!";
    /**
     * 接口响应：账户冻结，无法发包
     */
    public static final int RC_LOCKED = -5;
    public static final String RC_LOCKED_NAME = "资金账户被锁定!";
    /**
     * 接口响应：订单不是新订单
     */
    public static final int RC_ORDER_NOT_NEW = -7;
    public static final String RC_ORDER_NOT_NEW_NAME = "选择支付的订单不是新订单不允许支付!";
    /**
     * 接口响应：订单信息错误
     */
    public static final int RC_ORDER_INFO_ERROR = -8;
    public static final String RC_ORDER_INFO_ERROR_NAME = "请求失败，订单信息错误!";
    /**
     * 接口响应：调用第三方EBC接口失败
     */
    public static final int RC_INVOKE_EBC_ERROR = -9;
    public static final String RC_INVOKE_EBC_ERROR_NAME = "调用EBC接口失败!";
    /**
     * 接口响应：有信用账单未支付完成，不可继续发包
     */
    public static final int RC_DEBT = -11;
    public static final String RC_DEBT_NAME = "有信用账单未支付完成，不可继续发包!";
    /**
     * 接口响应：处理失败，账户冻结或余额不足
     */
    public static final int RC_BALANCE = -12;
    public static final String RC_BALANCE_NAME = "可用发包额度不足!";
    /**
     * 接口响应：处理失败，订单重复提交
     */
    public static final int RC_DUPLICATE_COMMIT = -13;
    public static final String RC_DUPLICATE_COMMIT_NAME = "订单重复提交";
    /**
     * 接口响应：订单不存在 
     */
    public static final int RC_NOORDER = -14;
    public static final String RC_NOORDER_NAME = "订单/红包不存在";
    
    /**
     * 接口响应：没有绑定银行卡
     */
    public static final int RC_NOCARDS = -15;
    /**
     * 接口响应：支付失败
     */
    public static final int RC_PAYFAILED = -16;
    
    /**
     * 接口响应 ：红包系统记账此红包余额 不等于 支付系统记账的红包余额
     */
    public static final int RC_BALANCE_NOTEQUAL = -17;
    public static final String RC_BALANCE_NOTEQUAL_NAME = "红包系统记账与支付系统记账余额不等!";
    
    /**
     * 接口响应 ：订单支付方式不是实时支付
     */
    public static final int RC_NOT_REALTIME_PAYTYPE = -18;
    public static final String RC_NOT_REALTIME_PAYTYPE_NAME = "请求失败，支付方式不是实时支付!";
    
    /**
     * 接口响应 : 权限不足
     */
    public static final int RC_NO_RIGHT = -19;
    public static final String RC_NO_RIGHT_NAME = "请求失败，您不是企业负责人没有权限操作！";
    
    /**
     * 编码
     */
    public static final String ENCODE_UTF8= "utf-8";

    /**
     * JfApi
     */
    private JfApiConstants() {
    }

}

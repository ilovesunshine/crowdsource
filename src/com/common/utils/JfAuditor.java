/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.common.event.EventManager;


/**
 * @Title: JfAuditor.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-11-27 下午5:57:43
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfAuditor {
    
    /**
     * 账户类型：-1-游客
     */
    public static final String ACCOUNT_TYPE_GUEST = "-1";
    /**
     * 账户类型：0-个人用户
     */
    public static final String ACCOUNT_TYPE_PER = "0";
    /**
     * 账户类型：1-企业用户
     */
    public static final String ACCOUNT_TYPE_ENT = "1";
    /**
     * 账户类型：2-运营管理员
     */
    public static final String ACCOUNT_TYPE_OPT = "2";
    /**
     * 账户类型：3-系统管理员
     */
    public static final String ACCOUNT_TYPE_SYS = "3";
    /**
     * 账户类型：4-API调用者
     */
    public static final String ACCOUNT_TYPE_API_REQUESTER = "4";
    /**
     * 账户类型：5-定时任务运行器
     */
    public static final String ACCOUNT_TYPE_JOB_RUNNER = "5";
    /**
     * 账户类型：6-系统自主运行
     */
    public static final String ACCOUNT_TYPE_SERVICE = "6";

    // *************************ACT_RESULT_操作结果列表*****************************************

    // 操作失败
    public static final int ACT_FAILED = 0;
    
    // 操作成功
    public static final int ACT_SUCESS = 1;
    
    // 无业务操作
    public static final int ACT_NOTHING = 2;

    // *************************ACT_RESULT_操作结果列表*****************************************
    /**
     *  ##支付管理##
     *  管理登录、登出         [行为人] 于 [发生时间] 登录支付管理成功/失败，密码错误
     *  转账  支付D账单       [行为人] 于 [发生时间] 支付D账单{账单号：名称}，金额XXX
     *  企业账户解冻、冻结 [行为人] 于 [发生时间] 冻结/解冻企业XXX
     *  设置信用额度            [行为人] 于 [发生时间] 变更企业XXX信用额度XXX为XXX
     *  设置小最提现额度     [行为人] 于 [发生时间] 变更最小提现额度XXX为XXX 
     *  下载对账单               [行为人] 于 [发生时间] 从仓库XXX下载文件XXX     
     */
    public static final String OBJ_PAY_OPT = "支付管理";
    /**
     *  ##企业##
     *  开通账户    [行为人] 于 [发生时间] 申请开通企业名称资金账户 成功-1，失败-0
     *  支付账单    [行为人] 于 [发生时间] 支付账单{账单号：名称}，金额XXX
     *  充值  [行为人] 于 [发生时间] 从XXX银行充值XXX元
     *  签单  [支付平台] 于 [发生时间] 接收到XXX企业{订单}签单请求，金额XXX
     *  同意支付    [支付平台] 于 [发生时间] 接收到XXX企业{订单}同意支付请求，金额XXX
     *  订单结束    [支付平台] 于 [发生时间] 接收到XXX企业{订单}结束通知，释放冻结金额XXX       
     */
    public static final String OBJ_PAY_ENT = "企业账户";
    /**
     *  ##个人 ##
     *  开通账户      [行为人] 于 [发生时间] 申请开通个人姓名资金账户
     *  提现             [行为人] 于 [发生时间] 从提现XXX元到XXX银行
     *  添加银行卡   [行为人] 于 [发生时间] 添加XXX银行卡绑定
     *  删除银行卡   [行为人] 于 [发生时间] 删除XXX银行卡绑定        
     */
    public static final String OBJ_PAY_PER = "个人账户";
    /**
     * ##接口##
     *  判断签单条件  [发包方] 于 [发生时间] 请求进行签单[金额]条件判断
     *  签订订单    [发包方] 于 [发生时间] 请求进行订单[订单号]签单[金额]处理
     *  同意支付    [发包方] 于 [发生时间] 请求进行订单[订单号]的[2]笔同意支付处理
     *  结束订单    [发包方] 于 [发生时间] 通知订单[订单号]结束，释放冻结金额[XXX]
     */
    public static final String OBJ_PAY_API = "接口";
    /**
     * ##定时任务##
     * 生成账单    [系统] 于 [发生时间] 自动生成当期[账单期]的接包方[XXX]份、发包方账单[XXX]份
     * 账单逾期支付检测    [系统 于 [发生时间] 自动检测当期账单的支付情况，账户[账号]因逾期未支付被自动冻结
     * 下载并解析转换交易日志文件   [系统] 于 [发生时间] 自动从[地址]下载交易日志文件[文件名]，解析成功，备份成功
     * 数据异常检测   [系统] 于 [发生时间] 自动检测账户[账号]的交易数据[ID-数据项名称]异常
     */
    public static final String OBJ_PAY_JOBS = "定时任务";
    /**
     * ##系统管理##
     * 管理员登录、登出         [行为人] 于 [发生时间] 登录系统管理成功/失败，密码错误
     * 添加管理员                    [行为人] 于 [发生时间] 添加XXX类型管理员XXX
     * 修改管理员信息              [行为人] 于 [发生时间] 修改管理员XXX类型|密码
     * 删除管理员                    [行为人] 于 [发生时间] 删除管理员XXX
     * 锁定管理员、解锁管理员[行为人] 于 [发生时间] 锁定/解除锁定管理员XXX
     */
    public static final String OBJ_PAY_SYS = "系统管理";
    
    /**
     * 系统自主运行，如用户开户时红包自动变现等
     */
    public static final String OBJ_PAY_SERVICE = "SERVICE";
    
    /**
     * 订单跟踪的行为审核
     */
    public static final String ACTION_CHECK_ORDER_NEW = "验证订单是否为新订单";
    public static final String ACTION_ORDER_PAYABLE = "验证能否签单";
    public static final String ACTION_ORDER_CREATE = "PM签单";
    //'PM签单'类型拆分
    public static final String ACTION_ORDER_CREDIT = "订单资金托管-信用|线下";
    public static final String ACTION_ORDER_REALTIME = "订单资金托管-实时";
    public static final String ACTION_BUM_AGREE_PAT = "BUM同意支付订单"; 
    //'BUM同意支付订单'类型拆分
    public static final String ACTION_AGREE_PAY_CREDIT = "订单同意支付-信用|线下"; 
    public static final String ACTION_AGREE_PAY_REALTIME = "订单同意支付-实时"; 
    public static final String ACTION_B_PAT = "企业支付账单";
    public static final String ACTION_JF_PAT = "平台支付账单";   
    public static final String ACTION_ORDER_RECORD = "订单记账";
    public static final String ACTION_ORDER_END = "订单结束";
    public static final String ACTION_ORDER_CANCELED = "订单取消";
    public static final String ACTION_QUERY_ACCOUNTINFO = "查询个人账户信息";
    public static final String ACTION_SYS_TRANSFER = "系统管理员通过转账进行支付补偿";   
    
    public static final String ACTION_CALLBACK_DEPOSITRESULT = "充值结果回调通知";   
    public static final String ACTION_CALLBACK_DEPOSITRESULT_JF = "JF平台充值结果回调通知";   
    public static final String ACTION_CALLBACK_MASSTRANSFERRESULT = "批量转帐回调通知";   
    public static final String ACTION_CALLBACK_CARDBINDSULT = "银行卡绑定结果回调通知"; 
    
    public static final String ACTION_BONUS_QUERY_BALANCE = "查询平台运营账户余额";
    public static final String ACTION_BONUS_QUERY_AMOUNT = "查询个人红包金额";
    public static final String ACTION_BONUS_CREATE = "红包创建";
    public static final String ACTION_BONUS_RECORD = "红包记账";
    public static final String ACTION_BONUS_PAY = "红包支付";
    public static final String ACTION_BONUS_END = "红包关闭";
    public static final String ACTION_BONUS_EXPIREDATE = "红包过期";
    public static final String ACTION_SYNCHRONIZED_OPEN_ACCOUNT = "同步企业账户开通状态";
    public static final String ACTION_SYNCHRONIZED_OPEN_PER_ACCOUNT = "同步个人账户开通状态";

    public static final String ACTION_PER_TRANSFER = "个人转账";
    
    public static final String ACTION_REGISTER_ENT_ACCOUNT = "企业开通资金账户";
    public static final String ACTION_REGISTER_PER_ACCOUNT = "个人开通资金账户";
    public static final String ACTION_REGISTER_ENT_ACCOUNT_SUB = "企业开通资金子账户";
    public static final String ACTION_REGISTER_PER_ACCOUNT_SUB = "个人开通资金子账户";
    public static final String ACTION_REGISTER_ENT_ACCOUNT_AGAIN = "重新开通企业资金账户";
    public static final String ACTION_REGISTER_PER_ACCOUNT_AGAIN = "重新开通个人资金账户";
    public static final String ACTION_LOGIN_PAY_ADMIN = "登录支付管理";
    public static final String ACTION_LOGIN_SYS_ADMIN = "登录系统管理";
    public static final String ACTION_LOGOUT_SYS_ADMIN = "登出系统管理";
    public static final String ACTION_LOGOUT_PAY_ADMIN = "登出支付管理";
    public static final String ACTION_BIND_BANKCARD = "绑定银行卡";
    public static final String ACTION_DELETE_BANKCARD = "删除绑定银行卡";
    public static final String ACTION_MODIFY_ENT_ACCOUNT = "修改企业账户状态[冻结/解冻]";
    public static final String ACTION_MODIFY_MOBILE = "修改平台账户手机号";
    public static final String ACTION_MODIFY_PAY_PASSWORD = "修改平台账户支付密码";
    public static final String ACTION_MODIFY_USERNAME = "修改开户名";
    public static final String ACTION_SET_WITHDRAW_AMOUNT = "设置最小提现额度";
    public static final String ACTION_SET_CREDIT_AMOUNT = "设置信用额度";
    public static final String ACTION_DECRYT_KEY_FIELDS = "解密关键字段";
    public static final String ACTION_ADD_ADMINISTRATOR = "添加管理员";
    public static final String ACTION_MODIFY_ADMINISTRATOR = "编辑管理员";
    public static final String ACTION_DELETE_ADMINISTRATOR = "删除管理员";
    public static final String ACTION_DOWNLOAD_TRANS_LOG_FILE = "下载并处理交易日志文件";
    public static final String ACTION_DOWNLOAD_BILL_FILE = "下载账单";
    public static final String ACTION_SYSTEM_MONITOR = "支付系统监控";
    public static final String ACTION_CREATE_BILL = "生成账单";
    public static final String ACTION_CHECK_BILL = "账单逾期支付检测";
    public static final String ACTION_CHECK_IS_FUND_ACCOUNT = "验证资金账户是否开通";
    public static final String ACTION_ACCOUNT_DEPOSIT = "账户充值";
    public static final String ACTION_ACCOUNT_WITHDRAW = "账户提现";
    public static final String ACTION_JF_INNER_TRANSFER = "平台内部账户转账";
    public static final String ACTION_EXPORT_DATA = "导出数据";
    public static final String ACTION_SET_PAYMENT = "设置发包方的支付方式";
    public static final String ACTION_SUB_RELEASE_FROZEN_ACMOUNT = "释放子账户的冻结金额";

    private JfAuditor(){
    }

    public static HttpServletRequest getRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        if(null == ra) {
            return null;
        }
        return ((ServletRequestAttributes) ra).getRequest();
    }
    
     /**
      * 记录行为审核
      * @param action 行为
      * @param message 描述
      * @param actObj 模块
      * @param actResult 结果
      * @param accountType 账户类别
      */
     public static void audit(String action,String message,String actObj,int actResult,String accountType) {
         //异步更新保存报文
         EventManager.getInstance().triggerJfAuditor(action, message, actObj, actResult, accountType);
     }

     /**
      * Audit for system user success
      * @param action
      * @param message
      */
     public static void auditSys(String action,String message){
    	 JfAuditor.audit(action, message, JfAuditor.OBJ_PAY_SYS, JfAuditor.ACT_SUCESS,JfAuditor.ACCOUNT_TYPE_SYS);
     }
     
     /**
      * Audit for system user failures
      * @param action
      * @param message
      */
     public static void auditSysFail(String action,String message){
    	 JfAuditor.audit(action, message, JfAuditor.OBJ_PAY_SYS, JfAuditor.ACT_FAILED,JfAuditor.ACCOUNT_TYPE_SYS);
     }
     
     /**
      * Audit for job runner success
      * @param action
      * @param message
      */
     public static void auditJob(String action,String message){
    	 JfAuditor.audit(action, message, JfAuditor.OBJ_PAY_JOBS, JfAuditor.ACT_SUCESS,JfAuditor.ACCOUNT_TYPE_JOB_RUNNER);
     }
     
     /**
      * Audit for job runner failures
      * @param action
      * @param message
      */
     public static void auditJobFail(String action,String message){
    	 JfAuditor.audit(action, message, JfAuditor.OBJ_PAY_JOBS, JfAuditor.ACT_FAILED,JfAuditor.ACCOUNT_TYPE_JOB_RUNNER);
     }
     
     /**
      * 记录行为审核
      * 
      * orderNo和orderType唯一确定一条订单
      * 
      * @param action 行为
      * @param message 描述
      * @param actObj 模块
      * @param actResult 结果
      * @param orderNo   ACT_CODE
      * @param orderType    ACT_ITEM_CODE
      */
     public static void audit(String action,String message,String actObj,int actResult,String orderNo,String orderType,String returnMessage,String accountType) { 
        // 异步更新保存报文
         EventManager.getInstance().triggerJfAuditor2(action, message, actObj, actResult, orderNo, orderType, returnMessage, accountType);
     }
     
     /**
      * 订单接口行为审核-正常
      * @param action
      * @param message
      * @param orderNo
      */
     public static void auditApi(String action,String message){
    	 JfAuditor.audit(action, message, JfAuditor.OBJ_PAY_API, JfAuditor.ACT_SUCESS,JfAuditor.ACCOUNT_TYPE_API_REQUESTER);
     }
     
     /**
      * 订单接口行为审核-异常
      * @param action
      * @param message
      * @param orderNo
      */
     public static void auditApiFail(String action,String message){
    	 JfAuditor.audit(action, message, JfAuditor.OBJ_PAY_API, JfAuditor.ACT_FAILED,JfAuditor.ACCOUNT_TYPE_API_REQUESTER);
     }
}

/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.config;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.common.utils.StringUtils;
import com.common.utils.UrlParserUtil;



/**
 * @Title: JfConstants.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-7-3 下午5:11:50
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfConstants {
	
    public static final String UTF8 = "utf-8";
    
	//平台项目名称
    public static final String JF_PRODUCTID="jointforce";
	
    //运营商
    public static final String JF_OWNERID = "EBC";
    
    //登陆方式
    public static final String BANK_LONGINTYPE_DEFAULT = "5";
    
    /**
     * 系统参数
     */
    public static final String SYSTEM_PAYPASS = "system.paypass";
    
    /**
     * 空字符串
     */
    public static final String JF_PAY_EMPTY = "";
    /**
     * EBC交易日志文件标示 - 常规日志
     */
    public static final String JF_PAY_EBC_COMON = "comon";
    /**
     * EBC交易日志文件标示- 校验日志
     */
    public static final String JF_PAY_EBC_CHECK = "check";
    /**
     * EBC交易日志文件标示 - 错误日志
     */
    public static final String JF_PAY_EBC_ERROR = "error";
    /**
     * EBC交易日志文件起始年份
     */
    public static final String JF_PAY_EBC_TRANSLOGS_STARTYEAR = "pay.ebc.translogs.startyear";
    
    
    //团队人员状态
    public static final Integer BUM_MEMBER_LEAVING = 0; //离职
    public static final Integer BUM_MEMBER_WORKING = 1; //在职
    public static final Integer BUM_MEMBER_ASSIGN = 2; //外派
    public static final Integer BUM_MEMBER_UNACCEPT = 3; //人员进入待确认
    public static final Integer BUM_MEMBER_REFUSED= 4; //人员进入 拒绝
    public static final Integer BUM_MEMBER_INVENT= 5; //邀请已加入的成员
    
    //人员状态(joinType) 1-目前正在团队，2-借调人员，3-外包人员
    public static final Integer EMPLOY_TYPE_INNER  = 1;
    public static final Integer EMPLOY_TYPE_SECONDED = 2;
    public static final Integer EMPLOY_TYPE_OUTER = 3;
    
    //管理员角色-1-运营管理,2-系统管理,3-运营专员
    public static final String ADMIN_ROLE_OPT = "1";
    public static final String ADMIN_ROLE_SYS = "2";
    public static final String ADMIN_ROLE_OPT_USER = "3";
    
    //管理用户状态-0-锁定,1-正常
    public static final String ADMIN_STATUS_LOCKED = "0";
    public static final String ADMIN_STATUS_NORMAL = "1";
    
    //删除标记-1-是,0-否
    public static final String DELETE_FLAG_TRUE = "1";
    public static final String DELETE_FLAG_FALSE = "0";
    
    /////////////////////////////////////////////////////////////////////////////////
    //订单状态 00-执行中,01-结束,02-取消
    public static final String ORDER_STATUS_RUNNING = "00";
    public static final String ORDER_STATUS_RUN_END = "01"; 
    public static final String ORDER_STATUS_CANCELED = "02"; 
    
    //订单类型 00-企业订单,01-红包,02-线下订单,99-其它
    public static final String ORDER_TYPE_ORDER = "00";
    public static final String ORDER_TYPE_BONUS = "01"; 
    public static final String ORDER_TYPE_OFFLINE_ORDER = "02"; 
    public static final String ORDER_TYPE_OTHER = "99";
    
    //是否新旧订单，0-旧订单，1-新订单
    public static final String ORDER_NEW = "1";
    public static final String ORDER_OLD = "0";
       
    //帐户类型：0-个人,1-企业
    public static final String AC_TYPE_PER = "0";
    public static final String AC_TYPE_ENT = "1";
    
    //子帐户类型：0-冻结子账户
    public static final String AC_SUBTYPE_FROZEN = "00";
    //帐户支付类型：00-现金担保支付 01-信用担保支付 02-现金即时支付 03-红包支付  04-信用线下支付 ""-未确定
    public static final String PAY_TYPE_CASH_GUARANTEE = "00";
    public static final String PAY_TYPE_CASH_NAME = "现金担保";
    public static final String PAY_TYPE_CREDIT = "01";
    public static final String PAY_TYPE_CREDIT_NAME = "信用担保";
    public static final String PAY_TYPE_CREDIT_NAME_NEW = "账期支付";
    public static final String PAY_TYPE_REALTIME = "02";
    public static final String PAY_TYPE_REALTIME_NAME = "现金实时";
    public static final String PAY_TYPE_BONUS = "03";
    public static final String PAY_TYPE_BONUS_NAME = "红包";
    public static final String PAY_TYPE_OFFLINE = "04";  
    public static final String PAY_TYPE_OFFLINE_NAME = "信用线下";
    public static final String PAY_TYPE_OFFLINE_NAME_NEW = "线下支付";
    public static final String PAY_TYPE_EMPTY = "";
    public static final String PAY_TYPE_EMPTY_NAME = "未知";
    
    //同意支付方式：00-正常支付，01-逾期强制支付
    public static final String PAY_METHOD_NORMAL = "00";
    public static final String PAY_METHOD_FORCE = "01";
    
    //JF平台帐户类型：01-资金账户 02-冻结账户 03-运营专用账户
    public static final String JF_AC_USETYPE_CASH = "01";
    public static final String JF_AC_USETYPE_PAYMENT = "02";
    public static final String JF_AC_USETYPE_OPERATION = "03";
    public static final String JF_AC_USETYPE_CASH_NAME = "资金账户";
    public static final String JF_AC_USETYPE_PAYMENT_NAME = "冻结账户";
    public static final String JF_AC_USETYPE_OPERATION_NAME = "运营账户";
    
    //平台账户的JFID
    public static final String JF_ACCOUNT_JFID = "-1";
    
    //平台资金变动流水:交易状态
    //平台资金账户变动交易状态交易状态********************************************************************************
    public static final String JF_CHANGE_STATE_DOING="01";//处理中
    public static final String JF_CHANGE_STATE_SUCCESS="02";//交易成功
    public static final String JF_CHANGE_STATE_FAIL="03";//交易失败
    
    //平台资金变动流水:交易类别
    //平台账户交易类别（01 冻结款 02 代收款 03 收取D平台服务费 04 收取B平台服务费 05 转账 06 充值 07 提现 08 代扣个人所得税  09 红包回收 ）*******************************************
    public static final String JF_TR_PAY ="01";             //冻结款
    public static final String JF_TR_COLLECT ="02"; 		//代收款
    public static final String JF_TR_FEE_D ="03";     		//收取D平台服务费
    public static final String JF_TR_FEE_B ="04";     		//收取B平台服务费
    public static final String JF_TR_TRANSFER ="05";   		//内部转账
    public static final String JF_TR_RECHARGE ="06"; 		//平台账户充值
    public static final String JF_TR_WIDTHDRAW ="07"; 		//平台账户提现
    public static final String JF_TR_INCOME_TAX ="08"; 		//代扣个人所得税 
    public static final String JF_TR_BONUS_REGAIN ="09";	//红包回收
    
    
    //平台账户资金变更类型 类别（01内部结算，02订单交易，03红包交易，99其它）*******************************************
    public static final String JF_AMOUNT_CHANGE_INSIDE ="01";     //内部结算
    public static final String JF_AMOUNT_CHANGE_ORDRES ="02"; 	  //订单交易
    public static final String JF_AMOUNT_CHANGE_BONUS="03";       //红包交易
    public static final String JF_AMOUNT_CHANGE_OTHERS ="99";     //其它

    //帐户状态：-1 注销 00-未生效 01-生效 02-冻结 03-异常
    public static final String AC_STATE_CANCEL = "-1";
    public static final String AC_STATE_INVALID = "00";
    public static final String AC_STATE_VALID = "01";
    public static final String AC_STATE_FREEZE = "02";
    public static final String AC_STATE_ABNORMAL = "03";
    
    //账户冻结操作：账户解冻-00 账户冻结-01
    public static final String AC_EDIT_FREEZE = "01";
    public static final String AC_EDIT_UNFREEZE = "00";
    
//    //帐户资金状态：00-实时冻结 01-逻辑冻结 02-逻辑解冻 03-实时解冻
    public static final String AC_FUND_STATE_REALTIME = "00";
    public static final String AC_FUND_STATE_LOGICAL = "01";
    public static final String AC_FUND_STATE_UNLOGICAL = "02";
    public static final String AC_FUND_STATE_UNREALTIME = "03";
    //帐户资金状态：01-资金冻结 02-资金解冻
    public static final String AC_FUND_STATE_FREEZE = "01";
    public static final String AC_FUND_STATE_UNFREEZE = "02";
    
    //资金冻结|解冻原因代码 
    //01-第一次签单冻结订单金额 
    //02-续签订单时增加冻结金额 
    //03-同意支付时超出原订单金额增加冻结金额
    //04-支付订单解冻订单金额
    //05-订单结束释放剩余冻结金额
    //06-订单取消释放剩余冻结金额
    public static final String AC_FUND_FREEZE_RCODE_INIT = "01";
    public static final String AC_FUND_FREEZE_RCODE_RENEW = "02";
    public static final String AC_FUND_FREEZE_RCODE_EXCEED = "03";
    public static final String AC_FUND_FREEZE_RCODE_CLOSE = "04";
    public static final String AC_FUND_FREEZE_RCODE_RELEASE = "05";
    public static final String AC_FUND_FREEZE_RCODE_CANCLE = "06";
    
    //交易模式：00-个人2个人，01-个人2企业，10-企业2个人，11-企业2企业，20平台2个人，21平台2企业
    public static final String TRANS_MODE_C2C = "00";
    public static final String TRANS_MODE_C2B = "01";
    public static final String TRANS_MODE_B2C = "10";
    public static final String TRANS_MODE_B2B = "11";
    public static final String TRANS_MODE_P2C = "20";
    public static final String TRANS_MODE_P2B = "21";
    
    //银行卡绑定：用户类型 0-个人用户 1-企业用户 3-JF平台
    public static final String BANK_USERTYPE_PER = "0";
    public static final String BANK_USERTYPE_ENT = "1";
    public static final String BANK_USERTYPE_JF = "3";
    
    //批量转账入批次表状态 0-全部失败,1-部分成功,2-成功
    public static final String MASS_TRANS_STATE_SUCCESS = "2";
    public static final String MASS_TRANS_STATE_PART_OF_SUCCESS = "1";
    public static final String MASS_TRANS_STATE_ALL_FAIL = "0";
    
    //批量转账返回码 00-成功,01-失败,50-NULL,60-部分成功,61-全部失败
    public static final String BANK_RETURN_CODE_SUCCESS = "00";
    public static final String BANK_RETURN_CODE_PART_OF_SUCCESS = "60";
    public static final String BANK_RETURN_CODE_ALL_FAIL = "61";
    public static final String BANK_RETURN_CODE_FALIURE = "01";
    public static final String BANK_RETURN_CODE_NULL = "50";
    
    //***账单相关**************************************************************************************/
    //订单支付记账流水状态:00-未出账，01-已出账，02-不记账
    public static final String TR_BILL_UNSETTLED = "00";
    public static final String TR_BILL_SETTLED = "01";
    public static final String TR_NOBILL = "02";
    
    //交流流水订单支付状态  支付状态   00：待支付；01：B已支付；02：对D已支付
    public static final String JF_TRANSACTION_PAY_STATE_WAITING_PAY = "00";
    public static final String JF_TRANSACTION_PAY_STATE_B_PAY = "01";
    public static final String JF_TRANSACTION_PAY_STATE_D_PAY = "02";
    
    //帐单支付状态: 00-未支付 01-支付成功 02-支付失败 03-逾期未支付 04-支付处理中 05-部分支付成功
    //补充发包方子账户支付失败编码:08-发包方子账户支付失败
    public static final String BILL_PAY_NO = "00";
    public static final String BILL_PAY_NO_NAME = "未支付";
    public static final String BILL_PAY_SUCCESS = "01";
    public static final String BILL_PAY_SUCCESS_NAME = "支付成功";
    public static final String BILL_PAY_FAILED = "02";
    public static final String BILL_PAY_FAILED_NAME = "支付失败";
    public static final String BILL_PAY_OVERDUE = "03";
    public static final String BILL_PAY_OVERDUE_NAME = "逾期未支付";
    public static final String BILL_PAY_PROCESSING = "04";
    public static final String BILL_PAY_PROCESSING_NAME = "支付处理中";
    public static final String BILL_PAY_PART_OF_SUCCESS = "05";
    public static final String BILL_PAY_PART_OF_SUCCESS_NAME = "部分支付成功";
    public static final String BILL_PAY_EXPIREDATE = "06";
    public static final String BILL_PAY_EXPIREDATE_NAME = "支付过期";
    public static final String BILL_PAY_REGAINED = "07";
    public static final String BILL_PAY_REGAINED_NAME = "红包金额已回收";

    // 1-修改支付密码，2-修改绑定手机号, 3-修改支付模式, 4-修改账户状态
    public static final String AC_EDIT_PAYPASS = "1";
    public static final String AC_EDIT_MOBILE = "2";
    public static final String AC_EDIT_PAYTYPE = "3";
    public static final String AC_EDIT_STATE = "4";
    
    //区分账单费用：00：账单费用；01：账单服务费
    public static final String FEE_TYPE_ACCOUNT = "00";
    public static final String FEE_TYPE_SERVICE = "01";
    
    //EBC回调地址
    //绑定银行卡 异步回调URL
    public static final String JF_PAY_BINDCARD_CALLBACK_URL = UrlParserUtil.parseUrl("${jfpay}/api/ebc/bindCard/bindResult");
    //批量转账回调地址
    public static final String JF_PAY_MASS_TRANSFER_CALLBACK_URL=UrlParserUtil.parseUrl("${jfpay}/api/ebc/transcation/masstransfer/backresult");
    //充值回调——前台
    public static final String JF_PAY_DEPOSIT_CALLBACK_FROUNT_URL=UrlParserUtil.parseUrl("${jfpay}/api/ebc/transcation/deposit/result");
    //充值后台回调地址
    public static final String JF_PAY_DEPOSIT_CALLBACK_BACK_URL=UrlParserUtil.parseUrl("${jfpay}/api/ebc/transcation/deposit/backresult");
    //平台账户充值后台回调接口
    public static final String JF_PAY_JF_ACCOUNT_DEPOSIT_BACK_URL=UrlParserUtil.parseUrl("${jfpay}/api/ebc/jfTranscation/jfDeposit/result");

    public static final String JF_PAY_DEFAULT_BANK_IMG = "none.gif";
    
    //是否是默认银行卡-0-否,1-是
    public static final String ISNOT_DEFAULT_BANKCARD = "0";
    public static final String IS_DEFAULT_BANKCARD = "1";
    
    //账户开户/变更流水-处理结果：0-失败 1-成功
    public static final String LOG_HANDLE_RESULT_SUCCESS = "1";
    public static final String LOG_HANDLE_RESULT_FAILURE = "0";
    
    //交易操作类型定义，可以跟记录的流水类型挂钩
    public static final String TC_ACCOUNT_CREATE = "00";//开销户
    public static final String TC_ACCOUNT_EDIT = "01";//修改户
    public static final String TC_ACCOUNT_LOCK = "02";//锁定解锁账户
    public static final String TC_RECHARGE = "03";//充值
    public static final String TC_WITHDRAW = "04";//提现
    public static final String TC_TRANSFER = "05";//转账
    public static final String TC_TRANSFER_BATCH = "06";//批量转账
    public static final String TC_DRAWBACK = "07";//退款
    public static final String TC_PAY_CASH = "08";//消费-余额支付D
    public static final String TC_ORDER = "09";//签单
    public static final String TC_FROZEN = "10";//冻结解冻资金
    public static final String TC_PAY_AGREE = "11";//同意支付
    public static final String TC_PAY_BANKLIST = "12";//获取银行卡列表
    public static final String TC_READ_BALANCE = "13";//读取账户余额
    public static final String TC_WITHDRAW_LIMIT="14";//修改提现最小值
    public static final String TC_PAY_BINDCARD = "15";//绑定银行卡
    public static final String TC_PAY_PER_INCOME="16";//D收入流水
    public static final String TC_PAY_TRANSFER_BATCH_SN="17";//对D支付生成批次号
    /**
     * modify by zhangchengjun at 2015.01.16 -start( old : 18-21)
     */
    public static final String TC_PAY_D2JFZJ_SN="30";//D对平台资金账户
    public static final String TC_QUERY_WITHDRAW_STATUS="31";//查询提现状态
    public static final String TC_PAY_AUTOMOBILE = "32";// 获取手机验证码
    public static final String TC_RESET_PAYPASS_WITHOUT_OLD="33";//用于用户找回密码调用接口
    /**
     * modify by zhangchengjun at 2015.01.16 -end
     */
    //转账，批量转账状态_ 02成功 03失败
    public static final String TRANSFER_STATE_SUCCESS = "02";
    public static final String TRANSFER_STATE_FAIL = "03";
    
    //个人支付流水类型  01 平台冻结给D 02 D付给平台服务费 03 D支付平台服务费 04个人转账
    public static final String PERSON_PAY_INCOME="01";
    public static final String PERSON_PAY_OUTCOME="02";
    public static final String PERSON_PAY_EXPENDITURE="03";
    public static final String PERSON_PAY_TRANSFER="04";
    
    //企业支付流水类型  02 B支付给平台冻结账户 03 B支付平台服务费
    public static final String PERSON_PAY_INCOME_B="02";
    public static final String PERSON_PAY_EXPENDITURE_B="03";
    
    //获取联行号 queryType 查询类型,1.总行 2.省 3.市 4.支行
    public static final String BANK_QUERY_TYPE_HEADQUARTERS ="1";
    public static final String BANK_QUERY_TYPE_PROVINCE ="2";
    public static final String BANK_QUERY_TYPE_CITY="3";
    public static final String BANK_QUERY_TYPE_BRANCH="4";
   
    //充值状态 01-支付中 02-成功  03-失败 04-失效已处理
    public static final String RECHARGEING="01";
    public static final String RECHARGE_SUCCESS="02";
    public static final String RECHARGE_FAIL="03";
    public static final String RECHARGE_LOSE_EFFICACY="04";
    
    /**
     * 充值类型
     */
    public static final String JF_PAY_RECHARGE_TYPE_Ebank = "00";
    
    
    public static final String ENV_ACCOUNT_TYPE = "account.type";
    public static final String ENV_ACCOUNT_SUFFIX = "account.suffix";
   
    //结果通知
    public static final String NOTIFICATIONS_RECHARGE="0";//充值
    public static final String NOTIFICATIONS_TRANSFERACCOUNT="0";//转账
    
    //提现状态
    public static final String WITHDRAW_DOING="00";//处理中
    public static final String WITHDRAW_SUCCESS="01";//成功
    public static final String WITHDRAW_FAIL="02";//失败
    public static final String WITHDRAW_OTHER="03";//其它
    
    //信用额度设为0
    public static final String JF_PAY_CREDIT_ZERO_STRING = "0.00";
    
    //EBC 接口返回数据名称
    /**
     * 调用EBC绑定银行卡接口返回数据-返回码
     */
    public static final String EBC_RECEIVE_REQUEST_RETURNCODE = "returncode";
    
    /**
     * 调用EBC绑定银行卡接口返回数据-钱包介质ID
     */
    public static final String EBC_RECEIVE_REQUEST_MEDIUMNO = "mediumno";
    
    /**
     * 调用EBC绑定银行卡接口返回数据-错误信息
     */
    public static final String EBC_RECEIVE_REQUEST_ERRORTEXT = "errtext";
    
    //EBC收到请求接口请求返回的状态 
    public static final String EBC_RECEIVE_REQUEST_SUCCESS = "00";//请求成功
    public static final String EBC_RECEIVE_REQUEST_FAIL="99";//请求失败
    
    public static final String EBC_RECEIVE_REGISTER_MOBILE_EXIST="18";//开户失败，手机号已被绑定
    
    public static final String EBC_RECEIVE_BALANCE_NOT_ENOUGH="27";//请求失败——余额不足
    
    public static final String EBC_RECEIVE_BINDCARD_HANDLING = "34";//绑定银行卡 状态 --处理中
    public static final String EBC_RECEIVE_BINDCARD_DIFFNAME = "32";//绑定银行卡 状态 --持卡人姓名与钱包姓名不一致
    public static final String EBC_RECEIVE_BINDCARD_NOTAUTH = "33";	//绑定银行卡 状态 --银行卡未认证
    public static final String EBC_RECEIVE_BINDCARD_BANKERROR = "16";	  //绑定银行卡 状态 --银行验证失败
    public static final String EBC_RECEIVE_BINDCARD_HADBIND = "03";	  //绑定银行卡 状态 --此卡已被绑定
    
    public static final String EBC_RECEIVE_BINDCARD_REHANDLING = "35";	  //绑定银行卡 状态 --此卡已申请绑定状态为绑定处理中(此状态码是jf支付自定义状态码，不是银盈通定义的状态码)
    
    public static final String EBC_RECHARGE_BACK_PER = "0";   //充值渠道-个人网银
    public static final String EBC_RECHARGE_BACK_ENT = "1";   //充值渠道-企业网银
    public static final String EBC_RECHARGE_BACK_REMIT = "2";   //充值渠道-电汇充值
    
    public static final String EBC_RECHARGE_FLAG_REMIT = "0";//交易类型  0 电汇， 1 现金充值，2 pos充值， 3 支票 ，4 其他
    
    public static final String EBC_CARD_T_DEBIT = "01";    //充值-个人-借记卡（储蓄卡）
    public static final String EBC_CARD_T_CREDIT = "02";   //充值-个人-货记卡（信用卡）
    
    //EBC回调接口类型 -
    public static final String NOTIFY_TYPE_RECHARGE="0";//充值回调
    public static final String NOTIFY_TYPE_TRANSFER="1";//转账回调
    public static final String NOTIFY_TYPE_BINDCARD="2";//绑定银行卡
    public static final String NOTIFY_TYPE_MASSTRANSFER="3";//批量转账回调
    
    //绑定银行卡 自定义的处理结果 ******************************************************
    public static final String JF_PAY_BINDCARD_INHAND = "00";//处理中
    public static final String JF_PAY_BINDCARD_SUCCESS = "01";//成功
    public static final String JF_PAY_BINDCARD_FAILED = "02";//失败
    
    //BU信息审核状态
    public static final String JF_PAY_BU_BEBLANK = "0";//待完善
    public static final String JF_PAY_BU_AUDITING = "1";//审核中
    public static final String JF_PAY_BU_AUDITED = "2";//审核通过
    
    //企业支付账单 状态 
    public static final String ENT_PAY_SUCCESS = "01";//成功
    public static final String ENT_PAY_FAILED = "02";//失败
    
    //账单明细 账单是否已经支付
    public static final String BILL_DETAIL_UNPAYED = "00";//未支付
    public static final String BILL_DETAIL_PAYED = "01";//已支付
    
    //企业支付流水******************************************************
    public static final String ENT_TRANS_ORDER_COST = "01" ;//平台代收款
    public static final String ENT_TRANS_FEE_COST = "02" ;//服务费
    
    
    
    public static final String JF_OPEN_ACCOUNT_INIT_PWD = "113355";//重新开户初始化密码

    //2015-04-23 JFPAY-348新增订单新旧类型  此次上线之前的订单为旧订单 上线之后的订单的新订单
    public static final String JF_ORDER_IS_OLD = "0";//表示旧订单
    public static final String JF_ORDER_IS_NEW = "1";//表示新订单
    public static final String JF_ORDER_IS_NOT_EXIST = "2";//表示订单不存在
    
    //所发包中新旧订单标示
    public static final String JF_ORDER_ONLY_OLD = "00";//只有旧订单
    public static final String JF_ORDER_ONLY_NEW = "01";//只有新订单
    public static final String JF_ORDER_NEW_AND_OLD = "02";//包含新旧订单
    public static final String JF_ORDER_NONE = "03";//无订单
    
    //支付状态
    public static final String JF_PAY_STATUS_RES = "-1";//拒绝支付 
    public static final String JF_PAY_STATUS_APP = "0";//发起支付申请
    public static final String JF_PAY_STATUS_AGR = "1";//同意支付
    public static final String JF_PAY_STATUS_PAY = "2";//已经支付
    
    //付款方式 =1：分阶段支付;!= 1：一次性支付 
    public static final String JF_PAY_METHOD_MUL = "1";//阶段性支付 
    public static final String JF_PAY_METHOD_ONE = "0";//一次性支付
    
    //子账户转账类型01：收入02：支出
    public static final String SUB_ACCOUNT_TRANS_TYPE_INCOME = "01";
    public static final String SUB_ACCOUNT_TRANS_TYPE_EXPENSE = "02";
    
    // 从EBC查询账户余额出错   出错余额显示方式
    public static final String ACCOUNT_BALANCE_ERROR_STR = "--";
    
    // 特殊金额值: 从EBC查询账户余额出错  但不影响事物提交时 金额显示为-1 便于排查
    public static final BigDecimal JF_SPECIAL_AMOUNT = new BigDecimal(-1);
    
    public static final int EBC_ENTERNAME_LENGTH = Integer.valueOf(32);// EBC开户enterName长度
    
    public static final String JF_PAY_CONSTANT_MERCHNO = "merchno";
    
    public static final String JF_PAY_CONSTANT_APPLICATION = "application/json";
    
    /**
     * 4.12 交易类型
     */
    private static final Map<String, String> dealMap = new HashMap<String, String>();
    /**
     * 交易状态
     */
    private static final Map<String, String> dealStateMap = new HashMap<String, String>();
    
    private JfConstants(){
    }
    
    static {
        dealMap.put("00", "消费");
        dealMap.put("01", "充值");
        dealMap.put("02", "转出");
        dealMap.put("03", "转入");
        dealMap.put("04", "提现");
        dealMap.put("05", "手续费订单");
        dealMap.put("06", "银行卡验证订单");
        dealMap.put("07", "清算充值订单");
        dealMap.put("08", "消费充值");
        dealMap.put("09", "API充值");
        dealMap.put("10", "外币消费充值");
        dealMap.put("11", "外币消费");
        dealMap.put("12", "电汇消费充值");
        dealMap.put("13", "电汇消费");
        dealMap.put("14", "线下消费");
        dealMap.put("15", "外卡充值");
        dealMap.put("16", "线下消费");
        dealMap.put("17", "电汇充值");
        dealMap.put("18", "退货");
        dealMap.put("19", "手续费充值");
        dealMap.put("20", "提现撤消充值");
        dealMap.put("21", "充值申购");
        dealMap.put("22", "转账申购");
        dealMap.put("23", "转账赎回");
        dealMap.put("24", "提现赎回");
        dealMap.put("25", "盈余宝收益充值");
    }
    /**
     * 根据交易类型编码得到交易类型名称
     * 
     * @param dealKey
     * @return
     * @author FengHaibing
     */
    public static String getDealType(String dealKey) {
        if(null == dealKey || "".equals(dealKey.trim())) {
            return "";
        }
        String dealVal = dealMap.get(dealKey);
        if( StringUtils.isNullStr(dealVal) ) {
            return "";
        }
        return dealVal;
    }
    
    static {
        dealStateMap.put("00", "成功");
        dealStateMap.put("01", "未处理");
        dealStateMap.put("02", "失败");
        dealStateMap.put("03", "异常");
        dealStateMap.put("04", "财务已经处理提交银行（银行未处理）");  //财务已经处理提交银行（银行未处理）
    }
    /**
     * 根据交易状态编码得到交易状态名称
     * 
     * @param dealKey
     * @return
     * @author FengHaibing
     */
    public static String getDealState(String dealKey) {
        if(null == dealKey || "".equals(dealKey.trim())) return "";
        
        String dealVal = dealStateMap.get(dealKey);
        if( StringUtils.isNullStr(dealVal) ) return "";
        
        return dealVal;
    }
    
    /**
     * 根据环境不同,得到不同的后缀,用区别在开户时的姓名,身份证号
     * @return
     */
    public static String getAccountSuffix() {
        String suffix = JFPayConfigFactory.getJfSettings().getAccountSuffix();
        if(Boolean.valueOf(suffix)) {
            return "_"+JFPayConfigFactory.getJfSettings().getAccountType();
        }
        return "";
    }
    
    /**
     * 杰简历访问路径
     */
    public static String getJfProfileUrl(){
        return UrlParserUtil.parseUrl("${jfprofile}/profile/show/");
    }
    
    /**
     * 人员头像获取路径
     */
    public static String getJfPersonPhotoUrl(){
        return UrlParserUtil.parseUrl("${jfprofile}/resume/pic/");
    }
    /**
     * 查看订单详情 访问路径
     *  需要传一个加密的 oid  加密方法 IdSecurity.encode(Long orderNo);
     *  例 ： https://dev.jointforce.com/jftrade/project/order/orderDetail?oid=MzE3OzQ5
     */
    public static String getJfOrderDetailsUrl(){
        return UrlParserUtil.parseUrl("${jftrade}/project/order/orderDetail");
    }
    
    /**
     * 通过orderNo查看订单详情链接
     * ${jftrade}/order/item/detail?item=E12323 
     */
    public static String getJfOrderDetailsUrlNo(){
        return UrlParserUtil.parseUrl("${jftrade}/order/item/detail");
    }
    
    /**
     * 订单支付结果通知URL
     */
    public static String getJfOrderPayResultNotifyUrl(){
        return UrlParserUtil.parseUrl("${jftrade}/orderapi/finish/pay/notice");
    }
    
    /**
     * 实时支付新订单签单结果通知URL
     */
    public static String getJfOrderCreateResultNotifyUrl(){
        return UrlParserUtil.parseUrl("${jftrade}/orderapi/finish/cashfreeze/notice");
    }
    
    
    // 收取发包/接包方的最低、最高费率 0.00-0.20
    public static final BigDecimal JF_PAY_LOWEST_FEE_RATE  = new BigDecimal("0.00");//最低费率
    public static final BigDecimal JF_PAY_HIGHEST_FEE_RATE  = new BigDecimal("0.20");//最高费率
    
    // 费率为零
    public static final BigDecimal JF_PAY_FEE_RATE_ZERO  = new BigDecimal("0.00");//费率为零
    
    // 收取发包/接包方服务费率
    public static final String JF_PAY_D_FEE_RATE = "0";//接包方费率
    public static final String JF_PAY_B_FEE_RATE = "1";//发包方费率

    
    public static final int EBC_SUBORDERID_LENGTH = Integer.valueOf(32);// 电商单笔转账订单ID长度
    
    public static final String JF_PAY_OPEN_API_DATA_DECODE = "1";// 对外提供的API 参数data需转码的标识
    
    //认证状态：0 未审核 1 审核通过 2 审核不通过
    public static final String JFPAY_CER_STATUS_NO_ADULT = "0";//未审核
    public static final String JFPAY_CER_STATUS_ADULT_PASS = "1";//审核通过
    public static final String JFPAY_CER_STATUS_UN_PASS = "2";//审核不通过
    
    public static final String JFPAY_CER_STATUS_HAS_BLANK = "3";//资料不完善
}

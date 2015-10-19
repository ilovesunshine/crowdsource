/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * @Title: JfConstants.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-8-5 下午5:11:50
 * @author admin
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class Monitor {

	private Monitor(){}

	public static final String UTF8 = "utf-8";

	//*********************************状态************************************
	/**
	 * 交易状态
	 */
	private static final Map<String, String> dealStateMap = new HashMap<String, String>();

	static {
		dealStateMap.put("00", "2");
		dealStateMap.put("01", "1");
		dealStateMap.put("02", "0");
		dealStateMap.put("03", "2");
	}

	/**
	 * 置换交易状态
	 * 
	 * @param dealKey
	 * @return
	 */
	public static String getDealState(String dealKey) {
		if(null == dealKey || "".equals(dealKey.trim())) return "";

		String dealVal = dealStateMap.get(dealKey);
		if( StringUtils.isBlank(dealVal) ) return "";

		return dealVal;
	}
	//*********************************状态************************************

	//*********************************交易************************************
	/**
	 * 交易
	 */
	private static final Map<String, String> actionMap = new HashMap<String, String>();

	static {
		//操作
		actionMap.put("企业开通资金账户", "51");
		actionMap.put("个人开通资金账户", "51");
		actionMap.put("账户充值", "53");
		actionMap.put("账户提现", "55");
		actionMap.put("绑定银行卡", "57");
		actionMap.put("企业支付账单", "59");
		//接口
		actionMap.put("开户接口", "01");
		actionMap.put("修改账户信息接口", "02");
		actionMap.put("添加电子账户接口", "03");
		actionMap.put("获取验证码接口", "04");
		actionMap.put("获取充值银行列表接口", "05");
		actionMap.put("充值接口", "06");
		actionMap.put("充值结果通知", "07");
		actionMap.put("余额支付", "08");
		actionMap.put("转账请求", "09");
		actionMap.put("批量转账", "10");
		actionMap.put("转账结果通知", "11");
		actionMap.put("绑定银行卡接口", "12");
		actionMap.put("修改绑定银行卡接口", "13");
		actionMap.put("提现接口", "14");
		actionMap.put("查询余额接口", "15");
		actionMap.put("获取联行号接口", "16");
		actionMap.put("绑定银行卡结果通知", "17");
		actionMap.put("查询提现状态接口", "18");
		actionMap.put("支付密码修改", "19");
		
		actionMap.put("验证能否签单", "60");
		actionMap.put("验证订单是否为新订单", "61");
		actionMap.put("订单资金托管-信用|线下", "62");
		actionMap.put("订单资金托管-实时", "63");
		actionMap.put("订单同意支付-信用|线下", "64");
		actionMap.put("订单同意支付-实时", "65");
		actionMap.put("订单记账", "66");
		actionMap.put("企业支付账单", "67");
		actionMap.put("订单结束", "68");
		actionMap.put("订单取消", "69");
		actionMap.put("下载并处理交易日志文件", "90"); 
		actionMap.put("账单逾期支付检测", "91"); 
		actionMap.put("生成账单", "92"); 
		actionMap.put("支付系统监控", "93"); 
	}

	/**
	 * 获取交易码
	 * @param name
	 * @return dealVal
	 */
	public static String getActionCode(String name) {
		if(null == name || "".equals(name.trim())) {
			return "";
		}

		String dealVal = actionMap.get(name);
		if( StringUtils.isBlank(dealVal) ) {
			return "";
		}

		return dealVal;
	}

	//*********************************交易************************************

	//*********************************模块************************************

	public static final String OBJ_PAY_ENT = "企业账户";
	public static final String OBJ_PAY_PER = "个人账户";
	public static final String OBJ_PAY_API = "接口";
	public static final String OBJ_PAY_OPT = "支付管理";
	public static final String OBJ_PAY_SYS = "系统管理";
	public static final String OBJ_PAY_JOBS = "定时任务";

	/**
	 * 模块
	 */
	private static final Map<String, String> modelMap = new HashMap<String, String>();

	static {
		modelMap.put("企业账户", "0");
		modelMap.put("个人账户", "0");
		modelMap.put("支付管理", "0");
		modelMap.put("系统管理", "0");
		modelMap.put("接口", "1");
		modelMap.put("定时任务", "2");
	}

	public static final String SYS_MONI_MODEL_OPT_CODE = "0";
	public static final String SYS_MONI_MODEL_OPT_NAME = "操作";
	public static final String SYS_MONI_MODEL_API_CODE = "1";
	public static final String SYS_MONI_MODEL_API_NAME = "接口";
	public static final String SYS_MONI_MODEL_SER_CODE = "2";
	public static final String SYS_MONI_MODEL_SER_NAME = "服务";

	/**
	 * 获取模块码
	 * @param name
	 * @return dealVal
	 */
	public static String getModelCode(String name) {
		if(null == name || "".equals(name.trim())) {
			return "";
		}

		String dealVal = modelMap.get(name);
		if( StringUtils.isBlank(dealVal) ) {
			return "";
		}

		return dealVal;
	}
	//*********************************模块************************************

	//操作指标
	private static final Map<String, String> itemOptMap = new HashMap<String, String>();
	static {
		itemOptMap.put("51", "开户");
		itemOptMap.put("53", "充值");
		itemOptMap.put("55", "提现");
		itemOptMap.put("57", "绑定银行卡");
		itemOptMap.put("59", "账单支付");
	}
	/**
	 * 操作指标
	 */
	public static Map<String, String> itemOptMap() {

		return itemOptMap;
	}

	//接口指标
	private static final Map<String, String> itemIfaceMap = new HashMap<String, String>();
	static {
		itemIfaceMap.put("01", "开户接口");
		itemIfaceMap.put("02", "修改账户信息接口");
		itemIfaceMap.put("03", "添加电子账户接口");
		itemIfaceMap.put("04", "获取验证码接口");
		itemIfaceMap.put("05", "获取充值银行列表接口");
		itemIfaceMap.put("06", "充值接口");
		itemIfaceMap.put("07", "充值结果通知");
		itemIfaceMap.put("08", "余额支付");
		itemIfaceMap.put("09", "转账请求");
		itemIfaceMap.put("10", "批量转账");
		itemIfaceMap.put("11", "转账结果通知");
		itemIfaceMap.put("12", "绑定银行卡接口");
		itemIfaceMap.put("13", "修改绑定银行卡接口");
		itemIfaceMap.put("14", "提现接口");
		itemIfaceMap.put("15", "查询余额接口");
		itemIfaceMap.put("16", "获取联行号接口");
		itemIfaceMap.put("17", "绑定银行卡结果通知");
		itemIfaceMap.put("18", "查询提现状态接口");
		itemIfaceMap.put("19", "支付密码修改");
		
		itemIfaceMap.put("60", "验证能否签单");
		itemIfaceMap.put("61", "验证订单是否为新订单");
		itemIfaceMap.put("62", "订单资金托管-信用|线下");
		itemIfaceMap.put("63", "订单资金托管-实时");
		itemIfaceMap.put("64", "订单同意支付-信用|线下");
		itemIfaceMap.put("65", "订单同意支付-实时");
		itemIfaceMap.put("66", "订单记账");
		itemIfaceMap.put("67", "企业支付账单");
		itemIfaceMap.put("68", "订单结束");
		itemIfaceMap.put("69", "订单取消");
	}
	/**
	 * 接口指标
	 */
	public static Map<String, String> itemIfaceMap() {

		return itemIfaceMap;
	}

	//服务指标
	private static final Map<String, String> itemServiceMap = new HashMap<String, String>();
	static {
		itemServiceMap.put("90", "下载并处理交易日志文件");
		itemServiceMap.put("91", "账单逾期支付检测");
		itemServiceMap.put("92", "生成账单");
		itemServiceMap.put("93", "支付系统监控");
	}
	/**
	 * 服务指标
	 */
	public static Map<String, String> itemServiceMap() {

		return itemServiceMap;
	}
}

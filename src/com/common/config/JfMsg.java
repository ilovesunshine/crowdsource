/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.config;

/**
 * @Title: JfMsg.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-30 上午12:15:34
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfMsg {
    
    private JfMsg(){
    }
	
	//消息类型：01-账户，02-交易，03-账单，04-参数
	public static final String T_ACCOUNT = "01";
	public static final String T_ORDER = "02";
	public static final String T_BILL = "03";
	public static final String T_CONFIG = "04";

	//0-未处理，1-已处理，2-忽略，3-挂起
	public static final String S_UNDO = "0";
	public static final String S_DONE = "1";
	public static final String S_IGNORE = "2";
	public static final String S_SUSPEND = "3";
}

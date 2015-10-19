/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: JfUuidUtil.java
 * @Description: UUID TOOL
 * @Company: crowdsource
 * @Created on 2015-4-2 下午7:17:59
 * @author ouyw
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfUuidUtil {
	private static final Logger LOG = LoggerFactory.getLogger(JfUuidUtil.class);
	
	private JfUuidUtil(){
	}

	/**
	 * 获取UUID
	 * @return 普通36位UUID
	 */
	public static String get36Uuid(){
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	
	/**
	 * 获取UUID
	 * @return 去掉“-”的32位UUID
	 */
	public static String get32Uuid(){
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}
	
	public static void main(String[] args) {
		JfLog.info(LOG,JfUuidUtil.get36Uuid());
		JfLog.info(LOG,JfUuidUtil.get32Uuid());
	}
}

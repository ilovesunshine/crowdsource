/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import com.common.vo.JfLoginUser;
import com.common.vo.PayAdmins;

/**
 * @Title: ModelUtils.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-8-19 上午11:01:07
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class ModelUtils {

	/**
	 * ModelUtils
	 */
	private ModelUtils() {
	}

	/**
	 * Transform PayAdmins to LoginUser
	 * @param pa
	 * @return
	 */
	public static JfLoginUser transform2LoginUser(PayAdmins pa){
		JfLoginUser user = new JfLoginUser();
		if(pa!=null){
			user.setJfid(pa.getJfid());
			user.setUserName(pa.getUserName());
			user.setUserRole(pa.getUserRole());
		}
		return user;
	}
}

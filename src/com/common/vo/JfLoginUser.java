/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.vo;


/**
 * @Title: JfLoginUser.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-8-19 上午10:57:12
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfLoginUser implements java.io.Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7143608283954472593L;

	// Fields    
	private Long jfid;
	private String userRole;
	private String userName;
	/**
	 * @return Returns the jfid.
	 */
	public Long getJfid() {
		return jfid;
	}
	/**
	 * @param jfid The jfid to set.
	 */
	public void setJfid(Long jfid) {
		this.jfid = jfid;
	}
	/**
	 * @return Returns the userRole.
	 */
	public String getUserRole() {
		return userRole;
	}
	/**
	 * @param userRole The userRole to set.
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}

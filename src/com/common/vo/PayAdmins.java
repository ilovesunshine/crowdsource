/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.vo;

import java.sql.Timestamp;

import com.common.dao.Model;

/**
 * 
 * @Title: PayAdmins.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-1 下午1:45:18
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class PayAdmins implements java.io.Serializable,Model{
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7791933140436810207L;
    // Fields    
	private Long saId; 
	private Long jfid; 
	private String userRole; 
	private String userName; 
	private String adminPass; 
	private String userStatus; 
	private Timestamp lastLoginTime; 
	private Timestamp createTime; 
	private Timestamp updateTime; 
	private String deleteFlag; 
		
	//default constructor
    public PayAdmins() {
    	super();
    }
    
    /**
     * @param pid
     * @param pwd
     */
    public PayAdmins(String userName, String adminPass) {
        this.userName = userName;
        this.adminPass = adminPass;
    }

    // Property accessors
	public Long getSaId() {
        return this.saId;
    }
    
    public void setSaId(Long saId) {
    	this.saId = saId;
    }
    
	public Long getJfid() {
        return this.jfid;
    }
    
    public void setJfid(Long jfid) {
    	this.jfid = jfid;
    }
    
	public String getUserRole() {
        return this.userRole;
    }
    
    public void setUserRole(String userRole) {
    	this.userRole = userRole;
    }
    
	public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
    	this.userName = userName;
    }
    
    public String getAdminPass() {
        return this.adminPass;
    }
    
    public void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }
    
	public String getUserStatus() {
        return this.userStatus;
    }
    
    public void setUserStatus(String userStatus) {
    	this.userStatus = userStatus;
    }
    
	public Timestamp getLastLoginTime() {
	    if (this.lastLoginTime == null) {
            return null;
        }
        return (Timestamp) this.lastLoginTime.clone();
    }
    
    public void setLastLoginTime(Timestamp lastLoginTime) {
    	 if (lastLoginTime != null) {
             this.lastLoginTime = (Timestamp) lastLoginTime.clone();
         }
    }

    public Timestamp getCreateTime() {
        if (this.createTime == null) {
            return null;
        }
        return (Timestamp) this.createTime.clone();
    }

    public void setCreateTime(Timestamp createTime) {
        if (createTime != null) {
            this.createTime = (Timestamp) createTime.clone();
        }
    }

    public Timestamp getUpdateTime() {
        if (this.updateTime == null) {
            return null;
        }
        return (Timestamp) this.updateTime.clone();
    }

    public void setUpdateTime(Timestamp updateTime) {
        if (updateTime != null) {
            this.updateTime = (Timestamp) updateTime.clone();
        }
    }
    
	public String getDeleteFlag() {
        return this.deleteFlag;
    }
    
    public void setDeleteFlag(String deleteFlag) {
    	this.deleteFlag = deleteFlag;
    }
    
   	public boolean validate(Object arg0) {
		return true;
	}

}
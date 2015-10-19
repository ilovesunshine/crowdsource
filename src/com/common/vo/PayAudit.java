/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.vo;

import java.math.BigDecimal;

import com.common.dao.Model;
import com.common.utils.CommonUtils;
import com.sys.constants.JfCfg;

/**
 * 
 * @Title: PayAudit.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-1 下午1:41:18
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class PayAudit implements Model {
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2027424729757899807L;
    // Fields    
	private Integer actId; 
	private String actor; 
	private String userid; 
	private String personuuid; 
	private String orgname; 
	private String orguuid; 
	private String enrolledOrg; 
	private String enrolledOrguuid; 
	private Integer accounttype; 
	private Integer actResult; 
	private String actAction; 
	private String actCode; 
	private String actObj; 
	private BigDecimal logoutTime; 
	private BigDecimal loginid; 
	private String loginMode; 
	private BigDecimal actTime; 
	private String actItemCode; 
	private Integer actItemNum; 
	private Integer isclob; 
	private String actMessage; 
	private String actIp; 
	private String signature; 
	private String ext1; 
	private String ext2; 
	private String ext3; 
	private Integer ext4; 
	private Integer ext5; 
		
	//default constructor
    public PayAudit() {
    	super();
    }
    
    // Property accessors
	public Integer getActId() {
        return this.actId;
    }
    
    public void setActId(Integer actId) {
    	this.actId = actId;
    }
    
	public String getActor() {
        return this.actor;
    }
    
    public void setActor(String actor) {
    	this.actor = actor;
    }
    
	public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
    	this.userid = userid;
    }
    
	public String getPersonuuid() {
        return this.personuuid;
    }
    
    public void setPersonuuid(String personuuid) {
    	this.personuuid = personuuid;
    }
    
	public String getOrgname() {
        return this.orgname;
    }
    
    public void setOrgname(String orgname) {
    	this.orgname = orgname;
    }
    
	public String getOrguuid() {
        return this.orguuid;
    }
    
    public void setOrguuid(String orguuid) {
    	this.orguuid = orguuid;
    }
    
	public String getEnrolledOrg() {
        return this.enrolledOrg;
    }
    
    public void setEnrolledOrg(String enrolledOrg) {
    	this.enrolledOrg = enrolledOrg;
    }
    
	public String getEnrolledOrguuid() {
        return this.enrolledOrguuid;
    }
    
    public void setEnrolledOrguuid(String enrolledOrguuid) {
    	this.enrolledOrguuid = enrolledOrguuid;
    }
    
	public Integer getAccounttype() {
        return this.accounttype;
    }
    public String getAccountTypeName(){
    	String name = JfCfg.GuestType.DATAMAP.get(accounttype);
    	return CommonUtils.isNullOrEmpty(name)?
    			JfCfg.GuestType.TYPE_OTHER_NAME:name;
    }
    public void setAccounttype(Integer accounttype) {
    	this.accounttype = accounttype;
    }
    
    public String getActResultName(){
    	String name = JfCfg.ActionResult.DATAMAP.get(actResult);
    	return CommonUtils.isNullOrEmpty(name)?JfCfg.ActionResult.RESULT_OTHER_NAME:name;
    }
	public Integer getActResult() {
        return this.actResult;
    }
    
    public void setActResult(Integer actResult) {
    	this.actResult = actResult;
    }
    
	public String getActAction() {
        return this.actAction;
    }
    
    public void setActAction(String actAction) {
    	this.actAction = actAction;
    }
    
	public String getActCode() {
        return this.actCode;
    }
    
    public void setActCode(String actCode) {
    	this.actCode = actCode;
    }
    
	public String getActObj() {
        return this.actObj;
    }
    
    public void setActObj(String actObj) {
    	this.actObj = actObj;
    }
    
	public BigDecimal getLogoutTime() {
        return this.logoutTime;
    }
    
    public void setLogoutTime(BigDecimal logoutTime) {
    	this.logoutTime = logoutTime;
    }
    
	public BigDecimal getLoginid() {
        return this.loginid;
    }
    
    public void setLoginid(BigDecimal loginid) {
    	this.loginid = loginid;
    }
    
	public String getLoginMode() {
        return this.loginMode;
    }
    
    public void setLoginMode(String loginMode) {
    	this.loginMode = loginMode;
    }
    
	public BigDecimal getActTime() {
        return this.actTime;
    }
	public String getActTimeStr(){
		return CommonUtils.turnNumberToString(actTime,
				JfCfg.DATE_PATTERN_YMDHMS);
	}
    
    public void setActTime(BigDecimal actTime) {
    	this.actTime = actTime;
    }
    
	public String getActItemCode() {
        return this.actItemCode;
    }
    
    public void setActItemCode(String actItemCode) {
    	this.actItemCode = actItemCode;
    }
    
	public Integer getActItemNum() {
        return this.actItemNum;
    }
    
    public void setActItemNum(Integer actItemNum) {
    	this.actItemNum = actItemNum;
    }
    
	public Integer getIsclob() {
        return this.isclob;
    }
    
    public void setIsclob(Integer isclob) {
    	this.isclob = isclob;
    }
    
	public String getActMessage() {
        return this.actMessage;
    }
    
    public void setActMessage(String actMessage) {
    	this.actMessage = actMessage;
    }
    
	public String getActIp() {
        return this.actIp;
    }
    
    public void setActIp(String actIp) {
    	this.actIp = actIp;
    }
    
	public String getSignature() {
        return this.signature;
    }
    
    public void setSignature(String signature) {
    	this.signature = signature;
    }
    
	public String getExt1() {
        return this.ext1;
    }
    
    public void setExt1(String ext1) {
    	this.ext1 = ext1; 
    }
    
	public String getExt2() {
        return this.ext2;
    }
    
    public void setExt2(String ext2) {
    	this.ext2 = ext2;
    }
    
	public String getExt3() {
        return this.ext3;
    }
    
    public void setExt3(String ext3) {
    	this.ext3 = ext3;
    }
    
	public Integer getExt4() {
        return this.ext4;
    }
    
    public void setExt4(Integer ext4) {
    	this.ext4 = ext4;
    }
    
	public Integer getExt5() {
        return this.ext5;
    }
    
    public void setExt5(Integer ext5) {
    	this.ext5 = ext5;
    }

}

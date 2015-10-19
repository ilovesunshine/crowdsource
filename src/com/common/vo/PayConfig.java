/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.common.dao.Model;
import com.common.utils.CommonUtils;
import com.sys.constants.JfCfg;

/**
 * @Title: PayConfig.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-11-26 下午5:11:50
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class PayConfig implements Serializable,Model{
	
	/**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7882365697901432738L;
    private String pkey; 
	private String pvalue; 
	private String memo; 
	private Timestamp createTime; 
	private Long createPersonId; 
	private Timestamp updateTime; 
	private Long updatePersonId; 
		
	// QueryOnlyFields
		
	//default constructor
    public PayConfig() {
    	super();
    }
    
    
	public String getPkey() {
        return this.pkey;
    }
    
    public void setPkey(String pkey) {
    	this.pkey = pkey;
    }
    
	public String getPvalue() {
        return this.pvalue;
    }
    
    public void setPvalue(String pvalue) {
    	this.pvalue = pvalue;
    }
    
	public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
    	this.memo = memo;
    }
    
    public Timestamp getCreateTime() {
        if (this.createTime == null) {
            return null;
        }
        return (Timestamp) this.createTime.clone();
    }
    public String getCreateTimeStr(){
    	if(createTime!=null){
        	return CommonUtils.turnNumberToString(new BigDecimal(createTime.getTime()),
    				JfCfg.DATE_PATTERN_YMDHMS);
        	}else{
        		return "";
        	}
    }
    public void setCreateTime(Timestamp createTime) {
        if (createTime != null) {
            this.createTime = (Timestamp) createTime.clone();
        }
    }
    
	public Long getCreatePersonId() {
        return this.createPersonId;
    }
    
    public void setCreatePersonId(Long createPersonId) {
    	this.createPersonId = createPersonId;
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
    public String getUpdateTimeStr(){
    	if(updateTime!=null){
        	return CommonUtils.turnNumberToString(new BigDecimal(updateTime.getTime()),
    				JfCfg.DATE_PATTERN_YMDHMS);
        	}else{
        		return "";
        	}
    }
	public Long getUpdatePersonId() {
        return this.updatePersonId;
    }
    
    public void setUpdatePersonId(Long updatePersonId) {
    	this.updatePersonId = updatePersonId;
    }
    
	// QueryOnlyProperty accessors
   	public boolean validate(Object arg0) {
		return true;
	}

}
/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.event;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.common.exception.JfEventException;
import com.common.utils.JfAuditor;

/**
 * @Title: EncryptedFieldWarnEvent.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-17 下午5:14:56
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class EncryptedFieldWarnEvent implements JfPayEvent{
	
	private String tbName;//数据源表名
	private String pmKeyName;
	private Long pmKeyValue;
	private Object model;//需要更新的实体
	private String fieldName;//触发的字段名
	private BigDecimal fieldValue;//错误的数据
	private BigDecimal newValue;//修正的数据
	private Timestamp triggleTime;
	
	public void setTbName(String tbName) {
		this.tbName = tbName;
	}

	public void setModel(Object model) {
		this.model = model;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public void setFiledValue(BigDecimal fieldValue) {
		this.fieldValue = fieldValue;
	} 

	public void setNewValue(BigDecimal newValue) {
		this.newValue = newValue;
	}

	public void setTriggleTime(Timestamp triggleTime) {
	    if(triggleTime!=null){
	        this.triggleTime = (Timestamp)triggleTime.clone();
	    }
	}
	
	public void setPmKeyName(String pmKeyName) {
        this.pmKeyName = pmKeyName;
    }

    public void setPmKeyValue(Long pmKeyValue) {
        this.pmKeyValue = pmKeyValue;
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.event.JfPayEvent#handle()
     */
    @Override
	public void handle() throws JfEventException {
		// update the number and notify warn message
        //apiService.update(model)异步更新模型数据有风险，可能导致数据更新前已经被修改
		if(model==null){
		    JfAuditor.audit("密文字段校验", triggleTime.toString()+"表"+this.tbName+"("+this.pmKeyName+"="+this.pmKeyValue+")的字段"+this.fieldName + "密文校验失败，数据"+this.fieldValue+"可能被篡改，重置为" + this.newValue, JfAuditor.OBJ_PAY_SYS, JfAuditor.ACT_FAILED,null);
		}
	}

}

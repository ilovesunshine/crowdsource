package com.common.vo;


import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * 
 * @author BizFoundation
 * @version 1.0
 * @since 1.0
 */
public class UserAuthentic implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7266038192988248155L;
	// Fields    
	private Long jfid; 
	private Long buid; 
	private Integer type; 
	private Integer status; 
	private String fullname; 
	private String idencode; 
	private Date idenexp; 
	private Integer idenper; 
	private String idenimgpos; 
	private String idenimgrev; 
	private Timestamp createtime; 
	private Timestamp checktime; 
	private String checkresult; 
		
	//default constructor
    public UserAuthentic() {
    	super();
    }
    
    // Property accessors
	public Long getJfid() {
        return this.jfid;
    }
    
    public void setJfid(Long jfid) {
    	this.jfid = jfid;
    }
    
	public Long getBuid() {
        return this.buid;
    }
    
    public void setBuid(Long buid) {
    	this.buid = buid;
    }
    
	public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
    	this.type = type;
    }
    
	public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
    	this.status = status;
    }
    
	public String getFullname() {
        return this.fullname;
    }
    
    public void setFullname(String fullname) {
    	this.fullname = fullname;
    }
    
	public String getIdencode() {
        return this.idencode;
    }
    
    public void setIdencode(String idencode) {
    	this.idencode = idencode;
    }
    
	public Date getIdenexp() {
        if (this.idenexp == null) {
            return null;
        }
        return (Date) this.idenexp.clone();
    }
    
    public void setIdenexp(Date idenexp) {
    	if(idenexp!=null){
    	    this.idenexp = (Date)idenexp.clone();
    	}
    }
    
	public Integer getIdenper() {
        return this.idenper;
    }
    
    public void setIdenper(Integer idenper) {
    	this.idenper = idenper;
    }
    
	public String getIdenimgpos() {
        return this.idenimgpos;
    }
    
    public void setIdenimgpos(String idenimgpos) {
    	this.idenimgpos = idenimgpos;
    }
    
	public String getIdenimgrev() {
        return this.idenimgrev;
    }
    
    public void setIdenimgrev(String idenimgrev) {
    	this.idenimgrev = idenimgrev;
    }
    
	public Timestamp getCreatetime() {
        if (this.createtime == null) {
            return null;
        }
        return (Timestamp) this.createtime.clone();
    }
    
    public void setCreatetime(Timestamp createtime) {
    	if(createtime!=null){
    	    this.createtime = (Timestamp)createtime.clone();
    	}
    }
    
	public Timestamp getChecktime() {
        if (this.createtime == null) {
            return null;
        }
        return (Timestamp) this.checktime.clone();
    }
    
    public void setChecktime(Timestamp checktime) {
    	if(checktime!=null){
    	    this.checktime = (Timestamp)checktime.clone();
    	}
    }
    
	public String getCheckresult() {
        return this.checkresult;
    }
    
    public void setCheckresult(String checkresult) {
    	this.checkresult = checkresult;
    }
    
   	public boolean validate(Object arg0) {
		return true;
	}
}
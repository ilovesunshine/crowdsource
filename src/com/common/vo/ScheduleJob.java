package com.common.vo;

import java.io.Serializable;
import java.util.Date;
/**
 * 
* @Description: 计划任务信息
* @author miaozs
* @date 2015年8月11日 下午10:49:43
 */
public class ScheduleJob implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String STATUS_RUNNING = "1";
	public static final String JOB_INUSED = "1";
	public static final String JOB_NOTINUSED = "1";
	public static final String STATUS_NOT_RUNNING = "0";
	public static final String CONCURRENT_IS = "1";
	public static final String CONCURRENT_NOT = "0";
	private Long jobId;

	private Date createTime;

	private Date updateTime;
	/**
	 * 任务名称
	 */
	private String jobName;
	/**
	 * 任务分组
	 */
	private String jobGroup;
	/**
	 * 任务状态 是否启动任务
	 */
	private String jobIsInUse;
	/**
	 * cron表达式
	 */
	private String cronExpression;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 任务执行时调用哪个类的方法 包名+类名
	 */
	private String beanClass;
	/**
	 * 任务是否有状态
	 */
	private String isConcurrent;
	/**
	 * spring bean
	 */
	private String springId;
	/**
	 * 任务调用的方法名
	 */
	private String methodName;
	
	private String jobStatus;
	
	private Date startTime;
	
	private Date endTime;
	
	private String duraTime;
	
	private Date preFireTime;
	
	private String lastExecStatus;
	
	
	
	
	/**
	 * @return the lastExecStatus
	 */
	public String getLastExecStatus() {
		return lastExecStatus;
	}
	public String getLastExecStatusStr(){
		if("ok".equals(lastExecStatus)){
			return "成功";
		}else if("failed".equals(lastExecStatus)){
			return "失败";
		}else{
			return "无法找到上次运行的状态";
		}
	}

	/**
	 * @param lastExecStatus the lastExecStatus to set
	 */
	public void setLastExecStatus(String lastExecStatus) {
		this.lastExecStatus = lastExecStatus;
	}

	/**
	 * @return the preFireTime
	 */
	public Date getPreFireTime() {
		return preFireTime;
	}

	/**
	 * @param preFireTime the preFireTime to set
	 */
	public void setPreFireTime(Date preFireTime) {
		this.preFireTime = preFireTime;
	}

	/**
	 * @return the duraTime
	 */
	public String getDuraTime() {
		return duraTime;
	}

	/**
	 * @param duraTime the duraTime to set
	 */
	public void setDuraTime(String duraTime) {
		this.duraTime = duraTime;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	/**
	 * @return the jobIsInUse
	 */
	public String getJobIsInUse() {
		return jobIsInUse;
	}
	public String getJobIsInUseStr() {
		if(JOB_INUSED.endsWith(jobIsInUse)){
			return "已启用";
		}else{
			return "未启用";
		}
	}
	/**
	 * @return the jobStatus
	 */
	public String getJobStatus() {
		return jobStatus;
	}

	/**
	 * @param jobStatus the jobStatus to set
	 */
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	/**
	 * @param jobIsInUse the jobIsInUse to set
	 */
	public void setJobIsInUse(String jobIsInUse) {
		this.jobIsInUse = jobIsInUse;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	public String getIsConcurrent() {
		return isConcurrent;
	}

	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}
	public String getIsConcurrentStr(){
		if(CONCURRENT_IS.equals(isConcurrent)){
			return "允许";
		}else{
			return "不允许";
		}
	}
	public String getSpringId() {
		return springId;
	}

	public void setSpringId(String springId) {
		this.springId = springId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}
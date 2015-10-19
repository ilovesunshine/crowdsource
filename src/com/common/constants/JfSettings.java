/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.constants;

/**
 * @Title: JfConfig.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-7-16 上午11:36:41
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfSettings implements java.io.Serializable{
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    private String evn;
    private String accountType;
    private String accountSuffix;

    private String feeRateEnt;
    private String feeRatePer;
    private String billRefund;

    private String translogsEbcStartyear;
    private String translogsFtpPath;
    private String translogsOssPath;

    private String prefixSubMobile;
    private String secretEncrypt;
    private String secretBonus;
    private String secretOrder;  
    
    private String ftpServerUrl;
    private String ftpServerUsername;
    private String ftpServerPwd;
    private String ftpServerSeperator;
    private String ftpServerPathEbc;
    private String ftpFileEbcSuffix;
    private String ftpFileLocalSuffix;
    private String ftpFileZipSuffix;

    private String ossAccessKeyId;
    private String ossAccessKeySecret;
    private String ossBucketName;
    private String ossLocalPathBD;
    private String ossLocalPathThridParty;
    private String ossServerPathBD;
    private String ossServerPathThirdParty;
    private String ossTransLogsPath;
    
    private String mailHost;
    private String mailPort;
    private String mailAuth;
    private String mailFrom; 
    private String mailFromUserName;
    private String mailFromPassWord;
    
    public JfSettings(){
    }
    
    public String getEvn() {
        return evn;
    }

    public void setEvn(String evn) {
        this.evn = evn;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountSuffix() {
        return accountSuffix;
    }

    public void setAccountSuffix(String accountSuffix) {
        this.accountSuffix = accountSuffix;
    }

    public String getFeeRateEnt() {
        return feeRateEnt;
    }

    public void setFeeRateEnt(String feeRateEnt) {
        this.feeRateEnt = feeRateEnt;
    }

    public String getFeeRatePer() {
        return feeRatePer;
    }

    public void setFeeRatePer(String feeRatePer) {
        this.feeRatePer = feeRatePer;
    }

    public String getBillRefund() {
        return billRefund;
    }

    public void setBillRefund(String billRefund) {
        this.billRefund = billRefund;
    }

    public String getTranslogsEbcStartyear() {
        return translogsEbcStartyear;
    }

    public void setTranslogsEbcStartyear(String translogsEbcStartyear) {
        this.translogsEbcStartyear = translogsEbcStartyear;
    }

    public String getTranslogsFtpPath() {
        return translogsFtpPath;
    }

    public void setTranslogsFtpPath(String translogsFtpPath) {
        this.translogsFtpPath = translogsFtpPath;
    }

    public String getTranslogsOssPath() {
        return translogsOssPath;
    }

    public void setTranslogsOssPath(String translogsOssPath) {
        this.translogsOssPath = translogsOssPath;
    }

    public String getPrefixSubMobile() {
        return prefixSubMobile;
    }

    public void setPrefixSubMobile(String prefixSubMobile) {
        this.prefixSubMobile = prefixSubMobile;
    }

    public String getSecretEncrypt() {
        return secretEncrypt;
    }

    public void setSecretEncrypt(String secretEncrypt) {
        this.secretEncrypt = secretEncrypt;
    }

    public String getSecretBonus() {
        return secretBonus;
    }

    public void setSecretBonus(String secretBonus) {
        this.secretBonus = secretBonus;
    }

    public String getSecretOrder() {
        return secretOrder;
    }

    public void setSecretOrder(String secretOrder) {
        this.secretOrder = secretOrder;
    }

    public String getFtpServerUrl() {
        return ftpServerUrl;
    }

    public void setFtpServerUrl(String ftpServerUrl) {
        this.ftpServerUrl = ftpServerUrl;
    }

    public String getFtpServerUsername() {
        return ftpServerUsername;
    }

    public void setFtpServerUsername(String ftpServerUsername) {
        this.ftpServerUsername = ftpServerUsername;
    }

    public String getFtpServerPwd() {
        return ftpServerPwd;
    }

    public void setFtpServerPwd(String ftpServerPwd) {
        this.ftpServerPwd = ftpServerPwd;
    }

    public String getFtpServerPathEbc() {
        return ftpServerPathEbc;
    }

    public void setFtpServerPathEbc(String ftpServerPathEbc) {
        this.ftpServerPathEbc = ftpServerPathEbc;
    }

    public String getFtpFileEbcSuffix() {
        return ftpFileEbcSuffix;
    }

    public void setFtpFileEbcSuffix(String ftpFileEbcSuffix) {
        this.ftpFileEbcSuffix = ftpFileEbcSuffix;
    }

    public String getFtpFileLocalSuffix() {
        return ftpFileLocalSuffix;
    }

    public void setFtpFileLocalSuffix(String ftpFileLocalSuffix) {
        this.ftpFileLocalSuffix = ftpFileLocalSuffix;
    }

    public String getFtpFileZipSuffix() {
        return ftpFileZipSuffix;
    }

    public void setFtpFileZipSuffix(String ftpFileZipSuffix) {
        this.ftpFileZipSuffix = ftpFileZipSuffix;
    }

    public String getOssAccessKeyId() {
        return ossAccessKeyId;
    }

    public void setOssAccessKeyId(String ossAccessKeyId) {
        this.ossAccessKeyId = ossAccessKeyId;
    }

    public String getOssAccessKeySecret() {
        return ossAccessKeySecret;
    }

    public void setOssAccessKeySecret(String ossAccessKeySecret) {
        this.ossAccessKeySecret = ossAccessKeySecret;
    }

    public String getOssBucketName() {
        return ossBucketName;
    }

    public void setOssBucketName(String ossBucketName) {
        this.ossBucketName = ossBucketName;
    }

    public String getOssLocalPathBD() {
        return ossLocalPathBD;
    }

    public void setOssLocalPathBD(String ossLocalPathBD) {
        this.ossLocalPathBD = ossLocalPathBD;
    }

    public String getOssLocalPathThridParty() {
        return ossLocalPathThridParty;
    }

    public void setOssLocalPathThridParty(String ossLocalPathThridParty) {
        this.ossLocalPathThridParty = ossLocalPathThridParty;
    }

    public String getOssServerPathBD() {
        return ossServerPathBD;
    }

    public void setOssServerPathBD(String ossServerPathBD) {
        this.ossServerPathBD = ossServerPathBD;
    }

    public String getOssServerPathThirdParty() {
        return ossServerPathThirdParty;
    }

    public void setOssServerPathThirdParty(String ossServerPathThirdParty) {
        this.ossServerPathThirdParty = ossServerPathThirdParty;
    }

    public String getOssTransLogsPath() {
        return ossTransLogsPath;
    }

    public void setOssTransLogsPath(String ossTransLogsPath) {
        this.ossTransLogsPath = ossTransLogsPath;
    }

    public String getFtpServerSeperator() {
        return ftpServerSeperator;
    }

    public void setFtpServerSeperator(String ftpServerSeperator) {
        this.ftpServerSeperator = ftpServerSeperator;
    }

	/**
	 * @return Returns the mailHost.
	 */
	public String getMailHost() {
		return mailHost;
	}

	/**
	 * @param mailHost The mailHost to set.
	 */
	public void setMailHost(String mailHost) {
		this.mailHost = mailHost;
	}

	/**
	 * @return Returns the mailPort.
	 */
	public String getMailPort() {
		return mailPort;
	}

	/**
	 * @param mailPort The mailPort to set.
	 */
	public void setMailPort(String mailPort) {
		this.mailPort = mailPort;
	}

	/**
	 * @return Returns the mailAuth.
	 */
	public String getMailAuth() {
		return mailAuth;
	}

	/**
	 * @param mailAuth The mailAuth to set.
	 */
	public void setMailAuth(String mailAuth) {
		this.mailAuth = mailAuth;
	}

	/**
	 * @return Returns the mailFrom.
	 */
	public String getMailFrom() {
		return mailFrom;
	}

	/**
	 * @param mailFrom The mailFrom to set.
	 */
	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	/**
	 * @return Returns the mailFromUserName.
	 */
	public String getMailFromUserName() {
		return mailFromUserName;
	}

	/**
	 * @param mailFromUserName The mailFromUserName to set.
	 */
	public void setMailFromUserName(String mailFromUserName) {
		this.mailFromUserName = mailFromUserName;
	}

	/**
	 * @return Returns the mailFromPassWord.
	 */
	public String getMailFromPassWord() {
		return mailFromPassWord;
	}

	/**
	 * @param mailFromPassWord The mailFromPassWord to set.
	 */
	public void setMailFromPassWord(String mailFromPassWord) {
		this.mailFromPassWord = mailFromPassWord;
	}

}

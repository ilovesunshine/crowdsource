/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.config;

/**
 * @Title: JFFTP.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-7-9 下午4:10:45
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfFTP {
    
    //FTP相关************************************************************
    //远端FTP连接参数 
    public static final String JF_FTP_SERVER_URL = "ftp.server.url";//SERVER
    public static final String JF_FTP_SERVER_USERNAME = "ftp.server.username";//USERNAME
    public static final String JF_FTP_SERVER_PWD = "ftp.server.pwd";//PWD
    public static final String JF_FTP_SERVER_PATH = "ftp.server.path.ebc";//SERVER PATH
    /**
     * EBC交易日志在FTP上的路径
     */
    public static final String JF_FTP_SERVER_PATH_EBC = "pay.ftp.translogs.path";
    /**
     * EBC交易日志文件备份在OSS服务器上的路径
     */
    public static final String JF_OSS_SERVER_PATH_EBC = "pay.oss.translogs.path";
    
    //远端FTP文件名规范- 例如：远端FTP文件名规范FY_P2P_000000000000000_20141127.txt begin
    public static final String JF_FTP_FILE_MERCHSHORT="ftp.filename.merchShort";//商户简写
    public static final String JF_FTP_FILE_BUSISHORT="ftp.filename.busiShort";//业务简写
    public static final String JF_FTP_FILE_MERCHNO="ftp.filename.merchNo";//商户号
    public static final String JF_FTP_FILE_EBC_SUFFIX="ftp.filename.ebc.suffix";//文件后缀
    public static final String JF_FTP_FILE_ZIP_SUFFIX="ftp.filename.zip.suffix";//压缩文件后缀名
    
    //本地FTP文件名规范-文件后缀 例如：远端FTP文件名规范FY_P2P_000000000000000_20141127.txt end
    public static final String JF_FTP_FILE_LOCAL_SUFFIX="ftp.filename.local.suffix";
    public static final String JF_FTP_FILE_SEPERATOR = "ftp.filename.seperator";
    
    //对账文件内容 标题 begin
    public static final String EBC_FTP_COL_MERCHNO = "商户号";//商户号
    public static final String EBC_FTP_COL_ORDERNO = "订单号";//订单号
    public static final String EBC_FTP_COL_ORDERTYPE = "订单类型";//订单类型
    public static final String EBC_FTP_COL_TRADESN = "唯一标识";//交易流水号
    public static final String EBC_FTP_COL_OUTMEDIAID = "转出介质id";//出方钱包ID
    public static final String EBC_FTP_COL_OUTCARDNO = "转出卡号";//出方电子账户ID
    public static final String EBC_FTP_COL_INMEDIAID = "转入介质id";//入方钱包ID
    public static final String EBC_FTP_COL_INCARDNO = "转入卡号";//入方电子账户ID
    public static final String EBC_FTP_COL_CURRENCY = "币种";//交易币种
    public static final String EBC_FTP_COL_AMOUNT = "金额";//交易金额
    public static final String EBC_FTP_COL_STATE = "状态";//交易状态
    public static final String EBC_FTP_COL_DATETIME = "日期时间";//交易时间
    //对账文件内容 标题 end
    public static final String EBC_FTP_COL_CHECKDATETIME = "check结果的的确认时间";//check结果的的确认时间
    public static final String EBC_FTP_COL_DEALREASULT = "处理结果描述";//处理结果描述
    public static final String EBC_FTP_COL_CASHSTATE = "资金变动情况";//资金变动情况
    
    
    //服务器端文件分隔符
    public static final String JF_FILE_SERVER_SEPERATOR = "file.server.seperator";
    
    //对帐文件前缀
    public static final String JF_FILE_PREFIX_B = "B_";//B对帐文件前缀
    public static final String JF_FILE_PREFIX_D = "D_";//D对帐文件前缀
    public static final String JF_FILE_PREFIX_3 = "T3_";//第三方对帐文件前缀
    
  //状态 00-无日志；01-有日志，下载成功；02-有日志，下载失败
    /**
     * 00-无日志
     */
    public static final String JF_PAY_EBCTRADE_NOLOGS = "00";
    /**
     * 01-有日志，下载成功
     */
    public static final String JF_PAY_EBCTRADE_SUCCES = "01";
    /**
     * 02-有日志，下载失败
     */
    public static final String JF_PAY_EBCTRADE_FAILED = "02";
    
    
    /**
     * 对账(B,D)文件在本地服务器的路径 LOCAL PATH BD
     */
    public static final String JF_PAY_CONFIG_OSS_LOCAL_PATH_BD = "com.csi.jf.pay.common.oss.file.localPath.BD";
    /**
     * 对账(第三方)文件在本地服务器的路径 LOCAL PATH THIRD PARTY
     */
    public static final String JF_PAY_CONFIG_OSS_LOCAL_PATH_3RD = "com.csi.jf.pay.common.oss.file.localPath.thirdparty";
    /**
     * 对账(B,D)文件 备份在OSS服务器上路径
     */
    public static final String JF_PAY_CONFIG_OSS_SERVER_PATH_BD = "com.csi.jf.pay.common.oss.file.serverPath.BD";
    /**
     * 对账(第三方)文件 备份在OSS服务器上路径
     */
    public static final String JF_PAY_CONFIG_OSS_SERVER_PATH_3RD = "com.csi.jf.pay.common.oss.file.serverPath.thirdparty";
    
    /**
     * AWS S3 bucketName
     */
    public static final String JF_PAY_CONFIG_OWS_S3_BUCKET_NAME = "aws.s3.bucketName";
    
    /**
     * 备份文件在AWS S3服务器上路径
     */
    public static final String JF_PAY_CONFIG_OWS_S3_SERVER_PATH = "aws.s3.keyName";
   

    private JfFTP() {
    }
    
}

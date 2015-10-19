/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.jobs;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.config.JFPayConfigFactory;
import com.common.config.JfConstants;
import com.common.config.JfFTP;
import com.common.exception.JfPayException;
import com.common.ftp.ExcelUtils;
import com.common.ftp.FileUtils;
import com.common.ftp.FtpUtils;
import com.common.utils.JfAuditor;
import com.common.utils.JfLog;

/**
 * @Title: TradeLogsJob.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-18 下午8:58:44
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class TradeLogsJob{
    
    private static final Logger LOG = LoggerFactory.getLogger(TradeLogsJob.class);
    
    /**
     * 远端FTP文件名规范FY_P2P_000000000000000_20141127.txt
     * 将从银盈通下载的文本对账文件，转换为EXCELL电子表格文件
     */
    public void downloadFileDaily() {
        String ftpServerPath = "";
        String fileName = "";
        try {
            ftpServerPath = JFPayConfigFactory.getJfSettings().getFtpServerPathEbc();
            fileName = "";
            	JfLog.info(LOG,"download '" + fileName + "' begin ......");
            downloadFile2Xls(fileName);
            	JfLog.info(LOG,"download '" + fileName + "' end ......");
            
            JfAuditor.auditJob(JfAuditor.ACTION_DOWNLOAD_TRANS_LOG_FILE, "系统自动从["+ftpServerPath+"]下载处理交易日志文件["+fileName+"]，解析成功，备份成功");
        } catch (Exception e) {
        			JfLog.error(LOG,"下载并处理交易日志文件出错:"+e.getMessage(),e);
            JfAuditor.auditJobFail(JfAuditor.ACTION_DOWNLOAD_TRANS_LOG_FILE, "系统自动从["+ftpServerPath+"]下载处理交易日志文件["+fileName+"]失败："+e.getMessage());
        }
    }
    
    /**
     * 远端FTP文件名规范FY_P2P_000000000000000_20141127.txt
     * 将从银盈通下载的文本对账文件，转换为EXCELL电子表格文件
     * 
     * @param fileName
     */
    public void downloadFile2Xls(String fileName) throws Exception{
        String localFullFileName = FtpUtils.getIntance().downloadFile(fileName);
        //将从银盈通下载的文本对账文件，转换为EXCELL电子表格文件
        txt2Xls(localFullFileName);
    }

    /**
     * 将从银盈通下载的文本对账文件，转换为EXCELL电子表格文件
     * CSI_P2P_88888888_20141227.txt
     * 
     * 商户号|订单号|订单类型|交易流水号|出方钱包ID|出方电子账户ID|入方钱包ID|入方电子账户ID|交易币种|交易金额|交易状态|交易时间
     * 000000000000000|140812172530001|01|140812172531111|0100980000249396|9001990000274085|0100980000195781|9001990000163396|CNY|12.03|00|20140807120012
     * 000000000000000|140812172530002|01|140812172532222|0100980000195781|9001990000163396|0100980000194851|9001990000162952|CNY|10.08|00|20140807130000
     */
    public void txt2Xls() throws Exception{
        String localPath = JFPayConfigFactory.getJfSettings().getOssLocalPathThridParty();
        localPath = FileUtils.endOfSeparator(localPath);//注意区分不同系统使用的分隔符
        String fileName = "";
        String fullFileName = localPath + fileName;
        
        txt2Xls(fullFileName);
    }
    
    /**
     * 将从银盈通下载的文本对账文件，转换为EXCELL电子表格文件
     * CSI_P2P_88888888_20141227.txt
     * 
     * 商户号|订单号|订单类型|交易流水号|出方钱包ID|出方电子账户ID|入方钱包ID|入方电子账户ID|交易币种|交易金额|交易状态|交易时间
     * 000000000000000|140812172530001|01|140812172531111|0100980000249396|9001990000274085|0100980000195781|9001990000163396|CNY|12.03|00|20140807120012
     * 000000000000000|140812172530002|01|140812172532222|0100980000195781|9001990000163396|0100980000194851|9001990000162952|CNY|10.08|00|20140807130000
     * 
     * @param fullFileName
     */
    public void txt2Xls(String fullFileName) throws JfPayException{
        if(null == fullFileName) {
            return ;
        }
        
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        int colNum = 12;
        try {
            String suffix = JFPayConfigFactory.getJfSettings().getFtpFileEbcSuffix();
            String localSuffix = JFPayConfigFactory.getJfSettings().getFtpFileLocalSuffix();
            String xlsFileName = fullFileName.replace(suffix, localSuffix);
            	JfLog.info(LOG,"Convert txt file '" + fullFileName + "' to xls '" + xlsFileName + "' begin.........");
            //generate column header begin
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(JfFTP.EBC_FTP_COL_MERCHNO, JfFTP.EBC_FTP_COL_MERCHNO);
            map.put(JfFTP.EBC_FTP_COL_ORDERNO, JfFTP.EBC_FTP_COL_ORDERNO);
            map.put(JfFTP.EBC_FTP_COL_ORDERTYPE, JfFTP.EBC_FTP_COL_ORDERTYPE);
            map.put(JfFTP.EBC_FTP_COL_TRADESN, JfFTP.EBC_FTP_COL_TRADESN);
            map.put(JfFTP.EBC_FTP_COL_OUTMEDIAID, JfFTP.EBC_FTP_COL_OUTMEDIAID);
            map.put(JfFTP.EBC_FTP_COL_OUTCARDNO, JfFTP.EBC_FTP_COL_OUTCARDNO);
            map.put(JfFTP.EBC_FTP_COL_INMEDIAID, JfFTP.EBC_FTP_COL_INMEDIAID);
            map.put(JfFTP.EBC_FTP_COL_INCARDNO, JfFTP.EBC_FTP_COL_INCARDNO);
            map.put(JfFTP.EBC_FTP_COL_CURRENCY, JfFTP.EBC_FTP_COL_CURRENCY);
            map.put(JfFTP.EBC_FTP_COL_AMOUNT, JfFTP.EBC_FTP_COL_AMOUNT);
            map.put(JfFTP.EBC_FTP_COL_STATE, JfFTP.EBC_FTP_COL_STATE);
            map.put(JfFTP.EBC_FTP_COL_DATETIME, JfFTP.EBC_FTP_COL_DATETIME);
            list.add(map);
            //generate column header end
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fullFileName),JfConstants.UTF8));
            int lineI = 0;
            for (String line = br.readLine(); line != null; line = br.readLine()) {
            	lineI++;
                String[] cols = line.split("[|]");
                
                if(null == cols || cols.length < colNum) {
                		JfLog.info(LOG,"the line " + lineI + ":" + line + "; cols.length:" + cols.length);
                }
                map = new HashMap<String, Object>();
                map.put(JfFTP.EBC_FTP_COL_MERCHNO, cols[0]);
                map.put(JfFTP.EBC_FTP_COL_ORDERNO, cols[1]);
                map.put(JfFTP.EBC_FTP_COL_ORDERTYPE, JfConstants.getDealType(cols[2]) );
                map.put(JfFTP.EBC_FTP_COL_TRADESN, cols[3]);
                map.put(JfFTP.EBC_FTP_COL_OUTMEDIAID, cols[4]);
                map.put(JfFTP.EBC_FTP_COL_OUTCARDNO, cols[5]);
                map.put(JfFTP.EBC_FTP_COL_INMEDIAID, cols[6]);
                map.put(JfFTP.EBC_FTP_COL_INCARDNO, cols[7]);
                map.put(JfFTP.EBC_FTP_COL_CURRENCY, cols[8]);
                map.put(JfFTP.EBC_FTP_COL_AMOUNT, cols[9]);
                map.put(JfFTP.EBC_FTP_COL_STATE, JfConstants.getDealState(cols[10]));
                map.put(JfFTP.EBC_FTP_COL_DATETIME, cols[11]);
                list.add(map);
            }
            br.close();
            //ExcelUtils.createExcel(list, xlsFileName);
            	JfLog.info(LOG,"Convert txt file '" + fullFileName + "' to xls '" + xlsFileName + "' end......");
        } catch (Exception e) {
        		JfLog.error(LOG,e.getMessage(),e);
            throw new JfPayException(e);
        }
        
        
    }
    
    /**
     * {@link service.CheckAccountService#compressMonthly(java.util.Date)}
     * {@link service.CheckAccountService#uploadFileThirdparty()}
     */
    public void execute() throws Exception{
        // 下载第三方支付平台交易日志,每天早上1点开始执行
        downloadFileDaily();
        // 从第三方支付平台下载对账的交易日志文件，备份存储
    }

}

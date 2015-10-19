/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.ftp;

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
import com.common.exception.JfPayException;
import com.common.utils.JfLog;

/**
 * @Title: FtpEBCTimer.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-26 上午11:20:29
 * @author FengHaiBing
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class FtpEBCJob {
    
    private static final Logger LOG = LoggerFactory.getLogger(FtpEBCJob.class);
    
    /**
     * 远端FTP文件名规范FY_P2P_000000000000000_20141127.txt
     * 将从银盈通下载的文本对账文件，转换为EXCELL电子表格文件
     * 
     * @param fileName
     */
    public void downloadFile(String fileName) throws JfPayException{
        FtpUtils.getIntance().downloadFile(fileName);
        
        //将从银盈通下载的文本对账文件，转换为EXCELL电子表格文件
        txt2Xls(fileName);
    }

    /**
     * 将从银盈通下载的文本对账文件，转换为EXCELL电子表格文件
     * CSI_P2P_88888888_20141227.txt
     * 
     * 商户号|订单号|订单类型|交易流水号|出方钱包ID|出方电子账户ID|入方钱包ID|入方电子账户ID|交易币种|交易金额|交易状态|交易时间
     * 000000000000000|140812172530001|01|140812172531111|0100980000249396|9001990000274085|0100980000195781|9001990000163396|CNY|12.03|00|20140807120012
     * 000000000000000|140812172530002|01|140812172532222|0100980000195781|9001990000163396|0100980000194851|9001990000162952|CNY|10.08|00|20140807130000
     */
    public void txt2Xls() throws JfPayException{
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
        
        String suffix = JFPayConfigFactory.getJfSettings().getFtpFileEbcSuffix();
        String localSuffix = JFPayConfigFactory.getJfSettings().getFtpFileLocalSuffix();
        String xlsFileName = fullFileName.replace(suffix, localSuffix);
        
        int colNum = 12;
        try {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            //generate column header begin
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("商户号", "商户号");
            map.put("订单号", "订单号");
            map.put("订单类型", "订单类型");
            map.put("交易流水号", "交易流水号");
            map.put("出方钱包ID", "出方钱包ID");
            map.put("出方电子账户ID", "出方电子账户ID");
            map.put("入方钱包ID", "入方钱包ID");
            map.put("入方电子账户ID", "入方电子账户ID");
            map.put("交易币种", "交易币种");
            map.put("交易金额", "交易金额");
            map.put("交易状态", "交易状态");
            map.put("交易时间", "交易时间");
            list.add(map);
            //generate column header end
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fullFileName),JfConstants.UTF8));
            int lineI = 0;
            for (String line = br.readLine(); line != null; line = br.readLine()) {
            	lineI++;
                String[] cols = line.split("[|]");
                if(null == cols || cols.length < colNum) {
                	if(LOG.isWarnEnabled()){
                		JfLog.warn(LOG,"the line " + lineI + ":" + line + "; cols.length:" + cols.length);
                	}
                }
                map = new HashMap<String, Object>();
                map.put("商户号", cols[0]);
                map.put("订单号", cols[1]);
                map.put("订单类型", JfConstants.getDealType(cols[2]) );
                map.put("交易流水号", cols[3]);
                map.put("出方钱包ID", cols[4]);
                map.put("出方电子账户ID", cols[5]);
                map.put("入方钱包ID", cols[6]);
                map.put("入方电子账户ID", cols[7]);
                map.put("交易币种", cols[8]);
                map.put("交易金额", cols[9]);
                map.put("交易状态", JfConstants.getDealState(cols[10]));
                map.put("交易时间", cols[11]);
                list.add(map);
            }
            br.close();
        } catch (Exception e) {
        	{
        		JfLog.error(LOG,e.getMessage(),e);
        	}
            throw new JfPayException(e);
        } 
    }
    
}

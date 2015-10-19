/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.ftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.config.JFPayConfigFactory;
import com.common.exception.JfPayException;
import com.common.utils.JfLog;

/**
 * @Title: FtpUtils.java
 * @Description: <br>
 * <br>
 * @Company: crowdsource
 * @Created on 2015-12-9 下午5:24:26
 * @author FengHaiBing
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class FtpUtils {
    
    private static final Logger LOG = LoggerFactory.getLogger(FtpUtils.class);

    private FTPClient client = null;
    
    private static FtpUtils instance = null;
    
    private FtpUtils() {
    }
    
    public static FtpUtils getIntance() {
        if(null == instance) {
            instance = new FtpUtils();
        }
        return instance;
    }
    
    /**
     * 
     * @return
     * @throws SocketException
     * @throws IOException
     */
    public FTPClient getFTPClient() throws SocketException, IOException {
        if(null == client) {
            client = new FTPClient();
        }
        String ftpServer = JFPayConfigFactory.getJfSettings().getFtpServerUrl();
        client.connect(ftpServer);

        String userName = JFPayConfigFactory.getJfSettings().getFtpServerUsername();
        String password = JFPayConfigFactory.getJfSettings().getFtpServerPwd();
        client.login(userName, password);
        return client;
    }

    /**
     * 
     * @param fileName
     */
    public void deleleAFile(String fileName) throws JfPayException {
        if (null == fileName){
            return;
        }
        try {
            client = getFTPClient();
            // Delete file
            boolean exist = client.deleteFile(fileName);
            if (exist) {
                // Notify user for deletion
            		JfLog.info(LOG,"File '" + fileName + "' deleted...");
            }else{
                // Notify user that file doesn't exist
            		JfLog.info(LOG,"File '" + fileName + "' doesn't exist...");
            }
            client.logout();
        } catch (Exception e) {
        			JfLog.error(LOG,e.getMessage(), e);
            throw new JfPayException(e);
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
            		JfLog.error(LOG,e.getMessage(),e);
            }
        }
    }
    
    /**
     * 
     * @param remoteFileName
     * @return
     * @throws JfPayException
     */
    public String downloadFile(String remoteFileName) throws JfPayException{
    		JfLog.info(LOG,"downloadFile " + remoteFileName + " from EBC begin....");
        String localFullFileName = null;
        OutputStream outputStream2 = null;
        InputStream inputStream = null;
        try {
            client = getFTPClient();
            client.enterLocalPassiveMode();
            client.setFileType(FTP.BINARY_FILE_TYPE);
 
            // APPROACH #2: using InputStream retrieveFileStream(String)
            String serverPath = JFPayConfigFactory.getJfSettings().getFtpServerPathEbc();
            String fileServerSeperator = JFPayConfigFactory.getJfSettings().getFtpServerSeperator();
            serverPath = FileUtils.endOfSeparator(serverPath, fileServerSeperator);//注意区分不同系统使用的分隔符
            String fullRemoteFile = serverPath + remoteFileName;
            String fileName = FileUtils.getFileName(remoteFileName);
            
            String localPath = JFPayConfigFactory.getJfSettings().getOssLocalPathThridParty();
            FileUtils.creatPath(localPath);
            localPath = FileUtils.endOfSeparator(localPath);//注意区分不同系统使用的分隔符
            localFullFileName = localPath + fileName; 
            File downloadFile = new File(localFullFileName);
            outputStream2 = new BufferedOutputStream(new FileOutputStream(downloadFile));
            inputStream = client.retrieveFileStream(fullRemoteFile);
            if(null == inputStream) {
            		JfLog.error(LOG,"download: " + fullRemoteFile + " is not exist on remote ");
                return null;
            }
            
            byte[] bytesArray = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inputStream.read(bytesArray)) != -1) {
                outputStream2.write(bytesArray, 0, bytesRead);
            }
 
            boolean success = client.completePendingCommand();
            if (success) {
            		JfLog.info(LOG,"File " + remoteFileName + " has been downloaded successfully.");
            }
        } catch (Exception e) {
        		JfLog.error(LOG,e.getMessage(),e);
            throw new JfPayException(e);
        } finally {
            try {
                if(outputStream2!=null){
                    outputStream2.close();
                }
            } catch (Exception e) {
            		JfLog.error(LOG,e.getMessage(),e);
            }
            try {
                if(inputStream!=null){
                    inputStream.close();
                }
            } catch (Exception e) {
            		JfLog.error(LOG,e.getMessage(),e);
            }
            try {
                if (client.isConnected()) {
                    client.logout();
                    client.disconnect();
                }
            } catch (IOException ex) {
            		JfLog.error(LOG,ex.getMessage(),ex);
            }
        }        
        	JfLog.info(LOG,"downloadFile " + remoteFileName + " from EBC end....");
        
        return localFullFileName;
    }
    
    /**
     * 
     * @param remoteFileName
     * @return
     * @throws JfPayException
     */
    public InputStream downloadEBCFile(String sServerPath ,String remoteFileName) throws JfPayException{
    		JfLog.info(LOG,"downloadFile " + remoteFileName + " from EBC begin....");
        InputStream inputStream = null;
        String serverPath = sServerPath;
        try {
            client = getFTPClient();
            client.enterLocalPassiveMode();
            client.setFileType(FTP.BINARY_FILE_TYPE);
 
            String fileServerSeperator = JFPayConfigFactory.getJfSettings().getFtpServerSeperator();
            serverPath = FileUtils.endOfSeparator(serverPath, fileServerSeperator);//注意区分不同系统使用的分隔符
            String fullRemoteFile = serverPath + remoteFileName;
            
            String localPath = JFPayConfigFactory.getJfSettings().getOssLocalPathThridParty();
            FileUtils.creatPath(localPath);
            inputStream = client.retrieveFileStream(fullRemoteFile);
        } catch (Exception e) {
        		JfLog.error(LOG,e.getMessage(),e);
            throw new JfPayException(e);
        } finally {
            try {
                if (client.isConnected()) {
                    client.logout();
                    client.disconnect();
                }
            } catch (IOException ex) {
            		JfLog.error(LOG,ex.getMessage(),ex);
            }
        }        
        	JfLog.info(LOG,"downloadFile " + remoteFileName + " from EBC end....");
        
        return inputStream;
    }
}

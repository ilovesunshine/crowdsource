/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.utils.JfLog;

/**
 * @Title: FileUtils.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-25 下午5:12:54
 * @author FengHaiBing
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public  class FileUtils {
    
    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);
    
    private FileUtils() {
        
    }
    
    /**
     * 文件路径以操作系统匹配的分隔符结尾
     * 
     * @param filePath
     * @return
     */
    public static String endOfSeparator(String filePath) {
        if(null == filePath) {
            return null;
        }
        
        return endOfSeparator(filePath, File.separator);
    }

    /**
     * 文件路径以操作系统匹配的分隔符结尾
     * 
     * @param filePath
     * @param seperator
     * @return
     */
    public static String endOfSeparator(String path, String seperator) {
        String filePath = path;
        if(null == filePath) {
            return null;
        }
        
        if(filePath.endsWith(seperator)) {
            return filePath;
        }
        
        filePath += seperator;
        
        return filePath;
    }

    /**
     * 移除 文件路径结尾的分隔符
     * 
     * @param filePath
     * @return
     */
    public static String removeEndOfSeparator(String filePath) {
        if(null == filePath) {
            return null;
        }
        
        return removeEndOfSeparator(filePath, File.separator);
    }
    
    /**
     * 移除 文件路径结尾的分隔符
     * 
     * @param filePath
     * @param seperator
     * @return
     */
    public static String removeEndOfSeparator(String path, String seperator) {
        String filePath = path;
        if(null == filePath) {
            return null;
        }
        
        if(!filePath.endsWith(seperator)) {
            return filePath;
        }
        
        filePath = filePath.substring(0, filePath.length() - 1);
        
        return filePath;
    }
    
    /**
     * 
     * @param fullName
     * @return
     * 
     * @author FengHaiBing
     */
    public static String getFileName(String fullName){
        if(null == fullName) {
            return "";
        }
        
        int lastIndex = fullName.lastIndexOf("/");
        if(lastIndex < 1) {
            lastIndex = fullName.lastIndexOf("\\");
        }
        if(lastIndex < 1) {
            return fullName;
        }
        
        return fullName.substring(lastIndex);
    }
    
    /**
     * 一次性建立所有层级目录
     * 
     * @param filePath
     */
    public static void creatPath(String filePath) {
        if(null == filePath) {
            return ;
        }
        
        File filePaths = new File(filePath);
        if(!filePaths.exists()) {
            boolean b = filePaths.mkdirs();
        		JfLog.info(LOG,"file.mkdirs:"+b);
        }
        
    }
    
    /**
     * 生产文件 如果文件所在路径不存在则生成路径
     *
     * @param fileName
     *            文件名 带路径
     * @param isDirectory 是否为路径
     * @return
     * @author FengHaiBing
     * @date 2014-12-27
     */
    public static File buildFile(String fileName, boolean isDirectory) {
        File target = new File(fileName);
        if (isDirectory) {
            boolean b = target.mkdirs();
        		JfLog.info(LOG,"file.mkdirs:"+b);
        } else {
            if (!target.getParentFile().exists()) {
                boolean result = target.getParentFile().mkdirs();
            		JfLog.info(LOG,"target.getParentFile().mkdirs:"+result);
                target = new File(target.getAbsolutePath());
            }
        }
        return target;
    } 
    
    public static File writeTempFile(String filename,InputStream is){
    	File file=new File(filename);
    	OutputStream os=null;
    	try{
    		os=new FileOutputStream(file);
    		byte buffer[]=new byte[512];
    		while((is.read(buffer))!=-1){
    			os.write(buffer);
    		}
    		os.flush();
    	}catch(Exception e){
        		JfLog.error(LOG,e.getMessage(),e);
    	}finally{
    		try{
    		    if(os!=null){
    		        os.close();
    		    }
    		}catch(Exception e){
    	    		JfLog.error(LOG,e.getMessage(),e);
    		}
    	}return file;
    }

}

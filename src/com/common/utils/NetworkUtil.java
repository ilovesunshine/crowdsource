/**
 * $Id$
 *
 * Copyright (c) 2013 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.exception.JfPayException;


/**
 * @Title: NetworkUtil.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-11-28 下午1:55:02
 * @author Administrator
 * @version  
 * @since JDK 1.6
 */
public class NetworkUtil {
    
	private static final Logger LOG = LoggerFactory.getLogger(NetworkUtil.class);
	
	private NetworkUtil(){
	}

	/**
     * 返回用户的IP地址
     * @param request
     * @return
     */
    public static String getUesrIpAddress(HttpServletRequest request){
        if(request==null){
            return null;
        }
        String ip = request.getHeader("x-forwarded-for");
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip==null||ip.length()==0||"unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     * 返回主机名
     * @return
     */
    public static String getRemoteHostName() {
        InetAddress a;
        try {
            a = InetAddress.getLocalHost();
            return a.getHostName();
        } catch (Exception e) {
            JfLog.error(LOG,e.getMessage(),e);
        }
        return null;
    }
    
    public static final String MIME_HTML = "text/html";
    public static final String MIME_PLAIN = "text/plain";
    public static final String MIME_GIF = "image/gif";
    public static final String MIME_JPEG = "image/jpeg";
    public static final String MIME_VIDEO = "video/x-msvideo";
    public static final String MIME_ZIP = "application/zip";
    public static final String MIME_MSWORD = "application/msword";
    public static final String MIME_EXCEL = "application/vnd.ms-excel";
    public static final String MIME_AUDIO = "audio/x-mpeg";
    /**
     * 文件流下载
     * @param response
     * @param is
     * @throws JfPayException
     * 常见的MIME类型及含义： 
        text/html   超文本标记语言文本(.html) 
        text/plain  普通文本(.txt) 
        image/gif   GIF图形(.gif) 
        image/jpeg  JPEG图形(.jpeg或.jpg) 
        video/x-msvideo  AVI文件(.avi) 
        application/zip  zip压缩包(.zip) 
        application/msword  word文档(.doc) 
        application/vnd.ms-excel  excel文档(.xls) 
        audio/x-mpeg  mp3音乐文件(.mp3) 
     */
    public static void downloadFile(HttpServletResponse response,InputStream is,String mimeType)throws JfPayException{
        try {
            String fileName=java.net.URLEncoder.encode("红包列表.xls","utf-8");  
            //该步是最关键的一步，使用setHeader()方法弹出"是否要保存"的对话框，打引号的部分都是固定的值，不要改变  
            response.setHeader("Content-disposition","attachment;filename="+fileName);  
            response.setContentType(mimeType);  
            if(is!=null){
                long fileLength=is.available();  
                String length=String.valueOf(fileLength);  
                response.setHeader("content_Length",length);  
                OutputStream servletOutPutStream=response.getOutputStream();  
                byte bytes[] = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    servletOutPutStream.write(bytes, 0, len);
                }
                servletOutPutStream.close();
                is.close();
            }
        } catch (Exception e) {
            JfLog.error(LOG,e.getMessage(), e);
            throw new JfPayException(e);
        }
    }

}

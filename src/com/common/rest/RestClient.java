/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.rest;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.config.JfConstants;
import com.common.config.JfTest;
import com.common.utils.JfLog;
import com.common.utils.MD5Util;
import com.csi.jointforce.common.model.RestToken;

/**
 * @Title: RestClient.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-11-25 上午11:49:31
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class RestClient {

    private static final Logger LOG = LoggerFactory.getLogger(RestClient.class);
    
    public String doGet(String url,boolean auth) throws IOException{ 
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(JfTest.getTestHost() + url);
            httpGet.setHeader("Accept", "*/*");
            httpGet.setHeader("Accept-Language", "zh-cn");
            httpGet.setHeader("Cache-Control", "no-cache");
            httpGet.setHeader("Accept-Charset", "UTF-8;");
            
            if(auth){
                String psw = MD5Util.encrypt("psw123");
                RestToken token = new RestToken("Administrator@chinasofti.com", psw);
                String jfToken = token.getToken(); // 获取token
                long time = token.getTime();// 获取时间
                String jfRandom = token.getRandom();// 获取随机数
                String jfUserId = token.getUserId();// 获取帐号
                httpGet.setHeader("jftoken",jfToken);
                httpGet.setHeader("jfrandom",jfRandom);
                httpGet.setHeader("jftime",String.valueOf(time));
                httpGet.setHeader("jfuserid",jfUserId);
                
                RestToken newToken = new RestToken(jfUserId, psw,time,jfRandom);
            		JfLog.info(LOG,newToken.getToken());
            }

	            JfLog.info(LOG,"****************************************************\n");
	            JfLog.info(LOG,"do post request for:\n"+JfTest.getTestHost() + url);
            
            CloseableHttpResponse response1 = httpclient.execute(httpGet);
            response1.addHeader("content-type", JfConstants.JF_PAY_CONSTANT_APPLICATION);
            response1.addHeader("accept", JfConstants.JF_PAY_CONSTANT_APPLICATION);
            
            try {
            		JfLog.info(LOG,String.valueOf(response1.getStatusLine()));
                HttpEntity entity1 = response1.getEntity();
                String retStr = null;
                if(entity1!=null){
                    retStr = EntityUtils.toString(entity1) ;
	                    JfLog.info(LOG,"Response:"+retStr);
	                    JfLog.info(LOG,"Length:"+entity1.getContentLength());
	                    JfLog.info(LOG,"ContentType:"+entity1.getContentType());
	                    JfLog.info(LOG,"Encoding:"+entity1.getContentEncoding());
                }
                return retStr;
            } finally {
                response1.close();
            }
        } catch (IOException e) {
        		JfLog.error(LOG,e.getMessage(), e);
        } finally {
            httpclient.close();
        }
        return null;
    }
    
    @SuppressWarnings("deprecation")
    public String doPost(String url,String json,List<BasicNameValuePair> nvps,int mode,boolean auth) throws  IOException{ 
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httpPost = new HttpPost(JfTest.getTestHost() + url);
        		JfLog.info(LOG,"url="+JfTest.getTestHost() + url);
            httpPost.setHeader("Accept", "*/*");
            httpPost.setHeader("Accept-Language", "zh-cn");
            httpPost.setHeader("Cache-Control", "no-cache");
            httpPost.setHeader("Accept-Charset", "UTF-8;");
            if(auth){
                String psw = MD5Util.encrypt("JFnow");
                RestToken token = new RestToken("Administrator@chinasofti.com", psw);
                String jfToken = token.getToken(); // 获取token
                long time = token.getTime();// 获取时间
                String jfRandom = token.getRandom();// 获取随机数
                String jfUserId = token.getUserId();// 获取帐号
                httpPost.setHeader("jftoken",jfToken);
                httpPost.setHeader("jfrandom",jfRandom);
                httpPost.setHeader("jftime",String.valueOf(time));
                httpPost.setHeader("jfuserid",jfUserId);
            }

	            JfLog.info(LOG,"****************************************************\n");
	            JfLog.info(LOG,"do post request for:\n"+JfTest.getTestHost() + url);
            if(mode==1){
                HttpEntity entity = new StringEntity(json, Charset.forName("utf-8"));
                httpPost.setEntity(entity);
            }else if(mode==2){
                httpPost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
            }
            
            CloseableHttpResponse response1 = httpclient.execute(httpPost);
            response1.addHeader("content-type", JfConstants.JF_PAY_CONSTANT_APPLICATION);
            response1.addHeader("accept", JfConstants.JF_PAY_CONSTANT_APPLICATION);
            
            try {
            		JfLog.info(LOG,String.valueOf(response1.getStatusLine()));
                HttpEntity entity1 = response1.getEntity();
                String retStr = null;
				if (entity1 != null) {
					retStr = EntityUtils.toString(entity1);
					JfLog.info(LOG, "Response:" + retStr);
					JfLog.info(LOG, "Length:" + entity1.getContentLength());
					JfLog.info(LOG, "ContentType:" + entity1.getContentType());
					JfLog.info(LOG, "Encoding:" + entity1.getContentEncoding());
				}
                return retStr;
            } finally {
                response1.close();
            }
		} catch (IOException e) {
			JfLog.error(LOG, e.getMessage(), e);
		} finally {
            httpclient.close();
        }
        return null;
    }
}

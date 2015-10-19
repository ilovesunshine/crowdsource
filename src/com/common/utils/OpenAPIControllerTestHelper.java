/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinasofti.ro.bizframework.core.libs.Base64;
import com.chinasofti.ro.bizframework.modules.json.JSONArray;
import com.chinasofti.ro.bizframework.modules.json.JSONObject;
import com.common.config.JfApi;
import com.common.config.JfConstants;
import com.common.config.JfTest;
import com.common.exception.JfPayApiException;
import com.common.rest.RestClient;

/**
 * @Title: OpenAPIControllerTestHelper.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-16 下午8:41:22
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class OpenAPIControllerTestHelper {
    
	private static final Logger LOG = LoggerFactory.getLogger(OpenAPIControllerTestHelper.class);

	private OpenAPIControllerTestHelper(){
	}

	/**
     * 订单相关接口响应处理
     * @param apiType
     * @param data
     * @param url
     * @throws JfPayApiException
     */
    public static void postOrder(int apiType,String data,String url) throws JfPayApiException{
        try {
            RestClient client = new RestClient();
            List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
            String datasign = JfPayOrderSecurity.getInstance().signAPI(data);
            
            
            	JfLog.info(LOG,"****** data:"+data);
            	JfLog.info(LOG,"****** datasign:"+datasign);
            nvps.add(new BasicNameValuePair("data", data));
            nvps.add(new BasicNameValuePair("datasign", datasign));
            
            String result = client.doPost(url, null, nvps, 2,true);
            
            	JfLog.info(LOG,"***********************************************************************\n");
            	JfLog.info(LOG,"result:\n" + result);
            JSONObject json = JSONObject.fromObject(result);
            if (json != null) {
                //验证响应数据签名
            		JfLog.info(LOG,"res.data"+json.getString("data"));
                
                String rData = new String(Base64.decode(json.getString("data").getBytes(JfConstants.UTF8)),JfConstants.UTF8);
                String rDatasign = json.getString("datasign");
                
                	JfLog.info(LOG,"rData="+rData);
                	JfLog.info(LOG,"rDatasign="+rDatasign);
                
                if (!JfPayOrderSecurity.getInstance().checkAPIResponseSignature(result)) {
                		JfLog.error(LOG,"数据验签失败");
                    throw new JfPayApiException("数据验签失败！");
                }else{
                		JfLog.info(LOG,"===============》 返回数据签名验证成功！！！！！");
                }
                
                //处理响应代码
                String resultcode = json.getString("resultcode");
                if (JfApi.RC_SUCCESS == Integer.parseInt(resultcode)) {
                		JfLog.info(LOG,"响应成功，开始处理响应数据");
                    JSONArray arrayData = JSONArray.fromObject(rData);
                    if (arrayData==null||arrayData.isEmpty()) {
                    		JfLog.error(LOG,"响应数据解析失败");
                    } else {
                        JSONObject order = arrayData.getJSONObject(0);
                        int i = 0;
                        if (apiType == JfTest.I_CHECK_PAYABLEFORORDER) {//1-可签单查询
                        		JfLog.info(LOG,"响应数据解析[" + (i++) + "]：订单可以签订");
                        } else if (apiType == JfTest.I_QUERY_ACCOUNTSTATE) {//2-账户开通状态查询
                        		JfLog.info(LOG,"响应数据解析[" + (i++) + "]：账户已开通");
                        } else if (apiType == JfTest.I_CHECK_ORDERISNEW) {//2-新旧订单查询
                            String orderType = "unknow";
                            if(JfConstants.JF_ORDER_IS_NEW.equals(order.getString("isNew"))){
                                orderType = "新订单";
                            }else{
                                orderType = "旧订单";
                            }
                            	JfLog.info(LOG,"响应数据解析[" + (i++) + "]：新订单类型="+orderType);
                        } else if (apiType == JfTest.I_NOTIFY_ORDERCREATED) {//3-签单
                            String rFrozenAmount = order.getString("frozenAmount");
                            	JfLog.info(LOG,"响应数据解析[" + (i++) + "]：签单成功，冻结金额" + rFrozenAmount);
                        } else if (apiType == JfTest.I_NOTIFY_ORDERCANCELED) {//4-取消订单
                            String rReleaseAmount = order.getString("releaseAmount");
                            	JfLog.info(LOG,"响应数据解析[" + (i++) + "]：订单撤消成功，释放冻结金额" + rReleaseAmount);
                        } else if (apiType == JfTest.I_NOTIFY_AGREETOPAY) {//5-同意支付
                        	JfLog.info(LOG,"响应数据解析[" + (i++) + "]：订单同意支付成功");
                        } else if (apiType == JfTest.I_NOTIFY_ORDERFINISHED) {//6-结束订单
                            String rReleaseAmount = order.getString("releaseAmount");
                            JfLog.info(LOG,"响应数据解析[" + (i++) + "]：订单关闭成功，释放冻结金额" + rReleaseAmount);
                        }
                    }
                    
                    	JfLog.info(LOG,"数据验签成功，处理数据-end");
                } else {
                    String error = json.getString("error");
                    throw new JfPayApiException("响应失败：resultcode=" + resultcode + ",error=" + error);
                }
            } else {
                throw new JfPayApiException("响应失败：无法接收响应数据!");
            }
        } catch (Exception e) {
        		JfLog.error(LOG,e.getMessage(),e);
            throw new JfPayApiException(e);
        }
    }
    
    /**
     * 红包相关接口响应处理
     * @param apiType
     * @param data
     * @param url
     * @throws JfPayApiException
     */
    public static void postBonus(int apiType,String data,String url) throws JfPayApiException{
        try {
            RestClient client = new RestClient();
            List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
            String datasign = JfPayBonusSecurity.getInstance().signAPI(data);
            
            	JfLog.info(LOG,"****** data:"+data);
            	JfLog.info(LOG,"****** datasign:"+datasign);
            nvps.add(new BasicNameValuePair("data", data));
            nvps.add(new BasicNameValuePair("datasign", datasign));
            
            String result = client.doPost(url, null, nvps, 2,true);
            
            	JfLog.info(LOG,"***********************************************************************\n");
            	JfLog.info(LOG,"result:\n" + result);
            JSONObject json = JSONObject.fromObject(result);
            if (json != null) {
                //验证响应数据签名
            	
            		JfLog.info(LOG,"res.data"+json.getString("data"));
                
                String rData = new String(Base64.decode(json.getString("data").getBytes(JfConstants.UTF8)),JfConstants.UTF8);
                String rDatasign = json.getString("datasign");
                
                	JfLog.info(LOG,"rData="+rData);
                	JfLog.info(LOG,"rDatasign="+rDatasign);
                if (!JfPayBonusSecurity.getInstance().checkAPIResponseSignature(result)) {
                		JfLog.error(LOG,"数据验签失败");
                    throw new JfPayApiException("数据验签失败！");
				} else {
					JfLog.info(LOG, "===============》 返回数据签名验证成功！！！！！");
				}
                
                //处理响应代码
                String resultcode = json.getString("resultcode");
                if (JfApi.RC_SUCCESS == Integer.parseInt(resultcode)) {
                		JfLog.info(LOG,"响应成功，开始处理响应数据");
                    JSONArray arrayData = JSONArray.fromObject(rData);
                    if (arrayData==null||arrayData.isEmpty()) {
                    		JfLog.error(LOG,"响应数据解析失败");
                    } else {
                        JSONObject bonus = arrayData.getJSONObject(0);
                        int i = 0;
                        if (apiType == 1) {
                            // 查询账户余额
                        } else if (apiType == 2) {
                            // 查询个人收到红包累计金额
                            String rJfId = bonus.getString("jfId");
                            
                            	JfLog.info(LOG,"响应数据解析[" + (i++) + "]：红包bonusId=" + rJfId);
                        } else if (apiType == 3) {
                            // 创建红包
                            String rBonusId = bonus.getString("bonusId");
                            
                            	JfLog.info(LOG,"响应数据解析[" + (i++) + "]：红包bonusId=" + rBonusId);
                        } else if (apiType == 4) {
                            // 抢红包，记账，转账
                            String rJfId = bonus.getString("jfId");
                            
                            	JfLog.info(LOG,"响应数据解析[" + (i++) + "]：红包bonusId=" + rJfId);
                        } else if (apiType == 5) {
                            // 红包结束
                            String rBonusId = bonus.getString("bonusId");
                            
                            	JfLog.info(LOG,"响应数据解析[" + (i++) + "]：红包bonusId=" + rBonusId);
                        }
                        String rAmount = bonus.getString("amount");
                        
                        	JfLog.info(LOG,"响应数据解析[" + (i++) + "]：amount=" + rAmount);
                    }
                    
                    	JfLog.info(LOG,"数据验签成功，处理数据-end");
                } else {
                    String error = json.getString("error");
                    throw new JfPayApiException("响应失败：resultcode=" + resultcode + ",error=" + error);
                }
            } else {
                throw new JfPayApiException("响应失败：无法接收响应数据!");
            }
        } catch (Exception e) {
        		JfLog.error(LOG,e.getMessage(),e);
            throw new JfPayApiException(e);
        }
    }
    
    /**
     * 红包相关接口响应处理
     * @param apiType
     * @param data
     * @param url
     * @throws JfPayApiException
     */
    public static String postEbcMock(int apiType,String json,String url) throws JfPayApiException{
        try {
            RestClient client = new RestClient();
            List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();
            
            	JfLog.info(LOG,"****** json:"+json);
            nvps.add(new BasicNameValuePair("json", json));
            
            String result = client.doPost(url, null, nvps, 2,true);
            
            	JfLog.info(LOG,"***********************************************************************\n");
            	JfLog.info(LOG,"result:\n" + result);
            JSONObject data = JSONObject.fromObject(result);
            if (data != null) {
                //验证响应数据签名
            	
            		JfLog.info(LOG,"res.data"+data.toString());
                //处理响应代码
                String returncode = data.getString("returncode");
                if ("00".equals(returncode)) {
                	
                		JfLog.info(LOG,"响应成功，开始处理响应数据");
                    if (apiType == 1) {
                        String automobile = data.getString("automobile");
                        
                        	JfLog.info(LOG,"automobile = " + automobile);
                    } else if (apiType == 4) {
                        String balance = data.getString("balance");
                        
                        	JfLog.info(LOG,"balance = " + balance);
                    } 
                    
                    	JfLog.info(LOG,"数据验签成功，处理数据-end");
                } else {
                    String errtext = data.getString("errtext");
                    	JfLog.error(LOG,"响应失败：returncode=" + returncode + ",error=" + errtext);
                }
            } else {
            		JfLog.error(LOG,"响应失败：无法接收响应数据!");
            }
            return result;
        } catch (Exception e) {
        		JfLog.error(LOG,e.getMessage(),e);
            throw new JfPayApiException(e);
        }
    }
    
    /**
     * EBC回调
     * @param apiType
     * @param json
     * @param url
     * @return
     * @throws JfPayApiException
     */
    public static String postEbcMockCallback(int apiType,List<BasicNameValuePair> nvps,String url) throws JfPayApiException{
        try {
            RestClient client = new RestClient();
            String result = client.doPost(url, null, nvps, 2,true);
            	JfLog.info(LOG,"apiType="+apiType);
            	JfLog.info(LOG,"***********************************************************************\n");
            	JfLog.info(LOG,"result:\n" + result);
            JSONObject data = JSONObject.fromObject(result);
            if (data != null) {
                //验证响应数据签名
            		JfLog.info(LOG,"res.data"+data.toString());
                //处理响应代码
                String resultcode = data.getString("resultcode");
                if ("00".equals(resultcode)) {
                		JfLog.info(LOG,"响应成功，开始处理响应数据");
                		JfLog.info(LOG,"数据验签成功，处理数据-end");
                } else {
                    String errtext = data.getString("errtext");
                    throw new JfPayApiException("响应失败：returncode=" + resultcode + ",error=" + errtext);
                }
            } else {
                throw new JfPayApiException("响应失败：无法接收响应数据!");
            }
            return result;
        } catch (Exception e) {
        		JfLog.error(LOG,e.getMessage(),e);
            throw new JfPayApiException(e);
        }
    }
    
    public static String getFirstNum(int n){
        return String.valueOf(n).substring(0,1);
    }
}

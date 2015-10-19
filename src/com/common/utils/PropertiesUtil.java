package com.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * date:2014-12-01
 * @author zhangchengjun
 * <p>M2.properties 工具类</p>
 *
 */
public class PropertiesUtil {
    
    private static final Logger LOG = LoggerFactory.getLogger(PropertiesUtil.class);
    
    private PropertiesUtil(){
    }
    
	/**
	 * date:2014-12-01
	 * @author zhangchengjun
	 * @return Properties
	 */
	public static Properties getM2Properties(){
		InputStream is = null;
		Properties pro=new Properties();
		try {
		    is = PropertiesUtil.class.getClassLoader().getResourceAsStream("context_path.properties");
			pro.load(is);
		} catch (FileNotFoundException e) {
		    JfLog.error(LOG,e.getMessage(),e);
		} catch (IOException e) {
		    JfLog.error(LOG,e.getMessage(),e);
		}finally{
		    if(is!=null){
		        try {
                    is.close();
                } catch (IOException e) {
                    JfLog.error(LOG,e.getMessage(),e);
                }
		    }
		}
		return pro;
	}
	/**
	 * date:2014-12-01
	 * @author zhangchengjun
	 * @param key
	 * @param defaultValue
	 * @return String
	 */
	public static String getM2Value(String key,String defaultValue){
		String value=defaultValue;
		if(!StringUtils.isEmpty(key)){
			value=getM2Properties().getProperty(key);
		}
		return value;
	}
	
}

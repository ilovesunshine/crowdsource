package com.common.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CommonUtils {
	
	public static Date parseDate(String timeStamp) {
		return timeStamp == null ? null : new Date(Long.parseLong(timeStamp));
	}
	
	public static boolean isNullOrEmpty(String value){
		return null==value || value.isEmpty();
	}
	public static String turnNumberToString(BigDecimal param,String pattern){
		if(null==param){
			return "";
		}
		Date date = new Date(param.longValue());
		DateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
}

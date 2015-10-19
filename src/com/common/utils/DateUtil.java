/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @Title: DateUtil.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2013-5-6 下午1:16:58
 * @author FengHaiBing
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class DateUtil {
    
	private static final Logger LOG = LoggerFactory.getLogger(DateUtil.class);
    
    /**
     * 年格式
     */
    public static final String YEAR="yyyy";
    
    /**
     * 年月格式
     */
    public static final String YEARMONTH="yyyyMM";
    
    /**
     * 年月-格式
     */
    public static final String YEAR_MONTH="yyyy-MM";
    
    /**
     * 日期格式yyyyMMdd
     */
    public static final String DATEFORMAT = "yyyyMMdd";
    
    /**
     * 日期格式yyyy-MM-dd
     */
    public static final String DATEYMD = "yyyy-MM-dd";
    
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DATEYMDHMS = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * MM-dd HH:mm
     */
    public static final String DATEMDHM = "MM-dd HH:mm";
    
    /**
     * MM-dd HH:mm
     */
    public static final String DATEMD = "MM-dd";
    
    private DateUtil(){
    }
    
    /**
     * 
     * @param date
     * @return
     * @Created on 2013-5-7 下午7:04:27
     * @author FengHaiBing
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static String formatDate(Date date) {
        return formatDate(date, DATEYMD);
    }
    
    public static Timestamp formatTimeStamp(Timestamp timestamp){
    	if(null == timestamp){
    		return null;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATEYMDHMS);
		return Timestamp.valueOf(sdf.format(timestamp));
    }
    
    /**
     * 
     * @param date
     * @param fmt
     * @return
     * @Created on 2013-5-7 下午7:05:50
     * @author FengHaiBing
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static String formatDate(Date date, String fmt) {
        if(null == date) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(fmt);
        String dateStr = df.format(date);
        return dateStr;
    }
    
    /**
     * yyyy-MM
     * @param month 
     * @return
     */
    public static String formatMonth(Date month){
        return formatDate(month, YEAR_MONTH);
    }
    
    /**
     * yyyyMM
     * @param month 
     * @return
     */
    public static String formatMonthShort(Date month){
        return formatDate(month, YEARMONTH);
    }
    
    /**
     * 将普通日期字符格式化为yyyy年MM月
     * @param dateStr
     * @param fmt
     * @return
     */
    public static String formatBillDate(String dateStr,String fmt){
    	Date date = parseDate(dateStr);
    	return formatDate(date,fmt);
    }
    
    /**
     * 将yyyy年MM月格式的账单日期转换为yyyy-mm-01的账单日期
     * @param dateStr
     * @param fmt
     * @return
     */
    public static java.sql.Date formatBillDateMonth(String dateStr,String fmt){
        Date dt = DateUtil.parseDate(dateStr, fmt);
        return new java.sql.Date(dt.getTime());
    }
    
    /**
     * 
     * @param date
     * @return
     * @Created on 2013-5-7 下午7:04:33
     * @author FengHaiBing
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static Date parseDate(String date) {
        return parseDate(date, DATEYMD);
    }
    
    public static java.sql.Date parseSqlDate(String date) {
        return parseSqlDate(date, DATEYMD);
    }

    public static java.sql.Date parseSqlDate(String date, String fmt) {
        if(date == null) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(fmt);
        try {
            return new java.sql.Date(df.parse(date).getTime());
        } catch (ParseException e) {
           JfLog.error(LOG,e.getMessage(),e);
        }
        return null;
    }
    /**
     * 
     * @param date
     * @param fmt
     * @return
     * @Created on 2013-5-7 下午7:06:52
     * @author FengHaiBing
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static Date parseDate(String date, String fmt) {
        if(date == null || "".equals(date)) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(fmt);
        try {
            return df.parse(date);
        } catch (ParseException e) {
           JfLog.error(LOG,e.getMessage(),e);
        }
        return null;
    }

    /**
     * 
     * @param date
     * @return
     * @Created on 2013-5-7 下午7:04:41
     * @author FengHaiBing
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static Date getTomorrow(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        if(date != null) {
            gc.setTime(date);
        }
        
        gc.add(Calendar.DAY_OF_MONTH, 1);
        return gc.getTime();
    }
    
    /**
     * 获取明天日期的字符串格式 yyyy-MM-dd
     * @Created On 2013-11-29 14:50
     * @author 吕佳诚
     * @return yyyy-MM-dd
     */
    public static String getTommorrow(){
    	GregorianCalendar gc = new GregorianCalendar();
    	gc.setTime(new Date());
    	gc.add(Calendar.DAY_OF_MONTH, 1);
    	SimpleDateFormat sdf = new SimpleDateFormat(DATEYMD);
    	String time = sdf.format(gc.getTime());
    	return time;
    }
    
    /**
     * 
     * @param date
     * @return
     * @Created on 2013-5-7 下午7:04:41
     * @author FengHaiBing
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static String getYesterday(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        if(date != null) {
            gc.setTime(date);
        }
        
        gc.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(DATEYMDHMS);
        String time = sdf.format(gc.getTime());
        return time;
    }
    
    /**
     * 
     * @param date
     * @return
     * @Created on 2013-5-7 下午7:04:41
     * @author 吕佳诚
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static Date getYesterday(Timestamp currentTimestamp ) {
        GregorianCalendar gc = new GregorianCalendar();
        if(currentTimestamp != null) {
	        gc.setTime(currentTimestamp);
	    } 
        
        gc.add(Calendar.DAY_OF_MONTH, -1);
        return gc.getTime();
    }
    
    /**
     * 获取昨天日期的字符串格式 yyyy-MM-dd
     * @Created On 2013-11-29 14:50
     * @author 吕佳诚
     * @return yyyy-MM-dd
     */
    public static String getYesterday(){
    	GregorianCalendar gc = new GregorianCalendar();
    	gc.setTime(new Date());
    	gc.add(Calendar.DAY_OF_MONTH, -1);
    	SimpleDateFormat sdf = new SimpleDateFormat(DATEYMD);
    	String time = sdf.format(gc.getTime());
    	return time;
    }
    
    public static Date getNexTenMins() { 
        Date d=new Date(); 
        d.setMinutes(d.getMinutes()+10); 
        return d; 
    } 
    
    /**
     * @author ShenYuede
     * @param date 原始日期
     * @param n 原始日期加上几天
     * @return 原始日期加上几天后的新日期
     */
    public static Date addDate(Date date,int n) {
        GregorianCalendar gc = new GregorianCalendar();
        if(date != null) {
            gc.setTime(date);
        }       
        gc.add(Calendar.DAY_OF_MONTH, n);        
        return gc.getTime();
    }
    
    /**
     * 月份加法
     * @author ShenYuede
     * @param date 原始日期
     * @param n 原始日期加上几个月
     * @return
     */
    public static Date addMonth(Date date,int n) {
        GregorianCalendar gc = new GregorianCalendar();
        if(date != null) {
            gc.setTime(date);
        }       
        gc.add(Calendar.MONTH, n);        
        return gc.getTime();
    }
    
    /**
     * 
     * @param date
     * @return
     * @Created on 2013-5-7 下午7:04:47
     * @author FengHaiBing
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static boolean isWeekEnd(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        if(date != null) {
            gc.setTime(date);
        }
        
        int dayOfWeek = gc.get(Calendar.DAY_OF_WEEK);
        return (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) ? true : false;
        
    }
    
    /**
     * 得到本周的周一日期
     * 
     * @param date
     * @return
     * @Created on 2013-9-12 下午5:09:50
     * @author FengHaiBing
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static Date getMondayOfWeek(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        if(date != null) {
            gc.setTime(date);
        }
        
        int dayOfWeek = gc.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == Calendar.MONDAY) {
            return date;
        }
        
        int intervalDay = dayOfWeek - Calendar.MONDAY;
        return addDate(date, -intervalDay);
    }
    
    /**
     * 得到本周的周一日期
     * 
     * @param date
     * @return
     * @Created on 2013-9-12 下午5:09:50
     * @author FengHaiBing
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static Date getMondayOfPrevWeek(Date date) {
        return addDate(getMondayOfWeek(date), -7);
    }
    
    /**
     * 得到本周二日期
     * 
     * @param date
     * @return
     * @Created on 2013-9-12 下午5:09:50
     * @author FengHaiBing
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static Date getTuesdayOfWeek(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        if(date != null) {
            gc.setTime(date);
        }
        
        int dayOfWeek = gc.get(Calendar.DAY_OF_WEEK);
        
        int intervalDay = dayOfWeek - Calendar.TUESDAY;
        return addDate(date, -intervalDay);
        
    }
    
    /**
     * 得到本周三日期
     * 
     * @param date
     * @return
     * @Created on 2015-9-02 
     * @author JCJ
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static Date getWedesdayOfWeek(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        if(date != null) {
            gc.setTime(date);
        }
        
        int dayOfWeek = gc.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == Calendar.WEDNESDAY) {
            return date;
        }
        
        int intervalDay = dayOfWeek - Calendar.WEDNESDAY;
        return addDate(date, -intervalDay);
        
    }
    
    /**
     * 得到本周四日期
     * 
     * @param date
     * @return
     * @Created on 2015-9-02 
     * @author JCJ
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static Date getThursdayOfWeek(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        if(date != null) {
            gc.setTime(date);
        }
        
        int dayOfWeek = gc.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == Calendar.THURSDAY) {
            return date;
        }
        
        int intervalDay = dayOfWeek - Calendar.THURSDAY;
        return addDate(date, -intervalDay);
        
    }
    
    /**
     * 得到本周四日期
     * 
     * @param date
     * @return
     * @Created on 2015-9-02 
     * @author JCJ
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static Date getFridayOfWeek(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        if(date != null) {
            gc.setTime(date);
        }
        
        int dayOfWeek = gc.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == Calendar.FRIDAY) {
            return date;
        }
        
        int intervalDay = dayOfWeek - Calendar.FRIDAY;
        return addDate(date, -intervalDay);
        
    }
    
    /**
     * 得到本周日日期
     * 
     * @param date
     * @return
     * @Created on 2013-10-12 下午5:09:50
     * @author FengHaiBing
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static Date getSundayOfWeek(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        if(date != null) {
            gc.setTime(date);
        }
        
        int dayOfWeek = gc.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == Calendar.SUNDAY) {
            return date;
        }
        int intervalDay = dayOfWeek - Calendar.SUNDAY;
        return addDate(date, -intervalDay);
    }
    
    /**
     * 按周一为第一天计算周日
     * @param date
     * @return
     */
    public static Date getSundayOfWeekCN(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        if(date != null) {
            gc.setTime(date);
        }
        
        int dayOfWeek = gc.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == Calendar.SUNDAY) {
            //如果指定日期为周日则直接返回
            return date;
        }
        int intervalDay = dayOfWeek - Calendar.SUNDAY -7;
        return addDate(date, -intervalDay);
    }
    
    /**
     * 得到本周最后一天 即周六
     * 
     * @param date
     * @return
     * @Created on 2015-08-27 下午1:09:50
     * @author JCJ
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static Date getSaturdayOfWeek(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        if(date != null) {
            gc.setTime(date);
        }
        
        int dayOfWeek = gc.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek == Calendar.SATURDAY) {
            return date;
        }
        
        int intervalDay = dayOfWeek - Calendar.SATURDAY;
        return addDate(date, -intervalDay);
        
    }
    
    /**
     * 得到下周周日
     * 
     * @param date
     * @return
     * @Created on 2015-09-01 下午1:09:50
     * @author JCJ
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static Date getSunDayOfNextWeek(Date date) {
        return addDate(getSundayOfWeek(date), 7);
        
    }
    
    /**
     * 得到上周周六
     * 
     * @param date
     * @return
     * @Created on 2015-09-01 下午1:09:50
     * @author JCJ
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static Date getSaturDayOfPreWeek(Date date) {
        return addDate(getSaturdayOfWeek(date) , -7);
        
    }
    
    /**
     * 今天是一年中的第几周
     * 
     * @param date
     * @return
     * @Created on 2015-08-22 中午11:09:50
     * @author FengHaiBing
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static int getWeekOfYear() {
        return getWeekOfYear(new Date());
    }
    
    /**
     * 
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar cal=Calendar.getInstance();

        cal.setTime(date);

        return cal.get(Calendar.WEEK_OF_YEAR);
    }
    
    /**
     * 
     * @param date yyyy-MM-dd
     * @return
     */
    public static int getWeekOfYear(String date) {
        Calendar cal=Calendar.getInstance();

        cal.setTime(parseDate(date));

        return cal.get(Calendar.WEEK_OF_YEAR);
    }
    
    /**
     * 今天是一年中的第几周
     * 
     * @param date
     * @return
     * @Created on 2015-12-01 
     * @author FengHaiBing
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static int getWeekOfYearCN() {
        return getWeekOfYearCN(new Date());
    }
    
    /**
     * 得到中国的第几周(周一为本周的第一天)
     * 
     * @param date
     * @return
     * @date 2014-12-01
     * 
     * @author FengHaiBing
     */
    public static int getWeekOfYearCN(Date date) {
        Calendar cal=Calendar.getInstance();

        cal.setTime(date);
        //set the first day of the week
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        
        return cal.get(Calendar.WEEK_OF_YEAR);
    }
    
    /**
     * 得到中国的第几周(周一为本周的第一天)
     * 
     * @param date
     * @return
     * @date 2014-12-01
     * 
     * @author FengHaiBing
     */
    public static int getWeekOfYearCN(String date) {
        return getWeekOfYearCN(parseDate(date));
    }
    
    /**
     * 得到下一月日期
     * @author FengHaiBing 
     * @param date
     * @return
     * 2006-4-28 14:11:41
     */
    public static Date getNextMonth(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.MONTH, 1);
        return gc.getTime();
    }
    
    
    /**
     * 得到下一月第一天
     * @author JiaChangjian 
     * @param date
     * @return
     */
    public static String getNextMonthFirstDay() {
    	GregorianCalendar gc = new GregorianCalendar(); 
    	Date date = new Date();
        String[] dateStr = formatDate(date,YEAR_MONTH).split("-");
        int year = Integer.parseInt(dateStr[0]);
        int month = Integer.parseInt(dateStr[1]);
        gc.set(year, month, 1);
        SimpleDateFormat sdf = new SimpleDateFormat(DATEYMD);
        return sdf.format(gc.getTime());
    }
    
    /**
     * 得到一个月之前的日期
     * @author ShenYuede 
     * @param date
     * @return
     */
    public static Date getPriorMonth(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.MONTH, -1);
        return gc.getTime();
    }
    
    /**
     * 得到一个月之前的日期
     * @author caoyanbin 
     * @param date
     * @return
     */
    public static Timestamp getPriorMonthT(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.MONTH, -1);
        gc.get(Calendar.MONTH);
        return new Timestamp(gc.getTimeInMillis());
    }
    
    /**
     * 得到月份号
     * @return
     * 
     * @author FengHaiBing
     */
    public static int getMonth() {
        return getMonth(new Date());
    }
    
    /**
     * 得到月份号
     * @param date
     * @return
     * 
     * @author FengHaiBing
     */
    public static int getMonth(Date date) {
        if(null == date) {
            return 0;
        }
        
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        
        return gc.get(Calendar.MONTH);
    }
    
    /**
     * 得到一个月之前的日期
     * @author ShenYuede 
     * @param date
     * @return
     */
    public static String getNextMonthStr(int i) {
        Date date = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.MONTH, i);
        return formatDate(gc.getTime(),"M月");
    }
    
    /**
     * 得到当月的第一天
     * @author ShenYuede 
     * @param date
     * @return
     */
    public static Date getFirtDateOfMonth(Date date){
        GregorianCalendar gc = new GregorianCalendar();        
        String[] dateStr = formatDate(date,YEAR_MONTH).split("-");
        int year = Integer.parseInt(dateStr[0]);
        int month = Integer.parseInt(dateStr[1]);
        gc.set(year, month-1, 1);
        return gc.getTime();
    }
    
    /**
     * 得到当月最后一天
     * 
     * @param date
     * @return
     * @Created on 2013-6-6 下午3:48:21
     * @author FengHaiBing
     * @version $Revision: 1.0 $
     * @since 1.0
     */
    public static Date getLastDayOfMonth(Date date) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.MONTH, 1);
        String dateStr = formatDate(gc.getTime(),YEAR_MONTH) + "-01";
        gc.setTime(parseDate(dateStr));
        gc.add(Calendar.DAY_OF_MONTH, -1);
        
        return gc.getTime();
    }
    
   /**
    * 获取当前月的前后(num-1)/2个个 共num个月
    * @param num num个月 (num-1)/2左右偏移量
    * @return
    */
    public static String [] getMonths(int num){
        String [] months=new String[num];
        for(int i=0;i<num;i++){
            
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(DATEYMD);
            String time = sdf.format(date);
            String[] item = time.split("-");
            int year  = Integer.parseInt(item[0]);
            int month = Integer.parseInt(item[1]);
            int targetMonth=month - (num-1)/2+i;//本次计算的月
            if(targetMonth <= 0){
                month = targetMonth+ 12 ;
                year = year -1;
            }else if(targetMonth >12){
                month = targetMonth-12;
                year = year +1;
            }  else {
                month = targetMonth;
            }
            
            months[i]=year + "-" + "00".substring((""+month).toString().length())+month ;
        }
       
        return months;  
    }
    
    /**
     * 以yyyy-MM-dd HH:mm:ss格式生成的时间戳
     * @return
     */
    public static Timestamp getCurrentTimestamp(){
        return Timestamp.valueOf(DateUtil.getCurrentTimeStr());
    }
    
    /**
     * 获取当前日期，格式yyyyMMdd
     * @return
     */
    public static String getCurrentDateShort(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
        return sdf.format(date);
    }
    
    /**
     * 获取当前日期，格式yyMMdd
     * @return
     */
    public static String getCurrentDayShort(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
        return sdf.format(date);
    }
    
    /**
     * 获取当前日期，格式yyyy-MM-dd
     * @return
     */
    public static String getCurrentDateStr(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATEYMD);
        return sdf.format(date);
    }
    
    /**
     * 返回Long型 当前时间戳(不带毫秒)
     */
    public static Long getCurrentTimeLong(){
        Timestamp timestamp = DateUtil.getCurrentTimestamp();
        return timestamp.getTime();
    }
    
    /**
     * 获取当前日期，格式yyyy-MM-dd
     * @return
     */
    public static String getCurrentDateStr(String dateFormat){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }
    
    /**
     * 获取当前日期，格式 标准格式 例：Wed Sep 03 00:00:00 CST 2014
     * @author JCJ
     * @return
     */
    public static String getCurrentNormalDateStr(){
        Date date = new Date();
        return String.valueOf(date);
    }
    
    /**
     * 获取当前时间，格式yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentTimeStr(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATEYMDHMS);
        return sdf.format(date);
    }
    
    /**
     * 获取周日到周六的日期名称
     * @param week
     * @return
     */
    public static String getMonthDayStr(int week){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0){
        	dayOfWeek = 7;
        }
        int idx = 0;
        c.add(Calendar.WEEK_OF_MONTH, idx); //idx 参数，0为当前，1为下周 -1为上周以此类推      
        c.add(Calendar.DATE, -dayOfWeek + week);//week=0-6,即周日,周一-周六
        return sdf.format(c.getTime());
    }
    
    private static final String[] weekNames = {"周六","周日","周一","周二","周三","周四","周五"};
    public static String getMonthDayName(int week){
        if(week>=0&&week<=7){
            return weekNames[week];
        }else{
            return "";
        }
    }
    
    /**
     * 获取未来第i个月的日期
     * @param i
     * @return
     */
    public static String getNextDate(int i){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(date);
        Calendar c = Calendar.getInstance();
        c.setTime(parseDate(time));
        c.add(Calendar.MONTH,i);//加两个月
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号
        return sdf.format(c.getTime());
    }
    /**
     * 获得指定日期的第几天
     * @param date
     * @param i
     * @return
     */
    public static String getNextDate(Date dt,int i){
        if(dt==null){
            return null;
        }
        Date date = (Date)dt.clone();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,i);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
    
    
    /**
     * 获得指定日期的第几天
     * @param date
     * @param i
     * @return
     */
    public static String getNextDate(Date dt,int i,SimpleDateFormat formatter){
        if(dt==null){
            return null;
        }
        Date date = (Date)dt.clone();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,i);//把日期往后增加一天.整数往后推,负数往前移动
        date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
        return formatter.format(date);
    }
    /**  
     *  取得指定月份的最后一天  
     *  
     *  @param  strdate  String  
     *  @return  String  
     */  
    public static String getMonthEnd(String strdate) {
        java.util.Date date = parseDate(getMonthBegin(strdate));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        return formatDate(calendar.getTime());
    }  
   
   /**  
    *  取得指定月份的第一天  
    *  
    *  @param  strdate  String  
    *  @return  String  
    */  
    public static String getMonthBegin(String strdate) {
        java.util.Date date = parseDate(strdate);
        return formatDate(date, "yyyy-MM") + "-01";
    }  
    /**
     * 获取当前及后(num-1)个月  个 共num个月
     * @param num 
     * @return
     */
     public static String [] getMonthsBeginCurrentMonth(int num){
         String [] months=new String[num];
         for(int i=0;i<num;i++){
             Date date = new Date();
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
             String time = sdf.format(date);
             String[] item = time.split("-");
             int year  = Integer.parseInt(item[0]);
             int month = Integer.parseInt(item[1]);
             int targetMonth=month +i;//本次计算的月
             if(targetMonth >12){
                 month = targetMonth-12;
                 year = year +1;
             }  else {
                 month = targetMonth;
             }
             months[i]=year + "-" + "00".substring((""+month).toString().length())+month ;
         }
         return months;  
     }
     
     /**
      * 计算两个日期的日期差天数
      * @param max
      * @param min
      * @return
      */
    public static long getDateDiffDays(Date max, Date min) {
        if (max == null || min == null) {
            return -1;
        }
        long diff = max.getTime() - min.getTime();
        return diff / (1000 * 60 * 60 * 24);
    }
     
     /**
      * 计算两个日期的时间差
      * @param from
      * @param to
      * @return
      */
     public static String getTimeDifStr(Long from,Long to){
         long l = from - to;
         long day=l/(24*60*60*1000);
         long hour=l/(60*60*1000)-day*24;
         long min=(l/(60*1000))-day*24*60-hour*60;
         long s=l/1000-day*24*60*60-hour*60*60-min*60;
         return ""+day+"天"+hour+"小时"+min+"分"+s+"秒";
     }
     
     /**
      * 获取当前日期，格式yyyy-MM
      * @return
      */
     public static String getCurrentMonth(){
         Date date = new Date();
         SimpleDateFormat sdf = new SimpleDateFormat(YEAR_MONTH);
         return sdf.format(date);
     }
     
     /**
      * 获取当前日期，格式yyyyMM
      * @return
      */
     public static String getCurrentMonthShort(){
         return formatMonthShort(new Date());
     }
     
     /**
      *  判断"子任务结束日期"是否大于"主任务结束日期" 
      * @author 吕佳诚
      * @param parentTaskDate 主任务结束日期
      * @param subTaskDate 子任务结束日期
      * @return boolean 
      */
     public static boolean isSubTaskDateLaterThanParentTaskDate(Date parentTaskDate ,Date subTaskDate){
             return subTaskDate.after(parentTaskDate) && !subTaskDate.equals(parentTaskDate) ;  
     }
     
     /**
      *  判断"子任务开始日期"是否小于"主任务开始日期" 
      * @author 吕佳诚
      * @param parentTaskDate 主任务结束日期
      * @param subTaskDate 子任务结束日期
      * @return boolean 
      */
     public static boolean isSubTaskDateEarlyThanParentTaskDate(Date parentTaskDate ,Date subTaskDate){
         return subTaskDate.after(parentTaskDate) && !subTaskDate.equals(parentTaskDate) ; 
     }
     
     /**
      * 获取两个时间段内的日期 例如[2014-03-21, 2014-02-17, 2014-02-27]
      * @author JiaChangjian
      * @param Date startDate
      * @param Date endDate
      */
     public static Set<String> getDays(Date startDate,Date endDate){
        Set<String> days=new TreeSet<String>();//用来存储返回的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        
        double  between=(endDate.getTime()-startDate.getTime())/1000.0;//除以1000是为了转换成秒      
        double  day=between/(24*3600.0);
        
        for(int i = 0;i<=day;i++){
            Calendar cd = Calendar.getInstance();   
            cd.setTime(startDate);   
            cd.add(Calendar.DATE, i); 
            days.add(sdf.format(cd.getTime()));
        }      
         return days;
     }
     
     /**
      * 比较两个日期大小
      * @param args
      */
    public static boolean dateCompare(Date dat1, Date dat2) {
        DateFormat df = new SimpleDateFormat(DateUtil.DATEYMD);
        Date d1 = DateUtil.parseDate(df.format(dat1));
        Date d2 = DateUtil.parseDate(df.format(dat2));
        
        return d1.compareTo(d2) >= 0;
    }
     
    /**
     * @param day
     * @return
     */
    public static String getLastPayDate(String day) {
        if(StringUtils.isNullStr(day)){
        	return null;
        }
        if(day.length()==1){
            return getCurrentMonth()+"-0"+day;
        }else if(day.length()==2){
            return getCurrentMonth()+"-"+day;
        }else{
            return null;
        }
    }
    
    /**
     * 按照格式返回日期字符串
     * @param date
     * @param fmt
     * @return
     */
    public static String parseStringDate(String date, String fmt) {
        if(date == null || "".equals(date)) {
            return null;
        }
        DateFormat df = new SimpleDateFormat(fmt);
        return df.format(DateUtil.parseDate(date));
    }
    
}

/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.view;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.chinasofti.ro.bizframework.modules.json.JsonConfig;
import com.chinasofti.ro.bizframework.modules.json.processors.JsonValueProcessor;

/**
 * @Title: DateJsonValueProcessor.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-15 下午12:08:05
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class DateJsonValueProcessor implements JsonValueProcessor {

    public static final String Default_DATE_PATTERN ="yyyy-MM-dd";
    private DateFormat dateFormat ;

    public DateJsonValueProcessor(String datePattern) {
        try {
            dateFormat = new SimpleDateFormat(datePattern);
        } catch (Exception e) {
            dateFormat = new SimpleDateFormat(Default_DATE_PATTERN);
        }
    }
   
    private Object process(Object value){
        //return dateFormat.format((Date)value);
        try {
            if (value instanceof Date) {
                dateFormat.format((Date)value);
            }
            return value == null ? "" : value.toString();
        } catch (Exception e) {
            return "";
        }
    }
    /* (non-Javadoc)
     * @see com.chinasofti.ro.bizframework.modules.json.processors.JsonValueProcessor#processArrayValue(java.lang.Object, com.chinasofti.ro.bizframework.modules.json.JsonConfig)
     */
    @Override
    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        return process(value);
    }

    /* (non-Javadoc)
     * @see com.chinasofti.ro.bizframework.modules.json.processors.JsonValueProcessor#processObjectValue(java.lang.String, java.lang.Object, com.chinasofti.ro.bizframework.modules.json.JsonConfig)
     */
    @Override
    public Object processObjectValue(String key, Object value,JsonConfig jsonConfig) {
        return process(value);
    }

}

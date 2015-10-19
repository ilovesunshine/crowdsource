/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.view;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.chinasofti.ro.bizframework.modules.json.ezmorph.MorphException;
import com.chinasofti.ro.bizframework.modules.json.ezmorph.object.AbstractObjectMorpher;

/**
 * 将json串中的日期字符串转换为bean中的Timestamp
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-15 下午12:11:12
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class TimestampMorpher extends AbstractObjectMorpher {

    /*** 日期字符串格式*/
    private String[] formats;
    public TimestampMorpher(String[] formats) {
        this.formats = formats;
    }
    public boolean supports( Class clazz ){
        return String.class.isAssignableFrom( clazz );
    }
    /* (non-Javadoc)
     * @see com.chinasofti.ro.bizframework.modules.json.ezmorph.ObjectMorpher#morph(java.lang.Object)
     */
    @Override
    public Object morph(Object value) {
        if( value == null){
            return null;
        }
        if( Timestamp.class.isAssignableFrom(value.getClass()) ){
            return (Timestamp) value;
        }
        if( !supports( value.getClass()) ){
            throw new MorphException( value.getClass() + " 是不支持的类型");
        }
        String strValue=(String) value;
        SimpleDateFormat dateParser=null;
        for( int i = 0; i < formats.length ; i++ ){
            if( null == dateParser ){
                dateParser=new SimpleDateFormat(formats[i]);
            }else{
                dateParser.applyPattern(formats[i]);
            }
            try{
                return new Timestamp( dateParser.parse( strValue.toLowerCase()).getTime() );}
            catch (ParseException e) {
                //e.printStackTrace();
            }
        }
        return null;
        }

    /* (non-Javadoc)
     * @see com.chinasofti.ro.bizframework.modules.json.ezmorph.Morpher#morphsTo()
     */
    @Override
    public Class morphsTo() {
        return Timestamp.class;
    }

}

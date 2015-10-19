/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.csi.jf.common;

import java.sql.Timestamp;

import com.chinasofti.ro.bizframework.modules.json.JSONObject;
import com.chinasofti.ro.bizframework.modules.json.JsonConfig;
import com.chinasofti.ro.bizframework.modules.json.util.JSONUtils;
import com.common.view.DateJsonValueProcessor;
import com.common.view.TimestampMorpher;

/**
 * @Title: JsonTest.java
 * @Description: <br>
 * <br>
 * @Company: crowdsource
 * @Created on 2015-9-15 下午12:13:16
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JsonTest {
    public static void main(String[] args) {
        String jsonStr = "{\"id\":\"101\",\"name\":\"张三\",\"age\":\"20\",\"birthday\":\"1992-10-19 23:52:18\"}";
        Student s = new Student();
        Timestamp b = Timestamp.valueOf("1992-10-19 23:52:18");
        s.setId(123456);
        s.setName("李四");
        s.setAge(20);
        s.setBirthday(b);
        Student s1 = jsonToBean(jsonStr);
        System.out.println(s1.getBirthday());
        System.out.println(beanToJson(s));
    }

    public static Student jsonToBean(String json) {
        String[] formats = { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };
        JSONUtils.getMorpherRegistry().registerMorpher(
                new TimestampMorpher(formats));
        JSONObject jsonObject = JSONObject.fromObject(json);
        return (Student) JSONObject.toBean(jsonObject, Student.class);
    }

    public static String beanToJson(Student s) {
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Timestamp.class,
                new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
        JSONObject json = JSONObject.fromObject(s, config);
        return json.toString();
    }
}

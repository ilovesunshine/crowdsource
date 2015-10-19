package com.common.utils;

import org.springframework.stereotype.Component;
@Component
public class BeanFactory{
	public static Object getBean(String name){
	    return com.chinasofti.ro.bizframework.core.libs.BeanFactory.getBean(name);
	}
}

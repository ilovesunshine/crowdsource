/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.dao;

import java.util.List;
import java.util.Map;

/**
 * @Title: IBaseDao.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-11 下午12:28:02
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 * @param <T>
 */
public interface IBaseDao {
    /**
     * 单表插入记录
     * 
     * @param obj
     */
    public <T> int insert(String _mybitsId, T obj);

    /**
     * 更新单表
     * 
     * @param obj
     */
    public <T> int update(String _mybitsId, T obj);

    /**
     * 删除记录
     * 
     * @param clz
     * @param id
     */
    public <T> int delete(String _mybitsId, T obj);

    /**
     * 
     * 返回查询一览表的信息
     * 
     * @param <T>
     * @param _mybitsId
     *            mybatis中对应业务标识
     * @param _params
     * @return
     */
    public <T> List<T> query(String _mybitsId, Map<String, Object> _params);

    /**
     * 查询相关列表信息
     * 
     * @param <T>
     *            返回数据
     * @param id
     *            mybatis中对应业务标识
     * @param _params
     * @return
     */
    public <T> List<T> query(String _mybitsId, Object _params);

    /**
     * 查询单个数据
     * 
     * @param queryString
     * @param object
     * @return
     */
    public Object queryOne(String _mybitsId, Object object);
}

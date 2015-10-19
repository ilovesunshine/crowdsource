/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.common.dao.IBaseDao;

/**
 * @Title: BaseDaoImpl.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-11 下午12:29:09
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
@Repository("baseDao")
public class BaseDaoImpl implements IBaseDao {
    
    @Resource
    private SqlSessionTemplate sqlSession;

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IBaseDao#insert(java.lang.String, java.lang.Object)
     */
    @Override
    public <T> int insert(String _mybitsId, T obj) {
        return sqlSession.insert(_mybitsId, obj);
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IBaseDao#update(java.lang.String, java.lang.Object)
     */
    @Override
    public <T> int update(String _mybitsId, T obj) {
        return sqlSession.update(_mybitsId, obj);
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IBaseDao#delete(java.lang.String, java.lang.Object)
     */
    @Override
    public <T> int delete(String _mybitsId, T obj) {
        return sqlSession.delete(_mybitsId, obj);
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IBaseDao#query(java.lang.String, java.util.Map)
     */
    @Override
    public <T> List<T> query(String _mybitsId, Map<String, Object> _params) {
        return sqlSession.selectList(_mybitsId, _params);
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IBaseDao#query(java.lang.String, java.lang.Object)
     */
    @Override
    public <T> List<T> query(String _mybitsId, Object _params) {
        return sqlSession.selectList(_mybitsId, _params);
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IBaseDao#queryOne(java.lang.String, java.lang.Object)
     */
    @Override
    public Object queryOne(String _mybitsId, Object object) {
        return sqlSession.selectOne(_mybitsId, object);
    }

}

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

import com.common.dao.exception.DBException;
import com.common.view.Page;


/**
 * @Title: IDAO.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-9 下午4:36:22
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public interface IDAO {
    
    public String dbMeta();
    /**
     * save
     * @param model
     */
    public void save(Object model);
    /**
     * update
     * @param model
     */
    public void update(Object model);
   
    /**
     * 
     * @param model
     * @return
     */
    public Model createQueryOne(Model model);
    
    public Page createNamedQuery(Class<?> clzz,Model params, Page page);
    /**
     * 分页查询-filter by Model
     * @param clazz
     * @param sql
     * @param model
     * @param page
     * @return
     * @throws DBException
     */
//    public Page<T> createNamedQuery(Model params, Page<T> page);
//    public Page<T> createNamedQueryMap(Map<String, Object> params,Page<T> page);
    public Page createNamedQuery(Class<?> clazz, String sql,Object params, Page page);
    public List createNamedQueryNoPage(Class<?> clazz, String sql,Object params)throws DBException;
    public Page createQueryWithSqlId(String sqlId,Object params, Page page)throws DBException;
    public Page createNamedQueryMap(Class<?> clazz, String sql,Map<String, Object> params,Page page);
    
    /**
     * 
     * @param model
     * @return
     */
    public List<? extends Model> createQuery(Model model);
    public List<? extends Model> createNamedQuery(Class<?> clazz, String sql, Object params);
    public List<? extends Model> createNamedQueryMap(Class<?> clazz, String sql, Map<String, Object> params);
        
    /**
     * batch
     * @return
     */
    public void batchSave(List<? extends Model> list);
    public void batchUpdate(List<? extends Model> list);
    public int[] batchUpdateWithSql(List<? extends Model> list,String sql);
    public int[] batchDelete(Class<?> clazz,String ids);

    public void execute(String sql, List<Object> sqlParam);
    public void executeNamedSql(String sql, Map<String, Object> params);

}

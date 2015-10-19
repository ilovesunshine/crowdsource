/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.common.dao.IDAO;
import com.common.dao.Model;
import com.common.dao.exception.DBException;
import com.common.utils.JfLog;
import com.common.view.Page;
import com.common.vo.JfSql;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * @Title: IDAOImpl.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-17 上午11:58:15
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
@Repository("dAOImpl")
public class DAOImpl implements IDAO {
    
    private static final Logger LOG = LoggerFactory.getLogger(DAOImpl.class.getName());
    @Resource
    protected SqlSessionTemplate sqlSession;
    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IDAO#dbMeta()
     */
    @Override
    public String dbMeta() {
        Object model = new Object();
        return sqlSession.getSqlSessionFactory().getConfiguration()
                .getMappedStatement(model.getClass().getName() + ".selectWithModel")
                .getBoundSql(null).getSql();
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IDAO#save(java.lang.Object)
     */
    @Override
    public void save(Object model) {
        try {
            sqlSession.insert(model.getClass().getName()+".save",model);
        } catch (Exception e) {
            throw new DBException(e.getMessage(),e);
        }
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IDAO#update(java.lang.Object)
     */
    @Override
    public void update(Object model) {
        try {
            sqlSession.update(model.getClass().getName()+".update",model);
        } catch (Exception e) {
            throw new DBException(e.getMessage(),e);
        }
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IDAO#createQueryOne(com.csi.jf.pay.common.dao.Model)
     */
    @Override
    public Model createQueryOne(Model model) {
        try {
            return sqlSession.selectOne(model.getClass().getName()+".selectWithModel", model);
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
    }
    
    @Override
    public Page createNamedQuery(Class<?> clz,Model params, Page page) {
        try {
            PageHelper.startPage(page.getStartPage(), page.getPageSize());
            PageHelper.orderBy(page.getOrderBy());
            List resultList = sqlSession.selectList(clz.getName()+".selectWithModel", params);
            page.setPageList(new PageInfo(resultList));
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return page;
    }

    public Page createNamedQueryMap(Class<?> clz,Map<String, Object> params, Page<T> page) {
        try {
            PageHelper.startPage(page.getStartPage(), page.getPageSize());
            PageHelper.orderBy(page.getOrderBy());
            List resultList = sqlSession.selectList(clz.getName()+".selectWithMap", params);
            page.setPageList(new PageInfo(resultList));
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return page;
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IDAO#createNamedQuery(java.lang.Class, java.lang.String, java.lang.Object, com.csi.jf.pay.common.view.Page)
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Page createNamedQuery(Class<?> clazz, String sql,Object params, Page page) {
        String runSQL = sql;
        try {
            PageHelper.startPage(page.getStartPage(), page.getPageSize());
            PageHelper.orderBy(page.getOrderBy());
            runSQL = formatSQL(sql,params);
            List resultList = sqlSession.selectList(clazz.getName()+".runSQL", new JfSql(runSQL));
            page.setPageList(new PageInfo(resultList));
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return page;
    }
    @SuppressWarnings("rawtypes")
    @Override
    public List createNamedQueryNoPage(Class<?> clazz, String sql,Object params) {
        String runSQL = sql;
        try {
            runSQL = formatSQL(sql,params);
            List resultList = sqlSession.selectList(clazz.getName()+".runSQL", new JfSql(runSQL));
            return resultList;
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
    }
    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IDAO#createNamedQueryMap(java.lang.Class, java.lang.String, java.util.Map, com.csi.jf.pay.common.view.Page)
     */
    @Override
    public Page createNamedQueryMap(Class<?> clazz, String sql, Map<String, Object> params, Page page) {
        try {
            PageHelper.startPage(page.getStartPage(), page.getPageSize());
            PageHelper.orderBy(page.getOrderBy());
            List resultList = sqlSession.selectList(clazz.getName()+".runSQL", params);
            page.setPageList(new PageInfo(resultList));
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return page;
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IDAO#createQuery(com.csi.jf.pay.common.dao.Model)
     */
    @Override
    public List<? extends Model> createQuery(Model model) {
        return createNamedQuery(model.getClass(),"selectWithModel",model);
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IDAO#createNamedQueryMap(java.lang.Class, java.lang.String, java.util.Map)
     */
    @Override
    public List<? extends Model> createNamedQueryMap(Class<?> clazz, String sql, Map<String, Object> params) {
        String runSQL = sql;
        List resultList = new ArrayList();
        try {
            runSQL = formatSQL(sql,params);
            resultList = sqlSession.selectList(clazz.getName()+".runSQL", new JfSql(runSQL));
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return resultList;
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IDAO#createNamedQuery(java.lang.Class, java.lang.String, java.lang.Object)
     */
    @Override
    public List<? extends Model> createNamedQuery(Class<?> clazz, String sql, Object params) {
        String sqlId = "selectWithModel";
        try {
            if(!StringUtils.isBlank(sql)){
                sqlId = sql;
            }
            return sqlSession.selectList(clazz.getName()+"." + sqlId, params);
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IDAO#batchSave(java.util.List)
     */
    @Override
    public void batchSave(List list) {
        try {
            if(list==null){
                throw new RuntimeException("list is null!");
            }
            sqlSession.insert(T.class.getName()+".batchSave",list);  
            sqlSession.commit();  
            sqlSession.close();  
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IDAO#batchUpdate(java.util.List)
     */
    @Override
    public void batchUpdate(List list) {
        try {
            if(list==null){
                throw new RuntimeException("list is null!");
            }
            Connection connection = SqlSessionUtils.getSqlSession(
                    sqlSession.getSqlSessionFactory(), sqlSession.getExecutorType(),
                    sqlSession.getPersistenceExceptionTranslator()).getConnection();
            PreparedStatement pst = connection.prepareStatement("");
            sqlSession.insert(T.class.getName()+".batchUpdate",list); 
            sqlSession.commit();  
            sqlSession.close();  
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
    }
    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IDAO#batchUpdate(java.util.List)
     */
    @Override
    public int[] batchUpdateWithSql(List list,String sql) {
        try {
            if(list==null || StringUtils.isBlank(sql)){
                throw new RuntimeException("list is null!");
            }
            Connection connection = SqlSessionUtils.getSqlSession(
                    sqlSession.getSqlSessionFactory(), sqlSession.getExecutorType(),
                    sqlSession.getPersistenceExceptionTranslator()).getConnection();
            PreparedStatement pst = connection.prepareStatement("");
            
            for(Object o : list){
                String newSql = formatSQL(sql, o);
                pst.addBatch(newSql);
            }
            return pst.executeBatch(); 
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IDAO#batchDelete(java.lang.String)
     */
    @Override
    public int[] batchDelete(Class<?> clazz,String ids) {
        int ret = 0;
        try {
            String[] idArray = new String[]{};
            if(ids!=null && !"".equals(ids.trim())){
                idArray = ids.split(",");
            }
            ret = sqlSession.delete(clazz.getName()+".batchDelete",idArray);
        } catch (Exception e) {
            throw new DBException(e.getMessage(),e);
        }
        return new int[]{ret};
    }
    
    private String formatSQL(String sql,Object sqlParams) throws IllegalArgumentException, 
                IllegalAccessException, InvocationTargetException{
        String formatedSQL = sql;
        if(StringUtils.isBlank(formatedSQL) || sqlParams==null){
            throw new RuntimeException("illeaga parameters!");
        }
        if(sqlParams instanceof Model){
            Map<String,Object> map = getKeyValue(sqlParams);
            Set<String> keys = map.keySet();
            for(String key:keys){
                formatedSQL = formatedSQL.replaceAll(":"+key, formatParam(map.get(key)));
            }
        }else if(sqlParams instanceof Map){
            Map<String,Object> map = (Map<String,Object>)sqlParams;
            Set<String> keys = map.keySet();
            for(String key:keys){
                formatedSQL = formatedSQL.replaceAll(":"+key, formatParam(map.get(key)));
            }
        }else if(sqlParams instanceof List){
            List list = (List)sqlParams;
            for(Object obj:list){
                formatedSQL = formatedSQL.replaceAll("\\?", formatParam(obj));
            }
        }else{
            throw new DBException("unknow supported param-type:"+sqlParams.getClass().getName());
        }
        return formatedSQL;
    }
    
    private Map<String,Object> getKeyValue(Object obj) throws IllegalAccessException, 
                IllegalArgumentException, InvocationTargetException {
        Map<String,Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            Method method = methods[i];
            if("get".equals(method.getName().substring(0, 3))){
                String name = method.getName();
                String key = name.substring(3,4).toLowerCase() + name.substring(4, name.length());
                Object value = method.invoke(obj, new Object[] {});
                if(value != null){
                    map.put(key, value);
                }
            }   
        }
        return map;
    }
    
    private String formatParam(Object p){
        if(p instanceof Date){
            return "'"+((Date)p).toString()+"'";
        }else if(p instanceof Number){
            return String.valueOf(p);
        }else{
            return "'"+String.valueOf(p)+"'";
        } 
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IDAO#execute(java.lang.String, java.util.List)
     */
    @Override
    public void execute(String sql, List<Object> sqlParams) {
        int rows;
        try {
            String runSQL = formatSQL(sql,sqlParams);
            Statement stmt = sqlSession.getConnection().createStatement();
            stmt.execute(runSQL);
            sqlSession.commit();
            rows = stmt.getUpdateCount();
            JfLog.info(LOG, "#execute sql update " + rows + " rows");
            stmt.close();
            sqlSession.close();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new DBException(e.getMessage(),e);
        }
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.common.dao.IDAO#executeNamedSql(java.lang.String, java.util.Map)
     */
    @Override
    public void executeNamedSql(String sql, Map<String, Object> sqlParams) {
        int rows;
        try {
            String runSQL = formatSQL(sql,sqlParams);
            Connection connection = SqlSessionUtils.getSqlSession(
                    sqlSession.getSqlSessionFactory(), sqlSession.getExecutorType(),
                    sqlSession.getPersistenceExceptionTranslator()).getConnection();
            PreparedStatement pst = connection.prepareStatement("");
            Statement stmt = connection.createStatement();
            stmt.execute(runSQL);
            rows = stmt.getUpdateCount();
            JfLog.info(LOG, "#execute sql update " + rows + " rows");
            stmt.close();
        } catch (Exception e) {
            throw new DBException(e.getMessage(),e);
        }
    }

    @Override
    public Page createQueryWithSqlId(String sqlId, Object params, Page page)
            throws DBException {
        try {
            PageHelper.startPage(page.getStartPage(), page.getPageSize());
            PageHelper.orderBy(page.getOrderBy());
            List resultList = sqlSession.selectList(sqlId, params);
            page.setPageList(new PageInfo(resultList));
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return page;
    }

}

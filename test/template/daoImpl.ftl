package com.csi.jf.pay.sys.dao.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import com.csi.jf.pay.common.dao.IQuery;
import com.csi.jf.pay.common.dao.Model;
import com.csi.jf.pay.common.dao.exception.DBException;
import com.csi.jf.pay.common.exception.UnimplementedException;
import com.csi.jf.pay.common.dao.impl.IQueryImpl;
import com.csi.jf.pay.common.view.Page;
import com.csi.jf.pay.sys.dao.I${entity.className}DAO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Repository("${entity.className?uncap_first}DAOImpl")
public class ${entity.className}DAOImpl implements I${entity.className}DAO {
	@Resource
	private SqlSessionTemplate sqlSession;
	
    @Override
    public String dbMeta() {
        throw new UnimplementedException();
    }
    @Override
    public void save(Object model) {
        try {
            sqlSession.insert("${entity.saveId}",model);
        } catch (Exception e) {
            throw new DBException(e.getMessage(),e);
        }
    }
    @Override
    public void update(Object model) {
        try {
            sqlSession.update("${entity.updateId}",model);
        } catch (Exception e) {
            throw new DBException(e.getMessage(),e);
        }
    }
    
    @Override
    public IQuery createQuery(Model model){
        IQuery iQuery = new IQueryImpl();
        try {
            List list = sqlSession.selectList("${entity.selectByModel}", model);
            iQuery = new IQueryImpl(list);
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return iQuery;
    }
    
    @Override
    public IQuery createNamedQuery(Class class1, String sql, Object model,Page page) {
        IQuery iQuery = new IQueryImpl();
        try {
            PageHelper.startPage(page.getStartPage(), page.getPageSize());
            PageHelper.orderBy(page.getOrderBy());
            List resultList = sqlSession.selectList("${entity.selectByModel}", model);
            page.setPageList(new PageInfo(resultList));
            iQuery = new IQueryImpl(page);
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
        return iQuery;
    }
  
    @Override
    public IQuery createQueryOne(Model model){
        try {
            return sqlSession.selectOne("${entity.selectByModelId}", model);
        } catch (Exception e) {
            throw new DBException(e.getMessage(), e);
        }
    }
    @Override
    public IQuery createNamedQuery(Class class1, String string,
            Map<String, Object> params) {
        throw new UnimplementedException();
    }
    @Override
    public IQuery createNamedQuery(Class class1, String string,
            Map<String, Object> params, Page page) {
        throw new UnimplementedException();
    }
    @Override
    public IQuery createNamedQuery(Class class1, String sql, Object model) {
        throw new UnimplementedException();
    }
    @Override
    public void batchSave(List list) {
        throw new UnimplementedException();
    }
    @Override
    public void batchUpdate(List list) {
        throw new UnimplementedException();
    }
    @Override
    public int[] batchDelete(String ids) {
        int ret = 0;
        try {
            String[] idArray = new String[]{};
            if(ids!=null&&"".equals(ids.trim())){
                idArray = ids.split(",");
            }
            ret = sqlSession.delete("${entity.batchDeleteId}",idArray);
        } catch (Exception e) {
            throw new DBException(e.getMessage(),e);
        }
        return new int[]{ret};
    }
    @Override
    public void execute(String sql, List<Object> sqlParam) {
        throw new UnimplementedException();
    }
    @Override
    public void executeNamedSql(String sql, Map<String, Object> params) {
        throw new UnimplementedException();
    }
}

package com.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.common.dao.IDAO;
import com.common.dao.exception.DBException;
import com.common.service.PayConfigService;
import com.common.view.Page;
import com.common.vo.PayConfig;

/**
 * @Title: PayConfigServiceImpl.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-11-26 下午5:11:50
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
@Service("payConfigServiceImpl")
public class PayConfigServiceImpl implements PayConfigService {


	@Resource(name="payConfigDAOImpl")
	private IDAO dao;
	/**
	 * 根据id查询数据
	 * 
	 * @param id
	 * @return model
	 * @throws DBException 
	 */
	@Override
	public PayConfig findById(String id) throws DBException {
		PayConfig payConfig = new PayConfig();
		payConfig.setPkey(id);
		return (PayConfig)dao.createQueryOne(payConfig);
	}

	/**
	 * 分页查询
	 * 
	 * @param payConfig
	 * @param page 分页
	 * @return
	 * @throws DBException 
	 */
	@Override
	public Page findByPage(PayConfig payConfig,Page page){
	    String sql = "SELECT * FROM jf_pay_config";
        return dao.createNamedQuery(payConfig.getClass(), sql, payConfig, page);
	}

	/**
	 * 批量删除数据
	 * 
	 * @param ids
	 * @return int[] delete records
	 * @throws DBException 
	 */
	public void batchDelete(String ids) throws DBException {
		if(StringUtils.isBlank(ids)){
			return;
		}
//		String[] arr = ids.split(",");
//		List<Integer> ls = new ArrayList<Integer>();    
//	    for(int i = 0;i < arr.length-1;i++){    
//	        ls.add(i);    
//	    } 
		dao.batchDelete(PayConfig.class,ids);
	}

	/**
	 * 保存数据
	 * @param Model
	 * @throws DBException 
	 */
	public void save(PayConfig model) throws DBException {
		dao.save(model);
	}

	/**
	 * 更新数据
	 * @param Model
	 * @throws DBException 
	 */
	public void update(PayConfig model) throws DBException {
		dao.update(model);
	}

    /* (non-Javadoc)
     * @see com.csi.jf.pay.sys.service.PayConfigService#findAll()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<PayConfig> findAll() throws DBException {
        String sql="select pkey,pvalue from jf_pay_config";
//        return dao.findPayConfigList(null);
        return (List<PayConfig>) dao.createNamedQuery(PayConfig.class, sql, new PayConfig());
    }
}
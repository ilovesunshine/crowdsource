package com.common.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.common.dao.IDAO;
import com.common.dao.exception.DBException;
import com.common.service.PayAdminsService;
import com.common.view.Page;
import com.common.vo.PayAdmins;

/**
 * 
 * @Title: PayAdminsServiceImpl.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-1 下午2:33:59
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
@Service("payAdminsServiceImpl")
public class PayAdminsServiceImpl implements PayAdminsService {

	@Resource(name="dAOImpl")
	private IDAO dao;
	/**
	 * 根据id查询数据
	 * 
	 * @param id
	 * @return model
	 * @throws DBException 
	 */
	@Override
	public PayAdmins findById(Long id) throws DBException {
		PayAdmins payAdmins = new PayAdmins();
		payAdmins.setSaId(id);
		return (PayAdmins)dao.createQueryOne(payAdmins);
	}

	 /* (non-Javadoc)
     * @see com.csi.jf.pay.sys.service.PayAdminsService#findByProperty(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public PayAdmins findByProperty(String userName, String pass, String userRole) throws DBException {
        PayAdmins pa = new PayAdmins();
        pa.setUserName(userName);
        pa.setAdminPass(pass);
        pa.setUserRole(userRole);
        return (PayAdmins)dao.createQueryOne(pa);
    }

    /* (non-Javadoc)
     * @see com.csi.jf.pay.sys.service.PayAdminsService#findByProperty(java.lang.String, java.lang.String)
     */
    @Override
    public PayAdmins findByProperty(String userId, String userRole) throws DBException {
        PayAdmins pa = new PayAdmins();
        pa.setUserName(userId);
        pa.setUserRole(userRole);
        return (PayAdmins)dao.createQueryOne(pa);
    }
    
    /* (non-Javadoc)
     * @see com.csi.jf.pay.sys.service.PayAdminsService#findByProperty(java.lang.String, java.lang.String)
     */
    @Override
    public PayAdmins findByProperty(PayAdmins pa)  throws DBException{
        return (PayAdmins)dao.createQueryOne(pa);
    }

	/**
	 * 分页查询
	 * 
	 * @param payAdmins
	 * @param page 分页
	 * @return
	 * @throws DBException 
	 */
    public Page findByPage(PayAdmins payAdmins, Page page) throws DBException {
        /*payAdmins.setSaId(Long.parseLong("14")); 
        String sql = "SELECT * FROM jf_pay_admins where 1=1 and SA_ID=:saId";
        */
        /*List<String> params = new ArrayList<String>(); 
        params.add("14");
        String sql = "SELECT * FROM jf_pay_admins where 1=1 and SA_ID=?";
        */
        String sql = "SELECT * FROM jf_pay_admins where 1=1";
        return dao.createNamedQuery(PayAdmins.class,sql,payAdmins, page);
    }

	/**
	 * 批量删除数据
	 * 
	 * @param ids
	 * @return int[] delete records
	 * @throws DBException 
	 */
	@Override
	public void batchDelete(String ids) throws DBException {
		if(StringUtils.isBlank(ids)){
			return;
		}
//		String[] arr = ids.split(",");
//		List<Integer> ls = new ArrayList<Integer>();    
//	    for(int i = 0;i < arr.length-1;i++){    
//	        ls.add(i);    
//	    } 
		dao.batchDelete(PayAdmins.class,ids);
	}

	/**
	 * 保存数据
	 * @param Model
	 * @throws DBException 
	 */
	public void save(PayAdmins model) throws DBException {
		dao.save(model);
	}

	/**
	 * 更新数据
	 * @param Model
	 * @throws DBException 
	 */
	public void update(PayAdmins model) throws DBException {
		dao.update(model);
	}

}
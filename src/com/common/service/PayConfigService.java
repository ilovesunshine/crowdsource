package com.common.service;

import java.util.List;

import com.common.dao.exception.DBException;
import com.common.view.Page;
import com.common.vo.PayConfig;


/**
 *
 * 业务逻辑接口类，在此定义实现业务逻辑需要的方法，使用时请注意：
 *
 * 1、平台默认提供声明式事物
 *
 * 平台为所有以Service结尾的类中的以save，update，insert，delete，batch，execute等单词为开头的方法提供了事务控制.
 * 对有事物控制的方法平台会在需要的时候自动开启/关闭Connection.
 * 声明式事物在applicationContext-core.xml中的dsTransactionInterceptor中定义：
 * <property name="transactionAttributes">
 * <props>
 * <prop key="save*">PROPAGATION_REQUIRED</prop>
 * <prop key="insert*">PROPAGATION_REQUIRED</prop>
 * <prop key="create*">PROPAGATION_REQUIRED</prop>
 * <prop key="update*">PROPAGATION_REQUIRED</prop>
 * <prop key="delete*">PROPAGATION_REQUIRED</prop>
 * <prop key="remove*">PROPAGATION_REQUIRED</prop>
 * <prop key="batch*">PROPAGATION_REQUIRED</prop>
 * <prop key="execute*">PROPAGATION_REQUIRED</prop>
 * <prop key="get*">PROPAGATION_REQUIRED,readOnly</prop>
 * <prop key="find*">PROPAGATION_REQUIRED,readOnly</prop><!-- readOnly表示是查询，一般在查询的方法时使用-->
 * <prop key="query*">PROPAGATION_REQUIRED,readOnly</prop>
 * <prop key="search*">PROPAGATION_REQUIRED,readOnly</prop>
 * </props>
 * </property>
 *
 * 除了上述默认外还可以自定义。比如现有load开头的方法用来查询数据库，数据库开启关闭想交给平台管理，只需要按下面的注册即可：
 * <prop key="load*">PROPAGATION_REQUIRED,readOnly</prop>
 *
 *
 * 2、除了声明式事物，还可以使用com.chinasofti.ro.bizframework.core.orm.Transaction类来在您的方法中使用编程式事务，如：
 * <pre>
 * public void myMethod(String id) {
 * Transaction.begin(); //开启事务
 * try {
 * ......
 * Transaction.commit(); //提交
 * } catch(Exception e) {
 * Transaction.rollback(); //回滚
 * }
 * }
 * </pre>
 *
 * 注意：在同一方法里，不要即使用声明式事务又使用编程式事务，
 * 更多内容请参考《ResourceOne5.0BizFoundation常见问题解答》中的3.1.1和3.1.2
 *
 * @Title: PayConfigService.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-11-26 下午5:11:50
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public interface PayConfigService {
  	/**
	 * <p>根据id查询数据</p>
	 *
	 * @param id
	 * @return Model
	 */
	public PayConfig findById(String id)throws DBException;

	/**
     * <p>查询数据</p>
     *
     * @return List
     */
    @SuppressWarnings("rawtypes")
    public List findAll()throws DBException;
	/**
	 * <p>批量删除数据</p>
	 *
	 * @param ids
	 * @return int[] delete records
	 */
	public void batchDelete(String ids);

	/**
	 * <p>返回符合查询条件的页面结果
	 * @param PayConfig
	 * @param page
	 * @return
	 */
	public Page findByPage(PayConfig payConfig, Page page);

	/**
	 * 保存数据
	 * @param Model
	 */
	public void save(PayConfig model);

	/**
	 * 更新数据
	 * @param Model
	 */
	public void update(PayConfig model);

}
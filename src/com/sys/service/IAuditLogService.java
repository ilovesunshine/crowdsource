package com.sys.service;

import java.util.List;

import com.common.dao.exception.DBException;
import com.common.view.Page;
import com.common.vo.PayAudit;


public interface IAuditLogService {

	/**
	 * 分页查询行为日志
	 * @param payAudit
	 * @param pageInfo
	 * @return
	 */
//	PageInfo<PayAudit> queryAuditWithPage(PayAudit payAudit,PageInfo<PayAudit> pageInfo) throws DBException;
	/**
	 * 异步查询行为日志
	 * @param payAudit
	 * @param pageInfo
	 * @return
	 * @throws DBException
	 */
	public Page<PayAudit> findByPage(Page<PayAudit> page,PayAudit payAudit) throws DBException;
	/**
	 * 不分页查询行为审核日志
	 * @param payAudit
	 * @return
	 * @throws DBException
	 */
	List<PayAudit> queryAuditLogList(PayAudit payAudit) throws DBException;
	
	public PayAudit findOneAuditById(PayAudit payAudit) throws DBException;
}

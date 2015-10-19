package com.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.common.dao.IDAO;
import com.common.dao.exception.DBException;
import com.common.view.Page;
import com.common.vo.PayAudit;
import com.sys.service.IAuditLogService;

@Service("auditLogServiceImpl")
public class AuditLogServiceImpl implements IAuditLogService{

    @Resource( name = "dAOImpl")
    private IDAO dao;
    //	@Override
    //	public PageInfo<PayAudit> queryAuditWithPage(PayAudit payAudit, PageInfo<PayAudit> pageInfo) throws DBException {
    //		return dao.queryAuditWithPage(payAudit, pageInfo);
    //	}f
    @Override
    public Page<PayAudit> findByPage(Page<PayAudit> page,PayAudit payAudit)
            throws DBException {
        return dao.createQueryWithSqlId("com.csi.jf.pay.common.vo.PayAudit.selectWithModel",  payAudit, page);
    }
    @Override
    public List<PayAudit> queryAuditLogList(PayAudit payAudit) throws DBException {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public PayAudit findOneAuditById(PayAudit payAudit) throws DBException {
        // TODO Auto-generated method stub
        return null;
    }


}

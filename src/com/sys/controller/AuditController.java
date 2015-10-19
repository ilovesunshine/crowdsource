/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.sys.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.common.dao.exception.DBException;
import com.common.exception.JfPayException;
import com.common.utils.JfLog;
import com.common.view.Page;
import com.common.view.PageUtil;
import com.common.vo.PayAudit;
import com.sys.service.IAuditLogService;
import com.sys.view.AuditLogExcelView;

/**
 * @Title: AuditController.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-9 下午3:39:00
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
@Controller
@RequestMapping("/sys/audit/*")
public class AuditController extends SysController {

    private static final Logger LOG = LoggerFactory.getLogger(AuditController.class);
    
    @Resource(name="auditLogServiceImpl")
    private IAuditLogService auditLogService;
    /**
     * 系统行为日志
     */
    @RequestMapping("/show")
    public String  showAudit() {
       return toJsp("sys/audit/auditList.jsp");
    }
    
    @RequestMapping(value="/query")
    @ResponseBody
    public void queryAuditLog(Page<PayAudit> page, PayAudit payAudit){
        try {
            Page<PayAudit> pPage = auditLogService.findByPage(page,payAudit);
            this.renderJSON(PageUtil.toJson4JqGrid(pPage, true));
        } catch (DBException e) {
            JfLog.error(LOG,e.getMessage(),e);
        }
        
    }
    
    @RequestMapping("/showDetail")
    public String showDetail(Integer id,ModelMap map) {
        if(null == id){
            throw new JfPayException("主键为Null");
        }
        PayAudit payAudit = new PayAudit();
        payAudit.setActId(id);
        try {
            map.put("payAudit", auditLogService.findOneAuditById(payAudit));
        } catch (DBException e) {
            JfLog.error(LOG, e.getMessage());
        }
        return toJsp("/sys/audit/auditShow.jsp"); 
    }
    
}

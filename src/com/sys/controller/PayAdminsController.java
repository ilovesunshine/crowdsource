/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.sys.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.common.config.JfConstants;
import com.common.dao.Model;
import com.common.dao.exception.DBException;
import com.common.exception.JfPayException;
import com.common.service.PayAdminsService;
import com.common.utils.DateUtil;
import com.common.utils.JfAuditor;
import com.common.utils.JfLog;
import com.common.utils.MD5Util;
import com.common.view.Page;
import com.common.view.PageUtil;
import com.common.view.View;
import com.common.vo.PayAdmins;

/**
 * <p>Controller</p>
 * 
 * @author BizFoundation
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/sys/admins/*")
public class PayAdminsController extends SysController {
    
    private static final Logger LOG = LoggerFactory.getLogger(PayAdminsController.class);
    /**
     * <p>注入 payAdminsService</p>
     */
    @Resource(name = "payAdminsServiceImpl")
    private PayAdminsService payAdminsService;

    /**
	 * <p>打开查询列表页面</p>
	 */
    @RequestMapping("/show")
	public String showAdmins() {
		return toJsp("sys/admins/adminsList.jsp");
	}
    @RequestMapping("/add")
    public String add() {
        return toJsp("sys/admins/adminsNew.jsp");
    }

	/**
	 * <p>查询分页数据</p>
	 * @param gridPage
	 * @param payAdmins
	 */
    @RequestMapping("/list")
	public void listPayAdmins(Page page,PayAdmins payAdmins) {
        Page pPage = null;
        try {
            pPage = payAdminsService.findByPage(payAdmins, page);
        } catch (DBException e) {
            JfLog.error(LOG, e.getMessage(),e);
        }
        this.renderJSON(PageUtil.toJson4JqGrid(pPage, true));
	}

	/**
	 * 根据username查找用户JFID
	 * @param userName
	 */
    @RequestMapping("/findbyusername")
	public void findJfIdByUsername(String userName){
	}

    @RequestMapping("/save")
	public String save(PayAdmins payAdmins) {
		/*if (Validation.hasErrors()) {
			View view = new View(FAILED);
			view.bind("payAdmins",payAdmins);
			this.render(view);
			return;
		}*/
        View view = new View("/sys/admins/show");
		try {
            payAdmins.setAdminPass(MD5Util.encrypt(payAdmins.getAdminPass()));
            payAdmins.setDeleteFlag(JfConstants.DELETE_FLAG_FALSE);
            payAdmins.setUserStatus(JfConstants.ADMIN_STATUS_NORMAL);
            payAdmins.setCreateTime(DateUtil.getCurrentTimestamp());
            payAdmins.setUpdateTime(payAdmins.getCreateTime());
            payAdminsService.save(payAdmins);
            // this.success("add.success");
            JfAuditor.audit(JfAuditor.ACTION_ADD_ADMINISTRATOR, "添加"
                    + payAdmins.getUserRole() + "类型管理员" + payAdmins.getJfid()
                    + "成功", JfAuditor.OBJ_PAY_SYS, JfAuditor.ACT_SUCESS, null);
           
        } catch (DBException e) {
            JfLog.error(LOG, e.getMessage(),e);
        }
		 return redirect(view);
	}

    @RequestMapping("/edit")
	public ModelAndView edit(Long id) {
		if(null == id){
			throw new JfPayException("主键为Null");
		}
		View view = new View(toJsp("sys/admins/adminsEdit.jsp"));
		try {
            Model payAdmins = (Model)payAdminsService.findById(id);
            view.bind("payAdmins",payAdmins);
        } catch (DBException e) {
            JfLog.error(LOG, e.getMessage(),e);
        }
		return this.render(view);
	}

    @RequestMapping("/update")
	public String update(PayAdmins payAdmins) {
	    PayAdmins pa = null;
	    try {
	        pa = (PayAdmins)payAdminsService.findById(payAdmins.getSaId());
            if(pa!=null){
                String pass = payAdmins.getAdminPass();
                String editType = "";
                if(!StringUtils.isEmpty(pass)){
                    pa.setAdminPass(MD5Util.encrypt(pass));
                    editType = "，修改管理密码";
                }
                pa.setUserRole(payAdmins.getUserRole());
                pa.setUserStatus(payAdmins.getUserStatus());
                pa.setUpdateTime(DateUtil.getCurrentTimestamp());
                payAdminsService.update(pa);
                JfAuditor.audit(JfAuditor.ACTION_MODIFY_ADMINISTRATOR, "修改"+payAdmins.getUserRole()+"类型管理员"+payAdmins.getJfid() + editType+"成功", JfAuditor.OBJ_PAY_SYS, JfAuditor.ACT_SUCESS,null);
            }
        } catch (DBException e) {
            JfLog.error(LOG, e.getMessage(),e);
        }
        View view = new View("/sys/admins/show");
        // TODO this.success("update.success");
        return this.redirect(view);
	}

    @RequestMapping("/delete")
	public String delete(String ids) {
        View view = new View("/sys/admins/show");
		try {
            this.payAdminsService.batchDelete(ids);
            JfAuditor.audit(JfAuditor.ACTION_DELETE_ADMINISTRATOR, "删除管理员"+ids, JfAuditor.OBJ_PAY_SYS, JfAuditor.ACT_SUCESS,null);
            // TODO this.success("delete.success");
        } catch (DBException e) {
            JfLog.error(LOG, e.getMessage(),e);
        }
		return this.redirect(view);
	}

}
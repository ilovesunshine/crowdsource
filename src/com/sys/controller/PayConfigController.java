/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.sys.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.common.service.PayConfigService;
import com.common.utils.DateUtil;
import com.common.utils.JfAuditor;
import com.common.utils.JfLog;
import com.common.view.Page;
import com.common.view.PageUtil;
import com.common.view.View;
import com.common.vo.PayConfig;
import com.common.vo.RetObj;

/**
 * @Title: PayConfigController.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-9 下午3:38:46
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
@Controller
@RequestMapping("/sys/config/*")
public class PayConfigController extends SysController{

    private static final Logger LOG = LoggerFactory.getLogger(PayConfigController.class);
    @Resource(name="payConfigServiceImpl")
    private PayConfigService payConfigService;
    
    /**
     * <p>打开查询列表页面</p>
     * 
     */
    @RequestMapping("/show")
    public String showConfig() {
       return toJsp("sys/config/configList.jsp");
    }
    
    @RequestMapping("/add")
    public String addConfig() {
        return toJsp("sys/config/configNew.jsp");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
    }

    /**
     * <p>查询分页数据</p>
     * @param gridPage
     * @param payConfig
     */
    @RequestMapping("/list")
    @ResponseBody
    public void listConfig(Page page,PayConfig payConfig) {
        Page pPage = null;
        try {
            pPage = payConfigService.findByPage(payConfig,page);
        } catch (DBException e) {
            JfLog.error(LOG, e.getMessage(),e);
        }
        this.renderJSON(PageUtil.toJson4JqGrid(pPage, true));
    }
    
    @RequestMapping("/detail")
    public String showConfigDetail(String id,ModelMap map) {
        if(null == id){
            throw new JfPayException("主键为Null");
        }
      try {
            map.put("payConfig",payConfigService.findById(id));
        } catch (DBException e) {
            JfLog.error(LOG, e.getMessage());
        }
      return toJsp("sys/config/configShow.jsp");
    }
    
    @RequestMapping("/save")
    public ModelAndView saveConfig(PayConfig payConfig) {
        Map<String, RetObj> model = new HashMap<String,RetObj>();
        RetObj retObj = new RetObj();
        try {
            payConfig.setCreateTime(DateUtil.getCurrentTimestamp());
            payConfigService.save(payConfig);
            retObj.setFlag(true);
            //DictUtil.resetDict("payConfig");
            retObj.setMsg("添加参数成功");
            JfAuditor.auditSys(JfAuditor.ACTION_MODIFY_USERNAME, "添加配置参数"+payConfig.getPkey()+"="+payConfig.getPvalue()+"成功。");
        } catch (Exception e) {
            JfLog.error(LOG, e.getMessage());
            retObj.setFlag(false);
            retObj.setMsg("添加参数失败");
            JfAuditor.auditSysFail(JfAuditor.ACTION_MODIFY_USERNAME, "添加配置参数失败。原因:"+e.getMessage());
        }
        model.put("ret", retObj);
        return new ModelAndView("forward:/sys/config/show",model);
    }
    @RequestMapping("/edit")
    public ModelAndView editConfig(String id,ModelMap modelMap) {
        if(null == id){
            throw new JfPayException("主键为Null");
        }
        PayConfig payConfig = null;
        try {
            payConfig = payConfigService.findById(id);
        } catch (DBException e) {
            JfLog.error(LOG, e.getMessage(),e);
        }
        View view = new View(toJsp("sys/config/configEdit.jsp"));
        view.bind("payConfig",payConfig);
        return this.render(view);
    }
    @RequestMapping("/update")
    public ModelAndView updateConfig(PayConfig payConfig) {
        Map<String, RetObj> model = new HashMap<String,RetObj>();
        RetObj retObj = new RetObj();
        try {
            PayConfig pc = (PayConfig) payConfigService.findById(payConfig
                    .getPkey());
            pc.setPvalue(payConfig.getPvalue());
            pc.setMemo(payConfig.getMemo());
            payConfig.setUpdateTime(DateUtil.getCurrentTimestamp());
            payConfigService.update(payConfig);
            retObj.setFlag(true);
            retObj.setMsg("修改参数成功");
            // TODO DictUtil.resetDict("payConfig");
            // TODO this.success("update.success");
            JfAuditor.audit(JfAuditor.OBJ_PAY_SYS, "将参数" + payConfig.getPkey()
                    + "的值" + pc.getPvalue() + "改为" + payConfig.getPvalue()
                    + "成功。", JfAuditor.ACTION_MODIFY_USERNAME,
                    JfAuditor.ACT_SUCESS, JfAuditor.ACCOUNT_TYPE_SYS);
        } catch (Exception e) {
            retObj.setFlag(false);
            retObj.setMsg("修改参数失败");
            JfLog.error(LOG, e.getMessage());
            JfAuditor.audit(JfAuditor.OBJ_PAY_SYS, "修改参数" + payConfig.getPkey()
                    + "的值失败。原因:" + e.getMessage(),
                    JfAuditor.ACTION_MODIFY_USERNAME, JfAuditor.ACT_FAILED,
                    JfAuditor.ACCOUNT_TYPE_SYS);
        }
        model.put("ret", retObj);
        return  new ModelAndView("forward:/sys/config/show", model);
    }

    @RequestMapping("/remove")
    public ModelAndView deleteConfig(String ids) {
        Map<String, RetObj> model = new HashMap<String,RetObj>();
        RetObj retObj = new RetObj();
        try {
            this.payConfigService.batchDelete(ids);
            // TODO DictUtil.resetDict("payConfig");
            // TODO this.success("delete.success");
            retObj.setFlag(true);
            retObj.setMsg("删除参数成功");
            JfAuditor.audit(JfAuditor.OBJ_PAY_SYS, "删除参数" + ids + "成功。",
                    JfAuditor.ACTION_MODIFY_USERNAME, JfAuditor.ACT_SUCESS,
                    JfAuditor.ACCOUNT_TYPE_SYS);
        } catch (Exception e) {
            JfAuditor.audit(JfAuditor.OBJ_PAY_SYS,
                    "删除参数" + ids + "失败。原因:" + e.getMessage(),
                    JfAuditor.ACTION_MODIFY_USERNAME, JfAuditor.ACT_FAILED,
                    JfAuditor.ACCOUNT_TYPE_SYS);
            retObj.setFlag(true);
            retObj.setMsg("删除参数失败");
        }
        model.put("ret", retObj);
        return  new ModelAndView("forward:/sys/config/show", model);
    }

}

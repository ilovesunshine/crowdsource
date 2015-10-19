/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinasofti.ro.bizframework.modules.json.JSONArray;
import com.chinasofti.ro.bizframework.modules.json.JSONObject;
import com.common.service.PayConfigService;
import com.common.vo.PayConfig;
import com.sys.controller.SysController;

/**
 * @Title: JfDictController.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-15 下午7:53:16
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
@Controller
@RequestMapping("/bizdict/*")
public class JfDictController extends SysController {
    
    /**
     * inject payBaseFeeRateService
     */
    @Resource(name = "payConfigServiceImpl")
    private PayConfigService payConfigService;
    
    @SuppressWarnings("rawtypes")
    @RequestMapping("/payConfig")
    public void queryConfig() {
        // SELECT pvalue AS name ,pkey AS value FROM jf_pay_config
        List list = payConfigService.findAll();
        String[] names = new String[list.size()];
        String[] values = new String[list.size()];
        PayConfig pc = null;
        for(int i=0;i<list.size();i++){
            pc = (PayConfig)list.get(i);
            names[i] = pc.getPvalue();
            values[i] = pc.getPkey();
        }
        this.renderJSON(createDict(names,values).toString());
    }
    
    @RequestMapping("/adminStatus")
    public void queryAdminStatus() {
        this.renderJSON(createDict(
                new String[] { "锁定", "正常" },
                new String[] { "0", "1" }).toString());
    }
    
    private JSONArray createDict(String[] names,String[] values){
        JSONArray array = new JSONArray();
        JSONObject json = null;
        for(int i=0;i<names.length;i++){
            json = new JSONObject();
            json.put("name", names[i]);
            json.put("value",values[i]);
            array.add(json);
        }
        return array;
    }
    
    @RequestMapping("/adminRole")
    public void queryAdminRole() {
        this.renderJSON(createDict(
                new String[] { "运营管理员", "系统管理员" ,"运营专员"},
                new String[] { "1", "2" ,"3"}).toString());
    }
    
    @RequestMapping("/fundFreezeCode")
    public void queryFundFreezeCode() {
        this.renderJSON(createDict(
                new String[] { "第一次签单冻结订单金额", "续签订单时增加冻结金额" ,"同意支付时超出原订单金额增加冻结金额","支付订单解冻订单金额","订单结束释放剩余冻结金额"},
                new String[] { "01", "02" ,"03","04","05"}).toString());
    }

}

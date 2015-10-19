/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.sys.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.common.config.JfConstants;
import com.common.service.PayAdminsService;
import com.common.utils.JfAuditor;
import com.common.utils.JfLog;
import com.common.utils.MD5Util;
import com.common.utils.ModelUtils;
import com.common.view.View;
import com.common.vo.JfLoginUser;
import com.common.vo.PayAdmins;

/**
 * @Title: SysController.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-11-26 下午5:11:50
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
@Controller
@RequestMapping("/sys/*")
public class SysController {
    
    private static final Logger LOG = LoggerFactory.getLogger(SysController.class);
    
    protected static final String SYS_USER = "_sys_user";
    
//    protected static final String SUCCESS = "success";
    
    protected HttpServletRequest request;  
    protected HttpServletResponse response;  
    protected HttpSession session;  
    
    /**
     * inject payAdminsService
     */
    @Resource(name = "payAdminsServiceImpl")
    private PayAdminsService payAdminsService;
      
    @ModelAttribute  
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
        this.session = request.getSession();
    }  
    
    /**
     * redirect page
     * @param view
     * @return
     */
    protected String redirect(View view){
        return "redirect:"+view.getJsp();
    }
    
    /**
     * output JSON
     * @param json
     */
    protected void renderJSON(String json) {
        try {
            this.response.setContentType("application/json");
            this.response.getWriter().write(json);
        } catch (IOException e) {
            JfLog.error(LOG, e.getMessage(), e);
        }
    }
    
    /**
     * render page-view
     * @param view
     * @return
     */
    protected ModelAndView render(View view){
        return view.getView();
    }
    
    /**
     * substring the .jsp
     * @param str
     * @return
     */
    protected String toJsp(String str) {
        if (!StringUtils.isBlank(str)) {
            int i = str.lastIndexOf(".jsp");
            if (i > -1) {
                return str.substring(0, str.lastIndexOf(".jsp"));
            }
        }
        return str;
    }
    
    /**
     * check admin if login
     * @return
     */
    protected String doAuthCheck(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        View view = new View(toJsp("sys/login.jsp"));
        if(request == null) {
            return this.redirect(view);
        }
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SYS_USER) == null) {
        	JfLog.warn(LOG,"Opt admin has not login!");
            return this.redirect(view);
        }
        return null;
    }
    
    /**
     * show system login page
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView showSysLogin() {
        View view = new View(toJsp("sys/login.jsp"));
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        if(request == null) {
            return null;
        }
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(SYS_USER) != null) {
            view = new View("sys/main.jsp");
        }
        return this.render(view);
    }
    
    /**
     * system login check
     * @param pid
     * @param pwd
     * @return
     */
    @RequestMapping("/loginpost")
    public ModelAndView login(String pid,String pwd){
        View  view = new View(toJsp("sys/login.jsp"));
        if(StringUtils.isEmpty(pid)||StringUtils.isEmpty(pwd)){
            JfAuditor.audit(JfAuditor.ACTION_LOGIN_SYS_ADMIN, "用户名或密码为空", JfAuditor.OBJ_PAY_SYS, JfAuditor.ACT_FAILED,null);
            return this.render(view);
        }
        
        try {
        	PayAdmins pa = new PayAdmins();
			pa.setUserName(pid.trim());
			pa.setAdminPass(MD5Util.encrypt(pwd.trim()));
			pa.setUserRole(JfConstants.ADMIN_ROLE_SYS);
        	PayAdmins payAdmins = payAdminsService.findByProperty(pa);
            if(payAdmins==null){
                JfAuditor.audit(JfAuditor.ACTION_LOGIN_SYS_ADMIN, "用户名或密码错误", JfAuditor.OBJ_PAY_SYS, JfAuditor.ACT_FAILED,null);
                return this.render(view);
            }
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            if(request == null) {
                return this.render(view);
            }
            HttpSession session = request.getSession(false);
            if(session!=null){
                if(session.getAttribute(SYS_USER) == null){
                	JfLoginUser user = ModelUtils.transform2LoginUser(payAdmins);
                    session.setAttribute(SYS_USER, user);
                }
                view = new View(toJsp("sys/main.jsp"));
                JfAuditor.audit(JfAuditor.ACTION_LOGIN_SYS_ADMIN, "", JfAuditor.OBJ_PAY_SYS, JfAuditor.ACT_SUCESS,null);
            }
        } catch (Exception e) {//NoSuchAlgorithmException
            JfLog.error(LOG,e.getMessage(),e);
        }
        return this.render(view);
    }
    
    /**
     * Logout
     */
    @RequestMapping("/logout")
    public ModelAndView logout(){
        View view = new View(toJsp("sys/login.jsp"));
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        if(request == null) {
            return this.render(view);
        }
        HttpSession session = request.getSession(false);
        if(session!=null){
        	//登出的验证加在session remove之前
        	JfAuditor.audit(JfAuditor.ACTION_LOGOUT_SYS_ADMIN, "", JfAuditor.OBJ_PAY_SYS, JfAuditor.ACT_SUCESS,null);
            session.removeAttribute(SYS_USER);
        }
        return this.render(view);
    }
    
}
package com.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
			Object arg2) throws Exception {
//		HttpSession session = req.getSession(true);  
//        Object obj = session.getAttribute("curuser");  
         
        // 判断如果没有取到用户信息，就跳转到登陆页面，提示用户进行登陆  
//        if (obj == null || "".equals(obj.toString())) {  
//            res.sendRedirect(req.getContextPath()+"/login/tologin");
//            return false;
//        } 
        return true;  
	}

}

/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @Title: JfExceptionResolver.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-11-24 上午11:58:57
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfExceptionResolver{
      
      /**
       * errorTitle
       * exception
       * errorCode
       * errorDescription
       */
      protected Map<String,String> buildTemplateArgs(HttpServletRequest request,
              HttpServletResponse response, Exception e) {          
          String contextPath = request.getContextPath();
          Map<String,String> map = new HashMap<String,String>();
          map.put("errorTitle", e.getMessage());
          map.put("contextPath", contextPath);
          return map;
      }

  }

/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.view;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinasofti.ro.bizframework.modules.json.JSONObject;
import com.common.utils.JfLog;

/**
 * @Title: PageUtil.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-9 下午6:25:25
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class PageUtil {
    
    private static final Logger LOG = LoggerFactory.getLogger(PageUtil.class);
    
    private PageUtil(){
    }

    public static String toJson4JqGrid(Page page, boolean b) {
        int sEcho = page.getSEcho();
        JSONObject getObj = new JSONObject();
        try {
            getObj.put("iTotalRecords", page.getPageList().getTotal());
            getObj.put("iTotalDisplayRecords", page.getPageList().getTotal());
            getObj.put("sEcho", sEcho);
            getObj.put("aaData", page.getPageList().getList());
        } catch (Exception e) {
            e.printStackTrace();
            JfLog.error(LOG, e.getMessage(),e);
        }
        System.out.println(getObj.toString());
        return getObj.toString();
    }

}

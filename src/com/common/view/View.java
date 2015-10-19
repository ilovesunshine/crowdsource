/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.view;

import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: View.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-9 下午5:58:32
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class View {
    
    private String jsp;
    private ModelAndView view;
    public View(String jsp){
        this.jsp = jsp;
        this.view = new ModelAndView(jsp);
    }
    
    public void bind(String key, Object value) {
       view.addObject(key, value);
    }
    
    public ModelAndView getView(){
        return this.view;
    }
    
    public String getJsp(){
        return this.jsp;
    }

}

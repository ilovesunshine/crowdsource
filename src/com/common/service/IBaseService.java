/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.service;

import com.common.dao.IBaseDao;

/**
 * @Title: IBaseService.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-11 下午12:31:47
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public interface IBaseService {
    
    public IBaseDao getBaseDao();

    public void setBaseDao(IBaseDao baseDao);
    
}

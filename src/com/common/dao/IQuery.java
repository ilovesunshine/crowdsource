/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.dao;

import java.util.List;

import com.common.view.Page;
import com.github.pagehelper.PageInfo;

/**
 * @Title: IQuery.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-9 下午4:42:57
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public interface IQuery {

    public List list();

    public PageInfo fetch(PageInfo page);

    public Page page();

    public Object first();
}

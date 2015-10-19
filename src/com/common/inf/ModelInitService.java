/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.inf;

import com.common.dao.exception.DBException;

/**
 * @Title: ModelInitService.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-1-8 上午10:50:41
 * @author FengHaiBing
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public interface ModelInitService {

    /**
     * 所有记录的加密字段初始化，重新加密
     * 
     * @param strWhere
     * 
     * @author FengHaiBing
     */
    public void updateResetEncryptFields(Long strWhere)throws DBException;
    
    /**
     * 验证所有记录 加密字段的有效性
     * 
     * @param strWhere
     * 
     * @author FengHaiBing
     * @throws DBException 
     */
    public void updateCheckFieldsValid(String strWhere) throws DBException; 
    
    /**
     * 所有记录的加密字段初始化，重新加密
     * Model的关键字段是已经解密的;金额字段有对应的加密字段来解密替换.
     * 
     * @param model
     * 
     * @author FengHaiBing
     */
    //public void updateResetEncryptFields(Model model);
    
}

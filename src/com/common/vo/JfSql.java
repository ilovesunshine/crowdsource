/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.vo;

import java.io.Serializable;

/**
 * @Title: JfSql.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-17 下午4:11:10
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class JfSql implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -422444422003227975L;
    private String sql;
    public JfSql(String sql) {
       this.sql = sql;
    }
    /**
     * @return Returns the sql.
     */
    public String getSql() {
        return sql;
    }
    /**
     * @param sql The sql to set.
     */
    public void setSql(String sql) {
        this.sql = sql;
    }
    
}

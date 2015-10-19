/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.view;

import java.util.Map;

import com.common.constants.CommonConstants;
import com.common.utils.JsonTools;
import com.github.pagehelper.PageInfo;


/**
 * @Title: Page.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-10 下午3:33:02
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class Page<T> {

    private String aoData;

    private String sortColumn;

    private Map<String, String> paramMap = null;
    
    private PageInfo<T> list = null;
    
    public PageInfo<T> getPageList(){
        return this.list;
    }
    
    public void setPageList(PageInfo<T> list){
        this.list = list;
    }
    
    /**
     * @return Returns the aoData.
     */
    public String getAoData() {
        return aoData;
    }

    /**
     * @param aoData
     *            The aoData to set.
     */
    public void setAoData(String aoData) {
        this.aoData = aoData;
    }
    
    /**
     * getParamMap
     * @return
     */
    public Map<String, String> getParamMap(){
        if(paramMap==null){
            paramMap = JsonTools.turnDataTableParamToMap(this.aoData);
        }
         return paramMap;
    }
    
    public int getSEcho(){
        return Integer.parseInt(getParamMap().get(CommonConstants.DataTable.SECHO));
    }
    
    public int getStartPage(){
        String  beginIndex =  getParamMap().get(CommonConstants.DataTable.IDISPLAYSTART);
        int index = beginIndex==null||Integer.parseInt(beginIndex)<=0?1:Integer.parseInt(beginIndex); // 起始索引
        int pageSize = Integer.parseInt(getParamMap().get(CommonConstants.DataTable.IDISPLAYLENGTH)); // 每页显示的行数
        return index/pageSize==0?1:index/pageSize+1;
   }
    
    public int getPageSize(){
        return Integer.parseInt(getParamMap().get(CommonConstants.DataTable.IDISPLAYLENGTH)); // 每页显示的行数
    }
    
    public String getOrderBy() {
        StringBuilder sortStringBuilder = new StringBuilder();
        Map<String, String> paramMap = getParamMap();
        int sortColumnNum = Integer.parseInt(paramMap
                .get(CommonConstants.DataTable.ISORTINGCOLS));
        if (sortColumnNum > 0) {
            String[] sortColumnArr = sortColumn.split(CommonConstants.COMMA);
            for (int i = 0; i < sortColumnNum; i++) {
                String columnIndex = paramMap
                        .get(CommonConstants.DataTable.ISORTCOL_PREFIX + i);
                sortStringBuilder.append(sortColumnArr[Integer
                        .valueOf(columnIndex)]
                        + " "
                        + paramMap
                                .get(CommonConstants.DataTable.SSORTDIR_PREFIX
                                        + i) + " ");
                if (i < sortColumnNum - 1) {
                    sortStringBuilder.append(CommonConstants.COMMA);
                }
            }
        }
        return sortStringBuilder.toString();
    }
   
    /**
     * @return Returns the sortColumn.
     */
    public String getSortColumn() {
        return sortColumn;
    }

    /**
     * @param sortColumn
     *            The sortColumn to set.
     */
    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

}

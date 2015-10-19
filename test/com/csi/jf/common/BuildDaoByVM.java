/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.csi.jf.common;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.utils.JfLog;

/**
 * @Title: BuildDaoByVM.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-9-11 下午2:20:49
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class BuildDaoByVM {
    
    private static final Logger LOG = LoggerFactory.getLogger(BuildDaoByVM.class);
    
    private static final String UTF8 = "UTF-8";
    
    private String daoPath;
    
    /**
     * @return Returns the daoPath.
     */
    public String getDaoPath() {
        return daoPath;
    }

    /**
     * @param daoPath The daoPath to set.
     */
    public void setDaoPath(String daoPath) {
        this.daoPath = daoPath;
    }

    public String getLowercaseChar(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }
    
    public String getLastChar(String str) {
        if ((str != null) && (str.length() > 0)) {
            int dot = str.lastIndexOf('.');
            if ((dot > -1) && (dot < (str.length() - 1))) {
                return str.substring(dot + 1);
            }
        }
        return str;
    }
    
    public String createDaoCode(Class<?> c){
        try {
            String template = "Dao.vm";
            String path = this.getDaoPath();
            String suffix = "DAO.java";
            File filePath = new File(path);
            createFilePath(filePath);
            VelocityContext context = new VelocityContext();
            String name = getLastChar(c.getName());
            String fullName = c.getName();
            String fileName = path + "I"+getLastChar(c.getName())+ suffix;
            context.put("modelNameLower",getLowercaseChar(name));
            context.put("modelName",name);
            context.put("modelNamePkg",c.getName());
            context.put("pkg",PKG);
            context.put("selectWithModelId", fullName + ".selectWithModel");
            context.put("selectWithMapId", fullName + ".selectWithMap");
            context.put("saveId", fullName + ".save");
            context.put("updateId", fullName + ".update");
            context.put("batchDeleteId", fullName + ".batchDelete");
            //dao
            outputFile(fileName,createCode(c,template,context));
            
            template = "DaoImpl.vm";
            path = this.getDaoPath()+"impl/";
            suffix = "DAOImpl.java";
            filePath = new File(path);
            createFilePath(filePath);
            fileName = path + getLastChar(c.getName())+ suffix;
            //daoimpl
            outputFile(fileName,createCode(c,template,context));
        } catch (Exception e) {
            JfLog.error(LOG, e.getMessage(),e);
        }
        return null;
    }
    
    public String createCode(Class<?> c,String template,VelocityContext context){
        try {
            VelocityEngine ve = new VelocityEngine();
            ve.setProperty("input.encoding", UTF8);
            ve.setProperty("output.encoding", UTF8);
            ve.init();
            StringWriter writer = new StringWriter();
            ve.mergeTemplate("test/vm/"+template, UTF8, context, writer);
            JfLog.info(LOG,"file.content："+writer.toString());
            return writer.toString();
        } catch (Exception e) {
            JfLog.error(LOG, e.getMessage(),e);
        }
        return null;
    }
    
    public void outputFile(String fileName ,String content) throws Exception {
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        JfLog.info(LOG,"file.path："+file.getAbsolutePath());
        fw.write(content);
        fw.flush();
        fw.close();
    }
    
    public void createFilePath(File file){
        if(!file.exists()){
            JfLog.info(LOG,"创建["+file.getAbsolutePath()+"]情况："+file.mkdirs());
        }else{
            JfLog.info(LOG,"存在目录："+file.getAbsolutePath());
        }
    }

    private static String DAO_PATH = "src/main/java/sys/com/csi/jf/pay/sys/dao/";
    private static String PKG = "com.csi.jf.pay.sys.dao";
    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {}

}

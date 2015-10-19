/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.jobs;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.config.JfConstants;
import com.common.utils.JfLog;

/**
 * @Title: DataBackupJob.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-18 下午8:59:18
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class DataBackupJob{

    private static final Logger LOG = LoggerFactory.getLogger(DataBackupJob.class);
    
    /**
     * 备份MySQL数据库的命令 
     * mysqldump -hhostname -uusername -ppassword databasename > backupfile.sql
     */
    public static final String CMD_BACKUP_DB = "mysqldump --no-defaults -h${hostname} -u${username} -p${password} ${databasename} > ${sql}";
    
    /**
     * 备份MySQL数据库为带删除表的格式 备份MySQL数据库为带删除表的格式，能够让该备份覆盖已有数据库而不需要手动删除原有数据库。
     * mysqldump -–add-drop-table -uusername -ppassword databasename > backupfile.sql
     */
    public static final String CMD_BACKUP_DB_DROP = "mysqldump  --no-defaults -–add-drop-table -u${username} -p${password} ${databasename} > ${sql}";
   
    /** 直接将MySQL数据库压缩备份
     * mysqldump -hhostname -uusername -ppassword databasename | gzip > backupfile.sql.gz
     */
    public static final String CMD_BACKUP_DB_COMPRESS = "mysqldump  --no-defaults -h${hostname} -u${username} -p${password} ${databasename} | gzip > ${sql}";
   
    /** 
     * 备份MySQL数据库某个(些)表
     * mysqldump -hhostname -uusername -ppassword databasename specific_table1  specific_table2 > backupfile.sql
     */
    public static final String CMD_BACKUP_TABLES = "mysqldump --no-defaults -h${hostname} -u${username} -p${password} ${databasename} ${specific_table1} ${specific_table2} > ${backupfile.sql}";
   
    /** 
     * 同时备份多个MySQL数据库
     * mysqldump -hhostname -uusername -ppassword –databases databasename1 databasename2 databasename3 > multibackupfile.sql
     */
    public static final String CMD_BACKUP_DBS = "mysqldump --no-defaults -h${hostname} -u${username} -p${password} –databases ${databasename1} ${databasename2} ${databasename3} > ${multibackupfile.sql}";
   
    /** 
     * 仅仅备份数据库结构
     * mysqldump –no-data –databases databasename1 databasename2 databasename3 > structurebackupfile.sql
     */
    public static final String CMD_BACKUP_DB_STRUCTURE = "mysqldump --no-defaults –no-data –databases ${databasename1} ${databasename2} ${databasename3} > ${structurebackupfile.sql}";
   
    /** 
     * 备份服务器上所有数据库
     * mysqldump –all-databases > allbackupfile.sql
     */
    public static final String CMD_BACKUP_DB_ALL = "mysqldump --no-defaults –all-databases > ${allbackupfile.sql}";
   
    /**
     * 还原MySQL数据库的命令
     * mysql -hhostname -uusername -ppassword databasename < backupfile.sql
     */
    public static final String CMD_BACKUP_RECOVER = "mysql --no-defaults -h${hostname} -u${username} -p${password} ${databasename} < ${backupfile.sql}";
   
    /**
     * 还原压缩的MySQL数据库
     * gunzip < backupfile.sql.gz | mysql -uusername -ppassword databasename
     */
    public static final String CMD_BACKUP_RECOVER_COMPRESS = "gunzip < ${backupfile.sql.gz} | mysql -u${username} -p${password} ${databasename}";
   
    /** 
     * 将数据库转移到新服务器
     * mysqldump -uusername -ppassword databasename | mysql –host=*.*.*.* -C databasename
      */
    public static final String CMD_BACKUP_MOVE = "mysqldump --no-defaults -u${username} -p${password} ${databasename} | mysql –host=*.*.*.* -C ${databasename}";
   
    public void execute() {
    	if(LOG.isWarnEnabled()){
    		JfLog.warn(LOG,"unimplemented!");
    	}
    }

    
    public void runCmd(String cmd) {
        InputStreamReader isr = null;
        LineNumberReader input = null;
        try {
            Runtime rt = Runtime.getRuntime();
            //String cmd = "mysqldump -h localhost -uroot -p1234 test blog_user > e:/mysql.sql"; // 一定要加
                                                                                               // -h
            Process process = rt.exec("cmd /c " + cmd);
            isr = new InputStreamReader(process.getErrorStream(),JfConstants.UTF8);
            input = new LineNumberReader(isr);
            String line;
            while ((line = input.readLine()) != null) {
            		JfLog.info(LOG,line + "~~~~~~~~~~");
            }
        	JfLog.info(LOG,"备份成功!");
        } catch (IOException e) {
        		JfLog.error(LOG,"备份失败:"+e.getMessage(),e);
        }finally{
            try {
                if(isr!=null){
                    isr.close();
                }
            } catch (IOException e) {
            		JfLog.error(LOG,e.getMessage(),e);
            }
            try {
                if(input!=null){
                    input.close();
                }
            } catch (IOException e) {
            	{
            		JfLog.error(LOG,e.getMessage(),e);
            	}
            }
        }
    }
    
    
}

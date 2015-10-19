/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.ftp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.apache.velocity.texen.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.config.JFPayConfigFactory;
import com.common.exception.JfPayException;
import com.common.utils.JfLog;

/**
 * 压缩/解压缩zip包处理类
 * 
 * @Title: ZipUtil.java
 * @Description: <br>
 *               <br>
 * @Company: crowdsource
 * @Created on 2015-12-27 下午3:23:24
 * @author FengHaiBing
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class ZipUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ZipUtil.class);
    
    private ZipUtil(){
    }

    /**
     * 压缩文件file成zip文件zipFile
     *
     * @param file
     *            要压缩的文件
     * @param zipFile
     *            压缩文件存放地方
     * @throws JfPayException
     */
    public static void zip(File file, File zipFile) throws JfPayException {
        ZipOutputStream output = null;
        try {
            output = new ZipOutputStream(new FileOutputStream(zipFile));
            // 顶层目录开始
            zipFile(output, file, "");
        } catch (Exception e) {
    		{
    			JfLog.error(LOG,e.getMessage(), e);
    		}
        } finally {
            // 关闭流
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (Exception e) {
        		JfLog.error(LOG,e.getMessage(), e);
            }
        }
    }
    
    /**
     * 压缩文件为zip格式
     *
     * @param output ZipOutputStream对象
     * @param file 要压缩的文件或文件夹
     * @param basePath 条目根目录
     * @throws IOException
     */
    private static void zipFile(ZipOutputStream output, File file,
            String baseFilePath) throws IOException {
        FileInputStream input = null;
        String basePath = baseFilePath;
        try {
            // 文件为目录
            if (file.isDirectory()) {
                // 得到当前目录里面的文件列表
                File list[] = file.listFiles();
                basePath = basePath + (basePath.length() == 0 ? "" : "/")
                        + file.getName();
                // 循环递归压缩每个文件
                if(list!=null && list.length>0){
                    for (File f : list){
                        zipFile(output, f, basePath);
                    }
                }
            } else {
                // 压缩文件
                basePath = (basePath.length() == 0 ? "" : basePath + "/")
                        + file.getName();
                output.putNextEntry(new ZipEntry(basePath));
                input = new FileInputStream(file);
                int readLen = 0;
                int len = 8;
                byte[] buffer = new byte[1024 * len];
                while ((readLen = input.read(buffer, 0, 1024 * len)) != -1)
                    output.write(buffer, 0, readLen);
            }
        } catch (Exception e) {
    		{
    			JfLog.error(LOG,e.getMessage(), e);
    		}
        } finally {
            // 关闭流
            try {
                if (input != null){
                    input.close();
                }
            } catch (Exception e) {
        		{
        		JfLog.error(LOG,e.getMessage(), e);
        		}
            }
        }
    }
    
    /**
     * 解压zip文件
     *
     * @param zipFilePath
     *            zip文件绝对路径
     * @param unzipDirectory
     *            解压到的目录
     * @throws JfPayException
     */
    public static void unzip(String zipFilePath, String unzipDirectory)
            throws JfPayException {
        // 定义输入输出流对象
        InputStream input = null;
        OutputStream output = null;
        ZipFile zipFile = null;
        try {
            // 创建文件对象
            File file = new File(zipFilePath);
            // 创建zip文件对象
            zipFile = new ZipFile(file);
            // 创建本zip文件解压目录
            String name = file.getName().substring(0,
                    file.getName().lastIndexOf("."));
            File unzipFile = new File(unzipDirectory + "/" + name);
            if (unzipFile.exists()){
                boolean b = unzipFile.delete();
        		JfLog.info(LOG,"unzipFile deleted:"+b);
            }
            boolean b2 = unzipFile.mkdir();
    			JfLog.info(LOG,"unzipFile mkdir:"+b2);
            // 得到zip文件条目枚举对象
            Enumeration<? extends ZipEntry> zipEnum = zipFile.entries();
            // 定义对象
            ZipEntry entry = null;
            String entryName = null, path = null;
            String names[] = null;
            int length;
            // 循环读取条目
            while (zipEnum.hasMoreElements()) {
                // 得到当前条目
                entry = (ZipEntry) zipEnum.nextElement();
                entryName = String.valueOf(entry.getName());
                // 用/分隔条目名称
                names = entryName.split("\\/");
                length = names.length;
                path = unzipFile.getAbsolutePath();
                int len = 8;
                for (int v = 0; v < length; v++) {
                    if (v < length - 1){ // 最后一个目录之前的目录
                    	path += "/" + names[v] + "/";
                        FileUtil.mkdir(path);
                    } else { // 最后一个
                        if (entryName.endsWith("/")) // 为目录,则创建文件夹
                            FileUtil.mkdir(unzipFile.getAbsolutePath() + "/"
                                    + entryName);
                        else { // 为文件,则输出到文件
                            input = zipFile.getInputStream(entry);
                            output = new FileOutputStream(new File(
                                    unzipFile.getAbsolutePath() + "/"
                                            + entryName));
                            byte[] buffer = new byte[1024 * len];
                            int readLen = 0;
                            while ((readLen = input.read(buffer, 0, 1024 * len)) != -1)
                                output.write(buffer, 0, readLen);
                            	output.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
    			JfLog.error(LOG,e.getMessage(), e);
        } finally {
            // 关闭流
            try {
                if(zipFile!=null){
                    zipFile.close();
                }
            } catch (Exception e) {
        		JfLog.error(LOG,e.getMessage(), e);
            }
            try {
                if (input != null)
                    input.close();
            } catch (Exception e) {
        		JfLog.error(LOG,e.getMessage(), e);
            }
            try {
                if (output != null) {
                    output.flush();
                    output.close();
                }
            } catch (Exception e) {
        		JfLog.error(LOG,e.getMessage(), e);
            }
        }
    }
    
    /**
     * 把接受的全部文件打成压缩包 
     * 
     * @param files
     * @param outputStream
     */
    public static void zipFile(List<File> files,ZipOutputStream outputStream) {
        if(null == outputStream) {
            return;
        }
        
        //close outputStream begin
        if(files==null||files.isEmpty()) {
            try {
                outputStream.finish();
                return ;
            } catch (IOException e) {
        		JfLog.error(LOG,e.getMessage(),e);
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
            		JfLog.error(LOG,e.getMessage(),e);
                }
            }
        }
        //close outputStream end
        
        int size = files.size();
        try {
            for(int i = 0; i < size; i++) {
                File file = files.get(i);
                zipFile(file, outputStream);
            }
            
            outputStream.flush();
        } catch (IOException e) {
    			JfLog.error(LOG,e.getMessage(),e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
        		JfLog.error(LOG,e.getMessage(),e);
            }
        }
    }
    
    /**
     * 把接受的全部文件打成压缩包 
     * 
     * @param files
     * @param zipFileName
     */
    public static void zipFile(List<File> files, String zipFileName) {
        //数据合法性校验 begin
        if(files==null||files.isEmpty()) {
            return ;
        }
        
        if(null == zipFileName || "".equals(zipFileName)) {
            return;
        }
        
        File zipFile = new File(zipFileName);
        if(zipFile.isDirectory()) {
            return;
        }
        //数据合法性校验 end
        
        ZipOutputStream outputStream = null;
        try {
            outputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            int size = files.size();
            for(int i = 0; i < size; i++) {
                File file = files.get(i);
                zipFile(file, outputStream);
            }
            outputStream.flush();
        } catch (FileNotFoundException e) {
    			JfLog.error(LOG,e.getMessage(),e);
        } catch (IOException e) {
    			JfLog.error(LOG,e.getMessage(),e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
        		JfLog.error(LOG,e.getMessage(),e);
            }
        }
        
    }
    
    /**
     * 根据输入的文件与输出流对文件进行打包
     * 
     * @param inputFile
     * @param ouputStream
     */
    public static void zipFile(File inputFile, ZipOutputStream ouputStream) {
        try {
            if(inputFile.exists()) {
                /**如果是目录的话这里是不采取操作的，
                 * 至于目录的打包正在研究中*/
                if (inputFile.isFile()) {
                    FileInputStream in = new FileInputStream(inputFile);
                    BufferedInputStream bins = new BufferedInputStream(in, 512);
                    //org.apache.tools.zip.ZipEntry
                    ZipEntry entry = new ZipEntry(inputFile.getName());
                    ouputStream.putNextEntry(entry);
                    // 向压缩文件中输出数据  
                    int nNumber;
                    byte[] buffer = new byte[512];
                    while ((nNumber = bins.read(buffer)) != -1) {
                        ouputStream.write(buffer, 0, nNumber);
                    }
                    // 关闭创建的流对象  
                    bins.close();
                    in.close();
                } else {
                    try {
                        File[] files = inputFile.listFiles();
                        if(files!=null && files.length>0){
                            for (int i = 0; i < files.length; i++) {
                                zipFile(files[i], ouputStream);
                            }
                        }
                    } catch (Exception e) {
                		JfLog.error(LOG,e.getMessage(),e);
                    }
                }
            }
        } catch (Exception e) {
    			JfLog.error(LOG,e.getMessage(),e);
        }
    }

    /** 
     * 压缩一个文件到一个zip 
     * @param fileName 输入的文件 
     * @param out 输出的zip名 
     * @param outEntry zip中的条目名 
     */  
    public static void zip(String fileName,ZipOutputStream out,String outEntry) {  
    	File file = new File(fileName) ;  
    	FileInputStream is = null;  
    	try {  
    		if(file.exists()){  
    			is = new FileInputStream(file);  
    			out.putNextEntry(new ZipEntry(outEntry));  
    			int b ;  
    			while ((b = is.read()) != -1) {  
    				out.write(b);  
    			}  
    			is.close();  
    		}else{  
    			//按照项目的需要在没有的时候给予提示吧。  
    				JfLog.info(LOG,"找不到指定文件："+fileName);  
    		}  
    	} catch (FileNotFoundException e) {
    			JfLog.error(LOG,e.getMessage(),e);
    	} catch (IOException e2) {
    			JfLog.error(LOG,e2.getMessage(),e2);
    	} catch (Exception e3) {  
    			JfLog.error(LOG,e3.getMessage(),e3);
    	}finally{
    		try {
    			if(is != null){
    				is.close();
    			}
			} catch (IOException e) {
	    			JfLog.error(LOG,e.getMessage(),e);
			}
    	}  
    }  

    /** 
     *  
     * @param in 输入的目录 
     * @param inEntrys 输入的条目集合 
     * @param out 输出的zip名 
     */  
    public static void zip(String path,List<String> fileNames,String out) {  
    	try {  
    		OutputStream zipOut = new FileOutputStream(out);  
    		ZipOutputStream zipOutputStream = new ZipOutputStream(zipOut);  
    		for(String fileName : fileNames){  
    			zip(path+fileName,zipOutputStream,fileName);  
    		}  
    		zipOutputStream.close();  
    	} catch (Exception e) { 
    			JfLog.error(LOG,e.getMessage(),e); 
    	}  
    }  
    
    /** 
     * @param in 输入的目录 
     * @param date 根据日期获取对应文件名 
     * @param out 输出的zip名 
     */  
    public static InputStream zip(String path,String date) {
    	InputStream is = null;
    	String suffix = JFPayConfigFactory.getJfSettings().getFtpFileEbcSuffix();
    	List<String> fileNames = new ArrayList<String>();  
    	fileNames.add(date + suffix);
    	fileNames.add(date + "_check" + suffix);
    	fileNames.add(date + "_error" + suffix);
    	String outPath = path + "/" + date + ".zip";
    	try {  
    		OutputStream zipOut = new FileOutputStream(outPath);  
    		ZipOutputStream zipOutputStream = new ZipOutputStream(zipOut);  
    		for(String fileName : fileNames){  
    			zip(path+"/"+fileName,zipOutputStream,fileName);  
    		}  
    		zipOutputStream.close();  
    		File file = new File(outPath); 
    		is = new FileInputStream(file);
    		boolean b = file.delete();
    			JfLog.info(LOG,"file.deleted:"+b);
    	} catch (Exception e) {  
    			JfLog.error(LOG,e.getMessage(),e);  
    	}  
    	return is;
    }  

    public static void zipFileInputStream(List<InputStream> inputStreams, String fileName) {
        File file = new File(fileName);
        if(file.isDirectory()) {
            return;
        }
        ZipOutputStream outputStream = null;
        try {
            outputStream = new ZipOutputStream(new FileOutputStream(file));
            int size = inputStreams.size();
            for(int i = 0; i < size; i++) {
            	InputStream inputStream = inputStreams.get(i);
                zipFile(inputStream, outputStream, fileName);
            }
            outputStream.flush();
        } catch (FileNotFoundException e) {
    			JfLog.error(LOG,e.getMessage(),e);
        } catch (IOException e) {
    			JfLog.error(LOG,e.getMessage(),e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
        		JfLog.error(LOG,e.getMessage(),e);
            }
        }
        
    }
    public static void zipFile(InputStream inputStream, ZipOutputStream ouputStream, String zipFileName) {
    	try {
    		BufferedInputStream bins = new BufferedInputStream(inputStream, 512);
    		//org.apache.tools.zip.ZipEntry
    		ZipEntry entry = new ZipEntry(zipFileName);
    		ouputStream.putNextEntry(entry);
    		// 向压缩文件中输出数据  
    		int nNumber;
    		byte[] buffer = new byte[512];
    		while ((nNumber = bins.read(buffer)) != -1) {
    			ouputStream.write(buffer, 0, nNumber);
    		}
    		// 关闭创建的流对象  
    		bins.close();
    		inputStream.close();
    	} catch (Exception e) {
    		{
    			JfLog.error(LOG,e.getMessage(),e);
    		}
        }
    }
}


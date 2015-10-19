package com.csi.jf.common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.common.vo.PayAdmins;

import model.Entity;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class GenorateFilesByFM {
    private static File javaFile = null;

    public static void main(String args[]) {
        Configuration cfg = new Configuration();    
        try { 
            // 步骤一：指定 模板文件从何处加载的数据源，这里设置一个文件目录
            cfg.setDirectoryForTemplateLoading(new File("./test/template"));
            cfg.setObjectWrapper(new DefaultObjectWrapper());

            // 步骤二：获取 模板文件
            Template template = cfg.getTemplate("daoImpl.ftl");

            // 步骤三：创建 数据模型
            Map<String, Object> root = createDataModel();

            // 步骤四：合并 模板 和 数据模型
            if(javaFile != null){// 创建.java类文件
                Writer javaWriter = new FileWriter(javaFile);
                template.process(root, javaWriter);
                javaWriter.flush();
                System.out.println("文件生成路径：" + javaFile.getCanonicalPath());

                javaWriter.close();
            }
            // 输出到Console控制台
            Writer out = new OutputStreamWriter(System.out);
            template.process(root, out);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建数据模型
     * @return
     */
    private static Map<String, Object> createDataModel() {
        @SuppressWarnings("rawtypes")
        Class clazz = PayAdmins.class;
        Map<String, Object> root = new HashMap<String, Object>();
        Entity entity = new Entity();
        entity.setJavaPackage("com.csi.jf.pay.sys.dao.impl");
        entity.setClassName(clazz.getName().replace("com.csi.jf.pay.common.vo.", ""));
        entity.setSaveId(clazz.getName()+".save");
        entity.setUpdateId(clazz.getName()+".update");
        entity.setBatchDeleteId(clazz.getName()+".batchDelete");
        entity.setSelectByModel(clazz.getName()+".getList");
        entity.setSelectByModelId(clazz.getName()+".findById");
        
        File outDirFile = new File("E://src-template");
        if(!outDirFile.exists()){
            outDirFile.mkdir();
        }
        javaFile = toJavaFilename(outDirFile, entity.getJavaPackage(), entity.getClassName()+"DAOImpl");

        root.put("entity", entity);
        return root;
    }

    private static File toJavaFilename(File outDirFile, String javaPackage, String javaClassName) {
        String packageSubPath = javaPackage.replace('.', '/');
        File packagePath = new File(outDirFile, packageSubPath);
        File file = new File(packagePath, javaClassName + ".java");
        if(!packagePath.exists()){
            packagePath.mkdirs();
        }
        return file;
    }
}

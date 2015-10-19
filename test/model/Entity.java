package model;

import java.util.List;

/**
 * 实体类
 * @author Administrator
 *
 */
public class Entity {
    // 实体所在的包名
    private String javaPackage;
    // 实体类名
    private String className;
    // 父类名
    private String superclass;
    // 属性集合
    List<Property> properties;
    // 是否有构造函数
    private boolean constructors; 
    // SQL ID
    private String saveId; //保存
    private String updateId; //变更
    private String batchDeleteId; //删除
    private String selectByModel; //查询
    private String selectByModelId; //主键查询
    
    public String getSaveId() {
        return saveId;
    }

    public void setSaveId(String saveId) {
        this.saveId = saveId;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getBatchDeleteId() {
        return batchDeleteId;
    }

    public void setBatchDeleteId(String batchDeleteId) {
        this.batchDeleteId = batchDeleteId;
    }

    public String getSelectByModel() {
        return selectByModel;
    }

    public void setSelectByModel(String selectByModel) {
        this.selectByModel = selectByModel;
    }

    public String getSelectByModelId() {
        return selectByModelId;
    }

    public void setSelectByModelId(String selectByModelId) {
        this.selectByModelId = selectByModelId;
    }

    public String getJavaPackage() {
        return javaPackage;
    }
     
    public void setJavaPackage(String javaPackage) {
        this.javaPackage = javaPackage;
    }
     
    public String getClassName() {
        return className;
    }
     
    public void setClassName(String className) {
        this.className = className;
    }
     
    public String getSuperclass() {
        return superclass;
    }
     
    public void setSuperclass(String superclass) {
        this.superclass = superclass;
    }
     
    public List<Property> getProperties() {
        return properties;
    }
     
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
     
    public boolean isConstructors() {
        return constructors;
    }
     
    public void setConstructors(boolean constructors) {
        this.constructors = constructors;
    }   
 
}


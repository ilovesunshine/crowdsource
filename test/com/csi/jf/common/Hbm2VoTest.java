/**
 * 
 */
package com.csi.jf.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * @author daniel
 *
 */
public class Hbm2VoTest extends TestCase {
    
    private static final String SRC_CLASS = "com.csi.jf.pay.common.vo.";
    private static final String TARGET_CLASS = "com.csi.jf.pay.common.vo.";
    private static final String VO_PATH = "src\\main\\java\\common\\com\\csi\\jf\\pay\\common\\vo";//test\\vo\\";
    private static final String SQL_COLUMNS_ID = "Base_Column_List";
    private static final String CR = "\n\r";
	
	static Map<String,String> types = new HashMap<String,String>();
	static{
		types.put("java.lang.String", "VARCHAR");
		types.put("java.lang.Long", "BIGINT");
		types.put("java.lang.Integer", "INTEGER");
		types.put("java.lang.Float", "FLOAT");
		types.put("java.lang.Double", "DOUBLE");
		types.put("java.math.BigDecimal", "DECIMAL");
		types.put("java.sql.Date", "DATE");
		types.put("java.sql.Time", "TIME");
		types.put("java.sql.Timestamp", "TIMESTAMP");
		types.put("java.sql.Blob", "BLOB");
		types.put("java.sql.Clob", "CLOB");
		//NUMERIC
	}
	
	public class HbmFilenameFilter implements FilenameFilter { 
		@Override
		public boolean accept(File dir, String name) {
			if(name.endsWith(".hbm.xml")){
				return true;
			}
			return false;
		} 
    } 
	
	public void testVos(){
		File dir = new File(VO_PATH);
//		if(dir!=null && dir.isDirectory()){
//			File[] files = dir.listFiles(new HbmFilenameFilter());
//			for(File f:files){
		String[] files = new String[]{"TransactionJfAccount"};
		for(String fileName:files){
	        File f = new File("src/main/java/common/com/csi/jf/pay/common/vo/"+fileName+".hbm.xml");
				System.out.println("\n****"+f.getAbsolutePath());
				testVo(f);
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public void testVo(File f) {
		try {
			SAXReader reader = new SAXReader();
			String srcFilename = f.getAbsolutePath();
			//test/PayAudit.hbm.xml
			Document srcDoc = reader.read(new File(srcFilename));
			Element srcRoot = srcDoc.getRootElement();
			Element srcClass = srcRoot.element("class");
			Element srcId = srcClass.element("id");
			String tableName = srcClass.attributeValue("table");
			
			Document targetDoc = reader.read(new File("test/template.xml"));
			Element targetRoot = targetDoc.getRootElement();
			
			targetRoot.addAttribute("namespace",getClassName(srcClass));
			Element resultMap = targetRoot.element("resultMap");
			resultMap.addAttribute("type", getClassName(srcClass));
			addResult(resultMap,"id",getPColumn(srcId),getPName(srcId),getPType(srcId));
			List<Element> list = srcClass.elements("property");
			if(list!=null){
				for(Element e:list){
					addResult(resultMap,"result",getPColumn(e),getPName(e),getPType(e));
				}
			}
			
			//SQL-columns
			// <sql id="Base_Column_List">SA_ID,JFID,USER_ROLE,USER_NAME,ADMIN_PASS,USER_STATUS</sql>
			addSql(targetRoot,getPColumn(srcId),list);
			
			//SQL-selectWithModel
			addSelect("selectWithModel",targetRoot,srcId,getClassName(srcClass),tableName,list);
			addSelect("selectWithMap",targetRoot,srcId,"map",tableName,list);
			
			//SQL-run
            addRunSQL(targetRoot);
            
			//SQL-save
			addInsert(targetRoot,getClassName(srcClass),tableName,list);
			
			//SQL-update
			addUpdate(targetRoot,srcId,getClassName(srcClass),tableName,list);
			
			//SQL-betchDelete
			addBetchDelete(targetRoot,srcId,tableName);
			  
			//System.out.println(targetDoc.asXML());
			String outFilename = srcFilename.replace(".hbm.xml", "Mapper.xml");
			writer(targetDoc,outFilename);  
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getClassName(Element e){
		String name = e.attributeValue("name");
		return name.replace(SRC_CLASS, TARGET_CLASS);
	}
	
	private String getPName(Element e){
		return e.attributeValue("name");
	}
	
	private String getPType(Element e){
		String type = e==null?null:e.attributeValue("type");
		if(!types.containsKey(type)){
			throw new RuntimeException("Type["+type+"] cannot mapping!");
		}
		return types.get(e.attributeValue("type"));
	}
	
	private String getPColumn(Element e){
		Element column = e.element("column");
		return column.attributeValue("name");
	}
	
	private Element addSql(Element e,String colId,List<Element> list) {
		Element sub = e.addElement("sql");
		sub.addAttribute("id", SQL_COLUMNS_ID);
		StringBuilder fields = new StringBuilder();
		fields.append(CR).append(colId);
		//构造字段
		if(list!=null){
            for(Element el:list){
                fields.append(",").append(getPColumn(el));
            }
        }
		if(fields.toString().endsWith(",")){
		    fields.substring(0,fields.length()-1);
		}
		sub.setText(fields.toString());
		fields.append(CR);
		return sub;
	}
	
	/**
	 * <select id="selectOnePayAdmin" resultType="com.csi.jf.pay.common.vo.PayAdmins" parameterType="com.csi.jf.pay.common.vo.PayAdmins">
        select
        <include refid="Base_Column_List" />
        from jf_pay_admins
        <where>
            <if test="saId != null and saId != ''">
                and SA_ID = #{saId}
            </if>
            ...
        </where>
       </select>
	 * @param e
	 * @param id
	 * @param resultMap
	 * @param parameterType
	 * @return
	 */
	private Element addSelect(String id,Element e,Element eId,String parameterType,String tableName,List<Element> list) {
		Element sub = e.addElement("select");
		sub.addAttribute("id", id);
		sub.addAttribute("resultMap", "BaseResultMap");
		sub.addAttribute("parameterType", parameterType);
		
		StringBuilder sql = new StringBuilder("select");
		sql.append(CR);
		sql.append("<include refid=\"Base_Column_List\" />").append(CR);
		sql.append("from ").append(tableName).append(" where 1=1 ").append(CR);
		
		Element eIf = DocumentHelper.createElement("if");
        eIf.addAttribute("test", getPName(eId) + "!= null and " + getPName(eId) + " != ''");
        eIf.setText(" and " + getPColumn(eId) + " = #{" + getPName(eId) + ",jdbcType=" + getPType(eId) + "}");
        sql.append(eIf.asXML());
		if(list!=null){
            for(Element col:list){
                eIf = DocumentHelper.createElement("if");
                eIf.addAttribute("test",getPName(col)+"!= null and "+getPName(col)+" != ''");
                eIf.setText(" and " + getPColumn(col) + " = #{" + getPName(col) + ",jdbcType=" + getPType(col) + "}");
                sql.append(eIf.asXML());
            }
        }
		sub.addText(sql.toString());
		return sub;
	}
	
	/**
	 * <select id="runSQL" resultType="com.csi.jf.pay.common.vo.PayAdmins" parameterType="com.csi.jf.pay.common.vo.JfSql">
        ${sql}
    </select> 
	 * @param id
	 * @param e
	 * @param eId
	 * @param parameterType
	 * @param tableName
	 * @param list
	 * @return
	 */
    private Element addRunSQL(Element e) {
        Element select = e.addElement("select");
        select.addAttribute("id", "runSQL");
        select.addAttribute("resultMap", "BaseResultMap");
        select.addAttribute("parameterType", "com.csi.jf.pay.common.vo.JfSql");
        StringBuilder sql = new StringBuilder();
        sql.append(CR);
        sql.append("${sql}").append(CR);
        select.addText(sql.toString());
        return select;
    }

	/**
	 * <insert id="addPayConfig" parameterType="com.csi.jf.pay.common.vo.PayConfig" useGeneratedKeys="true">  
    <![CDATA[ 
        insert into 
        jf_pay_config ( 
            PKEY,PVALUE,MEMO,CREATE_TIME,CREATE_PERSON_ID,UPDATE_TIME,UPDATE_PERSON_ID
        ) values ( 
            #{pkey,jdbcType=VARCHAR},
            #{pvalue,jdbcType=VARCHAR},
            #{memo,jdbcType=VARCHAR},
            #{createTime,jdbcType=TIMESTAMP},
            #{createPersonId ,jdbcType=BIGINT},
            #{updateTime ,jdbcType=TIMESTAMP},
            #{updatePersonId ,jdbcType=BIGINT}
        ) 
    ]]>
    </insert>
	 * @return
	 */
	private Element addInsert(Element e,String parameterType,String tableName,List<Element> list){
        Element sub = e.addElement("insert");
        sub.addAttribute("id", "save");
        sub.addAttribute("useGeneratedKeys", "true");
        sub.addAttribute("parameterType", parameterType);
        
        StringBuilder sql = new StringBuilder();
        sql.append(CR);
        sql.append("insert into "+tableName+" ( ").append(CR);
        sql.append("   PKEY,PVALUE,MEMO,CREATE_TIME,CREATE_PERSON_ID,UPDATE_TIME,UPDATE_PERSON_ID ").append(CR);
        sql.append(") values ( ").append(CR);
        if(list!=null){
            int i=0;
            for(Element col:list){
                if (i < list.size() - 1) {
                    sql.append("#{"+getPName(col)+",jdbcType="+getPType(col)+"},").append(CR);
                } else {
                    sql.append("#{"+getPName(col)+",jdbcType="+getPType(col)+"}").append(CR);
                }
                i++;
            }
        }
        sql.append(") ").append(CR);
        sub.addText(sql.toString());
        return sub;
    
	}
	
	/**
	 * <update id="update" parameterType="com.csi.jf.pay.common.vo.PayConfig">
        update jf_pay_config
        <set>
            <if test="pkey != null">
                PKEY = #{pkey,jdbcType=BIGINT},
            </if>
            ...
        </set>
        where PKEY = #{pkey,jdbcType=BIGINT}
    </update>
	 * @param e
	 * @param parameterType
	 * @param tableName
	 * @param list
	 * @return
	 */
	private Element addUpdate(Element e,Element eId,String parameterType,String tableName,List<Element> list){
        Element sub = e.addElement("update");
        sub.addAttribute("id", "update");
        sub.addAttribute("parameterType", parameterType);
        
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(tableName);
        sql.append(CR);
        
        Element set = DocumentHelper.createElement("set");
        if(list!=null){
            int i=0;
            for(Element col:list){
                Element eIf = set.addElement("if");
                eIf.addAttribute("test",getPName(col)+"!= null ");
                if (i < list.size() - 1) {
                    eIf.setText(getPColumn(col) + " = #{" + getPName(col) + ",jdbcType=" + getPType(col) + "},");
                } else {
                    eIf.setText(getPColumn(col) + " = #{" + getPName(col) + ",jdbcType=" + getPType(col) + "}");
                }
                i++;
            }
        }
        sql.append(set.asXML());
        sql.append(CR);
        sql.append("where "+getPColumn(eId)+" = #{"+getPName(eId)+",jdbcType="+getPType(eId)+"}");
        sql.append(CR);
        sub.addText(sql.toString());
        return sub;
	}
	
	/**
	 * <delete id="batchDelete">
        DELETE FROM JF_PAY_CONFIG WHERE PKEY IN
        <foreach collection="list" index="index" item="item" open="("
            separator="," close=")">
            #{item}
        </foreach>
    </delete>
    */
	private Element addBetchDelete(Element e,Element id,String tableName){
        Element sub = e.addElement("delete");
        sub.addAttribute("id", "batchDelete");
        
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ").append(tableName).append(" WHERE ").append(getPColumn(id)).append(" IN");
        sql.append(CR);
        
        Element foreach = DocumentHelper.createElement("foreach");
        foreach.addAttribute("collection","array");
        foreach.addAttribute("index","index");
        foreach.addAttribute("item","item");
        foreach.addAttribute("open","(");
        foreach.addAttribute("separator",",");
        foreach.addAttribute("close",")");
        foreach.addText("#{item}");
        
        sql.append(foreach.asXML());
        sub.addText(sql.toString());
        return sub;
	}
	
    /**
	 * @param e
	 * @param name
	 * @param column
	 * @param property
	 * @param jdbcType
	 * @return
	 */
	public Element addResult(Element e,String name,String column,String property,String jdbcType){
		Element sub = e.addElement(name);
		sub.addAttribute("column", column);
		sub.addAttribute("property", property);
		sub.addAttribute("jdbcType", jdbcType);
		return sub;
	}
	
	 /** 
     * 把document对象写入新的文件 
     *  
     * @param document 
     * @throws Exception 
     */  
    public void writer(Document document,String outFilename) throws Exception {  
        // 紧凑的格式  
        // OutputFormat format = OutputFormat.createCompactFormat();  
        // 排版缩进的格式  
        OutputFormat format = OutputFormat.createPrettyPrint();  
        // 设置编码  
        format.setEncoding("UTF-8");  
        // 创建XMLWriter对象,指定了写出文件及编码格式  
        // XMLWriter writer = new XMLWriter(new FileWriter(new  
        // File("src//a.xml")),format);  
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(  
                new FileOutputStream(new File(outFilename)), "UTF-8"), format);  
        writer.setEscapeText(false);
        // 写入  
        writer.write(document);  
        // 立即写入  
        writer.flush();  
        // 关闭操作  
        writer.close();  
    }  
  

}

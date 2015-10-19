package com.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;

import com.chinasofti.ro.bizframework.modules.json.JsonConfig;

public class JsonTools {
	
	public static Map<String, String> turnDataTableParamToMap(String param){
		JSONArray jsonarray = JSONArray.fromObject(param);
	    Map<String, String> result = new HashMap<String, String>();
	    for (int i = 0; i < jsonarray.size(); i++) {
	        JSONObject obj = (JSONObject) jsonarray.get(i);
	        result.put(obj.getString("name"), obj.getString("value"));
	    }
	    return result;
	}
	/**
     * 
    * jsonת��list.
    * <br>��ϸ˵��
    * @param jsonStr json�ַ�
    * @return
    * @return List<Map<String,Object>> list
    * @throws
    * @author slj
    * @date 2013��12��24�� ����1:08:03
     */
    public static List<Map<String, Object>> parseJSON2List(String jsonStr){
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        @SuppressWarnings("unchecked")
		Iterator<JSONObject> it = jsonArr.iterator();
        while(it.hasNext()){
            JSONObject json2 = it.next();
            list.add(parseJSON2Map(json2.toString()));
        }
        return list;
    }
    /**
	 * 将实体类对象转成json格式 {"id" : idValue, "name" : nameValue,"aBean" : {"aBeanId" : aBeanIdValue, ...}}
	 * @param obj 被转的实体类对象
	 * @param format 时间格式 "yyyy-MM-dd"
	 * @return
	 */
	public static String toJson(Object obj,String format) {
		JSONObject json = JSONObject.fromObject(obj);
		return json.toString();
	}
	
	/**
     * 将实体类对象转成json格式 {"id" : idValue, "name" : nameValue,"aBean" : {"aBeanId" : aBeanIdValue, ...}}
     * 主要针对sql.Date/sql/Timestamp时间格式
     * @deprecated
     * @param obj 被转的实体类对象
     * @param format 时间格式 "yyyy-MM-dd"
     * @return
     */
	@Deprecated
    public static String listToJson(Object obj,String format) {
	    //TODO
        JsonConfig config = new JsonConfig();
//        config.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor(format));
//        config.registerJsonValueProcessor(java.util.Date.class,new DateJsonValueProcessor(format));
//        config.registerJsonValueProcessor(Timestamp.class,new DateJsonValueProcessor(format));
//        return JSONArray.fromObject(obj, config).toString();
        return null;
    }
	
   /**
    * 
   * jsonת��map.
   * <br>��ϸ˵��
   * @param jsonStr json�ַ�
   * @return
   * @return Map<String,Object> ����
   * @throws
   * @author slj
    */
    @SuppressWarnings("unchecked")
	public static Map<String, Object> parseJSON2Map(String jsonStr){
        ListOrderedMap map = new ListOrderedMap();
        //��������
        JSONObject json = JSONObject.fromObject(jsonStr);
        ListOrderedMap map2 = map;
		for(Object k : json.keySet()){
            Object v = json.get(k); 
            //����ڲ㻹������Ļ����������
            if(v instanceof JSONArray){
                List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				Iterator<JSONObject> it = ((JSONArray)v).iterator();
                while(it.hasNext()){
                    JSONObject json2 = it.next();
                    list.add(parseJSON2Map(json2.toString()));
                }
                map2.put(k.toString(), list);
            } else {
                map2.put(k.toString(), v);
            }
        }
        return map2;
    }
    
    /**
     * 
    * ͨ��HTTP��ȡJSON���.
    * <br>ͨ��HTTP��ȡJSON��ݷ���list
    * @param url ����
    * @return
    * @return List<Map<String,Object>> list
    * @throws
    * @author slj
     */
    public static List<Map<String, Object>> getListByUrl(String url){
        try {
            //ͨ��HTTP��ȡJSON���
            InputStream in = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line=reader.readLine())!=null){
                sb.append(line);
            }
            return parseJSON2List(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     
   /**
    * 
   * ͨ��HTTP��ȡJSON���.
   * <br>ͨ��HTTP��ȡJSON��ݷ���map
   * @param url ����
   * @return
   * @return Map<String,Object> ����
   * @throws
   * @author slj
    */
    public static Map<String, Object> getMapByUrl(String url){
        try {
            //ͨ��HTTP��ȡJSON���
            InputStream in = new URL(url).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line=reader.readLine())!=null){
                sb.append(line);
            }
            return parseJSON2Map(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 
    * mapת��json.
    * <br>��ϸ˵��
    * @param map ����
    * @return
    * @return String json�ַ�
    * @throws
    * @author slj
     */
    public static String mapToJson(Map<String, String> map) {
        Set<String> keys = map.keySet();
        String key = "";
        String value = "";
        StringBuffer jsonBuffer = new StringBuffer();
        jsonBuffer.append("{");
        for (Iterator<String> it = keys.iterator(); it.hasNext();) {
            key = (String) it.next();
            value = map.get(key);
            jsonBuffer.append(key + ":" +"\""+ value+"\"");
            if (it.hasNext()) {
                jsonBuffer.append(",");
            }
        }
        jsonBuffer.append("}");
        return jsonBuffer.toString();
    }
     
}

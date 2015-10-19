/**
 * $Id$
 *
 * Copyright (c) 2014 ChinaSoft International, Ltd. All rights reserved
 * JointForce Project
 *
 */
package com.common.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.exception.JfPayException;

/**
 * @Title: StringUtils.java
 * @Description: <br>
 * @Company: crowdsource
 * @Created on 2015-6-18 下午3:04:51
 * @author Administrator
 * @version $Revision: 1.0 $
 * @since 1.0
 */
public class StringUtils {

	private static final Logger LOG = LoggerFactory.getLogger(StringUtils.class);
	private static final char[] h = "0000000000000000".toCharArray();
	private StringUtils() {
	}

	/**
	 * Check a string if empty
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		return s == null || "".equals(s.trim());
	}

	/**
	 * 判断字符是否是为空，或者无意义的NULL字符
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNullStr(String s) {
		return s == null || "".equals(s.trim()) || "null".equalsIgnoreCase(s);
	}

	/**
	 * 判断对象是否是为空，或者无意义的NULL字符
	 * 
	 * @param obj
	 * @return
	 * 
	 * @author FengHaiBing
	 */
	public static boolean isNullStr(Object obj) {
		if (null == obj) {
			return true;
		}

		return isNullStr(obj.toString());
	}

	/**
	 * 非空字符判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return (str == null || "".equals(str.trim())) ? false : true;
	}

	/**
	 * 非空字符判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNullStr(String str) {
		return !isNullStr(str);
	}

	/**
	 * 获取子串在字符串中出现的次数
	 * 
	 * @author JCJ
	 * @throws JfPayException
	 */
	public static int finderStr(String sourceStr, String childStr) {
		char[] sourceChar = sourceStr.toCharArray();
		char[] childChar = childStr.toCharArray();
		int count = 0;
		int t;
		if ((sourceStr.length() - childStr.length()) < 0) {
			JfLog.error(LOG,"The child cannot be longer than the main chain!");
		} else {
			int strLength = sourceStr.length();
			for (int i = 0; i < strLength; i++) {
				t = 0;
				while ((i + t) < strLength && t < childStr.length() && childChar[t] == sourceChar[i + t]) {
					t++;
				}
				if (t == childStr.length()) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * 构建HTTP响应消息
	 * 
	 * @param success
	 * @param msg
	 * @return
	 */
	public static String renderHttpStatusMessage(boolean success, String msg) {
		JSONObject json = new JSONObject();
		json.put("success", success);
		json.put("message", msg);
		return json.toString();
	}

	/**
	 * 将html标签转义处理
	 * 
	 * @param str
	 * @return
	 */
	public static String str2html(String str) {
		if (StringUtils.isEmpty(str)) {
			return "";
		} else {
			return str.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
					.replaceAll("\"", "&quot").replaceAll(" ", "&nbsp;").replaceAll("\t", "&nbsp;&nbsp;");
		}
	}

	public static String null2str(String str) {
		return str == null ? "" : str;
	}

	/**
	 * 字符模板
	 * 
	 * @param template
	 * @param args
	 * @return
	 */
	public static String format(String template, Map<String, Object> args) throws JfPayException {
		StringBuffer sb = new StringBuffer();
		try {
			Pattern pattern = Pattern.compile("\\$\\{(.+?)\\}");
			Matcher matcher = pattern.matcher(template);
			while (matcher.find()) {
				String name = matcher.group(1);// 键名
				String value = (String) args.get(name);// 键值
				if (value == null) {
					value = "";
				} else {
					value = value.replaceAll("\\$", "\\\\\\$");
				}
				matcher.appendReplacement(sb, value);
			}
			// 最后还得要把尾串接到已替换的内容后面去
			matcher.appendTail(sb);
		} catch (Exception e) {
			throw new JfPayException(e);
		}
		return sb.toString();
	}

	/**
	 * 摭罩字符串
	 * 
	 * @param str
	 *            要摭罩的字符串
	 * @param part11
	 *            明文显示第一部分的起始位
	 * @param part1Length
	 *            明文显示第一部分的长度
	 * @param showEndLength
	 *            明文显示最后部分的长度
	 * @return
	 * 
	 * 		sample: maskString("13691424314", 0, 1, 4) 1*******314
	 * @author FengHaiBing
	 */
	public static String maskString(String str, int part11, int part1Length, int showEndLength) {
		// 数据合法性校验 begin
		if (null == str || "".equals(str)) {
			return str;
		}

		if (part11 > str.length() || (part11 + part1Length) > str.length() || part1Length < 0) {
			return str;
		}

		if (showEndLength < 0) {
			return str;
		}

		// 数据合法性校验 end
		String firstPart = str.substring(part11, part11 + part1Length);
		String firstPartMask = "";
		int start = part11 + part1Length;
		int end = str.length() - showEndLength - 1;
		for (int i = start; i <= end; i++) {
			firstPartMask += "*";
		}

		if (str.length() < showEndLength) {
			return str;
		}
		String endPart = str.substring(str.length() - showEndLength, str.length());

		return firstPart + firstPartMask + endPart;
	}

	/**
	 * @param string
	 * @param string2
	 * @return
	 */
	public static String null2str(String str, String defaultValue) {
		if (isEmpty(str)) {
			return defaultValue;
		}
		return str;
	}

	public static String encodeBase64(String data) {
		return encodeBase64(data.getBytes());
	}

	public static String encodeBase64(byte[] data) {
		int len = data.length;
		StringBuffer ret = new StringBuffer((len / 3 + 1) * 4);
		for (int i = 0; i < len; i++) {
			int c = data[i] >> 2 & 0x3F;
			ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			c = data[i] << 4 & 0x3F;
			i++;
			if (i < len)
				c |= data[i] >> 4 & 0xF;
			ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			if (i < len) {
				c = data[i] << 2 & 0x3F;
				i++;
				if (i < len)
					c |= data[i] >> 6 & 0x3;
				ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			} else {
				i++;
				ret.append('=');
			}
			if (i < len) {
				c = data[i] & 0x3F;
				ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			} else {
				ret.append('=');
			}
		}

		return ret.toString();
	}

	public static String decodeBase64(String data) {
		return decodeBase64(data.getBytes());
	}

	public static String decodeBase64(byte[] data) {
		int len = data.length;
		StringBuffer ret = new StringBuffer(len * 3 / 4);
		for (int i = 0; i < len; i++) {
			int c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(data[i]);
			i++;
			int c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(data[i]);
			c = c << 2 | c1 >> 4 & 0x3;
			ret.append((char) c);
			i++;
			if (i < len) {
				c = data[i];
				if (61 == c)
					break;
				c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char) c);
				c1 = c1 << 4 & 0xF0 | c >> 2 & 0xF;
				ret.append((char) c1);
			}
			i++;
			if (i >= len)
				continue;
			c1 = data[i];
			if (61 == c1)
				break;
			c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char) c1);
			c = c << 6 & 0xC0 | c1;
			ret.append((char) c);
		}

		return ret.toString();
	}

	public static String replace(String src, String mod, String str) {
		if ((src == null) || (src.length() == 0)) {
			return src;
		}
		if ((mod == null) || (mod.length() == 0)) {
			return src;
		}
		StringBuffer buffer = new StringBuffer();
		int idx1 = 0;
		int idx2 = 0;
		while ((idx2 = src.indexOf(mod, idx1)) != -1) {
			buffer.append(src.substring(idx1, idx2)).append(str);
			idx1 = idx2 + mod.length();
		}
		buffer.append(src.substring(idx1));
		return buffer.toString();
	}

	public static final String replace(String line, String oldString, String newString, int[] count) {
		if (line == null)
			return null;
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			int counter = 0;
			counter++;
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;

			for (int j = i; (i = line.indexOf(oldString, i)) > 0; j = i) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				buf.append(line2, j, line2.length - j);
				count[0] = counter;
				return buf.toString();
			}
		}
		return line;
	}
	 public static final String zeroPadString(String string, int length)
	  {
	    if ((string == null) || (string.length() > length)) {
	      return string;
	    }
	    StringBuffer buf = new StringBuffer(length);
	    buf.append(h, 0, length - string.length()).append(string);
	    return buf.toString();
	  }
	 public static final String turnStrToSqlIn(String str){
	     if(StringUtils.isEmpty(str)){
	         return str;
	     }else{
	         return "('"+str.replace(",", "','")+"')";
	     }
	 }
	 public static void main(String[] args) {
        System.out.println(StringUtils.turnStrToSqlIn("1,2,3"));
    }
}

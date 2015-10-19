package com.common.sdk;

import com.common.utils.StringUtils;

public class IdSecurity {
	public static String encode(Long id)
	  {
	    if (id == null)
	      return null;
	    double r = Math.random() * 100.0D;
	    int sec = new Double(r).intValue();
	    String data = id.toString() + ";" + sec;
	    return StringUtils.encodeBase64(data);
	  }

	  public static String encodeStr(String id)
	  {
	    if (StringUtils.isEmpty(id))
	      return null;
	    double r = Math.random() * 100.0D;
	    int sec = new Double(r).intValue();
	    String data = id + ";" + sec;
	    return StringUtils.encodeBase64(data);
	  }

	  public static Long decode(String sec)
	  {
	    if (StringUtils.isEmpty(sec))
	      return null;
	    String data = "";
	    try
	    {
	      data = StringUtils.decodeBase64(sec);
	    }
	    catch (Exception e)
	    {
	      return null;
	    }
	    String[] ids = data.split(";");
	    if (ids.length < 2)
	      return null;
	    return new Long(ids[0]);
	  }

	  public static String decodeStr(String sec)
	  {
	    if (StringUtils.isEmpty(sec))
	      return null;
	    String data = "";
	    try
	    {
	      data = StringUtils.decodeBase64(sec);
	    }
	    catch (Exception e)
	    {
	      return null;
	    }
	    String[] ids = data.split(";");
	    if (ids.length < 2)
	      return null;
	    return new String(ids[0]);
	  }
}

package com.common.utils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinasofti.jf.users.sso.client.sso.OAuth2Config;
import com.csi.jointforce.common.model.UserInfo;
public class UrlParserUtil {
	protected static Logger logger = LoggerFactory.getLogger(UrlParserUtil.class);
	  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("menus");
	  private static final ResourceBundle CONTEXT_PATH_BUNDLE = ResourceBundle.getBundle("context_path");
	  public static final String BBS_SSO_Secretkey = "JF_ 5v0fymbo15";
	  public static final String ENV_DEV = "develop";
	  public static final String ENV_PRO = "production";
	  public static final String ENV_RC = "rc";
	  public static final String ENV_TEST = "test";
	  public static final String ENV_LOCAL = "local";

	  public static String getContext(HttpServletRequest request)
	  {
	    String evn = getEnvironment();
	    if ((StringUtils.isEmpty(evn)) || (evn.equals("local")))
	      return request.getContextPath();
	    return parseUrl("${jointforce}", request);
	  }

	  public static String getMenuJson(UserInfo user)
	  {
	    String role = "PERSONAL";
	    if (user.getBuId() != null)
	      if ((user.getBuType() != null) && (user.getBuType().intValue() == 1))
	      {
	        if (user.isOWNER())
	          role = "BUOWNER";
	        else
	          role = "BU";
	      }
	      else
	        role = "TEAM";
	    logger.debug(new StringBuilder().append("PageUtil->getMenuJson->role=").append(role).toString());
	    try
	    {
	      return RESOURCE_BUNDLE.getString(role.toUpperCase()).trim();
	    }
	    catch (MissingResourceException e)
	    {
	    }
	    return null;
	  }

	  public static String pageTile(String title)
	  {
	    String myTitle = "欢迎访问Jointforce";
	    if (StringUtils.isEmpty(title))
	      myTitle = new StringBuilder().append(myTitle).append("|").append(title).toString();
	    return myTitle;
	  }

	  public static String getLoginName(UserInfo user)
	  {
	    if ((user != null) && (!user.isDEV()))
	    {
	      if ((StringUtils.isNotBlank(user.getBuName())) && (user.getBuName().length() <= 4))
	        return user.getBuName();
	      if ((StringUtils.isNotBlank(user.getBuName())) && (user.getBuName().length() > 4))
	        return new StringBuilder().append(user.getBuName().substring(0, 3)).append("***").toString();
	    }
	    if (!StringUtils.isEmpty(user.getNickname()))
	      return user.getNickname();
	    if (!StringUtils.isEmpty(user.getFullName()))
	      return user.getFullName();
	    if (user.getUserName().length() <= 4)
	      return user.getUserName();
	    return new StringBuilder().append(user.getUserName().substring(0, 3)).append("***").toString();
	  }

	  public static String getLoginFullName(UserInfo user)
	  {
	    if ((user != null) && (user.getJfId() != null) && (user.isOWNER()) && (user.getPlatform() != null) && (user.getPlatform().equals(UserInfo.PLATFORM_D)))
	      return user.getBuName();
	    if (!StringUtils.isEmpty(user.getNickname()))
	      return user.getNickname();
	    if (!StringUtils.isEmpty(user.getFullName()))
	      return user.getFullName();
	    return user.getUserName();
	  }


	  public static String getEnvironment()
	  {
	    String jointforce = parseUrl("${jointforce}");
	    if (match(jointforce, "https://dev.jointforce.com"))
	      return "develop";
	    if (match(jointforce, "https://www.jointforce.com"))
	      return "production";
	    if (match(jointforce, "https://rc.jointforce.com"))
	      return "rc";
	    if ((match(jointforce, "https://test.jointforce.com")) || (match(jointforce, "https://test2.jointforce.com")))
	      return "test";
	    return "local";
	  }

	  private static boolean match(String input, String type)
	  {
	    if (StringUtils.isEmpty(input))
	      return false;
	    boolean flag = false;
	    try
	    {
	      Pattern regex = Pattern.compile(type);
	      Matcher matcher = regex.matcher(input);
	      flag = matcher.matches();
	    }
	    catch (Exception e)
	    {
	      flag = false;
	    }
	    return flag;
	  }

	  public static Map<String, String> getContextPath()
	  {
	    //BizCache bizCache = (BizCache)BeanFactory.getBean("bizCache");
	    Map<String, String> hashMap = new HashMap<String, String>();
	    Iterator<String> iter = CONTEXT_PATH_BUNDLE.keySet().iterator();
	    while (iter.hasNext())
	    {
	      String key = (String)iter.next();
	      hashMap.put(new StringBuilder().append("${").append(key).append("}").toString(), CONTEXT_PATH_BUNDLE.getString(key));
	    }
	    OAuth2Config config = new OAuth2Config();
	    if ((config == null) || (StringUtils.isEmpty(OAuth2Config.getSsoServer())))
	      try
	      {
	        config = new OAuth2Config("OAuth2-config.properties");
	      }
	      catch (IOException e)
	      {
	        e.printStackTrace();
	      }
	    if ((config != null) && (!StringUtils.isEmpty(OAuth2Config.getSsoServer())))
	      hashMap.put("${SSOSEVER}", OAuth2Config.getSsoServer());
	    return hashMap;
	  }

	  public static String parseUrl(String url, HttpServletRequest request)
	  {
	    Map hostMap = getContextPath();
	    hostMap = hostMap == null ? new HashMap() : hostMap;
	    if (request != null)
	      wholeVariable(hostMap, request);
	    return replaceVariables(url, hostMap);
	  }

	  public static String parseUrl(String url)
	  {
	    return parseUrl(url, null);
	  }

	  private static void wholeVariable(Map hostMap, HttpServletRequest request)
	  {
	    hostMap.put("${JFCONTEXT}", request.getContextPath());
	    hostMap.put("${JFHTTP}", request.getScheme().trim().toLowerCase());
	    hostMap.put("${JFHOST}", request.getServerName().trim().toLowerCase());
	    hostMap.put("${JFPORT}", String.valueOf(request.getServerPort()));
	    String server = new StringBuilder().append(request.getScheme().trim().toLowerCase()).append("://").append(request.getServerName().trim().toLowerCase()).toString();
	    hostMap.put("${JFSERVER}", server);
	  }

	  public static String replaceVariables(String url, Map variables)
	  {
	    if ((StringUtils.isEmpty(url)) || (variables == null) || (variables.isEmpty()))
	      return url;
	    Iterator iter = variables.keySet().iterator();
	    while (iter.hasNext())
	    {
	      String key = (String)iter.next();
	      url = StringUtils.replace(url, key, (String)variables.get(key));
	    }
	    return url;
	  }

	  public static String getClient(HttpServletRequest request)
	  {
	    String clientType = request.getParameter("clientType");
	    if ((clientType == null) || (clientType.trim().equals("")))
	    {
	      String agent = request.getHeader("User-Agent");
	      if (agent == null)
	      {
	        clientType = "-1";
	        return clientType;
	      }
	      agent = agent.toLowerCase();
	      if (agent.contains("android"))
	        clientType = "1";
	      else if ((agent.contains("iphone")) || (agent.contains("ipad")))
	        clientType = "0";
	      else
	        clientType = "-1";
	    }
	    return clientType;
	  }
	  public static void main(String[] args) {
		UrlParserUtil parserUtil = new UrlParserUtil();
		Map<String, String> map = parserUtil.getContextPath();
	  }
}

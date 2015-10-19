package com.common.vo;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import com.common.utils.StringUtils;
import com.common.utils.UrlParserUtil;
import com.csi.jointforce.common.model.Bu;

public class UserInfo {
	private static final long serialVersionUID = -8999080854832432805L;
	  private Long jfId;
	  private String userName;
	  private String fullName;
	  private String role;
	  private String loginIp;
	  private Timestamp loginTime;
	  private String email;
	  private String mailbox;
	  private String photoUrl;
	  private String mobile;
	  private String nickname;
	  private Long buId;
	  private String buName;
	  private boolean buUser;
	  private Integer buType;
	  private Integer pswStrength;
	  private Integer platform;
	  List buList;
	  public static final String ROLE_TYPE_ADMIN = "ADMIN";
	  public static final String ROLE_TYPE_DEV = "DEV";
	  public static final String ROLE_TYPE_BUM = "BUM";
	  public static final String ROLE_TYPE_OWNER = "OWNER";
	  public static final String ROLE_TYPE_PM = "PM";
	  public static final String ROLE_TYPE_USER = "USER";
	  public static final String ROLE_TYPE_PTS = "PTS";
	  public static final String ROLE_TYPE_PUSER = "PUSER";
	  public static final Integer PLATFORM_D = Integer.valueOf(0);
	  public static final Integer PLATFORM_B = Integer.valueOf(1);

	  public List getBuList()
	  {
	    return this.buList;
	  }

	  public void setBuList(List buList)
	  {
	    this.buList = buList;
	  }

	  public String getPhotoUrl()
	  {
	    String url = "${jfprofile}/resume/pic/" + this.jfId;
	    return UrlParserUtil.parseUrl(url);
	  }

	  public boolean switchBu(Long buid)
	  {
	    if ((buid == null) || (this.buList == null) || (this.buList.isEmpty()))
	      return false;
	    Iterator iterator = this.buList.iterator();
	    while (iterator.hasNext())
	    {
	      Bu bu = (Bu)iterator.next();
	      if (buid.equals(bu.getBuId()))
	      {
	        this.buId = bu.getBuId();
	        this.buName = bu.getBuName();
	        this.buType = bu.getButype();
	        this.role = bu.getRole();
	        return true;
	      }
	    }
	    return false;
	  }

	  public UserInfo()
	  {
	  }

	  public UserInfo(String role)
	  {
	    this.role = role;
	  }

	  public boolean isBuUser()
	  {
	    return this.buUser;
	  }

	  public void setBuUser(boolean buUser)
	  {
	    this.buUser = buUser;
	  }

	  public Integer getBuType()
	  {
	    return this.buType;
	  }

	  public void setBuType(Integer buType)
	  {
	    this.buType = buType;
	  }

	  public Integer getPswStrength()
	  {
	    return this.pswStrength;
	  }

	  public void setPswStrength(Integer pswStrength)
	  {
	    this.pswStrength = pswStrength;
	  }

	  public boolean isPTS()
	  {
	    if (this.buType == null)
	      return false;
	    return 4 == this.buType.intValue();
	  }

	  public String getMobile()
	  {
	    return this.mobile;
	  }

	  public void setMobile(String mobile)
	  {
	    this.mobile = mobile;
	  }

	  public Long getBuId()
	  {
	    return this.buId;
	  }

	  public void setBuId(Long buId)
	  {
	    this.buId = buId;
	  }

	  public String getBuName()
	  {
	    return this.buName;
	  }

	  public void setBuName(String buName)
	  {
	    this.buName = buName;
	  }

	  public String getFullName()
	  {
	    return this.fullName;
	  }

	  public void setFullName(String fullName)
	  {
	    this.fullName = fullName;
	  }

	  public Long getJfId()
	  {
	    return this.jfId;
	  }

	  public void setJfId(Long jfId)
	  {
	    this.jfId = jfId;
	  }

	  public String getUserName()
	  {
	    return this.userName;
	  }

	  public void setUserName(String userName)
	  {
	    this.userName = userName;
	  }

	  public String getRole()
	  {
	    this.role = (StringUtils.isEmpty(this.role) ? "DEV" : this.role);
	    return this.role;
	  }

	  public boolean isUser()
	  {
	    return "USER".equals(this.role);
	  }

	  public void setRole(String role)
	  {
	    this.role = role;
	  }

	  public String getLoginIp()
	  {
	    return this.loginIp;
	  }

	  public void setLoginIp(String loginIp)
	  {
	    this.loginIp = loginIp;
	  }

	  public Timestamp getLoginTime()
	  {
	    return this.loginTime;
	  }

	  public void setLoginTime(Timestamp loginTime)
	  {
	    this.loginTime = loginTime;
	  }

	  public String getEmail()
	  {
	    return this.email;
	  }

	  public void setEmail(String email)
	  {
	    this.email = email;
	  }

	  public String getMailbox()
	  {
	    return this.mailbox;
	  }

	  public void setMailbox(String mailbox)
	  {
	    this.mailbox = mailbox;
	  }

	  public String getNickname()
	  {
	    return this.nickname;
	  }

	  public void setNickname(String nickname)
	  {
	    this.nickname = nickname;
	  }

	  public Integer getPlatform()
	  {
	    return this.platform;
	  }

	  public void setPlatform(Integer platform)
	  {
	    this.platform = platform;
	  }

	  public boolean equals(Long jfId, Long buId, String role, Integer platform, Integer butype)
	  {
	    boolean jfIdEqual = isObjectEqual(this.jfId, jfId);
	    boolean buIdEqual = isObjectEqual(this.buId, buId);
	    boolean roleEqual = isObjectEqual(this.role, role);
	    boolean platEqual = isObjectEqual(this.platform, platform);
	    boolean butypeEqual = isObjectEqual(this.buType, butype);
	    return (jfIdEqual) && (buIdEqual) && (roleEqual) && (platEqual);
	  }

	  public boolean isSameBu(Long buId)
	  {
	    return isObjectEqual(this.buId, buId);
	  }

	  private boolean isObjectEqual(Object object1, Object object2)
	  {
	    if ((object1 == null) && (object2 == null))
	      return true;
	    return (object1 != null) && (object1.equals(object2));
	  }

	  public boolean isDEV()
	  {
	    return (StringUtils.isEmpty(this.role)) || ("DEV".equalsIgnoreCase(this.role)) || ("USER".equalsIgnoreCase(this.role));
	  }

	  public boolean isUSER()
	  {
	    return (StringUtils.isEmpty(this.role)) || ("USER".equalsIgnoreCase(this.role));
	  }

	  public boolean isPM()
	  {
	    return (StringUtils.isEmpty(this.role)) || ("PM".equalsIgnoreCase(this.role));
	  }

	  public boolean isBUM()
	  {
	    return ("BUM".equalsIgnoreCase(this.role)) || ("PTS".equalsIgnoreCase(this.role)) || ("OWNER".equalsIgnoreCase(this.role));
	  }

	  public boolean isAdmin()
	  {
	    return ("ADMIN".equalsIgnoreCase(this.role)) || ("ADMIN".equalsIgnoreCase(this.role));
	  }

	  public boolean isOWNER()
	  {
	    return "OWNER".equalsIgnoreCase(this.role);
	  }
}

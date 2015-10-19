<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="author" content="CSI"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/jfpay.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<%=request.getAttribute("csstheme")%>/theme.<c:out value='${webConfig["csssuffix"]}'/>"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<%=request.getAttribute("csstheme")%>/components/jqgrid/jquery.jqgrid.<c:out value='${webConfig["csssuffix"]}'/>"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<%=request.getAttribute("csstheme")%>/components/tree/zTreeStyle.<c:out value='${webConfig["csssuffix"]}'/>"/>
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<%=request.getAttribute("csstheme")%>/bootcss/bootstrap-ie6.<c:out value='${webConfig["csssuffix"]}'/>"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<%=request.getAttribute("csstheme")%>/bootcss/ie.<c:out value='${webConfig["csssuffix"]}'/>"/>
<![endif]-->
<% /**注:此文件禁止修改,会影响平台升级。如果想通过include方式为所有页面添加js库或css库请参照此文件自行新建jsp   */ %>

<style>
#header-spec {
border-top: 2px solid #3dd5af;
height: 50px;
background-color: #666;
}
#header-spec-box {
width: 1000px;
margin: 0 auto;
}
.header_spec_left {
float: left;
width: 360px;
padding-top: 12px;
}
.header_spec_left img {
display: inline;
margin-right: 10px;
width: auto\9;
height: auto;
max-width: 100%;
vertical-align: middle;
border: 0;
-ms-interpolation-mode: bicubic;
}
.header_spec_nav {
float: right;
}
.header_spec_right {
float: right;
line-height: 50px;
color: #8a8a8a;
}
.header_spec_nav li {
float: left;
height: 50px;
line-height: 50px;
padding-right: 35px;
}
.header_spec_nav li a {
font-size: 17px;
color: #c2c2c2;
}
</style>

<div id="header-spec">
	<div id="header-spec-box" style="width:auto;">
		<div class="header_spec_left" style="width:360px">
			<a style="color:#FFFFFF; font-size:24px;" href=""><img src="<c:url value='/images/topblack_new.png'/>" />运营平台</a>
			
		</div>
		<div class="header_spec_nav" style="float:right">
			<ul> 
				
			<li><a target="contentFrame" id="editPwd" href="javascript:"><span class="small" style="color:#fff">&nbsp;&nbsp; <i class="icon-edit icon-white" style="margin-top:5px;"></i>&nbsp;修改密码</span></a></li>
			<li style="padding-right:10px;"><a id="admin_logout" href="/logout"><span class="small" style="color:#fff">&nbsp;&nbsp; <i class="icon-off icon-white" style="margin-top:5px;"></i>&nbsp;退出系统&nbsp;&nbsp;</span></a></li>
				
			</ul>
		</div>		<div class="header_spec_right">
			
		</div>
		<div class="clear"></div>
	</div>
</div>
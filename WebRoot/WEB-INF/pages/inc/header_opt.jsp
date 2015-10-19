<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="author" content="CSI"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/styles/base.${webConfig["csssuffix"]}'/>" media="screen, projection"/> --%>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/common.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/jfpay.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<%=request.getAttribute("csstheme")%>/theme.<c:out value='${webConfig["csssuffix"]}'/>"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<%=request.getAttribute("csstheme")%>/components/jqgrid/jquery.jqgrid.<c:out value='${webConfig["csssuffix"]}'/>"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<%=request.getAttribute("csstheme")%>/components/tree/zTreeStyle.<c:out value='${webConfig["csssuffix"]}'/>"/>
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<%=request.getAttribute("csstheme")%>/bootcss/bootstrap-ie6.<c:out value='${webConfig["csssuffix"]}'/>"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<%=request.getAttribute("csstheme")%>/bootcss/ie.<c:out value='${webConfig["csssuffix"]}'/>"/>
<![endif]-->
<% /**注:此文件禁止修改,会影响平台升级。如果想通过include方式为所有页面添加js库或css库请参照此文件自行新建jsp   */ %>
<script type="text/javascript" src="<c:url value='/js/extends/utils/checkInput.${webConfig["jssuffix"]}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lib/biz.jquery.${webConfig["jssuffix"]}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/i18n/i18n_${webConfig["language"]}.${webConfig["jssuffix"]}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lib/WdatePicker.${webConfig["jssuffix"]}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lib/biz.${webConfig["jssuffix"]}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/extends/common.${webConfig["jssuffix"]}'/>"></script>

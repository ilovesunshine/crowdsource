<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="c-rt"  prefix="c" %>
<%@ taglib uri="fmt-rt"  prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="author" content="CSI"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${csssuffix}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/jfpay.${csssuffix}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${csssuffix}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/base.${csssuffix}'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/demo_table.${csssuffix}'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/jquery.dataTables.min.${csssuffix}'/>" />

<link rel="stylesheet" type="text/css" href="<c:url value='/styles/jquery.tooltip.${csssuffix}'/>" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<c:out value='${csstheme}'/>/theme.<c:out value='${csssuffix}'/>"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<c:out value='${csstheme}'/>/theme.<c:out value='${csssuffix}'/>"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<c:out value='${csstheme}'/>/components/jqgrid/jquery.jqgrid.<c:out value='${csssuffix}'/>"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<c:out value='${csstheme}'/>/components/tree/zTreeStyle.<c:out value='${csssuffix}'/>"/>
<title>JointForce</title>
<script type="text/javascript" src="<c:url value='/js/jquery-1.9.0.${jssuffix}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/i18n/i18n_zh.${jssuffix}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/common.${jssuffix}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/extends/DatePattern.${jssuffix}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lib/WdatePicker.${jssuffix}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.dataTables.${jssuffix}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.dataTables.min.${jssuffix}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-ui-1.9.2.custom.${jssuffix}'/>"></script>

<script type="text/javascript" src="<c:url value='/js/extends/jf/JfDataTable.js'/>"></script>
</head>
<title></title>
</head>
<body>

</body>
</html>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="../../common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>企业/个人用户验签</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${csssuffix}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${csssuffix}'/>" media="screen, projection"/> 
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/themeDS..${csssuffix}'/>"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/common.${csssuffix}'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/menu.${csssuffix}'/>"/>
<%@ include file="/WEB-INF/pages/inc//header_sys.jsp" %>
</head>

<body>
<div class="pay_cont_sys">
<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
	<div class="barTitle">
		账户信息&nbsp;|&nbsp;
		<span>
			<a href="<c:url value='/sys/accountreg/account/resolved'/>">注销账户</a> 
		</span>
	</div>
	<div style="height:150px;text-align:center;line-height: 150px;color:red;">
		<c:out value="${ errorMsg }"/>
	</div>
</div>
<%@ include file="/WEB-INF/pages/inc//footer.jsp" %>
<!-- 引入尾文件-->

<script type="text/javascript">
</script>
</body>
</html>
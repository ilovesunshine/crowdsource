<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="c-rt"  prefix="c" %>
<%@ taglib uri="fmt-rt"  prefix="fmt" %>
<%@ page import="com.csi.jointforce.common.util.LoginUtil" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>管理员登录</title>
	<%@ include file="../inc/header_sys.jsp" %>
</head>

<body>
<%@ include file="../common.jsp" %>
<div class="pay_cont_sys" style = "text-align:center;margin-top:120px;">
	<p>管理员登录</p>
	<form id="loginForm" name="loginForm" method="post" action="<c:url value='/sys/loginpost'/>" class="bizform">
		<input type="hidden" name="pid" id="pid" value="<%=LoginUtil.getUser(request).getUserName()%>">
		用户名：<%=LoginUtil.getUser(request).getUserName()%>
		<br>
		管理密码:<input type="password" name="pwd" id="pwd">
		<br>
		<input type="submit" name="submit" value="登录">
	<br class="clear">
</div>

<%@ include file="../inc/footer.jsp" %>
<script type="text/javascript">

</script>
</body>
</html>

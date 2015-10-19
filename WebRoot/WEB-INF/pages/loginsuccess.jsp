
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="./common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>login success</title>
	<script type="text/javascript">
		function loginout(){
			location.href="<c:url value='/login/logout'/>";
		}
		var oldUrl = document.referrer;
		if(!oldUrl.endsWith('/tologin')&&!oldUrl.endsWith('/login') && !oldUrl.endsWith('/logout')){
			location.href = oldUrl;
		}
	</script>
	</head>
	<body>
	Hello,name is <c:out value="${curuser}"></c:out>
	<a href="#" onclick="loginout();">退出</a>
	</body>
</html>
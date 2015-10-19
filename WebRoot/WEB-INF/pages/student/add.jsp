<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>all students</title>
</head>
<body>
	add
	<form method="post" action="<c:url value='/springmvc/addstu'/>" >
		name：<input name="name" type="text" maxlength="10"/>
		<button onclick="submit">新增</button>
	</form>
</body>
</html>
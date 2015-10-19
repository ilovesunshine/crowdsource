<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>all students</title>
</head>
<body>
List all Students
	<c:forEach var="stu" items="${list}">
		<c:out value="${stu.name}"></c:out><br/>
	</c:forEach>
</body>
</html>
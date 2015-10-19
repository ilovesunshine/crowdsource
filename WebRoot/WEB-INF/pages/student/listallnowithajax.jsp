<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Pages Demo</title>
</head>
<body>
	<form action="<c:url value='/springmvc/querystuwithparam'/>" id="queryForm" method="post">
		name:<input name="name" type="text" value='<c:out value="${stu.name}"/>'/>
		id:<input name="id" value='<c:out value="${stu.id}"/>'type="text"/> 
		<button type="button" onclick="queryStudent()">查询</button>
		<button type="button" onclick="exportStudentWithExcel()">导出</button>
		<table>
			<tr>
				<th><input id="checkedAll" type="checkbox" onclick="selectedAll('queryForm',this.checked)" ></input>全选</th>
				<th>name</th>
				<th>id</th>
			</tr>
			<c:forEach var="stu" items="${pages.list}">
				<tr>
					<td><input type="checkbox" name="deleteids" onchange="changCheckboxChecked('deleteids','queryForm','checkedAll');"/></td>
					<td><c:out value="${stu.name}"></c:out></td>
					<td><c:out value="${stu.id}"></c:out></td>
				</tr>
			</c:forEach>
		</table>
		<jsp:include page="../pagefooter.jsp"></jsp:include>
	</form>
</body>
<script type="text/javascript">
	function queryStudent(){
		$("#queryForm").attr("action","<c:url value='/springmvc/querystuwithparam'/>");
		queryFormSubmit(1);
	}
	function exportStudentWithExcel(){
		$("#queryForm").attr("action","<c:url value='/springmvc/exportStu'/>");
		$("#queryForm").submit();
	}
</script>
</html>
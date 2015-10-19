<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="common.jsp" %>

<body>
	<a href="#" onclick="login();">登陆</a>
	<script type="text/javascript">
		function login(){
			location.href="<c:url value='/login/login'/>";
		}
	</script>
</body>

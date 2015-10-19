<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<html>
<head>
<title>新增管理员</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${csssuffix}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${csssuffix}'/>" media="screen, projection"/> 
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/themeDS.${csssuffix}'/>"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/common.${csssuffix}'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/menu.${csssuffix}'/>"/>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
</head>
<body>
<div class="pay_cont_sys">
	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
    <div class="barTitle">
		<span>新增管理员</span>
	</div>
	<form id="payAdminsForm" action="<c:url value='/sys/admins/save'/>"  method="post" >
      <div class="ui-table ui-border">
       <table class="table">
       	<tbody>
			<tr>
				<td class="inputLabelTd">用户名：</td>
				<td class="inputTd">
					<input id="userName" name="userName" type="text" class="text"/>
				</td>
			</tr>
			<tr>
				<td class="inputLabelTd">管理密码：</td>
				<td class="inputTd">
					<input id="adminPass" name="adminPass" type="password" class="text"/>
				</td>
			</tr>
			<tr>
				<td class="inputLabelTd">用户角色：</td>
				<td class="inputTd" colspan="0">
					<select name="userRole" id="userRole" class="select">
						<option value="1">运营管理员</option>
						<option value="2">系统管理员</option>
						<option value="3">运营专员</option>
					</select>
				</td>
			</tr>
		</tbody>
		<tfoot class="footTd">
			<tr>
				<td class="inputTd" colspan="2">
					<button id="submit_button" class="button" type="button" onclick="save(this);"><fmt:message key='button.submit'/></button>&nbsp;&nbsp;
        			<button id="reset_button" class="button" type="reset"><fmt:message key='button.reset'/></button>&nbsp;&nbsp;
        			<button id="back_button" class="button" type="button" onclick="window.location = '<c:url value="/sys/admins/show"/>'"><fmt:message key='button.back'/></button>
				</td>
			</tr>
		</tfoot>
	  </table>
	 </div>
	</form>
</div>

<script type="text/javascript">
	function save(button){
		$("#payAdminsForm").submit();
	}
	
</script>
</body>
</html>

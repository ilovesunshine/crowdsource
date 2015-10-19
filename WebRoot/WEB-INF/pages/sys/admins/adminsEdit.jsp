<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<html>
<head>
<title>编辑管理员</title>
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
		<span>编辑管理员</span>
	</div>
	<form id="payAdminsForm" action="<c:url value='/sys/admins/update'/>"  method="post" >
		<div class="ui-table ui-border">
			<input type="hidden" id="edit_saId" name="saId" value="<c:out value='${payAdmins.saId}'/>"/>
	    <table class="table">
	    	<tbody>
				<tr>
					<td class="inputLabelTd">JFID：</td>
					<td class="inputTd"><c:out value='${payAdmins.jfid}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">用户名：</td>
					<td class="inputTd" colspan="0"><c:out value='${payAdmins.userName}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">用户角色：</td>
					<td class="inputTd" colspan="0">
						<select name="userRole" id="userRole" class="select">
							<option value="1" selected="${payAdmins.userRole==1?'selected':'null'}">运营管理员</option>
							<option value="2" selected="${payAdmins.userRole==2?'selected':'null'}">系统管理员</option>
							<option value="3" selected="${payAdmins.userRole==3?'selected':'null'}">运营专员</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="inputLabelTd">管理密码：</td>
					<td class="inputTd" colspan="0"><input type="password" name="adminPass"></td>
				</tr>
				<tr>
					<td class="inputLabelTd">状态：</td>
					<td class="inputTd" colspan="0">
						<select name="userStatus" id="userStatus" class="select" autocomplete="off">
							<option value="0" selected="${payAdmins.userStatus==0?'selected':'null'}">锁定</option>
							<option value="1" selected="${payAdmins.userStatus==1?'selected':'null'}">正常</option>
						</select>
					</td>
				</tr>
			</tbody>
			<tfoot class="footTd">
				<tr>
					<td class="inputTd" colspan="2">
						<button id="submit_button" class="button" type="button" onclick="update(this);"><fmt:message key='button.submit'/></button>&nbsp;&nbsp;
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
	function update(button){
		$("#payAdminsForm").submit();
	}
</script>
</body>
</html>

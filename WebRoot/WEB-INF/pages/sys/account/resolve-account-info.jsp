<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>blank</title>
	<%@ include file="../../common.jsp" %>
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
		<span>账户注册</span>
	</div>
	<c:if test="${errorMsg!=''}">
		<span style="color:red;display:block"><c:out value="${errorMsg}"/></span>
	</c:if>
	<c:if test="${msg!=''}">
		<span style="color:green;display:block"><c:out value="${msg}"/></span>
	</c:if>
	<div id="conditions" class="ui-table ui-widget ui-corner-all ui-margin" style="display:block">
		<form id="paForm" action="<c:url value='/sys/accountreg/account/openAccount'/>"  method="post" >
		
		<!-- 隐藏域Start -->
		<input type="hidden" name = "accountType" value = "<c:out value="${account.accountType}"/>" >
		<input type="hidden" name = "accountId" value = "<c:out value="${account.accountId}"/>" >
		<!-- 隐藏域End -->
		<table id= "entRechargeTB" width="1190" border="0" cellpadding="10" cellspacing="10"  class="hj_tab_boy">
		<%-- <c:choose> --%>
			<!-- 企业信息Start -->
			<c:if test="${account.accountType eq '1'}">
									
			<tr>		
				<td style="width:25%" class="pay-td-right">企业buId：</td>
				<td><c:out value="${account.jfId}"/></td>
			</tr>
			<tr>		
				<td class="pay-td-right">登录名：</td>
				<td><input type="text" name="enterName" value = "<c:out value='${account.enterName}'/>"></td>
			</tr>
			<tr>		
				<td class="pay-td-right">企业法人名称：</td>
				<td><input type="text" name="extUserNameSafe" value = "<c:out value='${account.extUserNameSafe}'/>"></td>
			</tr>
			<tr>		
				<td class="pay-td-right">组织机构代码：</td>
				<td><input type="text" name="codecertificate" value = "<c:out value='${account.codecertificate}'/>" ></td>
			</tr>
			<tr>		
				<td class="pay-td-right">营业执照号：</td>
				<td><input type="text" name="bulicense" value = "<c:out value='${account.bulicense}'/>"></td>
			</tr>
			<tr>
				<td class="pay-td-right">企业法定代表人身份证号码：</td>
				<td><input type="text" name="extIdCard" maxlength=18 size=18 value = "<c:out value='${account.extIdCardSafe}'/>"></td>
			</tr>
			<tr>
				<td class="pay-td-right">支付密码：</td>
				<td><input type="text" name="pwd" maxlength=6 size="6" value= "<c:out value='${pwd}'/>"></td>
			</tr>
			<tr>		
				<td class="pay-td-right">手机号：</td>
				<td><input type="text" name="extMobile" maxlength=11  size="11" value = "<c:out value='${account.extMobileSafe}'/>"></td>
			</tr>
		 </c:if>
		   	<!-- 企业信息Ent -->	
		 <c:if test="${account.accountType eq '0' }">
			<!-- 个人信息Start -->
			<tr>		
				<td style="width:25%" class="pay-td-right">个人JfId：</td>
				<td><c:out value="${account.jfId}"/></td>
			</tr>
			<tr>		
				<td class="pay-td-right">登录名：</td>
				<td><input type="text" name="enterName" value = "<c:out value='${account.enterName}'/>"></td>
			</tr>
			<tr>		
				<td class="pay-td-right">姓名：</td>
				<td><input type="text" name="extUserName" value = "<c:out value='${account.extUserNameSafe}'/>"></td>
			</tr>
			<tr>
				<td class="pay-td-right">身份证号：</td>
				<td><input type="text" name="extIdCard" maxlength=18 value = "<c:out value='${account.extIdCardSafe}'/>"></td>
			</tr>
			<tr>
				<td class="pay-td-right">支付密码：</td>
				<td><input type="text" name="pwd" maxlength=6 value = "<c:out value='${pwd}'/>"></td>
			</tr>
			<tr>		
				<td class="pay-td-right">手机号：</td>
				<td><input type="text" name="extMobile" maxlength=11 value = "<c:out value='${account.extMobileSafe}'/>"></td>
			</tr>
			<!-- 个人信息end -->
		 </c:if>
			<tr>		
				<td class="pay-td-right"><input type="submit" name="submit" value="重新开户"/></td>
				<td><input type="reset" name="reset" value="重置"/></td>
			</tr>
		</table>
		
		
		</form>
	</div>

</div>
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<script type="text/javascript">

</script>
</body>
</html>

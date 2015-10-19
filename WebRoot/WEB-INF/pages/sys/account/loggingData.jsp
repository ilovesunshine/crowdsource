
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>blank</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${csssuffix}'/>" media="screen, projection"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${csssuffix}'/>" media="screen, projection"/> 
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/themeDS.${csssuffix}'/>"/>

	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/common.${csssuffix}'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/menu.${csssuffix}'/>"/>	<style>
	  input{
	   width :200px;
	  }
	</style>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %></head>

<body>

	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
	<div class="barTitle">
		<span>平台账户数据录入</span>
	</div>
		<c:if test="${errorMsg!=''}">
		<span style="color:red;display:block"><c:out value="${errorMsg}"/></span>
		</c:if>
		<c:if test="${msg!=''}">
		<span style="color:green;display:block"><c:out value="${msg}"/></span>
		</c:if>
	<div id="conditions" class="ui-table ui-widget ui-corner-all ui-margin" style="display:block">
		<form id="paForm" action="<c:url value='/accountreg/loggingdata/logging'/>"  method="post" >
		
		<table id= "entRechargeTB" width="1190" border="0" cellpadding="7" cellspacing="8"  class="hj_tab_boy">
			<tr>		
				<td width="200">录入账户：</td>
				<td> <select name="useType">
				 <option value = "01">资金账户</option> 
				 <option value = "02">冻结账户</option> 
				 <option value = "03">运营账户</option> 
				
				</select> </td>
			</tr>
			<tr>		
				<td >企业Id(enterName)：</td>
				<td><input type="text" name="enterName"></td>
			</tr>
			<tr>		
				<td width="80">企业法人名称：</td>
				<td><input type="text" name="extUsernameSafe"></td>
			</tr>
			<tr>		
				<td>组织机构代码：</td>
				<td><input type="text" name="codecertificate"></td>
			</tr>
			<tr>		
				<td>营业执照号：</td>
				<td><input type="text" name="bulicense" ></td>
			</tr>
			<tr>
				<td>企业法定代表人身份证号码：</td>
				<td><input type="text" name="extIdCardSafe"></td>
			</tr>
			<tr>
				<td>第三方平台账户ID（ExtUserNo）：</td>
				<td><input type="text" name="extUserNo"></td>
			</tr>
			<tr>
				<td>钱包介质ID（ExtMediumNo）：</td>
				<td><input type="text" name="extMediumNo"></td>
			</tr>
			<tr>
				<td>第三方卡号（ExtCardNo）：</td>
				<td><input type="text" name="extCardNo"></td>
			</tr>
			<!-- <tr>
				<td>支付密码：</td>
				<td><input type="text" name="paypass"></td>
			</tr> -->
			<tr>		
				<td>手机号：</td>
				<td><input type="text" name="extMobile"></td>
			</tr>
		</table>
		<input type="submit" name="submit" value="提交" style="margin-left: 10px;padding-left:5px;padding-right:5px;"/>
		<input type="reset" name="reset" value="重置" style="margin-left: 10px;padding-left:5px;padding-right:5px;">
		</form>
		
	</div>

</div>
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<script type="text/javascript">

</script>
</body>
</html>

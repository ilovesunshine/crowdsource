<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/inc/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>JointForce</title>
	<%@ include file="/pages/inc/header_sys.jsp" %>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${webConfig["csssuffix"]}'/>" media="screen, projection"/> 
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/themeDS.${webConfig["csssuffix"]}'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/components/jqgrid/jquery.jqgrid.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/components/tree/zTreeStyle.${webConfig["csssuffix"]}'/>" media="screen, projection"/>

	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/common.${webConfig["csssuffix"]}'/>"/>
	<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/menu.${webConfig["csssuffix"]}'/>"/>
	<script type="text/javascript" src="<c:url value='/js/extends/utils/DatePattern.${webConfig["jssuffix"]}'/>"></script>	
	<style>
	  input{
	   width :200px;
	  }
	</style>
</head>

<body>
<%@ include file="/pages/inc/common.jsp" %>
<div class="pay_cont_sys">

	<%@ include file="/pages/inc/menu_sys.jsp" %>
	<div class="barTitle">
		<span>转账确认</span>
	</div>
	<c:if test="${resultcode!='00'}">
		<span style="color:red;display:block"><c:out value="${errtext}"/></span>
	</c:if>
	<c:if test="${msg!=''}">
		<span style="color:green;display:block"><c:out value="${msg}"/></span>
	</c:if>
	<div id="conditions" class="ui-table ui-widget ui-corner-all ui-margin" style="display:block">
		<form id="jfTransferForm" action="<c:url value='/sys/transfer/post'/>"  method="post" >
		<table id= "jfTransferTB" width="1190" border="0" cellpadding="10" cellspacing="10"  class="hj_tab_boy">
			<tr>		
				<td width="250">平台账户：</td>
				<td> 02-冻结账户</td>
			</tr>
			<tr>		
				<td >待处理订单号：</td>
				<td><c:out value="${orderNo}"/><input type="hidden" id="orderNo" name="orderNo" value="<c:out value='${orderNo}'/>"></td>
			</tr>
			<tr>		
				<td >待处理订单交易号：</td>
				<td><c:out value="${transId}"/></td>
			</tr>
			<tr>		
				<td >待处理接包方账号：</td>
				<td><c:out value="${incardno}"/><input type="hidden" id="incardno" name="incardno" value="<c:out value='${incardno}'/>"></td>
			</tr>
			<tr>		
				<td>待转账金额（不含服务费）：</td>
				<td><c:out value="${dAmount}"/></td>
			</tr>
			<!--<tr>		
				<td>转账密码：</td>
				<td><input type="text" id="transferPass"  name="transferPass"></td>
			</tr>-->
		</table>
		<input type="submit" name="submit" value="提交" style="margin-left: 10px;padding-left:5px;padding-right:5px;"/>
		<input type="button" name="button" value="返回" style="margin-left: 10px;padding-left:5px;padding-right:5px;" onclick="javascript:history.back()">
		</form>
	</div>

</div>
<%@ include file="/pages/inc/footer.jsp" %>
<script type="text/javascript">

</script>
</body>
</html>

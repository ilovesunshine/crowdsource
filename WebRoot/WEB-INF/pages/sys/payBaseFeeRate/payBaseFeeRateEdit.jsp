<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>服务费率管理</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${csssuffix}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${csssuffix}'/>" media="screen, projection"/> 
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/themeDS.${csssuffix}'/>"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/common.${csssuffix}'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/menu.${csssuffix}'/>"/>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
<style>
		.jfpay-area-info{
			clear:both;
			margin:20px;
			padding-bottom:10px;
		}
		.jfpay-area-info-bottom{
			border-bottom:1px solid #e6daa1;
		}
		.jfpay-area-info-title{
			margin-left:0px;
			font-size:18px;
			font-weight: bold;
		}
		.jfpay-area-info-context{
			margin-top:30px;
		}
		.jfpay-special-word{
			margin-left:5px;
			font-size:18px;
			font-weight: bold;
		}
		.jfpay_table_context{
			width:1190px;
			border:0; 
			cellpadding:10; 
			cellspacing:10;
		}
		.jfpay_table_tr_style{
			height:35px;
			font-size:18px;
		}
		.jfpay_table_td_style{
			width:100px;
		}
	</style>
</head>
<body>
<div class="pay_cont_sys">
	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
    <div class="barTitle">
		<span>服务费率管理</span>
	</div>
	<div id="conditions">
	<form id="payConfigForm" action="<c:url value='/sys/feerate/update'/>"  method="post" >
			<input type="hidden" id="baseFeeRateId" name="baseFeeRateId" value="<c:out value='${payBaseFeeRate.baseFeeRateId}'/>"/>
		<div class="jfpay-area-info-context">
	    <table id= "feerate" class="jfpay_table_context">
	    		<tr class="jfpay_table_tr_style">		
	    			<td class="jfpay_table_td_style">费率类型：</td>
	    			<td>
						<c:if test="${payBaseFeeRate.feeType == '0' }">接包方服务费率</c:if>
						<c:if test="${payBaseFeeRate.feeType == '1' }">发包方服务费率</c:if>
					</td>
					<td class="jfpay_table_td_style">服务费率：</td>
					<td>
						<input id="feeRate" name="feeRate" type="text" style="width:50px;font-weight: bold;"  class="text" value="<fmt:formatNumber value='${payBaseFeeRate.feeRate * 100}' pattern="#,##0"/>"/>
						<span class="jfpay-special-word">%</span>
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
				</tr>
				<tr class="jfpay_table_tr_style">
					<td class="">备注：</td>
					<td class="" colspan="3">
					<textarea id="memo" name="memo"  class="textarea"><c:out value='${payBaseFeeRate.memo}'/></textarea>
					</td>
				</tr>
			</table>
			<table id= "feerate" class="jfpay_table_context" style="margin-top:35px;">
				<tfoot class="footTd">
					<tr>
						<td class="inputTd" colspan="4">
							<button id="submit_button" class="button" type="button" onclick="update(this);"><fmt:message key='button.submit'/></button>&nbsp;&nbsp;
	            			<button id="back_button" class="button" type="button" onclick="window.location = '<c:url value="/sys/feerate/show"/>'"><fmt:message key='button.back'/></button>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</form>
	</div>
</div>

<script type="text/javascript">
	function update(button){
		$("#payConfigForm").submit();
	}
	
</script>
</body>
</html>

<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
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
		.jfpay_table_td_style_len{
			width:300px;
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
		<c:forEach var="feerate" items="${feeRateList}" varStatus="st"> 
		<c:if test="${feerate.feeType == '0' }">
		<div class="jfpay-area-info-context">
			<span class="jfpay-area-info-title">接包方服务费率</span><br><br>
			<table id= "perfeerate"  width="1190" class="jfpay_table_context">
				<tr class="jfpay_table_tr_style">		
					<td class="jfpay_table_td_style">服务费率：</td>
					<td class="jfpay_table_td_style_len"><fmt:formatNumber value="${feerate.feeRate * 100}" pattern="#,##0"/><span class="jfpay-special-word">%</span></td>
					<td class="jfpay_table_td_style">修改时间：</td>
					<td><c:out value="${feerate.updateTime}"/></td>
				</tr>
			</table>
			</div><br>
			<button id="editBtn" class="button" type="button" onclick="edit(<c:out value="${feerate.baseFeeRateId}"/>);">编辑</button>
			<button id="viewBtn" class="button" type="button" onclick="showHistory('0');">查看历史</button>
			<br><br>
			<div class="jfpay-area-info-bottom"></div>
			</c:if>
			<c:if test="${feerate.feeType == '1' }">
			<div class="jfpay-area-info-context">
			<span class="jfpay-area-info-title">发包方服务费率</span><br><br>
			<table id= "entfeerate" width="1190"  class="jfpay_table_context">
				<tr class="jfpay_table_tr_style">		
					<td class="jfpay_table_td_style">服务费率：</td>
					<td class="jfpay_table_td_style_len"><fmt:formatNumber value="${feerate.feeRate * 100}" pattern="#,##0"/><span class="jfpay-special-word">%</span></td>
					<td class="jfpay_table_td_style">修改时间：</td>
					<td><c:out value="${feerate.updateTime}"/></td>
				</tr>
			</table>
			</div><br>
			<button id="editBtn" class="button" type="button" onclick="edit(<c:out value="${feerate.baseFeeRateId}"/>);">编辑</button>
			<button id="viewBtn" class="button" type="button" onclick="showHistory('1');">查看历史</button>
			<br><br>
			</c:if>
		</c:forEach>
	</div>
</div>
<script type="text/javascript">
	function edit(basefeerateid){
		location.href="<c:url value='/sys/feerate/edit'/>?id=" + basefeerateid;
	}

	function showHistory(feeType){
		window.location="payBaseFeeRateLog?feeType="+feeType;
		location.href="<c:url value='/sys/feeratelog/show'/>?feeType=" + feeType;
	}
	
</script>
</body>
</html>

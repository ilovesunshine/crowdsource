<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>EBC交易日志明细</title>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
<style type="text/css">
.butOnHover {
	background: url(<   c :   url value =   '/images/moth_hover.jpg'/ >)
		no-repeat;
	width: 100px;
	height: 32px;
	margin-top: 8px;
	border: none;
	color: #ffffff;
}

.info-title {
	font-size: 15px;
	font-weight: bold;
	border-bottom: 1px solid #ccc;
	padding-top: 25px;
	padding-bottom: 2px;
}

.info-btn {
	color: #fff;
	background-color: #1dd2ad;
	padding: 2px 5px;
	cursor: pointer;
	font-size: 14px;
	border-radius: 5px;
}

.info-back-span {
	display: block;
	float: right;
}
table th{
    white-space: nowrap;
}
table td{
    white-space: nowrap;
}
body,table{
    font-size:12px;
}
table{
    empty-cells:show; 
    border-collapse: collapse;
    margin:0 auto;
}
</style>
</head>

<body>
	<input id="accountId" name="accountId" type="hidden" value="<c:out value='${accountId }'/>">
	<input id="date" name="date" type="hidden" value="<c:out value='${date }'/>">
	<!--cont-->
	<div class="pay-opt-body" id="wrap">
		<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
		<div class="info-title">
				<span><c:out value="${logName }" /></span>
				<span class="info-back-span">
					<button id="infoBackBtn" class="info-btn" type="button">返回</button>
				</span>
		</div>
		<div>
			<span class="pay_currency pay_currency_margin">单位：元  |  交易币种 ：人民币</span>
		</div>
		<!-- 表格数据 start -->
		<div class="pay_opt_main">
		<div style="overflow: auto; width: 100%;">
			<table id="b-bill-table" border="0" cellpadding="0" cellspacing="0" class="pay_opt_table" style="width:1190px;">
				<c:if test="${!empty list}">
					<c:forEach items="${list}" var="var" varStatus="status">
						<c:if test="${status.first}">
							<tr>
								<th class="hj_tab_topbj pay-td-center" style="width:100;">商户号</th>
								<th class="hj_tab_topbj pay-td-center" style="width:100;">订单号</th>
								<th class="hj_tab_topbj pay-td-center" style="width:30;">订单类型 </th>
								<th class="hj_tab_topbj pay-td-center" style="width:30;">唯一标识</th>
								<th class="hj_tab_topbj pay-td-center" style="width:80;">转出介质id</th>
								<th class="hj_tab_topbj pay-td-center" style="width:80;">转入卡号</th>
								<th class="hj_tab_topbj pay-td-center" style="width:80;">转入介质id</th>
								<th class="hj_tab_topbj pay-td-center" style="width:80;">转入卡号</th>
								<th class="hj_tab_topbj pay-td-center" style="width:20;">币种</th>
								<th class="hj_tab_topbj pay-td-center" style="width:50;">金额</th>
								<th class="hj_tab_topbj pay-td-center" style="width:20;">状态</th>
								<th class="hj_tab_topbj pay-td-center" style="width:80;">时间 </th>
								<c:if test="${flag == 'check'}">
									<th class="hj_tab_topbj pay-td-center" style="width:80;">check结果的的确认时间 </th>
								</c:if>
								<c:if test="${flag == 'error'}">
									<th class="hj_tab_topbj pay-td-center" style="width:80;">处理结果描述 </th>
									<th class="hj_tab_topbj pay-td-center" style="width:80;">资金变动情况 </th>
								</c:if>
							</tr>
						</c:if>
						<c:if test="${!status.first}">
							<tr>
								<td class="pay_tr pay-td-center"><c:out value="${var.商户号 }" /></td>
								<td class="pay_tr pay-td-center"><c:out value="${var.订单号 }" /></td>
								<td class="pay_tr pay-td-center"><c:out value="${var.订单类型 }" /></td>
								<td class="pay_tr pay-td-center"><c:out value="${var.唯一标识 }" /></td>
								<td class="pay_tr pay-td-center"><c:out value="${var.转出介质id}" /></td>
								<td class="pay_tr pay-td-center"><c:out value="${var.转出卡号 }" /></td>
								<td class="pay_tr pay-td-center"><c:out value="${var.转入介质id}" /></td>
								<td class="pay_tr pay-td-center"><c:out value="${var.转入卡号 }" /></td>
								<td class="pay_tr pay-td-center"><c:out value="${var.币种 }" /></td>
								<td class="pay_tr pay-td-center"><c:out value="${var.金额 }" /></td>
								<td class="pay_tr pay-td-center"><c:out value="${var.状态 }" /></td>
								<td class="pay_tr pay-td-center"><c:out value="${var.日期时间 }" /></td>
								<c:if test="${flag == 'check'}">
									<td class="pay_tr pay-td-center"><c:out value="${var.check结果的的确认时间 }" /></td>
								</c:if>
								<c:if test="${flag == 'error'}">
									<td class="pay_tr pay-td-center"><c:out value="${var.处理结果描述 }" /></td>
									<td class="pay_tr pay-td-center"><c:out value="${var.资金变动情况 }" /></td>
								</c:if>
							</tr>
						</c:if>
					</c:forEach>
				</c:if>
			</table>
		</div>
		</div>
		<!-- 表格数据 end -->
		<c:if test="${empty list}">
			 <!-- 空白页 end -->	
			 <div id="pay_blank" class="pay_blank" style="display:block;" >   
				<div style="width:400px; margin:0 auto 0">     
					<div id="pay_nodata" class="pay_nodata">         
						<div class="pay_nodata_desc">暂无日志信息</div>         
					</div> 
				</div>    
			</div>
			<!-- 空白页 end --> 
		</c:if>
		
<script>
	//返回EBC交易日志页面
	$("#infoBackBtn").click(function(){
		var date = $("#date").val();
		window.location = "<c:url value='/sys/transaction/ebclogs'/>?date="+date;
	});
</script>
</body>
</html>

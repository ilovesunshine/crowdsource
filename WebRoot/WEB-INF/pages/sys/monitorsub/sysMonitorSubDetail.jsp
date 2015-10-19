<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="page.title"/></title>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
<style>
	.jfpay-monitor-tr{
		height:100px;
	}
</style>
</head>
<body>
<div class="pay_cont_sys">
	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
    <div class="barTitle">
		<span>系统监控数据明细</span>
	</div>
	<div class="ui-table ui-border">
		<table class="table forview">
			<tbody>
				<tr class="jfpay-monitor-tr">
					<td class="inputLabelTd">日志</td>
					<td class="inputTd"><span class="text1"><c:out value='${monitor.errLog}'/></span></td>
				</tr>
				<tr class="jfpay-monitor-tr">
					<td class="inputLabelTd">行为审核</td>
					<td class="inputTd"><span class="text1"><c:out value='${monitor.errAdult}'/></span></td>
				</tr>
				<tr class="jfpay-monitor-tr">
					<td class="inputLabelTd">报文</td>
					<td class="inputTd"><span class="text1"><c:out value='${monitor.errNotifications}'/></span></td>
				</tr>
				<tr class="jfpay-monitor-tr">
					<td class="inputLabelTd">交易流水</td>
					<td class="inputTd"><span class="text1"><c:out value='${monitor.errTransaction}'/></span></td>
				</tr>
			</tbody>
			<tfoot class="footTd">
				<tr>
					<td class="inputTd" colspan="4">
            			<button id="back_button" class="button"  type="button" onclick="javascript:history.go(-1);"><fmt:message key='button.back'/></button>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
<!-- 引入尾文件-->
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>

<script type="text/javascript">
new biz.button({id:"#back_button", icons:{primary:'ui-icon-arrowthick-1-w'}});

	function getAccountType(s){
		switch(s){
			case -1:return "游客";
			case 0:return "个人用户";
			case 1:return "企业用户";
			case 2:return "运营管理员";
			case 3:return "系统管理员";
			default:return "未知";
		}
	}

	function getActResult(s){
		if(s==1){
			return "成功";
		}else if(s==0){
			return "失败";
		}
	}
</script>
</body>
</html>
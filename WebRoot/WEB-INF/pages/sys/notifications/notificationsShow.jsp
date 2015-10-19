<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>报文</title>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
<style>
body{
white-space:normal;
}
.text1{
	width:900px;
	display:block;
	word-break:break-all; /*支持IE，chrome，FF不支持*/
　　word-wrap:break-word;/*支持IE，chrome，FF*/
}
</style>

</head>
<body>
<div class="pay_cont_sys">
	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
    <div class="barTitle">
		<span>报文详情</span>
	</div>
	<div class="ui-table ui-border">
		<table class="table forview">
			<tbody>
				<tr>
					<td class="inputLabelTd">ID</td>
					<td class="inputTd"><c:out value='${notifications.ntId}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">接口名称</td>
					<td class="inputTd"><c:out value='${notifications.interfaceName}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">流水号</td>
					<td class="inputTd"><c:out value='${notifications.ordersn}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">订单号</td>
					<td class="inputTd"><c:out value='${notifications.dsorderid}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">请求报文</td>
					<td class="inputTd"><span class="text1"><c:out value='${notifications.reqMessage}'/></span></td>
				</tr>
				<tr>
					<td class="inputLabelTd">响应报文</td>
					<td class="inputTd"><span class="text1"><c:out value='${notifications.respMessage}'/></span></td>
				</tr>
				<tr>
					<td class="inputLabelTd">响应结果</td>
					<td class="inputTd"><script>fmtActResult("<c:out value='${notifications.state}'/>")</script></td>
				</tr>
				<tr>
					<td class="inputLabelTd">发送时间</td>
					<td class="inputTd" ><span id="sendTime"><c:out value='${notifications.sendTimeStr}'/></span></td>
				</tr>
				<tr>
					<td class="inputLabelTd">接收时间</td>
					<td class="inputTd" ><span id="recieveTime"><c:out value='${notifications.recieveTimeStr}'/></span></td>
				</tr>
				<tr>
					<td class="inputLabelTd">回调报文</td>
					<td class="inputTd"><span class="text1"><c:out value='${notifications.callbackMessage}'/></span></td>
				</tr>
				<tr>
					<td class="inputLabelTd">回调结果</td>
					<td class="inputTd"><c:out value='${notifications.callbackStateName}'/></td>
				</tr>
				<tr>
					<td class="inputLabelTd">回调时间</td>
					<td class="inputTd" ><span id="callbackTime"><c:out value='${notifications.callbackTimeStr}'/></span></td>
				</tr>
			</tbody>
			<tfoot class="footTd">
				<tr>
					<td class="inputTd" colspan="4" align="center">
            			<button id="back_button" class="button"  type="button" onclick="window.close()">关闭</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
<!-- 引入尾文件-->
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>

</body>
</html>
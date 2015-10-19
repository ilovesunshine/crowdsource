<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="payConfig.show.title"/></title>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
</head>
<body>
<div class="pay_cont_sys">
	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
    <div class="barTitle">
		<span><fmt:message key="payConfig.show.title"/></span>
	</div>
	<div class="ui-table ui-border">
		<table class="table forview">
			<tbody>
				<tr>
					<td class="inputLabelTd"><fmt:message key="payConfig.detailInfo.pkey"/></td>
					<td class="inputTd"><c:out value='${payConfig.pkey}'/></td>

					<td class="inputLabelTd"><fmt:message key="payConfig.detailInfo.pvalue"/></td>
					<td class="inputTd"><c:out value='${payConfig.pvalue}'/></td>

				</tr>
				<tr>
					<td class="inputLabelTd"><fmt:message key="payConfig.detailInfo.memo"/></td>
					<td class="inputTd"><c:out value='${payConfig.memo}'/></td>

					<td class="inputLabelTd"><fmt:message key="payConfig.detailInfo.createTime"/></td>
					<td class="inputTd"><c:out value='${payConfig.createTimeStr}'/></td>

				</tr>
				<tr>
					<td class="inputLabelTd"><fmt:message key="payConfig.detailInfo.createPersonId"/></td>
					<td class="inputTd"><c:out value='${payConfig.createPersonId}'/></td>

					<td class="inputLabelTd"><fmt:message key="payConfig.detailInfo.updateTime"/></td>
					<td class="inputTd"><c:out value='${payConfig.updateTimeStr}'/></td>

				</tr>
				<tr>
					<td class="inputLabelTd"><fmt:message key="payConfig.detailInfo.updatePersonId"/></td>
					<td class="inputTd" colspan="3"><c:out value='${payConfig.updatePersonId}'/></td>

				</tr>
			</tbody>
			<tfoot class="footTd">
				<tr>
					<td class="inputTd" colspan="4">
            			<button id="back_button" class="button"  type="button" onclick="window.location = '<c:url value="/sys/config/show"/>'"><fmt:message key='button.back'/></button>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</div>
<!-- 引入尾文件-->
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>

<script type="text/javascript">
</script>
</body>
</html>
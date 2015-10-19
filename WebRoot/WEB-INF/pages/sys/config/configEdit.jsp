<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="payConfig.form.title"/></title>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
</head>
<body>
<div class="pay_cont_sys">
	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
    <div class="barTitle">
		<span><fmt:message key="payConfig.form.title"/></span>
	</div>
	<form id="payConfigForm" action="<c:url value='/sys/config/update'/>"  method="post" >
		<div class="ui-table ui-border">
			<input type="hidden" id="edit_pkey" name="pkey" value="<c:out value='${payConfig.pkey}'/>"/>
	    <table class="table">
	    	<tbody>
				<tr>
					<td class="inputLabelTd"><fmt:message key="payConfig.form.pkey"/>：</td>
					<td class="inputTd">
					<c:out value='${payConfig.pkey}'/>
					</td>

					<td class="inputLabelTd"><fmt:message key="payConfig.form.pvalue"/>：</td>
					<td class="inputTd">
					<input id="pvalue" name="pvalue" type="text" class="text" value="<c:out value='${payConfig.pvalue}'/>"/>
					</td>

				</tr>
				<tr>
					<td class="inputLabelTd"><fmt:message key="payConfig.form.memo"/>：</td>
					<td class="inputTd" colspan="3">
					<textarea id="memo" name="memo"  class="textarea"><c:out value='${payConfig.memo}'/></textarea>
					</td>

				</tr>
			</tbody>
			<tfoot class="footTd">
				<tr>
					<td class="inputTd" colspan="4">
						<button id="submit_button" class="button" type="submit" onclick="update(this);"><fmt:message key='button.submit'/></button>&nbsp;&nbsp;
            			<button id="reset_button" class="button" type="reset"><fmt:message key='button.reset'/></button>&nbsp;&nbsp;
            			<button id="back_button" class="button" type="button" onclick="window.location = '<c:url value="/sys/config/show"/>'"><fmt:message key='button.back'/></button>
					</td>
				</tr>
			</tfoot>
		</table>
        </div>
	</form>
</div>
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
</body>
</html>

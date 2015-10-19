<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="../common.jsp" %>
<html>
<title>task</title>
<body>
	
	<div style="align:center;padding-left:250px;" id="addJobDiv">
		<table class="add_table">
			<tr>
				<td>Job名称</td>
				<td><c:out value="${job.jobName}"/></td>
			</tr>
			<tr>
				<td>Job分组</td>
				<td><c:out value="${job.jobGroup}"/></td>
			</tr>
			
			<tr>
				<td>Cron表达式</td>
				<td><c:out value="${job.cronExpression}"/></td>
			</tr>
			<tr>
				<td>Job是否启用</td>
				<td><c:out value="${job.jobIsInUseStr}"/></td>
			</tr>
			<tr>
				<td>Job描述</td>
				<td><c:out value="${job.description}"/></td>
			</tr>
			<tr>
				<td>是否允许并发执行</td>
				<td>
				<c:out value="${job.isConcurrentStr}"/>
				</td>
			</tr>
			<tr>
				<td>类全路径</td>
				<td><c:out value="${job.beanClass}"/></td>
			</tr>
			<tr>
				<td>Spring Id </td>
				<td><c:out value="${job.springId}"/></td>
			</tr>
			<tr>
				<td>方法名称 </td>
				<td><c:out value="${job.methodName}"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="button" onclick="cancel()" value="取消" />
				</td>
			</tr>
		</table>
	</div>
	<script>
		function cancel(){
			location.href="<c:url value='/task/taskList'/>";
		}
		
		function hideWaitMsg() {
			$('.datagrid-mask').remove();
			$('.datagrid-mask-msg').remove();
		}
	</script>
</body>
</html>





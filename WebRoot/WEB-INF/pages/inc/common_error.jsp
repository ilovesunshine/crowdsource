<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="author" content="CSI"/>
<script>
	function err_close() {
		$("#error_bar").css("display", "none");
	}
</script>
<style>
	#error_bar{
			display:block;
	}
</style>
<c:if test="${!empty message}">
	<div id="error_bar" class="add_bg">
		<div class="add_icon"><c:out value='${message}'/>
			<a href="javascript:err_close();" style="float:right;margin-right:30px;">关闭</a>
		</div>	
	</div>
</c:if>
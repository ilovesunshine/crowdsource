<%@page import="com.csi.jointforce.common.util.PageUtil"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
 
<div id="footerbox">
	<div id="footerd">
		<div class="footer">
			<div class="floatleft">
			    <a target="_blank" href="#">关于我们</a>
		        <a target="_blank" href="#">平台客服</a>
				<a target="_blank" href="#">规则中心</a>
				<a target="_blank" href="#">问题中心</a>
			</div>
			<div class="floatright">
				<span>Copyright &copy; 2015-<em id="nowyear">5000</em> 蚂蚁众服团队</span>
				<span class="pdlr4"></span>
				<span>京ICP备015007号</span>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var contextPath = "<%=request.getContextPath() %>";

function nowYear(){
	var year = new Date().getFullYear();
	$("#nowyear").html(year);
}

function footResize(){
	var h = $(window).height();
	var minh = h-54;
	$("body").css("min-height",minh);
}
function footClear(){
	var foot = $("#footerbox");
	var clear = $("<div>").addClass("clear");
	foot.prev("div").append(clear).css("padding-bottom","54px");
}
$(function(){
	footClear();
	footResize();
	nowYear();
})
window.onresize=function(){
	footResize();
};
window.onload = function(){
	$("input").attr("autocomplete","off").attr("disableautocomplete","")
}
</script>

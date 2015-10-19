<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<title>blank</title>
	<style type="text/css">
	.payWaitRemind{
			font-weight:bold;
			font-family: "Microsoft Yahei","Helvetica Neue",Helvetica,Arial,Tahoma,sans-serif;
			font-size:18px;
			line-height:64px;
			text-indent: 70px;
			margin-top:25px;
			margin-left:20px;
			background: url("<c:url value='/images/wait.gif' />") no-repeat scroll left center rgba(0, 0, 0, 0);
		}
		.payWait{
			height:110px;
			width:350px;
			position: fixed;
			top:50%;
			left:50%;
			background-color:#fff;
			margin-left:-175px;
			margin-top:-55px;
			border-radius:10px;
			display:none;
			z-index: 9999;
		}
		
		.bottom-screen{
			position: fixed;
			top:0px;
			bottom:0px;
			left:0px;
			right:0px;
			background-color: #000000;
			opacity:0.5;
			filter:alpha(opacity=50);
			-moz-opacity:0.5;
			-khtml-opacity: 0.5;
			display:none;
			z-index: 9998;
		}
	</style>
</head>
<body>
<div id="handleShade" class="bottom-screen">
</div>
<div id="payWaitRemind"  class="payWait" >
	<div class="payWaitRemind">正在处理中，请稍后...</div>
</div>
<script type="text/javascript">
	function showShade(){
		$('#handleShade').show();
		$('#payWaitRemind').show();
	}

	function hideShade(){
		$('#handleShade').hide();
		$('#payWaitRemind').hide();
	}
</script>
</body>
</html>

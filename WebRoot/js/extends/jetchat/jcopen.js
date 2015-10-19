$(function() {
	var jcweb = "/jetchatweb/";
//	$(document).on(
//			"click",
//			".jc-open",
//			function(event) {
	$(".jc-open-message,.jc-open-img").bind("click",function(event) {
				if(navigator.userAgent.indexOf("MSIE")>0){
					window.event.cancelBubble = true;
				}else{
					event.stopPropagation();
				}
				
				var element = event.currentTarget;
				var op = "chat";
				//var params = element.getAttribute("data-jid");
				var params;
				 /*通过jfId获取usersid*/
				 $.ajax({
					 url: jfuserurl + "/common/userid/" + element.getAttribute("data-jid"),
					 type:"post",
					 async:false,
					 dataType:"json",
					 success: function(date){
						 params=date.userid;
					}
				 });
				 	var time = Number(new Date());
					var k = 0;
					for (var i = 0; i < op.length; i++) {
						k = (((k << 3) ^ op.charCodeAt(i)) ^ (k >>> 29)) | 0;
					}
					k ^= time | 0;
					k ^= (time >>> 32) | 0;
					k ^= 0x97adc8ea;
					for (var i = 0; i < params.length; i++) {
						k = (((k << 3) ^ params.charCodeAt(i)) ^ (k >>> 29)) | 0;
					}
					var w=window.open(jcweb + "#jcwrpc:" + op + ":" + time + ":" + k
							+ ":" + params, "JetchatWeb");
					if(w){
						w.focus();
					}
			});
});
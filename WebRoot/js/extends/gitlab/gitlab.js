//====================================================================
//页面全局变量名
/*var gitlabId;//仓库id
var repository;//仓库信息
*///====================================================================
//初始化仓库信息		
/*function initConfig() {
	gitlabId = $("#gitlabId").val();
	if(gitlabId !="")
	$.ajax({
		url:contextPath+"/pm/repo/info",
		data:{"gitlabId":gitlabId},
		async:true,
		success: function(data, textStatus, jqXHR){		
			repository = data;
		}
	});	
}*/
//检查浏览器是否安装了flash		
function ifHaveFlash() {
	var isIE = !-[1,];
	if(isIE){
	    try{
	        var ieswf = new ActiveXObject('ShockwaveFlash.ShockwaveFlash');
	        return true;
	    }
	    catch(e){
	        return false;
	    }
	}
	else {
		var swf=navigator.plugins["Shockwave Flash"];
		if(swf){
			return true;
			}else{
				return false;
				} 
	}
}
//====================================================================
/*//主方法
$(function() {
	initConfig();
});*/
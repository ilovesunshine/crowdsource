/**
 * ------------------------------------------------
 * 版本升级提醒模块
 * @version  1.0
 * @author   zhangwei
 * ------------------------------------------------
 */
JFGlobals.version = (function() {	
	
	var url;
	var pageCode;
	var index=0;
	var versionNo;
	var hasNew;
	/**
	 * 初始化
	 * @param  {[type]} _pageCode [description]
	 * @return {[type]}           [description]
	 */
	function _init(_pageCode,_url){
		url=_url;
		pageCode=_pageCode;
		//初始化参数
		//获取升级更新的内容
		//创建显示DIV
		_showVersionInfo();
		$(".version-box .close").click(function() {
			_closeVersionInfo();
		});
	}

	/**
	 * 显示升级的内容
	 * @return {[type]} [description]
	 */
	function _showVersionInfo(){
		var versioninfo=_getVersionInfo();
		//cookie中保存的格式：version@pageCode@versionNo
		var isClose=getCookie("version@"+pageCode+"@"+versionNo);
		if(hasNew==false  || isClose=="1"){
			return ;
		}
		if($("body").find(".version-box").length==0){
			$("body").append(versioninfo);
				$('#versionCarousel').carousel({interval: 24*3600000});
			$('.vpre').click(function(event) {
				pre();
			});
			$('.vnext').click(function(event) {
				next();
			});
			$(".usernew").click(function(event) {
				userNew();		
			});
		}
		 
	}
	 
	 /**
	  * 显示上一条功能提醒
	  * @return {[type]} [description]
	  */
	function pre(){
		$('#versionCarousel').carousel("prev");
	}
	/**
	 * 显示下一天功能提醒
	 * @return {Function} [description]
	 */
	function next(){
		$('#versionCarousel').carousel("next");
	}

	/**
	 * 体验新功能
	 * @return {[type]} [description]
	 */
	function userNew(){
		_closeVersionInfo();
	}

	 /**
	  * 关闭升级信息提示
	  * @return {[type]} [description]
	  */
	function _closeVersionInfo(){
		setCookie("version@"+pageCode+"@"+versionNo,"1");
		$(".version-box").hide();
	}
	function _show(){
		$(".version-box").show();
	}

	function _hide(){
		$(".version-box").hide();
	}
	/**
	 * 获取升级版本数据
	 * @return {[type]} [description]
	 */
	function  _getVersionInfo(){
		var versionItem="";
	    var carouselData="";
		var _versionInfo="<div class='version-box'> <div style='position: absolute; width: 100%; height: 100%; background: none repeat scroll 0% 0% rgb(0, 0, 0); z-index: 1; opacity: 0.5;'></div><div class='close'></div>";
		$.ajax({
   			type: "POST",
   			url: url+"/version/info",
   			async: false,
   			data: "pageCode="+pageCode,
		   	success: function(data){
		   		if(data.records==0){
		   			hasNew=false;
		   		}else{
		   			hasNew=true;
		   		}
		   		for(var i=0;i<data.records;i++){
		   			if(i==0){
		   				carouselData+="<li data-target='#versionCarousel' data-slide-to='0' class='active'></li>";
		   				versionItem+="<div class='item active'><i class='info_cion'></i><span>"+data.rows[i].upgradeContent+"</span>";
		   			}else{
		   				carouselData+="<li data-target='#versionCarousel' data-slide-to='"+i+"'></li>";
		   				versionItem+="<div class='item'><i class='info_cion'></i><span>"+data.rows[i].upgradeContent+"</span>";
		   			}
		   			if(data.records==1){
		   				versionItem+="<button type='button' class='usernew kbtn kbtn26 kbtn_orange' style='margin-left:100px'>马上体验</button>";
		   			}else{
		   				if(i==0){
		   					versionItem+="<button type='button' class='vnext kbtn kbtn26 kbtn_orange' style='margin-left:100px'>下一条</button>";
		   				}else if(i<data.records-1 && i>0){
		   					versionItem+="<button type='button' class='vnext kbtn kbtn26 kbtn_orange' style='margin-left:15px'>下一条</button>";
		   				}else{
		   					versionItem+="<button type='button' class='usernew kbtn kbtn26 kbtn_orange' style='margin-left:15px'>马上体验</button>";
		   				}
		   			}
		   			versionItem+="</div>";
		   			versionNo=data.rows[i].version;
		   		}
		    }
		});
		_versionInfo+="<div class='version-info'>";
		_versionInfo+="<div id='versionCarousel' class='carousel' data-interval='false'>";
		_versionInfo+="<div class='carousel-inner' id='version-carousel-inner'>"+versionItem+"</div>";
		_versionInfo+="<ol class='carousel-indicators' id='version-carousel-indicators'>"+carouselData+"</ol>";
	    _versionInfo+="</div></div></div>";

		return _versionInfo;
	}


	/**
	 * 设置cookie
	 */
	function setCookie(name,value){//两个参数，一个是cookie的名子，一个是值
		var Days = 1000; 
		var exp  = new Date();    //new Date("December 31, 9998");
		exp.setTime(exp.getTime() + Days*24*60*60*1000);
		document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
	}
	
	/**
	 * 获取cookie数据
	 */
	function getCookie(name){//取cookies函数
		var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
		if(arr != null) return unescape(arr[2]); return null;
	}
	
	/**
	 * 删除cookie
	 */
	function delCookie(name){//删除cookie
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval=getCookie(name);
		if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();
	}
	
	function version(){
		this.init=_init;
		this.hide=_hide;
		this.show=_show;
    }
    return version; // 返回构造函数公共属性、方法
 
})();
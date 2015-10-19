<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>
<fmt:message key="page.title"/>
</title>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${csssuffix}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${csssuffix}'/>" media="screen, projection"/> 
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/themeDS.${csssuffix}'/>"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/common.${csssuffix}'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/menu.${csssuffix}'/>"/>	

</head>

<body>
<div class="pay_cont_sys">
	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
	<div class="barTitle">
		EBC交易日志&nbsp;|&nbsp;
		<span>
			<a href="<c:url value='/sys/statement/withdraw'/>">提现流水对账</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/statement/recharge'/>">充值流水对账</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/statement/cards'/>">绑定银行卡对账</a>&nbsp;|&nbsp;
			<a href="<c:url value='/sys/statement/showfails'/>">交易流水</a> 
		</span>
	</div>
	<div id="hiddenArea">
		<input id="hiddenYear" name="hiddenYear" type="hidden" value=""/>
		<input id="hiddenMonth" name="hiddenMonth" type="hidden" value=""/>
		<input id="date" name="date" type="hidden" value="<c:out value='${date }'/>">
		<input id="startYear" name="startYear" type="hidden" value="<c:out value='${startYear }'/>">
	</div>
	<div style="border: 1px solid #E5E5E5; margin: 5px 0 5px 0;padding:5px;">
		<div style="clear:both; margin: 10px;">
			<span style="clear:both; margin: 10px;font-size:15px;"> 年份：</span> <span id="years"></span>
			<br><br>
			<span style="clear:both; margin: 10px;font-size:15px;"> 月份：</span> <span id="months"></span>
		</div>
	</div>
	<!-- 表格数据 start -->
	<div class="pay_opt_main">
		<table id="ebc-log-table" style="table-layout:fixed" class="pay_opt_table">
     	</table>
	</div>
	<!-- 表格数据 end -->
</div>
<!-- 引入尾文件 start-->
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<!-- 引入尾文件 end-->
<script type="text/javascript">

window.onload = function(){
	init();
};	 		

//初始化数据
function init(){
	//初始化年份
	$("#years").empty();
	$("#ebc-log-table").hide();
	var yearHtml="";
	var year = new Date().getFullYear(); 
	var month = new Date().getMonth() + 1; 
	var date = $("#date").val();
	var startYear = $("#startYear").val();
	if(date != null && date != ''){
		year = date.split("-")[0];
		month = date.split("-")[1];
	}
	for(var y=startYear; y<=year; y++){
		if(y != year){
			yearHtml += "<a href=\"#\" id=\"queryYear"+y+"\" name=\"queryYear"+y+"\" onclick=\"query("+y+",'');\">"+y+"</a> &nbsp;&nbsp;&nbsp;";
		}else{
			$("#hiddenYear").val(y);
			yearHtml += "<a href=\"#\" class=\"but-year-weight\" id=\"queryYear"+y+"\" name=\"queryYear"+y+"\" onclick=\"query("+y+",'');\">"+y+"</a> &nbsp;&nbsp;&nbsp;";
		}
	}
	$("#years").append(yearHtml);
	//初始化月份 
	$("#months").empty();
	var monthHtml="";
	for(var m=1; m<=12; m++){
		if(m != month){
			monthHtml += "<input id=\"queryMonth"+m+"\" name=\"queryMonth"+m+"\" type=\"button\" class=\"but-month\" style=\"width:30px;\" value="+m+" onclick=\"query('',"+m+");\"/>&nbsp;&nbsp;&nbsp;";
		}else{
			$("#hiddenMonth").val(m);
			monthHtml += "<input id=\"queryMonth"+m+"\" name=\"queryMonth"+m+"\" type=\"button\" class=\"but-month-weight\" style=\"width:30px;font-weight:bold;\" value="+m+" onclick=\"query('',"+m+");\"/>&nbsp;&nbsp;&nbsp;";
		}
	}
	$("#months").append(monthHtml);
	//初始化查询数据
	query(year, month);
}

//按年月查询 
function query(year, month){
	if(year == ''){//点击月份查询按钮
		year = $("#hiddenYear").val();
		var m = $("#hiddenMonth").val();
		$("#queryMonth" + m).removeClass('but-month-weight');
		$("#queryMonth" + m).addClass('but-month');
		$("#hiddenMonth").val(month);//记忆月份
		$("#queryMonth" + month).removeClass('but-month');
		$("#queryMonth" + month).addClass('but-month-weight');
	}
	if(month == ''){//点击年份查询按钮
		month = $("#hiddenMonth").val();
		var y = $("#hiddenYear").val();
		$("#queryYear" + y).removeClass('but-year-weight');
		$("#queryYear" + y).addClass('but-year');
		$("#hiddenYear").val(year);//记忆年份
		$("#queryYear" + year).removeClass('but-year');
		$("#queryYear" + year).addClass('but-year-weight');
	}
	$.ajax({
		url : "<c:url value='/sys/statement/ebclogs/list'/>",
		data : {
			"year" : year, 
			"month" : month
		},
		cache : false,
		async: true,
		success : function(data) {
			weave(year, month, data);
		},
		error : function() {
               alert("查询出错！"); 
        }
    });
}

//组装页面
function weave(year, month, data){
	$("#ebc-log-table").show();
	var now = new Date();
	var nowdate = now.getFullYear() + bingo(now.getMonth()+1) + bingo(now.getDate());
	//根据年月获取天数
	var days = DaysOfMonth(year, month);
	if(data != null){ 
		$("#ebc-log-table").empty();
		var dbillTableHtml = ""+
			"<thead><tr>" +      
			"<th class=\"hj_tab_topbj pay-td-center\">No.</th>" +				
			"<th class=\"hj_tab_topbj pay-td-center\">日期</th>" +
			"<th class=\"hj_tab_topbj pay-td-left  \">OSS-Text</th>" +
			"<th class=\"hj_tab_topbj pay-td-left  \">OSS-Check</th>" +
			"<th class=\"hj_tab_topbj pay-td-left  \">OSS-Error</th>" +
			"<th class=\"hj_tab_topbj pay-td-center\">操作</th>" +
			"</tr></thead>";
		var map = getMap(); 
		for(var i = 0; i < data.length; i++){
			var date = Date.formate("yyyy-MM-dd",new Date(data[i].date.time));
			var key = date.split("-")[2];
			if(data[i].commonTradeLog != "" || data[i].checkTradeLog  != "" ||
					data[i].errorTradeLog != ""){
				map.put(key,data[i]);
			}
		}
		for(var i = 0; i < days; i++){
			var no = bingo(i+1);
			if(map.get(no) != null){
				var obj = map.get(no);
				var check = '--';
				if(obj.checkTradeLog != null){
					check = obj.checkTradeLog.replace(/\//g,'!');
				}
				var error = '--';
				if(obj.errorTradeLog != null){
					error = obj.errorTradeLog.replace(/\//g,'!');
				}
				var common = '--';
				if(obj.commonTradeLog != null){
					common = obj.commonTradeLog.replace(/\//g,'!');
				}
				dbillTableHtml += "<tbody><tr>" + 
				"<td class=\"pay_tr pay-td-center\">" + no + "</td>" +
				"<td class=\"pay_tr pay-td-center\">" + Date.formate("yyyy-MM-dd",new Date(obj.date.time)) + "</td>" +
				"<td class=\"pay_tr pay-td-left  \">" +
					"<a href=\"#\" id=\"\" onclick=\"obtain('" + common + "');\">" + common + "</a>"+
				"</td>" + 
				"<td class=\"pay_tr pay-td-left  \">" +
					"<a href=\"#\" id=\"\" onclick=\"obtain('" + check + "');\">" + check + "</a>"+
				"</td>" + 
				"<td class=\"pay_tr pay-td-left  \">" +
					"<a href=\"#\" id=\"\" onclick=\"obtain('" + error + "');\">" + error + "</a>"+
				"</td>" ;
				if(obj.STATE == '01'){
					dbillTableHtml += 
					"<td class=\"pay_tr pay-td-center\">" +
						"<a href=\"<c:url value='/sys/statement/ebclogs/download'/>?ope=0&date="+obj.date+"\" id=\"\" >打包下载</a>&nbsp;|&nbsp;"+
						"<a href=\"<c:url value='/sys/statement/ebclogs/download'/>?ope=1&date="+obj.date+"\" id=\"\" >Excel</a>&nbsp;|&nbsp;"+
						"<a href=\"<c:url value='/sys/statement/ebclogs/download'/>?ope=2&date="+obj.date+"\" id=\"\" >FTP下载 </a>"+
					"</td>";
				}else{
					dbillTableHtml += 
					"<td class=\"pay_tr pay-td-center\">" +
						"<span  id=\"\" onclick=\"\">打包下载</span>&nbsp;|&nbsp;"+
						"<span  id=\"\" onclick=\"\">Excel</span>&nbsp;|&nbsp;"+
						"<a href=\"<c:url value='/sys/statement/ebclogs/download'/>?ope=2&date="+obj.date+"\" id=\"\" >FTP下载 </a>"+
					"</td>";	
				}
				dbillTableHtml += "</tr>";
			}else{
				var currentdate = year + bingo(month) + no;
				if(currentdate <= nowdate){
					dbillTableHtml +=
						"<td class=\"pay_tr pay-td-center\">" + no + "</td>"+
						"<td class=\"pay_tr pay-td-center\">" + (year + "-" + bingo(month)+ "-" + no) + "</td>"+
						"<td class=\"pay_tr pay-td-center\"> -- </td>"+
						"<td class=\"pay_tr pay-td-center\"> -- </td>"+
						"<td class=\"pay_tr pay-td-center\"> -- </td>";
					dbillTableHtml += 
						"<td class=\"pay_tr pay-td-center\">" +
							"<span  id=\"\" onclick=\"\">打包下载</span>&nbsp;|&nbsp;"+
							"<span  id=\"\" onclick=\"\">Excel</span>&nbsp;|&nbsp;"+
							"<a href=\"/jfpay/sys/transaction/ebclogdownload?date="+(year + "-" + bingo(month)+ "-" + no)+"\" id=\"\" >FTP下载 </a>"+
						"</td>";	
					dbillTableHtml += "</tr>";
				}else{
					dbillTableHtml +=
						"<td class=\"pay_tr pay-td-center\">" + no + "</td>"+
						"<td class=\"pay_tr pay-td-center\">" + (year + "-" + bingo(month)+ "-" + no) + "</td>"+
						"<td class=\"pay_tr pay-td-center\"> -- </td>"+
						"<td class=\"pay_tr pay-td-center\"> -- </td>"+
						"<td class=\"pay_tr pay-td-center\"> -- </td>";
					dbillTableHtml += 
						"<td class=\"pay_tr pay-td-center\">" +
							"<span  id=\"\" onclick=\"\">打包下载</span>&nbsp;|&nbsp;"+
							"<span  id=\"\" onclick=\"\">Excel</span>&nbsp;|&nbsp;"+
							"<span  id=\"\" onclick=\"\">FTP下载 </span>"+
						"</td>";	
					dbillTableHtml += "</tr>";
				}
			}
		}
		dbillTableHtml += "</tbody>";
		$("#ebc-log-table").append(dbillTableHtml);
	}else{
	    $("#ebc-log-table").empty();
	    return;
	}
}

//获取日志详细信息
function obtain(logName){
	window.location = "<c:url value='/sys/statement/ebclogs/showdetail'/>?logName="+logName;
}

//操作选择器
function operator(ope,date){
	window.location = "<c:url value='/sys/transaction/ebctradelogoperator'/>?ope="+ope+"&date="+date; 
}

//根据年份和月份获取当月天数 
function DaysOfMonth(Year, Month){
    var d = new Date(Year,Month,0);
    return d.getDate();
}

//取短补长
function bingo(no){
	var str = no.toString();
	if(len(str)==1){
		str = "0" + str;
	}
	return str;
}

//获取字符串长度
function len(s) { 
	var length = 0; 
	var a = s.split(""); 
	for (var i=0;i<a.length;i++) { 
		if (a[i].charCodeAt(0)<299) { 
			length++; 
		} else { 
			length += 2; 
		} 
	} 
	return length; 
}

//定义简单Map  
function getMap() {//初始化map_,给map_对象增加方法，使map_像Map
     var map_ = new Object();    
     map_.put = function(key, value) {    
         map_[key+'_'] = value;    
     };    
     map_.get = function(key) {    
         return map_[key+'_'];    
     };    
     map_.remove = function(key) {    
         delete map_[key+'_'];    
     };    
     map_.keyset = function() {    
         var ret = "";    
         for(var p in map_) {    
             if(typeof p == 'string' && p.substring(p.length-1) == "_") {    
                 ret += ",";    
                 ret += p.substring(0,p.length-1);    
             }    
         }    
         if(ret == "") {    
             return ret.split(",");    
         } else {    
             return ret.substring(1).split(",");    
         }    
     };    
     return map_;    
}
</script>
</body>
</html>
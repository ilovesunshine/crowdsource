<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="page.title"/></title>
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
		绑定银行卡对账&nbsp;|&nbsp;
		<span>
			<a href="<c:url value='/sys/statement/recharge'/>">充值流水对账</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/statement/withdraw'/>">提现流水对账</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/statement/showfails'/>">交易流水</a>&nbsp;|&nbsp;  
			<a href="<c:url value='/sys/statement/ebclogs'/>">EBC交易日志</a> 
		</span>
	</div>
	<form id="queryForm"  class="bizform">
		<div class="row"> 
			<div class="query-cell">
				<div class="cell" style="padding-left:8px;">
					账户名称：<input id="userName" name="userName" type="text" />
				</div>
				<div class="cell" style="padding-left:8px;">
					银行：<input id="bankName" name="bankName" type="text" />
				</div>
				<div class="cell" style="padding-left:8px;">
					卡号：<input id="cardNo" name="cardNo" type="text" />
				</div>
				<div class="cell" style="padding-left:8px;">
					<input id="startDate" name="startDate" type="text" class="Wdate"
							placeHolder="开始时间" value="<c:out value='${startDate}'/>" 
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}'})" /> - 
					<input id="endDate" name="endDate" type="text" class="Wdate" 
							placeHolder="截止时间" value="<c:out value='${endDate}'/>" 
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}'})" />
				</div>
				<div class="cell" style="padding-left:8px;">
					<button id="query_button" class="class_queryform_first info-btn" type="button" onclick="">查询</button>
				</div>
			</div>
		</div>

		<table id="cardbindTable" style="table-layout:fixed" class="display">
			<thead>
				<tr>
					<th width="15px;"><input type="checkbox" id="checkedAll" onClick="selectedAll('queryForm',this.checked)"/></th>
					<th width="40px;">账户ID</th>
					<th width="50px;">账户名称</th>
					<th width="50px;">账户类别</th>
					<th width="30px;">银行代码</th>
					<th width="20px;">银行</th>
					<th width="50px;">卡号</th>
					<th width="20px;">默认</th>
					<th width="20px;">钱包介质ID</th>
					<th width="20px;">响应码</th>
					<th width="20px;">回调响应码</th>
					<th width="40px;">创建时间</th>
					<th width="40px;">更新时间</th>
				</tr>
			</thead>
		</table>
	</form>
</div>
<!-- 引入尾文件-->
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<script type="text/javascript">
$(document).ready(function(){
	var sourceUrl = "<c:url value='/sys/statement/cardbind/list'/>";
	var imgUrl = "<c:url value='/styles/images/loading.gif'/>";
    var sortColumns= "BC_ID,ACCOUNT_ID,ACCOUNT_NAME,EXT_USER_TYPE,"+
    "BANK_CODE,BANK_NAME,CARD_NO,IS_DEFAULT,EXT_MEDIUM_NO,EXT_RESULT_CODE,EXT_RESULT_CODE_CALLBACK,CREATE_TIME,UPDATE_TIME";
    var sort = [[ 11, "desc" ],[ 12, "desc" ]];
    function renderCheckBox(data, type, row, meta){
    	return '<input name="ids" type="checkbox" value="'+data+
		'" id="someCheckbox"+'+data+' onchange="changCheckboxChecked(\'ids\',\'queryForm\',\'checkedAll\');"'
		+' name="someCheckbox" />';
	}
	function fmtActResult(data, type, row, meta){
		if(data=='0'){
			return "否";
		}else if(data=='1'){
			return "默认";
		}else{
			return "未知";
		}
	}
	function fmtResultCode(data, type, row, meta){
		if(data == null || data == ""){
			return "";
		}
		if(data=='00'){
			return "00-成功";
		}else if(data=='34'){
			return "34-处理中";
		}else{
			var text = "<span onclick=showText('12','"+row.cardNo+"','"+row.extMediumNo+"','"+row.extErrtext+"');>" + data + "-银行处理失败 </span>";
			text += "<span style='display:none' >\n"+row.extErrtext+"</span>";
			return text;
		}
	}
	function fmtResultCodeCallback(data, type, row, meta){
		if(data == null || data == ""){
			return "";
		}
		if(data=='00'){
			return "00-成功";
		}else if(data=='34'){
			return "34-处理中";
		}else{
			var text = "<span onclick=showText('17','"+row.cardNo+"','"+row.extMediumNo+"','"+row.extErrtextCallback+"');>" + data + "-银行处理失败 </span>";
			text += "<span style='display:none' >\n"+row.extErrtextCallback+"</span>";
			return text;
		}
	}
	function fmtTime(data, type, row){
		if(data){
			return Date.formate("yyyy-MM-dd hh:mm:ss",new Date(data.time)); 
		}
		return data;
	}
	function acTypeFmt (data, options, rowObject) {
		var string = '未知';
		if(data == '0'){
			string = "个人";
		}else if(data == '1'){
			string = "企业";
		}else if(data == '3'){
			string = "JF平台";
		}
		return string;
	}

	var aocolumns = [ 
		{"mDataProp" : "bcId","bSortable": false}, 
		{"mDataProp" : "accountId"},
		{"mDataProp" : "userName","bSortable": false},
		{"mDataProp" : "extUserType"},
		{"mDataProp" : "bankCode"},
		{"mDataProp" : "bankName"},
		{"mDataProp" : "cardNo"},
		{"mDataProp" : "isDefault"},
		{"mDataProp" : "extMediumNo"},
		{"mDataProp" : "extResultCode"},
		{"mDataProp" : "extResultCodeCallback"},
		{"mDataProp" : "createTime",
			render : function(data,type,row){
				return fmtTime(data, type, row);
			}	
		},
		{"mDataProp" : "updateTime",
			render : function(data,type,row){
				return fmtTime(data, type, row);
			}		
		}
	]; 
	var columnDefs = [ 
		{"aTargets": [0], "render": renderCheckBox} ,
		{"aTargets": [2], "render": convertname},
		{"aTargets": [3], "render": acTypeFmt},
		{"aTargets": [7], "render": fmtActResult},
		{"aTargets": [9], "render": fmtResultCode},
		{"aTargets": [10], "render": fmtResultCodeCallback} 
	];

	initDataTable("#cardbindTable",aocolumns,columnDefs,sortColumns,sourceUrl,imgUrl,sort);
});
	function getQueryCondition(){
        var obj = {};
		//addAttrToObject(obj,"interfaceName");
		//addAttrToObject(obj,"reqMessage");
		//addAttrToObject(obj,"respMessage");
		//addAttrToObject(obj,"state");
		return obj;
    }

	function showText(messageType,cardNo,mediumNo,text){
		if(text == null || text == ""){
			text = matchingMessage(messageType,cardNo,mediumNo);
		}else{
			text = matchingDetail(text);
			new biz.alert({
				type : "alert",//信息提示类型，可选alert、confirm、prompt
				message : text,//提示信息
				title : "失败信息" //提示框标题
			});
		}
	}

	//匹配报文
	function matchingMessage(messageType,cardNo,mediumNo){
	    var url = "<c:url value='/sys/transaction/matchingMessage'/>";
	    $.post(url,{messageType:messageType,cardNo:cardNo,mediumNo:mediumNo}, function(data) {
	    	var text = data.message;
	    	if(text == null || text == ""){
	    		text = "未匹配到相应的报文信息";
			}else{
				text = matchingDetail(text);
			}
	    	new biz.alert({
				type : "alert",//信息提示类型，可选alert、confirm、prompt
				message : text,//提示信息
				title : "失败信息" //提示框标题
			});
	    }, "json");
	}

	//匹配细节
	function matchingDetail(message){
		var text = message;
		if(message.indexOf("账号解析失败")>=0){
			text = "失败原因：账号解析失败";
		}else if(message.indexOf("账号不存在")>=0){
			text = "失败原因：账号不存在";
		}else if(message.indexOf("请求信息不全")>=0){
			text = "失败原因：请求信息不全";
		}else if(message.indexOf("账户类型非法")>=0){
			text = "失败原因：账户类型非法";
		}else if(message.indexOf("账号不合法")>=0){
			text = "失败原因：账号不合法";
		}else if(message.indexOf("业务处理失败")>=0){
			text = "失败原因：业务处理失败";
		}else if(message.indexOf("账号、户名不符")>=0){
			text = "失败原因：账号与户名不符";
		}else if(message.indexOf("请重新添加")>=0){
			text = "失败原因：系统忙，请稍后再试";
		}else if(message.indexOf("银行验证失败")>=0){
			text = "失败原因：银行验证失败";
		}else if(message.indexOf("添加银行卡姓名与钱包姓名不一致")>=0){
			text = "失败原因：持卡人名称与账户名称不一致";
		}else if(message.indexOf("银行卡未认证")>=0){
			text = "失败原因：银行卡未认证";
		}
		return text;
	}

	function convertname(data, options, rowObject){
		if(data == null || data==""){
			return rowObject.extUsername + "<span style='color:red;'>(由密文解析)</span>";
		}
		return data;
	}

	function addAttrToObject(obj,name){
		element = document.getElementById(name);
		var notInputValue="";
		if(element!=null){
			if(element.value==null||element.value == ""){
				if($(element).attr("uiType")!=null){
					if($(element).attr("uiType")=="checkbox"){
						var tempCheckBoxObj = $(element).find(".checkbox");
						if(tempCheckBoxObj!=null && tempCheckBoxObj.length>0){
							tempCheckBoxObj.each(function(){
								if(this.checked) {
									notInputValue  += this.value+",";
								}
							  });
							if(notInputValue!=null && notInputValue.indexOf(",")!=-1){
								notInputValue = notInputValue.substring(0,notInputValue.length-1);
							}
					
						}
					}else if($(element).attr("uiType")=="radio"){
						var tempRadioObj = $(element).find(".radio");
						if(tempRadioObj!=null && tempRadioObj.length>0){
							  tempRadioObj.each(function(){
								if(this.checked) {
									notInputValue  = this.value;
									return;
								}
							  });
						}		  
					}
					if(notInputValue == null || notInputValue == "" ){
						  return;
					}
				}else{
                   return;
				}
			}
		}else{
			return;
		}

		obj = obj || {}; 
		var value = element.value!=null?element.value:notInputValue;
		if(name=='reqMessage' || name=='respMessage' || name=='interfaceName'){
			value = "%"+value+"%";
		}
		obj[name] = value;
	}

	//导出Excel
	function excelExport(){
		roNotificationsGrid.excelExport(getQueryCondition());
	}
	
	function add(){
		window.location="sys/audit/add";
	}
	
	function edit(rowid){
		rowid = listGrid.getGridParam("selarrrow");
		if(rowid == null || rowid.length == 0){
			showInfo("<fmt:message key='grid.edit.chooseColAlert'/>",3000);
			return ;
		}else if(rowid.length > 1){
			showInfo("<fmt:message key='grid.edit.onlyOne'/>",3000);
			return ;
		}
		var id = listGrid.getCell(rowid[0],"actId");
		window.location="sys/audit/edit/" + id;
	}

	function view(){
		var rowid = listGrid.getGridParam("selarrrow");
		if(rowid == null || rowid.length == 0){
			showInfo("<fmt:message key='grid.view.chooseColAlert'/>",3000);
			return ;
		}else if(rowid.length > 1){
			showInfo("<fmt:message key='grid.view.onlyOne'/>",3000);
			return ;
		}
		var id = listGrid.getCell(rowid[0],"actId");
		window.location="sys/audit/show?id=" + id;
	}

	function batchDelete(rowid){
		var rowid = listGrid.getGridParam('selarrrow');
		if(rowid == null || rowid.length == 0){
			showInfo("<fmt:message key='grid.delete.chooseColAlert'/>",3000);
			return ;
		}
		new biz.alert({type:"confirm",message:I18N.msg_del_confirm,
			title:I18N.promp,callback:function(result){
	    		if (!result) {
	    			return;
	    		}
				var ids="";
				for(var i=0; i < rowid.length;i++){
					var id = listGrid.getCell(rowid[i],'actId');
					if(i>0){
						ids +=',';
					}
					ids += id;
				}
				window.location="sys/audit/delete/" + ids;
			}
		});
	}
	
</script>
</body>
</html>
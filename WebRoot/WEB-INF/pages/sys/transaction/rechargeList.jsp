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
		充值流水对账</a>&nbsp;|&nbsp;
		<span>
			<a href="<c:url value='/sys/statement/withdraw'/>">提现流水对账</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/statement/cards'/>">绑定银行卡对账</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/statement/showfails'/>">交易流水</a>&nbsp;|&nbsp;  
			<a href="<c:url value='/sys/statement/ebclogs'/>">EBC交易日志</a>  
		</span>
	</div>
<div id="editDialog" class="container-fluid" style="display:none">
	<form id="rechargeFormEdit"  class="bizform">
		<input type="hidden" name="rechargeId" id="rechargeId" value="" />
		<div class="ui-table">
			<table class="table">
				<tr>
					<td class="inputLabelTd">失效描述：</td>
					<td class=inputTd>
						<textarea id="edit_note" maxLength=120 name="note" rows="100" cols="5" style="width:350px;height:80px;"></textarea>
					</td>
				</tr>
			</table>
        </div>
	</form>
</div>
<div id="editRemitDialog" class="container-fluid" style="display:none">
	<form id="remitRechargeFormEdit"  class="bizform">
		<input type="hidden" name="remitRechargeId" id="remitRechargeId" value="" />
		<div class="ui-table">
			<table class="table">
				<tr>
					<td class="inputLabelTd">电汇充值结果：</td>
					<td class=inputTd>
						<select name="remitRechargeState" id="remitRechargeState"  class="select" >
							<option value="02">充值成功</option>
							<option value="03">充值失败</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="inputLabelTd">更改备注：</td>
					<td class=inputTd>
						<textarea id="remitNote" name="remitNote" maxLength=120 rows="100" cols="5" style="width:200px;height:80px;"></textarea>
					</td>
				</tr>
			</table>
        </div>
	</form>
</div>
<form id="queryForm"  class="bizform">
			<div class="row"> 
				<div class="query-cell">
					<div class="cell">
					账户持有人：<input id="accountName" name="accountName" type="text" class="text"/>
					充值状态：<select name="extRechargeState" id="extRechargeState"  class="select" >
							<option value="">请选择----</option>
							<option value="01">处理中</option>
							<option value="02">充值成功</option>
							<option value="03">充值失败</option>
							<option value="04">失效已处理</option>
						</select>
					</div>
					<div class="cell">
					充值类别：<select name="rechargeType" id="rechargeType"  >
							<option value="">请选择----</option>
							<option value="00">默认</option>
							<option value="0">个人银行</option>
							<option value="1">企业银行</option>
							<option value="2">电汇充值</option>
						</select>
					</div>
					<div class="cell">
					创建时间：
						<input id="startDate" name="startTime" type="text" class="Wdate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'#F{$dp.$D(\'endDate\')}'})" /> -
						<input id="endDate" name="endTime" type="text" class="Wdate" value="" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'startDate\')}'})" />
					<button id="query_button" class="class_queryform_first button" type="button" onclick="query();">查询</button>
	           		</div> 
	           </div>
			</div>

		<table id="listTable" style="table-layout:fixed" class="display">
			<thead>
				<tr>
					<th width="20px;">展开</th>
					<th width="30px;" style="word-break:keep-all;">流水号</th>
					<th width="30px;">账户ID</th>
					<th width="50px;">账户持有人</th>
					<th width="50px;">充值类别</th>
					<th width="40px;">银行</th>
					<th width="40px;">金额</th>
					<th width="20px;">卡号</th>
					<th width="30px;">响应码</th>
					<th width="40px;">响应时间</th>
					<th width="40px;">充值状态</th>
					<th width="40px;">创建时间</th>
					<th width="40px;">更新时间</th>
					<!-- 
					<th width="20px;">充值浏览器</th>
					 -->
					<th width="40px;">操作</th>
				</tr>
			</thead>
		</table>
</div>
</form>
<!-- 引入尾文件-->
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<script type="text/javascript">
	var sourceUrl = "<c:url value='/sys/statement/recharge/list'/>";
	var imgUrl = "<c:url value='/styles/images/loading.gif'/>";
    var sortColumns= "recharge_Id,RECHARGE_BN,ACCOUNT_ID,ACCOUNT_NAME,RECHARGE_TYPE,"+
    "BANK_NAME,AMOUNT,BANK_CARD_NO,EXT_RESULT_CODE,EXT_RESULT_TIME,EXT_RECHARGE_STATE,CREATE_TIME,UPDATE_TIME";
    var sort = [[ 1, "desc" ]];

	//显示或隐藏查询条件
$(document).ready(function(){
	var aocolumns = [ 
		{"className":'details-control',"bSortable": false,
			render:function(data, type, row){
		    	return '';}
		},
		{"mDataProp" : "rechargeBn",
				"render":function(data, type, row){
					return cutStr(data,4);
				}
			
		},
		{"mDataProp" : "accountId"},
		{"mDataProp" : "accountName",
			"render":function(data, type, row){
				return cutStr(data,4);
			}
		},
		{"mDataProp" : "rechargeType","render":fmtRechargeType},
		{"mDataProp" : "bankName"},
		{"mDataProp" : "amount"},
		{"mDataProp" : "bankCardNo",
			"render":function(data, type, row){
				return cutStr(data,5);
			}
	    },
		{"mDataProp" : "extResultCode"},
		{"mDataProp" : "extResultTime"},
		{"mDataProp" : "extRechargeState","render":fmtActResult},
		{"mDataProp" : "createTimeStr"},
		{"mDataProp" : "updateTimeStr"},
		{"mDataProp" : "extRechargeState","bSortable": false, "aTargets": [ 12 ],
			render:function (data, type, row){
				if(data=='01'){
					return '<input type="button" class="button" onClick="cancel('+data+')" value="失效"/>';
				}else{
					return fmtActResult(data, type, row);
				}
			}	
		}
	];
	var columnDefs = [ 
	];
	
	initDataTable("#listTable",aocolumns,columnDefs,sortColumns,sourceUrl,imgUrl,sort);
	
});
function fmtActResult(data, type, row){
	if(data=='01'){
		return "处理中";
	}
	else if(data=='02'){
		return "充值成功";
	}
	else if(data=='03'){
		return "充值失败";
	}else if(data == '04'){
		return "失效已处理";
	}else {
		state = data;
	}
}
function fmtRechargeType(data, type, row){
	if(data=='00'){
		return "默认";
	}else if(data=='0'){
		return "个人网银";
	}else if(data=='1'){
		return "企业网银";
	}else if(data=='2'){
		return "电汇充值";
	}else {
		return "未知"+data;
	}
}

function fmtTime(data, type, row){
	if(data){
		return new Date(data).pattern("yyyy-MM-dd HH:mm:ss");
	}
	return data;
}
function format(d) {
	return 'JFID:'+d.jfId+'<br/>流水号:'+d.rechargeBn+'</br>卡号:'+d.bankCardNo+'<br/>充值浏览器：'+
	d.rechargeBrowser+'<br/>异常：'+d.extResultNote;
}
function cancel(id){
	$("#editDialog").dialog({
		height: 300,width:500,
		buttons: [
		          	{ text: "失效", click: function() {
		          					$( this ).dialog( "close" );
		          			} 
		          	},
		          	{ text: "取消", click: function() {
      					$( this ).dialog( "close" );
      					} 
      				} 
		          ] 
	});
}
	
</script>
</body>
</html>
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
		<span>支付报文</span>
	</div>
	<form id="queryForm"  class="bizform" action="<c:url value='/sys/notifications/list'/>">
	<div id="conditions" class="ui-table ui-widget ui-corner-all ui-margin" style="display:block">
		<div class="row"> 
			<div class="query-cell">
				<div class="cell" style="padding-left:8px;">
					接口：<input id="interfaceName" name="interfaceName" type="text" class="text" value="<c:out value='${notifications.message}'/>"/>
				</div>		
				<div class="cell" style="padding-left:8px;">
					请求报文：<input id="reqMessage" name="reqMessage" type="text" class="text" value="<c:out value='${notifications.message}'/>"/>
				</div>
				<div class="cell" style="padding-left:8px;">
					响应报文：<input id="respMessage" name="respMessage" type="text" class="text" value="<c:out value='${notifications.message}'/>"/>
				</div>
			</div>
		</div>
			<div class="row"> 
				<div class="query-cell">
					<div class="cell" style="padding-left:8px;">状态：
						<select id="state" name="state" class="select" >
							<option value="">--请选择--</option>
							<option value="00">发送中</option>
							<option value="01">成功</option>
							<option value="02">失败</option>
						</select>
					</div>
					<div class="cell" style="padding-left:8px;">
						回调报文：<input id="callbackMessage" name="callbackMessage" type="text" class="text" value="<c:out value='${notifications.callbackMessage}'/>"/>
					</div>
					<div class="cell" style="padding-left:8px;">
						回调状态：<select id="callbackState" name="callbackState" class="select" >
							<option value="">--请选择--</option>
							<option value="00">发送中</option>
							<option value="01">成功</option>
							<option value="02">失败</option>
						</select>
					</div>
					<div class="cell" style="padding-left:8px;">
						<button id="query_button" class="class_queryform_first button" type="button" onclick=""><fmt:message key='button.search'/></button>
					</div>
					<div class="cell" style="padding-left:8px;">
						<button id="exportBtn" class="button" onclick="excelExport();"><fmt:message key='button.export'/></button>
				    </div>
				</div>
			</div>
	</div>
	<table id="notificationlist" class="display">
        <thead>
          <tr>
           	<th width="30px;"><input type="checkbox" id="checkedAll" onClick="selectedAll('queryForm',this.checked)"/></th>
            <th width="80px;">报文</th>
            <th width="70px;">流水号</th>
            <th width="70px;">订单号</th>
            <th width="80px;"  style="white-space:nowrap;">请求报文</th>
            <th width="80px;"  style="white-space:nowrap;">响应报文</th>
            <th width="40px;">响应结果</th>
            <th width="60px;">发送时间</th>
            <th width="60px;">接收时间</th>
            <th width="70px;"  style="white-space:nowrap;">回调报文</th>
            <th width="70px;">回调结果</th>
            <th width="70">回调时间</th>
            <th width="40">展开</th>
          </tr>
        </thead>
      </table>
</form>
</div>
<!-- 引入尾文件-->
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	var imgUrl = "<c:url value='/styles/images/loading.gif'/>";
	var id = '#notificationlist';
	var sort = [[ 1, "desc" ]];
	var columns = [
	               ];
	var sortColumns = 'NT_ID,NT_ID,ORDERSN,DSORDERID,REQ_MESSAGE,RESP_MESSAGE,STATE,SEND_TIME,RECIEVE_TIME';
	var sourceUrl = "<c:url value='/sys/notifications/list'/>";
	var aocolumns = [{"mDataProp": "ntId", "bSortable": false,"aTargets": [ 0 ],
			render: function (data, type, row, meta) {   // o, v contains the object and value for the column
				return '<input name="ids" type="checkbox" value="'+data+
				'" id="someCheckbox"+'+data+' onchange="changCheckboxChecked(\'ids\',\'queryForm\',\'checkedAll\');"'
				+' name="someCheckbox" />';
			}
		},
		{"mDataProp": "interfaceName", "aTargets": 1,
			"render": function (data, type, row) {   
				return '<a href=\"#\" onClick="showDetail(\''+row.ntId+'\')">'+data+'</>';
			}
		},
		{"mDataProp": "ordersn", "aTargets": [ 2 ]},
		{"mDataProp": "dsorderid", "aTargets": [ 3 ]},
		{"mDataProp": "reqMessage", "aTargets": [ 4 ], 
			"bSortable": false,
			"render": function (data, type, row) {   
				return cutStr(data,25);
			}
		},
		{"mDataProp": "respMessage", "aTargets": [ 5 ], "bSortable": false,
			"render": function (data, type, row) {   
				return cutStr(data,25);
			}
		},
		{"mDataProp": "stateName", "aTargets": [ 6 ]},
		{"mDataProp": "sendTimeStr", "aTargets": [ 7 ]},
		{"mDataProp": "recieveTimeStr", "aTargets": [ 8 ]},
		{"mDataProp": "callbackMessage", "bSortable": false,"aTargets": [ 9 ],
			"render": function (data, type, row) {   
				return cutStr(data,25);
			}},
		{"mDataProp": "callbackStateName","bSortable": false, "aTargets": [ 10 ]},
		{"mDataProp": "callbackTimeStr","bSortable": false, "aTargets": [ 11 ]},
		{"bSortable": false, "aTargets": [ 12 ],"className":'details-control',
			"render":function(data, type, row){
				return ''
			}	
		}
	];
	initDataTable(id,aocolumns,columns,sortColumns,sourceUrl,imgUrl,sort);
} );
function format ( d ) {
    return  '流水号:'+d.ordersn+'</br>请求报文：'+d.reqMessage+'</br>响应报文:'+d.respMessage+
    '<br/>回调报文:'+d.callbackMessage;
}
function showDetail(ntId){
	window.open("<c:url value='/sys/notifications/show'/>?id="+ntId);
}
function excelExport(){
}
function showMessage(message){
}
</script>
</body>
</html>
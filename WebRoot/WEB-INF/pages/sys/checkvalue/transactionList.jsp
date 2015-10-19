<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>交易验签</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${csssuffix}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${csssuffix}'/>" media="screen, projection"/> 
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/themeDS.${csssuffix}'/>"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/common.${csssuffix}'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/menu.${csssuffix}'/>"/>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
</head>

<body>
<div class="pay_cont_sys">
<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
	<div class="barTitle">
		<span></span>
		交易验签&nbsp;|&nbsp;
		<span>
			<a href="<c:url value='/sys/checkvalue/show'/>">企业/个人用户验签</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/checkvalue/showjf'/>">平台账户验签</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/checkvalue/showorder'/>">订单验签</a> 
		</span>
	</div>
	<form id="queryForm"  class="bizform">
		<div class="row"> 
			<!--
            <div class="cell">
            &nbsp;&nbsp;订单名称：<input id="orderName" name="orderName" type="text" class="text" value="<c:out value='${jFTransaction.orderName}'/>"/>
            &nbsp;&nbsp;付款方名称：<input id="payerAccountName" name="payerAccountName" type="text" class="text" value="<c:out value='${jFTransaction.payerAccountName}'/>"/>
            &nbsp;&nbsp;收款方名称：<input id="payeeAccountName" name="payeeAccountName" type="text" class="text" value="<c:out value='${jFTransaction.payeeAccountName}'/>"/>
            <button id="query_button" class="button" type="button" onclick="query();">查询</button>
			</div>
            <div class="querycell">
				<button id="query_button" class="button" type="button" onclick="query();"><fmt:message key='button.search'/></button>
            </div>-->
		</div>
    
    	<div class="nav">
            &nbsp;&nbsp;订单名称：<input id="orderName" name="orderName" type="text" class="text" value="<c:out value='${jFTransaction.orderName}'/>"/>
            &nbsp;&nbsp;付款方名称：<input id="payerAccountName" name="payerAccountName" type="text" class="text" value="<c:out value='${jFTransaction.payerAccountName}'/>"/>
            &nbsp;&nbsp;收款方名称：<input id="payeeAccountName" name="payeeAccountName" type="text" class="text" value="<c:out value='${jFTransaction.payeeAccountName}'/>"/>
            <button id="query_button" class="class_queryform_first button" type="button" onclick="query();">查询</button>
        	<button id="resetAllBtn" class="button" type="button" onclick="resetAll();">重置所有记录</button>
			<!--
        	<button id="restBtn" class="button" type="button" onclick="resetSel();">重置选中记录</button>
			-->
	    </div>
	
	<table id="transactionlist" style="table-layout:fixed" class="display">
        <thead>
          <tr>
          	<th width="50px;">交易流水ID</th>
            <th width="50px;">交易流水号</th>
            <th width="100px;">订单ID</th>
            <th width="90px;">订单编号</th>
            <th width="110px;">订单名称</th>
            <th width="50px;">发包方</th>
            <th width="50px;">接包方</th>
            <th width="100px;">验签值</th>
            <th width="30px;">状态</th>
            <th width="70px;">操作</th>
          </tr>
        </thead>
      </table>
     </form>
     <div id="recheckdialog" title="重新验签" style="display:none;valign:middle" >
		确认重新验签？
	  </div>
</div>

<!-- 引入尾文件-->
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>

<script type="text/javascript">

$(document).ready(function() {
	var sourceUrl = "<c:url value='/sys/checkvalue/querytrans'/>";
	var imgUrl = "<c:url value='/styles/images/loading.gif'/>";
    var sortColumns= "T_ID";
    var sort = [[ 0, "desc" ]];
    function renderStatus(data, type, row, meta){
    	var string = '未知';
		if(data == true){
			string = "<span style='color:greeb'>正常</span>";
		}else if(data == false){
			string = "<span style='color:red'>验签失败</span>";
		}
		return string;
    }
    function renderOperate(data, type, row, meta){
    	return '<a href="#" onClick="reCheckValue(\''+row.tId+'\')">重新验签</a>';
    }
	var aocolumns = [ 
	    {"mDataProp": "tId"},
		{"mDataProp": "transId"},
		{"mDataProp": "oId"},
		{"mDataProp": "orderNo"},
		{"mDataProp": "orderName"},
		{"mDataProp": "payerAccountName"},
		{"mDataProp": "payeeAccountName"},
		{"mDataProp": "checkValue"},
		{"mDataProp": "checkFlag"}
	];
	var columnDefs = [ 
		{"aTargets": [8], "render": renderStatus},
		{"aTargets": [9], "render": renderOperate}
	]; 

	initDataTable("#transactionlist",aocolumns,columnDefs,sortColumns,sourceUrl,imgUrl,sort);
   
});

	function query(){
		listGrid.setGridParam({"page":"1"});
		listGrid.trigger('reloadGrid');
	}
	function resetAll(){
		window.location="transactionresetAll";
	}
	
	function resetSingle(id,value){
		window.location="transactionreset/" + id;
	}
	function reCheckValue(tId){
		$("#recheckdialog").dialog({
			buttons: [
			          	{ text: "确定", click: function() {
			          			$( this ).dialog( "close" );
			          			exeReCheckValue(tId);
			          		} 
			          	} ,
			          	{text: "取消", click: function() {
		          				$( this ).dialog( "close" );
		          			} 
			          	} ,
			         ] 
		});
	}
	function exeReCheckValue(tId){  
		location.href="<c:url value='/sys/checkvalue/treset'/>?tId="+tId; 
	}
	
	function resetAll(){
		location.href="<c:url value='/sys/checkvalue/tresetAll'/>";
	}
</script>
</body>
</html>
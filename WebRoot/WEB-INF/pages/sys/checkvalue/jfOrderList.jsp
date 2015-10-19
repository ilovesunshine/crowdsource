<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<html>
<head>
<title>订单验签</title>
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
		订单验签&nbsp;|&nbsp;
		<span>
			<a href="<c:url value='/sys/checkvalue/show'/>">企业/个人用户验签</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/checkvalue/showjf'/>">平台账户验签</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/checkvalue/showtrans'/>">交易验签</a> 
		</span>
	</div>
	<div id="recheckdialog" title="重新验签" style="display:none;valign:middle" >
		确认重新验签？
	  </div>
	<form id="queryForm"  class="bizform">
    	<div class="nav">
	        &nbsp;订单名称：<input id="orderName" name="orderName" type="text" class="text"/>
	        <button id="query_button" class="class_queryform_first button" type="button" onclick="query();">查询</button>
        	<button id="resetAllBtn" class="button" type="button" onclick="resetAll();">重置所有记录</button>
			<!--
        	<button id="restBtn" class="button" type="button" onclick="resetSel();">重置选中记录</button>
			-->
	    </div>
	    <table id="orderlist" style="table-layout:fixed" class="display">
        <thead>
          <tr>
          	<th width="50px;">订单ID</th>
            <th width="90px;">订单编号</th>
            <th width="150px;">订单名称</th>
            <th width="50px;">类别</th>
            <th width="100px;">验签值</th>
            <th width="50px;">状态</th>
            <th width="70px;">操作</th>
          </tr>
        </thead>
      </table>
	</form>
	
</div>

<!-- 引入尾文件-->
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
	var sourceUrl = "<c:url value='/sys/checkvalue/queryorder'/>";
	var imgUrl = "<c:url value='/styles/images/loading.gif'/>";
    var sortColumns= "O_ID";
    var sort = [[ 0, "desc" ]];
    function renderType(data, type, row, meta){
    	var string = '未知';
		if(data == '01'){
			string = "订单";
		}else if(data == '02'){
			string = "<span style='color:red'>红包</span>";
		}
		return string;
    }
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
    	return '<a href="#" onClick="reCheckValue(\''+row.oId+'\')">重新验签</a>';
    } 
	var aocolumns = [ 
	    {"mDataProp": "oId"},
		{"mDataProp": "orderNo"},
		{"mDataProp": "orderName"},
		{"mDataProp": "orderType"},
		{"mDataProp": "checkValue"},
		{"mDataProp": "checkFlag"}
	];
	var columnDefs = [ 
		{"aTargets": [3], "render": renderType},
		{"aTargets": [5], "render": renderStatus},
		{"aTargets": [6], "render": renderOperate}
	];

	initDataTable("#orderlist",aocolumns,columnDefs,sortColumns,sourceUrl,imgUrl,sort);
   
});

	function query(){
		listGrid.setGridParam({"page":"1"});
		listGrid.trigger('reloadGrid');
	}
	function resetAll(){
		window.location="jfOrderresetAll";
	}
	
	function resetSingle(id,value){
		window.location="jfOrderreset/" + id;
	}
	
	function reCheckValue(oId){
		$("#recheckdialog").dialog({
			buttons: [
			          	{ text: "确定", click: function() {
			          			$( this ).dialog( "close" );
			          			exeReCheckValue(oId);
			          		} 
			          	} ,
			          	{text: "取消", click: function() {
		          				$( this ).dialog( "close" );
		          			} 
			          	} ,
			         ] 
		});
	}
	function exeReCheckValue(oId){  
		location.href="<c:url value='/sys/checkvalue/oreset'/>?oId="+oId; 
	}
	
	function resetAll(){
		location.href="<c:url value='/sys/checkvalue/oresetAll'/>";
	}

	$("#dialog").dialog({
	    bgiframe: true,
	    resizable: false,
	    height:140,
	    modal: true,
	    overlay: {
	        backgroundColor: '#000',
	        opacity: 0.5
	    },
	    buttons: {
	        'Delete all items in recycle bin': function() {
	            $(this).dialog('close');
	        },
	        Cancel: function() {
	            $(this).dialog('close');
	        }
	    }
	});
	
</script>
</body>
</html>
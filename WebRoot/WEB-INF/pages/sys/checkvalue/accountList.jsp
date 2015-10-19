<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>企业/个人用户验签</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${csssuffix}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${csssuffix}'/>" media="screen, projection"/> 
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/themeDS.${csssuffix}'/>"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/common.${csssuffix}'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/menu.${csssuffix}'/>"/>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
</head>
<style>
.query-cell{display:inline;width:200px;}
.query-value{display:inline !important;width:100px;}
.query-label{display:inline !important;width:100px;}
</style>
<body>
<div class="pay_cont_sys">
	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
	<div class="barTitle">
		企业/个人用户验签&nbsp;|&nbsp;
		<span>
			<a href="<c:url value='/sys/checkvalue/showjf'/>">平台账户验签</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/checkvalue/showorder'/>">订单验签</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/checkvalue/showtrans'/>">交易验签</a> 
		</span>
	</div>
<form id="queryForm"  class="bizform" action="<c:url value='/admincontroller/sys/exportWithExcel'/>">
	<div class="nav">
        <div class="query-cell">
			<div class="query-label">用户名：</div>
			<div class="query-value"><input id="userName" name="userName" type="text" class="text" value="<c:out value='${}'/>"/></div>
		</div>
		<div class="query-cell">
			<button class="class_queryform_first button" type="button" onClick="query();">查询</button>
		</div>
        &nbsp;<button id="resetAllBtn" class="button" type="button" onclick="resetAll();">重新验签</button>
	</div>
	<table id="accountlist" style="table-layout:fixed" class="display">
        <thead>
          <tr>
          	<th width="50px;">账户ID</th>
            <th width="50px;">JF平台ID</th>
            <th width="100px;">用户名</th>
            <th width="90px;">账号</th>
            <th width="110px;">用户标示</th>
            <th width="50px;">账户类别</th>
            <th width="100px;">验签值</th>
            <th width="30px;">状态</th>
            <th width="70px;">操作</th>
          </tr>
        </thead>
      </table>
      </form>
      <div id="dd" title="重置用户名" style="display:none;valign:middle" >
		确认重置用户名？
	  </div>
	  <div id="recheckdialog" title="重新验签" style="display:none;valign:middle" >
		确认重新验签？
	  </div>
	  
<div id="renamedialog" style="padding:5px;width:400px;height:200px;display:none;">  
    <table id="dialogTable" cellspacing=3 class="" style="margin-top : 15px;">
		<tr>
			<td class="dialog-tr-left">账户名称：</td>
			<td class="dialog-tr-right"> 
				<input type="text" id="userName" name="userName" value=""/>
			</td>
		</tr>
	</table>  
</div>  
	  
</div>
<script type="text/javascript">
$(document).ready(function() {
	var sourceUrl = "<c:url value='/sys/checkvalue/query'/>";
	var imgUrl = "<c:url value='/styles/images/loading.gif'/>";
    var sortColumns= "ACCOUNT_ID";
    var sort = [[ 0, "desc" ]];
    function renderType(data, type, row, meta){
    	var string = '未知';
		if(data == '0'){
			string = "个人";
		}else if(data == '1'){
			string = "企业";
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
    	return '<a href="#" onClick="reCheckValue(\''+row.accountId+'\')">重新验签</a>';
    }
	var aocolumns = [ 
	    {"mDataProp": "accountId"},
		{"mDataProp": "jfId"},
		{"mDataProp": "extUserNameStr"},
		{"mDataProp": "extCardNo"},
		{"mDataProp": "extUserNo"},
		{"mDataProp": "extUserType"},
		{"mDataProp": "checkValue"},
		{"mDataProp": "checkFlag"}
	];
	var columnDefs = [ 
		{"aTargets": [5], "render": renderType},
		{"aTargets": [7], "render": renderStatus},
		{"aTargets": [8], "render": renderOperate}
	]; 

	initDataTable("#accountlist",aocolumns,columnDefs,sortColumns,sourceUrl,imgUrl,sort);
   
});
function excelExport(){
	$("#queryForm").submit();
}
function resetAccName(accountId){
	$('#renamedialog').dialog({  
	    title:'重置用户名',  
		buttons: {
	    '确定': function() {
	    	exeResetName(accountId);
	        $(this).dialog('close');
	    },
	    '取消': function() {
	        $('#renamedialog').dialog('close');
	    }
	}
	});
}
function exeResetName(accountId){
	var userName = $("#userName").val();
	$.ajax({
		   type: "POST",
		   url: "<c:url value='/sys/checkvalue/reset'/>",
		   data: 
		   {
		   "accountId": accountId,
		   "userName": userName
		   },
		   success : function(data, textStatus) {
			   alert(data.returncode);
			   if (data.returncode != '00') {
				 $("#errMsg").text(data.errMsg);
				 $("#errMsg").show();
			   } else {
				var userNameNew = $("#userName").val();
				accountListGrid.setCell($("#accountId").val(),"extUserNameStr", userNameNew);;
			   }
		   },
		   error : function(data, textStatus) {
			   alert("重置用户名失败！");
		   }
		});
}
function reCheckValue(accountId){
	$("#recheckdialog").dialog({
		buttons: [
		          	{ text: "确定", click: function() {
		          			$( this ).dialog( "close" );
		          			exeReCheckValue(accountId);
		          		} 
		          	} ,
		          	{text: "取消", click: function() {
	          				$( this ).dialog( "close" );
	          			} 
		          	} ,
		         ] 
	});
}
function exeReCheckValue(accountId){  
	location.href="<c:url value='/sys/checkvalue/recheck'/>?accountId="+accountId; 
}

function resetAll(){
	location.href="<c:url value='/sys/checkvalue/recheckAll'/>";
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
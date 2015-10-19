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

<body>

<div class="pay_cont_sys">
<div id="dialog" title="确认重新开户对话框" style="display:none;valign:middle" >
	确认重新开户？
</div>
<div id="recodenamedialog" title="确认解密旧账户对话框" style="display:none;valign:middle" >
	确定批量解密账户数据？
</div>
<div id="recodenamedialogresult" title="解密旧账户结果对话框" style="display:none;valign:middle" >
</div>
<div id="recreatedialog" title="重新开户失败对话框" style="display:none;valign:middle" >
</div>
<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
	<div class="barTitle">
		已开户信息&nbsp;|&nbsp;
		<span>
			<a href="<c:url value='/sys/accountreg/account/resolved'/>">已注销账户</a> 
		</span>
	</div>
	
	<form id="queryForm"  action="<c:url value='/admincontroller/sys/exportWithExcel'/>" class="bizform" method="post">
		<div class="row"> 
            <div class="cell">&nbsp;&nbsp;
             	账户类别：<select id="accountType" name="accountType">
							<option value="">--请选择--</option>
							<option value="0">个人</option>
							<option value="1">企业</option>
						</select>&nbsp;
			        用户/账户名称：<input id="userName" name="userName" type="text" class="text" value="<c:out value='${payAccount.userName}'/>"/>&nbsp;
                                      账号：<input id="extCardNo" name="extCardNo" type="text" class="text" value=""/>&nbsp;
            <button id="query_button" class="class_queryform_first button" type="button" onclick="" style="margin-left:10px;">查询</button>
			</div>
		</div>
		
		<div class="nav">
        	<button id="resetAllBtn" class="button" type="button" onclick="resetAll();">重新验签</button>
			<button id="decodeBtn" class="button" type="button" onclick="decodeUserName();">解密旧账户</button>
			<button id="harvestBtn" class="button" type="button" onclick="harvestDisplayName();">获取显示名称</button>
	    </div>
		<table id="resolvelist" style="table-layout:fixed" class="display">
        <thead>
          <tr>
           	<th width="30px;"><input type="checkbox" id="checkedAll" onClick="selectedAll('queryForm',this.checked)"/>选择</th>
            <th width="30px;">账户ID</th>
            <th width="120px;">账户名称</th>
            <th width="20px;">JFID</th>
            <th width="60px;">显示名称</th>
            <th width="80px;">账号</th>
            <th width="40px;">账户类别</th>
            <th width="40px;">支付方式</th>
            <th width="90px;">开户时间</th>
            <!--  -->
            <th width="40">验签状态</th>
            <th width="80">操作</th>
          </tr>
        </thead>
      </table>
      </form>
</div>
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<!-- 引入尾文件-->

<script type="text/javascript">
$(document).ready(function() {
	var imgUrl = "<c:url value='/styles/images/loading.gif'/>";
	var id = '#resolvelist';
	var columns = [];
	var sort = [[ 1, "desc" ]];
	var sortColumns = 'ACCOUNT_ID,ACCOUNT_ID,USER_NAME,JF_ID,JF_NAME,EXT_CARD_NO,ACCOUNT_TYPE,PAY_TYPE,CREATE_TIME';
	var sourceUrl = "<c:url value='/sys/accountreg/account/listaccount'/>";
	var aocolumns = [ 
					{"mDataProp": "accountId", "bSortable": false,"aTargets": [ 0 ],
						render: function (data, type, row) {   // o, v contains the object and value for the column
							return '<input name="ids" type="checkbox" value="'+data+
							'" id="someCheckbox"+'+data+' onchange="changCheckboxChecked(\'ids\',\'queryForm\',\'checkedAll\');"'
							+' name="someCheckbox" />';
						}
					},
					{"mDataProp": "accountId", "aTargets": [ 1 ]},
					{"mDataProp": "userName", "aTargets": [ 2 ]},
					{"mDataProp": "jfId", "aTargets": [ 3 ]},
					{"mDataProp": "jfName","bSortable": false, "aTargets": [ 4 ],
						render: function (data, type, row) {   // o, v contains the object and value for the column
							if(row.jfName == null || row.jfName == ""){
								return "<span style='display:none' id=\""+row.jfId+"_"+"_span\"></span><a id=\""
								+row.jfId+"_"+"_a\" href=\"#\" onClick=getShowName(\""+row.jfId+
										"\",\""+row.accountType+"\")>获取显示名称</a>";
							}else{
								return data;
							}
						},
					},
					{"mDataProp": "extCardNo", "aTargets": [ 5 ]},
					{"mDataProp": "extUserTypeName", "aTargets": [ 6 ]},
					{"mDataProp": "payTypeName", "aTargets": [ 7 ],
						render: function (data, type, row) {   // o, v contains the object and value for the column
							if(data=='未知'){
								return '<font color=red>未知</font>';
							}else{
								return data;
							}
						},
					},
					{"mDataProp": "createTimeStr", "aTargets": [ 8 ]},
					{"mDataProp": "checkFlagResult", "aTargets": [ 9 ],"bSortable": false},
					{
						"bSortable": false,
						aTargets: [10],    // Column number which needs to be modified
						render: function (data, type, row) {   // o, v contains the object and value for the column
							//
							return '<a href="#" onClick="recreateAccount(\''+ row.accountId+'\')">重新开户</a>&nbsp;'+
							'|&nbsp;<a href="#" onClick="reCheckValue(\''+row.accountId+'\')">重新验签</a>';
						},
					}
      	              ];
	initDataTable(id,aocolumns,columns,sortColumns,sourceUrl,imgUrl,sort);
} );
function getShowName(jfId,accountType){
	$.ajax({
        url : "<c:url value='/sys/accountreg/account/queryDisplayName'/>",
        data : {"jfId":jfId,"accountType":accountType},
        type : 'post',
        dataType : 'json',
        async : false,
        success : function(result) {
        	var aId = "#"+jfId+"_"+accountType+"_a";
        	$(aId)[0].style.display='none';
        	aId = "#"+jfId+"_"+accountType+"_span";
        	$(aId)[0].style.display='block';
        	$(aId)[0].innerHTML=result.displayName;
        },
        error : function(msg) {
        	alert("查询失败！");
        }
    });
}
function recreateAccount(accountId){
	$("#dialog").dialog({
		buttons: [
		          	{ text: "确定", click: function() {
		          					$( this ).dialog( "close" );
		          					callRecreateAccount(accountId);
		          			} } ,
		          	  {text: "取消", click: function() {
	          					$( this ).dialog( "close" );
	          			} } ,
		          ] 
	});
}
function callRecreateAccount(accountId){
	$.ajax({
        url : "<c:url value='/sys/accountreg/account/forwardToOpenAccount'/>",
        data : {"accountId":accountId},
        type : 'post',
        dataType : 'json',
        async : false,
        success : function(result) {
        	if(result.flag){
        		location.href="<c:url value='/sys/accountreg/account/toOpenAccountPage'/>?accountId="+accountId;
        	}else{
        		$("#recreatedialog").html(result.msg);
        		$("#recreatedialog").dialog({
        			buttons: [
        			          	{ text: "确定", click: function() {
        			          					$( this ).dialog( "close" );
        			          			} } 
        			          	  
        			          ] 
        		});
        	}
        },
        error : function(msg) {
        	alert("操作失败！");
        }
    });
}
function reCheckValue(accountId){
	location.href="<c:url value='/sys/accountreg/account/resetaccount'/>?accountId="+accountId;
}
function resetAll(){
	location.href="<c:url value='/sys/accountreg/account/resetallaccount'/>";
}
function decodeUserName(){
	$("#recodenamedialog").html("确定批量解密账户数据？");
	$("#recodenamedialog").dialog({
		buttons: [
		          	{ text: "确定", click: function() {
		          					$( this ).dialog( "close" );
		          					 setTimeout("callDecodeUserName()",2000);
		          			} } ,
		          	  {text: "取消", click: function() {
	          					$( this ).dialog( "close" );
	          			} } ,
		          ] 
	});
}
function callDecodeUserName(){
	$.ajax({
        url : "<c:url value='/sys/accountreg/account/decodeaccountname'/>",
        type : 'post',
        dataType : 'json',
        async : false,
        success : function(result) {
        	if(result.flag){
        		$("#recodenamedialogresult").html("解密成功，共解密账户:" + result.msg + "个!");
        		$("#recodenamedialogresult").dialog({
        			buttons: [
        			          	{ text: "确定", click: function() {
        			          					$( this ).dialog( "close" );
        			          					location.href ="<c:url value='/sys/accountreg/account/resolveerror'/>";
        			          			} } 
        			          	  
        			          ] 
        		});
        	}else{
        		$("#recodenamedialogresult").html("解密失败，请稍后再试");
        		$("#recodenamedialogresult").dialog({
        			buttons: [
        			          	{ text: "确定", click: function() {
        			          					$( this ).dialog( "close" );
        			          			} } 
        			          	  
        			          ] 
        		});
        	}
        },
        error : function(msg) {
        	$("#recodenamedialogresult").html("解密失败，请稍后再试");
    		$("#recodenamedialogresult").dialog({
    			buttons: [
    			          	{ text: "确定", click: function() {
    			          					$( this ).dialog( "close" );
    			          			} } 
    			          	  
    			          ] 
    		});
        }
    });
}
//获取显示名称
function harvestDisplayName(){
	$("#recodenamedialog").html("确定批量获取显示名称？");
	$("#recodenamedialog").dialog({
		buttons: [
		          	{ text: "确定", click: function() {
		          					$( this ).dialog( "close" );
		          					 setTimeout("execharvestDisplayName()",2000);
		          			} } ,
		          	  {text: "取消", click: function() {
	          					$( this ).dialog( "close" );
	          			} } ,
		          ] 
	});
	}

function execharvestDisplayName(){
	$.ajax({
        url : "<c:url value='/sys/accountreg/account/harvestDisplayName'/>",
        type : 'post',
        dataType : 'json',
        async : false,
        success : function(result) {
        	if(result.flag){
        		$("#recodenamedialogresult").html("获取成功，共获取:" + result.msg + "个显示名称!");
        		$("#recodenamedialogresult").dialog({
        			buttons: [
        			          	{ text: "确定", click: function() {
        			          					$( this ).dialog( "close" );
        			          					location.href ="<c:url value='/accountreg/account/resolveerror'/>";
        			          			} } 
        			          	  
        			          ] 
        		});
        	}else{
        		$("#recodenamedialogresult").html("获取失败，请稍后再试！");
        		$("#recodenamedialogresult").dialog({
        			buttons: [
        			          	{ text: "确定", click: function() {
        			          					$( this ).dialog( "close" );
        			          			} } 
        			          	  
        			          ] 
        		});
        	}
        },
        error : function(msg) {
        	$("#recodenamedialogresult").html("获取失败，请稍后再试！");
    		$("#recodenamedialogresult").dialog({
    			buttons: [
    			          	{ text: "确定", click: function() {
    			          					$( this ).dialog( "close" );
    			          			} } 
    			          	  
    			          ] 
    		});
        }
    });
}
</script>
</body>
</html>
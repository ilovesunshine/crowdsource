<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>子账户管理</title>
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
		<span>子账户管理</span>
	</div>
	<form id="queryForm"  class="bizform" action="<c:url value='/sys/notifications/list'/>">
	<div id="conditions" class="ui-widget" style="display:block;padding-bottom:6px;">
		<div class="row"> 
    		<div class="query-cell">
    		<button id="openBtn" class="button" type="button" onclick="batchOpenFrozenAccount();">批量开通冻结子账户</button>
			</div>
		</div>
	</div>
	<table id="subaccountlist" style="table-layout:fixed" class="display">
        <thead>
          <tr>
           	<th width="30px;"><input type="checkbox" id="checkedAll" onClick="selectedAll('queryForm',this.checked)"/>选择</th>
            <th width="30px;">账户ID</th>
            <th width="40px;">平台ID</th>
            <th width="100px;">用户名</th>
            <th width="50px;">账户类别</th>
            <th width="90px;">主账户</th>
            <th width="90px;">主账户开户时间</th>
            <th width="80">子账户</th>
            <th width="80">冻结金额</th>
            <th width="60">操作</th>
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
	var id = '#subaccountlist';
	var sort = [[ 1, "desc" ]];
	var columns = [];
	var sortColumns = 'ACCOUNT_ID,ACCOUNT_ID,JF_ID,USER_NAME,ACCOUNT_TYPE,EXT_CARD_NO,CREATE_TIME,SUB_EXT_CARD_NO';
	var sourceUrl = "<c:url value='/sys/subAccount/query'/>";
	var aocolumns = [{"mDataProp": "accountId", "bSortable": false,"aTargets": [ 0 ],
			render: function (data, type, row) {   // o, v contains the object and value for the column
				return '<input name="ids" type="checkbox" value="'+data+
				'" id="someCheckbox"+'+data+' onchange="changCheckboxChecked(\'ids\',\'queryForm\',\'checkedAll\');"'
				+' name="someCheckbox" />';
			}
		},
		{"mDataProp": "accountId", "aTargets": [ 1 ]},
		{"mDataProp": "jfId", "aTargets": [ 2 ]},
		{"mDataProp": "userName", "aTargets": [ 3 ]},
		{"mDataProp": "accountTypeName", "aTargets": [ 4 ]},
		{"mDataProp": "extCardNo", "aTargets": [ 5 ]},
		{"mDataProp": "createTimeStr", "aTargets": [ 6 ]},
		{"mDataProp": "subExtCardNo", "aTargets": [ 7 ]},
		{"bSortable": false, "aTargets": [ 8 ],
			render: function (data, type, row) {   // o, v contains the object and value for the column
				if(row.subExtCardNo == ""){
					return "";
				}else{
                    return "<label id = \"balance" + row.jfId +
                    	"\"></label><label id = \"showBalance" + row.jfId
                    		+ "\" onclick=\"getBalance('" + row.jfId + "','" + row.accountId + "')\" style=\"color:blue;cursor:pointer;text-decoration:underline;\">查看余额</label>";
				}
			},	
		},
		{ "aTargets": [ 9 ],"bSortable": false,
			render: function (data, type, row) {   // o, v contains the object and value for the column
				//
				return '<input type="button" value="开通冻结子账户" onClick="openFrozenAccount('+row.accountId+')"/>';
			},
		}
		
	];
	initDataTable(id,aocolumns,columns,sortColumns,sourceUrl,imgUrl,sort);
	
} );
//异步获取余额
function getBalance(jfId,accountId){
    var url = "<c:url value='/sys/subAccount/queryBalance'/>";
    $.ajax({
        url : "<c:url value='/sys/subAccount/queryBalance'/>",
        data : {"accountId":accountId},
        type : 'post',
        dataType : 'json',
        async : true,
        success : function(data) {
        	 var balance = data.balance;
             $("#showBalance" + jfId).css("display","none");
             $("#balance" + jfId).css("display","block");$("#balance" + jfId).css("text-align","center");
             $("#balance" + jfId).text(balance);
             //
        },
        error : function(msg) {
        	alert("查询失败！");
        }
    });
} 
//单个开通冻结子账户
function openFrozenAccount(accountId){
	var dataUrl = "<c:url value='/sys/subAccount/singleOpen' />?accountId=" + accountId;
	$.ajax({
		url: dataUrl,
		cache:false,
		success: function(data, textStatus){
			if(data.result == '00'){
				showInfo("操作成功",3000);
				setTimeout("refreshPage()",2000);
			}else{
				showInfo("操作失败,"+data,3000);
			}
		},
	});
}
//批量开通冻结子账户
function batchOpenFrozenAccount(rowid){
	var count = $("[name='ids']:checked").length;
	if(count == null || count== 0){
		showInfo("请选择需要开通冻结子账户的账户",1500); 
		return ;
	}
	var accountIds = '';
	var now = 0;
	$("[name='ids']:checked").each(function(){
		 now ++;
		 accountIds+=$(this).val();
		 if(now!=count){
			 accountIds+=','
		 }
	 });
	$('#frozenShade').show();
	$('#frozenInfo').show();
	var dataUrl = "<c:url value='/sys/subAccount/batchOpen' />?accountIds=" + accountIds;
	$.ajax({
		url: dataUrl,
		cache:false,
		success: function(data, textStatus){
			 $('#frozenShade').hide();
			 $('#frozenInfo').hide();
			if(data.result == '00'){
				showInfo("操作成功",3000);
				setTimeout("refreshPage()",2000);
			}else{
				showInfo("批量操作失败,请查看行为日志审核！",3000);
			}
        },
        error : function() {	
	       	 $('#frozenShade').hide();
			 $('#frozenInfo').hide();         	   
			 showInfo("批量操作失败,请查看行为日志审核！",3000);
          }
	});
}

function refreshPage(){
	location.reload();
}
</script>
</body>
</html>
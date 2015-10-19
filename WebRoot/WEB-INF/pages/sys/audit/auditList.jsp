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
		<span>行为审核</span>
	</div>
	<form id="queryForm"  class="bizform" action="<c:url value='/sys/audit/export'/>">
	<div class="row"> 
		<div class="query-cell">
			<div class="cell">
				操作人：<input id="actor" name="actor" type="text" class="text" value="<c:out value='${payAudit.actor}'/>"/>
			</div>
			<div class="cell" style="padding-left:8px;">
				&nbsp;&nbsp;&nbsp;用户名：<input id="userid" name="userid" type="text" class="text" value="<c:out value='${payAudit.userid}'/>"/>
			</div>
			<div class="cell" style="padding-left:6px;">
				用户ID：<input id="personuuid" name="personuuid" type="text" class="text" value="<c:out value='${payAudit.personuuid}'/>"/>
			</div>
			<div class="cell" style="padding-left:6px;">
				账户类型：<select id="accounttype" name="accounttype" class="select" >
							<option value="">--请选择--</option>
							<option value="-1">GUEST</option>
							<option value="0">个人用户</option>
							<option value="1">企业用户</option>
							<option value="2">运营管理员</option>
							<option value="3">系统管理员</option>
							<option value="4">API请求者</option>
							<option value="5">JOB运行者</option>
							<option value="6">SERVICE</option>
						</select>
			</div>
		</div>
	</div>
	<div class="row"> 
		<div class="cell" style="padding-left:12px;">
			行为：<select id="actAction" name="actAction" class="select" ></select>
		</div>
		<div class="cell" style="padding-left:6px;">
			功能模块：<select id="actObj" name="actObj" class="select" ></select>
		</div>
		<div class="cell" style="padding-left:19px;">
			事件：<input id="actMessage" name="actMessage" type="text" class="text" value="<c:out value='${payAudit.actMessage}'/>"/>
		</div>
		<div class="cell" style="padding-left:6px;">
		操作结果：<select id="actResult" name="actResult" class="select" >
							<option value="">--请选择--</option>
							<option value="1">成功</option>
							<option value="0">失败</option>
						</select>
		</div>
	</div>
	<span> 
		<div class="cell"  style="padding-right:90px;" align="right">
			<button class="class_queryform_first button" type="button" onClick="">查询</button>
			<button id="exportBtn" class="button" onclick="excelExport();">导出为Excel</button>
		</div>
	</span>
	<table id="auditloglist" style="table-layout:fixed" class="display">
         <thead>
          <tr>
           	<th width="30px;"><input type="checkbox" id="checkedAll" onClick="selectedAll('queryForm',this.checked)"/></th>
            <th width="30px;">ID</th>
            <th width="50px;">操作人</th>
            <th width="60px;">用户名</th>
            <th width="30px;">用户ID</th>
            <th width="60px;">账户类型</th>
            <th width="40px;">操作结果</th>
            <th width="100px;">行为</th>
            <th width="20px;">模块</th>
            <th width="90px;">时间</th>
            <!--  -->
            <th width="160">事件</th>
            <th width="30">展开</th>
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
	var id = '#auditloglist';
	var columns = [];
	var sort = [[ 1, "desc" ]];
	var sortColumns = 'ACT_ID,ACT_ID,ACTOR,userid,PERSONUUID,ACCOUNTTYPE,ACT_RESULT,ACT_ACTION,ACT_OBJ,ACT_TIME';
	var sourceUrl = "<c:url value='/sys/audit/query'/>";
	var aocolumns = [
	{"mDataProp": "actId", "bSortable": false,"aTargets": [ 0 ],
		render: function (data, type, row) {  
			return '<input name="ids" type="checkbox" value="'+data+
			'" id="someCheckbox"+'+data+' onchange="changCheckboxChecked(\'ntids\',\'queryForm\',\'checkedAll\');"'
			+' name="someCheckbox" />';
		}
	},
	{ "mDataProp": "actId", "aTargets": [ 1 ],
		render: function (data, type, row) {  
			return '<a href="#" onClick="toDetailPage('+ data+')">'+data+'</a>';
		}
	}, 
  	{ "mDataProp": "actor", "aTargets": [ 2 ] } ,
  	{ "mDataProp": "userid","aTargets": [3]},
 	 {"mDataProp": "personuuid","aTargets": [4],'visible':false},
 	{"mDataProp": "accountTypeName","aTargets": [5]},	
 	{"mDataProp": "actResultName","aTargets": [6]},
 	{"mDataProp": "actAction","aTargets": [7]},
 	{"mDataProp": "actObj","aTargets": [8]},
 	{"mDataProp": "actTimeStr","aTargets": [9]},
 	{"mDataProp": "actMessage","bSortable": false,"aTargets": [10],
 		render : function(data,type,row){
 			return cutStr(data,20);
 		}	
 	},
 	{"bSortable": false,"aTargets": [11],"className":'details-control',
 		render : function(data,type,row){
 			return '';
 		}	
 	}
	];
	initDataTable(id,aocolumns,columns,sortColumns,sourceUrl,imgUrl,sort);
	
} );
function excelExport(){
	$("#queryForm").submit();
}
function toDetailPage(id){
	window.open("<c:url value='/sys/audit/showDetail'/>?id="+id);
}
function format(row){
	return "ID:"+row.actId+'<br/>用户名:'+row.userid+'<br/>用户ID:'+row.personuuid+'<br/>事件:'+row.actMessage;
}
</script>
</body>
</html>
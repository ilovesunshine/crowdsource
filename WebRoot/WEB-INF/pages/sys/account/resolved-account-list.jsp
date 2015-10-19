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
<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
	<div class="barTitle">
		<span>
			<a href="<c:url value='/sys/accountreg/account/resolveerror'/>">已开户信息</a>&nbsp;|&nbsp; 
		</span>
		已注销账户
	</div>
	<form id="queryForm"  class="bizform">
		<div class="row"> 
            <div class="cell">&nbsp;&nbsp;用户名：<input id="extUserName" name="extUserName" type="text" class="text" value="<c:out value='${payAccount.username}'/>"/>
			<button class="class_queryform_first button" type="button" onClick="">查询</button>
			</div>
		</div>
    
	<table id="resolvelist" style="table-layout:fixed" class="display">
        <thead>
          <tr>
           	<th width="30px;"><input type="checkbox" id="checkedAll" onClick="selectedAll('queryForm',this.checked)"/>选择</th>
            <th width="30px;">账户ID</th>
            <th width="20px;">JFID</th>
            <th width="30px;">用户名</th>
            <th width="60px;">账号</th>
            <th width="40px;">账户类别</th>
            <th width="50px;">原登录名</th>
            <th width="80px;">新登录名</th>
            <th width="80">身份证号</th>
            <th width="80">第一次开户时间</th>
            <th width="80">重新开户时间</th>
          </tr>
        </thead>
      </table>	
	 <!-- 设置  dailog end -->
	</form>
</div>
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<!-- 引入尾文件-->

<script type="text/javascript">
$(document).ready(function() {
	var imgUrl = "<c:url value='/styles/images/loading.gif'/>";
	var id = '#resolvelist';
	var columns = [];
	var sortColumns = 'ACCOUNT_ID,ACCOUNT_ID,JF_ID';
	var sort = [[ 1, "desc" ]];
	var sourceUrl = "<c:url value='/sys/accountreg/account/resolvedlistAccount'/>";
	var aocolumns = [ 
						 { "mDataProp": "accountId","bSortable": false, "aTargets": [ 0 ], 
						 render: function (data, type, row) {   // o, v contains the object and value for the column
								//
								return '<input name="ids" type="checkbox" value="'+data+
								'" id="someCheckbox"+'+data+' onchange="changCheckboxChecked(\'deleteids\',\'queryForm\',\'checkedAll\');"'
								+' name="someCheckbox" />';
							},
						 }, 
						 {"mDataProp": "accountId", "aTargets": [ 1 ]},
						 {"mDataProp": "jfId", "aTargets": [ 2 ]},
						 {"mDataProp": "extUserName", "aTargets": [ 3 ],"bSortable": false},
						 {"mDataProp": "extCardNo", "aTargets": [ 4 ],"bSortable": false},
						 {"mDataProp": "extUserTypeName", "aTargets": [ 5 ],"bSortable": false},
						 {"mDataProp": "entername", "aTargets": [ 6 ],"bSortable": false},
						 {"mDataProp": "newEnterName", "aTargets": [ 7 ],"bSortable": false},
						 {"mDataProp": "extIdCard", "aTargets": [ 8 ],"bSortable": false},
						 {"mDataProp": "createTimeStr", "aTargets": [ 9 ],"bSortable": false},
						 {"mDataProp": "handleTimeStr", "aTargets": [ 10 ],"bSortable": false}
						 
      	                ];
	initDataTable(id,aocolumns,columns,sortColumns,sourceUrl,imgUrl,sort);
} );
</script>
</body>
</html>
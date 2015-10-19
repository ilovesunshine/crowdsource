<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<title>管理员</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${csssuffix}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${csssuffix}'/>" media="screen, projection"/> 
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/themeDS.${csssuffix}'/>"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/common.${csssuffix}'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/menu.${csssuffix}'/>"/>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
<body>
<div class="pay_cont_sys">
	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
	<div class="barTitle">
		<span>管理员</span>
	</div>
	<form id="queryForm" class="bizform" action="<c:url value='/sys/admins/show'/>"  method="post" >
		<!--  <div class="row"> 
            <div class="cell">&nbsp;&nbsp;用户名：<input id="userName" name="userName" type="text" class="text" value="<c:out value='${payAdmins.userName}'/>"/><button id="query_button" class="button" type="button" onclick="query();">查询</button>
			</div>
		</div>-->
		<div class="row">
		<div class="query-cell">
        	<button id="addBtn" class="button" type="button" onclick="add();">新增</button>
        	<button id="editBtn" class="button" type="button" onclick="edit();">编辑</button>
		    <button id="deleteBtn" class="button" type="button" onclick="batchDelete();">删除</button>
	    </div>
	    </div>
    
	<table id="adminlist" style="table-layout:fixed" class="display">
        <thead>
          <tr>
          	<th width="15px;"><input type="checkbox" id="checkedAll" onClick="selectedAll('queryForm',this.checked)"/>选择</th>
            <th width="150px;">用户名</th>
            <th width="90px;">角色</th>
            <th width="50px;">状态</th>
            <th width="80px;">最后登录时间</th>
            <th width="80px;">添加时间</th>
            <th width="20px;">是否删除</th>
          </tr>
        </thead>
      </table>
      </form>
      <div id="dialog" title="选择记录" style="display:none;valign:middle" >
		  请选择一条记录进行操作。
	  </div>
	  <div id="deletedialog" title="删除记录" style="display:none;valign:middle" >
		 请选择要删除的记录。
	  </div>
</div>
<script type="text/javascript">
$(document).ready(function() {
	var sourceUrl = "<c:url value='/sys/admins/list'/>";
	var imgUrl = "<c:url value='/styles/images/loading.gif'/>";
    var sortColumns= "SA_ID,USER_NAME,USER_ROLE,USER_STATUS,LAST_LOGIN_TIME,CREATE_TIME,DELETE_FLAG";
    var sort = [[ 0, "desc" ]];
    /*
	data: The data for the cell
	type: this will be 'filter', 'display', 'type' or 'sort'.
	row: The full data source for the row (not based on columns.data)
	meta: An object that contains additional information about the cell being requested. This object contains the following properties:
		row - The row index for the requested cell. See row().index().
		col - The column index for the requested cell. See column().index().
		settings - The DataTables.Settings object for the table in question. This can be used to obtain an API instance if required.
	*/
    function renderCheckBox(data, type, row, meta){
    	return '<input name="ids" type="checkbox" value="'+data+
		'" id="someCheckbox"+'+data+' onchange="changCheckboxChecked(\'ids\',\'queryForm\',\'checkedAll\');"'
		+' name="someCheckbox" />';
	}
    function renderRole(data, type, row, meta){
		var string = '未知';
		if(data=='1'){
			string = "运营管理员";
		}else if(data=='2'){
			string = "系统管理员";
		}else if(data=='3'){
			string = "运营专员";
		}
		return string;	
	}
	function renderStatus(data, type, row, meta){
		var string = '未知';
		if(data=='0'){
			string = "锁定";
		}else if(data=='1'){
			string = "正常";
		}
		return string;
	}
	function renderDelFlag(data, type, row, meta){ 
		var string = '未知';
		if(data=='0'){
			string = "否";
		}else if(data=='1'){
			string = "是";
		}
		return string;
	}
	function renderLastTime(data, type, row, meta){ 
		if(data != null && data != ''){
			return Date.formate("yyyy-MM-dd hh:mm:ss",new Date(data.time));
		}else{
			return data;
		}
	}
	function renderCreateTime(data, type, row, meta){ 
		if(data != null && data != ''){
			return Date.formate("yyyy-MM-dd hh:mm:ss",new Date(data.time));
		}else{
			return data;
		}
	}
	var aocolumns = [ 
	    {"mDataProp": "saId", "bSortable": false},
		{"mDataProp": "userName"},
		{"mDataProp": "userRole"},
		{"mDataProp": "userStatus"},
		{"mDataProp": "lastLoginTime"},
		{"mDataProp": "createTime"},
		{"mDataProp": "deleteFlag"}
	];
	var columnDefs = [ 
		{"aTargets": [0], "render": renderCheckBox},
		{"aTargets": [2], "render": renderRole},
		{"aTargets": [3], "render": renderStatus},
		{"aTargets": [4], "render": renderLastTime},
		{"aTargets": [5], "render": renderCreateTime},
		{"aTargets": [6], "render": renderDelFlag}
	];

	initDataTable("#adminlist",aocolumns,columnDefs,sortColumns,sourceUrl,imgUrl,sort);
   
});
function query(){
	
}
function add(){
	location.href="<c:url value='/sys/admins/add'/>";
}
function edit(){
	if($("[name='ids']:checked").length==1){
		location.href="<c:url value='/sys/admins/edit'/>?id="+$("[name='ids']:checked").attr("value");
	}else{
		$("#dialog").dialog({
			buttons: [{ text: "确定", click: function() {$( this ).dialog("close");}}] 
		});
	  }
}
function batchDelete(){
	 var count = $("[name='ids']:checked").length;
	 if(count==0){
		 $("#deletedialog").dialog({
				buttons: [{ text: "确定", click: function() {$( this ).dialog("close");}}] 
		 });
		 return;
	 }
	 var now = 0;
	 var ids = '';
	 $("[name='ids']:checked").each(function(){
		 now ++;
		 ids+=$(this).val();
		 if(now!=count){
			 ids+=','
		 }
	 });
	 location.href="<c:url value='/sys/admins/delete'/>?ids="+ids;
}
function exportExcel(){
	$("#queryForm").submit();
}
function detail(id){
	location.href="<c:url value='/sys/admins/showDetail'/>?id="+id;
}
</script>
</body>
</html>
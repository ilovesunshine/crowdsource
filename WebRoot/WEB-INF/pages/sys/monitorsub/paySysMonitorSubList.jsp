<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统监控</title>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
<style>
	.jfpay-area-bt{
		margin-right:10px;
		float:right;
	}
	.info-back-span{
		display:block;
		float:right;
	}
	.jfpay-area-context{
		margin-top:20px;
	}
</style>
</head>
<body>
<div class="pay_cont_sys">
<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
	<div class="barTitle jfpay-area-context"> 
			<span>系统监控信息</span>
			<span style="float:right;margin-right:10px;"><button id="back_button" class="button"  type="button" onclick="javascript:history.go(-1);"><fmt:message key='button.back'/></button></span>
	</div>
	
		<form id="queryForm" >
			<input name="status" type="hidden" value="<c:out value='${status }'/>">
			<input name="sysMonitorId" type="hidden" value="<c:out value='${sysMonitorId }'/>">
			
		<table id="monitorsublist" style="table-layout:fixed" class="display">
        <thead>
          <tr>
          	<th width="90px;">主键</th>
            <th width="90px;">模块</th>
            <th width="150px;">操作</th>
            <th width="50px;">操作人</th>
            <th width="80px;">操作时间</th>
            <th width="50px;">状态</th>
            <th width="80px;">创建时间</th>
          </tr>
        </thead>
      </table>
		</form>

</div>

<div id="errorDialog"></div>
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	var sourceUrl = "<c:url value='/sys/monitorsub/list'/>";
	var imgUrl = "<c:url value='/styles/images/loading.gif'/>";
    var sortColumns= "SYS_MONITOR_SUB_ID";
    var sort = [[ 0, "desc" ]];
    function renderFirst(data, type, row, meta){
    	return "<a href='<c:url value='/sys/monitorsub/detail'/>?sysMonitorSubId="+data+"'>"+data+"</a>";
	}
    function renderModel(data, type, row, meta){
    	var string = '未知';
		if(data == '0'){
			return "操作";
		}else if(data == '1'){
			return "接口";
		}else if(data == '2'){
			return "服务";
		}
		return string;	
	}
	function renderStatus(data, type, row, meta){
		var string = '未知';
		if(data == '0'){
			return "失败";
		}else if(data == '1'){
			return "成功";
		}else if(data == '2'){
			return "处理中";
		}
		return string;
	}
	function renderOperateTime(data, type, row, meta){ 
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
	    {"mDataProp": "sysMonitorSubId", "bSortable": false},
		{"mDataProp": "model"},
		{"mDataProp": "operateName"},
		{"mDataProp": "operator"},
		{"mDataProp": "operateTime"},
		{"mDataProp": "status"},
		{"mDataProp": "createTime"}
	];
	var columnDefs = [ 
		{"aTargets": [0], "render": renderFirst},
		{"aTargets": [1], "render": renderModel},
		{"aTargets": [4], "render": renderOperateTime},
		{"aTargets": [5], "render": renderStatus},
		{"aTargets": [6], "render": renderCreateTime}
	];

	initDataTable("#monitorsublist",aocolumns,columnDefs,sortColumns,sourceUrl,imgUrl,sort);
   
});
	
    
	function query(){
		paySysMonitorSubGrid.setGridParam({"page":"1"});
		paySysMonitorSubGrid.trigger('reloadGrid');
	}

</script>
</body>
</html>
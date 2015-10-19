<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<html>
<head>
<title>基础费率修改历史</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${csssuffix}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${csssuffix}'/>" media="screen, projection"/> 
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/themeDS.${csssuffix}'/>"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/common.${csssuffix}'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/menu.${csssuffix}'/>"/>
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
		<span>基础费率修改历史</span>
		<span class="jfpay-area-bt"><button id="editBtn" class="button" type="button" onclick="window.location = '<c:url value="/sys/feerate/show"/>'">返回</button></span>
	</div>
	<form id="payBaseFeeRateLogForm"  class="bizform">
		<input name="feeType" type="hidden" value="<c:out value='${feeType }'/>">
    <table id="feerateloglist" style="table-layout:fixed" class="display">
        <thead>
          <tr>
            <th width="30px;">费率类型</th>
            <th width="50px;">修改前费率</th>
            <th width="70px;">修改后费率</th>
            <th width="80px;">修改人JFID</th>
            <th width="80px;">修改人名称</th>
            <th width="80px;">备注</th>
            <th width="70px;">修改时间</th>
          </tr>
        </thead>
      </table>
     </form>
</div>

<div id="errorDialog"></div>
<script type="text/javascript">
$(document).ready(function() {
	var imgUrl = "<c:url value='/styles/images/loading.gif'/>";
	var id = '#feerateloglist';
	var sortColumns = 'BASE_FEE_RATE_LOG_ID';
	var sourceUrl = "<c:url value='/sys/feeratelog/list'/>";
	var sort = [[ 0, "desc" ]];
	function renderFeeType(data, type, row, meta){
		var string = '未知';
		if(data == '0'){
			return "接包方服务费率";
		}else if(data == '1'){
			return "发包方服务费率";
		}
		return string;
	}
	function renderFeeRate(data, type, row, meta){
		var val = '--';
		if(data != null){
			val = data*100;
			return val + "%";
		}
		return val;
	}
	function renderCreateTime(data, type, row, meta){ 
		if(data != null && data != ''){
			return Date.formate("yyyy-MM-dd hh:mm:ss",new Date(data.time));
		}else{
			return data;
		}
	}
	var aocolumns = [
		{"mDataProp": "feeType"},
		{"mDataProp": "feeRateOld"},
		{"mDataProp": "feeRateNew"},
		{"mDataProp": "jfId"},
		{"mDataProp": "jfName"},
		{"mDataProp": "memo"},
		{"mDataProp": "createTime"}
	];
	var columnDefs = [ 
	    {"aTargets": [0], "render": renderFeeType},
	    {"aTargets": [1], "render": renderFeeRate},
	    {"aTargets": [2], "render": renderFeeRate},
	    {"aTargets": [6], "render": renderCreateTime}
	];
	initDataTable(id,aocolumns,columnDefs,sortColumns,sourceUrl,imgUrl,sort);
} );
    
	function query(){
		payBaseFeeRateLogGrid.setGridParam({"page":"1"});
		payBaseFeeRateLogGrid.trigger('reloadGrid');
	}

	function batchDelete(rowid){
		var rowid = payBaseFeeRateLogGrid.getGridParam('selarrrow');
		if(rowid == null || rowid.length == 0){
			showInfo("<fmt:message key='grid.delete.chooseColAlert'/>",3000);
			return ;
		}
		new biz.alert({type:"confirm",message:I18N.msg_del_confirm,
			title:I18N.promp,callback:function(result){
	    		if (!result) {
	    			return;
	    		}
	    		var ids="";
	    		for(var i=0; i < rowid.length;i++){
	    			var id = payBaseFeeRateLogGrid.getCell(rowid[i],"baseFeeRateLogId");
	    			if(i>0){
	    				ids +=',';
	    			}
	    			ids += id;
	    		}
	    		var dataUrl = "payBaseFeeRateLog/delete/" + ids;
				$.ajax({
					url: dataUrl,
					cache:false,
					success: function(data, textStatus, jqXHR){
						query();
						showInfo("<fmt:message key='delete.success'/>",3000);
					},
					error:biz.utils.loadError
				});
			}
		});
	}
	function goBack(){
		window.location="queryPayBaseFeeRate";
	}

</script>
</body>
</html>
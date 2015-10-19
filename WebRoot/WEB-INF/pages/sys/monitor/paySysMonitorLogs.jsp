<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/inc/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>系统监控</title>
<%@ include file="/pages/inc/header_sys.jsp" %>
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
<%@ include file="/pages/inc/common.jsp" %>
<div class="pay_cont_sys">
<%@ include file="/pages/inc/menu_sys.jsp" %>
	<div class="barTitle jfpay-area-context"> 
			<span>系统监控日志</span>
			<span style="float:right;margin-right:10px;"><button id="back_button" class="button"  type="button" onclick="javascript:history.go(-1);"><fmt:message key='button.back'/></button></span>
	</div>
	<form id="paySysMonitorForm" class="bizform">
		<div class="row"> 
			<div class="cell">
				<div class="valuecell">
					<span style="margin-left:10px;">日期：</span>
					<input id="date" name="date" type="text" class="Wdate" style="width:100px;" value="${showdate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" /> 
					<button id="query_button" class="button" style="margin-left:10px;" type="button" onclick="query();">查询</button>
				</div>
			</div>
		</div>
	</form>
    <div class="ui-table ui-margin">
	    <table id="paySysMonitorTable"></table>
		<div id="paySysMonitorPager"></div>
	</div>
</div>

<div id="errorDialog"></div>
<%@ include file="/pages/inc/footer.jsp" %>
<script type="text/javascript">
	
	var paySysMonitorGrid;
    $(function(){
    	paySysMonitorGrid = new biz.grid({
    		id : "#paySysMonitorTable",
    		url : "paySysMonitor/listPaySysMonitorLogs",
			rowNum : 50,
			width:"auto",
			multiselect:false,
    		pager : "#paySysMonitorPager",
    		prmNames : {page:"pageNo",rows:"pageSize",sort:"orderFields",order:"order"},
    		sortname : "",
    		sortorder : "",
    		colModel : [
    			{name : "sysMonitorId", hidden:true,key:true}, 
				//{name : "sysMonitorId",label:"主键"},
				{name : "model",label:"模块",align:"center",
					formatter: function(value, options, row){
						if(value == '0'){
							return "操作";
						}else if(value == '1'){
							return "接口";
						}else if(value == '2'){
							return "服务";
						}
					}
				},
				{name : "operateName",label:"操作"},
				{name : "optSuccess",label:"成功数量",align:"center"},
				{name : "optFail",label:"失败数量",align:"center",
					formatter: function(value, options, row){
						if(value == 0){
							return value;
						}
						var accountInfo = "<a href=\"<c:url value='/paySysMonitorSub'/>?sysMonitorId=" + row.sysMonitorId + "\" style=\"color:blue;text-decoration:underline;\">" + value + "</a>";
						return accountInfo;
					}
				},
				{name : "optTotal",label:"合计",align:"center"},
				{name : "createTime",label:"创建时间",align:"center"}
    		],
    		serializeGridData:function(postData){//添加查询条件值
				var obj = biz.utils.serializeObject("#paySysMonitorForm");//示例中心搜索"biz.utils方法详解"查看方法说明
    			$.extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		}
    	});
    })
    
	function query(){
    	var date = $("#date").val();
    	if(date == null || date == ''){
    		new biz.alert({
				type : "alert",//信息提示类型，可选alert、confirm、prompt
				message : "请选择日期！",//提示信息
				title : "提示" //提示框标题
			});
			return;
        }
		paySysMonitorGrid.setGridParam({"page":"1"});
		paySysMonitorGrid.trigger('reloadGrid');
	}

</script>
</body>
</html>
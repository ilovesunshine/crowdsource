<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="page.title"/></title>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${webConfig["csssuffix"]}'/>" media="screen, projection"/> 
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/themeDS.${webConfig["csssuffix"]}'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/components/jqgrid/jquery.jqgrid.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/components/tree/zTreeStyle.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/common.${webConfig["csssuffix"]}'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/menu.${webConfig["csssuffix"]}'/>"/>
<script type="text/javascript" src="<c:url value='/js/extends/utils/DatePattern.${webConfig["jssuffix"]}'/>"></script>	
<style>
.query-cell{display:inline;width:200px;}
.query-value{display:inline !important;width:100px;}
.query-label{display:inline !important;width:100px;}
</style>
</head>

<body>
<div class="pay_cont_sys">
	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
	<div class="barTitle">
		EBC交易日志&nbsp;|&nbsp;
		<span>
			<a href="<c:url value='/sys/statement/withdraw'/>">提现流水对账</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/statement/recharge'/>">充值流水对账</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/statement/cards'/>">绑定银行卡对账</a>&nbsp;|&nbsp;
			<a href="<c:url value='/sys/statement/showfails'/>">交易流水</a> 
		</span>
	</div>
	<div id="conditions" class="ui-table ui-widget ui-corner-all ui-margin" style="display:block">
		<form id="listForm"  class="bizform">
		</form>
	</div>

	<div class="ui-table ui-margin">
    	<div class="nav">
	    </div>
	    <table id="listTable"></table>
		<div id="listPager"></div>
	</div>
</div>
<!-- 引入尾文件-->
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<script type="text/javascript">
	new biz.button({id:"#query_button"});
	new biz.button({id:"#addBtn"});
	new biz.button({id:"#editBtn"});
	new biz.button({id:"#viewBtn"});
	new biz.button({id:"#deleteBtn"});
	new biz.button({id:"#exportBtn"});

	//显示或隐藏查询条件
    $(document).ready(function(){
		$("#displayHidden").click(function(){
		       var conditionsDiv = document.getElementById("conditions");
		       var displayHidden = document.getElementById("displayHidden");
		       if(conditionsDiv.style["display"]=="block"){
		          conditionsDiv.style["display"]="none";
		          displayHidden.innerHTML="<fmt:message key='query.ExpandCondition'/>";
		       }else if(conditionsDiv.style["display"]=="none"){
		          conditionsDiv.style["display"]="block";
		          displayHidden.innerHTML="<fmt:message key='query.hideCondition'/>";
		       }
		});
	});

	function fmtActResult(data, type, row, meta){
		if(data=='00'){
			return "处理中";
		}else if(data=='01'){
			return "提现成功";
		}else if(data=='02'){
			return "提现失败";
		}else{
			return data;
		}
	}

	function fmtTime(data, type, row, meta){
		if(data){
			return new Date(data).pattern("yyyy-MM-dd HH:mm:ss");
		}
		return data;
	}

	var listGrid;
    $(function(){
    	listGrid = new biz.grid({
    		id : "#listTable",
    		url : "list?transType=withdraw",
    		page : "<c:out value='${page.pageNo}' default='1'/>",
    		rowNum : "<c:out value='${page.pageSize}' default='15'/>",
			width : "auto",
			multiselect : true,
    		pager : "#listPager",
    		prmNames : {page:"pageNo",rows:"pageSize",sort:"orderFields",order:"order"},
    		sortname : "",
    		sortorder : "",
    		colModel : [
			    {name : "withdrawId",hidden:true,key:true}, 
				{name : "withdrawBn",label:"流水号",width:40},
				{name : "accountId",label:"账户ID",width:40},
				{name : "accountName",label:"姓名",width:60},
				//{name : "withdrawType",label:"提现类别",width:30,align:"center"},
				{name : "bankName",label:"银行",width:50},
				{name : "bankCardNo",label:"卡号",width:50,align:"left"},
				{name : "amount",label:"金额",width:30,align:"right"},
				{name : "withdrawCharge",label:"手续费",width:30,align:"right"},
				//{name : "amountEn",label:"金额En",width:30,align:"right"},
				//{name : "withdrawChargeEn",label:"金额En",width:30,align:"left"},
				{name : "withdrawState",label:"提现状态","render":fmtActResult,width:30,align:"center"},
				{name : "extResultCode",label:"响应码",width:40,align:"center"},
				{name : "extResultNote",label:"异常",width:40,align:"left"},
				{name : "extOrderid",label:"Orderid",width:40,align:"left"},
				{name : "extCashid",label:"Cashid",width:40,align:"left"},
				{name : "extSyssn",label:"Syssn",width:40,align:"left"},
				{name : "extResultTime",label:"响应时间",width:40,align:"center"},
				{name : "createTime",label:"创建时间",width:40,align:"center"},
				{name : "updateTime",label:"更新时间",width:40,align:"center"}
			],		
    		serializeGridData:function(postData){//添加查询条件值
    			//var obj = biz.utils.serializeObject("#listForm");//示例中心搜索"biz.utils方法详解"查看方法说明
     			var obj = getQueryCondition();
   				$.extend(true,obj,postData);//合并查询条件值与grid的默认传递参数
    			return obj;
    		}
    	});

    })

	function getQueryCondition(){
        var obj = {};
		//addAttrToObject(obj,"interfaceName");
		//addAttrToObject(obj,"reqMessage");
		//addAttrToObject(obj,"respMessage");
		//addAttrToObject(obj,"state");
		return obj;
    }

	function addAttrToObject(obj,name){
		element = document.getElementById(name);
		var notInputValue="";
		if(element!=null){
			if(element.value==null||element.value == ""){
				if($(element).attr("uiType")!=null){
					if($(element).attr("uiType")=="checkbox"){
						var tempCheckBoxObj = $(element).find(".checkbox");
						if(tempCheckBoxObj!=null && tempCheckBoxObj.length>0){
							tempCheckBoxObj.each(function(){
								if(this.checked) {
									notInputValue  += this.value+",";
								}
							  });
							if(notInputValue!=null && notInputValue.indexOf(",")!=-1){
								notInputValue = notInputValue.substring(0,notInputValue.length-1);
							}
					
						}
					}else if($(element).attr("uiType")=="radio"){
						var tempRadioObj = $(element).find(".radio");
						if(tempRadioObj!=null && tempRadioObj.length>0){
							  tempRadioObj.each(function(){
								if(this.checked) {
									notInputValue  = this.value;
									return;
								}
							  });
						}		  
					}
					if(notInputValue == null || notInputValue == "" ){
						  return;
					}
				}else{
                   return;
				}
			}
		}else{
			return;
		}

		obj = obj || {}; 
		var value = element.value!=null?element.value:notInputValue;
		if(name=='reqMessage' || name=='respMessage' || name=='interfaceName'){
			value = "%"+value+"%";
		}
		obj[name] = value;
	}

	function query(){
		listGrid.setGridParam({"page":"1"});
		listGrid.trigger('reloadGrid');
	}

	//导出Excel
	function excelExport(){
		roNotificationsGrid.excelExport(getQueryCondition());
	}
	
	function add(){
		window.location="sys/audit/add";
	}
	
	function edit(rowid){
		rowid = listGrid.getGridParam("selarrrow");
		if(rowid == null || rowid.length == 0){
			showInfo("<fmt:message key='grid.edit.chooseColAlert'/>",3000);
			return ;
		}else if(rowid.length > 1){
			showInfo("<fmt:message key='grid.edit.onlyOne'/>",3000);
			return ;
		}
		var id = listGrid.getCell(rowid[0],"actId");
		window.location="sys/audit/edit/" + id;
	}

	function view(){
		var rowid = listGrid.getGridParam("selarrrow");
		if(rowid == null || rowid.length == 0){
			showInfo("<fmt:message key='grid.view.chooseColAlert'/>",3000);
			return ;
		}else if(rowid.length > 1){
			showInfo("<fmt:message key='grid.view.onlyOne'/>",3000);
			return ;
		}
		var id = listGrid.getCell(rowid[0],"actId");
		window.location="sys/audit/show?id=" + id;
	}

	function batchDelete(rowid){
		var rowid = listGrid.getGridParam('selarrrow');
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
					var id = listGrid.getCell(rowid[i],'actId');
					if(i>0){
						ids +=',';
					}
					ids += id;
				}
				window.location="sys/audit/delete/" + ids;
			}
		});
	}
	
</script>
</body>
</html>
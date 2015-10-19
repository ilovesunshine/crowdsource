<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="page.title"/></title>
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
		提现流水对账&nbsp;|&nbsp;
		<span>
			<a href="<c:url value='/sys/statement/recharge'/>">充值流水对账</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/statement/cards'/>">绑定银行卡对账</a>&nbsp;|&nbsp;
			<a href="<c:url value='/sys/statement/showfails'/>">交易流水</a>&nbsp;|&nbsp;  
			<a href="<c:url value='/sys/statement/ebclogs'/>">EBC交易日志</a>  
		</span>
	</div>
	<form id="queryForm"  class="bizform">
	<div id="conditions" class="ui-table ui-widget ui-corner-all ui-margin" style="display:block">
		
	</div>

	<table id="withdrawlist" style="table-layout:fixed" class="display">
        <thead>
          <tr>
            <th width="60px;">流水号</th>
            <th width="40px;">账户ID</th>
            <th width="50px;">姓名</th>
            <th width="80px;">银行</th>
            <th width="80px;">卡号</th>
            <th width="30px;">金额</th>
            <th width="40px;">手续费</th>
            <th width="50px;">提现状态</th>
            <th width="40px;">响应码</th>
            <th width="60px;">异常</th>
            <!--  
            <th width="80px;">Orderid</th>
            <th width="80px;">Cashid</th>
            <th width="80px;">Syssn</th>
            -->
            <th width="60px;">响应时间</th>
            <th width="60px;">创建时间</th>
            <th width="60px;">更新时间</th>
          </tr>
        </thead>
      </table>
     </form>
</div>
<!-- 引入尾文件-->
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	var sourceUrl = "<c:url value='/sys/statement/withdraw/list'/>";
	var imgUrl = "<c:url value='/styles/images/loading.gif'/>";
    var sortColumns= "WITHDRAW_ID,ACCOUNT_ID,ACCOUNT_NAME,BANK_NAME,BANK_CARD_NO,"+
    	"AMOUNT,WITHDRAW_CHARGE,WITHDRAW_STATE,EXT_RESULT_CODE,EXT_RESULT_NOTE,EXT_RESULT_TIME,CREATE_TIME,UPDATE_TIME";
    var sort = [[ 0, "desc" ]];
	var aocolumns = [ 
		{"mDataProp": "withdrawBn"},
		{"mDataProp": "accountId"},
		{"mDataProp": "accountName"},
		{"mDataProp": "bankName"},
		{"mDataProp": "bankCardNo"},
		{"mDataProp": "amount"},
		{"mDataProp": "withdrawCharge"},
		{"mDataProp": "withdrawState",
			render : function(data, type, row){
				return fmtActResult(data, type, row);
			}
		},
		{"mDataProp": "extResultCode"},
		{"mDataProp": "extResultNote","bSortable": false},
		//{"mDataProp": "extOrderid"},
		//{"mDataProp": "extCashid"},
		//{"mDataProp": "extSyssn"},
		{"mDataProp": "extResultTime",
			render : function(data, type, row){
				return fmtTime(data, type, row);
			}
		},
		{"mDataProp": "createTime",
			render : function(data, type, row){
				return fmtTime(data, type, row);
			}
		},
		{"mDataProp": "updateTime",
			render : function(data, type, row){
				return fmtTime(data, type, row);
			}	
		}
	];
	var columnDefs = [ ];

	initDataTable("#withdrawlist",aocolumns,columnDefs,sortColumns,sourceUrl,imgUrl,sort);
   
});

	function fmtActResult(data, type, row){
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

	function fmtTime(data, type, row){
		if(data){
			return Date.formate("yyyy-MM-dd hh:mm:ss",new Date(data.time));  
		}
		return data;
	}

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
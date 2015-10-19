<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="page.title"/></title>
<%@ include file="/WEB-INF/pages/inc/header_sys.jsp" %>
<%@ include file="/WEB-INF/pages/inc/shade.jsp" %>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${csssuffix}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${csssuffix}'/>" media="screen, projection"/> 
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/themeDS.${csssuffix}'/>"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/common.${csssuffix}'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/skin/jf_skin/extends/menu.${csssuffix}'/>"/>	

</head>

<body>
<%@ include file="/WEB-INF/pages/inc/shade.jsp" %>
<div class="pay_cont_sys">
	<%@ include file="/WEB-INF/pages/inc/menu_sys.jsp" %>
	<div class="barTitle">
		交易流水&nbsp;|&nbsp;
		<span>
			<a href="<c:url value='/sys/statement/recharge'/>">充值流水对账</a>&nbsp;|&nbsp; 
			<a href="<c:url value='/sys/statement/withdraw'/>">提现流水对账</a>&nbsp;|&nbsp;  
			<a href="<c:url value='/sys/statement/cards'/>">绑定银行卡对账</a>&nbsp;|&nbsp;  
			<a href="<c:url value='/sys/statement/ebclogs'/>">EBC交易日志</a> 
		</span>
	</div>
	
	<div id="err_bindCard" class="add_bg" style = "width:100%;margin-top:15px;">
		 <div class="add_icon"><span id = "errorMsg">重新支付前，请务必与第三方对账文件进行对账，确保此交易在第三方未处理或处理失败！</span></div>	
    </div>
    <form id="queryForm" class="bizform">
		<div class="row"> 
		<div class="query-cell">
			<div class="cell" style="padding-left:8px;">订单状态：
				 <select name="state" id = "state" >
				  <option value = "">全部 </option>
				  <option value = "00">未支付 </option>
				  <option value = "01">支付成功 </option>
				  <option value = "02">支付失败</option>
				  <option value = "06">支付过期</option>
				  <option value = "07">已回收</option>
				 </select>
			</div>
		</div>
		
		<div class="query-cell">
			<div class="cell" style="padding-left:8px;">交易流水号：
				<input id="transId" class="text" type="text" value="" name="transId">
			</div>
		</div>
		
		<div class="query-cell">
			<div class="cell" style="padding-left:8px;">收款人姓名：
			<input id="payeeAccountName" class="text" type="text" value="" name="payeeAccountName">
			</div>
		</div>

		<div class="query-cell">
			<div class="cell" style="padding-left:8px;">
				<button id="query_button" class="class_queryform_first kbtn kbtn_green kbtn30" onclick="" type="button" >
				 	查询
				</button>
				<c:if test="${ !flag }">
				<button class="kbtn kbtn_green kbtn30"  onclick="handleFee();" type="button" >
				 	服务费处理
				</button>
				<div id="effect" class="toggler">
				     	 <c:out value="${ret.msg}"/>
				  </div>
				</c:if>
			</div>
	</div>
	
	</div>
	<!-- 重新支付对话框 Start -->
	<div id="creditlineSettingDialog" class="pay_div_hidden" >
        <div id="exportMsg" style = "align:center;font-size:20px;margin-left: 140px; margin-top: 72px;">  </div>
	</div>
	<div id="repaydialog" title="重新支付" style="display:none;valign:middle" >
		确认重新支付？
	</div>
	<div id="handleFeedialog" title="服务费处理" style="display:none;valign:middle" >
		您确定更新交易表中的服务费数据吗？
	</div>
	<!-- 重新支付对话框 End -->
	
	<table id="adminlist" style="table-layout:fixed" class="display">
        <thead>
          <tr>
            <th width="50px;">流水号</th>
            <th width="60px;">订单|红包编号</th>
            <th width="50px;">订单|红包名称</th>
            <th width="50px;">付款方jfId</th>
            <th width="60px;">付款方名称</th>
            <th width="60px;">收款方jfId</th>
            <th width="60px;">收款方名称</th>
            <th width="60px;">金额</th>
            <th width="60px;">交易类型</th>
            <th width="60px;">创建时间</th>
            <th width="60px;">交易状态</th>
            <th width="40px;">操作</th>
          </tr>
        </thead>
     </table>
    </form>
</div>
<!-- 引入尾文件-->
<%@ include file="/WEB-INF/pages/inc/footer.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	var sourceUrl = "<c:url value='/sys/statement/failed/list'/>";
	var imgUrl = "<c:url value='/styles/images/loading.gif'/>";
    var sortColumns= "T_ID,ORDER_NO,ORDER_NAME,PAYER_JFID,PAYER_ACCOUNT_NAME,PAYEE_JFID,PAYEE_ACCOUNT_NAME,"+
    "TRANS_AMOUNT,PAY_TYPE,CREATE_TIME,STATE";
    var sort = [[ 0, "desc" ]];
    function renderActType(data, type, row, meta){
		if(data=='03'){
			return "红包支付";
		}else{
			return "订单支付";
		}
	}
	function renderState(data, type, row, meta){
		if(data=='00'){
			return "未支付";
		}else if(data=='01'){
			return "支付成功";
		}else if(data=='02'){
			return "支付失败";
		}else if(data=='06'){
			return "支付过期";
		}else if(data=='07'){
			return "已回收";
		}else{
			return "未知";
		}
	}
	var aocolumns = [ 
	    //{"mDataProp": "tId", "bSortable": false},
		{"mDataProp": "transId"},
		{"mDataProp": "orderNo"},
		{"mDataProp": "orderName"},
		{"mDataProp": "payerJfId"},
		{"mDataProp": "payerAccountName"},
		{"mDataProp": "payeeJfId"},
		{"mDataProp": "payeeAccountName"},
		{"mDataProp": "transAmount"},
		{"mDataProp": "payType"},
		{"mDataProp": "createTime"},
		{"mDataProp": "state"}
	];
	var columnDefs = [ 
		{"aTargets": [8], "render": renderActType},
		{"aTargets": [10], "render": renderState} ,
		{"aTargets": [9], "render": fmtTime},
		{"aTargets": [11], "render": fmtButton}
	];

	initDataTable("#adminlist",aocolumns,columnDefs,sortColumns,sourceUrl,imgUrl,sort);
   
});
	

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
		//$("#conditions").remove();
	});

	function fmtActResult(data, type, row, meta){
		if(data=='03'){
			return "红包支付";
		}else{
			return "订单支付";
		}
	}

	function fmtState(data, type, row, meta){
		if(data=='00'){
			return "未支付";
		}else if(data=='01'){
			return "支付成功";
		}else if(data=='02'){
			return "支付失败";
		}else if(data=='06'){
			return "支付过期";
		}else if(data=='07'){
			return "已回收";
		}else{
			return "未知";
		}
	}

	function fmtTime(data, type, row){
		if(data){
			return Date.formate("yyyy-MM-dd hh:mm:ss",new Date(data.time));
		}
		return data;
	}

	//重新支付方法
	function reFund(tId,jfId){
		 //确认框
		$("#repaydialog").dialog({
				buttons: [
				          	{ text: "确定", click: function() {
					          		$( this ).dialog( "close" );
					          		$("#exportMsg").html("重新支付中...");
					          		$("#creditlineSettingDialog").dialog({
					          			open: function() {
					          				refundMthod(jfId,tId)
					          	         },
					          			}
					          		);
				         		} 
				          	},
				          	{ text: "取消", click: function() {
	          						$( this ).dialog( "close" );
	          					} 
	          				}
				          ] 
			});
	}

	function refundMthod(jfId,tId){
		//ajax请求
		$.ajax({
		          url :"<c:url value='/opt/accounts/reFundPerBouns'/>",
		          type: "POST",
		          cache : false,
		          async : true,
		          data : {
                      "jfId" : jfId,
                      "tId" : tId
              },
              success : function(data) {
                 if(data.returncode == '00'){
                  	$("#exportMsg").html("重新支付成功...");
                  	 window.setTimeout(function(){
                  		$("#creditlineSettingDialog").dialog( "close" );
                  	 },1000);
                  	 //重新加载
                  	 $(".class_queryform_first").click();
                 }else{
                	 $("#exportMsg").html("重新支付失败...");
                     window.setTimeout(function(){
                  		$("#creditlineSettingDialog").dialog( "close" );
                  	 },1000);
                 }
                
              },
              error : function() {                   
                  	$("#exportMsg").html("重新支付失败...");
                  	window.setTimeout(function(){
                  		$("#creditlineSettingDialog").dialog( "close" );
                 	 },1000);
              }
        });
	}
	function fmtButton(data, type, row){

		var returnStr= "<input type='button' style='padding:0px 3px 0px 3px;' value='重新支付' onclick='reFund("+row.tId+","+row.payeeJfId+")' />";

		var state = row.state;
		if(state == "01" || state == "06" || state == "07"){
			return "—";
		}else{
			return returnStr;
		}
	}

	function getQueryCondition(){
        var obj = {};
		addAttrToObject(obj,"payeeAccountName");
		addAttrToObject(obj,"transId");
		addAttrToObject(obj,"state");
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

	function handleFee(){
		 //确认框
		$("#handleFeedialog").dialog({
				buttons: [
				          	{ text: "确定", click: function() {
				          		showShade();
				          		$.ajax({
							          url : "<c:url value='/sys/statement/fee/update'/>",
							          type: "POST",
							          cache : false,
							          async : true,
					                  success : function(data, textStatus) {
					      	    		  hideShade();
					      	    		$("#handleFeedialog").dialog( "close" );
						                  if(data.returncode == '00'){
						                      showInfo("数据更新成功，更新红包数据" + data.bounsCount + "条，更新订单数据" + data.orderCount + "条。",10000);
						                      setTimeout("reloadData()",4000);
						                  }else{
						                      showInfo("数据更新失败！",3000);
						                  }
					                  },
					                  error : function() {
					      	    		  hideShade(); 
					      	    		$("#handleFeedialog").dialog( "close" );
					                	  showInfo("数据更新失败！",3000);
					                  }
					             });
				         		} 
				          	},
				          	{ text: "取消", click: function() {
	          						$( this ).dialog( "close" );
	          					} 
	          				}
				          ] 
			});
	}

	function reloadData(){
		$(".class_queryform_first").click();
	}
</script>
</body>
</html>